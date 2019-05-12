package pt.iscte.sid.projeto.GUI.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pt.iscte.sid.projeto.GUI.SubscreverUtilizador;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;

class SubscreverUtilizadorTest {

	SubscreverUtilizador sub;
	
	boolean b;
	
	DatabaseMiddleManForAdministrador databaseConnection;
	
	
	@Test
	final void testSubscreverUtilizador() {
		assertNull(sub);
	}
	
	@Test
	final void testBoolean() {
		assertFalse(b);
	}

	@Test
	final void testSubscreverUtilizadorDatabaseMiddleManForAdministradorBoolean() {
		
	}

}
