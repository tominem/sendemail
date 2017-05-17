package br.com.chubbytech.nfedispatcher.service;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.chubbytech.nfedispatcher.model.Address;
import br.com.chubbytech.nfedispatcher.model.Attachment;
import br.com.chubbytech.nfedispatcher.model.Email;
import br.com.chubbytech.nfedispatcher.model.From;
import br.com.chubbytech.nfedispatcher.model.MessageBody;
import br.com.chubbytech.nfedispatcher.model.TO;

public class SendMail {

	private Email email;

	public SendMail(Email email) {
		this.email = email;
	}
	
	public void send() throws Exception {
		
		From from = email.getFrom();
		
		Email.validate(email);
		
		Properties emailProps = loadProps(from);
		
		String username = from.getUsername();
		String senha	= null;
		
		try {
			senha = new EncryptDecrypt().decrypt(from.getPassword());
		} catch (Exception e) {
			throw new Exception(String.format("Erro no momento de descriptografar a senha, verifique se a senha está criptografada corretamente, motivo: %s", e.getMessage()));
		}
		
		Session session = createSession(emailProps, username, senha);
		Message message = buildMessage(session, username);

		Transport.send(message);
		
	}

	private Message buildMessage(Session session, String username) throws MessagingException, AddressException {
		
		TO 			  to		  = email.getTo();
		List<Address> addresses   = to.getAddresses();
		MessageBody   messageBody = email.getMessageBody();
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.setRecipients(Message.RecipientType.TO, buildAddresses(addresses));
		message.setSubject(messageBody.getSubject());
		message.setContent(buildBodyPart(messageBody));
		
		return message;
	}

	private Multipart buildBodyPart(MessageBody messageBody) throws MessagingException {
		
		Multipart multipart = new MimeMultipart();
		
		BodyPart textPart = new MimeBodyPart();
		textPart.setContent(messageBody.getText(), messageBody.getContent());
		
		multipart.addBodyPart(textPart);
		
		for(Attachment att : messageBody.getAttachments()){
			addAttachment(multipart, att.getSrc());
		}
		
		return multipart;
	}
	
	private void addAttachment(Multipart multipart, String filename) throws MessagingException
	{
	    DataSource source = new FileDataSource(filename);
	    BodyPart messageBodyPart = new MimeBodyPart();        
	    messageBodyPart.setDataHandler(new DataHandler(source));
	    messageBodyPart.setFileName(filename);
	    multipart.addBodyPart(messageBodyPart);
	}

	private InternetAddress[] buildAddresses(List<Address> addresses) throws AddressException {
		return InternetAddress
				.parse(addresses.stream().map(Object::toString)
				.collect(Collectors.joining(",")));
	}
	
	private Session createSession(Properties emailProps, String userName, String senha) {
		
		Session session = Session.getInstance(emailProps, new Authenticator(){
		
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, senha);
			}
			
		});
		
		return session;
	}

	private Properties loadProps(From from) {
		
		Properties props = new Properties();
		props.put("mail.smtp.auth"           , from.isAuth()     );
		props.put("mail.smtp.starttls.enable", from.isTls()      );
		props.put("mail.smtp.host"           , from.getSmtpHost());
		props.put("mail.smtp.port"           , from.getSmtpPort());
		
		return props;
	}

}
