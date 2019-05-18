package pt.iscte.sid.sensor.connections;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.MongoCredential;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;

import pt.isce.sid.sensor.utils.Log;
import pt.isce.sid.sensor.utils.TypeLog;

public class MongoCon {

    private String dbName = "sensorbd";
    private String colName = "MedicoesSensor";
    private Boolean isMongoAlive = false; // flag para determinar se a coneção existe
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private MongoCredential credential;
    private String user = "";
    private String password = "";
    

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


    public void connectMongoBd(Log logFile) throws IOException {
    	credential = MongoCredential.createPlainCredential(user, dbName, password.toCharArray());
        
        mongoClient = MongoClients.create("mongodb://localhost:27017,localhost:25017,localhost:23017/?replicaSet=BDMedSensor");
        logFile.log("Connected to mongoDb", TypeLog.NORMAL);        
      
        database = mongoClient.getDatabase(dbName);
        logFile.log("Connected to database 'sensorbd'", TypeLog.NORMAL);
     
        collection = database.getCollection(colName);
        logFile.log("Connected to collection 'MedicoesSensor'", TypeLog.NORMAL);
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


