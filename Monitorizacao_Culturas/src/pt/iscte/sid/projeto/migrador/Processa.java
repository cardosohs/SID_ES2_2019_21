package pt.iscte.sid.projeto.migrador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.bson.BsonTimestamp;
import org.bson.Document;
import java.sql.Timestamp; 

public class Processa {

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
//prepara os dados recebidos do mongo para serem inseridos na MySQL
	public static ConcurrentHashMap<BsonTimestamp, Medicao> prepara(List<Document> listaBruta) {
ConcurrentHashMap<BsonTimestamp,Medicao> listaLimpa = new ConcurrentHashMap<BsonTimestamp,Medicao>();
		
		String tipo;
		Double medicao;
			for (Document doc : listaBruta) {
				if (doc.containsKey("cell")) {
					 tipo = "cell";
					 
					 medicao= Double.parseDouble((String) doc.get("cell"));
					 
				}
				else {
					 tipo = "tmp";
			
					 medicao= Double.parseDouble((String) doc.get("tmp"));
			
				}
				Timestamp tsMedicao = Timestamp.valueOf(transformaString((String) doc.get( "timestamp")));			
				//correcao das diferencas horarias
				tsMedicao.setTime( tsMedicao.getTime() + TimeUnit.HOURS.toMillis(2));
				BsonTimestamp tsHoraGravacao = (BsonTimestamp) doc.get("time_med");
				Medicao ml = new Medicao(tipo,medicao,tsMedicao,tsHoraGravacao);
				listaLimpa.put(tsHoraGravacao,ml);
				//o documento depois de processado e eliminado do mongoDB
				MongoDb.apaga(doc);
			}
	
			 
		return listaLimpa;		
	}
}


