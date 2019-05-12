package pt.iscte.sid.projeto.GUI.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.iscte.sid.projeto.GUI.AreaAdmin;
import pt.iscte.sid.projeto.GUI.ConfigurarLimites;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;

class ConfigurarLimitesTest {

	DatabaseMiddleManForAdministrador databaseConnection;
	
	ConfigurarLimites window;
	
	
	@BeforeEach
	void setUp() throws Exception {
		window = new ConfigurarLimites(databaseConnection);
	}
	
	@Test
	final void testConfigurarLimites() {
		assertNotNull(window);
	}

	

}
