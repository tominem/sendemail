package br.com.chubbytech.nfedispatcher.model;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("cc")
public class CC implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5619065771466846927L;
	
	@XStreamImplicit(itemFieldName="endereco")
	private List<String> addresses;
	
	public CC() {
		
	}

	public List<String> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}

}
