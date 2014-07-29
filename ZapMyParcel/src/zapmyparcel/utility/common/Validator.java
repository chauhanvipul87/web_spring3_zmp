package zapmyparcel.utility.common;

import java.util.ArrayList;
import java.util.List;

public class Validator {
	
	public static boolean validateInteger(String value){
		try{
			int r = Integer.parseInt(value);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	
	public static boolean validateFloat(String value){
		try {
			float f = Float.parseFloat(value);
			System.out.println(f);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static int checkSize(){
		List list = new ArrayList();
		return list.size();
	}
	
	public static void main(String[] rg){
		System.out.println(validateFloat("9.0.0"));
	}

}
