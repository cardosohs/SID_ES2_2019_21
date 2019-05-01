package pt.iscte.sid.sensor.worker;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Producer implements Runnable  {
	
	protected BlockingQueue<Object> queue;
	
	Producer(BlockingQueue<Object> theQueue) {
        this.queue = theQueue;
    }

	@Override
	public void run() {
		/*
		 * Receber a string
		 * transformar em json
		 * criar documento
		 * tentar inserir 
		 */
		
	}
	
	
	
	
	public static boolean isJSONValid(String isJsonString ) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.readTree(isJsonString);
			return true;
		}catch (IOException e) { 
			return false;
		}	
	}
	
	

}
