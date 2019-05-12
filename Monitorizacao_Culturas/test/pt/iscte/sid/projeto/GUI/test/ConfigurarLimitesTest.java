package pt.iscte.sid.projeto.GUI.test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;


import pt.iscte.sid.projeto.GUI.ConfigurarLimites;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;

class ConfigurarLimitesTest {

	DatabaseMiddleManForAdministrador databaseConnection;
	
	ConfigurarLimites window;
	
	
	@Test
	final void testConfigurarLimites() {
		assertThrows(NullPointerException.class,
	            ()->{
	            window = new ConfigurarLimites(databaseConnection);
	            	});
	}

	

}
