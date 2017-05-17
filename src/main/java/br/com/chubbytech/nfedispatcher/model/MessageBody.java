package br.com.chubbytech.nfedispatcher.model;

import static java.lang.String.format;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("message")
public class MessageBody implements TagEmail {

	@XStreamAsAttribute
	@Size(min=4, message="O atributo (subject) da [mensagem] deve conter no mínimo {min} caracteres")
	private String  subject;
	
	@XStreamAsAttribute
	@NotNull(message="O atributo (content) da tag [mensagem] não pode ser nulo, informe: \"text/plain; charset=UTF-8\" ou \"text/html; charset=utf-8\"")
	private String  content = "text/plain; charset=UTF-8";
	
	@XStreamAsAttribute
	@Size(min=15, message="O conteúdo (text) da [mensagem] deve conter no mínimo {min} caracteres")
	private String  text;
	
	@XStreamAlias("msg-parameters")
	private List<Parameter> parameters;
	
	private List<Attachment> attachments;

	public MessageBody() {

	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getText() {
		try {
			if (text.toLowerCase().startsWith("file://")) {
				text = text.substring("file://".length());
				text = new String(Files.readAllBytes(Paths.get(text)));
			}
			
			replaceParametersIfExists();
			
		} catch (IOException e) {
			throw new RuntimeException(format("Arquivo: %s não pode ser anexado, motivo: %s", text, e.getMessage()));
		}
		return text;
	}

	private void replaceParametersIfExists() {
		Optional.ofNullable(getParameters()).ifPresent(params -> {
			
			for (Parameter param : params) {
				setText(text.replace(param.getKey(), param.getValue()));
			}
			
		});
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public void addAttachment(Attachment attachment) {
		getAttachments().add(attachment);
	}

	public List<Attachment> getAttachments() {
		if (attachments == null) {
			attachments = new ArrayList<>();
		}
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<Parameter> getParameters() {
		if (parameters == null) {
			parameters = new ArrayList<>();
		}
		return parameters;
	}

	public void setParameters(List<Parameter> params) {
		this.parameters = params;
	}

	public void addParameters(Parameter param) {
		getParameters().add(param);
	}

}
