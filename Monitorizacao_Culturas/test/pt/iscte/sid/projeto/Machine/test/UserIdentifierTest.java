package pt.iscte.sid.projeto.Machine.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import pt.iscte.sid.projeto.GUI.ConfigurarLimites;
import pt.iscte.sid.projeto.Machine.UserIdentifier;

class UserIdentifierTest {

 final String DatabaseName="g21origem";
 UserIdentifier user;
     String goodUsername="vasco";
     String goodPassword="vasco";
     String badUsername="carlos";
     String badPassword="312";
     
    
    
	
	
	
	@Test
	final void testWhatUserIsThis() {
		String ok = "A";
		String notOK = "F";
		
		assertThrows(NullPointerException.class,
	            ()->{
	            	assertSame(ok, user.whatUserIsThis(goodUsername, goodPassword));
	            	});
		
		assertThrows(NullPointerException.class,
	            ()->{
	            	assertSame(notOK, user.whatUserIsThis(goodUsername, badPassword));
	            	});
		
		assertThrows(NullPointerException.class,
	            ()->{
	            	assertNotNull( user.whatUserIsThis(badUsername, badPassword));
	            	});
		
		
		
	}

}
