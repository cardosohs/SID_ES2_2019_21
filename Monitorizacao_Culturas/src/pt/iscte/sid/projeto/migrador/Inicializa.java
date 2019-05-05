package pt.iscte.sid.projeto.migrador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.bson.BsonTimestamp;

public class Inicializa {
	
	 static DadosInicializacao getInitData() throws SQLException {
	//verifica se há dados na tabela sensormigracaolog
	//e  obtem a última inserção
	ResultSet rs = MySQL.GetTopLog();
	if (rs.first()) {
		//transforma os dados para serem enviados para o MongoDB
		BsonTimestamp bts = transformaEmBson(rs);

			//passa o objecto de inicialização para o Migrador
		return new DadosInicializacao(bts);
		
	}
	
	return null;
	
	}

	private static BsonTimestamp transformaEmBson(ResultSet rs) {
		Timestamp ts;
		Integer seconds=null;
		Integer increment= null;
		
		try {
			//rs.next();
			ts = rs.getTimestamp("momento");
			 seconds = (int) (ts.getTime()/1000L)-10;//-10 para testes
			increment = rs.getInt("ordem");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BsonTimestamp bts = new BsonTimestamp   (seconds, increment);
		bts.toString();
		return bts;
	}

}
