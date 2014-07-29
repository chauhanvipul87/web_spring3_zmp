package zapmyparcel.utility.common;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import zapmyparcel.utility.email.Email;

public class InitialisedProperties {
	
	public static void initialiseQueries() {
		Properties prop = new Properties();
		try{
			InputStream in = InitialisedProperties.class.getResourceAsStream("queries.properties");
			prop.load(in);
			GlobalVariables.getcountry_qry 	     = prop.getProperty("getcountry_qry");
			GlobalVariables.insert_agent   	     = prop.getProperty("insert_agent");
			GlobalVariables.alloffer_price       = prop.getProperty("alloffer_price");	
			GlobalVariables.getCountryContent 	 =prop.getProperty("getCountryContent");
			GlobalVariables.insert_quote_details =prop.getProperty("insert_quote_details");
			GlobalVariables.getselectedcountry   = prop.getProperty("getselectedcountry");
			GlobalVariables.getSpecificOfferPrice =prop.getProperty("getSpecificOfferPrice");
			GlobalVariables.checkAvailibilityKey = prop.getProperty("checkAvailibilityKey");
			GlobalVariables.insert_temporder     = prop.getProperty("insert_temporder");
			GlobalVariables.getCartItems 		 = prop.getProperty("getCartItems");
			GlobalVariables.removeItem_cart      = prop.getProperty("removeItem_cart");
			GlobalVariables.updateItem_cart   	 = prop.getProperty("updateItem_cart");
			
			GlobalVariables.placeOrder_query      = prop.getProperty("placeOrder_query");
			GlobalVariables.getTempOrderByUserKey = prop.getProperty("getTempOrderByUserKey");
			GlobalVariables.insert_orderDetails   = prop.getProperty("insert_orderDetails");
			GlobalVariables.remove_tempOrder      = prop.getProperty("remove_tempOrder");
			GlobalVariables.comp_delivery_address = prop.getProperty("comp_delivery_address");
			GlobalVariables.entered_item      	  = prop.getProperty("entered_item");
			GlobalVariables.current_itemDetails   = prop.getProperty("current_itemDetails");
			GlobalVariables.remaining_item     	  = prop.getProperty("remaining_item");
			GlobalVariables.insert_delivery_address = prop.getProperty("insert_delivery_address");
			GlobalVariables.update_add_tempOrder    = prop.getProperty("update_add_tempOrder");
			GlobalVariables.showDeliveryAddress_country    = prop.getProperty("showDeliveryAddress_country");
			GlobalVariables.select_all_agentQuery    = prop.getProperty("select_all_agentQuery");
			GlobalVariables.cartsubtotal_query   	 = prop.getProperty("cartsubtotal_query");
			GlobalVariables.selectDelivery_address   = prop.getProperty("selectDelivery_address");
			GlobalVariables.insert_user_registration = prop.getProperty("insert_user_registration");
			GlobalVariables.selectUser_register      = prop.getProperty("selectUser_register");
			GlobalVariables.update_tempOrderFlag     = prop.getProperty("update_tempOrderFlag");
			GlobalVariables.getOrderId    			 = prop.getProperty("getOrderId");
			GlobalVariables.updateOrderForBuyMore     = prop.getProperty("updateOrderForBuyMore");
			GlobalVariables.getOrderData_display     = prop.getProperty("getOrderData_display");
			GlobalVariables.getDeliveryDetailByOrderID = prop.getProperty("getDeliveryDetailByOrderID");
			GlobalVariables.getAgentDetails    		     = prop.getProperty("getAgentDetails");
			GlobalVariables.check_existOrders    	     = prop.getProperty("check_existOrders");
			GlobalVariables.updateCartPriceDetails_order = prop.getProperty("updateCartPriceDetails_order");
			GlobalVariables.remove_OrderDetailsByTempId  = prop.getProperty("remove_OrderDetailsByTempId");
			GlobalVariables.update_OrderDetailsByTempId  = prop.getProperty("update_OrderDetailsByTempId");
			GlobalVariables.check_existOrdersByUser_Key  = prop.getProperty("check_existOrdersByUser_Key");
			GlobalVariables.selectOrderDetailsById       = prop.getProperty("selectOrderDetailsById");
			GlobalVariables.updateDeliveryAddress_orderDetails     = prop.getProperty("updateDeliveryAddress_orderDetails");
			GlobalVariables.updateCheckOutFlag_tempOrder    	   = prop.getProperty("updateCheckOutFlag_tempOrder");
			GlobalVariables.someOrderData1    			 = prop.getProperty("someOrderData1");
			GlobalVariables.insert_paypay_log    	     = prop.getProperty("insert_paypay_log");
			GlobalVariables.paypay_success_insertresponse  = prop.getProperty("paypay_success_insertresponse");
			GlobalVariables.updateOrders_paypalID    	   = prop.getProperty("updateOrders_paypalID");
			GlobalVariables.insert_paypayErrors    		 = prop.getProperty("insert_paypayErrors");
			GlobalVariables.gettrasaction_id    		 = prop.getProperty("gettrasaction_id");
			GlobalVariables.getFinalItemDetails    	     = prop.getProperty("getFinalItemDetails");
			GlobalVariables.getFinalAgentDetails    	 = prop.getProperty("getFinalAgentDetails");
			GlobalVariables.checkOrderExistErrorOrders 	 = prop.getProperty("checkOrderExistErrorOrders");
			GlobalVariables.updatePaypalErrorCodes    	 = prop.getProperty("updatePaypalErrorCodes");
			GlobalVariables.checkOrderExitsPayment    	 = prop.getProperty("checkOrderExitsPayment");
			GlobalVariables.updatePayment_response    	 = prop.getProperty("updatePayment_response");
			GlobalVariables.getFinalOrderInfoQuery    	 = prop.getProperty("getFinalOrderInfoQuery");
			GlobalVariables.insert_contactUs    		 = prop.getProperty("insert_contactUs");
			GlobalVariables.selectTrackingOrderId    	 = prop.getProperty("selectTrackingOrderId");
			GlobalVariables.selectTrakingErrors    		 = prop.getProperty("selectTrakingErrors");
			GlobalVariables.getFinalOrderInfoAdmin   	 = prop.getProperty("getFinalOrderInfoAdmin");
			GlobalVariables.getFinalItemDetailsAdmin     = prop.getProperty("getFinalItemDetailsAdmin");
			GlobalVariables.removeDeliveryAddress    	 = prop.getProperty("removeDeliveryAddress");
			GlobalVariables.updateInsurance_cover    	 = prop.getProperty("updateInsurance_cover");
			GlobalVariables.updateSignatureQuery    	 = prop.getProperty("updateSignatureQuery");
//			GlobalVariables.checkOrderExitsPayment    	 = prop.getProperty("checkOrderExitsPayment");
//			GlobalVariables.checkOrderExitsPayment    	 = prop.getProperty("checkOrderExitsPayment");
//			GlobalVariables.checkOrderExitsPayment    	 = prop.getProperty("checkOrderExitsPayment");
//			GlobalVariables.checkOrderExitsPayment    	 = prop.getProperty("checkOrderExitsPayment");
			
			
			
			
			
			
			
			GlobalVariables.validateLogin = prop.getProperty("validateLogin");
			GlobalVariables.adminReport = prop.getProperty("adminReport");
			GlobalVariables.carriersList = prop.getProperty("carriersList");
			GlobalVariables.getAgentName = prop.getProperty("getAgentName");
			GlobalVariables.countParcelForOrder = prop.getProperty("countParcelForOrder");
			GlobalVariables.getCarrierName = prop.getProperty("getCarrierName");
			GlobalVariables.updateSingleOrder =prop.getProperty("updateSingleOrder");
			GlobalVariables.retrieveSingleOrder =prop.getProperty("retrieveSingleOrder");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void initialiseMailsParam() {
		Properties prop = new Properties();
		try{
			
			InputStream in = Email.class.getResourceAsStream("mailconfig.properties");
			prop.load(in);

			GlobalVariables.host 					=prop.getProperty("host");
			GlobalVariables.mail_server_properties	=prop.getProperty("mail_server_properties");
			
			GlobalVariables.recipients_moreWeight 	=prop.getProperty("recipients_moreWeight");
			GlobalVariables.subjectLine_moreWeight  =prop.getProperty("subjectLine_moreWeight");
			GlobalVariables.mailBody_moreWeight     =prop.getProperty("mailBody_moreWeight");
			GlobalVariables.from_moreWeight         =prop.getProperty("from_moreWeight");
			
			GlobalVariables.recipients_postCodeFinder 	  = prop.getProperty("recipients_postCodeFinder");
			GlobalVariables.subjectLine_postCodeFinder    = prop.getProperty("subjectLine_postCodeFinder");
			GlobalVariables.mailBody_postCodeFinder       = prop.getProperty("mailBody_postCodeFinder");
			GlobalVariables.from_postCodeFinder           = prop.getProperty("from_postCodeFinder");
			
			
			GlobalVariables.recipients_collectParcel 	  = prop.getProperty("recipients_collectParcel");
			GlobalVariables.subjectLine_collectParcel    = prop.getProperty("subjectLine_collectParcel");
			GlobalVariables.mailBody_collectParcel       = prop.getProperty("mailBody_collectParcel");
			GlobalVariables.from_collectParcel           = prop.getProperty("from_collectParcel");
			GlobalVariables.mailBody_collectParcel_agent = prop.getProperty("mailBody_collectParcel_agent");
			
			/* send mail for Final Order Notification Details to internal staff */
			
			GlobalVariables.recipients_FinalOrderInfo 	  = prop.getProperty("recipients_FinalOrderInfo");
			GlobalVariables.subjectLine_FinalOrderInfo    = prop.getProperty("subjectLine_FinalOrderInfo");
			GlobalVariables.mailBody_FinalOrderInfo       = prop.getProperty("mailBody_FinalOrderInfo");
			GlobalVariables.from_FinalOrderInfo           = prop.getProperty("from_FinalOrderInfo");
			
			
			/*  send mail for contact us to internal staff members */
				
			GlobalVariables.recipients_ContactUs 	 = prop.getProperty("recipients_ContactUs");
			GlobalVariables.subjectLine_ContactUs    = prop.getProperty("subjectLine_ContactUs");
			GlobalVariables.mailBody_ContactUs       = prop.getProperty("mailBody_ContactUs");
			GlobalVariables.from_ContactUs           = prop.getProperty("from_ContactUs");

			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	
	public static void initialiseErrors(){
		Properties prop = new Properties();
		try{
			
			InputStream in = InitialisedProperties.class.getResourceAsStream("error.properties");
			prop.load(in);
			GlobalVariables.success_requestQuote = prop.getProperty("success_requestQuote");
			GlobalVariables.error_requestQuote   = prop.getProperty("error_requestQuote");
			GlobalVariables.error_Message        = prop.getProperty("error_Message");
			GlobalVariables.addToCart_success    = prop.getProperty("addToCart_success");
			GlobalVariables.updateItem_Cart_success =prop.getProperty("updateItem_Cart_success");
			GlobalVariables.removeItem_cart_success = prop.getProperty("removeItem_cart_success");
			GlobalVariables.orderMore_success_msg = prop.getProperty("orderMore_success_msg");
			GlobalVariables.contactus_message 	  = prop.getProperty("contactus_message");
//			GlobalVariables.removeItem_cart_success = prop.getProperty("removeItem_cart_success");
//			GlobalVariables.removeItem_cart_success = prop.getProperty("removeItem_cart_success");
//			GlobalVariables.removeItem_cart_success = prop.getProperty("removeItem_cart_success");
//			GlobalVariables.removeItem_cart_success = prop.getProperty("removeItem_cart_success");
//			GlobalVariables.removeItem_cart_success = prop.getProperty("removeItem_cart_success");
			
			
			
			GlobalVariables.inValidLogin = prop.getProperty("inValidLogin");
			GlobalVariables.order_success_msg		= prop.getProperty("order_success_msg");
			GlobalVariables.order_error_msg 		= prop.getProperty("order_error_msg");
			GlobalVariables.enter_emailmsg 			= prop.getProperty("enter_emailmsg");
			GlobalVariables.enter_passwordmsg	    = prop.getProperty("enter_passwordmsg");
			GlobalVariables.noteAuthorized 			= prop.getProperty("noteAuthorized");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void initialiseConfig(){
		Properties prop = new Properties();
		try{
			InputStream in = InitialisedProperties.class.getResourceAsStream("config.properties");
			//prop.load(in);
//			GlobalVariables.goodsInDirPath = prop.getProperty("goodsindirpath");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void initialisePayPayConfig(){
		Properties prop = new Properties();
		try{
			InputStream in = InitialisedProperties.class.getResourceAsStream("paypalconfig.properties");
			prop.load(in);
			
			GlobalVariables.PayPal_APIUsername 			= prop.getProperty("PayPal_APIUsername");
			GlobalVariables.PayPal_APIPassword    	    = prop.getProperty("PayPal_APIPassword");
			GlobalVariables.PayPal_Signature       		= prop.getProperty("PayPal_Signature");
			GlobalVariables.PayPal_Environment   		= prop.getProperty("PayPal_Environment");
			GlobalVariables.PayPal_Subject		 		= prop.getProperty("PayPal_Subject");
			GlobalVariables.PayPal_VERSION         		= prop.getProperty("PayPal_VERSION");
			GlobalVariables.PayPal_METHOD 		   		= prop.getProperty("PayPal_METHOD");
			
			GlobalVariables.PayPal_paymentAction        = prop.getProperty("PayPal_paymentAction");
			GlobalVariables.PayPay_CurrencyCode         = prop.getProperty("PayPay_CurrencyCode");
			GlobalVariables.PayPay_countryCode        	= prop.getProperty("PayPay_countryCode");
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public static void initialisePostCodeFinderConfig(){
		Properties prop = new Properties();
		try{
			InputStream in = InitialisedProperties.class.getResourceAsStream("postcodefinderconfig.properties");
			prop.load(in);
			GlobalVariables.postCodeURL				 = prop.getProperty("postCodeURL");
			GlobalVariables.postCodeFinder_key	 	 = prop.getProperty("postCodeFinder_key");
			GlobalVariables.postCodeFinder_userName  = prop.getProperty("postCodeFinder_userName");
			GlobalVariables.postCodeFinder_building  = prop.getProperty("postCodeFinder_building");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static void main(String args[])
	{
		/*initialiseConfig();
		System.out.println("OrderType is : "+GlobalVariables.ordertype);
		System.out.println("FullfillmentType is : "+GlobalVariables.fullfillmenttype);
		System.out.println("GiftWrap is : "+GlobalVariables.giftwrap);
		InitialisedProperties ini = new InitialisedProperties();
		String seprator=null;
		List returnlist = ini.readblock(GlobalVariables.fullfillmenttype, seprator);
		System.out.println("List Size : "+returnlist.size());*/
	}
	
	public List readblock(String str,String seprator)
	{
		if( seprator==null || seprator.equals(""))
		{
			seprator=",";
		}
		StringTokenizer st;
		st = new StringTokenizer(str,seprator);
	//	System.out.println("Total CountTokens : "+st.countTokens());
		List list=new ArrayList();
		int count=0;
		while(st.hasMoreTokens()) 
		{
			list.add(st.nextToken());
		//	System.out.println("List["+count+"] : "+list.get(count));
			count++;
		}
		return list;
	}

	

}
