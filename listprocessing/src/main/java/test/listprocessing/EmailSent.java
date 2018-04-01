
package test.listprocessing;

import java.util.Properties;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSent {

	public static void sendMessage() throws AddressException, MessagingException {
		
		Properties server;
		Session getMail;
		MimeMessage msg;
		String body;
		
		server = System.getProperties();
		
		server.put("mail.smtp.port", "587");
		server.put("mail.smtp.starttls.enable", "true");
		server.put("mail.smtp.auth", "true");
		
		getMail = Session.getDefaultInstance(server, null);
		msg = new MimeMessage(getMail);
		
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress("joshua.beaudoin@uconn.edu"));
		
		msg.setSubject("ListProcessing Error");
		
		body = "There was an error in the List Processing workflow. Requires attention.";
		
		msg.setContent(body, "text/html");
		
		Transport send = getMail.getTransport("smtp");
		 
		send.connect("smtp.gmail.com", "senior.desgin2018@gmail.com", "Senior2018");
		
		send.sendMessage(msg, msg.getAllRecipients());
		send.close();
		
	}
	
	public static void main(String[] args) throws AddressException, MessagingException {
		
		sendMessage();
		System.out.println("Successful");

	}

	
}
