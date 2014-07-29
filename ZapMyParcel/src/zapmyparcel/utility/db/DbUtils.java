package zapmyparcel.utility.db;


import javax.sql.DataSource;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import zapmyparcel.utility.common.InitialisedProperties;

public class DbUtils {

	   static DataSource dataSource;
	   
	   public static void setDataSource(DataSource datasource) {
	      DbUtils.dataSource = datasource;
	      System.out.println("set datasource "+DbUtils.dataSource);
	   }
	   
	   public static String initialiseDataSource(){
		   XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("dbconfig.xml"));
			dataSource = (DataSource) beanFactory.getBean("zmpDataSource");
			InitialisedProperties.initialiseErrors();
			InitialisedProperties.initialiseConfig();
			InitialisedProperties.initialiseQueries();
			InitialisedProperties.initialiseMailsParam();
			InitialisedProperties.initialisePostCodeFinderConfig();
			InitialisedProperties.initialisePayPayConfig();
		   return "success";
	   }

	   public static DataSource getDataSource(){
		   if(dataSource != null){
			   return dataSource; 
		   }else{
			   String res = initialiseDataSource();
			   System.out.println(dataSource);
			   if(res.equalsIgnoreCase("success")){
				   return dataSource;
			   }
		   }
		   
		   System.out.println("get datasource "+DbUtils.dataSource);
	      return dataSource;
	   }
	   public static void main(String args[]){
		   getDataSource();
	   }
}
