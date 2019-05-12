package pt.iscte.sid.projeto.GUI.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VariaveisMedidasTest {

	VariaveisMedidasTest varmed;
	
	
	@Test
	final void testSubscreverUtilizador() {
		assertNotNull(varmed);
	}

	@Test
	final void testSubscreverUtilizadorDatabaseMiddleManForAdministradorBoolean() {
		assertThrows(NullPointerException.class,
	            ()->{
	            varmed = new VariaveisMedidasTest();
	            	});
	}

}
