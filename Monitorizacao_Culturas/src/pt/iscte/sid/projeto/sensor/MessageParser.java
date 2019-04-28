package pt.iscte.sid.projeto.sensor;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class MessageParser {
	
	public static String [] parse (String fullMessage) {
		
		String[] valores = new String[3];
		
		//Insere a vírgula que faltava
		String treated = fullMessage.replace("\"\"", "\",\"");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String data="NULL";
		String hora="NULL";
		String temp="NULL";
		String lum="NULL";
		
		JsonNode jsonNode;
		try {
			jsonNode = objectMapper.readTree(treated);
			data = jsonNode.get("dat").asText();
			hora = jsonNode.get("tim").asText();
			temp = jsonNode.get("tmp").asText();
			lum = jsonNode.get("cell").asText();
		} catch (JsonProcessingException e) {
			System.out.println("erro ao processar mensagem Valor colocado a NULL");
		} catch (IOException e) {
			System.out.println("erro de IO");
			e.printStackTrace();
		}
	
		valores[0]=data+" "+hora;
		valores[1]=temp;
		valores[2]=lum;
		
		return valores;
	}

}
