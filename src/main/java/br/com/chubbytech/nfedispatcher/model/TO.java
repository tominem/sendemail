package br.com.chubbytech.nfedispatcher.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

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
	
	@Override
	public Set<ConstraintViolation<TagEmail>> validate() {
		
		Set<ConstraintViolation<TagEmail>> validateCollections = new HashSet<ConstraintViolation<TagEmail>>();
		
		if (addresses != null) {
			for (Address address : addresses) {
				validateCollections.addAll(address.validate());
			}
		}
		
		return validateCollections;
	}

}
