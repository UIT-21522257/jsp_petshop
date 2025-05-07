package com.demo.helpers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailHelper {
	final static String username = "atun123456789cu@gmail.com";//your email id
    final static String password = "qnwb zznk duhr ogmw";// your password

	 public static void MailHelper(String email, String subject, String final_Text) {
		 Properties props = new Properties();
	        props.put("mail.smtp.auth", true);
	        props.put("mail.smtp.starttls.enable", true);
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
		 Session session = Session.getInstance(props,
	              new javax.mail.Authenticator() {
	                  @Override
	                  protected PasswordAuthentication getPasswordAuthentication() {
	                      return new PasswordAuthentication(username, password);
	                  }
	              });
	      try {
	          Message message = new MimeMessage(session);
	          message.setFrom(new InternetAddress(username));
	          message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
	          MimeBodyPart textPart = new MimeBodyPart();
	          Multipart multipart = new MimeMultipart();
	          textPart.setContent(final_Text, "text/html; charset=utf-8");
	          message.setSubject(subject);
	          multipart.addBodyPart(textPart);
	          message.setContent(multipart);
	          message.setSubject(subject);
	          //out.println("Sending");
	          Transport.send(message);
	        
	      } catch (Exception e) {
	          
	      }
	  
	 }
	
	
}
