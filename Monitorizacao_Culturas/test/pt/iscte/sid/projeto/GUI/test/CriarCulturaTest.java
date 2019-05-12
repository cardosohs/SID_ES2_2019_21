package pt.iscte.sid.projeto.GUI.test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;


import pt.iscte.sid.projeto.GUI.CriarCultura;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;

class CriarCulturaTest {

	DatabaseMiddleManForInvestigador databaseConnection;
	
	CriarCultura window;
	
	
	
	@Test
	final void testCriarCultura() {
		assertThrows(NullPointerException.class,
	            ()->{
	            window = new CriarCultura(databaseConnection);
	            	});
	}

}
