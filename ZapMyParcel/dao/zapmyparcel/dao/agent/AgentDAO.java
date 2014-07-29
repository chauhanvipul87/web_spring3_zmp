package zapmyparcel.dao.agent;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;

import zapmyparcel.utility.common.GlobalVariables;
import zapmyparcel.utility.common.LogUtility;
import zapmyparcel.utility.date.DateTimeFormater;
import zapmyparcel.utility.db.DbUtils;



public class AgentDAO {

	Date date = new Date();
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String ErrorMsg="";
	Logger logger= Logger.getLogger(this.getClass().getName());
	GlobalVariables global;
	public String addAgent(HttpServletRequest req, HttpServletResponse res){
		LogUtility.logInfo("AgentDAO:addAgent Start:::" + date, logger);
		String returnMsg ="";
		try{
		
		String email      = req.getParameter("email")		== null ?  "" : req.getParameter("email");
		String telephone  = req.getParameter("telephone")	== null ?  "" : req.getParameter("telephone");
		String mobile 	  = req.getParameter("mobile")		== null ?  "" : req.getParameter("mobile");
		String hear  	  = req.getParameter("hear")		== null ?  "" : req.getParameter("hear");
		String password   = req.getParameter("password")	== null ?  "" : req.getParameter("password");
		
		String country1   = req.getParameter("country1")	== null ?  "" : req.getParameter("country1");
		String postcode1  = req.getParameter("postcode1")	== null ?  "" : req.getParameter("postcode1");
		String address1   = req.getParameter("address1")	== null ?  "" : req.getParameter("address1");
		String address2   = req.getParameter("address2")	== null ?  "" : req.getParameter("address2");
		String address3   = req.getParameter("address3")	== null ?  "" : req.getParameter("address3");
		
		String town  	  = req.getParameter("town")		== null ?  "" : req.getParameter("town");
		String county  	  = req.getParameter("county")		== null ?  "" : req.getParameter("county");
		String postcode2  = req.getParameter("postcode2")	== null ?  "" : req.getParameter("postcode2");
		String country2   = req.getParameter("country2")	== null ?  "" : req.getParameter("country2");
		
		
		String query = global.insert_agent;
		query = query.replaceFirst(global.querySeparator, email);
		query = query.replaceFirst(global.querySeparator, telephone);
		query = query.replaceFirst(global.querySeparator, mobile);
		query = query.replaceFirst(global.querySeparator, hear);
		query = query.replaceFirst(global.querySeparator, password);
		query = query.replaceFirst(global.querySeparator, new DateTimeFormater().format_datetime());
		
		int affectedRows = qrun.update(query);
		if(affectedRows>0){
			returnMsg ="<div id=\'emsg\' class=\'success\'><script type=\'text/javascript\'>showMessage(\'You are successfully registered.\');</script>";
		}else{
			returnMsg ="<div id=\'emsg\' class=\'error\'><script type=\'text/javascript\'>showMessage(\'Something went wrong while processing your request.\');</script>";
		}
			
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"AgentDAO:addAgent End:::" + e.getMessage(), logger);
			returnMsg ="<div id=\'emsg\' class=\'error\'><script type=\'text/javascript\'>showMessage(\'Something went wrong while processing your request.\');</script>";
		}
		
		LogUtility.logInfo("AgentDAO:addAgent End:::" + date, logger);
		return returnMsg;
		
	}
	
}
