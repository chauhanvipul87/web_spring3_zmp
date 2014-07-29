package zapmyparcel.service.main;



import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
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

import zapmyparcel.service.postcode.PostCodeFinderService;
import zapmyparcel.utility.common.CommonDAO;
import zapmyparcel.utility.common.GlobalVariables;
import zapmyparcel.utility.common.LogUtility;
import zapmyparcel.utility.date.DateTimeFormater;
import zapmyparcel.utility.db.DbUtils;
import zapmyparcel.utility.email.Email;

public class OrderServices {

	Date date = new Date();
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String ErrorMsg="";
	Logger logger= Logger.getLogger(this.getClass().getName());
	GlobalVariables global;
	
	
	public ModelAndView findaddress(HttpServletRequest req,HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		LogUtility.logInfo("OrderServices:findaddress Start:::" + date, logger);
		try{
			
			String postcode   = req.getParameter("postcode") == null ?  "" : req.getParameter("postcode");
			String prefix     = req.getParameter("prefix")   == null ?  "" : req.getParameter("prefix");
			
			try{
					Hashtable[] address  = new PostCodeFinderService().PostcodeAnywhere_Interactive_RetrieveByPostcodeAndBuilding_v1_10(global.postCodeFinder_key, postcode, global.postCodeFinder_building, global.postCodeFinder_userName);
					List<String> addressLst = new PostCodeFinderService().getAddressList(address);
					model.addObject("addressList", addressLst);
					model.addObject("prefix",prefix);
			}catch(Exception ep){
				//ep.printStackTrace();
				List<String> addressLst = new ArrayList<String>();
				for(int i=0;i<global.postCodeResponseErrors.length;i++){
					if(ep.getMessage().toString().equalsIgnoreCase(global.postCodeResponseErrors[i])){
					   if(global.postCodeResponseErrors[i].equalsIgnoreCase("Postcode Required")){
							model.addObject("errorClass",global.errorClass);
							model.addObject("ErrorMsg","Postcode Required");
						   
					   }else if(global.postCodeResponseErrors[i].equalsIgnoreCase("Postcode Invalid")){
							model.addObject("errorClass",global.errorClass);
							model.addObject("ErrorMsg","Postcode Invalid");
					   }else{
							String description[] = global.postCodeResponseDescription[i].split("#"); 
							String errorCode = description[0];
							String description_error= global.postCodeResponseErrors[i];
							String cause = description[1];
							String resolution = description[2];
							
							String [] recipients = global.recipients_postCodeFinder.split(",");
							 String body =global.mailBody_postCodeFinder;
							 String temp = "";
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + errorCode;
							 body = temp + body.substring(body.indexOf("$")+1);
							 
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + description_error;
							 body = temp + body.substring(body.indexOf("$")+1);
							 
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + cause;
							 body = temp + body.substring(body.indexOf("$")+1);
							 
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + resolution;
							 body = temp + body.substring(body.indexOf("$")+1);
							 
							// body = body.replaceFirst("$","test code");
							 System.out.println("body _str ::"+body);
							 new Email().sendEmail(recipients, global.from_postCodeFinder,"mail.cdl-it.com","mail.smtp.host", global.subjectLine_postCodeFinder,body,"html");
							 
							 model.addObject("errorClass",global.errorClass);
							 model.addObject("ErrorMsg",global.error_Message);
					   }
					}
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"OrderServices:findaddress End:::" + e.getMessage(), logger);
			model.addObject("errorClass",global.errorClass);
			model.addObject("ErrorMsg",global.error_Message);
		}
		
		LogUtility.logInfo("OrderServices:findaddress End:::" + date, logger);
		return model;
	}


	public ModelAndView orderParcels(HttpServletRequest req,HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		LogUtility.logInfo("OrderServices:orderParcels Start:::" + date, logger);
		try{
		
			
			String sname  		 = req.getParameter("sname") 	   == null ?  "" : req.getParameter("sname");
			String spostcode     = req.getParameter("spostcode")   == null ?  "" : req.getParameter("spostcode");
			String saddress1  	 = req.getParameter("saddress1")   == null ?  "" : req.getParameter("saddress1");
			String saddress2  	 = req.getParameter("saddress2")   == null ?  "" : req.getParameter("saddress2");
			String saddress3  	 = req.getParameter("saddress3")   == null ?  "" : req.getParameter("saddress3");
			String stown     	 = req.getParameter("stown")   	   == null ?  "" : req.getParameter("stown");
			String scity  		 = req.getParameter("scity") 	   == null ?  "" : req.getParameter("scity");
			String scounty       = req.getParameter("scounty")     == null ?  "" : req.getParameter("scounty");
			String sphone        = req.getParameter("sphone")      == null ?  "" : req.getParameter("sphone");
			String semail        = req.getParameter("semail")      == null ?  "" : req.getParameter("semail");
			
			String agentID       = req.getParameter("agentID")      == null ?  "0" : req.getParameter("agentID");
			String total_price   = req.getParameter("total_price")  == null ?  "" : req.getParameter("total_price");
			String subTotal   	 = req.getParameter("subTotal")     == null ?  "" : req.getParameter("subTotal");
			String vat		     = req.getParameter("vat")   	    == null ?  "" : req.getParameter("vat");
			
			
			String package_content  	  = req.getParameter("package_content")   	 == null ?  "" : req.getParameter("package_content");
			String value_parcel      	  = req.getParameter("value_parcel")   	 	 == null ?  "" : req.getParameter("value_parcel");
			String num_parcel  		   	  = req.getParameter("num_parcel") 	    	 == null ?  "" : req.getParameter("num_parcel");
			String insurance         	  = req.getParameter("insurance")         	 == null ?  "" : req.getParameter("insurance");
			String delivery_instruction   = req.getParameter("delivery_instruction") == null ?  "" : req.getParameter("delivery_instruction");
			String collection_type  	  = req.getParameter("collection_type") 	 == null ?  "" : req.getParameter("collection_type");
			if(agentID.equalsIgnoreCase("")){
				agentID ="0";
			}

			String user_key = "",user_order_id="",buymore="";
			HttpSession session = req.getSession();	
			if(session.getAttribute("user_key") != null){
				user_key = session.getAttribute("user_key").toString(); //when key already exists in session .
			}
			
			if(session.getAttribute("user_order_id") != null){
				user_order_id = session.getAttribute("user_order_id").toString(); //when user_order_id already exists in session .
			}
			
			if(session.getAttribute("buymore") != null){
				buymore = session.getAttribute("buymore").toString(); //check whether click on buymore is in session (if user click on buymore then only its session will be created)
			}
			
			// normal order and one the user place order buymore is set to the session
			if(buymore.equalsIgnoreCase("")){
				//get IP Address of user
				String ipAddress =req.getRemoteAddr() +":"+req.getRemotePort();
				int order_id = new CommonDAO().getautoGenerateID("orders","order_id");
				
				java.util.Date today = new java.util.Date();
				long t = today.getTime();
				java.sql.Date order_date = new java.sql.Date(t);
				System.out.println(order_date);
				
				/// get temp order based on user_key
				List<Map<String,Object>> tempOrderList = qrun.query(global.getTempOrderByUserKey,new MapListHandler(),user_key);
				
				if(tempOrderList.size()>0){
					
					// get register user id
					Integer user_register_id = (Integer)qrun.query(global.selectUser_register,new ScalarHandler(),user_key);
					System.out.println("user_register_id ::"+user_register_id);
					String oid = String.valueOf(order_id);
					int length = global.orderNoLimit - oid.length();
					user_order_id  = CommonDAO.generateRandomString(length);
					user_order_id = user_order_id +order_id;
					session.setAttribute("user_order_id",user_order_id);
					
					// insert into order table
					Object[] params ={order_id,user_order_id,user_key,global.order_status_notified,collection_type,sname,spostcode,saddress1,saddress2,saddress3,stown,scity,
							scounty,sphone,semail,package_content,value_parcel,num_parcel,insurance,delivery_instruction,ipAddress,
							new DateTimeFormater().format_datetime(),new DateTimeFormater().format_datetime(),total_price,
							order_date,Integer.parseInt(agentID),subTotal,vat,user_register_id};
					int affectedRows = qrun.update(global.placeOrder_query,params);
					
					if(affectedRows>0){
						 new CommonDAO().setautoGenerateID("orders","order_id",(order_id+1));
						 for(int i=0;i<tempOrderList.size();i++){
						    	Map<String,Object>  m  = tempOrderList.get(i);
						    	String temp_id         = m.get("temp_id").toString();
						    	String weight 		   = m.get("temp_weight").toString();
						    	String country_id      = m.get("country_id").toString();
						    	String temp_quantity   = m.get("temp_quantity").toString();
						    	String temp_price	   = m.get("temp_price").toString();
						    	String temp_subtotal   = m.get("temp_subtotal").toString();
						    	
						    	// get address id from delivery_address table
						    	Integer delivery_addressID = (Integer)qrun.query(global.selectDelivery_address,new ScalarHandler(),country_id,user_key);
						    
						    	// insert into order_details table
						    	Object[] param = {order_id,user_order_id,user_key,weight,country_id,temp_quantity,temp_price,temp_subtotal,new DateTimeFormater().format_datetime(),new DateTimeFormater().format_datetime(),delivery_addressID,temp_id};
						    	int affected_rows=qrun.update(global.insert_orderDetails,param);
//						    	if(affected_rows >0){
//						    		 affectedRows=qrun.update(global.remove_tempOrder,user_key,temp_id);
//						    	}
						    	if(affected_rows >0){
						    		 affectedRows=qrun.update(global.update_tempOrderFlag,temp_id,user_key);
						    	}
						    }	
					}
					System.out.println("Done..");
					String temp ="";
					String body = global.order_success_msg;
					temp = body.substring(0, body.indexOf("#"));
					temp = temp + user_order_id;
					body = temp + body.substring(body.indexOf("#")+1);
					
					String msg =global.order_success_msg.replaceFirst("#", user_order_id+"");
					System.out.println("msg ::"+msg);
					
					model.addObject("errorClass",global.successClass);
					model.addObject("ErrorMsg",body);
					
					session.setAttribute("buymore","buymore");  // for adding more function
					
				}else{
					model.addObject("errorClass",global.errorClass);
					model.addObject("ErrorMsg",global.order_error_msg);
				}
				
			}else{
				
				Integer order_Id = (Integer)qrun.query(global.getOrderId,new ScalarHandler(),global.order_status_notified,user_key);
				System.out.println("order_Id "+order_Id);
				if(order_Id != null){
					int affectedRows =0;
					List<Map<String,Object>>  country_idList = qrun.query(global.selectOrderDetailsById,new MapListHandler(),order_Id,user_key);
					System.out.println("country_idList ::"+country_idList);
					if(country_idList!= null && country_idList.size() ==0){
							/// get temp order based on user_key // when removed all items from cart after place successfully order we need to put entery first for the order_detail table
							List<Map<String,Object>> tempOrderList = qrun.query(global.getTempOrderByUserKey,new MapListHandler(),user_key);
							if(tempOrderList.size()>0){
								for(int i=0;i<tempOrderList.size();i++){
							    	Map<String,Object>  m  = tempOrderList.get(i);
							    	String temp_id         = m.get("temp_id").toString();
							    	String weight 		   = m.get("temp_weight").toString();
							    	String country_id      = m.get("country_id").toString();
							    	String temp_quantity   = m.get("temp_quantity").toString();
							    	String temp_price	   = m.get("temp_price").toString();
							    	String temp_subtotal   = m.get("temp_subtotal").toString();
							    	
							    	// get address id from delivery_address table
							    	Integer delivery_addressID = (Integer)qrun.query(global.selectDelivery_address,new ScalarHandler(),country_id,user_key);
							    	System.out.println("select delivery address id ::"+delivery_addressID);
							    	
							    	// insert into order_details table
							    	Object[] param = {order_Id,user_order_id,user_key,weight,country_id,temp_quantity,temp_price,temp_subtotal,new DateTimeFormater().format_datetime(),new DateTimeFormater().format_datetime(),delivery_addressID,temp_id};
							    	int affected_rows=qrun.update(global.insert_orderDetails,param);
							    	System.out.println("global.insert_orderDetails affected_rows "+affected_rows);
//							    	if(affected_rows >0){
//							    		 affectedRows=qrun.update(global.remove_tempOrder,user_key,temp_id);
//							    	}
							    	if(affected_rows >0){
							    		 affectedRows=qrun.update(global.update_tempOrderFlag,temp_id,user_key);
							    	}
							    }
								/* when country id list zero that means that order details contains the same countryid and 
								delivery address field is already updated but then its size greater then zero that means there is a entery in order details but still 
								delivery address field still is in pending.
								*/
							   
								affectedRows=qrun.update(global.updateOrderForBuyMore,package_content,value_parcel,num_parcel,insurance,delivery_instruction,total_price,subTotal,vat,order_Id,user_key);
								System.out.println("global.updateOrderForBuyMore ::"+global.updateOrderForBuyMore);
								if(affectedRows >0){
									model.addObject("errorClass",global.successClass);
									model.addObject("ErrorMsg",global.orderMore_success_msg);
								}else{
									model.addObject("errorClass",global.errorClass);
									model.addObject("ErrorMsg",global.error_Message);
								}
								
							}else{
								
								affectedRows=qrun.update(global.updateOrderForBuyMore,package_content,value_parcel,num_parcel,insurance,delivery_instruction,total_price,subTotal,vat,order_Id,user_key);
								System.out.println("global.updateOrderForBuyMore in else ::"+global.updateOrderForBuyMore);
								if(affectedRows >0){
									model.addObject("errorClass",global.successClass);
									model.addObject("ErrorMsg",global.orderMore_success_msg);
								}else{
									model.addObject("errorClass",global.errorClass);
									model.addObject("ErrorMsg",global.error_Message);
								}
							}
					}else{
						
						// 
						System.out.println("in else..........................buymore............");
						for(int i=0;i<country_idList.size();i++){
							Map<String,Object>  m  = country_idList.get(i);
							String country_id      = m.get("country_id").toString();
							System.out.println("country_id :::::::::: "+country_id);
							// get address id from delivery_address table
					    	Integer delivery_addressID = (Integer)qrun.query(global.selectDelivery_address,new ScalarHandler(),country_id,user_key);
					    	System.out.println("delivery_addressID :::::::::: "+delivery_addressID); 
					    	affectedRows=qrun.update(global.updateDeliveryAddress_orderDetails,delivery_addressID,order_Id,country_id,user_key);
					    	System.out.println("affectedRows ::::::::::affectedRows "); 
						}
						if(affectedRows >0){
							System.out.println("in if :::::::::: "); 
							affectedRows=qrun.update(global.updateOrderForBuyMore,package_content,value_parcel,num_parcel,insurance,delivery_instruction,total_price,subTotal,vat,order_Id,user_key);
							System.out.println("global.updateOrderForBuyMore ::"+global.updateOrderForBuyMore);
							System.out.println("affectedRows ::"+affectedRows);
							if(affectedRows >0){
								model.addObject("errorClass",global.successClass);
								model.addObject("ErrorMsg",global.orderMore_success_msg);
							}else{
								model.addObject("errorClass",global.errorClass);
								model.addObject("ErrorMsg",global.error_Message);	
							}
						}else{
							System.out.println("in else :::::::::: "); 
							model.addObject("errorClass",global.errorClass);
							model.addObject("ErrorMsg",global.error_Message);
						}
				    	
					}
					
				}else{
					model.addObject("errorClass",global.errorClass);
					model.addObject("ErrorMsg",global.order_error_msg);
				}
			}
		
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"OrderServices:orderParcels End:::" + e.getMessage(), logger);
			model.addObject("errorClass",global.errorClass);
			model.addObject("ErrorMsg",global.error_Message);
		}
		
		LogUtility.logInfo("OrderServices:orderParcels End:::" + date, logger);
		return model;	
	}


	public ModelAndView placeOrder(HttpServletRequest req,HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		LogUtility.logInfo("OrderServices:placeOrder Start:::" + date, logger);
		try{
			
			String user_key;
			HttpSession session = req.getSession();	
			if(session.getAttribute("user_key") != null){
				user_key = session.getAttribute("user_key").toString(); //when key already exists in session .
				model.setViewName("order/placeorder");
			}else{
			    model=new OfferPriceService().getOffer(req,res);
				model.setViewName("home/default");
				LogUtility.logInfo("OrderServices:placeOrder End:::" + date, logger);
				return model;
			}
			
			
			// currently entered delivery details for country specific 
			List<Map<String,Object>> entered_address = qrun.query(global.entered_item,new MapListHandler(),user_key); 
			model.addObject("entered_address",entered_address);
			String country_id ="";
			String currentlyEnteredCountryCode= "";
			for(int i=0;i<entered_address.size();i++){
				Map<String,Object> m =entered_address.get(i);
				country_id = m.get("country_id").toString();
				currentlyEnteredCountryCode = m.get("country_code").toString();
			}
			
			// all the details of item which is currently entered.
			List<Map<String,Object>> current_itemDetails = qrun.query(global.current_itemDetails,new MapListHandler(),user_key,country_id); 
			if(current_itemDetails != null && current_itemDetails.size() >0){
				for(int i=0;i<current_itemDetails.size();i++){
					Map<String, Object> m=current_itemDetails.get(i);
					Double qty= Double.parseDouble(m.get("temp_quantity").toString());
					m.put("temp_weight",CommonDAO.getConvertedValue(m.get("temp_weight").toString()));
					m.put("temp_quantity", qty.intValue());
					m.put("temp_price"   , CommonDAO.getConvertedValue(m.get("temp_price").toString()));
					m.put("temp_subtotal", CommonDAO.getConvertedValue(m.get("temp_subtotal").toString()));
				}
			}
			model.addObject("current_itemDetails",current_itemDetails);
			
			
			  
			List<Map<String,Object>> remaining_item = qrun.query(global.remaining_item,new MapListHandler(),user_key,country_id); 
			model.addObject("remaining_item",remaining_item);
			if(remaining_item != null && remaining_item.size() >0){
				for(int i=0;i<remaining_item.size();i++){
					Map<String, Object> m=remaining_item.get(i);
					Double qty= Double.parseDouble(m.get("temp_quantity").toString());
					m.put("temp_weight",CommonDAO.getConvertedValue(m.get("temp_weight").toString()));
					m.put("temp_quantity", qty.intValue());
					m.put("temp_price"   , CommonDAO.getConvertedValue(m.get("temp_price").toString()));
					m.put("temp_subtotal", CommonDAO.getConvertedValue(m.get("temp_subtotal").toString()));
				}
			}
			
			
			// already entered delivery address (compelted delivery address) whose address_flag =1
			List<Map<String,Object>> completedDeliveryAddress = qrun.query(global.comp_delivery_address,new MapListHandler(),user_key);
			model.addObject("completed_item",completedDeliveryAddress);		
			
			if(completedDeliveryAddress != null && completedDeliveryAddress.size() >0){
				for(int i=0;i<completedDeliveryAddress.size();i++){
					Map<String, Object> m=completedDeliveryAddress.get(i);
					Double qty= Double.parseDouble(m.get("temp_quantity").toString());
					m.put("temp_weight",CommonDAO.getConvertedValue(m.get("temp_weight").toString()));
					m.put("temp_quantity", qty.intValue());
					m.put("temp_price"   , CommonDAO.getConvertedValue(m.get("temp_price").toString()));
					m.put("temp_subtotal", CommonDAO.getConvertedValue(m.get("temp_subtotal").toString()));
				}
			}
			
			List<Map<String,Object>> cartList = qrun.query(global.getCartItems,new MapListHandler(),user_key);
			Float itemInBasket =0.0f;
			if(cartList !=null && cartList.size()>0){
				for(int i=0;i<cartList.size();i++){
					Map<String, Object> m=cartList.get(i);
					itemInBasket = itemInBasket+Float.parseFloat(m.get("temp_quantity").toString());
					System.out.println("............................itemInBasket "+itemInBasket);
				}
			}

			model.addObject("itemInBasket",itemInBasket.intValue());
			model.addObject("country_code",currentlyEnteredCountryCode);
			model.addObject("country_id",country_id);
		
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"OrderServices:placeOrder End:::" + e.getMessage(), logger);
			model.addObject("errorClass",global.errorClass);
			model.addObject("ErrorMsg",global.error_Message);
		}
		
		LogUtility.logInfo("OrderServices:placeOrder End:::" + date, logger);
		return model;	
	}


	public ModelAndView addDeliveryAddress(HttpServletRequest req,HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		LogUtility.logInfo("OrderServices:addDeliveryAddress Start:::" + date, logger);
		try{
			
			String user_key;
			HttpSession session = req.getSession();	
			if(session.getAttribute("user_key") != null){
				user_key = session.getAttribute("user_key").toString(); //when key already exists in session .
				model.setViewName("order/placeorder");
			}else{
			    model=new OfferPriceService().getOffer(req,res);
				model.setViewName("home/default");
				LogUtility.logInfo("OrderServices:addDeliveryAddress End:::" + date, logger);
				return model;
			}
			
			System.out.println(req.getQueryString());
			
			String dname  		 = req.getParameter("dname") 	   == null ?  "" : req.getParameter("dname");
			String dpostcode     = req.getParameter("dpostcode")   == null ?  "" : req.getParameter("dpostcode");
			String daddress1  	 = req.getParameter("daddress1")   == null ?  "" : req.getParameter("daddress1");
			String daddress2  	 = req.getParameter("daddress2")   == null ?  "" : req.getParameter("daddress2");
			String daddress3  	 = req.getParameter("daddress3")   == null ?  "" : req.getParameter("daddress3");
			String dtown     	 = req.getParameter("dtown")   	   == null ?  "" : req.getParameter("dtown");
			String dcity  		 = req.getParameter("dcity") 	   == null ?  "" : req.getParameter("dcity");
			String dcountry      = req.getParameter("dcountry")    == null ?  "" : req.getParameter("dcountry");// it refers to the country code
			String dphone        = req.getParameter("dphone")      == null ?  "" : req.getParameter("dphone");
			String country_id    = req.getParameter("country_id")  == null ?  "" : req.getParameter("country_id");
			
			Object[] param ={country_id,user_key,dname,dpostcode,daddress1,daddress2,daddress3,dtown,dcity,dphone};
			int affected_rows=qrun.update(global.insert_delivery_address,param);
			if(affected_rows >0){
				affected_rows=qrun.update(global.update_add_tempOrder,country_id,user_key);		
				if(affected_rows >0){
					 model =placeOrder(req, res);
				}else{
					model.addObject("errorClass",global.errorClass);
					model.addObject("ErrorMsg",global.error_Message);
				}
			}else{
				model.addObject("errorClass",global.errorClass);
				model.addObject("ErrorMsg",global.error_Message);
			}
			
		
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"OrderServices:addDeliveryAddress End:::" + e.getMessage(), logger);
			model.addObject("errorClass",global.errorClass);
			model.addObject("ErrorMsg",global.error_Message);
		}
		
		LogUtility.logInfo("OrderServices:addDeliveryAddress End:::" + date, logger);
		return model;
	
	
	}

	public ModelAndView showDeliveryAddress_country(HttpServletRequest req,	HttpServletResponse res) {

		ModelAndView model = new ModelAndView();
		LogUtility.logInfo("OrderServices:showDeliveryAddress_country Start:::" + date, logger);
		try{
			
			String country_id    = req.getParameter("country_id")  == null ?  "" : req.getParameter("country_id");
			String user_key;
			HttpSession session = req.getSession();	
			if(session.getAttribute("user_key") != null){
				user_key = session.getAttribute("user_key").toString(); //when key already exists in session .
				model.setViewName("order/placeorder");
			}else{
			    model=new OfferPriceService().getOffer(req,res);
				model.setViewName("home/default");
				LogUtility.logInfo("OrderServices:addDeliveryAddress End:::" + date, logger);
				return model;
			}
			
			List<Map<String,Object>> delivery_addressDetails = qrun.query(global.showDeliveryAddress_country,new MapListHandler(),country_id,user_key);
			model.addObject("delivery_addressDetails",delivery_addressDetails);
			
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"OrderServices:showDeliveryAddress_country End:::" + e.getMessage(), logger);
			model.addObject("errorClass",global.errorClass);
			model.addObject("ErrorMsg",global.error_Message);
		}
		
		LogUtility.logInfo("OrderServices:showDeliveryAddress_country End:::" + date, logger);
		return model;
	
	}


	public ModelAndView dropToAgentDetails(HttpServletRequest req,HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		LogUtility.logInfo("OrderServices:dropToAgentDetails Start:::" + date, logger);
		try{
			
			String action       = req.getParameter("action")     == null ?  "" : req.getParameter("action");
			if(!action.equals("")){
				model.addObject("action", action);
			}
			
			List<Map<String,Object>> agentDeails = qrun.query(global.select_all_agentQuery,new MapListHandler());
			model.addObject("agentDeails",agentDeails);
			System.out.println("agent details ::"+agentDeails);
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"OrderServices:dropToAgentDetails End:::" + e.getMessage(), logger);
			model.addObject("errorClass",global.errorClass);
			model.addObject("ErrorMsg",global.error_Message);
		}
		
		LogUtility.logInfo("OrderServices:dropToAgentDetails End:::" + date, logger);
		return model;
	
	}


	public ModelAndView showOrderDetails(HttpServletRequest req,HttpServletResponse res) {

		ModelAndView model = new ModelAndView();
		LogUtility.logInfo("OrderServices:showOrderDetails Start:::" + date, logger);
		try{
			String user_key;
			HttpSession session = req.getSession();	
			if(session.getAttribute("user_key") != null){
				user_key = session.getAttribute("user_key").toString(); //when key already exists in session .
				model.setViewName("order/showorderdetails");
			}else{
				 model=new OfferPriceService().getOffer(req,res);
				 model.setViewName("home/default");
				 LogUtility.logInfo("OrderServices:addDeliveryAddress End:::" + date, logger);
				 return model;
			}
			/* this code is to display the item in basket in header part start*/
			List<Map<String,Object>> cartList = qrun.query(global.getCartItems,new MapListHandler(),user_key);
			model.addObject("cartList",cartList);
			model.addObject("itemInBasket",cartList.size());
			/* this code is to display the item in basket in header part end */
			
			
			List<Map<String,Object>> order_data = qrun.query(global.getOrderData_display,new MapListHandler(),user_key,global.order_status_notified);
			if(order_data !=null && order_data.size()>0){
				for(int i=0;i<order_data.size();i++){
					Map<String, Object> m=order_data.get(i);
					m.put("sub_total",CommonDAO.getConvertedValue(m.get("sub_total").toString()));
					m.put("vat", CommonDAO.getConvertedValue(m.get("vat").toString()));
					m.put("total_price", CommonDAO.getConvertedValue(m.get("total_price").toString()));
					m.put("value_parcel", CommonDAO.getConvertedValue(m.get("value_parcel").toString()));
					
				}
			}
			model.addObject("order_data",order_data);
			String order_id ="";
			String agent_id="";
			if(order_data != null){
				for(int i=0;i<order_data.size();i++){
					Map<String,Object> m =order_data.get(i);
					order_id=m.get("order_id").toString();
					agent_id =m.get("dropoff_agent_id").toString();
				}
				if(!order_id.equals("")){
					List<Map<String,Object>> delivery_addressList = qrun.query(global.getDeliveryDetailByOrderID,new MapListHandler(),order_id,user_key);
					model.addObject("delivery_addressList",delivery_addressList);
					
				}
				if(!agent_id.equalsIgnoreCase("0")){
					List<Map<String,Object>> agentDetails = qrun.query(global.getAgentDetails,new MapListHandler(),agent_id);
					model.addObject("agentDetails",agentDetails);
				}
				model.addObject("order_id",order_id);
			}
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"OrderServices:showOrderDetails End:::" + e.getMessage(), logger);
			model.addObject("errorClass",global.errorClass);
			model.addObject("ErrorMsg",global.error_Message);
		}
		
		LogUtility.logInfo("OrderServices:showOrderDetails End:::" + date, logger);
		return model;
	}


	public ModelAndView userRegistration(HttpServletRequest req,HttpServletResponse res) {

		ModelAndView model = new ModelAndView();
		LogUtility.logInfo("OrderServices:userRegistration Start:::" + date, logger);
		try{
			
			String email       = req.getParameter("email")     == null ?  "" : req.getParameter("email");
			String password    = req.getParameter("password")  == null ?  "" : req.getParameter("password");
			
			System.out.println("email    ::"+email);
			System.out.println("password ::"+password);
			
			if(email.equalsIgnoreCase("")){
				model.addObject("errorClass",global.errorClass);
				model.addObject("ErrorMsg",global.enter_emailmsg);
				LogUtility.logInfo("OrderServices:userRegistration End:::" + date, logger);
				return model;
			}
			if(password.equalsIgnoreCase("")){
				model.addObject("errorClass",global.errorClass);
				model.addObject("ErrorMsg",global.enter_passwordmsg);
				LogUtility.logInfo("OrderServices:userRegistration End:::" + date, logger);
				return model;
			}
			String user_key;
			HttpSession session = req.getSession();	
			if(session.getAttribute("user_key") != null){
				user_key = session.getAttribute("user_key").toString(); //when key already exists in session .
			
			}else{
				model.addObject("errorClass",global.errorClass);
				model.addObject("ErrorMsg",global.error_Message);
				LogUtility.logInfo("OrderServices:userRegistration End:::" + date, logger);
				return model;
			}
			
			int affected_rows=qrun.update(global.insert_user_registration,email,password,user_key);
			if(affected_rows >0){
				model.addObject("resmsg", "success");
			}else{
				model.addObject("resmsg", "error");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"OrderServices:userRegistration End:::" + e.getMessage(), logger);
			model.addObject("errorClass",global.errorClass);
			model.addObject("ErrorMsg",global.error_Message);
		}
		
		LogUtility.logInfo("OrderServices:userRegistration End:::" + date, logger);
		return model;
		
	}


	public ModelAndView checkOutOrder(HttpServletRequest req,HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		LogUtility.logInfo("OrderServices:checkOutOrder Start:::" + date, logger);
		try{
			
			String user_key;
			HttpSession session = req.getSession();	
			if(session.getAttribute("user_key") != null){
				user_key = session.getAttribute("user_key").toString(); //when key already exists in session .
				model.setViewName("home/under_construction");
			}else{
				 model=new OfferPriceService().getOffer(req,res);
				 model.setViewName("home/default");
				 LogUtility.logInfo("OrderServices:checkOutOrder End:::" + date, logger);
				 return model;
			}
			
			int affected_rows=qrun.update(global.updateCheckOutFlag_tempOrder,user_key);
			session.removeAttribute("user_key");
			session.removeAttribute("buymore");
			session.removeAttribute("user_order_id");
			session.invalidate();
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"OrderServices:checkOutOrder End:::" + e.getMessage(), logger);
			model.addObject("errorClass",global.errorClass);
			model.addObject("ErrorMsg",global.error_Message);
		}
		
		LogUtility.logInfo("OrderServices:checkOutOrder End:::" + date, logger);
		return model;
	}


	public ModelAndView track_order(HttpServletRequest req,	HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		LogUtility.logInfo("OrderServices:track_order Start:::" + date, logger);
		try{
			
			String user_order_id       = req.getParameter("user_order_id")     == null ?  "" : req.getParameter("user_order_id");
			if(user_order_id.equals("")){
				model.addObject("errorClass",global.errorClass);
				model.addObject("ErrorMsg","Please Enter Your Order ID.");
				LogUtility.logInfo("OrderServices:track_order End:::" + date, logger);
				return model;
			}
			
			List<Map<String,Object>> getOrderDetails = qrun.query(global.selectTrackingOrderId, new MapListHandler(),user_order_id);
			
			System.out.println("getOrderDetails :: "+getOrderDetails);
			System.out.println(getOrderDetails.size());
			if(getOrderDetails != null && getOrderDetails.size() >0){
					if(getOrderDetails.get(0).get("status").toString().equalsIgnoreCase(GlobalVariables.paypal_Failure_Status)
						||	getOrderDetails.get(0).get("status").toString().equalsIgnoreCase(GlobalVariables.paypal_SuccessWithWarning_Status)
						||	getOrderDetails.get(0).get("status").toString().equalsIgnoreCase(GlobalVariables.paypal_FailureWithWarning_Status)){
						
						List<Map<String,Object>> getErrorDetails = qrun.query(global.selectTrakingErrors, new MapListHandler(),getOrderDetails.get(0).get("error_id").toString(),user_order_id);
						model.addObject("errorDetails", getErrorDetails);
					}
			}
			
			/*if(getOrderDetails !=null && getOrderDetails.size() >0){
				for(int i=0;i<getOrderDetails.size();i++){
					Map<String, Object> m=getOrderDetails.get(i);
					m.put("total_price"   , CommonDAO.getConvertedValue(m.get("total_price").toString()));
				}
			}*/
			
			model.addObject("getOrderDetails", getOrderDetails);
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"OrderServices:track_order End:::" + e.getMessage(), logger);
			model.addObject("errorClass",global.errorClass);
			model.addObject("ErrorMsg",global.error_Message);
		}
		
		LogUtility.logInfo("OrderServices:track_order End:::" + date, logger);
		return model;
	}


	public ModelAndView getCartDetails(HttpServletRequest req,	HttpServletResponse res) {
		
		ModelAndView model = new ModelAndView();
		LogUtility.logInfo("OrderServices:getCartDetails Start:::" + date, logger);
		try{
			
			String user_key = "";
			HttpSession session = req.getSession();	
			if(session.getAttribute("user_key") != null){
				user_key = session.getAttribute("user_key").toString(); //when key already exists in session .
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
			
				
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"OrderServices:getCartDetails End:::" + e.getMessage(), logger);
			model.addObject("errorClass",global.errorClass);
			model.addObject("ErrorMsg",global.error_Message);
		}
		
		LogUtility.logInfo("OrderServices:getCartDetails End:::" + date, logger);
		return model;
	}
}
