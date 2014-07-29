package zapmyparcel.service.user;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import zapmyparcel.utility.common.GlobalVariables;
import zapmyparcel.utility.common.LogUtility;
import zapmyparcel.utility.db.DbUtils;

public class LoginAuthenticationService {
		Date date =new Date();
		Logger logger= Logger.getLogger(LoginAuthenticationService.class);
		QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	/* method used for a validating the user authentication...*/	
	public ModelAndView login(HttpServletRequest req,HttpServletResponse res)
	{
		LogUtility.logInfo("Enter class :invoke login() " +date,logger);
		try{
			   if(req.getParameter("username")!=null){
				String username=req.getParameter("username").trim();
				String password=req.getParameter("password").trim();
				List<String> returnMsgList=Validate(username,password,req,res);
				Iterator<String> it=returnMsgList.iterator();
				String returnMsg="";
				while(it.hasNext()){
					String strTemp=it.next().toString();
					if(strTemp.equalsIgnoreCase("true")){
						returnMsg="true";
						break;
					}else{
						returnMsg=returnMsg+"<br/>"+strTemp;
					}
				}
				if(!(returnMsg.equalsIgnoreCase("true"))){
				returnMsg=returnMsg+"<br/>";
				}
				if(returnMsg.equalsIgnoreCase("true")){
					HttpSession session=req.getSession();
					session.setAttribute("username",username);
					ModelAndView model=new ModelAndView();
					model.addObject("sesssion",session);
					
					String query ="select access_zones from user_login where username = ? and delete_flag = ?";
					Map	access_zones = (Map) qrun.query(query, new MapHandler(),username,GlobalVariables.ZEROINT);
					System.out.println("user access level :"+access_zones);
					session.setAttribute("access_zones",access_zones);
					model.setViewName("home/home");
					LogUtility.logInfo("Exit loginValidate class :end login() " +date,logger);
//					return new ModelAndView("redirect:home.html");
					return model;
				}else{
					ModelAndView model=new ModelAndView();
					model.addObject("ErrorMsg",returnMsg);
					model.setViewName("home/login");
					LogUtility.logInfo("Exit loginValidate class :end login() " +date,logger);
					return model; 
				}
			}else{
				ModelAndView model=new ModelAndView();
				model.addObject("ErrorMsg","Access Denied");
				model.setViewName("home/login");
				LogUtility.logInfo("Exit loginValidate class :end login() " +date,logger);
				return model; 
			}
		}catch(Exception e){
			ModelAndView model=new ModelAndView();
			model.addObject("ErrorMsg","Access Denied");
			model.setViewName("home/login");
			LogUtility.logInfo(date+"Exit loginValidate  class : end login()"+e.getMessage(),logger);
			e.printStackTrace();
			return model; 
		}
	}
	/*end of  method used for validating the user authentication.*/	
	
	
	private List<String> Validate(String username, String password,HttpServletRequest req,HttpServletResponse res) {
		LogUtility.logInfo("Enter loginValidate class :invoke Validate() " +date,logger);
		String ErrorMsg="";
		List<String> errorlst=new ArrayList();
		if(username.equalsIgnoreCase("") || username.equalsIgnoreCase("null") ){
			ErrorMsg="Enter your Username.";
			errorlst.add(ErrorMsg);
		}
		if(password.equalsIgnoreCase("") || password.equalsIgnoreCase("null")){
			ErrorMsg="Enter your Password.";
			errorlst.add(ErrorMsg);
		}
		if(errorlst.size()==0){
			try {
			   QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
//			   System.out.println("getData sourse:"+qrun.getDataSource());
			   
//			   String newpassword= MD5Encryption.getMD5(password);  //*4ACFE3202A5FF5CF467898FC58AAB1D615029441
			   String query = "SELECT * from user_login where username= ? and password = password(?) and delete_flag = ?";
			   Map	m = (Map) qrun.query(query, new MapHandler(),username,password,GlobalVariables.ZEROINT);
			   System.out.println("query :: ::"+query);
			   if(m ==null){
				    ErrorMsg="The username or password you entered is incorrect.";
	    			errorlst.add(ErrorMsg);
				}else{
					HttpSession session = req.getSession();
					session.setAttribute("userid", m.get("userid"));
					errorlst.add("true");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ErrorMsg="Connection Lost.";
				LogUtility.logError(date+"Exit loginValidate class :end Validate()"+e.getMessage(),logger);
    			errorlst.add(ErrorMsg);
			}
		}
		LogUtility.logInfo("Exit loginValidate class :end Validate() " +date,logger);
		return errorlst;
	}
	
	/* method used for a validating the user authentication...*/	
	public ModelAndView logout(HttpServletRequest req,HttpServletResponse res)
	{
		LogUtility.logInfo("Enter class :invoke login() " +date,logger);
		try{
			HttpSession session = req.getSession();
			ModelAndView model=new ModelAndView();
			System.out.println("in .... logout....");
			if(session.getAttribute("username")!=null){
				session.removeAttribute("username"); // remove username from session 
				session.removeAttribute("access_zones"); // for handling menu.
				session.removeAttribute("userid");  //user id
				session.invalidate();
			}
			session = null;
			model.clear();
			model.setViewName("home/login");
			LogUtility.logInfo("Exit logout class :end login() " +date,logger);
			return model;
		}catch(Exception e){
			ModelAndView model=new ModelAndView();
			model.addObject("ErrorMsg","Session Problem");
			model.setViewName("home/login");
			LogUtility.logInfo(date+"Exit logout  class : end logout()"+e.getMessage(),logger);
			e.printStackTrace();
			return model; 
		}
	}
	

	
}
