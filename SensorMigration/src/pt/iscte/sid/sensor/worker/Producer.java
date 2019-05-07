package pt.iscte.sid.sensor.worker;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import org.bson.BsonTimestamp;
import org.bson.Document;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.async.client.*;

import pt.iscte.sid.sensor.connections.MongoCon;
import pt.iscte.sid.sensor.connections.MqttSubscriber;

public class Producer implements Runnable  {
	
	protected BlockingQueue<Object> queue;	
	
	public Producer(BlockingQueue<Object> theQueue) {
        this.queue = theQueue;        
    }

	@Override
	public void run() {
		//liga a mongodb
		//MongoCon.getInstance().connectMongoBd();
		
		//liga o sensor
		MqttSubscriber.getInstance().getClient().setCallback(new MqttCallback() {
			public void connectionLost(Throwable throwable) {
				throwable.printStackTrace();
				System.out.println("fodeu a ligacao por timeout e tratas aqui");
			}			
			public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
				//System.out.println("Topic : " + topic + " Message : " + mqttMessage);
				String js1 = mqttMessage.toString();
				 
				if(js1.contains("\"\"")) {
					String treated = js1.replace("\"\"", "\",\"");
					if(isJSONValid(treated)==true) {
						final ObjectMapper mapper = new ObjectMapper();
						JsonNode actualObj = mapper.readTree(treated);
						
						if(actualObj.has("tmp")==true) {
							final Document tmp = new Document("tmp",actualObj.findValue("tmp").asText())
									.append("timestamp", actualObj.findValue("dat").asText() + " " + actualObj.findValue("tim").asText())
									.append("time_med", new BsonTimestamp());
							/*MongoCon.getInstance().getCollection().insertOne(tmp, (result, throwable) -> {
								if(throwable != null) {
									if(throwable.getCause() instanceof IOException) {
										queue.add(tmp);
									}
									LOGGER.log(Level.Warning, throwable.toString());
								}
								
							});
							*/							
						}
						
						if(actualObj.has("cell")==true) {
							final Document cell = new Document("cell",actualObj.findValue("cell").asText())
									.append("timestamp", actualObj.findValue("dat").asText() + " " + actualObj.findValue("tim").asText())
									.append("time_med", new BsonTimestamp());
							MongoCon.getInstance().getCollection().insertOne(cell);									
						}
					}
				}
				
			}

			public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
				System.out.println("Delivery complete : " + iMqttDeliveryToken);
			}
		});			
		
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

