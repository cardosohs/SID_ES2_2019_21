package pt.iscte.sid.sensor.worker;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.BlockingQueue;



import org.bson.BsonTimestamp;
import org.bson.Document;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pt.iscte.sid.sensor.connections.MongoCon;
import pt.iscte.sid.sensor.connections.MqttSubscriber;




public class Producer implements Runnable  {
    protected BlockingQueue<Document> queue;    
        
    public Producer(BlockingQueue<Document> theQueue) {
        this.queue = theQueue;        
    }

    @Override
    public void run() {            	
            	
        MqttSubscriber.getInstance().getClient().setCallback(new MqttCallback() {        	
            public void connectionLost(Throwable throwable) {
                throwable.printStackTrace();                
            }
            
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
    			Date date1= new Date();
   			    long time = date1.getTime();
    			Timestamp ts = new Timestamp(time);
                System.out.println("Topic : " + topic + " Message : " + mqttMessage + " Timestamp INICIO: " + ts.toString() );
                String js1 = mqttMessage.toString();
                if(js1.contains("\"\"")) {
                    String treated = js1.replace("\"\"", "\",\"");
                    if(isJSONValid(treated)) {
                        final ObjectMapper mapper = new ObjectMapper();
                        JsonNode actualObj = mapper.readTree(treated);

                        if(actualObj.has("tmp")) {
                            final Document tmp = new Document("tmp",actualObj.findValue("tmp").asText())
                                    .append("timestamp", actualObj.findValue("dat").asText() + " " + actualObj.findValue("tim").asText())
                                    .append("time_med", new BsonTimestamp());
							MongoCon.getInstance().getCollection().insertOne(tmp, (result, throwable) -> {
								if(throwable != null) {
									if(throwable.getCause() instanceof IOException) {
										queue.add(tmp);
									}
								}
							});
                        }
                        if(actualObj.has("cell")) {
                            final Document cell = new Document("cell",actualObj.findValue("cell").asText())
                                    .append("timestamp", actualObj.findValue("dat").asText() + " " + actualObj.findValue("tim").asText())
                                    .append("time_med", new BsonTimestamp());
                            MongoCon.getInstance().getCollection().insertOne(cell, (result, throwable) -> {
                                if(throwable != null) {
                                    if(throwable.getCause() instanceof IOException) {
                                        queue.add(cell);
                                    }
                                }
                            });
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