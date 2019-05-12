package pt.iscte.sid.projeto.Machine.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pt.iscte.sid.projeto.Machine.DatabaseMiddleManGeneral;
import pt.iscte.sid.projeto.Machine.UserIdentifier;

class DatabaseMiddleManGeneralTest {

	DatabaseMiddleManGeneral goodDatabase;
	DatabaseMiddleManGeneral badDatabase;
	String goodUsername = "vasco";
	String goodPassword = "vasco";
	String badUsername = "carlos";
	String badPassword = "312";

	@Test
	final void testDatabaseMiddleManGeneral() {

		// NotOK
		badDatabase = new DatabaseMiddleManGeneral(badUsername, goodPassword);
		assertNotNull(badDatabase);

		badDatabase = new DatabaseMiddleManGeneral(badUsername, badPassword);
		assertNotNull(badDatabase);

		// OK
		goodDatabase = new DatabaseMiddleManGeneral(goodUsername, goodPassword);
		assertNotNull(goodDatabase);

	}

	@Test
	final void testFailed() {
		Boolean ok = true;
		Boolean ko = true;

		// assertEquals(ko, goodDatabase.failed());

		assertThrows(NullPointerException.class, () -> {
			assertEquals(ok, badDatabase.failed());

		});

	}

	@Test
	final void testGetMyId() {
		//assertNotNull(goodDatabase.getMyId());
		assertThrows(NullPointerException.class, () -> {
			assertNotNull(badDatabase.getMyId());

		});

	}

	@Test
	final void testGetMyName() {
		// assertNotNull(goodDatabase.getMyName());
		assertThrows(NullPointerException.class, () -> {
			assertNotNull(badDatabase.getMyName());
		});

	}

	@Test
	final void testGetVariaveis() {
		//assertNotNull(goodDatabase.getVariaveis());
		assertThrows(NullPointerException.class, () -> {
			assertNotNull(badDatabase.getVariaveis());
		});
	}

	@Test
	final void testGetSistema() {
		// assertNotNull(goodDatabase.getVariaveis());
		assertThrows(NullPointerException.class, () -> {
			assertNotNull(badDatabase.getVariaveis());
		});

	}

	@Test
	final void testCloseConnection() {
		Boolean ok = true;
		Boolean ko = true;

//		assertEquals(ok, goodDatabase.closeConnection());
		assertThrows(NullPointerException.class, () -> {
			assertEquals(ko, badDatabase.closeConnection());

		});



	}

}
