package pt.iscte.sid.sensor.connections;

import org.bson.Document;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;



public class MongoCon {
	
	private String dbName = "sensorbd";
	private String colName = "MedicoesSensor";
	private Boolean isMongoAlive = false; // flag para determinar se a coneção existe
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> collection;
	
	
	/**
	 * Implementação de uma singleton pattern para garantir que só existe 1 instância a correr
	 */
	private MongoCon() {};
	
	private static class MongoConHelper{
		private static final MongoCon INSTANCE = new MongoCon();
	}
	
	public static MongoCon getInstance() {
		return MongoConHelper.INSTANCE;
	}
	
	
	public void connectMongoBd() {
		mongoClient = MongoClients.create("mongodb://localhost");
		//mongoClient = new MongoClient("localhost", 27017);
		//System.out.println("Connection established");
		//Request da DB
		database = mongoClient.getDatabase(dbName);
		//Request da colecao
		collection = database.getCollection(colName);				
	}
	
	public Boolean getIsMongoAlive() {
		return isMongoAlive;
	}

	public void setIsMongoAlive(Boolean isMongoAlive) {
		this.isMongoAlive = isMongoAlive;
	}


	public MongoClient getMongoClient() {
		return mongoClient;
	}


	public MongoDatabase getDatabase() {
		return database;
	}


	public MongoCollection<Document> getCollection() {
		return collection;
	}
	
	


}
