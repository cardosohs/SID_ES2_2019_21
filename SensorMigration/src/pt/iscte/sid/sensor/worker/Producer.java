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
		 * tenta inserir na mongoDB
		 * se consegue trata o seguinte 
		 * se falha mete na blockingQueue
		 * 
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
