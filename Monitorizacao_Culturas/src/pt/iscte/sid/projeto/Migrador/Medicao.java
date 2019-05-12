/**
 * 
 */
package pt.iscte.sid.projeto.Migrador;

import java.sql.Timestamp;

import org.bson.BsonTimestamp;

/**
 * SuperClasse das medições
 *
 */
public class Medicao {
	
	//atributos comuns 
	
	BsonTimestamp tsHoraGravacao;
	String tipo; // tipo da medicao (tmp or lum)
	Double medicao; //valor da medicao
	Timestamp tsMedicao; //hora da medicao
	
	public Medicao(String tipo2, Double medicao2, Timestamp tsMedicao2, BsonTimestamp tsHoraGravacao2) {
		this.tipo=tipo2;
		this.medicao=medicao2;
		this.tsMedicao=tsMedicao2;
		this.tsHoraGravacao=tsHoraGravacao2;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	public Double getMedicao() {
		return medicao;
	}
	public void setMedicao(Double medicao) {
		this.medicao = medicao;
	}
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
