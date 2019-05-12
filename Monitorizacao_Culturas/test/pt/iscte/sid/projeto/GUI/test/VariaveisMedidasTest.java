package pt.iscte.sid.projeto.GUI.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pt.iscte.sid.projeto.GUI.VariaveisMedidas;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;

class VariaveisMedidasTest {
	
	VariaveisMedidas vm;
	
	DatabaseMiddleManForInvestigador databaseConnection;
	
	@Test
	final void testVariaveisMedidas() {
		vm = new VariaveisMedidas(databaseConnection);
		assertNotNull(vm);
	}

}
