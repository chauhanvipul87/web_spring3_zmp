package zapmyparcel.utility.common;

public class GlobalVariables {
	
	public static String ZEROSTR = "0";
	public static String ONESTR = "1";
	
	public static int ZEROINT = 0;
	public static int ONEINT = 1;

	public static int orderNoLimit = 8;
	
	public static String querySeparator ="#";
	
	/* started by vipul for zmp */
	
	public static String getcountry_qry;
	public static String insert_agent;
	public static String alloffer_price;
	public static String getCountryContent;
	public static String insert_quote_details;
	public static String getselectedcountry;
	public static String getSpecificOfferPrice;
	public static String checkAvailibilityKey;
	public static String insert_temporder;
	public static String getCartItems;
	public static String removeItem_cart;
	public static String updateItem_cart;
	public static String placeOrder_query;
	public static String getTempOrderByUserKey;
	public static String insert_orderDetails;
	public static String remove_tempOrder;
	public static String comp_delivery_address;
	public static String entered_item;
	public static String current_itemDetails;
	public static String remaining_item;
	public static String insert_delivery_address; 
	public static String update_add_tempOrder; 
	public static String validateLogin; 
	public static String adminReport;
	public static String carriersList;
	public static String getAgentName;
	public static String countParcelForOrder;	
	public static String getCarrierName;
	public static String updateSingleOrder;
	public static String retrieveSingleOrder;

	
	public static String showDeliveryAddress_country;
	public static String select_all_agentQuery; 
	public static String cartsubtotal_query;
	public static String selectDelivery_address; 
	public static String insert_user_registration; 
	public static String selectUser_register;
	public static String update_tempOrderFlag ;
	public static String  getOrderId;
	public static String updateOrderForBuyMore;
	public static String getOrderData_display;
	public static String getDeliveryDetailByOrderID;
	public static String getAgentDetails;
	public static String check_existOrders;
	public static String updateCartPriceDetails_order;
	public static String remove_OrderDetailsByTempId;
	public static String update_OrderDetailsByTempId;
	public static String check_existOrdersByUser_Key;
	public static String selectOrderDetailsById;
	public static String updateDeliveryAddress_orderDetails ;
	public static String updateCheckOutFlag_tempOrder ;
	public static String someOrderData1 ;
	public static String insert_paypay_log;
	public static String paypay_success_insertresponse;
	public static String updateOrders_paypalID ;
	public static String insert_paypayErrors;
	public static String gettrasaction_id ;
	public static String getFinalItemDetails;
	public static String getFinalAgentDetails;
	public static String checkOrderExistErrorOrders  ;
	public static String updatePaypalErrorCodes ;
	public static String checkOrderExitsPayment ;
	public static String updatePayment_response ;
	public static String getFinalOrderInfoQuery ;
	public static String insert_contactUs ;
	public static String selectTrackingOrderId ;
	public static String selectTrakingErrors ;
	public static String getFinalOrderInfoAdmin ;
	public static String getFinalItemDetailsAdmin ;
	public static String removeDeliveryAddress ;
	public static String updateInsurance_cover ;
	public static String updateSignatureQuery;
//	public static String updatePaypalErrorCodes ;
//	public static String updatePaypalErrorCodes ;
//	public static String updatePaypalErrorCodes ;
//	public static String updatePaypalErrorCodes ;
//	public static String updatePaypalErrorCodes ;
//	public static String updatePaypalErrorCodes ;
	
	
	
	
	
	
	
	
	/* PayPay Config Variables */
	
	public static String PayPal_APIUsername;
	public static String PayPal_APIPassword;
	public static String PayPal_Signature;
	public static String PayPal_Environment;
	public static String PayPal_Subject;
	public static String PayPal_VERSION;
	public static String PayPal_METHOD;
	
	public static String PayPal_paymentAction ;
	public static String PayPay_CurrencyCode ;
	public static String PayPay_countryCode ;
	
	
	/* ended by vipul  for zmp */
	
	//success and error msg 
	public static String success_requestQuote;
	public static String error_requestQuote;
	public static String error_Message;
	public static String addToCart_success;
	public static String updateItem_Cart_success;
	public static String removeItem_cart_success;
	public static String inValidLogin;
	public static String order_success_msg;
	public static String order_error_msg;
	public static String enter_emailmsg;
	public static String enter_passwordmsg;
	public static String noteAuthorized;
	public static String orderMore_success_msg;
	public static String contactus_message;
	public static String host;
	public static String mail_server_properties;
	
	public static String  postCodeURL;
	public static String  postCodeFinder_key;
	public static String  postCodeFinder_userName;
	public static String  postCodeFinder_building; 
	
	
	//error message for the post code finder
	public static String []postCodeResponseErrors ={"Unknown error","Unknown key","Account out of credit","Request not allowed from this IP",
											"Request not allowed from this URL","Web service not available on this key","Web service not available on your plan",
											"Key daily limit exceeded","Surge protector running","Surge protector triggered","No valid license available",
											"Management key required","Demo limit exceeded","Free service limit exceeded","Wrong type of key","Key expired",
											"Postcode Required","Postcode Invalid"} ;
	
	public static String []postCodeResponseDescription ={  "-1#The cause of the error is unknown but details have been passed to our support staff who will investigate.#These problems are typically short lived and often resolved by trying again in a few minutes.",
														   "2#The key you are using to access the service was not found.#Please check the key is correct, it should be the in form AA11-AA11-AA11-AA11.",
														   "3#Your account is either out of credit or has insufficient credit to service this request.#Please check your account balance and top it up if necessary.",
														   "4#The request was disallowed from the IP address.#Check the security settings on the key first. If that looks fine, please contact support as it may be from an IP addresses on our blacklist.",
														   "5#The request was disallowed from the URL.#Check the security settings on the key first. If that looks fine, please contact support as it may be from a URL on our black list.",
														   "6#The requested web service is disallowed on this key.#Check the security settings on the key first. You can limit a key to certain web services.",
														   "7#The requested web service is not currently available on your payment plan.#Some services are only available in specific regions due to licensing restrictions. Please contact us for more information.",
														   "8#The daily limit on the key has been exceeded.#Alter the daily limit on the key if the usage seems normal. Check the usage details first though to see if it is unexpected.",
														   "9#The surge protector is currently enabled and has temporarily suspended access to the account.#You can disable the surge protector at any time but this is only recommended if you are running through a batch of requests. Unsuspend your account now.",
														   "10#An unusually large number of requests have been processed for your account and the surge protector has been enabled.#You can disable the surge protector at any time but this is only recommended if you are running through a batch of requests. Unsuspend your account now.",
														   "11#The request requires a valid license but none were found.#Please check your purchase history, you may be using a license that is no longer valid or of an incorrect type.",
														   "12#To use this web service you require a management key. Management can be enabled on any key but it is recommended management keys are use with care.#Log onto the website and create a new management key or change an existing key.",
														   "13#The daily demonstration limit for this service or account has been exceeded.#The limit will be reset at midnight tonight or, if you would like the limit increased, please contact us." +
														   "14#You have used too many free web services.#Many of our web services are designed to operate in several stages - the first is usually a Find service followed by a Retrieve. If you use too many Finds without the corresponding number of retrieves you will receive this error. For more information, please contact us.",
														   "15#The type of key you're using isn't supported by this web service.#This usually happens if you're using a user or server license with a web service that only supports transactional keys. Please try another key and try again.",
														   "16#The key you are trying to use has expired.#Please check that you are using the right key - a new one may have been issued if you have recently renewed your key. Please contact us if your have any questions.",
														   "1001#The Postcode parameter was not supplied.#Please ensure that you supply the Postcode parameter and try again.",
														   "1002#The Postcode parameter was not valid.#The Postcode parameter should be valid UK postcode.",

														};
	
	//error messsages class starts
	public static String errorClass						  ="error";
	public static String warningClass 					  = "warning";
	public static String successClass 					  = "success";
	public static String infoClass 						  = "info";
	
	// Order Table status 
	public static String order_status_notified 			  ="notified";
	public static String order_status_enroute			  ="enroute";
	public static String order_status_delivered 		  ="delivered";
	public static String order_status_ordered 			  ="ordered";
	
	
	public static String order_status_card_left 		  ="card_left";
	public static String order_status_check_address			  ="check_address";
	public static String order_status_refused 		  	  ="refused";
	public static String order_status_rts 			  	  ="rts";
	
	

	public static String order_collection_status_agent	  = "droptoagent";
	public static String order_collection_status_reversed = "reversed";
	
	public static String paypay_request				      ="request";
	public static String paypal_response 				  ="response";
	
	public static String paypal_Success_Status 			  ="Success";
	public static String paypal_Failure_Status 			  ="Failure";
	public static String paypal_SuccessWithWarning_Status ="SuccessWithWarning";
	public static String paypal_FailureWithWarning_Status ="FailureWithWarning";
	

	
	/* send mail when user entered weight more then 20 kg*/ 
	public static String recipients_moreWeight;
	public static String subjectLine_moreWeight;
	public static String mailBody_moreWeight;
	public static String from_moreWeight;
	

	/* send mail when error occur for finding address based on postcode */ 

	public static String recipients_postCodeFinder;
	public static String subjectLine_postCodeFinder;
	public static String mailBody_postCodeFinder;
	public static String from_postCodeFinder;
	
	/* send mail for collect parcel from home  and from agent to internal staff */
	
	public static String recipients_collectParcel;
	public static String subjectLine_collectParcel;
	public static String mailBody_collectParcel;
	public static String from_collectParcel;
	public static String mailBody_collectParcel_agent;
	
	/* send mail for Final Order Notification Details to internal staff */
	
	public static String recipients_FinalOrderInfo;
	public static String subjectLine_FinalOrderInfo;
	public static String from_FinalOrderInfo;
	public static String mailBody_FinalOrderInfo;
	
	
	/*  send mail for contact us to internal staff members */
	
	public static String recipients_ContactUs;
	public static String subjectLine_ContactUs;
	public static String from_ContactUs;
	public static String mailBody_ContactUs;
	
	
}
