package zapmyparcel.service.main;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;

import zapmyparcel.utility.common.GlobalVariables;
import zapmyparcel.utility.common.LogUtility;
import zapmyparcel.utility.date.DateTimeFormater;
import zapmyparcel.utility.db.DbUtils;
import zapmyparcel.utility.email.Email;




public class RequestQuoteService {
	Date date = new Date();
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String ErrorMsg="";
	Logger logger= Logger.getLogger(this.getClass().getName());
	GlobalVariables global;
	public String addRequestQuote(HttpServletRequest req,HttpServletResponse res) {
		LogUtility.logInfo("RequestQuoteService:addRequestQuote Start:::" + date, logger);
		String returnMsg= "";
		try{
		
			String quote_name       		 = req.getParameter("quote_name")			     == null ?  "" : req.getParameter("quote_name");
			String quote_email     			 = req.getParameter("quote_email")		    	 == null ?  "" : req.getParameter("quote_email");
			String quote_contant_no  		 = req.getParameter("quote_contant_no")			 == null ?  "" : req.getParameter("quote_contant_no");
			String quote_destination_country = req.getParameter("quote_destination_country") == null ?  "" : req.getParameter("quote_destination_country");
			String quote_comments			 = req.getParameter("quote_comments")			 == null ?  "" : req.getParameter("quote_comments");

			String query =global.insert_quote_details;
			query = query.replaceFirst(global.querySeparator,quote_name);
			query = query.replaceFirst(global.querySeparator,quote_email);
			query = query.replaceFirst(global.querySeparator,quote_contant_no);
			query = query.replaceFirst(global.querySeparator,quote_destination_country);
			query = query.replaceFirst(global.querySeparator,quote_comments);
			query = query.replaceFirst(global.querySeparator,new DateTimeFormater().format_datetime());
			query = query.replaceFirst(global.querySeparator,new DateTimeFormater().format_datetime());
			
			int affectedRows= qrun.update(query);
			System.out.println("affectedrow :"+affectedRows);
			if(affectedRows > 0){
				 returnMsg =GlobalVariables.success_requestQuote;

				 String str=  GlobalVariables.recipients_moreWeight;
				 String [] recipients = GlobalVariables.recipients_moreWeight.split(",");
				 String body =GlobalVariables.mailBody_moreWeight;
				 body = body.replaceFirst("#",quote_name);
				 body = body.replaceFirst("#",quote_email);
				 body = body.replaceFirst("#",quote_contant_no);
				 new Email().sendEmail(recipients, GlobalVariables.from_moreWeight,"mail.cdl-it.com","mail.smtp.host", GlobalVariables.subjectLine_moreWeight,body,"text");
				 
			}else{
				returnMsg = GlobalVariables.error_requestQuote;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"RequestQuoteService:addRequestQuote End:::" + e.getMessage(), logger);
			returnMsg = GlobalVariables.error_requestQuote;
		}
		
		LogUtility.logInfo("RequestQuoteService:addRequestQuote End:::" + date, logger);
		return returnMsg;
	}
	
	
	
	
	
}
