package pt.iscte.sid.projeto.migrador;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.poi.poifs.crypt.dsig.services.TimeStampService;
import org.bson.BsonTimestamp;
import org.bson.Document;

public class MySQL {

	private static String mySQLdb = "g21origem";
	private static Connection conn;
	private static PreparedStatement psSeletTopLog;
	private static PreparedStatement psInsereTudo;
	// private static PreparedStatement

	static void liga() {

		try {
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://localhost/" + mySQLdb
					+ "?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
			//// localhost:3306/database?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC
			Class.forName(myDriver);
			conn = DriverManager.getConnection(myUrl, "root", "");
		} catch (Exception e) {
			System.out.println("erro na ligacao ao MySQL");
		}

		geraPreparedStatements();

	}

// gera as PS que podem ser usadas com esta ligacacao
	private static void geraPreparedStatements() {
		String queryInsereTudo = "insert into sensormigracaolog (momento,ordem,sensor,medicao,datetime_sensor)"
				+ " values (?,?,?,?,?)";

		// as PreparedStatement são variaveis globais, declaradas no inicio da classe
		try {

			psInsereTudo = conn.prepareStatement(queryInsereTudo);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao criar prepared Statement");
		}

	}

//obtem o log mais recente
	static ResultSet GetTopLog() {
		try {
			String querySeletTopLog = "SELECT * FROM `sensormigracaolog` ORDER by momento DESC, ordem DESC LIMIT 1";
			psSeletTopLog = conn.prepareStatement(querySeletTopLog);
			ResultSet rs = psSeletTopLog.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("erro ao obter log mais recente");
		}

		return null;

	}

	public static void escreveSensorMigracaoLog(ConcurrentHashMap<BsonTimestamp, Medicao> leituras) {
		for (Map.Entry<BsonTimestamp, Medicao> me : leituras.entrySet()) {
			Medicao medicao = me.getValue();
			String tipo = medicao.tipo;
			Timestamp tsMedicao = medicao.tsMedicao;
			Double valorMedicao = medicao.medicao;
			BsonTimestamp time_med = medicao.tsHoraGravacao;
			Timestamp ts = new Timestamp(time_med.getTime() * 1000L);
			Integer inc = time_med.getInc();
			try {

//"insert into sensormigracaolog (momento,ordem,sensor,medicao,datetime_sensor)"

				psInsereTudo.setTimestamp(1, ts);
				psInsereTudo.setInt(2, inc);
				psInsereTudo.setString(3, tipo);
				psInsereTudo.setDouble(4, valorMedicao);
				psInsereTudo.setTimestamp(5, tsMedicao);
				psInsereTudo.execute();
				System.out.println("inserido " + time_med.toString() + " do tipo " + tipo);
				//Apaga do mongo
			} catch (SQLException e) {
				System.out.println("Erro na PS insere Temperatura");
				e.printStackTrace();
			}

		}

	}



}
