package zapmyparcel.control;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import zapmyparcel.service.admin.AdminService;
import zapmyparcel.utility.common.LogUtility;

@Controller
public class AdminController {
	Date date = new Date();
	Logger logger = Logger.getLogger(this.getClass());
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/adminlogin",method=RequestMethod.GET)
	public ModelAndView loginPage(HttpServletRequest req, HttpServletResponse res){
		LogUtility.logInfo("AdminController :: loginPage() Method Start", logger);
		ModelAndView model = new ModelAndView();
		Map<String, Object> userdetail = new HashMap<String, Object>();
		HttpSession session = req.getSession();
		int uSize = 0;
		try {
			userdetail = (Map<String, Object>) session.getAttribute("userdetail") ;
			uSize = userdetail.size();
		} catch (Exception e) {
			LogUtility.logInfo("Null Pointer Exception",logger);
		}
		if(uSize > 0){
			return adminhome(req, res);
		}else{
			model.setViewName("admin/login");
		}
		LogUtility.logInfo("AdminController :: loginPage() Method End", logger);
		return model;
	}
	
	@RequestMapping(value="/adminlogin",method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest req, HttpServletResponse res){
		LogUtility.logInfo("AdminController :: login() Method Start", logger);
		ModelAndView model = new ModelAndView();
		model = new AdminService().validateLogin(req,res);
		LogUtility.logInfo("AdminController :: login() Method End", logger);
		return model;
	}
	
	@RequestMapping(value="/adminlogout")
	public ModelAndView logout(HttpServletRequest req, HttpServletResponse res){
		LogUtility.logInfo("AdminController :: logout() Method Start", logger);
		ModelAndView model = new ModelAndView();
		HttpSession session = req.getSession();
		session.removeAttribute("userdetail");
		session.invalidate();
		session = null;
		model.setViewName("admin/login");
		LogUtility.logInfo("AdminController :: logout() Method End", logger);
		return model;
	}
	
	@RequestMapping(value="/adminhome")
	public ModelAndView adminhome(HttpServletRequest req, HttpServletResponse res){
		LogUtility.logInfo("AdminController :: home() Method Start", logger);
		ModelAndView model = new ModelAndView();
		model = new AdminService().getData(req, res);
		model.setViewName("admin/home");
		LogUtility.logInfo("AdminController :: home() Method End", logger);
		return model;
	}
	
	@RequestMapping(value="/updateOrderDetail")
	public ModelAndView updateOrderDetail(HttpServletRequest req, HttpServletResponse res){
		LogUtility.logInfo("AdminController :: updateOrderDetail() Method Start", logger);
		ModelAndView model = new ModelAndView();
		model = new AdminService().updateOrderDetail(req, res);
		LogUtility.logInfo("AdminController :: updateOrderDetail() Method End", logger);
		return model;
	}
	
	@RequestMapping(value="/updateSignature")
	public ModelAndView updateSignature(HttpServletRequest req, HttpServletResponse res){
		LogUtility.logInfo("AdminController :: updateSignature() Method Start", logger);
		ModelAndView model = new ModelAndView();
		model = new AdminService().updateSignature(req, res);
		LogUtility.logInfo("AdminController :: updateSignature() Method End", logger);
		return model;
	}
	
	
	
	
	@RequestMapping(value="/adminReport")
	public ModelAndView adminReport(HttpServletRequest req, HttpServletResponse res){
		LogUtility.logInfo("AdminController :: adminReport() Method Start", logger);
		ModelAndView model = new ModelAndView();
		model = new AdminService().adminReport(req, res);
		LogUtility.logInfo("AdminController :: adminReport() Method End", logger);
		return model;
	}
	
	@RequestMapping(value="/viewFullOrderDetails")
	public ModelAndView viewFullOrderDetails(HttpServletRequest req, HttpServletResponse res){
		LogUtility.logInfo("AdminController ::viewFullOrderDetails() Method Start", logger);
		ModelAndView model = new ModelAndView();
		model = new AdminService().viewFullOrderDetails(req, res);
		model.setViewName("admin/orderdetailspopup");
		LogUtility.logInfo("AdminController :: viewFullOrderDetails() Method End", logger);
		return model;
	}
}
