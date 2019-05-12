package pt.iscte.sid.projeto.GUI.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pt.iscte.sid.projeto.GUI.CulturasLista;
import pt.iscte.sid.projeto.GUI.ManutencaoUtilizadores;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;

class ManutencaoUtilizadoresTest {
	
	
	ManutencaoUtilizadores mu;
	private DatabaseMiddleManForAdministrador databaseConnection;

	@Test
	final void testManutencaoUtilizadores() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	mu = new ManutencaoUtilizadores(databaseConnection);
	            	});
	}



}
