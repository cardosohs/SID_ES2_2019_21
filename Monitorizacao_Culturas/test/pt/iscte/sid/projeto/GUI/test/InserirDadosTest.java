package pt.iscte.sid.projeto.GUI.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import pt.iscte.sid.projeto.GUI.InserirDados;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;

class InserirDadosTest {
	
	
	InserirDados id;
	private DatabaseMiddleManForInvestigador databaseConnection;

	@Test
	final void testInserirDados() {
		assertThrows(NullPointerException.class,
	            ()->{
	            id = new InserirDados(databaseConnection);
	            	});
	}

}
