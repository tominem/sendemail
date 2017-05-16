package br.com.chubbytech.nfedispatcher.model;

import java.io.Serializable;
import java.util.List;

public class TO implements Serializable, TagEmail {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7554650522623588123L;
	
	private List<Address> addresses;
	
	public TO() {
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}
