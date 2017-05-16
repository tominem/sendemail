package br.com.chubbytech.nfedispatcher.model;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("email")
public class Email implements TagEmail {

	private static Validator validator;
	
	@NotNull(message="É obrigatório informar o id do email")
	private String id;

	private From from;

	private TO to;

	private MessageBody messageBody;

	public Email() {
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public From getFrom() {
		return from;
	}

	public void setFrom(From from) {
		this.from = from;
	}

	public TO getTo() {
		return to;
	}

	public void setTo(TO to) {
		this.to = to;
	}

	public MessageBody getMessageBody() {
		return messageBody;
	}

	public void setMessage(MessageBody messageBody) {
		this.messageBody = messageBody;
	}
	
	public String toXML(){
		
		XStream xstream = new XStream();
		xstream.aliasSystemAttribute(null, "class");
		xstream.processAnnotations(getClass());
		
		return xstream.toXML(this);
	}

	public static Email fromXML(File file) {
		
		XStream xstream = new XStream();
		xstream.aliasSystemAttribute(null, "class");
		xstream.processAnnotations(Email.class);
		
		return (Email) xstream.fromXML(file);
	}
	
	@SuppressWarnings("unchecked")
	public static void validate(Email email){
		
		if (validator == null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			validator 				 = factory.getValidator();
		}
		
		From from 			= email.getFrom();
		TO to 				= email.getTo();
		MessageBody message = email.getMessageBody();
		
		Set<ConstraintViolation<Email>>       emailVal   = validator.validate(email  );
		Set<ConstraintViolation<From>>        fromVal    = validator.validate(from   );
		Set<ConstraintViolation<TO>>          ToVal      = validator.validate(to     );
		Set<ConstraintViolation<MessageBody>> messageVal = validator.validate(message);
		
		Set<ConstraintViolation<TagEmail>> validateCollections = new HashSet<ConstraintViolation<TagEmail>>();
		
		validateCollections.addAll((Collection<? extends ConstraintViolation<TagEmail>>) emailVal  );
		validateCollections.addAll((Collection<? extends ConstraintViolation<TagEmail>>) fromVal   );
		validateCollections.addAll((Collection<? extends ConstraintViolation<TagEmail>>) ToVal     );
		validateCollections.addAll((Collection<? extends ConstraintViolation<TagEmail>>) messageVal);
		
		if (validateCollections.size() > 0) {
			
			throw new InvalidationException(validateCollections);
			
		}
		
	}

}
