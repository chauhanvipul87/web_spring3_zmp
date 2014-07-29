package zapmyparcel.control;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import zapmyparcel.dao.agent.AgentDAO;
import zapmyparcel.service.country.Country;
import zapmyparcel.utility.common.LogUtility;
import zapmyparcel.utility.db.DbUtils;


@Controller
public class AgentController {
	
	Date date = new Date();
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String ErrorMsg="";
	Logger logger= Logger.getLogger(this.getClass().getName());
	
	
	@RequestMapping("/agent_register")
	/* for showing register page for zmp agent*/
	public ModelAndView show_agentRegisterForm(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("AgentController:show_agentRegisterForm Start:::" + date, logger);
	    ModelAndView model=new ModelAndView();
	    model.addObject("countryLst", new Country().getCountryList());
	    model.setViewName("agent/agent_register");
		LogUtility.logInfo("Exit AgentController:show_agentRegisterForm End :::" +date,logger);
		return model;
	}
	
	
	@RequestMapping("/addAgent")
	/* for add  agent in db */
	public void addAgent(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("AgentController:addAgent Start:::" + date, logger);
		System.out.println("in add agent...");
		AgentDAO adao = new AgentDAO();
		String returnMsg = adao.addAgent(req, res);
		res.getWriter().write(returnMsg);
		LogUtility.logInfo("Exit AgentController:addAgent End :::" +date,logger);
	}
	
	
	
	

}
