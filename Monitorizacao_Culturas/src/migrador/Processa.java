package migrador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import java.sql.Timestamp; 

public class Processa {

	static HashMap<Timestamp,Double> preparaTemperatura(List<Document> listaBruta) {
		HashMap<Timestamp,Double> listaLimpa = new HashMap<Timestamp,Double>();
		
		if (listaBruta.get(1).containsKey("temp")) {
			for (Document doc : listaBruta) {
				Double temp = Double.parseDouble( (String) doc.get("temp"));
				Timestamp momento = Timestamp.valueOf((String) doc.get( "timestamp"));
			
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
				Timestamp momento = Timestamp.valueOf((String) doc.get( "timestamp"));
			
				listaLimpa.put(momento,lum);
			}
		}
			 else {// se não for de temp é de luminusidade
					System.out.println("lista errada");
					
				}
		return listaLimpa;			 	
			}


}


