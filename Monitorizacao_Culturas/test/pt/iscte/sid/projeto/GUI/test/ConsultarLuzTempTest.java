package pt.iscte.sid.projeto.GUI.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pt.iscte.sid.projeto.GUI.ConsultarLuzTemp;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;

class ConsultarLuzTempTest {
	
	
	ConsultarLuzTemp clt;
	DatabaseMiddleManForInvestigador databaseConnection;
	

	@Test
	final void testConsultarLuzTemp() {		
		 assertThrows(NullPointerException.class,
		            ()->{
		            	clt = new ConsultarLuzTemp(databaseConnection);
		            	});
	}	

}
