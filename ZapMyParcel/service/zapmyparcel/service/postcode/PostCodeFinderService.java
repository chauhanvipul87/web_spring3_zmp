package zapmyparcel.service.postcode;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import zapmyparcel.utility.common.GlobalVariables;

public class PostCodeFinderService {

	public static List<String> getAddressList(Hashtable[] arrtable)
	{
		List<String> addressList = new ArrayList<String>();
		for (int m = 0; m < arrtable.length; m++)
		{
			System.out.println(m);
			String addressString ="";
			for(Iterator n=arrtable[m].entrySet().iterator(); n.hasNext(); )
			{	
				Map.Entry e = (Map.Entry) n.next();
				if(e.getKey().toString().equalsIgnoreCase("Line1")){
					addressString = addressString+","+e.getValue().toString();	
				}
				
				if(e.getKey().toString().equalsIgnoreCase("PostTown")){
					addressString = addressString+","+e.getValue().toString();
				}
				if(e.getKey().toString().equalsIgnoreCase("County")){
					addressString = addressString+","+e.getValue().toString();
				}
				System.out.println("Keys=" + e.getKey() + " Value=" + e.getValue());
			}
			if(!addressString.equalsIgnoreCase("")){
				String tempStr =addressString.substring(0,addressString.lastIndexOf(","));
				String addressLine1 = addressString.substring(addressString.lastIndexOf(",")+1,addressString.length());
				addressList.add(addressLine1+tempStr);
			}
				System.out.println("************");
		}
		System.out.println(addressList);
		return addressList;
	}
	
	
	static public java.util.Hashtable[] PostcodeAnywhere_Interactive_RetrieveByPostcodeAndBuilding_v1_10(String Key, String Postcode, String Building, String UserName) throws Exception 
	{

	  String requestUrl = new String();
	  String key = new String();
	  String value = new String();

	  //Build the url
	  requestUrl = GlobalVariables.postCodeURL;
	  requestUrl += "&Key=" + java.net.URLEncoder.encode(Key);
	  requestUrl += "&Postcode=" + java.net.URLEncoder.encode(Postcode);
	  requestUrl += "&UserName=" + java.net.URLEncoder.encode(UserName);

	  //Get the data
	  java.net.URL url = new java.net.URL(requestUrl);
	  java.io.InputStream stream = url.openStream();
	  javax.xml.parsers.DocumentBuilder docBuilder = javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder();
	  org.w3c.dom.Document dataDoc = docBuilder.parse(stream);

	  //Get references to the schema and data
	  org.w3c.dom.NodeList schemaNodes = dataDoc.getElementsByTagName("Column");
	  org.w3c.dom.NodeList dataNotes = dataDoc.getElementsByTagName("Row");

	  //Check for an error
	  if (schemaNodes.getLength()==4 && schemaNodes.item(0).getAttributes().getNamedItem("Name").getNodeValue().equals("Error"))
	  {
	    throw new Exception(dataNotes.item(0).getAttributes().getNamedItem("Description").getNodeValue());
	  };

	  //Work though the items in the response
	  java.util.Hashtable[] results = new java.util.Hashtable[dataNotes.getLength()];
	  for (int rowCounter=0; rowCounter<dataNotes.getLength(); rowCounter++)
	  {
	    java.util.Hashtable rowData = new java.util.Hashtable();
	    for (int colCounter=0; colCounter<schemaNodes.getLength(); colCounter++)
	    {
	      key = (String)schemaNodes.item(colCounter).getAttributes().getNamedItem("Name").getNodeValue();
	      if(dataNotes.item(rowCounter).getAttributes().getNamedItem(key)==null)
	        {
	          value="";
	        }
	      else
	        {
	          value = (String)dataNotes.item(rowCounter).getAttributes().getNamedItem(key).getNodeValue();
	        };
	      rowData.put (key, value);
	    }
	    
	    results[rowCounter] = rowData;
	  }
	  //Return the results
	  return results;
	}
}
