package br.com.chubbytech.nfedispatcher.model;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

public interface TagEmail {

	default Set<ConstraintViolation<TagEmail>> validate(){
		Validator validator = ValidatorUtis.getInstance();
		return validator.validate(this);
	}
	
}
