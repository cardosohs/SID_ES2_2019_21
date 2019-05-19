package pt.iscte.sid.parte2.connections;


import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


import pt.iscte.sid.parte2.util.Config;
import pt.iscte.sid.parte2.util.Log;
import pt.iscte.sid.parte2.util.TypeLog;

public class MongoConn{
	
	@SuppressWarnings("unused")
	private Log logFile;
	MongoCursor<Document> cursor;
	MongoCursor<Document> cursorTmp;
	private String dataReadyforMysql = "";
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> collection;	
	
	public MongoConn(Config confFile, Log logFile) throws IOException {
		this.logFile = logFile;
		
		//ligar mongo		
		/*MongoClientURI connectionString = new MongoClientURI(
				"mongodb://" + confFile.getMongoIp() + ":" + confFile.getMongoPort());
		MongoClient mongoClient = new MongoClient(connectionString);*/
		mongoClient = MongoClients.create("mongodb://localhost:27017,localhost:25017,localhost:23017/?replicaSet=BDMedSensor");
		
		logFile.log("Connected to mongoDb", TypeLog.NORMAL);
		
		//obter bd

		database = mongoClient.getDatabase("sensorbd");
		logFile.log("Connected to database 'sensorbd'", TypeLog.NORMAL);

		collection = database.getCollection("MedicoesSensor");
		logFile.log("Connected to collection 'MigracaoSensor'", TypeLog.NORMAL);

	}

	
	public void getDataFromMongoDb() {
		cursor = collection.find().iterator();
		cursorTmp = collection.find().iterator();
		System.out.println( collection.countDocuments() + " documents to migrate/delete.");
	}

	@SuppressWarnings("finally")
	public String prepareDataToMySql() throws ParseException {
		try {
			
			dataReadyforMysql = "";
			String sensor = "";
			String medicao = "";			
			String datetime_sensor = "";
			
			while (cursor.hasNext()) {

				String next = cursor.next().toJson();

				JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(next);
				
				if ((String) json.get("cell") == null) {
					sensor = "tmp";
					medicao = (String) json.get("tmp");
					datetime_sensor = (String) json.get("timestamp");				
					
					if (!medicao.equals("null")) medicao = "'" + medicao + "'";
	
					String timstamp = dateToTimeStamp(datetime_sensor);
					
					dataReadyforMysql += "('" + sensor + "', " + medicao + ", '" + timstamp + "'),";
				}else{
					sensor = "cell";
					medicao = (String) json.get("cell");
					datetime_sensor = (String) json.get("timestamp");				
					
					if (!medicao.equals("null")) medicao = "'" + medicao + "'";
	
					String timstamp = dateToTimeStamp(datetime_sensor);
					
					dataReadyforMysql += "('" + sensor + "', " + medicao + ", '" + timstamp + "'), ";
					
				}
			}
						
		} finally {
			
			if(!dataReadyforMysql.equals("")) 
				dataReadyforMysql = dataReadyforMysql.substring(0, dataReadyforMysql.length() - 1);
			if(dataReadyforMysql.endsWith(","))
			{
			  dataReadyforMysql = dataReadyforMysql.substring(0,dataReadyforMysql.length() - 1);
			}
			return dataReadyforMysql;
			
		}

	}
	
	private String dateToTimeStamp(String date) throws java.text.ParseException {
		try {
			
			String OLD_FORMAT = "dd/MM/yyyy HH:mm:ss";
			String NEW_FORMAT = "yyyy-MM-dd HH:mm:ss";

			String oldDateString = date;
			
			//@SuppressWarnings("unused")
			String newDateString;

			SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
			
			java.util.Date utilStartDate = sdf.parse(oldDateString);
			
			java.util.Date plusOneHour = new Date(utilStartDate.getTime() + 1 * (3600 * 1000));
					
			java.sql.Date sqlStartDate = new java.sql.Date(plusOneHour.getTime());
			
			sdf.applyPattern(NEW_FORMAT);
			newDateString = sdf.format(sqlStartDate);
			
			Timestamp ts = new Timestamp(((java.util.Date)sdf.parse(newDateString)).getTime());
			
			//Código martelado
//			Date date1= new Date();
//			long time = date1.getTime();
//			Timestamp ts = new Timestamp(time);
		
			return ts.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
		
	}

	public void deleteAllInCollection() {
		
		while (cursorTmp.hasNext()) {
			collection.deleteOne(cursorTmp.next());
		}
		
		cursorTmp.close();
		
	}
}
