package br.com.chubbytech.nfedispatcher.service;

import static br.com.chubbytech.nfedispatcher.model.Email.fromXML;

import java.io.File;
import java.util.Arrays;

public class XMLFileScanner implements Runnable{


	@Override
	public void run() {
		
		String basePathStr = System.getProperty("user.dir");
    	
    	File basePath = new File(basePathStr);
    	
    	File[] files = basePath.listFiles((f) -> !f.getName().startsWith("resp-") && f.getName().endsWith(".xml"));
    	
    	if (files != null) {
    		
    		Arrays.sort(files, (f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()));
    		
    		for (File file : files) {
    			
				new SendMail(fromXML(file)).send();
				
				boolean delete = file.delete();
				
				if (!delete) {
					//TODO imprime log de erro
				}
				
			}
    		
		}
    	
	}
	

}
