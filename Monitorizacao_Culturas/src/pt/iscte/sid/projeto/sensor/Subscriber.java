package pt.iscte.sid.projeto.sensor;

import org.bson.Document;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Subscriber {
	
	
	//Sample of mqttMessage: {"tmp":"25.40","hum":"39.70","dat":"27/4/2019","tim":"21:15:5","cell":"18""sens":"eth"}
	public static void trataString (MqttMessage message) {
		String luz, tmp, data, hora = "";
		String temp1 = message.toString();
		String temp2 = temp1.replaceAll("\"", "");
		String temp3 = temp2.replace("{", "");
		String temp4 = temp3.replace("}", "");
		String temp5 = temp4.replace("sens:eth", "");
		String [] temp6 = temp5.split(",");
		tmp = temp6[0].substring(4,9);
		luz = temp6[4].substring(5,8);
		data = temp6[2].substring(4,13);
		if (temp6[3].length() == 12)
			hora = temp6[3].substring(4,12);
		else 
			hora = temp6[3].substring(4,11);
		System.out.println("A luz é: " + luz);
		System.out.println("A temp é: " + tmp);
		System.out.println("A data é: " + data);
		System.out.println("A hora é: " + hora);
	}
	
	
    public static void main(String[] args) {
    	
    	//resultados obtidos da mensagem:
    	
    	
        String topic = "/sid_lab_2019";
        String broker = "tcp://broker.mqtt-dashboard.com:1883";
        String clientId = "Client1";
        
    	String dbName = "mybd";
    	String colName = "MedicoesSensor";
    	
        MemoryPersistence persistence = new MemoryPersistence();
        
//        //Estabelece ligação com a MongoDB PRIMARIA
//        MongoClient mongoClient = new MongoClient("localhost", 27017);
//    	System.out.println("Connection established");
//    	
//    	//Request da DB
//    	MongoDatabase database = mongoClient.getDatabase(dbName);
//    	
//    	//Request da coleção
//    	MongoCollection<Document> collection = database.getCollection(colName);
    	
    
        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            sampleClient.connect(connOpts);

            sampleClient.subscribe(topic);
            sampleClient.setCallback(new MqttCallback() {
                public void connectionLost(Throwable throwable) {
                    throwable.printStackTrace();
                }

                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {

               Document doct = Document.parse(mqttMessage.toString());
             			 	
               
               trataString(mqttMessage);

               System.out.println("Topic : " + topic + " Message : " + mqttMessage);
                 
                }


             	 //collection.insertOne(doct);
                  //  System.out.println("Topic : " + topic + " Message : " + mqttMessage.toString());
                  //  System.out.println("Mqttmessage: " + mqttMessage);
                  //  System.out.println("Mqttmessagestr: " + mqttMessage.toString());
                }
                
               
                
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    System.out.println("Delivery complete : " + iMqttDeliveryToken);
                }
                
            });
            //timer para terminar recolha de info ... alterar conforme necessário
            new CountDownLatch(1).await(100, TimeUnit.SECONDS);
            sampleClient.disconnect();            
        } catch (MqttException | InterruptedException e) {
            e.printStackTrace();
        }
      // mongoClient.close();
        
    }
}
