package zapmyparcel.control;




import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import zapmyparcel.service.user.LoginAuthenticationService;
import zapmyparcel.utility.common.LogUtility;

@Controller
public class LoginController {
	Date date = new Date();
	//QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String ErrorMsg="";
	Logger logger= Logger.getLogger(LoginController.class);
	@RequestMapping(value="/login", method = RequestMethod.POST)
		public ModelAndView login(HttpServletRequest req, HttpServletResponse res) throws Exception{
		System.out.println("in login control..");
		LogUtility.logInfo("Enter LoginController class :invoke login()" +date,logger);
	    ModelAndView model=new LoginAuthenticationService().login(req,res);
		LogUtility.logInfo("Exit LoginController class :exit login()" +date,logger);
		return model;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView Logout(HttpServletRequest req, HttpServletResponse res) throws Exception{
	System.out.println("Logout Controller Start..");
	LogUtility.logInfo("Enter LogoutController class :invoke login()" +date,logger);
    ModelAndView model=new LoginAuthenticationService().logout(req,res);
	LogUtility.logInfo("Exit LogoutController class :exit login()" +date,logger);
	return model;
}
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView Home(HttpServletRequest req,HttpServletResponse res)
	{
		System.out.println("Home Start..");
		ModelAndView model = new ModelAndView();
		model.setViewName("home/home");
		System.out.println("Home End..");
		return model;
	}	
}
