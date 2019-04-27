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
    public static void main(String[] args) {

        String topic = "/sid_lab_2019";
        String broker = "tcp://broker.mqtt-dashboard.com:1883";
        String clientId = "Client1";
        
    	String dbName = "mybd";
    	String colName = "MedicoesSensor";
    	
        MemoryPersistence persistence = new MemoryPersistence();
        
        //Estabelece ligação com a MongoDB PRIMARIA
        MongoClient mongoClient = new MongoClient("localhost", 27017);
    	System.out.println("Connection established");
    	
    	//Request da DB
    	MongoDatabase database = mongoClient.getDatabase(dbName);
    	
    	//Request da coleção
    	MongoCollection<Document> collection = database.getCollection(colName);
    	
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
             			 	

             	 collection.insertOne(doct);
                    System.out.println("Topic : " + topic + " Message : " + mqttMessage);
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
        mongoClient.close();
    }
}
