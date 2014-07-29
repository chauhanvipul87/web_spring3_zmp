/*
 * Copyright 2005, 2008 PayPal, Inc. All Rights Reserved.
 *
 * DoDirectPayment NVP example; last modified 08MAY23. 
 *
 * Process a credit card payment.  
 */

package zapmyparcel.service.paypay;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;

import zapmyparcel.utility.common.CommonDAO;
import zapmyparcel.utility.common.GlobalVariables;
import zapmyparcel.utility.date.DateTimeFormater;
import zapmyparcel.utility.db.DbUtils;

import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.core.nvp.NVPEncoder;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.NVPCallerServices;
/**
 * PayPal Java SDK sample code
 */
public class DoDirectPayment 
{
	Date date = new Date();
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String ErrorMsg="";
	Logger logger= Logger.getLogger(this.getClass().getName());
	
	public Map<String,Object> DoDirectPaymentCode(String amount,String cardType,String acct,String expdate,String cvv2, String firstName,
								String lastName, String street, String city,String zip,String ipAddress,String email,String order_id,String user_key, HttpSession session)
	{
		NVPEncoder encoder = new NVPEncoder();
		NVPDecoder decoder = new NVPDecoder();
		Map<String,Object> returnMap = new HashMap<String, Object>();
		try
		{
 
			NVPCallerServices caller = new NVPCallerServices();
//			System.out.println("caller get API version "+caller.getAPIVersion());
		
		    APIProfile profile = ProfileFactory.createSignatureAPIProfile();
//			System.out.println("caller get API version "+profile.getAPIPassword());
		    /*
			 WARNING: Do not embed plaintext credentials in your application code.
			 Doing so is insecure and against best practices.
			 Your API credentials must be handled securely. Please consider
			 encrypting them for use in any production environment, and ensure
			 that only authorized individuals may view or modify them.
			 */
		// Set up your API credentials, PayPal end point, API operation and version.
		    
//			System.out.println("GlobalVariables.PayPal_APIUsername "+GlobalVariables.PayPal_APIUsername);
//			System.out.println("PayPal_APIPassword "+GlobalVariables.PayPal_APIPassword);
//			System.out.println("GlobalVariables.PayPal_APIUsername "+GlobalVariables.PayPal_APIUsername);
//			System.out.println("PayPal_Signature "+GlobalVariables.PayPal_Signature);
//			System.out.println("PayPal_Environment "+GlobalVariables.PayPal_Environment);
//			System.out.println("PayPal_Subject "+GlobalVariables.PayPal_Subject);
//			
//			System.out.println("PayPal_VERSION "+GlobalVariables.PayPal_VERSION);
//			System.out.println("PayPal_METHOD "+GlobalVariables.PayPal_METHOD);
//			System.out.println("PayPal_paymentAction "+GlobalVariables.PayPal_paymentAction);
//			System.out.println("PayPay_countryCode "+GlobalVariables.PayPay_countryCode);
//			System.out.println("PayPay_CurrencyCode "+GlobalVariables.PayPay_CurrencyCode);
			
		    
			profile.setAPIUsername(GlobalVariables.PayPal_APIUsername);
	        profile.setAPIPassword(GlobalVariables.PayPal_APIPassword);
	        profile.setSignature(GlobalVariables.PayPal_Signature);
	        profile.setEnvironment(GlobalVariables.PayPal_Environment);
	        profile.setSubject(GlobalVariables.PayPal_Subject);
	        caller.setAPIProfile(profile);
 
			encoder.add("VERSION", GlobalVariables.PayPal_VERSION);
			encoder.add("METHOD",GlobalVariables.PayPal_METHOD);
 
		// Add request-specific fields to the request string.
			encoder.add("PAYMENTACTION",GlobalVariables.PayPal_paymentAction);
			
			encoder.add("AMT",amount);
			encoder.add("CREDITCARDTYPE",cardType);		
			//encoder.add("ACCT","4539644852839415");  // this is for generating error	
			encoder.add("ACCT",acct);	
			encoder.add("EXPDATE",expdate);
			encoder.add("CVV2",cvv2);
			encoder.add("FIRSTNAME",firstName);
			encoder.add("LASTNAME",lastName);										
			encoder.add("STREET",street);
			encoder.add("CITY",city);	
//			encoder.add("STATE",state);			
//			encoder.add("ZIP",zip);	
		    encoder.add("COUNTRYCODE",GlobalVariables.PayPay_countryCode);
			encoder.add("IPADDRESS",ipAddress); // open comment when go to live
			encoder.add("EMAIL",email);
			encoder.add("CURRENCYCODE",GlobalVariables.PayPay_CurrencyCode);
			encoder.add("INVNUM",order_id);  // open comment when go to live
			
			if(acct.equalsIgnoreCase("4783407338464235")){
				encoder.add("L_NAME0","Test");
				encoder.add("L_DESC0","Test");
				encoder.add("L_AMT0","1.00");
				encoder.add("L_NUMBER0","1111");
				encoder.add("L_QTY0","1");
			}
			
			if(acct.equalsIgnoreCase("4916985889534399")){
				encoder.add("L_NAME0","Test");
				encoder.add("L_DESC0","Test");
				encoder.add("L_AMT0","1.00");
				encoder.add("L_NUMBER0","1111");
				encoder.add("L_QTY0","1");
				encoder.add("ACCT","4539644852839415");  // this is for generating error	
			}
			
		// Execute the API operation and obtain the response.
			String NVPRequest = encoder.encode();
			String NVPResponse =(String) caller.call(NVPRequest);
			System.out.println("Main res :"+NVPResponse);
			decoder.decode(NVPResponse);
			System.out.println("decoder.get(ACK) :"+decoder.get("ACK") );
			
			if(decoder.get("ACK") != null){
				if(decoder.get("ACK").equalsIgnoreCase(GlobalVariables.paypal_Success_Status)){
					
					int paypal_response_id = new CommonDAO().getautoGenerateID("paypay_response","paypal_response_id");
					String status 			= decoder.get("ACK");
					String timestamps	    = decoder.get("TIMESTAMP");
					String correlationid    = decoder.get("CORRELATIONID");
					String versions 		= decoder.get("VERSION");
					String build			= decoder.get("BUILD");
					String tran_amount	    = decoder.get("AMT");
					String currency_code	= decoder.get("CURRENCYCODE");
					String avscode			= decoder.get("AVSCODE");
					String cvv2Match	    = decoder.get("CVV2MATCH");
					String transactionid	= decoder.get("TRANSACTIONID");
					
					// check if payment id is already there in table if yes then only update some field , if not found then insert new record into 
					Integer payment_id = (Integer) qrun.query(GlobalVariables.checkOrderExitsPayment,new ScalarHandler(),order_id,user_key);
					System.out.println("user_key"+user_key);
					System.out.println("order_id"+order_id);
					System.out.println("payment_id"+payment_id);
					System.out.println("GlobalVariables.checkOrderExitsPayment :"+GlobalVariables.checkOrderExitsPayment);
					System.out.println("GlobalVariables.updatePayment_response"+GlobalVariables.updatePayment_response);
					
					System.out.println();
					if(payment_id !=null){
						int affectedRows= qrun.update(GlobalVariables.updatePayment_response,avscode,cvv2Match,transactionid,status,payment_id,order_id,user_key);
						if(affectedRows >0){
							// update order table for change status from notfied to ordered.
							Object[] obj_param = {GlobalVariables.order_status_ordered,order_id,user_key,GlobalVariables.order_status_notified};
							affectedRows= qrun.update(GlobalVariables.updateOrders_paypalID,obj_param) ;
							
							if(affectedRows >0){
								// update temp order table for the checkoutFlag set to 1 so it will not display in cart..
								 affectedRows= qrun.update(GlobalVariables.updateCheckOutFlag_tempOrder,user_key);
							}
						}
						returnMap.put("ACK",decoder.get("ACK"));
						return returnMap;
						
					}else{
						Object[] param = {paypal_response_id,order_id,"0",status,timestamps,correlationid,versions,build,tran_amount,currency_code,
								avscode,cvv2Match,transactionid,user_key,new DateTimeFormater().format_datetime()};
						
						int affectedRows= qrun.update(GlobalVariables.paypay_success_insertresponse,param) ;
						System.out.println("update row in respose susscess "+affectedRows);
						if(affectedRows >0){
							new CommonDAO().setautoGenerateID("paypay_response","paypal_response_id",(paypal_response_id+1));
							// update order table 
							Object[] obj_param = {GlobalVariables.order_status_ordered,order_id,user_key,GlobalVariables.order_status_notified};
							affectedRows= qrun.update(GlobalVariables.updateOrders_paypalID,obj_param) ;
							System.out.println("in if .. "+affectedRows);
							// update temp order table for the checkoutFlag set to 1 so it will not display in cart..
							int affected_rows=qrun.update(GlobalVariables.updateCheckOutFlag_tempOrder,user_key);
					}
						returnMap.put("ACK",decoder.get("ACK"));
						return returnMap;
					}
				}else if(decoder.get("ACK").equalsIgnoreCase(GlobalVariables.paypal_Failure_Status) || decoder.get("ACK").equalsIgnoreCase(GlobalVariables.paypal_FailureWithWarning_Status)){
						String status =decoder.get("ACK");
						String timestamps	    = decoder.get("TIMESTAMP");
						String correlationid    = decoder.get("CORRELATIONID");
						String versions 		= decoder.get("VERSION");
						String build			= decoder.get("BUILD");
						String tran_amount	    = decoder.get("AMT");
						String currency_code	= decoder.get("CURRENCYCODE");
						
						
						Integer error_id = (Integer) qrun.query(GlobalVariables.checkOrderExistErrorOrders,new ScalarHandler(),order_id);
						String returnMessage ="";
						if(error_id != null ){
							for(int i=0;decoder.get("L_ERRORCODE"+i)!=null ;i++){
								System.out.println(decoder.get("L_ERRORCODE"+i));
								System.out.println(decoder.get("L_LONGMESSAGE"+i));
//								System.out.println(decoder.get("L_SHORTMESSAGE"+i));
//								System.out.println(decoder.get("L_SEVERITYCODE"+i));
								
								String L_ERRORCODE			= decoder.get("L_ERRORCODE"+i);
								String L_SHORTMESSAGE	    = decoder.get("L_SHORTMESSAGE"+i);
								String L_LONGMESSAGE		= decoder.get("L_LONGMESSAGE"+i);
								String L_SEVERITYCODE       = decoder.get("L_SEVERITYCODE"+i);
								
								Object [] paypayErrors = {error_id,order_id,L_ERRORCODE,L_SHORTMESSAGE,L_LONGMESSAGE,L_SEVERITYCODE,new DateTimeFormater().format_datetime()};
								int affectedRows= qrun.update(GlobalVariables.insert_paypayErrors,paypayErrors) ;
								returnMessage =returnMessage+"<li>"+L_LONGMESSAGE+"</li>";
							}
						}else{
							
							int paypal_errorid = new CommonDAO().getautoGenerateID("paypay_errors","paypal_errorid");
							
							for(int i=0;decoder.get("L_ERRORCODE"+i)!=null ;i++){
								System.out.println(decoder.get("L_ERRORCODE"+i));
								System.out.println(decoder.get("L_LONGMESSAGE"+i));
//								System.out.println(decoder.get("L_SHORTMESSAGE"+i));
//								System.out.println(decoder.get("L_SEVERITYCODE"+i));
								
								String L_ERRORCODE			= decoder.get("L_ERRORCODE"+i);
								String L_SHORTMESSAGE	    = decoder.get("L_SHORTMESSAGE"+i);
								String L_LONGMESSAGE		= decoder.get("L_LONGMESSAGE"+i);
								String L_SEVERITYCODE       = decoder.get("L_SEVERITYCODE"+i);
								
								Object [] paypayErrors = {paypal_errorid,order_id,L_ERRORCODE,L_SHORTMESSAGE,L_LONGMESSAGE,L_SEVERITYCODE,new DateTimeFormater().format_datetime()};
								int affectedRows= qrun.update(GlobalVariables.insert_paypayErrors,paypayErrors) ;
								returnMessage =returnMessage+"<li>"+L_LONGMESSAGE+"</li>";
							}
							
							if(!returnMessage.equalsIgnoreCase("")){
								new CommonDAO().setautoGenerateID("paypay_errors","paypal_errorid",(paypal_errorid+1));
							}
							
							int paypal_response_id = new CommonDAO().getautoGenerateID("paypay_response","paypal_response_id");
							
							Object[] param = {paypal_response_id,order_id,paypal_errorid,status,timestamps,correlationid,versions,build,tran_amount,currency_code,
									"","","",user_key,new DateTimeFormater().format_datetime()};
							int affectedRows= qrun.update(GlobalVariables.paypay_success_insertresponse,param) ;
							if(affectedRows >0){
								new CommonDAO().setautoGenerateID("paypay_response","paypal_response_id",(paypal_response_id+1));
							}
						}
							
						returnMap.put("ACK",decoder.get("ACK"));
						returnMap.put("Message","<h4>Following error(s) occured</h4><ol>"+returnMessage+"</ol>");

						return returnMap;
					}else if(decoder.get("ACK").equalsIgnoreCase(GlobalVariables.paypal_SuccessWithWarning_Status)){
						
						int paypal_response_id = new CommonDAO().getautoGenerateID("paypay_response","paypal_response_id");
						String status 			= decoder.get("ACK");
						String timestamps	    = decoder.get("TIMESTAMP");
						String correlationid    = decoder.get("CORRELATIONID");
						String versions 		= decoder.get("VERSION");
						String build			= decoder.get("BUILD");
						String tran_amount	    = decoder.get("AMT");
						String currency_code	= decoder.get("CURRENCYCODE");
						String avscode			= decoder.get("AVSCODE");
						String cvv2Match	    = decoder.get("CVV2MATCH");
						String transactionid	= decoder.get("TRANSACTIONID");
						
						
			 /* -------------------------------------------------------- For handing Errors --------------------------------------------------------*/
						// check if error is there in order 
						
						Integer error_id = (Integer) qrun.query(GlobalVariables.checkOrderExistErrorOrders,new ScalarHandler(),order_id);
						
						String returnMessage="";
						
						if(error_id != null){
							for(int i=0;decoder.get("L_ERRORCODE"+i)!=null ;i++){
								System.out.println(decoder.get("L_ERRORCODE"+i));
								System.out.println(decoder.get("L_LONGMESSAGE"+i));
//								System.out.println(decoder.get("L_SHORTMESSAGE"+i));
//								System.out.println(decoder.get("L_SEVERITYCODE"+i));
								
								String L_ERRORCODE			= decoder.get("L_ERRORCODE"+i);
								String L_SHORTMESSAGE	    = decoder.get("L_SHORTMESSAGE"+i);
								String L_LONGMESSAGE		= decoder.get("L_LONGMESSAGE"+i);
								String L_SEVERITYCODE       = decoder.get("L_SEVERITYCODE"+i);
								
								Object [] paypayErrors = {error_id,order_id,L_ERRORCODE,L_SHORTMESSAGE,L_LONGMESSAGE,L_SEVERITYCODE,new DateTimeFormater().format_datetime()};
								int affectedRows= qrun.update(GlobalVariables.insert_paypayErrors,paypayErrors) ;
								returnMessage =returnMessage+"<p>"+L_LONGMESSAGE+"</p><br/>";
							}
							
							
							// check if payment id is already there in table if yes then only update some field , if not found then insert new record into 
							Integer payment_id = (Integer) qrun.query(GlobalVariables.checkOrderExitsPayment,new ScalarHandler(),order_id,user_key);
							if(payment_id !=null){
								
								int affectedRows= qrun.update(GlobalVariables.updatePayment_response,avscode,cvv2Match,transactionid,GlobalVariables.paypal_SuccessWithWarning_Status,payment_id,order_id,user_key);
								if(affectedRows >0){
									// update order table for change status from notfied to ordered.
									Object[] obj_param = {GlobalVariables.order_status_ordered,order_id,user_key,GlobalVariables.order_status_notified};
									affectedRows= qrun.update(GlobalVariables.updateOrders_paypalID,obj_param) ;
									
									if(affectedRows >0){
										// update temp order table for the checkoutFlag set to 1 so it will not display in cart..
										 affectedRows= qrun.update(GlobalVariables.updateCheckOutFlag_tempOrder,user_key);
									}
								}
								returnMap.put("ACK",decoder.get("ACK"));
								returnMap.put("Message",returnMessage);
								return returnMap;
							}
						}else{
							int paypal_errorid = new CommonDAO().getautoGenerateID("paypay_errors","paypal_errorid");
							for(int i=0;decoder.get("L_ERRORCODE"+i)!=null ;i++){
								System.out.println(decoder.get("L_ERRORCODE"+i));
								System.out.println(decoder.get("L_LONGMESSAGE"+i));
//								System.out.println(decoder.get("L_SHORTMESSAGE"+i));
//								System.out.println(decoder.get("L_SEVERITYCODE"+i));
								
								String L_ERRORCODE			= decoder.get("L_ERRORCODE"+i);
								String L_SHORTMESSAGE	    = decoder.get("L_SHORTMESSAGE"+i);
								String L_LONGMESSAGE		= decoder.get("L_LONGMESSAGE"+i);
								String L_SEVERITYCODE       = decoder.get("L_SEVERITYCODE"+i);
								
								Object [] paypayErrors = {paypal_errorid,order_id,L_ERRORCODE,L_SHORTMESSAGE,L_LONGMESSAGE,L_SEVERITYCODE,new DateTimeFormater().format_datetime()};
								int affectedRows= qrun.update(GlobalVariables.insert_paypayErrors,paypayErrors) ; //with new error id
								returnMessage =returnMessage+"<p>"+L_LONGMESSAGE+"</p><br/>";
							}
							
							if(!returnMessage.equalsIgnoreCase("")){
								new CommonDAO().setautoGenerateID("paypay_errors","paypal_errorid",(paypal_errorid+1));
							}
							
							paypal_response_id = new CommonDAO().getautoGenerateID("paypay_response","paypal_response_id");
							
							Object[] param = {paypal_response_id,order_id,paypal_errorid,status,timestamps,correlationid,versions,build,tran_amount,currency_code,
									avscode,cvv2Match,transactionid,user_key,new DateTimeFormater().format_datetime()};
							
							int affectedRows= qrun.update(GlobalVariables.paypay_success_insertresponse,param) ;
							System.out.println("update row in respose susscess "+affectedRows);
							if(affectedRows >0){
								new CommonDAO().setautoGenerateID("paypay_response","paypal_response_id",(paypal_response_id+1));
								// update order table 
								Object[] obj_param = {GlobalVariables.order_status_ordered,order_id,user_key,GlobalVariables.order_status_notified};
								affectedRows= qrun.update(GlobalVariables.updateOrders_paypalID,obj_param) ;
								System.out.println("in if .. "+affectedRows);
								// update temp order table for the checkoutFlag set to 1 so it will not display in cart..
								int affected_rows=qrun.update(GlobalVariables.updateCheckOutFlag_tempOrder,user_key);
						}
							returnMap.put("ACK",decoder.get("ACK"));
							returnMap.put("Message",returnMessage);
							return returnMap;
						}
					}else if(decoder.get("ACK").equalsIgnoreCase(GlobalVariables.paypal_FailureWithWarning_Status)) {
							
						
					}
			}
		} catch(Exception ex)
		{
			ex.printStackTrace();
			returnMap.put("ACK","error");
			returnMap.put("Message",GlobalVariables.error_Message);
			return returnMap;
		}
		returnMap.put("ACK","error");
		returnMap.put("Message",GlobalVariables.error_Message);
		return returnMap;
	}
	
}