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
    	
    	ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    	scheduler.scheduleAtFixedRate(new XMLFileScanner(), 0, 5, TimeUnit.SECONDS);
    	
    }
}
