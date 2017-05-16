package br.com.chubbytech.nfedispatcher;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import br.com.chubbytech.nfedispatcher.model.Address;
import br.com.chubbytech.nfedispatcher.model.Attachment;
import br.com.chubbytech.nfedispatcher.model.Email;
import br.com.chubbytech.nfedispatcher.model.From;
import br.com.chubbytech.nfedispatcher.model.MessageBody;
import br.com.chubbytech.nfedispatcher.model.Parameter;
import br.com.chubbytech.nfedispatcher.model.TO;
import br.com.chubbytech.nfedispatcher.service.SendMail;

public class SendEmailTest {
	
	@Test
	public void send() {

		Email email = Email.fromXML(new File("C:/Users/tom/email-01.xml"));
		
		new SendMail(email).send();
		
	}
		
	private Email createEmail() {
		
		Email email = new Email();
		
		email.setId("XXX");
		
		From from = new From();
		from.setUsername("tominemtom@gmail.com");
		from.setPassword("ewtt159168");
		from.setSmtpHost("smtp.gmail.com");
		from.setSmtpPort(587);
		from.setSsl(true);
		from.setAuth(true);
		from.setTls(true);
		
		TO to = new TO();
		to.setAddresses(Address.asList("tominem@hotmail.com", "tominemtom@gmail.com"));
		
		MessageBody messageBody = new MessageBody();
		messageBody.setSubject("Teste de email 2");
		messageBody.setText("Teste de envio de e-mail do tipo texto de: $meu_email,$meu_email para $outro_email");
		messageBody.addAttachment(new Attachment("C:/Users/tom/pl.ini"));
		messageBody.addAttachment(new Attachment("C:/Users/tom/soapui-settings.xml"));
		messageBody.addParameters(new Parameter("$meu_email"  , "tominem@hotmail.com"));
		messageBody.addParameters(new Parameter("$outro_email", "tominemtom@gmail.com"));
		
		email.setFrom(from);
		email.setTo(to);
		email.setMessage(messageBody);
		
		System.out.println(email.toXML());

		Assert.assertEquals("Teste de envio de e-mail do tipo texto de: tominem@hotmail.com,tominem@hotmail.com para tominemtom@gmail.com", messageBody.getText());

		return email;
	}
	
//	@Test
//	public void test(){
//		
//		Properties props = new Properties();
//		props.put("mail.smtp.auth"           , true     );
//		props.put("mail.smtp.starttls.enable", true     );
//		props.put("mail.smtp.host"           , "smtp.gmail.com");
//		props.put("mail.smtp.port"           , 587);
//		
//		try {
//			
//			Session session = Session.getInstance(props, new Authenticator(){
//				
//				@Override
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication("tominemtom@gmail.com", "ewt9168");
//				}
//				
//			});
//
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress("tominemtom@gmail.com"));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("tominemtom@gmail.com"));
//			message.setSubject("Testing Subject");
//			message.setText("Dear Mail Crawler,"
//				+ "\n\n No spam to my email, please!");
//
//			Transport.send(message);
//
//			System.out.println("Done");
//
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
//		
//	}
	
}
