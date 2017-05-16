package br.com.chubbytech.nfedispatcher.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("from")
public class From implements Serializable, TagEmail {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8493851353540493060L;
	
	@XStreamAsAttribute
	@NotNull(message="O host do smtp deve ser informado")
	private String  smtpHost;
	
	@XStreamAsAttribute
	@NotNull(message="A porta do smtp deve ser informada")
	private Integer	smtpPort;
	
	@XStreamAsAttribute
	private boolean ssl;
	
	@XStreamAsAttribute
	private boolean tls;
	
	@XStreamAsAttribute
	private boolean auth;
	
	@XStreamAsAttribute
	@NotNull(message="O usuário deve ser informado")
	@Email(message="Usuário informado na tag [from] [${validatedValue}] não segue o formato, ex: usuario@gmail.com")
	private String  username;
	
	@XStreamAsAttribute
	private String  password;

	public From() {

	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public Integer getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(Integer smtpPort) {
		this.smtpPort = smtpPort;
	}

	public boolean isSsl() {
		return ssl;
	}

	public void setSsl(boolean ssl) {
		this.ssl = ssl;
	}

	public boolean isTls() {
		return tls;
	}

	public void setTls(boolean tls) {
		this.tls = tls;
	}

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
