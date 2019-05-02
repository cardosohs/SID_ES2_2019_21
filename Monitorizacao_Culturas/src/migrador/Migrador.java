package migrador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;
import org.bson.Document;

public class Migrador {

//	private final static String tabelaLuz= "medicoesluz";
//	private final static String tabelaTemperatura="medicoestemp";

	public static void main(String[] args) {
		// inicializacao de variaveis
		List<Document> leiturasLuz = new ArrayList<Document>();
		List<Document> leiturasTemperatura = new ArrayList<Document>();

		// estabelcer ligações
		MySQL.liga();
		MongoDb.liga();

		// tenta obter o último log e outros dados de inicializacao
		DadosInicializacao init = Inicializa.getInitData();

		// ler dados MongoDb
		if (init != null) {
			// ou pede os dados depois do último log
			leiturasLuz = MongoDb.le("lum", init.ultimoLog);
			leiturasTemperatura = MongoDb.le("tmp",init.ultimoLog);

		} else {
			// ou pede todos os dados
			leiturasLuz = MongoDb.le("lum");
			leiturasTemperatura = MongoDb.le("tmp");
		}
		// prepara os dados
		ConcurrentHashMap<Timestamp, MedicaoLuz> leiturasLuzProntas = Processa.preparaLuz(leiturasLuz);
		ConcurrentHashMap<Timestamp, MedicaoTemperatura> leiturasTemperaturaProntas = Processa
				.preparaTemperatura(leiturasTemperatura);

		// escrever dados no MySQL
		MySQL.escreveLuz(leiturasLuzProntas);
		MySQL.escreveTemperatura(leiturasTemperaturaProntas);

	}

}
