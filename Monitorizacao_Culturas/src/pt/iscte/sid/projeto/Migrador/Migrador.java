package pt.iscte.sid.projeto.Migrador;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.bson.BsonTimestamp;
import org.bson.Document;

public class Migrador {

//	private final static String tabelaLuz= "medicoesluz";
//	private final static String tabelaTemperatura="medicoestemp";

	public static void main(String[] args) {

		// inicializacao de variaveis
		List<Document> leituras = new ArrayList<Document>();

		// estabelcer ligações
		MySQL.liga();
		MongoDb.liga();

		while (true) {
		// tenta obter o último log e outros dados de inicializacao
//		DadosInicializacao init = null;
//		try {
//			init = Inicializa.getInitData();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
			// ler dados MongoDb
		// pede todos os dados
		leituras = MongoDb.le();
		System.out.println("lidos do mongo:" + leituras.size() +" documentos");
//		
//			if (init != null) {
//				// ou pede os dados depois do último log
//				leituras = MongoDb.le(init.ultimoLog);
//
//			} else {
//				// ou pede todos os dados
//				leituras = MongoDb.le();
//			}
			// prepara os dados
			ConcurrentHashMap<BsonTimestamp, Medicao> leiturasFormatadas = Processa.prepara(leituras);

			// escrever dados no MySQL
			MySQL.escreveSensorMigracaoLog(leiturasFormatadas);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Acordou do Slepp");
				e.printStackTrace();
			}

		}
	}

}
