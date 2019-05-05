package pt.iscte.sid.sensor.worker;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.fasterxml.jackson.databind.ObjectMapper;

import pt.iscte.sid.sensor.connections.MongoCon;
import pt.iscte.sid.sensor.connections.MqttSubscriber;

public class Producer implements Runnable  {
	
	protected BlockingQueue<Object> queue;
	
	public Producer(BlockingQueue<Object> theQueue) {
        this.queue = theQueue;
    }

	@Override
	public void run() {
		
		MongoCon.getInstance().connectMongoBd();
		MqttSubscriber.getInstance().connectSensor();
		
		/*		 
		 * tenta inserir na mongoDB
		 * se consegue trata o seguinte 
		 * se falha mete na blockingQueue
		 * 
		 */
		
	}
	
	public String dealWithString(MqttMessage mqttMessage) {
		String js1 = mqttMessage.toString();
		String treated = js1.replace("\"\"", "\",\"");
		if(isJSONValid(treated)==false) {
			return null;
		}
		return treated;
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
