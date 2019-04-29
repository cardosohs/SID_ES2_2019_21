package pt.iscte.sid.projeto.ligacao1e2;

import com.mongodb.MongoClient;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;


import java.net.UnknownHostException;

public class MongoJavaRead {
	
	public static void main (String[] args) throws UnknownHostException {
		
		//Estabelece ligação com a MongoDB PRIMARIA
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		System.out.println("Connection established");
		
		//Request da DB "mydb"
		MongoDatabase database = mongoClient.getDatabase("mydb");
		
		//Request de TODAS as coleccoes designadas "test" da DB "mydb"
		MongoCollection<Document> collection = database.getCollection("MedicoesSensor");
		
		//Imprime todos os elementos de uma coleção
		MongoCursor<Document> cursor = collection.find().iterator();
		try {
		    while (cursor.hasNext()) {
		        System.out.println(cursor.next().toJson());
		    }
		} finally {
		    cursor.close();
		}
		
		//Conta todos os elementos de uma colecção e imprime-os na consola
		System.out.println("Num de elementos na coleção: " + collection.count());
		
		//Encontra o 1º elemento de uma coleção
		Document myDoc = collection.find().first();
		System.out.println("O primeiro elemento é " + myDoc.toJson());
		
		//Obter todos os elementos de uma colecao cujo campo "i" tenham o valor "71"
		myDoc = collection.find(eq("i", 71)).first();
		System.out.println(myDoc.toJson());
		
		
		//Obter todos os elementos de uma colecao cujo campo "i" seja < 50
		Block<Document> printBlock = new Block<Document>() {
		     @Override
		     public void apply(final Document document) {
		         System.out.println(document.toJson());
		     }
		};
		collection.find(gt("i", 50)).forEach(printBlock);
		
		//Obter todos os elementos de uma coleção cujo campo "i" esteja entre 50 e 100
		collection.find(and(gt("i", 50), lte("i", 100))).forEach(printBlock);
		
	}
}
