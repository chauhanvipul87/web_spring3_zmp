package zapmyparcel.control;

/**
 * @author vipul chauhan   
 * OrderController for handling all request for orders
 */

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import zapmyparcel.service.main.ManageCartService;
import zapmyparcel.service.main.OfferPriceService;
import zapmyparcel.service.main.OrderServices;
import zapmyparcel.service.main.ParcelSpecification;
import zapmyparcel.service.main.RequestQuoteService;
import zapmyparcel.utility.common.GlobalVariables;
import zapmyparcel.utility.common.LogUtility;
import zapmyparcel.utility.db.DbUtils;



@Controller
public class OrderController {

	Date date = new Date();
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String ErrorMsg="";
	Logger logger= Logger.getLogger(this.getClass().getName());
	GlobalVariables global;
	
	@RequestMapping("/removeAll")
	public ModelAndView removeAll(HttpServletRequest req, HttpServletResponse res) throws Exception{
		HttpSession session = req.getSession();	
		session.invalidate();
		ModelAndView model=new OfferPriceService().getOffer(req,res);
		model.setViewName("home/default");
		return model;
	}
	
	@RequestMapping("/buymore")
	// method call when click on buymore button 
	public ModelAndView buyMore(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("OrderController:buyMore Start:::" + date, logger);
		/* session maintain for buymore value */
		HttpSession session = req.getSession();	
		session.setAttribute("buymore","buymore");
		/* session end.. */
		ModelAndView model=new OfferPriceService().getOffer(req,res);
		model.setViewName("home/default");
		LogUtility.logInfo("Exit OrderController:buyMore End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/checkout")
	// method call when click on buymore button 
	public ModelAndView checkOutOrder(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("OrderController:checkOutOrder Start:::" + date, logger);
		ModelAndView model=new OrderServices().checkOutOrder(req,res);
		LogUtility.logInfo("Exit OrderController:checkOutOrder End :::" +date,logger);
		return model;
	}
	
	
	@RequestMapping("/parcel_specification")
	// display second screen for based on seletec offer or country from first screen
	public ModelAndView parcel_specification(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("OrderController:parcel_specification Start:::" + date, logger);
	    ModelAndView model=new ParcelSpecification().setParcelParameter(req,res);
	    model.setViewName("book_parcel/parcel_specification");
		LogUtility.logInfo("Exit OrderController:parcel_specification End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/request_quote")
	// when user enter weight more than 20 then send a request quote
	public void request_quote(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("OrderController:request_quote Start:::" + date, logger);
	    String returnMsg=new RequestQuoteService().addRequestQuote(req,res);
	    res.getWriter().write(returnMsg);
		LogUtility.logInfo("Exit OrderController:request_quote End :::" +date,logger);
	}
	
	
	@RequestMapping("/addTocart")
	// For Add to cart 
	public ModelAndView addToCart(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("OrderController:addTocart Start:::" + date, logger);
		ModelAndView model=new ManageCartService().addToCart(req,res);
		model.addObject("option","addToCart");;
		model.setViewName("book_parcel/cartresponse");
		LogUtility.logInfo("Exit OrderController:addTocart End :::" +date,logger);
		return model;
	}
	
	
	@RequestMapping("/showCart")
	// Used to show card On the page
	public ModelAndView showCart(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("OrderController:showCart Start:::" + date, logger);
		ModelAndView model=new ManageCartService().showCart(req,res);
		model.addObject("option","showCart");;
		model.setViewName("book_parcel/cartresponse");
		LogUtility.logInfo("Exit OrderController:showCart End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/removeItem_Cart")
	// Remove items from the cart.
	public ModelAndView removeItem_Cart(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("OrderController:showCart Start:::" + date, logger);
		ModelAndView model=new ManageCartService().removeItem_Cart(req,res);
		model.addObject("option","showCart");;
		model.setViewName("book_parcel/cartresponse");
		LogUtility.logInfo("Exit OrderController:showCart End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/editItem_Cart")
	//Edit cart items
	public ModelAndView editItem_Cart(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("OrderController:editItem_Cart Start:::" + date, logger);
		ModelAndView model=new ManageCartService().editItem_Cart(req,res);
		model.addObject("option","showCart");;
		model.setViewName("book_parcel/cartresponse");
		LogUtility.logInfo("Exit editItem_Cart End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/updateItem_Cart")
	// Update Cart Items
	public ModelAndView updateItem_Cart(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("OrderController:updateItem_Cart Start:::" + date, logger);
		ModelAndView model=new ManageCartService().updateItem_Cart(req,res);
		model.addObject("option","showCart");;
		model.setViewName("book_parcel/cartresponse");
		LogUtility.logInfo("Exit OrderController:updateItem_Cart End :::" +date,logger);
		return model;
	}
	
	
	@RequestMapping("/placeorder")
	// initial screen for the place order
	public ModelAndView placeOrder(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("OrderController:placeOrder Start:::" + date, logger);
		ModelAndView model=new OrderServices().placeOrder(req,res);
		LogUtility.logInfo("Exit OrderController:placeOrder End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/findaddress")
	//find address based on postcode entered by user
	public ModelAndView findAddress(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("OrderController:findAddress Start:::" + date, logger);
		ModelAndView model=new OrderServices().findaddress(req,res);
		model.addObject("option","loadaddress");
		model.setViewName("order/orderresponse");
		LogUtility.logInfo("Exit OrderController:findAddress End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/orderparcels")
	// Place Order Handle request for  (new order + More Orders) 
	public ModelAndView orderParcels(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("OrderController:orderParcels Start:::" + date, logger);
		ModelAndView model=new OrderServices().orderParcels(req,res);
		model.setViewName("order/orderresponse");
		LogUtility.logInfo("Exit OrderController:findAddress End :::" +date,logger);
		return model;
	}
	
	
	
	@RequestMapping("/userregistration")
	// user registration before place order
	public ModelAndView userRegistration(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("OrderController:userRegistration Start:::" + date, logger);
		ModelAndView model=new OrderServices().userRegistration(req,res);
		model.addObject("option","userRegistration");
		model.setViewName("order/orderresponse");
		LogUtility.logInfo("Exit OrderController:userRegistration End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/showorderdetails")
	// display order information before check out 
	public ModelAndView showOrderDetails(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("OrderController:showOrderDetails Start:::" + date, logger);
		ModelAndView model=new OrderServices().showOrderDetails(req,res);
		//model.setViewName("order/showorderdetails");
		LogUtility.logInfo("Exit OrderController:showOrderDetails End :::" +date,logger);
		return model;
	}
	
	
	
	@RequestMapping("/add_deliveryaddress")
	//  Add delivery address to db which is entered by user
	public ModelAndView add_DeliveryAddress(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("OrderController:add_DeliveryAddress Start:::" + date, logger);
		ModelAndView model=new OrderServices().addDeliveryAddress(req,res);
		model.addObject("option","load_delivery_address");
		model.setViewName("order/orderresponse");
		LogUtility.logInfo("Exit OrderController:add_DeliveryAddress End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/showdeliveryaddress_country")
	
	public ModelAndView showDeliveryAddress_country(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("OrderController:showDeliveryAddress_country Start:::" + date, logger);
		ModelAndView model=new OrderServices().showDeliveryAddress_country(req,res);
		model.addObject("option","showdeliveryaddress_country");
		model.setViewName("order/orderresponse");
		LogUtility.logInfo("Exit OrderController:add_DeliveryAddress End :::" +date,logger);
		return model;
	}
	
	
	
	
	@RequestMapping("/droptoagentdetails")
	// 	call when When user select drop to agent  	
	public ModelAndView dropToAgentDetails(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("OrderController:dropToAgentDetails Start:::" + date, logger);
		ModelAndView model=new OrderServices().dropToAgentDetails(req,res);
		model.addObject("option","dropToAgentDetails");
		model.setViewName("order/orderresponse");
		LogUtility.logInfo("Exit OrderController:dropToAgentDetails End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/track_order")
	// 	call when When user select drop to agent  	
	public ModelAndView track_order(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("OrderController:track_order Start:::" + date, logger);
		ModelAndView model=new OrderServices().track_order(req,res);
		model.addObject("option","track_order");
		model.setViewName("order/orderresponse");
		LogUtility.logInfo("Exit OrderController:track_order End :::" +date,logger);
		return model;
	}
	
	@RequestMapping("/getcartdetails")
	// 	call when When user select drop to agent  	
	public ModelAndView getCartDetails(HttpServletRequest req, HttpServletResponse res) throws Exception{
		LogUtility.logInfo("OrderController:getCartDetails Start:::" + date, logger);
		ModelAndView model=new ManageCartService().showCart(req,res);
		model.addObject("option","getcartdetails");
		model.setViewName("order/orderresponse");
		LogUtility.logInfo("Exit OrderController:getCartDetails End :::" +date,logger);
		return model;
	}
	
	
	
	
	
}
