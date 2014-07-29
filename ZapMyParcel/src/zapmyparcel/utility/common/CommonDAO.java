package zapmyparcel.utility.common;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;

import zapmyparcel.utility.common.LogUtility;
import zapmyparcel.utility.db.DbUtils;

public class CommonDAO {
	Date date = new Date();
	Logger logger= Logger.getLogger(CommonDAO.class);
	String query="",ErrorMsg="";
	QueryRunner qrun = new QueryRunner(DbUtils.getDataSource());
	List commonList=new ArrayList();
	
		
	public List showFields(String tablenames,String fieldnames,String conditions) {
		try {
			LogUtility.logInfo("CommonDAO:showFields Start:::"+date,logger);
			String fields="",tables="",criteria="";
			if(tablenames.equals("") || tablenames==null )
			{
				commonList.add("Error : Table Name Not Provided");
				System.out.println("Error : Table Name Not Provided");
			    LogUtility.logInfo("CommonDAO:showFields End:::"+date,logger);
				return commonList;
			}else
			{
				tables=tablenames;
			}
			
			if(fieldnames.equals("") || fieldnames==null )
			{
				fields="*";
			}else
			{
				fields=fieldnames;
			}
			System.out.println("Tables : "+tables);
			System.out.println("Fields : "+fields);
			System.out.println("Crieteria : "+conditions);
			if(conditions.equals("") || conditions==null )
			{
				criteria="";
				query="select "+fields+" from "+tables+"";
			}else
			{
				criteria=conditions;
				query="select "+fields+" from "+tables+" where "+criteria+"";
			}
	 		System.out.println("Query : "+query);
			commonList = qrun.query(query,new MapListHandler());
			System.out.println("CommonList Size : "+commonList.size());
			LogUtility.logInfo("CommonDAO:showFields End:::"+date,logger);
			return commonList;	
		} catch (Exception e) {
			commonList.add("Error : "+e.getMessage());
		    LogUtility.logInfo("CommonDAO:showFields End:::"+e.getMessage() +date,logger);
			e.printStackTrace();
			return commonList;
		}
	}
	
	public synchronized int getautoGenerateID(String tablename,String fieldname) {
		try {
			LogUtility.logInfo("CommonDAO:autoGenerateID Start:::"+date,logger);
			String field="",table="";
			int returnresposne=0;
			table=tablename;
			field=fieldname;
			System.out.println("Table : "+table);
			System.out.println("Field : "+field);
								
			query="select nextnum from nextid where tablename = '"+table+"' and fieldname = '"+field+"'";
	 		System.out.println("Query : "+query);
			returnresposne = (Integer) qrun.query(query,new ScalarHandler());
			System.out.println("Return Resposne getID: "+returnresposne);
			//System.out.println("CommonList Size : "+commonList.size());
			LogUtility.logInfo("CommonDAO:autoGenerateID End:::"+date,logger);
			return returnresposne;	
		} catch (Exception e) {
			commonList.add("Error : "+e.getMessage());
		    LogUtility.logInfo("CommonDAO:autoGenerateID End:::"+e.getMessage() +date,logger);
			e.printStackTrace();
			return 0;
		}
	}
	
	public synchronized int  setautoGenerateID(String tablename,String fieldname,int nextautoid) {
		try {
			LogUtility.logInfo("CommonDAO:setautoGenerateId Start:::"+date,logger);
			String field="",table="",returnresposne="";
			long nextautonum=0;
			table=tablename;
			field=fieldname;
			nextautonum=nextautoid;
			System.out.println("Table : "+table);
			System.out.println("Field : "+field);
				
			query="UPDATE nextid SET nextnum = "+nextautonum+" WHERE tablename = '"+table+"' and fieldname = '"+field+"'";
			System.out.println("Query : "+query);
			int affecedrow = qrun.update(query);
			System.out.println("Affecedrow  " + affecedrow);
			
			if (affecedrow == 1) 
			{
				 LogUtility.logInfo("CommonDAO:setautoGenerateId End:::" +date,logger);
				return 1;
			}
			else
			{
				 LogUtility.logInfo("CommonDAO:setautoGenerateId End:::"+date,logger);
				return 0;
			}
		} catch (Exception e) {
			//commonList.add("Error : "+e.getMessage());
		    LogUtility.logInfo("CommonDAO:setautoGenerateId End:::"+e.getMessage() +date,logger);
			e.printStackTrace();
			return 0;
		}
	}
	
	public static String getConvertedValue(String val) {
		try{
			String str =String.valueOf(val);
			if(str.contains(".")){
				String sub  = str.substring(str.indexOf(".")+1,str.length());
				if(sub.length()==1){
					str =str +"0";
					return str;
				}else{
					if(sub.length()>2){
						Float vat1 = Float.parseFloat(str+"");
						double convertedValue =(double) Math.round(vat1*100)/100;
						return convertedValue+"";
					}else{
						return str;
					}
				}
			 }
			  return val;
			}catch(Exception e){
				e.printStackTrace();
			  return val;
			}
	}
	
	private static Random random = new Random((new Date()).getTime());

    public static String generateRandomString(int length) {
      char[] values = {'A','B','C','D','E','F','G','H','I','J',
               'K','L','M','N','O','P','Q','R','S','T',
               'U','V','W','X','Y','Z'};

      String out = "";

      for (int i=0;i<length;i++) {
          int idx=random.nextInt(values.length);
        out += values[idx];
      }

      return out;
    }
    
    
	
	public static void main(String args[])
	{
		System.out.println("Common Main Start...");
		//CommonDAO cm = new CommonDAO();
	//	List resultlist = cm.showFields("warehouse_master", "warehouse_id,warehouse_code", "warehouse_code='SDCP'");
		//System.out.println("Result Size : "+resultlist.size());
		//long returnstr = cm.getautoGenerateID("preorder_placement", "preorder_id");
		//System.out.println("Return Str : "+returnstr);
		//returnstr = cm.setautoGenerateID("preorder_placement", "preorder_id",21);
		//System.out.println("Return Str : "+returnstr);
		System.out.println("Common Main End...");
	}
}
