package zapmyparcel.service.main;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import zapmyparcel.utility.common.GlobalVariables;
import zapmyparcel.utility.common.LogUtility;
import zapmyparcel.utility.db.DbUtils;

public class ParcelSpecification {

	
	Date date = new Date();
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String ErrorMsg="";
	Logger logger= Logger.getLogger(this.getClass().getName());
	GlobalVariables global;
	
	public ModelAndView setParcelParameter(HttpServletRequest req,HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		LogUtility.logInfo("ParcelSpecification:setParcelParameter Start:::" + date, logger);
		try{
			
				String frm_price      = req.getParameter("frm_price")		== null ?  "" : req.getParameter("frm_price");
				String frm_weight  	  = req.getParameter("frm_weight")		== null ?  "" : req.getParameter("frm_weight");
				String frm_country 	  = req.getParameter("frm_country")		== null ?  "" : req.getParameter("frm_country");
				
				String query =global.getCountryContent;
				query = query.replaceFirst(global.querySeparator,frm_country);
				String country_description = (String) qrun.query(query, new ScalarHandler());
				System.out.println("country_description" +country_description);
				
				String  query_country_id = global.getselectedcountry;
				
				query_country_id = query_country_id.replaceFirst(global.querySeparator,frm_country);
				query_country_id = query_country_id.replaceFirst(global.querySeparator,global.ZEROSTR);
				query_country_id = query_country_id.replaceFirst(global.querySeparator,global.ZEROSTR);
				System.out.println("query_country_id::"+query_country_id);
				
				Integer cntry_id = (Integer) qrun.query(query_country_id, new ScalarHandler());
				System.out.println("counry _id "+cntry_id);
//				
				model.addObject("country_id"	,cntry_id);
				model.addObject("country_description"	,country_description);
				model.addObject("param_price"	,frm_price);
				model.addObject("param_weight"	,frm_weight);
				model.addObject("param_country"	,frm_country);
			
			
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"ParcelSpecification:setParcelParameter End:::" + e.getMessage(), logger);
		}
		
		LogUtility.logInfo("ParcelSpecification:setParcelParameter End:::" + date, logger);
		return model;
		
	}

}
