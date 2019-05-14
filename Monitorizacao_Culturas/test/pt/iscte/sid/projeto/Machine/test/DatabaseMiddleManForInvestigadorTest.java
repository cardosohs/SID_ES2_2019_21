package pt.iscte.sid.projeto.Machine.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;




class DatabaseMiddleManForInvestigadorTest {
	
	private String username = "fffff";
	private String password = "password";
	private String catProf= "student";
	private String variavel = "pH";
	private int id = 1;
	
	
	private DatabaseMiddleManForInvestigador databaseConnection;	

	@Test
	final void testDatabaseMiddleManForInvestigador() {
		databaseConnection = new DatabaseMiddleManForInvestigador(username, password);
		assertNotNull(databaseConnection);
	}

	@Test
	final void testCreateCultura() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = databaseConnection.createCultura("Verduras", "nada");
	            	b.equals(false);
	            	});
	}

	@Test
	final void testGetCulturas() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	databaseConnection.getCulturas();	            	
	            	});
	}

	@Test
	final void testUpdateCultura() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = databaseConnection.updateCultura(id, "Nome2", "desc");
	            	b.equals(false);
	            	});	
	}

	@Test
	final void testDeleteCultura() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = databaseConnection.deleteCultura(id);
	            	b.equals(false);
	            	});
	}

	@Test
	final void testCreateVariavelMedida() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = databaseConnection.createVariavelMedida(id, id, id, id+8);
	            	b.equals(false);
	            	});
	}

	@Test
	final void testGetVariaveisMedidas() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	databaseConnection.getVariaveisMedidas(id, id);	            	
	            	});
	}

	@Test
	final void testUpdateVariaveisMedidas() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = databaseConnection.updateVariaveisMedidas(id, id, id+5);
	            	b.equals(false);
	            	});	
	}

	@Test
	final void testDeleteVariaveisMedidas() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = databaseConnection.deleteVariaveisMedidas(id);
	            	b.equals(false);
	            	});
	}

	@Test
	final void testGetMedicoesTemperatura() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	databaseConnection.getMedicoesTemperatura();	            	
	            	});
	}

	@Test
	final void testGetMedicoesLuz() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	databaseConnection.getMedicoesLuz();	            	
	            	});
	}

	@Test
	final void testCreateMedicoes() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = databaseConnection.createMedicoes(id, id+85);
	            	b.equals(false);
	            	});
	}

	@Test
	final void testGetMedicoes() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	databaseConnection.getMedicoes();	            	
	            	});
	}

	@Test
	final void testUpdateMedicao() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = databaseConnection.updateMedicao(id, 15);
	            	b.equals(false);
	            	});	
	}

	@Test
	final void testDeleteMedicao() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = databaseConnection.deleteMedicao(id);
	            	b.equals(false);
	            	});
	}

}
