package br.com.chubbytech.nfedispatcher.service;

import static br.com.chubbytech.nfedispatcher.model.Email.fromXML;
import static java.lang.String.format;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.mapper.CannotResolveClassException;

import br.com.chubbytech.nfedispatcher.exception.CannotCastXMLFile;
import br.com.chubbytech.nfedispatcher.model.Email;

public class XMLFileScanner implements Runnable{

	static Logger logger = LoggerFactory.getLogger(XMLFileScanner.class);
	
	private long lastDate;
	
	private AtomicInteger count = new AtomicInteger(1);
	

	@Override
	public void run() {
		
		String basePathStr = System.getProperty("user.dir");
    	
    	File basePath = new File(basePathStr);
    	
    	File[] files = basePath.listFiles((f) -> f.lastModified() > lastDate && f.getName().startsWith("email") && f.getName().endsWith(".xml"));
    	
    	if (files != null && files.length > 0)
    	{
    		
    		Arrays.sort(files, (f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()));
    		
    		lastDate = files[files.length-1].lastModified();
    		
    		ResponseGenerator responseGenerator = new ResponseGenerator();

    		for (File file : files) 
    		{
    			
    			Email email = null;
    			
				try {
					
					email = fromXML(file);
					new SendMail(email).send();
					responseGenerator.responseOK(email.getId());
					
					logger.info(format("Email id: %s, enviado com sucesso", email.getId()));
					
				} catch (Exception e) {
					
					if (e instanceof CannotResolveClassException) {
						String id = "ERR" + count.getAndIncrement();
						e = new CannotCastXMLFile(String.format("Não foi possível carregar o arquivo %s, verifique o formato do mesmo", file.getAbsolutePath()));
						responseGenerator.responseError(e, id);
						logger.error(format("Não foi possível enviar o email id: %s, arquivo: %s ", id, file.getAbsolutePath()), e);
					}
					else{
						responseGenerator.responseError(e, email.getId());
						logger.error(format("Não foi possível enviar o email id: %s, arquivo: %s ", email.getId(), file.getAbsolutePath()), e);
					}
					
					
				} finally{
					
					boolean delete = file.delete();
					
					if (!delete) {
						
						logger.error(format("Não foi possível deletar o arquivo: %s", file.getAbsolutePath()));
						
					}
					
				}
				
			}
    		
		}
    	
	}
	

}
