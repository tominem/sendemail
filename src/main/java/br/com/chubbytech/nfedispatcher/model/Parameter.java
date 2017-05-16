package br.com.chubbytech.nfedispatcher.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("parameter")
public class Parameter {

	@XStreamAsAttribute
	private String key;

	@XStreamAsAttribute
	private String value;
	
	public Parameter() {
		// TODO Auto-generated constructor stub
	}
	
	public Parameter(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
