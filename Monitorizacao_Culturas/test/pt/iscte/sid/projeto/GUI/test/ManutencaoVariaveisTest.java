package pt.iscte.sid.projeto.GUI.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pt.iscte.sid.projeto.GUI.AreaInvestigador;
import pt.iscte.sid.projeto.GUI.ManutencaoVariaveis;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;

class ManutencaoVariaveisTest {
	
	ManutencaoVariaveis window;
	
	DatabaseMiddleManForAdministrador databaseConnection;

	@Test
	final void testManutencaoVariaveis() {
		assertThrows(NullPointerException.class,
	            ()->{
	            window = new ManutencaoVariaveis(databaseConnection);
	            	});
	}
}
