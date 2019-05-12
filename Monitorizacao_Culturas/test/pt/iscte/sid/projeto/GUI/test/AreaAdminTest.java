package pt.iscte.sid.projeto.GUI.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.iscte.sid.projeto.GUI.AreaAdmin;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;

class AreaAdminTest {
	
	DatabaseMiddleManForAdministrador databaseConnection;
	
	AreaAdmin window;
	
	
	@BeforeEach
	void setUp() throws Exception {
		window = new AreaAdmin(databaseConnection);
	}

	@Test
	final void testAreaAdmin() {
		assertNotNull(window);
	}

}
