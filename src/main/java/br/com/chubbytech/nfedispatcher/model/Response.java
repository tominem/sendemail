package br.com.chubbytech.nfedispatcher.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("response")
public class Response {

	private String id;
	
	private Status status;
	
	private List<ErrorResponse> errors;

	public Response(){
		
	}
	
	public Response(Status status){
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<ErrorResponse> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorResponse> errors) {
		this.errors = errors;
	}

	public void toXML(String id) throws IOException {
		XStream xstream = new XStream();
		xstream.aliasSystemAttribute(null, "class");
		xstream.processAnnotations(getClass());
		
		String path = String.format("%s/resp-%s.xml", System.getProperty("user.dir"), id);
		
		try(FileOutputStream fos = new FileOutputStream(new File(path)))
		{
			xstream.toXML(this, fos);
		}
		
	}

}
