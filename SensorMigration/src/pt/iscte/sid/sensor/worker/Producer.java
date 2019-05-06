package pt.iscte.sid.sensor.worker;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Producer implements Runnable  {
	
	protected BlockingQueue<Object> queue;
	protected String MsgSensor;
	
	public Producer(BlockingQueue<Object> theQueue, String MsgSensor) {
        this.queue = theQueue;
        this.MsgSensor = MsgSensor;
    }

	@Override
	public void run() {
		
		System.out.println(MsgSensor);
		//MqttSubscriber.getInstance().connectSensor();
		//MongoCon.getInstance().connectMongoBd();
				
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

