package pt.iscte.sid.projeto.GUI.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pt.iscte.sid.projeto.GUI.VariaveisMedidas;

class VariaveisMedidasTest {
	
	VariaveisMedidas vm;	
	
	@Test
	final void testVariaveisMedidas() {
		vm = new VariaveisMedidas();
		assertNotNull(vm);
	}

}
