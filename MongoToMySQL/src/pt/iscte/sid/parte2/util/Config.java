package pt.iscte.sid.parte2.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Config {

	String currentFolder = System.getProperty("user.dir");
	
	String mySqlUser;
	String mySqlPass;
	String mySqlDbName;
	
	String mongoIp;
	String mongoPort;
	String mongoUser;
	String mongoPass;
	
	String periodicity;
	
	public void loadParameters() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser(); 
		JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(currentFolder + "\\settings.conf"));

		JSONObject mysql = (JSONObject) jsonObject.get("mysql");		
		mySqlUser = (String) mysql.get("user");
		mySqlPass = (String) mysql.get("password");
		mySqlDbName = (String) mysql.get("databasename");
		
		
		JSONObject mongo = (JSONObject) jsonObject.get("mongodb");
		mongoIp = (String) mongo.get("ip_server");
		mongoPort = (String) mongo.get("port");
		mongoUser = (String) mongo.get("user");
		mongoPass = (String) mongo.get("password");
		
		periodicity = (String) jsonObject.get("periodicity");
	}

	public String getCurrentFolder() {
		return currentFolder;
	}	

	public String getMySqlUser() {
		return mySqlUser;
	}

	public String getMySqlPass() {
		return mySqlPass;
	}	

	public String getMySqlDbName() {
		return mySqlDbName;
	}

	public String getMongoIp() {
		return mongoIp;
	}

	public String getMongoPort() {
		return mongoPort;
	}

	public String getMongoUser() {
		return mongoUser;
	}

	public String getMongoPass() {
		return mongoPass;
	}

	public String getPeriodicity() {
		return periodicity;
	}
	
}
