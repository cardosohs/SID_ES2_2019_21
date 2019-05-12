package pt.iscte.sid.projeto.GUI.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import pt.iscte.sid.projeto.GUI.LoginWindow;

class LoginWindowTest {
	
	
	LoginWindow lw;
	
	@BeforeEach
	void setUp() throws Exception {
		lw = new LoginWindow();
	}

	@Test
	final void test() {
		assertNotNull(lw);
	}

}
