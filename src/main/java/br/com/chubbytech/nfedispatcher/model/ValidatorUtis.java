package br.com.chubbytech.nfedispatcher.model;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorUtis {

	private static Validator validator;
	
	public static Validator getInstance() {
		
		if (validator == null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			validator = factory.getValidator();
		}
		
		return validator;
	}

}
