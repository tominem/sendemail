package br.com.chubbytech.nfedispatcher.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

public class InvalidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4227621025345652205L;
	
	private Set<ConstraintViolation<TagEmail>> validateCollections;

	public InvalidationException() {
		// TODO Auto-generated constructor stub
	}

	public InvalidationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidationException(Set<ConstraintViolation<TagEmail>> validateCollections) {
		super(InvalidationException.arrayToString(validateCollections));
		
		this.setValidateCollections(validateCollections);
	}

	private static String arrayToString(Set<ConstraintViolation<TagEmail>> validateCollections) {
		
		List<String> collect = validateCollections.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
		
		return String.join(",", collect);
	}

	public Set<ConstraintViolation<TagEmail>> getValidateCollections() {
		return validateCollections;
	}

	public void setValidateCollections(Set<ConstraintViolation<TagEmail>> validateCollections) {
		this.validateCollections = validateCollections;
	}

}
