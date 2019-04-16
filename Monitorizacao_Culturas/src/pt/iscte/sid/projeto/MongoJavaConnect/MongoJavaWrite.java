package pt.iscte.sid.projeto.MongoJavaConnect;

import java.net.UnknownHostException;


import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;


import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.async.client.*;
import com.mongodb.async.SingleResultCallback;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.connection.ClusterSettings;
import com.mysql.cj.x.protobuf.MysqlxNotice.Warning.Level;

import org.bson.BsonTimestamp;
import org.bson.Document;
import org.bson.json.JsonParseException;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.currentDate;
import static com.mongodb.client.model.Updates.set;
import static java.util.Arrays.asList;

import com.mongodb.MongoClient;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoJavaWrite {
		
	public static void main (String[] args) throws UnknownHostException {
		
		//Estabelece ligação com a MongoDB PRIMARIA
		MongoClient mongoClient = new MongoClient("localhost", 25017);
		System.out.println("Connection established");
		
		//Request da DB "mydb"
		MongoDatabase database = mongoClient.getDatabase("mydb");
		
		//Request de TODAS as coleccoes de "mydb"
		MongoCollection<Document> collection = database.getCollection("test");

		
		/*Cria uma nova entrada na colecao "test"
		Exemplo:
	 		{
			   "name" : "MongoDB",
			   "type" : "database",
			   "i" : "
			   "count" : 1,
			   "versions": [ "alex", "ric", "sergio" ],
			   "info" : { x : 203, y : 102 }
			  }
		  */
		
		 Document doc = 
				 	new Document("name", "MongoDB")
	                .append("temp", "database")
	                .append("data", "09-09-09")
	                .append("time", "12:00")
	                .append("luz", "123");
	                //.append("luz", Arrays.asList("alex", "ric", "sergio"))
	                //.append("info", new Document("x", 203).append("y", 102));
		 
		 collection.insertOne(doc);
		 
		 
//		 public void messageArrived(String topic, MqttMessage mqttMessage) {
//			 try {
//				 final Document document = Document.parse(mqttMessage.toString());
//				 // Mapeia tópico de sensor para Entrada ou Saída
//				 document.append("sensor", TOPIC_TO_SENSOR.get(topic));
//				 document.append("created_at", new BsonTimestamp());
//				 // pedido de inserção e callback handler para resultado
//				 collection.insertOne(document, (result, throwable) -> {
//					 if (throwable != null) {
//						 // caso não seja possível contactar com o MongoDB,
//						 // guarda passagem para tentar mais tarde
//						 if (throwable.getCause() instanceof IOException) {
//							 failedDocuments.add(document);
//						 }
//						 LOGGER.log(Level.WARNING, throwable.toString());
//					 }
//				 });
//			 } catch (JsonParseException | IllegalArgumentException e) {
//				 LOGGER.log(Level.WARNING, e.toString());
//			 }
//		 }
		 
		 
	}
}
