package pt.iscte.sid.projeto.GUI.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pt.iscte.sid.projeto.GUI.ConsultarLuzTemp;
import pt.iscte.sid.projeto.GUI.CulturasLista;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;

class CulturasListaTest {
	
	CulturasLista cl;
	private DatabaseMiddleManForInvestigador databaseConnection;

	@Test
	final void testCulturasLista() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	cl = new CulturasLista(databaseConnection);
	            	});
	}


}
