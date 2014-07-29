package zapmyparcel.utility.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	 public static void sendEmail(String[] recipients,String from,String host,String mail_server_properties,String subjectLine,String mailBody,String opt){
		   Properties properties = System.getProperties();
		   properties.setProperty(mail_server_properties, host);
		   Session session = Session.getDefaultInstance(properties);
		   try{
		         MimeMessage message = new MimeMessage(session);
		         message.setFrom(new InternetAddress(from));
		         MimeMessage msg = new MimeMessage(session);
		             msg.setFrom(new InternetAddress(from));
		             InternetAddress[] addressTo = new InternetAddress[recipients.length];
		             for (int i = 0; i < recipients.length; i++)
		             {
		                 addressTo[i] = new InternetAddress(recipients[i]);
		             }
		         message.addRecipients(Message.RecipientType.TO,addressTo);
		         message.setSubject(subjectLine);
		         
		         if(opt.equalsIgnoreCase("html")){
		        	// Send the actual HTML message, as big as you like
			         message.setContent(mailBody, "text/html" );
		         }else{
		        	message.setText(mailBody);
		         }
		    
		         // Send message
		         Transport.send(message);
		         System.out.println("Sent message successfully....");
		      }catch (Exception mex) {
		         mex.printStackTrace();
		      }
	   }
	   
}
