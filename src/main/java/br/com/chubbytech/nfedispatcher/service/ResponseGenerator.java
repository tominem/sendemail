package br.com.chubbytech.nfedispatcher.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.chubbytech.nfedispatcher.model.ErrorResponse;
import br.com.chubbytech.nfedispatcher.model.InvalidationException;
import br.com.chubbytech.nfedispatcher.model.Response;
import br.com.chubbytech.nfedispatcher.model.Status;

public class ResponseGenerator {
	
	static Logger logger = LoggerFactory.getLogger(ResponseGenerator.class);

	public void responseOK(String id) {

		try {

			Response response = new Response(Status.OK);
			response.setId(id);
			response.toXML();
			
		} catch (IOException ex) {
			
			logger.error(ex.getMessage(), ex);
			
		}
		
	}

	public void responseError(Exception e, String id) {
		
		try {
			
			Response response = new Response(Status.ERROR);
			
			List<ErrorResponse> errors = new ArrayList<>();
			
			if (e instanceof InvalidationException) {
				
				InvalidationException ex = (InvalidationException) e;
				
				errors = ex.getValidateCollections().stream()
					.map(iex -> new ErrorResponse(iex.getMessage())).collect(Collectors.toList());
				
			}
			
			else {
				
				errors.add(new ErrorResponse(e.getMessage()));
				
			}
			
			response.setId(id);
			response.setErrors(errors);
			
			response.toXML();
			
		} catch (Exception ex) {
			
			logger.error(ex.getMessage(), ex);
			
		}
		
	}
	
}
