package br.com.chubbytech.nfedispatcher.service;

import static br.com.chubbytech.nfedispatcher.model.Email.fromXML;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import com.thoughtworks.xstream.mapper.CannotResolveClassException;

import br.com.chubbytech.nfedispatcher.exception.CannotCastXMLFile;
import br.com.chubbytech.nfedispatcher.model.Email;

public class XMLFileScanner implements Runnable{

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
					
				} catch (Exception e) {
					
					if (e instanceof CannotResolveClassException) {
						e = new CannotCastXMLFile(String.format("Não foi possível carregar o arquivo %s, verifique o formato do mesmo", file.getAbsolutePath()));
						responseGenerator.responseError(e, "ERR" + count.getAndIncrement());
					}
					else{
						responseGenerator.responseError(e, email.getId());
					}
					
				} finally{
					
					boolean delete = file.delete();
					
					if (!delete) {
						
						System.out.println("Não deletou");
						
					}
					
				}
				
			}
    		
    		System.out.printf("Finalizou task %d", count.getAndIncrement());
    		
		}
    	
	}
	

}
