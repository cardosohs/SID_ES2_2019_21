package pt.iscte.sid.projeto.GUI.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.iscte.sid.projeto.GUI.AreaInvestigador;

import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;

class AreaInvestigadorTest {

	DatabaseMiddleManForInvestigador databaseConnection;
	
	AreaInvestigador window;
	
	@BeforeEach
	void setUp() throws Exception {
		window = new AreaInvestigador(databaseConnection);
	}
	
	@Test
	final void testAreaInvestigador() {
		assertNotNull(window);
	}


}
