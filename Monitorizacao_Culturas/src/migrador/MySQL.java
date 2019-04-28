package migrador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;

public class MySQL {
	
	private static String mySQLdb="g21origem"; 	
	private static	Connection conn ;
	
static void liga() {
		
		try {
		String myDriver = "com.mysql.cj.jdbc.Driver";
    	String myUrl = "jdbc:mysql://localhost/"+mySQLdb+"?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
      ////localhost:3306/database?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC
    	Class.forName(myDriver);
    	 conn = DriverManager.getConnection(myUrl, "root", "");
		}
		catch (Exception e){
			System.out.println("erro na ligacao ao MySQL");
		}
		
	}

public static void escreveLuz(HashMap<Timestamp,Integer> Leituras) {
//	for (Document lido : leituras) {
//		System.out.println(lido.toJson());
	//na tabela de luminusidade
	//na tabela de temperatura
	//na tabela de logs
	//na tabela de alarmes
//	}
	
	
}

public static void escreveTemperatura(HashMap<Timestamp,Double> Leituras) {
	//
	
}


}
