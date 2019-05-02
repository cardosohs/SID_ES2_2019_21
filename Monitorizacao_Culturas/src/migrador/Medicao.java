/**
 * 
 */
package migrador;

import java.sql.Timestamp;

import org.bson.BsonTimestamp;

/**
 * SuperClasse das medições
 *
 */
public class Medicao {
	
	//atributos comuns 
	Timestamp tsMedicao;
	BsonTimestamp tsHoraGravacao;
	
	public Timestamp getTsMedicao() {
		return tsMedicao;
	}
	public void setTsMedicao(Timestamp tsMedicao) {
		this.tsMedicao = tsMedicao;
	}
	public BsonTimestamp getTsHoraGravacao() {
		return tsHoraGravacao;
	}
	public void setTsHoraGravacao(BsonTimestamp tsHoraGravacao) {
		this.tsHoraGravacao = tsHoraGravacao;
	}

}
