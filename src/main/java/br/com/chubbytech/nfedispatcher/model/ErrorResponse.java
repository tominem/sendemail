package br.com.chubbytech.nfedispatcher.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("error")
public class ErrorResponse {

	@XStreamAsAttribute
	private String message;

	public ErrorResponse(String message) {
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
