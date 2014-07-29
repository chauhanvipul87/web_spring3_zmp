package zapmyparcel.service.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import zapmyparcel.control.AdminController;
import zapmyparcel.utility.common.GlobalVariables;
import zapmyparcel.utility.common.LogUtility;
import zapmyparcel.utility.db.DbUtils;

public class AdminService {
	Date date = new Date();
	Logger logger = Logger.getLogger(AdminService.class);
	String query = "";
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	
	public ModelAndView validateLogin(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("AdminService :: validateLogin() Method Start", logger);
		ModelAndView model = new ModelAndView();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		List<Map<String,Object>> userList = new ArrayList<Map<String,Object>>();
		try {
			query = GlobalVariables.validateLogin;
			userList = qrun.query(query, new MapListHandler(),username,password);
		
			if(userList.size()==1 && userList.get(0).get("delete_flag").toString().equalsIgnoreCase("0")){
				HttpSession session = req.getSession();
				
				Map<String,Object> sessionMap = new HashMap<String, Object>();
				sessionMap.put("userid", userList.get(0).get("userid"));
				sessionMap.put("username", userList.get(0).get("username"));
				sessionMap.put("first_name", userList.get(0).get("first_name"));
				sessionMap.put("last_name", userList.get(0).get("last_name"));
				session.setAttribute("userdetail",sessionMap);
				
				model = new AdminController().adminhome(req, res);
			}else{
				model.addObject("errorMsg", GlobalVariables.inValidLogin);
				model.setViewName("admin/login");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("errorMsg", GlobalVariables.error_Message);
			model.setViewName("admin/login");
		}
		LogUtility.logInfo("AdminService :: validateLogin() Method End", logger);
		return model;
	}

	public ModelAndView adminReport(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("AdminService :: adminReport() Method Start", logger);
		ModelAndView model = new ModelAndView();
		List<Map<String,Object>> tempData = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> reportData = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> carriers = new ArrayList<Map<String,Object>>();
		try {
			String fromDates = req.getParameter("fromDates");
			String toDates = req.getParameter("toDates");
			String additionQuery = "";
			if(fromDates != null && toDates !=null){
				additionQuery = " AND order_date BETWEEN '"+fromDates+"' AND '"+toDates+"' ";
			}else{
				
				String search_date    = req.getParameter("search_date")  == null ?  "" : req.getParameter("search_date");
				if(search_date !=null && !search_date.equals("")){
					additionQuery += " AND orders.order_date = '"+search_date+"'";
				}
				
				String search_zmp_ref    = req.getParameter("search_zmp_ref")  == null ?  "" : req.getParameter("search_zmp_ref");
				if(search_zmp_ref !=null  && !search_zmp_ref.equals("")){
					additionQuery += " AND orders.order_id = "+search_zmp_ref+"";
				}
				
				String search_paypal_status    = req.getParameter("search_paypal_status")  == null ?  "" : req.getParameter("search_paypal_status");
				if(search_paypal_status !=null  && !search_paypal_status.equals("")){
					additionQuery += " AND paypay_response.status = '"+search_paypal_status+"'";
				}
				
				String search_name    = req.getParameter("search_name")  == null ?  "" : req.getParameter("search_name");
				if(search_name !=null  && !search_name.equals("")){
					additionQuery += " AND orders.s_name = '"+search_name+"'";
				}
				
				String search_Insurance    = req.getParameter("search_Insurance")  == null ?  "" : req.getParameter("search_Insurance");
				if(search_Insurance !=null  && !search_Insurance.equals("")){
					int ins =0;
					if(search_Insurance.equalsIgnoreCase("Yes")){
						additionQuery += " AND orders.insurance_cover > 0 ";
					}else{
						additionQuery += " AND orders.insurance_cover <= 0 ";
					}
				}
				
				String search_orderstatus    = req.getParameter("search_orderstatus")  == null ?  "" : req.getParameter("search_orderstatus");
				if(search_orderstatus !=null  && !search_orderstatus.equals("")){
					if(search_orderstatus.equalsIgnoreCase("To be Collected")){
						search_orderstatus =GlobalVariables.order_status_ordered;
					}
					additionQuery += " AND orders.order_status = '"+search_orderstatus+"'";
				}
				
				String search_tracking_no    = req.getParameter("search_tracking_no")  == null ?  "" : req.getParameter("search_tracking_no");
				if(search_tracking_no !=null  && !search_tracking_no.equals("")){
					additionQuery += " AND orders.tracking_no= '"+search_tracking_no+"'";
				}
				
				
				
				
			}
			if(!additionQuery.equals("")){
				query = GlobalVariables.adminReport + additionQuery;
			}else{
				query = GlobalVariables.adminReport;
			}
			
			System.out.println(query);
			tempData = qrun.query(query, new MapListHandler(),GlobalVariables.order_status_enroute,GlobalVariables.order_status_delivered,GlobalVariables.order_status_ordered,GlobalVariables.order_status_notified);
			System.out.println(tempData);
			if(tempData.size() > 0 ){
				for (int i = 0; i <tempData.size(); i++) {
					Map<String,Object> tempMap = new HashMap<String, Object>();
					tempMap = tempData.get(i);
					
					String orderDate="",zapRef="",transactionId="",paymentId="",paymentStatus="";
					String name="",carrierName="",carrierId="",collectionType="";
					String trackingNo="",parcel="",totalPrice="",insurance="No",orderStatus="",order_id,signature;
					String dropAgentId = "0";
					double parcelValue = 0.0;
					float insPrice = 0;
					
					orderDate = tempMap.get("order_date") != null ? tempMap.get("order_date").toString() : "";
					zapRef = tempMap.get("user_order_id") != null ? tempMap.get("user_order_id").toString() : "";
					signature = tempMap.get("signature") != null ? tempMap.get("signature").toString() : "";
					order_id = tempMap.get("order_id") != null ? tempMap.get("order_id").toString() : "";
					transactionId = tempMap.get("transactionid") != null ? tempMap.get("transactionid").toString() : "";
					paymentId = tempMap.get("paypal_response_id") != null ? tempMap.get("paypal_response_id").toString() : "";
					paymentStatus = tempMap.get("status") != null ? tempMap.get("status").toString() : "";
					name = tempMap.get("s_name") != null ? tempMap.get("s_name").toString() : "";
					//carrierName = tempMap.get("") != null ? tempMap.get("").toString() : "-";
					carrierId = tempMap.get("carrier_id") != null ? tempMap.get("carrier_id").toString() : "";
					collectionType = tempMap.get("collection_type") != null ? tempMap.get("collection_type").toString() : "";
					trackingNo = tempMap.get("tracking_no") != null ? tempMap.get("tracking_no").toString() : "";
					//parcel = tempMap.get("num_parcel") != null ? tempMap.get("num_parcel").toString() : "";
					totalPrice = tempMap.get("total_price") != null ? tempMap.get("total_price").toString() : "";
					insPrice = Float.parseFloat(tempMap.get("insurance_cover") != null ? tempMap.get("insurance_cover").toString() : "0.0");
					if(insPrice > 0){
						insurance = "YES";
					}
					if(collectionType.equalsIgnoreCase("droptoagent")){
						dropAgentId = tempMap.get("dropoff_agent_id").toString();
						query = GlobalVariables.getAgentName;
						collectionType = (String) qrun.query(query, new ScalarHandler(),dropAgentId);
					}
					
					
					query = GlobalVariables.countParcelForOrder;
					parcelValue = (Double) qrun.query(query, new ScalarHandler(),order_id);
					parcel = String.valueOf(parcelValue);
					
					String search_carrier    = req.getParameter("search_carrier")  == null ?  "" : req.getParameter("search_carrier");
					if(!search_carrier.equalsIgnoreCase("")){
						if(carrierId.equalsIgnoreCase(search_carrier)){
							System.out.println("carrierId ::"+carrierId);
							query = GlobalVariables.getCarrierName;
							carrierName = (String) qrun.query(query, new ScalarHandler(),carrierId);
						}
					}else{
						query = GlobalVariables.getCarrierName;
						carrierName = (String) qrun.query(query, new ScalarHandler(),carrierId);
					}
					orderStatus = tempMap.get("order_status") != null ? tempMap.get("order_status").toString() : "";
					if(orderStatus.equalsIgnoreCase(GlobalVariables.order_status_ordered)){
						orderStatus = "To be Collected";
					}
					if(!search_carrier.equalsIgnoreCase("")){
						if(carrierId.equalsIgnoreCase(search_carrier)){
							tempMap = new HashMap<String, Object>();
							tempMap.put("orderDate", orderDate);
							tempMap.put("zapRef", zapRef);
							tempMap.put("order_id", order_id);
							tempMap.put("transactionId", transactionId);
							tempMap.put("paymentId", paymentId);
							tempMap.put("paymentStatus", paymentStatus);
							tempMap.put("name", name);
							tempMap.put("carrierName", carrierName);
							tempMap.put("carrierId", carrierId);
							tempMap.put("collectionType", collectionType);
							tempMap.put("trackingNo", trackingNo);
							tempMap.put("parcel", parcel);
							tempMap.put("totalPrice", totalPrice);
							tempMap.put("insurance", insurance);
							tempMap.put("orderStatus", orderStatus);
							tempMap.put("signature", signature);
							reportData.add(tempMap);
						}
					}else{
						String search_type    = req.getParameter("search_type")  == null ?  "" : req.getParameter("search_type");
						if(!search_type.equalsIgnoreCase("")){
							if(collectionType.equalsIgnoreCase(search_type)){
								tempMap = new HashMap<String, Object>();
								tempMap.put("orderDate", orderDate);
								tempMap.put("zapRef", zapRef);
								tempMap.put("order_id", order_id);
								tempMap.put("transactionId", transactionId);
								tempMap.put("paymentId", paymentId);
								tempMap.put("paymentStatus", paymentStatus);
								tempMap.put("name", name);
								tempMap.put("carrierName", carrierName);
								tempMap.put("carrierId", carrierId);
								tempMap.put("collectionType", collectionType);
								tempMap.put("trackingNo", trackingNo);
								tempMap.put("parcel", parcel);
								tempMap.put("totalPrice", totalPrice);
								tempMap.put("insurance", insurance);
								tempMap.put("signature", signature);
								tempMap.put("orderStatus", orderStatus);
								reportData.add(tempMap);
								
							}
						}else{
							tempMap = new HashMap<String, Object>();
							tempMap.put("orderDate", orderDate);
							tempMap.put("zapRef", zapRef);
							tempMap.put("order_id", order_id);
							tempMap.put("transactionId", transactionId);
							tempMap.put("paymentId", paymentId);
							tempMap.put("paymentStatus", paymentStatus);
							tempMap.put("name", name);
							tempMap.put("carrierName", carrierName);
							tempMap.put("carrierId", carrierId);
							tempMap.put("collectionType", collectionType);
							tempMap.put("trackingNo", trackingNo);
							tempMap.put("parcel", parcel);
							tempMap.put("totalPrice", totalPrice);
							tempMap.put("insurance", insurance);
							tempMap.put("signature", signature);
							tempMap.put("orderStatus", orderStatus);
							reportData.add(tempMap);
						}
					}
				}
			}
			
			query = GlobalVariables.carriersList;
			carriers = qrun.query(query, new MapListHandler());
			
			model.addObject("carriers", carriers);
			model.addObject("reportData",reportData);
			model.addObject("enroute", GlobalVariables.order_status_enroute);
			model.addObject("delivered", GlobalVariables.order_status_delivered);
			/*START ADDED NEW */
			model.addObject("card_left", GlobalVariables.order_status_card_left);
			model.addObject("check_address", GlobalVariables.order_status_check_address);
			model.addObject("refused", GlobalVariables.order_status_refused);
			model.addObject("rts", GlobalVariables.order_status_rts);
			/*START ADDED END */
			model.addObject("display", "adminReport");
			model.setViewName("admin/response");
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("errorClass",GlobalVariables.errorClass);
			model.addObject("ErrorMsg",GlobalVariables.error_Message);
			model.addObject("display", "adminReportError");
			model.setViewName("admin/response");
			LogUtility.logInfo("In Exception ::\n"+e.getMessage(), logger);
		}
		LogUtility.logInfo("AdminService :: adminReport() Method End", logger);
		return model;
	}
	public ModelAndView updateSignature(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("AdminService :: updateOrderDetail() Method Start", logger);
		ModelAndView model = new ModelAndView();
		String orderDate = "",zapRef = "",transactionId = "",paymentId = "",paymentStatus = "";
		String name = "",carrierName = "",carrierId = "",collectionType = "";
		String trackingNo = "",parcel = "",totalPrice = "",insurance="No",orderStatus = "",order_id,signature;
		String dropAgentId = "0";
		double parcelValue = 0.0;
		float insPrice = 0;
		int affectedRow = 0;
		
		try{
			
			signature  =  req.getParameter("signature")== null ? "" : req.getParameter("signature");
		    order_id    =  req.getParameter("order_id")== null ? "" : req.getParameter("order_id");
		    
		    if(signature != ""){
				query = GlobalVariables.updateSignatureQuery;
				affectedRow = qrun.update(query,signature,order_id);
			}

			LogUtility.logInfo("Update Order ::"+order_id+"  affectedRow ::"+affectedRow +" & signature ::"+signature, logger);
			// Creating Response 
			List<Map<String,Object>> carriers = new ArrayList<Map<String,Object>>();
			Map<String,Object> tempMap = new HashMap<String, Object>();
			
			query = GlobalVariables.retrieveSingleOrder;
			tempMap = qrun.query(query, new MapHandler(),GlobalVariables.order_status_enroute,GlobalVariables.order_status_delivered,GlobalVariables.order_status_ordered,order_id);

			if(tempMap.size() > 0 ){
				orderDate	  = tempMap.get("order_date")	  		!= null 	? tempMap.get("order_date").toString() : "";
				order_id 	  = tempMap.get("order_id") 	 		!= null 	? tempMap.get("order_id").toString()   : "";
				signature 	  = tempMap.get("signature") 		    != null 	? tempMap.get("signature").toString() : "";
				zapRef 	      = tempMap.get("user_order_id")  		!= null 	? tempMap.get("user_order_id").toString() : "";
				transactionId = tempMap.get("transactionid") 		!= null 	? tempMap.get("transactionid").toString() : "";
				paymentId 	  = tempMap.get("paypal_response_id")   != null 	? tempMap.get("paypal_response_id").toString() : "";
				paymentStatus = tempMap.get("status") 			    != null  	? tempMap.get("status").toString() : "";
				name 		  = tempMap.get("s_name") 				!= null 	? tempMap.get("s_name").toString() : "";
				carrierId 	  = tempMap.get("carrier_id") 			!= null 	? tempMap.get("carrier_id").toString() : "";
				collectionType= tempMap.get("collection_type") 		!= null 	? tempMap.get("collection_type").toString() : "";
				trackingNo 	  = tempMap.get("tracking_no") 			!= null 	? tempMap.get("tracking_no").toString() : "";
				totalPrice 	  = tempMap.get("total_price") 			!= null 	? tempMap.get("total_price").toString() : "";
				insPrice	  = Float.parseFloat(tempMap.get("insurance_cover") != null ? tempMap.get("insurance_cover").toString() : "0.0");
				//carrierName = tempMap.get("") != null ? tempMap.get("").toString() : "-";
				//parcel = tempMap.get("num_parcel") != null ? tempMap.get("num_parcel").toString() : "";
				
				if(insPrice > 50){
					insurance = "YES";
				}
				if(collectionType.equalsIgnoreCase("droptoagent")){
					dropAgentId = tempMap.get("dropoff_agent_id").toString();
					query = GlobalVariables.getAgentName;
					collectionType = (String) qrun.query(query, new ScalarHandler(),dropAgentId);
				}
				
				query = GlobalVariables.countParcelForOrder;
				parcelValue = (Double) qrun.query(query, new ScalarHandler(),order_id);
				parcel = String.valueOf(parcelValue);
				
				orderStatus = tempMap.get("order_status") != null ? tempMap.get("order_status").toString() : "";
				if(orderStatus.equalsIgnoreCase(GlobalVariables.order_status_ordered)){
					orderStatus = "To be Collected";
				}
			}
			
			query = GlobalVariables.getCarrierName;
			carrierName = (String) qrun.query(query, new ScalarHandler(),carrierId);
			
			query = GlobalVariables.carriersList;
			carriers = qrun.query(query, new MapListHandler());
			model.addObject("carriers", carriers);
	
			model.addObject("orderDate",orderDate);
			model.addObject("zapRef",zapRef);
			model.addObject("signature",signature);
			model.addObject("order_id",order_id);
			model.addObject("transactionId",transactionId);
			model.addObject("paymentId",paymentId);
			model.addObject("paymentStatus",paymentStatus);
			model.addObject("name",name);
			model.addObject("carrierName",carrierName);
			model.addObject("carrierId",carrierId);
			model.addObject("collectionType",collectionType);
			model.addObject("trackingNo",trackingNo);
			model.addObject("parcel",parcel);
			model.addObject("totalPrice",totalPrice);
			model.addObject("insurance",insurance);
			model.addObject("orderStatus",orderStatus);
			model.addObject("enroute", GlobalVariables.order_status_enroute);
			model.addObject("delivered", GlobalVariables.order_status_delivered);
			
			/*START ADDED NEW */
			model.addObject("card_left", GlobalVariables.order_status_card_left);
			model.addObject("check_address", GlobalVariables.order_status_check_address);
			model.addObject("refused", GlobalVariables.order_status_refused);
			model.addObject("rts", GlobalVariables.order_status_rts);
			/*START ADDED END */
			
			
			model.addObject("display", "updateOrderDetail"); 
			
		    model.setViewName("admin/response");
		
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("errorClass",GlobalVariables.errorClass);
			model.addObject("ErrorMsg",GlobalVariables.error_Message);
			model.addObject("display", "adminReportError");
			model.setViewName("admin/response");
			LogUtility.logInfo("In Exception ::updateSignature\n"+e.getMessage(), logger);
		}
		LogUtility.logInfo("AdminService :: updateSignature() Method End", logger);
		return model;
	}
	
	public ModelAndView updateOrderDetail(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("AdminService :: updateOrderDetail() Method Start", logger);
		ModelAndView model = new ModelAndView();
		String orderDate="",zapRef="",transactionId="",paymentId="",paymentStatus="";
		String name="",carrierName="",carrierId="",collectionType="";
		String trackingNo="",parcel="",totalPrice="",insurance="No",orderStatus="",order_id="";
		String dropAgentId = "0";
		double parcelValue = 0.0;
		float insPrice = 0;
		int affectedRow = 0;
		
		try{
			//Updating OrderDetail
			zapRef = req.getParameter("orderId");
			carrierId = req.getParameter("carrierId");
			trackingNo = req.getParameter("trackingNo");
			orderStatus = req.getParameter("status");
			String updateQuery = "";
			if(carrierId != ""){
				updateQuery += ", carrier_id ="+carrierId;
			}
			if(trackingNo != ""){
				updateQuery += ", tracking_no ='"+trackingNo+"' ";
			}
			if(orderStatus != ""){
				updateQuery += ", order_status ='"+orderStatus+"' ";
			}
			query = GlobalVariables.updateSingleOrder;
			query = query.replaceFirst(GlobalVariables.querySeparator,updateQuery);
			affectedRow = qrun.update(query,zapRef,zapRef);

			LogUtility.logInfo("Update Order ::"+zapRef+"  affectedRow ::"+affectedRow, logger);
			// Creating Response 
			List<Map<String,Object>> carriers = new ArrayList<Map<String,Object>>();
			Map<String,Object> tempMap = new HashMap<String, Object>();
			
			query = GlobalVariables.retrieveSingleOrder;
			tempMap = qrun.query(query, new MapHandler(),GlobalVariables.order_status_enroute,GlobalVariables.order_status_delivered,GlobalVariables.order_status_ordered,zapRef);

			if(tempMap.size() > 0 ){
				orderDate = tempMap.get("order_date") != null ? tempMap.get("order_date").toString() : "";
				order_id = tempMap.get("order_id") != null ? tempMap.get("order_id").toString() : "";
				zapRef = tempMap.get("user_order_id") != null ? tempMap.get("user_order_id").toString() : "";
				transactionId = tempMap.get("transactionid") != null ? tempMap.get("transactionid").toString() : "";
				paymentId = tempMap.get("paypal_response_id") != null ? tempMap.get("paypal_response_id").toString() : "";
				paymentStatus = tempMap.get("status") != null ? tempMap.get("status").toString() : "";
				name = tempMap.get("s_name") != null ? tempMap.get("s_name").toString() : "";
				//carrierName = tempMap.get("") != null ? tempMap.get("").toString() : "-";
				carrierId = tempMap.get("carrier_id") != null ? tempMap.get("carrier_id").toString() : "";
				collectionType = tempMap.get("collection_type") != null ? tempMap.get("collection_type").toString() : "";
				trackingNo = tempMap.get("tracking_no") != null ? tempMap.get("tracking_no").toString() : "";
				//parcel = tempMap.get("num_parcel") != null ? tempMap.get("num_parcel").toString() : "";
				totalPrice = tempMap.get("total_price") != null ? tempMap.get("total_price").toString() : "";
				insPrice = Float.parseFloat(tempMap.get("insurance_cover") != null ? tempMap.get("insurance_cover").toString() : "0.0");
				if(insPrice > 50){
					insurance = "YES";
				}
				if(collectionType.equalsIgnoreCase("droptoagent")){
					dropAgentId = tempMap.get("dropoff_agent_id").toString();
					query = GlobalVariables.getAgentName;
					collectionType = (String) qrun.query(query, new ScalarHandler(),dropAgentId);
				}
				
				query = GlobalVariables.countParcelForOrder;
				parcelValue = (Double) qrun.query(query, new ScalarHandler(),order_id);
				parcel = String.valueOf(parcelValue);
				
				orderStatus = tempMap.get("order_status") != null ? tempMap.get("order_status").toString() : "";
				if(orderStatus.equalsIgnoreCase(GlobalVariables.order_status_ordered)){
					orderStatus = "To be Collected";
				}
			}
			
			query = GlobalVariables.getCarrierName;
			carrierName = (String) qrun.query(query, new ScalarHandler(),carrierId);
			
			query = GlobalVariables.carriersList;
			carriers = qrun.query(query, new MapListHandler());
			model.addObject("carriers", carriers);
	
			model.addObject("orderDate",orderDate);
			model.addObject("zapRef",zapRef);
			model.addObject("order_id",order_id);
			model.addObject("transactionId",transactionId);
			model.addObject("paymentId",paymentId);
			model.addObject("paymentStatus",paymentStatus);
			model.addObject("name",name);
			model.addObject("carrierName",carrierName);
			model.addObject("carrierId",carrierId);
			model.addObject("collectionType",collectionType);
			model.addObject("trackingNo",trackingNo);
			model.addObject("parcel",parcel);
			model.addObject("totalPrice",totalPrice);
			model.addObject("insurance",insurance);
			model.addObject("orderStatus",orderStatus);
			
			model.addObject("enroute", GlobalVariables.order_status_enroute);
			model.addObject("delivered", GlobalVariables.order_status_delivered);
			
			/*START ADDED NEW */
			model.addObject("card_left", GlobalVariables.order_status_card_left);
			model.addObject("check_address", GlobalVariables.order_status_check_address);
			model.addObject("refused", GlobalVariables.order_status_refused);
			model.addObject("rts", GlobalVariables.order_status_rts);
			/*START ADDED END */
			
			model.addObject("display", "updateOrderDetail");
			model.setViewName("admin/response");
		}catch (Exception e){
			e.printStackTrace();
			model.addObject("errorClass",GlobalVariables.errorClass);
			model.addObject("ErrorMsg",GlobalVariables.error_Message);
			model.setViewName("admin/response");
			LogUtility.logInfo("In Exception ::\n"+e.getMessage(), logger);
		}
		LogUtility.logInfo("AdminService :: updateOrderDetail() Method End", logger);
		return model;
	}

	public ModelAndView getData(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("AdminService :: getData()  Method Start", logger);
		ModelAndView model = new ModelAndView();
		try{
			
			List<Map<String,Object>> carriers = new ArrayList<Map<String,Object>>();
			query = GlobalVariables.carriersList;
			carriers = qrun.query(query, new MapListHandler());
			model.addObject("carriers", carriers);
	
		}catch (Exception e){
			e.printStackTrace();
			model.addObject("errorClass",GlobalVariables.errorClass);
			model.addObject("ErrorMsg",GlobalVariables.error_Message);
			model.setViewName("admin/response");
			LogUtility.logInfo("In Exception ::\n"+e.getMessage(), logger);
		}
		LogUtility.logInfo("AdminService :: getData() Method End", logger);
		return model;
	}

	public ModelAndView viewFullOrderDetails(HttpServletRequest req,HttpServletResponse res) {
		LogUtility.logInfo("AdminService :: viewFullOrderDetails()  Method Start", logger);
		ModelAndView model = new ModelAndView();
		try{
			
			String order_id    = req.getParameter("order_id")  == null ?  "" : req.getParameter("order_id");
			
			List<Map<String,Object>> order_data = qrun.query(GlobalVariables.getFinalOrderInfoAdmin,new MapListHandler(),order_id);
			model.addObject("order_data",order_data);
			if(order_data != null && order_data.size() >0){
				if(order_data.get(0).get("collection_type").toString().equalsIgnoreCase("droptoagent")){
					
					String dropoff_agent_id = order_data.get(0).get("dropoff_agent_id").toString();
					List<Map<String,Object>> getAgentDetails = qrun.query(GlobalVariables.getAgentDetails,new MapListHandler(),dropoff_agent_id);
					model.addObject("getAgentDetails", getAgentDetails);
					
				}
			}
			
			
			
			List<Map<String,Object>> cartList = qrun.query(GlobalVariables.getFinalItemDetailsAdmin,new MapListHandler(),order_id);
			model.addObject("cartList",cartList);
			
			
			/* List<Map<String,Object>> finalOrderDetails = qrun.query(GlobalVariables.getFinalOrderInfoQuery,new MapListHandler(), order_data.get(0).get("order_id").toString(),user_key,GlobalVariables.order_status_ordered);
			*/
			
			
	
		}catch (Exception e){
			e.printStackTrace();
			model.addObject("errorClass",GlobalVariables.errorClass);
			model.addObject("ErrorMsg",GlobalVariables.error_Message);
			model.setViewName("admin/response");
			LogUtility.logInfo("In Exception ::\n"+e.getMessage(), logger);
		}
		LogUtility.logInfo("AdminService :: viewFullOrderDetails() Method End", logger);
		return model;
	}

}
