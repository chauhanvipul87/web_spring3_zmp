package zapmyparcel.service.main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import zapmyparcel.utility.common.CommonDAO;
import zapmyparcel.utility.common.GlobalVariables;
import zapmyparcel.utility.common.LogUtility;
import zapmyparcel.utility.date.DateTimeFormater;
import zapmyparcel.utility.db.DbUtils;
import zapmyparcel.utility.email.Email;



public class OfferPriceService {

	Date date = new Date();
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String ErrorMsg="";
	Logger logger= Logger.getLogger(this.getClass().getName());
	GlobalVariables global;
	
	public ModelAndView getOffer(HttpServletRequest req, HttpServletResponse res) {
		LogUtility.logInfo("OfferPriceService:getOffer Start:::" + date, logger);
		ModelAndView model = new ModelAndView();
		try{
		
			List<Map<String,Object>> offerLst = qrun.query(global.alloffer_price, new MapListHandler());
			List<String> countryNamesLst = new ArrayList<String>();
			List<String> priceList		 = new ArrayList<String>();
			
			for(int i=0;i<offerLst.size();i++){
				Map <String,Object> m =(Map)offerLst.get(i);
				if(!countryNamesLst.contains(m.get("country_name"))){
					countryNamesLst.add(m.get("country_name").toString());
				}
			}
			
		//	System.out.println(" countryNamesLst "+countryNamesLst);
			int cnt =0;
			Map<String, Object> countryMap = new HashMap<String, Object>();
			for(int i=0;i<countryNamesLst.size();i++){
				Map<String, Object> priceMap = new HashMap<String, Object>();
				int counter =1;
				for(int j=cnt;j<(cnt+6);j++){
					Map <String,Object> m =(Map)offerLst.get(j);
				//	System.out.println(m.get("price"));
					switch (counter) {
					case 1:
						if(countryNamesLst.get(i).equalsIgnoreCase("UK - Mainland")){
							HttpSession session = req.getSession(true);
							session.setAttribute("countryName","UK - Mainland");
							session.setAttribute("OneKgPrice",CommonDAO.getConvertedValue(m.get("price").toString()));
						}
						priceMap.put("1",CommonDAO.getConvertedValue(m.get("price").toString()));
						break;
					case 2:
						priceMap.put("2",CommonDAO.getConvertedValue(m.get("price").toString()));
						break;
					case 3:
						priceMap.put("5",CommonDAO.getConvertedValue(m.get("price").toString()));
						break;
					case 4:
						priceMap.put("10",CommonDAO.getConvertedValue(m.get("price").toString()));
						break;
					case 5:
						priceMap.put("15",CommonDAO.getConvertedValue(m.get("price").toString()));
						break;
					case 6:
						priceMap.put("20",CommonDAO.getConvertedValue(m.get("price").toString()));
						break;	
					default:
						break;
					}
					counter ++;
				}
				countryMap.put(countryNamesLst.get(i), priceMap);
				//System.out.println("PriceMap ::"+priceMap);
				//System.out.println("out...");
				cnt =cnt +6;
			}
			
			System.out.println(" countryMap "+countryMap);
			model.addObject("countryMap"			,	countryMap);
			model.addObject("countryNamesLst"	,	countryNamesLst);
			
			LogUtility.logInfo("OfferPriceService:getOffer End:::" + date, logger);
			return model;	
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"OfferPriceService:getOffer End:::" +e.getMessage(), logger);
			return model;
		}
	}

	public ModelAndView send_contactMail(HttpServletRequest req,HttpServletResponse res) {
		LogUtility.logInfo("OfferPriceService:send_contactMail Start:::" + date, logger);
		ModelAndView model = new ModelAndView();
		try{
			String name       	 = req.getParameter("name")			    	 == null ?  "" : req.getParameter("name");    
			String phone     	 = req.getParameter("phone")		    	 == null ?  "" : req.getParameter("phone"); 
			String email  		 = req.getParameter("email")				 == null ?  "" : req.getParameter("email");  
			String message  	 = req.getParameter("message")				 == null ?  "" : req.getParameter("message");
			
			int affectedRows =qrun.update(GlobalVariables.insert_contactUs,name,phone,email,message,DateTimeFormater.format_datetime());
			if(affectedRows >0){
				
				 String [] recipient = GlobalVariables.recipients_ContactUs.split(",");
				 String body = GlobalVariables.mailBody_ContactUs;
				 String temp ="";
				
				 temp = body.substring(0, body.indexOf("$"));
				 temp = temp + name;
				 body = temp + body.substring(body.indexOf("$")+1); 
				 
				 temp = body.substring(0, body.indexOf("$"));
				 temp = temp + phone;
				 body = temp + body.substring(body.indexOf("$")+1); 
				 
				 temp = body.substring(0, body.indexOf("$"));
				 temp = temp + email;
				 body = temp + body.substring(body.indexOf("$")+1); 
				 
				 temp = body.substring(0, body.indexOf("$"));
				 temp = temp + message;
				 body = temp + body.substring(body.indexOf("$")+1); 
				 
				 temp = body.substring(0, body.indexOf("$"));
				 temp = temp + DateTimeFormater.display_datetime();
				 body = temp + body.substring(body.indexOf("$")+1); 
				
				 
				 System.out.println("body _str ::"+body);
				 Email.sendEmail(recipient, GlobalVariables.from_ContactUs,GlobalVariables.host,GlobalVariables.mail_server_properties, GlobalVariables.subjectLine_ContactUs,body,"html");
				 
				model.addObject("errorClass",GlobalVariables.errorClass);
				model.addObject("ErrorMsg",GlobalVariables.contactus_message);
				
			}else{
				model.addObject("errorClass",GlobalVariables.errorClass);
				model.addObject("ErrorMsg",GlobalVariables.error_Message);
			}
			
			LogUtility.logInfo("OfferPriceService:send_contactMail End:::" + date, logger);
			return model;	
		}catch(Exception e){
			e.printStackTrace();
			LogUtility.logInfo(date+"OfferPriceService:send_contactMail End:::" +e.getMessage(), logger);
			return model;
		}
	}

	public ModelAndView getCountryWisePriceList(HttpServletRequest req,	HttpServletResponse res) {
		LogUtility.logInfo("OfferPriceService:getCountryWisePriceList Start:::" + date, logger);
		ModelAndView model = new ModelAndView();
		String query ="";
		try{
			String countryName = req.getParameter("countryName") == null ? "UK" : req.getParameter("countryName");
			if(countryName.equalsIgnoreCase("UK")){
				query = global.alloffer_price +" AND c.country_name like ('"+countryName+"%')";
				
			}else{
				query = global.alloffer_price +" AND c.country_name = '"+countryName+"' ";
			}
			
			List<Map<String,Object>> offerLst = qrun.query(query, new MapListHandler());
			List<String> countryNamesLst = new ArrayList<String>();
			List<String> priceList		 = new ArrayList<String>();
			
			for(int i=0;i<offerLst.size();i++){
				Map <String,Object> m =(Map)offerLst.get(i);
				if(!countryNamesLst.contains(m.get("country_name"))){
					countryNamesLst.add(m.get("country_name").toString());
					System.out.println("country name is  ::"+m.get("country_name").toString());
					model.addObject("aboutCountryHTML",getCountryFileContent(countryName));
					
				}
			}
			
		//	System.out.println(" countryNamesLst "+countryNamesLst);
			int cnt =0;
			Map<String, Object> countryMap = new HashMap<String, Object>();
			for(int i=0;i<countryNamesLst.size();i++){
				Map<String, Object> priceMap = new HashMap<String, Object>();
				int counter =1;
				for(int j=cnt;j<(cnt+6);j++){
					Map <String,Object> m =(Map)offerLst.get(j);
				//	System.out.println(m.get("price"));
					switch (counter) {
					case 1:
						if(countryNamesLst.get(i).equalsIgnoreCase("UK - Mainland")){
							HttpSession session = req.getSession(true);
							session.setAttribute("countryName","UK - Mainland");
							session.setAttribute("OneKgPrice",CommonDAO.getConvertedValue(m.get("price").toString()));
						}
						priceMap.put("1",CommonDAO.getConvertedValue(m.get("price").toString()));
						break;
					case 2:
						priceMap.put("2",CommonDAO.getConvertedValue(m.get("price").toString()));
						break;
					case 3:
						priceMap.put("5",CommonDAO.getConvertedValue(m.get("price").toString()));
						break;
					case 4:
						priceMap.put("10",CommonDAO.getConvertedValue(m.get("price").toString()));
						break;
					case 5:
						priceMap.put("15",CommonDAO.getConvertedValue(m.get("price").toString()));
						break;
					case 6:
						priceMap.put("20",CommonDAO.getConvertedValue(m.get("price").toString()));
						break;	
					default:
						break;
					}
					counter ++;
				}
				countryMap.put(countryNamesLst.get(i), priceMap);
				//System.out.println("PriceMap ::"+priceMap);
				//System.out.println("out...");
				cnt =cnt +6;
			}
			
			System.out.println(" countryMap "+countryMap);
			model.addObject("countryMap"			,	countryMap);
			model.addObject("countryNamesLst"	,	countryNamesLst);
			
			
			
			LogUtility.logInfo("OfferPriceService:getCountryWisePriceList End:::" + date, logger);
			return model;	
	}catch(Exception e){
		e.printStackTrace();
		LogUtility.logInfo(date+"OfferPriceService:getCountryWisePriceList End:::" +e.getMessage(), logger);
		return model;
	}
  }
	
	
	public String getCountryFileContent(String countryName){
		LogUtility.logInfo("OfferPriceService:getCountryFileContent Start:::" + date, logger);
		StringBuffer contents = new StringBuffer("");
        BufferedReader reader = null;

        try {
        	URL defaultImage = OfferPriceService.class.getResource(countryName+".txt");
    		File file = new File(defaultImage.toURI());
    		
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            // repeat until all lines is read
            while ((text = reader.readLine()) != null) {
                contents.append(text)
                        .append(System.getProperty(
                                "line.separator"));
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        	LogUtility.logInfo(e.getMessage()+"OfferPriceService:getCountryFileContent Start:::" + date, logger);
        } catch (Exception e) {
            //e.printStackTrace();
        	LogUtility.logInfo(e.getMessage()+"OfferPriceService:getCountryFileContent Start:::" + date, logger);
        	
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                //e.printStackTrace();
                LogUtility.logInfo(e.getMessage()+"OfferPriceService:getCountryFileContent Start:::" + date, logger);
            }
        }

        // show file contents here
        //System.out.println(contents.toString());
        LogUtility.logInfo("OfferPriceService:getCountryFileContent End:::" + date, logger);
		return contents.toString();
    }
		
	public static void main(String[] args) {
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;

        try {
        	URL defaultImage = OfferPriceService.class.getResource("USA.txt");
    		File file = new File(defaultImage.toURI());
    		
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            // repeat until all lines is read
            while ((text = reader.readLine()) != null) {
                contents.append(text)
                        .append(System.getProperty(
                                "line.separator"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // show file contents here
        System.out.println(contents.toString());
    }
	
}
