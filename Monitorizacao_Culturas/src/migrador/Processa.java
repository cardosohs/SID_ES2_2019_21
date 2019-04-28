package migrador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import java.sql.Timestamp; 

public class Processa {

	static HashMap<Timestamp,Double> preparaTemperatura(List<Document> listaBruta) {
		HashMap<Timestamp,Double> listaLimpa = new HashMap<Timestamp,Double>();
		
		if (listaBruta.get(1).containsKey("tmp")) {
			for (Document doc : listaBruta) {
				Double temp = Double.parseDouble( (String) doc.get("tmp"));
				Timestamp momento = Timestamp.valueOf(transformaString((String) doc.get( "timestamp")));
			
				listaLimpa.put(momento,temp);
			}
		}
			 else {// se não for de temp é de luminusidade
					System.out.println("lista errada");
					
				}
		return listaLimpa;			 	
			}
			
			
	static HashMap<Timestamp,Integer> preparaLuz(List<Document> listaBruta) {
		HashMap<Timestamp,Integer> listaLimpa = new HashMap<Timestamp,Integer>();
		
		if (listaBruta.get(1).containsKey("lum")) {
			for (Document doc : listaBruta) {
				Integer lum = Integer.parseInt((String) doc.get("lum"));
				Timestamp momento = Timestamp.valueOf(transformaString((String) doc.get( "timestamp")));
			
				listaLimpa.put(momento,lum);
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


