import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;

public class email {
	
	public static void sendEmail(String recipient, String code) throws Exception {
		System.out.println("Preparing to send email");
		Properties properties = new Properties();
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		
		//start to use actual email for local host or to send it from
		//created a throwaway email bot on gmail
		String myEmail = "stevesmessagebot@gmail.com";
		String password = "SD5678FGh";
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myEmail, password);
			}

		});
		
		Message message = prepareMessage(session, myEmail, recipient, code);
		
		Transport.send(message);
		System.out.println("Message sent successfully");
	}
		
	
	public static String generateCode() {
		 	String possibleChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	     	StringBuilder verifyCode = new StringBuilder();
	        Random rnd = new Random();
	        while (verifyCode.length() < 8) {
	            int index = (int) (rnd.nextFloat() * possibleChar.length());
	            verifyCode.append(possibleChar.charAt(index));
	        }
	        return verifyCode.toString();
	}
	
	
	private static Message prepareMessage(Session session, String myEmail, String recipient, String code){
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("Login Verification Code");
			message.setText("Verification Code: " + code);
			return message;
		} 
			catch (Exception exception) {
				System.out.println(exception.getMessage());
			}
		return null;
		}
	}
	
