package br.com.chubbytech.nfedispatcher.model;

import java.io.File;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("file")
public class Attachment {

	@XStreamAsAttribute
	private String src;

	public Attachment(String src) {
		this.src = src;
	}
	
	public Attachment() {
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}
	
	public String getFileName() {
		return new File(getSrc()).getName();
	}

}
