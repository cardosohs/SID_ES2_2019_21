package pt.iscte.sid.projeto.GUI.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import pt.iscte.sid.projeto.GUI.Main;

class MainTest {
	
	Main mn;
	
	@BeforeEach
	void setUp() throws Exception {
		mn = new Main();
	}

	@Test
	final void testMain() {
		assertNotNull(mn);
	}

}
