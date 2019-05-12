package pt.iscte.sid.projeto.GUI.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pt.iscte.sid.projeto.GUI.AreaInvestigador;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;

class AreaInvestigadorTest {

	DatabaseMiddleManForInvestigador databaseConnection;
	
	AreaInvestigador window;

	
	@Test
	final void testAreaInvestigador() {
		assertThrows(NullPointerException.class,
	            ()->{
	            window = new AreaInvestigador(databaseConnection);
	            	});
	}


}
