package pt.iscte.sid.projeto.codigoauxiliar;
import org.bson.BsonTimestamp;
import org.bson.Document;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Subscriber {
	
	
	//private static String [] resultados  = new String [3];
	private static List<Document> docs = new ArrayList<Document>();
	
	
	public static void main(String[] args) {

		String topic = "/sid_lab_2019";
		//String topic = "/sid_lab_2019";
		//String broker = "tcp://broker.mqtt-dashboard.com:1883";
		String broker = "tcp://broker.mqttdashboard.com:1883";
		String clientId = "Client1";
		String dbName = "sensorbd";
		String colName = "MedicoesSensor";
		 
		MemoryPersistence persistence = new MemoryPersistence();
		
//		//Estabelece ligacao com a MongoDB PRIMARIA
//		MongoClient mongoClient = new MongoClient("localhost", 27017);
//		System.out.println("Connection established");
//
//		//Request da DB
//		MongoDatabase database = mongoClient.getDatabase(dbName);
//
//		//Request da colecao
//		MongoCollection<Document> collection = database.getCollection(colName);
		 

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
					//String js1 = mqttMessage.toString();
					//resultados = MessageParser.parse(js1);
					
//					createDocuments(mqttMessage);
//					
//					if (!docs.isEmpty())
//						for (Document d : docs)
//							collection.insertOne(d);
//					
//					docs.clear();
					
					System.out.println("Topic : " + topic + " Message : " + mqttMessage);

					//System.out.println(resultados);
				}

				public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
					System.out.println("Delivery complete : " + iMqttDeliveryToken);
				}

			});
			//timer para terminar recolha de info ... alterar conforme necessÃ¡rio
			new CountDownLatch(1).await(100, TimeUnit.SECONDS);
			sampleClient.disconnect();            
		} catch (MqttException | InterruptedException e) {
			e.printStackTrace();
		}
		//mongoClient.close();
	}
	
	
	/**
	 * Create two documents and add then into a array 
	 */
	private static void createDocuments (MqttMessage mqttMessage) {
		
		String message = mqttMessage.toString();
		
		//Insere a vírgula que faltava na mensagem recebida
		String treated = message.replace("\"\"", "\",\"");
		
		//Strings inicializadas com "NULL" para posterior tratamento
		String data="NULL";
		String hora="NULL";
		String temp="NULL";
		String lum="NULL";
		
		//Objetos necessários para a identificação e atribuição de 
		//jsonNodes as strings da mensagem recebida
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		try {
			jsonNode = objectMapper.readTree(treated);
			data = jsonNode.get("dat").asText();
			hora = jsonNode.get("tim").asText();
			temp = jsonNode.get("tmp").asText();
			lum = jsonNode.get("cell").asText();
		} catch (JsonProcessingException e) {
			System.out.println("erro ao processar mensagem Valor colocado a NULL");
		} catch (IOException e) {
			System.out.println("erro de IO");
			e.printStackTrace();
		}
		
		//Se a mensagem não possui valores de luminosidade ou temperatura
		//não é criado o documento respectivo
		if (!temp.equals("NULL")) {
			Document docTemp = new Document ("tmp", temp)
							.append("timestamp", data + " " + hora)
							.append("time_med", new BsonTimestamp());
			docs.add(docTemp);
		}
		if (!lum.equals("NULL")) {
			Document docLuz = new Document ("lum", lum).
							append("timestamp", data + " " + hora).
							append("time_med", new BsonTimestamp());
			docs.add(docLuz);
		}
	}

}
