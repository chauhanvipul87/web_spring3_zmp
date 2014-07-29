package zapmyparcel.utility.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MD5Encryption {

	

	
	  public static String getMD5(String input) {
	        byte[] source;
	        try {
	            //Get byte according by specified coding...
	            source = input.getBytes("UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            source = input.getBytes();
	        }
	        String result = null;
	        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7',
	                '8', '9', 'a', '*', '#', 'd', 'e', 'f'};
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.update(source);
	            //The result should be one 128 integer
	            byte temp[] = md.digest();
	            char str[] = new char[3 * 2];
	            int k = 0;
	            for (int i = 0; i < 3; i++) {
	                byte byte0 = temp[i];
	                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	                str[k++] = hexDigits[byte0 & 0xf];
	            }
	            result = new String(str);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return result;
	    }
	  public static void main(String arg[]){
		  
		  System.out.println("admin pass::"+getMD5("admin"));
	  }
	  
}
