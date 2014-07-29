package zapmyparcel.service.country;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.Logger;

import zapmyparcel.utility.common.GlobalVariables;
import zapmyparcel.utility.db.DbUtils;




public class Country {
	
	Date date = new Date();
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	String ErrorMsg="";
	Logger logger= Logger.getLogger(this.getClass().getName());
	GlobalVariables  global;
	
	public List<Map<String,Object>> getCountryList(){
		List<Map<String,Object>> country_lst = new ArrayList<Map<String,Object>>();
		try {
		String query =global.getcountry_qry;
		query = query.replaceFirst(global.querySeparator,global.ZEROSTR);
		query = query.replaceFirst(global.querySeparator,global.ZEROSTR);
		System.out.println(query);
		country_lst = qrun.query(query, new MapListHandler());
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return country_lst; 
	}
}
