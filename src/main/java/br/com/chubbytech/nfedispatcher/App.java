package br.com.chubbytech.nfedispatcher;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.chubbytech.nfedispatcher.service.XMLFileScanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
    	scheduler.scheduleAtFixedRate(new XMLFileScanner(), 5, 5, TimeUnit.SECONDS);
    	
    }
}
