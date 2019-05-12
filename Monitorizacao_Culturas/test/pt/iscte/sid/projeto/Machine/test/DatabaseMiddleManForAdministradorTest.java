package pt.iscte.sid.projeto.Machine.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;

class DatabaseMiddleManForAdministradorTest {
	
	
	private String username = "fffff";
	private String password = "password";
	private String catProf= "student";
	private String variavel = "pH";
	private int id = 1;
	
	DatabaseMiddleManForAdministrador admin;
	private DatabaseMiddleManForAdministrador databaseConnection;			

	@Test
	final void testDatabaseMiddleManForAdministrador() {
		admin = new DatabaseMiddleManForAdministrador(username, password);
		assertNotNull(admin);
	}

	@Test
	final void testGetInvestigador() {
		
		assertThrows(NullPointerException.class,
	            ()->{
	            	databaseConnection.getInvestigador();
	            	});
	}

	@Test
	final void testUpdateInvestigador() {		
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = admin.updateInvestigador(id, username,catProf);
	            	b.equals(false);
	            	});		
	}

	@Test
	final void testUpdateInvestigadorPassword() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = admin.updateInvestigadorPassword(id, password);
	            	b.equals(false);
	            	});	
	}

	@Test
	final void testDeleteInvestigador() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = admin.deleteInvestigador(id);
	            	b.equals(false);
	            	});
	}

	@Test
	final void testGetAdministradores() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	databaseConnection.getAdministradores();
	            	});
	}

	@Test
	final void testUpdateAdministrador() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = admin.updateAdministrador(id, username);
	            	b.equals(false);
	            	});	
	}

	@Test
	final void testUpdateAdministradorPassword() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = admin.updateAdministradorPassword(id, password);
	            	b.equals(false);
	            	});	
	}

	@Test
	final void testDeleteAdmin() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = admin.deleteAdmin(id);
	            	b.equals(false);
	            	});
	}

	@Test
	final void testCreateVariavel() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = admin.createVariavel(variavel);
	            	b.equals(false);
	            	});
	}

	@Test
	final void testDeleteVariavel() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = admin.deleteVariavel(id);
	            	b.equals(false);
	            	});
	}

	@Test
	final void testCreateSistema() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = admin.createSistema(id,id+1,id,id+1);
	            	b.equals(false);
	            	});
	}

	@Test
	final void testUpdateSistema() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = admin.updateSistema(id, id, id+2, id, id+2);
	            	b.equals(false);
	            	});
	}

	@Test
	final void testDeleteSistema() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = admin.deleteSistema(id);
	            	b.equals(false);
	            	});
	}

	@Test
	final void testExecuteSP() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	Boolean b = admin.executeSP("eu","eu","eu","eu","I");
	            	b.equals(false);
	            	});
	}

}
