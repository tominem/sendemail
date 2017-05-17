package br.com.chubbytech.nfedispatcher;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.chubbytech.nfedispatcher.service.XMLFileScanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	Logger logger = LoggerFactory.getLogger(App.class);
    	logger.info("==> Iniciou sistema ...");
    	
    	ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    	scheduler.scheduleAtFixedRate(new XMLFileScanner(), 0, 5, TimeUnit.SECONDS);
    	
    }
}
