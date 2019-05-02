package migrador;

import java.sql.Timestamp;

import org.bson.BsonTimestamp;

public class MedicaoLuz extends Medicao {

	Integer medicao;
	
	public MedicaoLuz() {
		
	}

	

	MedicaoLuz(Timestamp tsMedicao, Integer luminusidade, BsonTimestamp tsHoraGravacao) {
		this.medicao=luminusidade;
		super.tsMedicao=tsMedicao;
		super.tsHoraGravacao=tsHoraGravacao;
	}



	public Integer getMedicao() {
		return medicao;
	}

	public void setMedicao(Integer medicaoLuz) {
		this.medicao = medicaoLuz;
	}

}
