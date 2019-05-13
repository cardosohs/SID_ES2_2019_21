package pt.iscte.sid.projeto.Migrador;

import java.sql.Timestamp;

import org.bson.BsonTimestamp;

public class MedicaoTemperatura extends Medicao {

	public MedicaoTemperatura(String tipo2, Double medicao2, Timestamp tsMedicao2, BsonTimestamp tsHoraGravacao2) {
		super(tipo2, medicao2, tsMedicao2, tsHoraGravacao2);
		// TODO Auto-generated constructor stub
	}

	Double medicao;
	
//	public MedicaoTemperatura() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public MedicaoTemperatura(Timestamp tsMedicao, Double temperatura, BsonTimestamp tsHoraGravacao) {
//		this.medicao=temperatura;
//		super.tsMedicao=tsMedicao;
//		super.tsHoraGravacao=tsHoraGravacao;
//	}

	public Double getMedicao() {
		return medicao;
	}

	public void setMedicao(Double medicao) {
		this.medicao = medicao;
	}

}
