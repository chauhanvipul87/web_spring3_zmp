package zapmyparcel.dao.menu;

import java.util.Date;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import zapmyparcel.utility.common.LogUtility;

public class CommonMenuDAO {
		
	Date date = new Date();
	//QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String ErrorMsg = "";
	Logger logger = Logger.getLogger(this.getClass().getName());
	String query = "";
	
	public ModelAndView volumeCalculator(HttpServletRequest req, HttpServletResponse res){
		ModelAndView model = new ModelAndView(); 
		LogUtility.logInfo("GoodsInDAO:volumeCalculator Start:::" + date, logger);
		try{
			String type = req.getParameter("type");
			type = req.getParameter("type")== null ? "volume": req.getParameter("type") ; 
			model.setViewName("menu/"+type);
			
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"GoodsInDAO:volumeCalculator End:::" +e.getMessage(), logger);
		}finally{
			LogUtility.logInfo("GoodsInDAO:volumeCalculator End:::" + date, logger);
		}
		
		return model;
	}
	
	
	
}
