package pt.iscte.sid.projeto.Migrador;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.bson.BsonTimestamp;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDb {
	private static String dbName = "sensorbd";
	static String colName = "MedicoesSensor";
	static MongoCollection<Document> collection;

	//estabelece a ligacao a Mongo
	static void liga() {

		try {
			// Estabelece ligação com a MongoDB PRIMARIA
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			System.out.println("Connection established");

			// Request da DB
			MongoDatabase database = mongoClient.getDatabase(dbName);

			// Request da coleção
			collection = database.getCollection(colName);
		} catch (Exception e) {
			System.out.println("erro na ligacao ao MongoDB");
		}

	}

	
	
	//obtem todas as colecções após um BsonTimeStamp
//	public static List<Document> le(BsonTimestamp ultimoLog) {
//		List<Document> leituras = new ArrayList<Document>();
//		// Imprime todos os elementos de uma coleção após data de último Log
//		Bson queryTime = new BasicDBObject("time_med", new BasicDBObject("$gt", ultimoLog));
//		MongoCursor<Document> cursor = collection.find(queryTime).iterator();
//		try {
//			while (cursor.hasNext()) {
//				leituras.add(cursor.next());
//				System.out.println( leituras.get(leituras.size()-1).toJson());
//			}
//		} finally {
//			cursor.close();
//		}
//		return leituras;
//	}
	
	//obtem todas as colecções 
	public static List<Document> le() {
		List<Document> leituras = new ArrayList<Document>();
		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while (cursor.hasNext()) {
				leituras.add(cursor.next());
				//System.out.println( leituras.get(leituras.size()-1).toJson());
			}
		} finally {
			cursor.close();
		}
		return leituras;
	}
	
	
	
	
	//apaga dados
	public static void apaga(Document doc) {
	
		collection.deleteOne(doc);
		
		
	}

}
