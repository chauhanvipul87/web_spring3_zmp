package zapmyparcel.utility.common;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;

public class MailManagement
{

  private static final String SMTP_HOST_NAME = "mail.cdlbackoffice.com";
  private static final String SMTP_AUTH_USER = "jayesh@cdlbackoffice.com";
  private static final String SMTP_AUTH_PWD  = "jayesh01203";

  private static final String emailMsgTxt      = "Test Mail From IFS Demo System With Attachment";
  private static final String emailSubjectTxt  = "IFS Test Mail with Attachment";
  private static final String emailFromAddress = "jayesh@cdlbackoffice.com";

  // Add List of Email address to who email needs to be sent to
  private static final String[] emailList = {"jayeshpatel6483@gmail.com"};

  public void SendMail(String from, String recipients[ ], String subject, String messagebodytext , String filepath,String attachedfilename) throws MessagingException
  {
    boolean debug = false;

    //Set the host smtp address
    Properties props = new Properties();
    props.put("mail.smtp.host", SMTP_HOST_NAME);
    props.put("mail.smtp.auth", "true");

    Authenticator auth = new SMTPAuthenticator();
    Session session = Session.getDefaultInstance(props, auth);
    session.setDebug(debug);
    //Transport transport = session.getTransport();
    // create a message
    Message message = new MimeMessage(session);
    // set the from and to address
    InternetAddress addressFrom = new InternetAddress(from);
    message.setFrom(addressFrom);
    InternetAddress[] addressTo = new InternetAddress[recipients.length];
    for (int i = 0; i < recipients.length; i++)
    {
        addressTo[i] = new InternetAddress(recipients[i]);
    }
    message.setRecipients(Message.RecipientType.TO, addressTo);
    // Setting the Subject and Content Type
    message.setSubject(subject);
    // Create the message part 
    BodyPart messageBodyPart = new MimeBodyPart();
    // Fill the message
    messageBodyPart.setText(messagebodytext);
    // Create a multipar message
    Multipart multipart = new MimeMultipart();
    // Set text message part
    multipart.addBodyPart(messageBodyPart);
    // Part two is attachment
    messageBodyPart = new MimeBodyPart();
    DataSource source = new FileDataSource(filepath);
    messageBodyPart.setDataHandler(new DataHandler(source));
    messageBodyPart.setFileName(attachedfilename);
    multipart.addBodyPart(messageBodyPart);
    // Send the complete message parts
    message.setContent(multipart );
   // transport.connect();
   // transport.send(message);
   // transport.close();
    Transport.send(message);
 }


/**
* SimpleAuthenticator is used to do simple authentication when the SMTP server requires it.
*/
private class SMTPAuthenticator extends javax.mail.Authenticator
{

    public PasswordAuthentication getPasswordAuthentication()
    {
        String username = SMTP_AUTH_USER;
        String password = SMTP_AUTH_PWD;
        return new PasswordAuthentication(username, password);
    }
}

public static void main(String args[]) throws Exception
{
  MailManagement smtpMailSender = new MailManagement();
  smtpMailSender.SendMail( emailFromAddress, emailList, emailSubjectTxt, emailMsgTxt,"D:\\FTP\\Client.csv","ClientMaster.csv");
  System.out.println("Sucessfully Sent mail to All Users");
}

}


