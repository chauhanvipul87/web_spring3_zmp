package zapmyparcel.service.paypay;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.NVPCallerServices;

import zapmyparcel.service.main.OfferPriceService;
import zapmyparcel.utility.common.CommonDAO;
import zapmyparcel.utility.common.GlobalVariables;
import zapmyparcel.utility.common.LogUtility;
import zapmyparcel.utility.date.DateTimeFormater;
import zapmyparcel.utility.db.DbUtils;
import zapmyparcel.utility.email.Email;

public class PayPalServices {
	
	Date date = new Date();
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String ErrorMsg="";
	Logger logger= Logger.getLogger(this.getClass().getName());
	//GlobalVariaGbles global;

	// method call when user checkout from the showorderdetail.html page (for paypal)
	public ModelAndView payForOrders(HttpServletRequest req,HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		LogUtility.logInfo("PayPalServices:payForOrders Start:::" + date, logger);
		
		String CardType  		 = req.getParameter("CardType")  == null ?  "" : req.getParameter("CardType");
		String card_no     		 = req.getParameter("card_no")   == null ?  "" : req.getParameter("card_no");
		String fname  			 = req.getParameter("fname")     == null ?  "" : req.getParameter("fname");
		String lname  	         = req.getParameter("lname")     == null ?  "" : req.getParameter("lname");
		String smonth  	         = req.getParameter("smonth")    == null ?  "" : req.getParameter("smonth");
		String syear     	     = req.getParameter("syear")   	 == null ?  "" : req.getParameter("syear");
		String emonth  		     = req.getParameter("emonth") 	 == null ?  "" : req.getParameter("emonth");
		String eyear      		 = req.getParameter("eyear")     == null ?  "" : req.getParameter("eyear");
		String cvv_no      		 = req.getParameter("cvv_no")    == null ?  "" : req.getParameter("cvv_no");
		String issue_no          = req.getParameter("issue_no")  == null ?  "" : req.getParameter("issue_no");
		String order_id          = req.getParameter("order_id")  == null ?  "" : req.getParameter("order_id");
		
		// get IP Address of user
		String ipAddress =req.getRemoteAddr();
		if(ipAddress.equalsIgnoreCase("0:0:0:0:0:0:0:1")){
			ipAddress ="127.0.0.1";
		}
		try{
			
			String user_key ="";
			HttpSession session = req.getSession(false);	
			if(session.getAttribute("user_key") != null){
				user_key = session.getAttribute("user_key").toString(); //when key already exists in session .
			}else{
				model.addObject("errorClass",GlobalVariables.errorClass);
				model.addObject("ErrorMsg",GlobalVariables.noteAuthorized);
				LogUtility.logInfo("PayPalServices:payForOrders End:::" + date, logger);
				return model;
			}
			
			if(!order_id.equals("") && !user_key.equals("")){
				
				// insert into paypay_log for request log
		    	Object[] param = {CardType ,card_no,fname,lname,smonth,syear,emonth,eyear,cvv_no,issue_no,new DateTimeFormater().format_datetime(),order_id,ipAddress,GlobalVariables.paypay_request};
		    	int affected_rows=qrun.update(GlobalVariables.insert_paypay_log,param);
				
		    	if(affected_rows >0){
		    		/* by using this query we get the order data which is required by paypay and in future 
		    		 if we want to get more data from query then just need to add the column name and get in following loop. */
		    		
		    		List<Map<String,Object>> orderDataList = qrun.query(GlobalVariables.someOrderData1,new MapListHandler(),order_id,GlobalVariables.order_status_notified,user_key);

		    		String addressLine1="",addressLine2 ="",city ="",postcode ="",phone ="",email ="";
		    		String amount="";
		    		
					if(orderDataList != null){
						for(int i= 0 ; i<orderDataList.size();i++){
								Map<String,Object> m = (Map<String,Object>)orderDataList.get(i);
								addressLine1 = m.get("s_addressline1").toString();
								addressLine2 = m.get("s_addressline2").toString();
								city		 = m.get("s_city").toString();
								postcode	 = m.get("s_postcode").toString();
								phone		 = m.get("s_phone").toString();
								email		 = m.get("s_email").toString();
								amount		 = m.get("total_price").toString();
						}
						
						String expdate = emonth+""+eyear;

						System.out.println("amount "+amount);
						System.out.println("CardType "+CardType);
						System.out.println("card_no "+card_no);
						System.out.println("expdate "+expdate);
						System.out.println("cvv_no "+cvv_no);
						System.out.println("fname "+fname);
						System.out.println("lname "+lname);
						System.out.println("addressLine1 "+addressLine1);
						System.out.println("city "+city);
						System.out.println("postcode "+postcode);
						System.out.println("ipAddress "+ipAddress);
						System.out.println("email "+email);
						
						
						DoDirectPayment doDirectPayment = new DoDirectPayment();
						
						Map<String,Object> retMap = doDirectPayment.DoDirectPaymentCode(amount,CardType,card_no,expdate, 
																cvv_no, fname, lname, addressLine1, city,postcode,ipAddress,email,order_id,user_key,session);
						
						if(retMap != null){
							if(retMap.get("ACK").equals(GlobalVariables.paypal_Success_Status)){
								System.out.println("resString "+retMap.get("ACK").toString());
								model.addObject("errorClass",GlobalVariables.successClass);
								model.addObject("ErrorMsg","success");
							}else if(retMap.get("ACK").equals(GlobalVariables.paypal_Failure_Status) || retMap.get("ACK").equals(GlobalVariables.paypal_FailureWithWarning_Status)){
									/*System.out.println("in if....of failure");*/
									model.addObject("errorClass",GlobalVariables.errorClass);
									System.out.println(retMap.get("Message").toString());
									model.addObject("ErrorMsg",retMap.get("Message").toString());
									model.addObject("option","Failure");
							}else if(retMap.get("ACK").equals(GlobalVariables.paypal_SuccessWithWarning_Status)){
										/*System.out.println("in if....of failure");*/
										model.addObject("errorClass",GlobalVariables.successClass);
										System.out.println(retMap.get("Message").toString());
										session.setAttribute("SuccessWithWarning",retMap.get("Message"));  // for display on the final screen on payment confirmation 
										model.addObject("ErrorMsg",retMap.get("Message").toString());
							}else{
							model.addObject("errorClass",GlobalVariables.errorClass);
							model.addObject("ErrorMsg",GlobalVariables.error_Message);
						}
					}
						
				 }
					
		      }
		    	// insert into paypay_log for response log 
		    	Object[] param1 = {CardType ,card_no,fname,lname,smonth,syear,emonth,eyear,cvv_no,issue_no,new DateTimeFormater().format_datetime(),order_id,ipAddress,GlobalVariables.paypal_response};
		    	affected_rows=qrun.update(GlobalVariables.insert_paypay_log,param1);
		    	
		    }else{
				model.addObject("errorClass",GlobalVariables.errorClass);
				model.addObject("ErrorMsg",GlobalVariables.noteAuthorized);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"PayPalServices:payForOrders End:::" + e.getMessage(), logger);
			model.addObject("errorClass",GlobalVariables.errorClass);
			model.addObject("ErrorMsg",GlobalVariables.error_Message);
			// insert into paypay_log for response log 
			try{
	    	Object[] param1 = {CardType ,card_no,fname,lname,smonth,syear,emonth,eyear,cvv_no,issue_no,new DateTimeFormater().format_datetime(),"",ipAddress,GlobalVariables.paypal_response+" Exception"};
	    	int affected_rows=qrun.update(GlobalVariables.insert_paypay_log,param1);
	    	
			}catch(Exception e1){
				e1.printStackTrace();
			}
		}
		
		LogUtility.logInfo("PayPalServices:payForOrders End:::" + date, logger);
		return model;	
	}

	public ModelAndView paymentConfirm(HttpServletRequest req,HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		LogUtility.logInfo("PayPalServices:paymentConfirm Start:::" + date, logger);
		try{
			
			String user_key ="";
			HttpSession session = req.getSession(false);	
			if(session.getAttribute("user_key") != null){
				user_key = session.getAttribute("user_key").toString(); //when key already exists in session .
				model.setViewName("order/paymentconfirm");
			}else{
				 model=new OfferPriceService().getOffer(req,res);
				 model.setViewName("home/default");
				 LogUtility.logInfo("PayPalServices:paymentConfirm End:::" + date, logger);
				return model;
			}
			
			List<Map<String,Object>> paymanentDetails =  qrun.query(GlobalVariables.gettrasaction_id,new MapListHandler(),user_key,GlobalVariables.order_status_ordered);
			if(paymanentDetails !=null && paymanentDetails.size() >0){
				
				for(int i=0;i<paymanentDetails.size();i++){
					Map<String,Object> m = paymanentDetails.get(i);
					m.put("amount"		,CommonDAO.getConvertedValue(m.get("amount").toString()));
				}
			}
			model.addObject("paymanentDetails",paymanentDetails);
			
			List<Map<String,Object>> order_data = qrun.query(GlobalVariables.getOrderData_display,new MapListHandler(),user_key,GlobalVariables.order_status_ordered);
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
			
			List<Map<String,Object>> cartList = qrun.query(GlobalVariables.getFinalItemDetails,new MapListHandler(),GlobalVariables.order_status_ordered,user_key);
			model.addObject("cartList",cartList);
			
			String finalBody ="";
			String [] recipients = GlobalVariables.recipients_collectParcel.split(",");
			if(order_data != null){
				// prepare email for home delivery 
				if(order_data.get(0).get("collection_type").toString().equalsIgnoreCase(GlobalVariables.order_collection_status_reversed)){
					String body =GlobalVariables.mailBody_collectParcel;
					for(int i=0; i<order_data.size();i++){
						 Map<String,Object> m = order_data.get(i);
						 String temp = "";
						
						 temp = body.substring(0, body.indexOf("$"));
						 temp = temp + m.get("user_order_id");
						 body = temp + body.substring(body.indexOf("$")+1);
					
						 
						 temp = body.substring(0, body.indexOf("$"));
						 temp = temp + m.get("order_status");
						 body = temp + body.substring(body.indexOf("$")+1);
						 
						 temp = body.substring(0, body.indexOf("$"));
						 temp = temp + m.get("collection_type");
						 body = temp + body.substring(body.indexOf("$")+1);
						 
						 
						 temp = body.substring(0, body.indexOf("$"));
						 if(m.get("insurance_cover") !=null){
							 if(Double.parseDouble(m.get("insurance_cover").toString())>0){
								 temp = temp + "Yes";		 
							 }else{
								 temp = temp + "No";
							 }
						 }
						 body = temp + body.substring(body.indexOf("$")+1);
						 
						 
						 temp = body.substring(0, body.indexOf("$"));
						 temp = temp + m.get("s_name");
						 body = temp + body.substring(body.indexOf("$")+1);
						 
						 
						 temp = body.substring(0, body.indexOf("$"));
						 temp = temp + m.get("s_postcode");
						 body = temp + body.substring(body.indexOf("$")+1);
						 
						 temp = body.substring(0, body.indexOf("$"));
						 temp = temp + m.get("s_addressLine1");
						 body = temp + body.substring(body.indexOf("$")+1);
						 
						 temp = body.substring(0, body.indexOf("$"));
						 temp = temp + m.get("s_addressLine2");
						 body = temp + body.substring(body.indexOf("$")+1);
						 
						 temp = body.substring(0, body.indexOf("$"));
						 temp = temp + m.get("s_addressLine3");
						 body = temp + body.substring(body.indexOf("$")+1);
						 
						 temp = body.substring(0, body.indexOf("$"));
						 temp = temp + m.get("s_town");
						 body = temp + body.substring(body.indexOf("$")+1);
						 
						 temp = body.substring(0, body.indexOf("$"));
						 temp = temp + m.get("s_city");
						 body = temp + body.substring(body.indexOf("$")+1); 
						 
						 temp = body.substring(0, body.indexOf("$"));
						 temp = temp + m.get("s_phone");
						 body = temp + body.substring(body.indexOf("$")+1); 
						 
						 temp = body.substring(0, body.indexOf("$"));
						 temp = temp + m.get("s_email");
						 body = temp + body.substring(body.indexOf("$")+1); 
						 
						 temp = body.substring(0, body.indexOf("$"));
						 temp = temp + m.get("delivery_Instructions");
						 body = temp + body.substring(body.indexOf("$")+1); 
						 
					}
				
					String order_detail="";
					for(int i=0; i<cartList.size();i++){
						 Map<String,Object> m = cartList.get(i);
						 
						/* m.put("weight"			,CommonDAO.getConvertedValue(m.get("weight").toString()));
						 m.put("quantity"   	,CommonDAO.getConvertedValue(m.get("quantity").toString()));*/
						 m.put("Item_price"		,CommonDAO.getConvertedValue(m.get("Item_price").toString()));
						 m.put("Item_subtotal"  ,CommonDAO.getConvertedValue(m.get("Item_subtotal").toString()));
						 
						 order_detail =order_detail+"<tr>";
						 order_detail =order_detail+"<td align=\"left\">"+m.get("weight")+"</td>";
						 order_detail =order_detail+"<td align=\"left\">"+m.get("quantity")+"</td>";
						 order_detail =order_detail+"<td align=\"left\">&#163;"+m.get("Item_price")+"</td>";
						 order_detail =order_detail+"<td align=\"left\">&#163;"+m.get("Item_subtotal")+"</td>";
						 order_detail =order_detail+"</tr>";
					}
					 order_detail = order_detail+"</table></body></html>";
					 body = body+order_detail;
					 finalBody =body;
				}else{
					// prepare email for pick up parcel from Agent. 
					if(order_data.get(0).get("collection_type").toString().equalsIgnoreCase(GlobalVariables.order_collection_status_agent)){
						String body =GlobalVariables.mailBody_collectParcel_agent;
						List<Map<String,Object>> agentDetails = qrun.query(GlobalVariables.getFinalAgentDetails,new MapListHandler(),GlobalVariables.order_collection_status_agent,user_key,GlobalVariables.order_status_ordered);
						if(agentDetails != null){
							for(int i=0; i<agentDetails.size();i++){
								 Map<String,Object> m = agentDetails.get(i);
								 
								 String temp = "";
									
								 temp = body.substring(0, body.indexOf("$"));
								 temp = temp + m.get("user_order_id");
								 body = temp + body.substring(body.indexOf("$")+1);
							
								 
								 temp = body.substring(0, body.indexOf("$"));
								 temp = temp + m.get("order_status");
								 body = temp + body.substring(body.indexOf("$")+1);
								 
								 temp = body.substring(0, body.indexOf("$"));
								 temp = temp + m.get("collection_type");
								 body = temp + body.substring(body.indexOf("$")+1);
								 
								 
								 temp = body.substring(0, body.indexOf("$"));
								 if(m.get("insurance_cover") !=null){
									 if(Double.parseDouble(m.get("insurance_cover").toString())>0){
										 temp = temp + "Yes";		 
									 }else{
										 temp = temp + "No";
									 }
								 }
								 body = temp + body.substring(body.indexOf("$")+1);
								 
								 temp = body.substring(0, body.indexOf("$"));
								 temp = temp + m.get("agent_code");
								 body = temp + body.substring(body.indexOf("$")+1);
								
								 temp = body.substring(0, body.indexOf("$"));
								 temp = temp + m.get("agent_name");
								 body = temp + body.substring(body.indexOf("$")+1);
								 
								 
								 temp = body.substring(0, body.indexOf("$"));
								 temp = temp + m.get("Postcode");
								 body = temp + body.substring(body.indexOf("$")+1);
								 
								 temp = body.substring(0, body.indexOf("$"));
								 temp = temp + m.get("address1");
								 body = temp + body.substring(body.indexOf("$")+1);
								 
								 temp = body.substring(0, body.indexOf("$"));
								 temp = temp + m.get("address2");
								 body = temp + body.substring(body.indexOf("$")+1);
								 
								 temp = body.substring(0, body.indexOf("$"));
								 temp = temp + m.get("address3");
								 body = temp + body.substring(body.indexOf("$")+1);
								 
								 temp = body.substring(0, body.indexOf("$"));
								 temp = temp + m.get("town");
								 body = temp + body.substring(body.indexOf("$")+1);
								 
								 temp = body.substring(0, body.indexOf("$"));
								 temp = temp + m.get("city");
								 body = temp + body.substring(body.indexOf("$")+1); 
								 
								 temp = body.substring(0, body.indexOf("$"));
								 temp = temp + m.get("Phone");
								 body = temp + body.substring(body.indexOf("$")+1); 
								 
								 temp = body.substring(0, body.indexOf("$"));
								 temp = temp + m.get("Email");
								 body = temp + body.substring(body.indexOf("$")+1); 
								 
								 temp = body.substring(0, body.indexOf("$"));
								 temp = temp + m.get("delivery_Instructions");
								 body = temp + body.substring(body.indexOf("$")+1); 
								 
							}
						}
						String order_detail="";
						for(int i=0; i<cartList.size();i++){
							 Map<String,Object> m = cartList.get(i);
				/*			 m.put("weight"			,CommonDAO.getConvertedValue(m.get("weight").toString()));
							 m.put("quantity"   	,CommonDAO.getConvertedValue(m.get("quantity").toString()));*/
							 m.put("Item_price"		,CommonDAO.getConvertedValue(m.get("Item_price").toString()));
							 m.put("Item_subtotal"  ,CommonDAO.getConvertedValue(m.get("Item_subtotal").toString()));
							 
							 
							 order_detail =order_detail+"<tr>";
							 order_detail =order_detail+"<td align=\"left\">"+m.get("weight")+"</td>";
							 order_detail =order_detail+"<td align=\"left\">"+m.get("quantity")+"</td>";
							 order_detail =order_detail+"<td align=\"left\">&#163;"+m.get("Item_price")+"</td>";
							 order_detail =order_detail+"<td align=\"left\">&#163;"+m.get("Item_subtotal")+"</td>";
							 order_detail =order_detail+"</tr>";
						}
						 order_detail = order_detail+"</table></body></html>";
						 body = body+order_detail;
						 finalBody =body;
					}
				}
				 System.out.println("body _str ::"+finalBody);
				 Email.sendEmail(recipients, GlobalVariables.from_collectParcel,GlobalVariables.host,GlobalVariables.mail_server_properties, GlobalVariables.subjectLine_collectParcel,finalBody,"html");	
			
				 
				 // Details Order information send to internal staff member 
				 String [] recipient = GlobalVariables.recipients_FinalOrderInfo.split(",");
				 String body = GlobalVariables.mailBody_FinalOrderInfo;
//				 String paypalStatus ="'"+GlobalVariables.paypal_Success_Status+"','"+GlobalVariables.paypal_SuccessWithWarning_Status+"'";
				 
				 List<Map<String,Object>> finalOrderDetails = qrun.query(GlobalVariables.getFinalOrderInfoQuery,new MapListHandler(),
						 order_data.get(0).get("order_id").toString(),user_key,GlobalVariables.order_status_ordered);
				 
				 System.out.println("Main final body :"+GlobalVariables.mailBody_FinalOrderInfo);
				 System.out.println(body.indexOf("$")+finalOrderDetails.size() +""+finalOrderDetails);
				 System.out.println(GlobalVariables.getFinalOrderInfoQuery);
				 System.out.println(order_data.get(0).get("order_id").toString());
				 System.out.println(user_key);
				 
				 if(finalOrderDetails != null){
						for(int i=0; i<finalOrderDetails.size();i++){
							 Map<String,Object> m = finalOrderDetails.get(i);
							 
							 String temp = "";
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + m.get("user_order_id");
							 body = temp + body.substring(body.indexOf("$")+1);
							 
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + m.get("transactionid");
							 body = temp + body.substring(body.indexOf("$")+1);
							 
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + m.get("order_status");
							 body = temp + body.substring(body.indexOf("$")+1);
							 
							 
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + m.get("status");
							 body = temp + body.substring(body.indexOf("$")+1);
							 
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + m.get("collection_type");
							 body = temp + body.substring(body.indexOf("$")+1);
							 
							 temp = body.substring(0, body.indexOf("$"));
							 if(m.get("insurance_cover") !=null){
								 if(Double.parseDouble(m.get("insurance_cover").toString())>0){
									 temp = temp + "Yes";		 
								 }else{
									 temp = temp + "No";
								 }
							 }
							 body = temp + body.substring(body.indexOf("$")+1);
							 
							 
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + m.get("value_parcel");
							 body = temp + body.substring(body.indexOf("$")+1);		
							
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + m.get("order_date").toString();
							 body = temp + body.substring(body.indexOf("$")+1);	
							 
							 
							 
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + m.get("s_name");
							 body = temp + body.substring(body.indexOf("$")+1);
							 
							 
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + m.get("s_postcode");
							 body = temp + body.substring(body.indexOf("$")+1);
							 
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + m.get("s_addressLine1");
							 body = temp + body.substring(body.indexOf("$")+1);
							 
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + m.get("s_addressLine2");
							 body = temp + body.substring(body.indexOf("$")+1);
							 
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + m.get("s_addressLine3");
							 body = temp + body.substring(body.indexOf("$")+1);
							 
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + m.get("s_town");
							 body = temp + body.substring(body.indexOf("$")+1);
							 
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + m.get("s_city");
							 body = temp + body.substring(body.indexOf("$")+1); 
							 
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + m.get("s_phone");
							 body = temp + body.substring(body.indexOf("$")+1); 
							 
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + m.get("s_email");
							 body = temp + body.substring(body.indexOf("$")+1); 
							 
							 temp = body.substring(0, body.indexOf("$"));
							 temp = temp + m.get("delivery_Instructions");
							 body = temp + body.substring(body.indexOf("$")+1); 
							 
						}
						
						String order_detail="";
						for(int i=0; i<cartList.size();i++){
							 Map<String,Object> m = cartList.get(i);
							 
						/*	 m.put("weight"			,CommonDAO.getConvertedValue(m.get("weight").toString()));
							 m.put("quantity"   	,CommonDAO.getConvertedValue(m.get("quantity").toString()));*/
							 m.put("Item_price"		,CommonDAO.getConvertedValue(m.get("Item_price").toString()));
							 m.put("Item_subtotal"  ,CommonDAO.getConvertedValue(m.get("Item_subtotal").toString()));
							 
							 order_detail =order_detail+"<tr>";
							 order_detail =order_detail+"<td align=\"left\">"+m.get("weight")+"</td>";
							 order_detail =order_detail+"<td align=\"left\">"+m.get("quantity")+"</td>";
							 order_detail =order_detail+"<td align=\"left\">&#163;"+m.get("Item_price")+"</td>";
							 order_detail =order_detail+"<td align=\"left\">&#163;"+m.get("Item_subtotal")+"</td>";
							 order_detail =order_detail+"</tr>";
						}
						 order_detail = order_detail+"</table>";
						 
						for(int i=0; i<finalOrderDetails.size();i++){
							 Map<String,Object> m = finalOrderDetails.get(i);
							 
							 m.put("sub_total"			,CommonDAO.getConvertedValue(m.get("sub_total").toString()));
							 m.put("insurance_cover"   	,CommonDAO.getConvertedValue(m.get("insurance_cover").toString()));
							 m.put("vat"				,CommonDAO.getConvertedValue(m.get("vat").toString()));
							 m.put("total_price"  		,CommonDAO.getConvertedValue(m.get("total_price").toString()));
							 
							 order_detail = order_detail+"<br/><table width='100%' border='0' cellspacing='0' cellpadding='3' class='tabuler_forms'><tr><th width='20%' align='left'><strong>Sub Total :</strong> </th><td align='left'><strong>&#163; "+m.get("sub_total")+"</strong></td></tr>";
							 order_detail = order_detail+"<tr><th width='20%' align='left'><strong>Insurance :</strong> </th><td align='left'><strong>&#163; "+m.get("insurance_cover")+"</strong></td></tr>";
							 order_detail = order_detail+"<tr><th width='20%' align='left'><strong>Vat@20% :</strong> </th><td align='left'><strong>&#163; "+m.get("vat")+"</strong></td></tr>";
							 order_detail = order_detail+"<tr><th width='20%' align='left'><strong>Total :</strong> </th><td align='left'><strong>&#163; "+m.get("total_price")+"</strong></td></tr></table>";
						}
						 
						 order_detail = order_detail+"</body></html>";
						 body = body+order_detail;
						 finalBody =body;
					
						 System.out.println("body _str ::"+finalBody);
						 Email.sendEmail(recipient, GlobalVariables.from_FinalOrderInfo,GlobalVariables.host,GlobalVariables.mail_server_properties, GlobalVariables.subjectLine_FinalOrderInfo,finalBody,"html");
						 
						 if(order_data.get(0).get("s_email") !=null && !order_data.get(0).get("s_email").toString().equalsIgnoreCase("")){
							 String[] recipient_customer  = {order_data.get(0).get("s_email").toString()};
							 Email.sendEmail(recipient_customer, GlobalVariables.from_FinalOrderInfo,GlobalVariables.host,GlobalVariables.mail_server_properties, "ZapMyParcel :Order Details",finalBody,"html");
						 }
				 }
			}
			if(session.getAttribute("SuccessWithWarning") !=null ){
				model.addObject("SuccessWithWarning",session.getAttribute("SuccessWithWarning"));
			}
			
			session.removeAttribute("user_key");
			session.removeAttribute("buymore");
			session.removeAttribute("user_order_id");
			session.removeAttribute("SuccessWithWarning");
			session.invalidate();
			
			
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"PayPalServices:paymentConfirm End:::" + e.getMessage(), logger);
			model.addObject("errorClass",GlobalVariables.errorClass);
			model.addObject("ErrorMsg",GlobalVariables.error_Message);
		}
		
		LogUtility.logInfo("PayPalServices:payForOrders End:::" + date, logger);
		return model;	
	}

}
