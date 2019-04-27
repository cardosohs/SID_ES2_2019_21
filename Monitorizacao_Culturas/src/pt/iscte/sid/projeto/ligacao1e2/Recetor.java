package pt.iscte.sid.projeto.ligacao1e2;

import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.async.client.*;
import com.mongodb.async.SingleResultCallback;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.connection.ClusterSettings;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.currentDate;
import static com.mongodb.client.model.Updates.set;
import static java.util.Arrays.asList;

import java.net.UnknownHostException;

public class Recetor {
	
	public static void main (String[] args){
	
	
//	 public void messageArrived(String topic, MqttMessage mqttMessage) {
//	 try {
//		 final Document document = Document.parse(mqttMessage.toString());
//		 // Mapeia tópico de sensor para Entrada ou Saída
//		 document.append("sensor", TOPIC_TO_SENSOR.get(topic));
//		 document.append("created_at", new BsonTimestamp());
//		 // pedido de inserção e callback handler para resultado
//		 collection.insertOne(document, (result, throwable) -> {
//			 if (throwable != null) {
//				 // caso não seja possível contactar com o MongoDB,
//				 // guarda passagem para tentar mais tarde
//				 if (throwable.getCause() instanceof IOException) {
//					 failedDocuments.add(document);
//				 }
//				 LOGGER.log(Level.WARNING, throwable.toString());
//			 }
//		 });
//	 } catch (JsonParseException | IllegalArgumentException e) {
//		 LOGGER.log(Level.WARNING, e.toString());
//	 }
//}
	
	
	}
}
