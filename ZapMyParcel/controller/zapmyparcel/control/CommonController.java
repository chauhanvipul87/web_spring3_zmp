package zapmyparcel.control;

import java.util.Date;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import zapmyparcel.dao.menu.CommonMenuDAO;
import zapmyparcel.service.main.OfferPriceService;
import zapmyparcel.service.main.ParcelSpecification;
import zapmyparcel.utility.common.LogUtility;

@Controller
public class CommonController {

	Date date = new Date();
	//QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String ErrorMsg="";
	Logger logger= Logger.getLogger(this.getClass().getName());
	
	@RequestMapping("/removeall")
	public ModelAndView removeall(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();	
		session.removeAttribute("buymore");
		ModelAndView model=new OfferPriceService().getOffer(req,res);
		model.setViewName("home/default");
		LogUtility.logInfo("Exit CommonController:info_dropOffAgent End :::" +date,logger);
		return model;
	}
	
	
	@RequestMapping("/send_contactmail")
	public ModelAndView send_contactMail(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("CommonController:send_contactMail Start:::" + date, logger);
		ModelAndView model=new OfferPriceService().send_contactMail(req,res);
		model.setViewName("order/orderresponse");
		LogUtility.logInfo("Exit CommonController:send_contactMail End :::" +date,logger);
		return model;
	}
	
	
	
	
	@RequestMapping("/index")
	// For display index page (intial page) 
	public ModelAndView welcome(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("CommonController:welcome Start:::" + date, logger);
	    ModelAndView model=new OfferPriceService().getOffer(req,res);
	    model.setViewName("home/default");
		LogUtility.logInfo("Exit CommonController:welcome End :::" +date,logger);
		return model;
	}
	

	@RequestMapping("/info_dropoffagent")
	public ModelAndView info_dropOffAgent(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("CommonController:info_dropOffAgent Start:::" + date, logger);
		 ModelAndView model=new ModelAndView();
	    model.setViewName("order/info_dropoffagent");
		LogUtility.logInfo("Exit CommonController:info_dropOffAgent End :::" +date,logger);
		return model;
	}
	
	
	/* loaded  menu items start */ 
	
	@RequestMapping("/faq")
	public ModelAndView faq(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("CommonController:faq Start:::" + date, logger);
	    ModelAndView model=new ModelAndView();
	    model.setViewName("menu/faq");
		LogUtility.logInfo("Exit CommonController:faq End :::" +date,logger);
		return model;
	}
	@RequestMapping("/our_partners")
	public ModelAndView our_partners(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("CommonController:our_partners Start:::" + date, logger);
	    ModelAndView model=new ModelAndView();
	    model.setViewName("menu/our_partners");
		LogUtility.logInfo("Exit CommonController:our_partners End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/contact_us")
	public ModelAndView contact_us(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("CommonController:contact_us Start:::" + date, logger);
	    ModelAndView model=new ModelAndView();
	    model.setViewName("menu/contact_us");
		LogUtility.logInfo("Exit CommonController:contact_us End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/privacy_policy")
	public ModelAndView privacy_policy(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("CommonController:privacy_policy Start:::" + date, logger);
	    ModelAndView model=new ModelAndView();
	    model.setViewName("menu/privacy_policy");
		LogUtility.logInfo("Exit CommonController:privacy_policy End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/prohibited_items")
	public ModelAndView prohibited_items(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("CommonController:prohibited_items Start:::" + date, logger);
	    ModelAndView model=new ModelAndView();
	    model.setViewName("menu/prohibited_items");
		LogUtility.logInfo("Exit CommonController:prohibited_items End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/about")
	public ModelAndView about(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("CommonController:about Start:::" + date, logger);
	    ModelAndView model=new ModelAndView();
	    model.setViewName("menu/about");
		LogUtility.logInfo("Exit CommonController:about End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/import_parcel")
	public ModelAndView import_parcel(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("CommonController:import_parcel Start:::" + date, logger);
	    ModelAndView model=new ModelAndView();
	    model.setViewName("menu/import_parcel");
		LogUtility.logInfo("Exit CommonController:import_parcel End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("CommonController:register Start:::" + date, logger);
	    ModelAndView model=new ModelAndView();
	    model.setViewName("menu/register");
		LogUtility.logInfo("Exit CommonController:register End :::" +date,logger);
		return model;
	}
	
	
	@RequestMapping("/how_to_parcel")
	public ModelAndView how_to_parcel(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("CommonController:how_to_parcel Start:::" + date, logger);
	    ModelAndView model=new ModelAndView();
	    model.setViewName("menu/how_to_parcel");
		LogUtility.logInfo("Exit CommonController:how_to_parcel End :::" +date,logger);
		return model;
	}
	
	
	@RequestMapping("/volume")
	public ModelAndView volume(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("CommonController:volume Start:::" + date, logger);
		ModelAndView model=new ModelAndView();
		model.setViewName("menu/volume");
		LogUtility.logInfo("Exit CommonController:volume End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/InternationalVolume")
	public ModelAndView InternationalVolume(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("CommonController:InternationalVolume Start:::" + date, logger);
		ModelAndView model=new ModelAndView();
		model.setViewName("menu/InternationalVolume");
		LogUtility.logInfo("Exit CommonController:InternationalVolume End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/UKVolume")
	public ModelAndView UKVolume(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("CommonController:UKVolume Start:::" + date, logger);
		ModelAndView model=new ModelAndView();
		model.setViewName("menu/UKVolume");
		LogUtility.logInfo("Exit CommonController:UKVolume End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/terms_condition")
	public ModelAndView termsAndCondition(HttpServletRequest req, HttpServletResponse res){
		LogUtility.logInfo("CommonController:termsAndCondition Start:::" + date, logger);
		ModelAndView model=new ModelAndView();
		model.setViewName("menu/terms_condition");
		LogUtility.logInfo("Exit CommonController:termsAndCondition End :::" +date,logger);
		return model;
	}
	
	
	@RequestMapping("/getCountryWisePriceList")
	public ModelAndView getCountryWisePriceList(HttpServletRequest req, HttpServletResponse res){
		LogUtility.logInfo("CommonController:getCountryWisePriceList Start:::" + date, logger);
		ModelAndView model=new OfferPriceService().getCountryWisePriceList(req,res);
		model.addObject("option","getCountryWisePriceList");
		model.setViewName("home/default");
		LogUtility.logInfo("Exit CommonController:getCountryWisePriceList End :::" +date,logger);
		return model;
	}
	
	
	/* loaded  menu items end */
	
	
}
