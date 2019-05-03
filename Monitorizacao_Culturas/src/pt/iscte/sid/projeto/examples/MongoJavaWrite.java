package pt.iscte.sid.projeto.examples;

import java.net.UnknownHostException;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;


public class MongoJavaWrite {
		
	public static void main (String[] args) throws UnknownHostException {
		
		//Estabelece ligação com a MongoDB PRIMARIA
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		System.out.println("Connection established");
		
		//Request da DB "mydb"
		MongoDatabase database = mongoClient.getDatabase("mybd");
		
		//Request de TODAS as coleccoes de "mydb"
		MongoCollection<Document> collection = database.getCollection("MedicoesSensor");

		
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
				 	new Document("name", "measurement")
	                .append("temp", "37")
	                .append("data", "09-09-09")
	                .append("time", "12:00")
	                .append("luz", "123");
	                //.append("luz", Arrays.asList("alex", "ric", "sergio"))
	                //.append("info", new Document("x", 203).append("y", 102));
		 
		 collection.insertOne(doc);
		 
		 

		 
		 
	}
}
