package pt.iscte.sid.projeto.Machine;

	import java.sql.*;
	import java.util.Calendar;
	import java.util.Random;

	/**
	 * A Java MySQL PreparedStatement INSERT example.
	 * Demonstrates the use of a SQL INSERT statement against a
	 * MySQL database, called from a Java program, using a
	 * Java PreparedStatement.
	 * 
	 */
	public class JavaMysqlPreparedStatementInsertExample2
	{

	  public static void main(String[] args)
	  {
	    try{
	    	final int NUM =20000;
	    	final String DB = "g21origem"; 
	      // create a mysql database connection
	    	String myDriver = "com.mysql.cj.jdbc.Driver";
	    	String myUrl = "jdbc:mysql://localhost/"+DB+"?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
	      ////localhost:3306/database?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC
	    	Class.forName(myDriver);
	    	Connection conn = DriverManager.getConnection(myUrl, "root", "");
	    
	      // create a sql date object so we can use it in our INSERT statement
	    	Calendar calendar = Calendar.getInstance();
	    	java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
	    	java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
	     

	      // the mysql insert statement
	    	String query1 = " insert into investigador (IdInvestigador, Email, NomeInvestigador, CategoriaProfe)"
	    	        + " values (?, ?, ?, ?)";
	    	
	    	PreparedStatement preparedStmt1 = conn.prepareStatement(query1);

	      
	    	String query2 = " insert into cultura (IdCultura, IdInvestigador, NomeCultura, DescricaoCultura)"
	    	        + " values (?, ?, ?, ?)";
	    	
	    	PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
	    	
	    	
	    	
	      
	    	String query3 = " insert into variaveismedidas (IdVarMed, IdCultura, IdVariavel, LimiteInferior, LimiteSuperior)"
	    	        + " values (?, ?, ?, ?, ?)";
	    	
	    	PreparedStatement preparedStmt3 = conn.prepareStatement(query3);
	    	
	    	preparedStmt3.setNull (1, 1);
	      	preparedStmt3.setInt (2, 1);
	      	preparedStmt3.setInt (3, 1);
	      	preparedStmt3.setInt (4, 0);
	      	preparedStmt3.setInt (5, 100);
	    
	      
	    	String query4 = " insert into variaveis (IdVariavel, NomeVariavel)"
	    	        + " values (?, ?)";   	
	    	
	    	PreparedStatement preparedStmt4 = conn.prepareStatement(query4);  	
	    
	    	preparedStmt4.setNull (1, 1);
	    	preparedStmt4.setString (2, "var1" );
	    	
	     
	    	String query5 = " insert into medicoes (IdMed, IdVarMed, DataHoraMed, ValorMed)"
	        + " values (?, ?, ?, ?)";
	    	
	    	PreparedStatement preparedStmt5 = conn.prepareStatement(query5);
	    	preparedStmt5.setNull (1, 1);
	    	preparedStmt5.setInt (2, 1);
	    	preparedStmt5.setTimestamp(3, date);
	    	preparedStmt5.setDouble(4, 6.66);

	      
	    	String query6 = " insert into sistema (IdSistema, LimiteInferiorTemp, LimiteSuperiorTemp , LimiteInferiorLuz, LimiteSuperiorLuz)"
	    	        + " values (?, ?, ?, ?, ?)";
	    	
	    	PreparedStatement preparedStmt6 = conn.prepareStatement(query6);
	    	preparedStmt6.setNull (1, 1);
	    	preparedStmt6.setDouble(2, 0.00);
	    	preparedStmt6.setDouble(3, 50.0);
	    	preparedStmt6.setDouble(4, 0.00);
	    	preparedStmt6.setDouble(5, 200.0);
	    	
	    	

	      
	    	String query7 = " insert into medicoesluz (IdMedicao, ValorMedicaoLuz, DataHoraMed)"
	    	        + " values (?, ?, ?)";
	    	
	    	PreparedStatement preparedStmt7 = conn.prepareStatement(query7);
	    	preparedStmt7.setNull (1, 1);
	       	preparedStmt7.setDouble(2, 6.66);
	    	preparedStmt7.setTimestamp(3, date);
	    	
	    	

	      
	    	String query8 = " insert into medicoestemp (IdMedicao, DataHoraMed, ValorMedicaoTemp)"
	    	        + " values (?, ?, ?)";
	    	
	    	PreparedStatement preparedStmt8 = conn.prepareStatement(query8);
	    	preparedStmt8.setNull (1, 1);
	    	preparedStmt8.setTimestamp(2, date);
	    	preparedStmt8.setDouble(3, 6.66);
	    	
	    	
	    	String query9 = " insert into administrador (IdAdmin, Email, NomeAdmin)"
	    	        + " values (?, ?, ?)";
	    	
	    	PreparedStatement preparedStmt9 = conn.prepareStatement(query9);

	      
	      
	      //INSERT INTO `medicoes` (`IdVarMed`, `NumeroMed`, `DataHoraMed`, `ValorMed`) VALUES ('1', NULL, CURRENT_TIMESTAMP, '12.5');

	    	
	    	


	    	
	      
	      
	      
	      // create the mysql insert preparedstatement
	    //	PreparedStatement preparedStmt = conn.prepareStatement(query4);
	      //preparedStmt.setInt (1, "");
	    //preparedStmt.setNull (1, 1);
	    	//preparedStmt.setString (2, "PH" );
	      //preparedStmt.setTimestamp(3, date);
	      //preparedStmt.setDouble(2, 6.77);
	      
	      
	      
	      
	      
		      System.out.println("iniciei o ciclo ");     
		      for(int i=0; i<NUM; i++) {
		    	preparedStmt1.setNull (1, 1);
		    	preparedStmt1.setString (2, "Email"+i); // colocar dentro ciclo +i
		      	preparedStmt1.setString (3, "Alex" );
		      	preparedStmt1.setString (4, "STD" );
		    	preparedStmt1.execute();    	  
		      }
		      
		      for(int i=0; i<NUM; i++) {
		    	preparedStmt2.setNull (1, 1);
			    preparedStmt2.setInt (2, 1);
			    preparedStmt2.setString (3, "Planta"+i);
			    preparedStmt2.setString (4, "mm" );
		    	preparedStmt2.execute();	    	  
		      }
		      
		      for(int i=0; i<NUM; i++) {
		    	preparedStmt4.setNull (1, 1);
			    preparedStmt4.setString (2, "var1"+i );
		    	preparedStmt4.execute();	    	  
		      }
		      
		      for(int i=0; i<NUM; i++) {
		    	preparedStmt3.execute();	    	  
		      }
		      
		      for(int i=0; i<NUM; i++) {
		    	  preparedStmt5.execute();	    	  
		      }
		      for(int i=0; i<NUM; i++) {
		    	  preparedStmt6.execute();	    	  
		      }
		      for(int i=0; i<NUM; i++) {
		    	  preparedStmt7.execute();	    	  
		      }
		      for(int i=0; i<NUM; i++) {
		    	  preparedStmt8.execute();	    	  
		      }
		      
		      for(int i=0; i<NUM; i++) {
				    preparedStmt9.setNull (1, 1);
				    preparedStmt9.setString (2, "email"+i );
				    preparedStmt9.setString(3, "Alex");
			    	preparedStmt9.execute();	    	  
		      }     
	      
		      System.out.println("terminei o ciclo ");
		      
		    
		      conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception!");
	      System.err.println(e.getMessage());
	    }
	  }
	}
