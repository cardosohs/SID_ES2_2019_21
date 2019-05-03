package pt.iscte.sid.projeto.migrador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;
import org.bson.Document;



public class Migrador {

//	private final static String tabelaLuz= "medicoesluz";
//	private final static String tabelaTemperatura="medicoestemp";
	
	public static void main(String[] args) {
		List<Document> leiturasLuz = new ArrayList<Document>();
		List<Document> leiturasTemperatura = new ArrayList<Document>();

		// estabelcer ligações
		MySQL.liga();
		MongoDb.liga();

		// ler dados MongoDb
		leiturasLuz = MongoDb.le("lum");
		leiturasTemperatura = MongoDb.le("tmp");
		
		// prepara os dados
		HashMap<Timestamp,Integer> leiturasLuzProntas = Processa.preparaLuz(leiturasLuz);
		HashMap<Timestamp,Double> leiturasTemperaturaProntas = Processa.preparaTemperatura(leiturasTemperatura);
		
		// escrever dados no MySQL
		MySQL.escreveLuz(leiturasLuzProntas);
		MySQL.escreveTemperatura(leiturasTemperaturaProntas);

	}

	

}
