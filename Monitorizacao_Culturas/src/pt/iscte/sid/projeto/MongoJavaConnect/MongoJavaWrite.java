package pt.iscte.sid.projeto.MongoJavaConnect;

import java.net.UnknownHostException;
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
		MongoClient mongoClient = new MongoClient("localhost", 27017);
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
		
		 Document doc = new Document("name", "MongoDB")
	                .append("type", "database")
	                .append("count", 1)
	                .append("versions", Arrays.asList("alex", "ric", "sergio"))
	                .append("info", new Document("x", 203).append("y", 102));
		 
		 collection.insertOne(doc);
	}
}
