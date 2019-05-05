package pt.iscte.sid.projeto.migrador;

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
//		List<Document> leiturasLuz = new ArrayList<Document>();
//		List<Document> leiturasTemperatura = new ArrayList<Document>();
		List<Document> leituras = new ArrayList<Document>();

		// estabelcer ligações
		MySQL.liga();
		MongoDb.liga();

		// tenta obter o último log e outros dados de inicializacao
		DadosInicializacao init=null;
		try {
			init = Inicializa.getInitData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ler dados MongoDb
		if (init != null) {
			// ou pede os dados depois do último log
//			leiturasLuz = MongoDb.le("lum", init.ultimoLog);
//			leiturasTemperatura = MongoDb.le("tmp",init.ultimoLog);
			leituras = MongoDb.le(init.ultimoLog);

		} else {
			// ou pede todos os dados
//			leiturasLuz = MongoDb.le("lum");
//			leiturasTemperatura = MongoDb.le("tmp");
			leituras = MongoDb.le();
		}
		// prepara os dados
//		ConcurrentHashMap<Timestamp, MedicaoLuz> leiturasLuzProntas = Processa.preparaLuz(leiturasLuz);
//		ConcurrentHashMap<Timestamp, MedicaoTemperatura> leiturasTemperaturaProntas = Processa.preparaTemperatura(leiturasTemperatura);
		ConcurrentHashMap<BsonTimestamp, Medicao> leiturasTodas = Processa.prepara(leituras);
		
		// escrever dados no MySQL
//		MySQL.escreveLuz(leiturasLuzProntas);
//		MySQL.escreveTemperatura(leiturasTemperaturaProntas);
		MySQL.escreveSensorMigracaoLog(leiturasTodas);
		
	}

}
