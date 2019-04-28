package pt.iscte.sid.projeto.sensor;

import org.bson.BsonTimestamp;
import org.bson.Document;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Subscriber {
	
	
	private static String [] resultados  = new String [3];
	private static List<Document> docs = new ArrayList<Document>();
	
	
	/**
	 * Create two documents and add then into a array 
	 */
	private static void createDocuments () {
		if (!resultados[1].equals("NULL")) {
			Document docTemp = new Document ("tmp", resultados[1])
							.append("timestamp", resultados[0])
							.append("time_med", new BsonTimestamp());
			docs.add(docTemp);
		}
		if (!resultados[2].equals("NULL")) {
			Document docLuz = new Document ("lum", resultados[2]).
							append("timestamp", resultados[0]).
							append("time_med", new BsonTimestamp());
			docs.add(docLuz);
		}
	}
	
	
	public static void main(String[] args) {

		String topic = "/sid_lab_2019";
		String broker = "tcp://broker.mqtt-dashboard.com:1883";
		String clientId = "Client1";
		String dbName = "sensorbd";
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
				
				
				// Sample [28/4/2019 11:10:43, 32.20, 5]
				public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
					String js1 = mqttMessage.toString();
					resultados = MessageParser.parse(js1);
					
					createDocuments();
					collection.insertMany(docs);
					docs.clear();
					
					System.out.println("Topic : " + topic + " Message : " + mqttMessage);
					System.out.println(resultados);
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
