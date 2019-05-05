package pt.iscte.sid.projeto.migrador;

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

	//pede todos os dados de um tipo 
//	public static List<Document> le(String tipo) {
//		List<Document> leituras = new ArrayList<Document>();
//		// Imprime os elementos do tipo <tipo> de uma coleção
//		Bson query = new BasicDBObject(tipo, new BasicDBObject("$exists", true));
//		MongoCursor<Document> cursor = collection.find(query).iterator();
//		
//		try {
//			while (cursor.hasNext()) {
//				leituras.add(cursor.next());
////				System.out.println( leituras.get(leituras.size()-1).toJson());
//			}
//		} finally {
//			cursor.close();
//		}
//		return leituras;
//	}

	//pede os dados de um tipo após uma data
//	public static List<Document> le(String tipo, BsonTimestamp bts) {
//		List<Document> leituras = new ArrayList<Document>();
//		// Imprime os elementos do tipo <tipo> de uma coleção
////		DBObject query = new BasicDBObject(tipo, new BasicDBObject("$exists", true));
//		Bson queryTime = new BasicDBObject("time_med", new BasicDBObject("$gt", bts)).append(tipo, new BasicDBObject("$exists", true));
//		//query.put("$and",queryTime);
//		MongoCursor<Document> cursor = collection.find(queryTime).iterator();
//		
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
	
	//obtem todas as colecções após um BsonTimeStamp
	public static List<Document> le(BsonTimestamp ultimoLog) {
		List<Document> leituras = new ArrayList<Document>();
		// Imprime todos os elementos de uma coleção após data de último Log
		Bson queryTime = new BasicDBObject("time_med", new BasicDBObject("$gt", ultimoLog));
		MongoCursor<Document> cursor = collection.find(queryTime).iterator();
		try {
			while (cursor.hasNext()) {
				leituras.add(cursor.next());
				System.out.println( leituras.get(leituras.size()-1).toJson());
			}
		} finally {
			cursor.close();
		}
		return leituras;
	}
	
	//obtem todas as colecções após um BsonTimeStamp
	public static List<Document> le() {
		List<Document> leituras = new ArrayList<Document>();
		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while (cursor.hasNext()) {
				leituras.add(cursor.next());
				System.out.println( leituras.get(leituras.size()-1).toJson());
			}
		} finally {
			cursor.close();
		}
		return leituras;
	}
	
	
	
	
	//apaga dados
	public static boolean apaga(List<Document> leituras) {
		boolean resultado = false;
		// apaga as leituras em <leituras>
		return resultado;
	}



}
