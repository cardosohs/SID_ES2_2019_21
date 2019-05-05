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
//	private static PreparedStatement psInsereLuz;
//	private static PreparedStatement psInsereTemperatura;
//	private static PreparedStatement psInsereLog;
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
//		String queryInsereLuz = "insert into medicoesluz (DataHoraMed,ValorMedicaoLuz)" + " values (?,?)";
//		String queryInsereTemperatura = "insert into medicoestemp (DataHoraMed,ValorMedicaoTemp)" + " values (?,?)";
//		String queryInsereLog = "insert into sensormigracaolog (momento,ordem)" + " values (?,?)";
		String queryInsereTudo = "insert into sensormigracaolog (momento,ordem,sensor,medicao,datetime_sensor)"
				+ " values (?,?,?,?,?)";

		// as PreparedStatement são variaveis globais, declaradas no inicio da classe
		try {
//			psInsereLuz = conn.prepareStatement(queryInsereLuz);
//			psInsereTemperatura = conn.prepareStatement(queryInsereTemperatura);
//			psInsereLog = conn.prepareStatement(queryInsereLog);
			psInsereTudo = conn.prepareStatement(queryInsereTudo);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao criar prepared Statement");
		}

	}

//insere dados na tabela de Mysql de leituras de Luminusidade
//	public static void escreveLuz(ConcurrentHashMap<Timestamp, MedicaoLuz> leituras) {
//		for (Map.Entry<Timestamp, MedicaoLuz> me : leituras.entrySet()) {
//			MedicaoLuz medicao = me.getValue();
////	 Timestamp tsMedicao = medicao.tsMedicao;
//			Integer valorMedicao = medicao.medicao;
//			BsonTimestamp time_med = medicao.tsHoraGravacao;
//			Timestamp ts = new Timestamp(time_med.getTime() * 1000L);
////	 Instant ts = Instant.ofEpochSecond(time_med.getTime());
////	 Time ts = (Time) Time.from(Instant.ofEpochSecond(time_med.getTime()));
//			Integer inc = time_med.getInc();
//
//			try { // inserir medicao
//				psInsereLuz.setTimestamp(1, (Timestamp) me.getKey());
//				psInsereLuz.setInt(2, valorMedicao);
//				psInsereLuz.execute();
//				try {// inserir o log
//					psInsereLog.setTimestamp(1, ts);
//					psInsereLog.setInt(2, inc);
//					psInsereLog.execute();
//				} catch (SQLException e) {
//					System.out.println("Erro ao inserir log");
//				}
//
//			} catch (SQLException e) {
//				System.out.println("Erro na PS insere Luz");
//				System.out.println(e.toString());
//			}
//
//		}
//
//	}

//Tenta escrever os valores na tabela de temperatura
//	public static void escreveTemperatura(ConcurrentHashMap<Timestamp, MedicaoTemperatura> Leituras) {
//		for (Map.Entry<Timestamp, MedicaoTemperatura> me : Leituras.entrySet()) {
//			MedicaoTemperatura medicao = me.getValue();
////		 Timestamp tsMedicao = medicao.tsMedicao;
//			Double valorMedicao = medicao.medicao;
//			BsonTimestamp time_med = medicao.tsHoraGravacao;
//			Timestamp ts = new Timestamp(time_med.getTime() * 1000L);
//			Integer inc = time_med.getInc();
//			try {
//				psInsereTemperatura.setTimestamp(1, (Timestamp) me.getKey());
//				psInsereTemperatura.setDouble(2, valorMedicao);
//				psInsereTemperatura.execute();
//				try {// inserir o log
//					psInsereLog.setTimestamp(1, ts);
//					psInsereLog.setInt(2, inc);
//					psInsereLog.execute();
//				} catch (SQLException e) {
//					System.out.println("Erro ao inserir log");
//				}
//
//			} catch (SQLException e) {
//				System.out.println("Erro na PS insere Temperatura");
//			}
//
//		}
//	}

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
				System.out.println("inserido "+time_med.toString()+" do tipo "+tipo);
			} catch (SQLException e) {
				System.out.println("Erro na PS insere Temperatura");
			}

		}

	}

	// obtem dados de inicialização

//	DadosInicializacao getInitData() {
//		ResultSet Top10Luz=null;
//		ResultSet Top10Temperatura=null;
//		
//		// se houver um log
//		ResultSet rs = MySQL.GetTopLog();
//		if (rs != null) {
//			// pede as 10 entradas mais recentes
//			String stSelectTop10Luz = "SELECT ValorMedicaoLuz, datahoraMed from medicoesluz ORDER by DataHoraMed desc limit 10";
//			"SELECT std(ValorMedicaoLuz) from ( SELECT ValorMedicaoLuz FROM `medicoesluz` ORDER by DataHoraMed limit 10 ) as desvio"
//			try {
//				PreparedStatement psSelectTop10Luz = conn.prepareStatement(stSelectTop10Luz);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			Top10Luz= psSelectTop10Luz.execute();
//			
//			
//			
//			// Pede o desvio Padrão e das 10 entradas mais recentes o valor medido da última
//			// entrada
//			// da tabela de luz
//			// da tabela de temperatura
//			
////////			String stGetDevPadraoLuz;
//////			String stGetUltMedLuz;
////////			String stetDevPadraoTemperatura;
//////			String stGetUltMedTemperatura;	
////			
////			PreparedStatement psGetDevPadraoLuz;
////			PreparedStatement psGetUltMedLuz;
////			PreparedStatement psGetDevPadraoTemperatura;
////			PreparedStatement psGetUltMedTemperatura;
//
//			
//			
//			// Pede o desvio Padrão e das 10 entradas mais recentes o valor medido da última
//			// entrada
//			// da tabela de luz
//			// da tabela de temperatura
//
//			// passa o objecto de inicialização para o Migrador
//		}
//
//		return null;
//
//	}

}
