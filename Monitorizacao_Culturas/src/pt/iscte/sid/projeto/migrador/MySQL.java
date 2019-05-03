package pt.iscte.sid.projeto.migrador;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

public class MySQL {
	
	private static String mySQLdb="g21origem"; 	
	private static	Connection conn ;
	private static PreparedStatement psInsereLuz;
	private static PreparedStatement psInsereTemperatura;
	private static PreparedStatement psInsereLog;
	//private static PreparedStatement
	
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
		
		geraPreparedStatements(); 

	}

// gera as PS que podem ser usadas com esta ligacacao
private static void geraPreparedStatements() {
	String queryInsereLuz = "insert into medicoesluz (DataHoraMed,ValorMedicaoLuz)" + " values (?,?)";
	String queryInsereTemperatura = "insert into medicoestemp (DataHoraMed,ValorMedicaoTemp)" + " values (?,?)";
	String queryInsereLog="insert into sensormigracaolog (momento,ordem)" + " values (?,?)";
	
	//as PreparedStatement  são variaveis globais, declaradas no inicio da classe
	try {
		psInsereLuz=conn.prepareStatement(queryInsereLuz);
		psInsereTemperatura=conn.prepareStatement(queryInsereTemperatura);
		psInsereLog=conn.prepareStatement(queryInsereLog);
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("Erro ao criar prepared Statement");
	}
	

	
}

//insere dados na tabela de Mysql de leituras de Luminusidade
public static void escreveLuz(HashMap<Timestamp,Integer> Leituras) {
 for (Map.Entry me : Leituras.entrySet()) {
	try {
		psInsereLuz.setTimestamp(1, (Timestamp) me.getKey());
		psInsereLuz.setInt(2, (int) me.getValue());
		psInsereLuz.execute(); 
		
	} catch (SQLException e) {
		System.out.println("Erro na PS insere Luz");
		System.out.println(e.toString());
	}
	
 }
	
}

public static void escreveTemperatura(HashMap<Timestamp,Double> Leituras) {
	 for (Map.Entry me : Leituras.entrySet()) {
			try {
				psInsereTemperatura.setTimestamp(1, (Timestamp) me.getKey());
				psInsereTemperatura.setDouble(2, (Double) me.getValue());
				psInsereTemperatura.execute(); 
				
			} catch (SQLException e) {
				System.out.println("Erro na PS insere Temperatura");
			}
			
		 }
}


}
