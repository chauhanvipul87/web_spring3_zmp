package zapmyparcel.service.main;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import zapmyparcel.utility.common.CommonDAO;
import zapmyparcel.utility.common.GlobalVariables;
import zapmyparcel.utility.common.LogUtility;
import zapmyparcel.utility.date.DateTimeFormater;
import zapmyparcel.utility.db.DbUtils;
import java.util.UUID;


public class ManageCartService {

	Date date = new Date();
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String ErrorMsg="";
	Logger logger= Logger.getLogger(this.getClass().getName());
	GlobalVariables global;
	
	
	public ModelAndView addToCart(HttpServletRequest req, HttpServletResponse res) {
		String returnMsg="";
		ModelAndView model = new ModelAndView();
		LogUtility.logInfo("ManageCartService:addToCart Start:::" + date, logger);
		try{
			
			String weight       	 = req.getParameter("weight")			    	 == null ?  "1" : req.getParameter("weight");     // weight that is selected.
			String main_price     	 = req.getParameter("main_price")		    	 == null ?  "" : req.getParameter("main_price"); // price that is display as an offer
			String quantity  		 = req.getParameter("quantity")					 == null ?  "1" : req.getParameter("quantity");   // entered quantity
			String country_id  		 = req.getParameter("country_id")				 == null ?  "" : req.getParameter("country_id"); 
			
			
			HttpSession session = req.getSession(true);
			Float qty= Float.parseFloat(quantity);
			Float pricePerParcel=	 (Float) qrun.query(global.getSpecificOfferPrice,new ScalarHandler(),weight,weight,country_id);
			float subtotal = qty.intValue() * pricePerParcel;
			
			String  key="";
			String uuid ="",user_order_id="";
			if(session.getAttribute("user_key") == null){
				session = req.getSession(true);
				uuid= UUID.randomUUID().toString();
				//System.out.println("uuid = " + uuid);
				
				Integer key_awail = (Integer) qrun.query(global.checkAvailibilityKey,new ScalarHandler(),uuid); 
				if(key_awail == null){
					key =uuid;
				}
			}else{
				//System.out.println("in else.."+session.getAttribute("user_key"));
				if(session.getAttribute("user_key") != null){
					key = session.getAttribute("user_key").toString(); //when key already exists.
				}
				//session.removeAttribute("user_key"); // if want to remove key from session
				//System.out.println("key is "+key);
				
				if(session.getAttribute("user_order_id") != null){
					user_order_id = session.getAttribute("user_order_id").toString(); //when key already exists.
				}
			}
			
			Long order_exits = (Long) qrun.query(global.check_existOrdersByUser_Key,new ScalarHandler(),key);
			System.out.println("order_exits :: "+order_exits);
			if(order_exits ==0){
				int nextTempOrderID= new CommonDAO().getautoGenerateID("temporder", "temp_id");
				Object[] params ={nextTempOrderID,key,weight,country_id,quantity,pricePerParcel,subtotal,new DateTimeFormater().format_datetime(),new DateTimeFormater().format_datetime()};
				int affectedRows =  qrun.update(global.insert_temporder,params);	
				if(affectedRows > 0){
					new CommonDAO().setautoGenerateID("temporder", "temp_id",(nextTempOrderID+1)); 
					 returnMsg = GlobalVariables.addToCart_success;
					 model.addObject("errorClass",GlobalVariables.successClass);
					 model.addObject("ErrorMsg",returnMsg);
					 
				}else{
					 model.addObject("errorClass",GlobalVariables.errorClass);
					 model.addObject("ErrorMsg",GlobalVariables.error_Message);
				}
			}else{
				// get order ID
				Integer order_Id = (Integer)qrun.query(global.getOrderId,new ScalarHandler(),global.order_status_notified,key);
				System.out.println("order _id ="+order_Id);
				if(order_Id != null){
					int nextTempOrderID= new CommonDAO().getautoGenerateID("temporder", "temp_id");
					Object[] params ={nextTempOrderID,key,weight,country_id,quantity,pricePerParcel,subtotal,new DateTimeFormater().format_datetime(),new DateTimeFormater().format_datetime()};
					int affectedRows =  qrun.update(global.insert_temporder,params);
					if(affectedRows >0){
						new CommonDAO().setautoGenerateID("temporder", "temp_id",(nextTempOrderID+1)); 
						/// get temp order based on user_key
						List<Map<String,Object>> tempOrderList = qrun.query(global.getTempOrderByUserKey,new MapListHandler(),key);
						 for(int i=0;i<tempOrderList.size();i++){
							 Map<String,Object>  m  = tempOrderList.get(i);
						    	String temp_id         = m.get("temp_id").toString();
						    	String weight_temp 		   = m.get("temp_weight").toString();
						    	String country_id_temp      = m.get("country_id").toString();
						    	String temp_quantity   = m.get("temp_quantity").toString();
						    	String temp_price	   = m.get("temp_price").toString();
						    	String temp_subtotal   = m.get("temp_subtotal").toString();
						    	
						    	
						    	
						    	// get address id from delivery_address table
						    	Integer delivery_addressID = (Integer)qrun.query(global.selectDelivery_address,new ScalarHandler(),country_id_temp,key);
						    	if(delivery_addressID == null){
						    		delivery_addressID =0;
						    	}else{
						    		int affected =qrun.update(global.update_add_tempOrder,country_id,key);
						    		if(affected >0){
						    			System.out.println("countery is already exist just update the address flag in temporder");
						    		}
						    	}
						    	// insert into order_details table
						    	Object[] param = {order_Id,user_order_id,key,weight_temp,country_id_temp,temp_quantity,temp_price,temp_subtotal,new DateTimeFormater().format_datetime(),new DateTimeFormater().format_datetime(),delivery_addressID,temp_id};
						    	int affected_rows=qrun.update(global.insert_orderDetails,param);
//						    	if(affected_rows >0){
//						    		 affectedRows=qrun.update(global.remove_tempOrder,user_key,temp_id);
//						    	}
						    	if(affected_rows >0){
						    		 affectedRows=qrun.update(global.update_tempOrderFlag,temp_id,key);
						    	}
						    	
						    	
						 }
						if(affectedRows >0){
						
							List<Map<String,Object>> order_data = qrun.query(global.getOrderData_display,new MapListHandler(),key,global.order_status_notified);
							model.addObject("for getting order_data",order_data);
							String insurance_cover ="";
							if(order_data != null){
								for(int i=0;i<order_data.size();i++){
									Map<String,Object> m =order_data.get(i);
									insurance_cover=m.get("insurance_cover").toString();
								}
								Double subTotal = (Double) qrun.query(global.cartsubtotal_query,new ScalarHandler(),key);
								System.out.println("subTotal ::"+subTotal);
					
								Float insurance = Float.parseFloat(insurance_cover);
								System.out.println("insurance_cover ::"+insurance_cover);
								
								Float vat = (20*(Float.parseFloat(subTotal+"")+(insurance))/100);
								System.out.println("vat ::"+vat);
								
								Float total_price =vat +Float.parseFloat(subTotal+"")+insurance;
								System.out.println("total_price ::"+total_price);
								
								affectedRows = qrun.update(global.updateCartPriceDetails_order,total_price,subTotal,vat,order_Id,global.order_status_notified,key);
								if(affectedRows >0){
									 returnMsg = GlobalVariables.addToCart_success;
									 model.addObject("errorClass",GlobalVariables.successClass);
									 model.addObject("ErrorMsg",returnMsg);
									
								}else{
									model.addObject("errorClass",GlobalVariables.errorClass);
									model.addObject("ErrorMsg",GlobalVariables.error_Message);
								}
							}else{
								model.addObject("errorClass",GlobalVariables.errorClass);
								model.addObject("ErrorMsg",GlobalVariables.error_Message);
							}
							
						}else{
							model.addObject("errorClass",GlobalVariables.errorClass);
							model.addObject("ErrorMsg",GlobalVariables.error_Message);
							}
						}
					}else{
						model.addObject("errorClass",GlobalVariables.errorClass);
						model.addObject("ErrorMsg",GlobalVariables.error_Message);
					}
					
				}
			 session.setAttribute("user_key",key);
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"ManageCartService:addToCart End:::" + e.getMessage(), logger);
			model.addObject("errorClass",GlobalVariables.errorClass);
			model.addObject("ErrorMsg",GlobalVariables.error_Message);
		}
		
		LogUtility.logInfo("ManageCartService:addToCart End:::" + date, logger);
		return model;
	}


	public ModelAndView showCart(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		LogUtility.logInfo("ManageCartService:showCart Start:::" + date, logger);
		try{
			
			String user_key       = req.getParameter("user_key") 	  == null ?    ""     : req.getParameter("user_key");
			String action  	      = req.getParameter("action") 	 	  == null ?    ""     : req.getParameter("action");  // when action is not null at that time place order button is disable this happen with placeorder.html page we do not require the place order button we are on placeorder page 
			String insuranceVal	  = req.getParameter("insuranceVal")  == null ?    "0.00" : req.getParameter("insuranceVal"); // at time of order when user select insurance then change the vat as well as total
			
			System.out.println("insuranceVal ::"+insuranceVal);
			
			if(user_key.equalsIgnoreCase("")){
				HttpSession session = req.getSession();	
				if(session.getAttribute("user_key") != null){
					user_key = session.getAttribute("user_key").toString(); //when key already exists in session .
				}
			}
			if(!action.equalsIgnoreCase("")){
				model.addObject("action",action);
			}else{
				model.addObject("action","");
			}
			List<Map<String,Object>> cartList = qrun.query(global.getCartItems,new MapListHandler(),user_key);
			if(cartList !=null && cartList.size()>0){
				for(int i=0;i<cartList.size();i++){
					Map<String, Object> m=cartList.get(i);
					Double qty= Double.parseDouble(m.get("temp_quantity").toString());
					m.put("temp_weight",CommonDAO.getConvertedValue(m.get("temp_weight").toString()));
					m.put("temp_quantity", qty.intValue());
					m.put("temp_price"   , CommonDAO.getConvertedValue(m.get("temp_price").toString()));
					m.put("temp_subtotal", CommonDAO.getConvertedValue(m.get("temp_subtotal").toString()));
					
				}
			}
			model.addObject("cartList",cartList);
			model.addObject("itemInBasket",cartList.size());
			
			System.out.println(cartList);
			System.out.println(action);
			System.out.println("user_key ::"+user_key);
			if(cartList.size()>0){
			
				Double subTotal = (Double) qrun.query(global.cartsubtotal_query,new ScalarHandler(),user_key);
				model.addObject("subTotal",CommonDAO.getConvertedValue(subTotal+""));
				System.out.println("subTotal ::"+subTotal);
				//check value from the order table for insurance val if already ordered.
				if(insuranceVal.equalsIgnoreCase("0.00")){
					
					List<Map<String,Object>> order_data = qrun.query(global.getOrderData_display,new MapListHandler(),user_key,global.order_status_notified);
					if(order_data != null && order_data.size() >0){
						for(int i=0;i<order_data.size();i++){
							Map<String,Object> m =order_data.get(i);
							insuranceVal=m.get("insurance_cover").toString();
						}
				  }
				}
				
				Float insurance = Float.parseFloat(insuranceVal);
				model.addObject("insurance",CommonDAO.getConvertedValue(insurance+""));
				
				System.out.println("insuranceVal ::"+insuranceVal);
				
				Float vat1 = (20*(Float.parseFloat(subTotal+"")+(insurance))/100);
//					double vat =(double) Math.round(vat1*100)/100;
				model.addObject("vat",CommonDAO.getConvertedValue(vat1+""));
			
				Float totalf =vat1 +Float.parseFloat(subTotal+"")+insurance;
				//double total =(double) Math.round(totalf*100)/100;
				model.addObject("total",CommonDAO.getConvertedValue(totalf+""));
				
				
		    }
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"ManageCartService:showCart End:::" + e.getMessage(), logger);
			model.addObject("errorClass",GlobalVariables.errorClass);
			model.addObject("ErrorMsg",GlobalVariables.error_Message);
		}
		
		LogUtility.logInfo("ManageCartService:showCart End:::" + date, logger);
		return model;
	}

	public ModelAndView removeItem_Cart(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		LogUtility.logInfo("ManageCartService:removeItem_Cart Start:::" + date, logger);
		try{
			
			String user_key   = req.getParameter("user_key") == null ?  "" : req.getParameter("user_key");
			String temp_id    = req.getParameter("temp_id") == null ?  "" : req.getParameter("temp_id");
			if(user_key.equalsIgnoreCase("")){
				HttpSession session = req.getSession();	
				if(session.getAttribute("user_key") != null){
					user_key = session.getAttribute("user_key").toString(); //when key already exists in session .
				}
			}
			
			Long order_exits = (Long) qrun.query(global.check_existOrders,new ScalarHandler(),temp_id,user_key);
			System.out.println("order_exits :: "+order_exits);
			if(order_exits ==0){
				int affectedRows = qrun.update(global.removeItem_cart,temp_id,user_key);
				if(affectedRows <=0){
					model.addObject("errorClass",GlobalVariables.errorClass);
					model.addObject("ErrorMsg",GlobalVariables.error_Message);
				}else{
					model.addObject("errorClass",GlobalVariables.successClass);
					model.addObject("ErrorMsg",GlobalVariables.removeItem_cart_success);
				}
				
			}else{
				// get order ID
				Integer order_Id = (Integer)qrun.query(global.getOrderId,new ScalarHandler(),global.order_status_notified,user_key);
				System.out.println("order _id ="+order_Id);
				if(order_Id != null){
					int affectedRows = qrun.update(global.removeDeliveryAddress,temp_id,user_key,user_key,temp_id,order_Id);
					
					affectedRows = qrun.update(global.removeItem_cart,temp_id,user_key);
					if(affectedRows >0){
							affectedRows = qrun.update(global.remove_OrderDetailsByTempId,temp_id,user_key,order_Id);
							if(affectedRows >0){
								
								List<Map<String,Object>> order_data = qrun.query(global.getOrderData_display,new MapListHandler(),user_key,global.order_status_notified);
								model.addObject("for getting order_data",order_data);
								String insurance_cover ="";
								
								System.out.println("order_data ::"+order_data);
								
								if(order_data != null && order_data.size() >0){
									for(int i=0;i<order_data.size();i++){
										Map<String,Object> m =order_data.get(i);
										insurance_cover=m.get("insurance_cover").toString();
									}
									
									
									Double subTotal = (Double) qrun.query(global.cartsubtotal_query,new ScalarHandler(),user_key);
									System.out.println("subTotal ::"+subTotal);
									/* This case preform when all the temporder is removed from db and as well as removed from the odrder details*/
									String subtotal ="0";
									if(subTotal == null){
										insurance_cover ="0";
										affectedRows = qrun.update(global.updateInsurance_cover,insurance_cover,order_Id,user_key);
										
									}else{
										subtotal = String.valueOf(subTotal);
									}
						
									Float insurance = Float.parseFloat(insurance_cover);
									System.out.println("insurance_cover ::"+insurance_cover);
									
									Float vat = (20*(Float.parseFloat(subtotal)+(insurance))/100);
									System.out.println("vat ::"+vat);
									
									Float total_price =vat +Float.parseFloat(subtotal+"")+insurance;
									System.out.println("total_price ::"+total_price);
									
									affectedRows = qrun.update(global.updateCartPriceDetails_order,total_price,subtotal,vat,order_Id,global.order_status_notified,user_key);
									if(affectedRows >0){
										model.addObject("errorClass",GlobalVariables.successClass);
										model.addObject("ErrorMsg",GlobalVariables.removeItem_cart_success);
									}else{
										model.addObject("errorClass",GlobalVariables.errorClass);
										model.addObject("ErrorMsg",GlobalVariables.error_Message);
									}
								}else{
									model.addObject("errorClass",GlobalVariables.errorClass);
									model.addObject("ErrorMsg",GlobalVariables.error_Message);
								}
							}else{
								model.addObject("errorClass",GlobalVariables.errorClass);
								model.addObject("ErrorMsg",GlobalVariables.error_Message);
							}
					}else{
						model.addObject("errorClass",GlobalVariables.errorClass);
						model.addObject("ErrorMsg",GlobalVariables.error_Message);
					}
				}
			}
			
			List<Map<String,Object>> cartList = qrun.query(global.getCartItems,new MapListHandler(),user_key);
			model.addObject("cartList",cartList);
			model.addObject("itemInBasket",cartList.size());
			System.out.println(cartList);
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"ManageCartService:removeItem_Cart End:::" + e.getMessage(), logger);
			model.addObject("errorClass",GlobalVariables.errorClass);
			model.addObject("ErrorMsg",GlobalVariables.error_Message);
		}
		
		LogUtility.logInfo("ManageCartService:removeItem_Cart End:::" + date, logger);
		return model;
	}


	public ModelAndView editItem_Cart(HttpServletRequest req,HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		LogUtility.logInfo("ManageCartService:editItem_Cart Start:::" + date, logger);
		try{
			
			String user_key   = req.getParameter("user_key") == null ?  "" : req.getParameter("user_key");
			String temp_id    = req.getParameter("temp_id") == null ?  "" : req.getParameter("temp_id");
			String insuranceVal	  = req.getParameter("insuranceVal")  == null ?    "0.00" : req.getParameter("insuranceVal"); // at time of order when user select insurance then chagne the vat as well as total
			System.out.println("editItem_Cart insuranceVal ::"+insuranceVal);

			if(user_key.equalsIgnoreCase("")){
				HttpSession session = req.getSession();	
				if(session.getAttribute("user_key") != null){
					user_key = session.getAttribute("user_key").toString(); //when key already exists in session .
				}
			}
			model.addObject("temp_id", temp_id);
			model.addObject("user_key", user_key);
			model.addObject("action", "editItem");
			
			List<Map<String,Object>> cartList = qrun.query(global.getCartItems,new MapListHandler(),user_key);
			model.addObject("cartList",cartList);
			if(cartList !=null && cartList.size()>0){
				for(int i=0;i<cartList.size();i++){
					Map<String, Object> m=cartList.get(i);
					Double qty= Double.parseDouble(m.get("temp_quantity").toString());
					m.put("temp_weight",CommonDAO.getConvertedValue(m.get("temp_weight").toString()));
					m.put("temp_quantity", qty.intValue());
					m.put("temp_price"   , CommonDAO.getConvertedValue(m.get("temp_price").toString()));
					m.put("temp_subtotal", CommonDAO.getConvertedValue(m.get("temp_subtotal").toString()));
				}
			}
			model.addObject("itemInBasket",cartList.size());
			
			if(cartList !=null && cartList.size()>0){
				
				Double subTotal = (Double) qrun.query(global.cartsubtotal_query,new ScalarHandler(),user_key);
				model.addObject("subTotal",CommonDAO.getConvertedValue(subTotal+""));
				System.out.println("subTotal ::"+subTotal);
	
				Float insurance = Float.parseFloat(insuranceVal);
				model.addObject("insurance",CommonDAO.getConvertedValue(insurance+""));
				
				System.out.println("insuranceVal ::"+insuranceVal);
				
				Float vat = (20*(Float.parseFloat(subTotal+"")+(insurance))/100);
				model.addObject("vat",CommonDAO.getConvertedValue(vat+""));
			
				Float total =vat +Float.parseFloat(subTotal+"")+insurance;
				model.addObject("total",CommonDAO.getConvertedValue(total+""));
		    }
			System.out.println(cartList);
			
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"ManageCartService:editItem_Cart End:::" + e.getMessage(), logger);
			model.addObject("errorClass",GlobalVariables.errorClass);
			model.addObject("ErrorMsg",GlobalVariables.error_Message);
		}
		
		LogUtility.logInfo("ManageCartService:editItem_Cart End:::" + date, logger);
		return model;
	}


	public ModelAndView updateItem_Cart(HttpServletRequest req,HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		LogUtility.logInfo("ManageCartService:updateItem_Cart Start:::" + date, logger);
		try{
			
			String user_key   = req.getParameter("user_key") == null ?  "" : req.getParameter("user_key");
			String temp_id    = req.getParameter("temp_id")  == null ?  "" : req.getParameter("temp_id");
			String qty   	  = req.getParameter("quantity") == null ?	"" : req.getParameter("quantity");
			
			if(user_key.equalsIgnoreCase("")){
				HttpSession session = req.getSession();	
				if(session.getAttribute("user_key") != null){
					user_key = session.getAttribute("user_key").toString(); //when key already exists in session .
				}
			}
			Float quantity= Float.parseFloat(qty);
			
			Long order_exits = (Long) qrun.query(global.check_existOrders,new ScalarHandler(),temp_id,user_key);
			System.out.println("order_exits :: "+order_exits);
			if(order_exits ==0){
				int affectedRows = qrun.update(global.updateItem_cart,quantity.intValue(),quantity.intValue(),temp_id,user_key);
				if(affectedRows <=0){
					model.addObject("errorClass",GlobalVariables.errorClass);
					model.addObject("ErrorMsg",GlobalVariables.error_Message);
				}else{
					model.addObject("errorClass",GlobalVariables.successClass);
					model.addObject("ErrorMsg",GlobalVariables.updateItem_Cart_success);
				}
				
			}else{
				// get order ID
				Integer order_Id = (Integer)qrun.query(global.getOrderId,new ScalarHandler(),global.order_status_notified,user_key);
				System.out.println("order _id ="+order_Id);
				int flag =0;
				if(order_Id != null){
					
					int affectedRows = qrun.update(global.updateItem_cart,quantity.intValue(),quantity.intValue(),temp_id,user_key);
					if(affectedRows >0 ){
						affectedRows = qrun.update(global.update_OrderDetailsByTempId,quantity.intValue(),quantity.intValue(),temp_id,user_key,order_Id);
						if(affectedRows >0){
							List<Map<String,Object>> order_data = qrun.query(global.getOrderData_display,new MapListHandler(),user_key,global.order_status_notified);
							model.addObject("for getting order_data",order_data);
							String insurance_cover ="";
							if(order_data != null){
								for(int i=0;i<order_data.size();i++){
									Map<String,Object> m =order_data.get(i);
									insurance_cover=m.get("insurance_cover").toString();
								}
								Double subTotal = (Double) qrun.query(global.cartsubtotal_query,new ScalarHandler(),user_key);
								System.out.println("subTotal ::"+subTotal);
					
								Float insurance = Float.parseFloat(insurance_cover);
								System.out.println("insurance_cover ::"+insurance_cover);
								
								Float vat = (20*(Float.parseFloat(subTotal+"")+(insurance))/100);
								System.out.println("vat ::"+vat);
								
								Float total_price =vat +Float.parseFloat(subTotal+"")+insurance;
								System.out.println("total_price ::"+total_price);
								
								affectedRows = qrun.update(global.updateCartPriceDetails_order,total_price,subTotal,vat,order_Id,global.order_status_notified,user_key);
								if(affectedRows >0){
									model.addObject("errorClass",GlobalVariables.successClass);
									model.addObject("ErrorMsg",GlobalVariables.updateItem_Cart_success);
								}else{
									flag =1;
								}
								
						}else{
							flag =1;
						}
					}else{
						flag =1;
					}

					if(flag == 1){
						model.addObject("errorClass",GlobalVariables.errorClass);
						model.addObject("ErrorMsg",GlobalVariables.error_Message);
					}
				}
			 }else{
				 model.addObject("errorClass",GlobalVariables.errorClass);
				 model.addObject("ErrorMsg",GlobalVariables.error_Message);
			 }
		 }
			
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"ManageCartService:updateItem_Cart End:::" + e.getMessage(), logger);
			model.addObject("errorClass",GlobalVariables.errorClass);
			model.addObject("ErrorMsg",GlobalVariables.error_Message);
		}
		
		LogUtility.logInfo("ManageCartService:updateItem_Cart End:::" + date, logger);
		return model;
	}
	
	
	
	
}
