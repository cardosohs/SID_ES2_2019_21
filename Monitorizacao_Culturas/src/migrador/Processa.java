package migrador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.bson.BsonTimestamp;
import org.bson.Document;
import java.sql.Timestamp; 

public class Processa {

	static ConcurrentHashMap<Timestamp,MedicaoTemperatura> preparaTemperatura(List<Document> listaBruta) {
		ConcurrentHashMap<Timestamp,MedicaoTemperatura> listaLimpa = new ConcurrentHashMap<Timestamp,MedicaoTemperatura>();
		
		//verifica se é mesmo uma lista de documentos de temperatura 
		// e cria um ConcurrentHashMap com as Medicoes
		if (listaBruta.get(1).containsKey("tmp")) {
			for (Document doc : listaBruta) {
				Double temperatura = Double.parseDouble( (String) doc.get("tmp"));
				Timestamp tsMedicao = Timestamp.valueOf(transformaString((String) doc.get( "timestamp")));
				
				BsonTimestamp tsHoraGravacao = (BsonTimestamp) doc.get("time_med");
				MedicaoTemperatura mt = new MedicaoTemperatura(tsMedicao,temperatura,tsHoraGravacao);
				listaLimpa.put(tsMedicao,mt);
			}
		}
			 else {// se não for de temp é de luminusidade
					System.out.println("lista errada");
					
				}
		return listaLimpa;			 	
			}
			
	//verifica se é mesmo uma lista de documentos de Luminusidade 
			// e cria um ConcurrentHashMap com as Medicoes		
	static ConcurrentHashMap<Timestamp, MedicaoLuz> preparaLuz(List<Document> listaBruta) {
		ConcurrentHashMap<Timestamp,MedicaoLuz> listaLimpa = new ConcurrentHashMap<Timestamp,MedicaoLuz>();
		
		if (listaBruta.get(1).containsKey("lum")) {
			for (Document doc : listaBruta) {
				Integer luminusidade = Integer.parseInt((String) doc.get("lum"));
				Timestamp tsMedicao = Timestamp.valueOf(transformaString((String) doc.get( "timestamp")));			
				BsonTimestamp tsHoraGravacao = (BsonTimestamp) doc.get("time_med");
				MedicaoLuz ml = new MedicaoLuz(tsMedicao,luminusidade,tsHoraGravacao);
				listaLimpa.put(tsMedicao,ml);
			}
		}
			 else {// se não for de temp é de luminusidade
					System.out.println("lista errada");
					
				}
		return listaLimpa;			 	
			}

	//transforma o timeStamp em yyyy-mm-dd hh:mm:ss
	private static String transformaString(String badstring) {
		String hora = (badstring.split(" "))[1];
		String data = (badstring.split(" "))[0];
		String dia = (data.split("/"))[0];
		String mes = (data.split("/"))[1];
		String ano = (data.split("/"))[2];
		String goodString = ano+"-"+mes+"-"+dia+" "+hora;
		return goodString;
	}



}


