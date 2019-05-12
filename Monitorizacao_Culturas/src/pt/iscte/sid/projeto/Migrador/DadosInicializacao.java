package pt.iscte.sid.projeto.Migrador;

import org.bson.BsonTimestamp;

public class DadosInicializacao {
	// da tabela de luz
	// desvio Padrão e das 10 entradas
	// mais recentes o valor medido da última entrada
	Double desvioPadraoLuz;
	Integer ultimaMedicaoLuz;

	// da tabela de temperatura
	// desvio Padrão e das 10 entradas
	// mais recentes o valor medido da última entrada
	Double desvioPadraoTemperatura;
	Double ultimaMedicaoTemperatura;

	BsonTimestamp ultimoLog;

	DadosInicializacao(Double dpl, Integer uml, Double dpt, Double umt, BsonTimestamp ul) {
		this.desvioPadraoLuz = dpl;
		this.ultimaMedicaoLuz = uml;
		this.desvioPadraoTemperatura = dpt;
		this.ultimaMedicaoTemperatura = umt;
		this.ultimoLog = ul;
	}

	DadosInicializacao(BsonTimestamp ul) {
		this.desvioPadraoLuz = null;
		this.ultimaMedicaoLuz = null;
		this.desvioPadraoTemperatura = null;
		this.ultimaMedicaoTemperatura = null;
		this.ultimoLog = ul;
	}

}
