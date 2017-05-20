package br.com.chubbytech.nfedispatcher.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.Email;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("address")
public class Address implements TagEmail {

	@XStreamAsAttribute
	@Email(message="atributo value=[${validatedValue}] da tag [addresses] é inválido, informe o value seguindo o padrão, ex: user@gmail.com")
	private String value;

	public Address() {
	}
	
	public Address(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static List<Address> asList(String ... addresses) {
		return Arrays.asList(addresses).stream().map(Address::new).collect(Collectors.toList());
	}
	
	@Override
	public String toString() {
		return value;
	}
	
}
