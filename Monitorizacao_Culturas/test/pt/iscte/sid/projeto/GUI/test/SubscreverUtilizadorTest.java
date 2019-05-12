package pt.iscte.sid.projeto.GUI.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pt.iscte.sid.projeto.GUI.InserirDados;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;

class SubscreverUtilizadorTest {

	SubscreverUtilizadorTest sub;
	
	
	@Test
	final void testSubscreverUtilizador() {
		assertNotNull(sub);
	}

	@Test
	final void testSubscreverUtilizadorDatabaseMiddleManForAdministradorBoolean() {
		assertThrows(NullPointerException.class,
	            ()->{
	            sub = new SubscreverUtilizadorTest();
	            	});
	}

}
