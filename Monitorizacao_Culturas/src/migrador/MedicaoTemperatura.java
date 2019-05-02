package migrador;

import java.sql.Timestamp;

import org.bson.BsonTimestamp;

public class MedicaoTemperatura extends Medicao {

	Double medicao;
	
	public MedicaoTemperatura() {
		// TODO Auto-generated constructor stub
	}

	public MedicaoTemperatura(Timestamp tsMedicao, Double temperatura, BsonTimestamp tsHoraGravacao) {
		this.medicao=temperatura;
		super.tsMedicao=tsMedicao;
		super.tsHoraGravacao=tsHoraGravacao;
	}

	public Double getMedicao() {
		return medicao;
	}

	public void setMedicao(Double medicao) {
		this.medicao = medicao;
	}

}
