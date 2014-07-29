package zapmyparcel.control;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import zapmyparcel.service.paypay.PayPalServices;
import zapmyparcel.utility.common.GlobalVariables;
import zapmyparcel.utility.common.LogUtility;
import zapmyparcel.utility.db.DbUtils;

@Controller
public class PayPalController {
	
	Date date = new Date();
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String ErrorMsg="";
	Logger logger= Logger.getLogger(this.getClass().getName());
	GlobalVariables global;
	
	
	@RequestMapping("/payfororders")
	// method call when user checkout from the showorderdetail.html page (for paypal)
	public ModelAndView payForOrders(HttpServletRequest req, HttpServletResponse res) throws Exception{
	
		LogUtility.logInfo("PayPalController:payForOrders Start:::" + date, logger);
		ModelAndView model=new PayPalServices().payForOrders(req,res);
		System.out.println("payfororders "+model.getModelMap().get("option"));
		System.out.println("Model is::"+model);
		model.setViewName("order/response_msg");
		LogUtility.logInfo("Exit PayPalController:payForOrders End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/paymentconfirm")
	public ModelAndView paymentConfirm(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("PayPalController:paymentconfirm Start:::" + date, logger);
		ModelAndView model=new PayPalServices().paymentConfirm(req,res);
		LogUtility.logInfo("Exit PayPalController:paymentconfirm End :::" +date,logger);
		return model;
	}
	
	
	
	
}
