package zapmyparcel.utility.common;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



public class LogUtility {
		

	public static void logInfo(String message,Logger logger){
		try{
			
		Properties prop = new Properties();
	 	InputStream configIS= LogUtility.class.getResourceAsStream("log4j.properties");
		prop.load(configIS);	
		System.out.println(message);		
		PropertyConfigurator.configure(prop);
		logger.info(message);
		
		
	/*	logger.debug(message);
		logger.info(message);
		logger.warn(message);
		logger.error(message);
		logger.fatal(message);*/
		/*SampleReport obj = new SampleReport();
		obj.generateReport();..*/
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String...a){
		Logger logger= Logger.getLogger(LogUtility.class);
		logInfo("hello",logger);
	}
	public static void logError(String message, Logger logger) {
		try{
//			System.out.println("in error: "+message);		
		Properties prop = new Properties();
	 	InputStream configIS= LogUtility.class.getResourceAsStream("log4j.properties");
		prop.load(configIS);	
		PropertyConfigurator.configure(prop);
		logger.error(message);
		
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
}
