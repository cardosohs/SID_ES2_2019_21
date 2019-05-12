package pt.iscte.sid.projeto.CodigoAuxiliar;

import java.sql.*;
import java.util.Calendar;

/**
 * A Java MySQL PreparedStatement INSERT example.
 * Demonstrates the use of a SQL INSERT statement against a
 * MySQL database, called from a Java program, using a
 * Java PreparedStatement.
 * 
 */
public class Bulking_singleTable
{

  public static void main(String[] args)
  {
    try
    {
      // create a mysql database connection
      String myDriver = "com.mysql.cj.jdbc.Driver";
      String myUrl = "jdbc:mysql://localhost/g21origem?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
      ////localhost:3306/database?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "");
    
      // create a sql date object so we can use it in our INSERT statement
      Calendar calendar = Calendar.getInstance();
      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
      java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
     

      // the mysql insert statement
      String query = " insert into medicoes (IdVarMEd, ValorMed)"
        + " values (?, ?)";
      
      //INSERT INTO `medicoes` (`IdVarMed`, `NumeroMed`, `DataHoraMed`, `ValorMed`) VALUES ('1', NULL, CURRENT_TIMESTAMP, '12.5');

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setInt (1, 1);
      //preparedStmt.setString (2, );
      //preparedStmt.setTimestamp(3, date);
      preparedStmt.setDouble(2, 6.77);
      
      long startTime = System.nanoTime();
      System.out.println("iniciei o ciclo " + System.currentTimeMillis());
  

      // execute the preparedstatement
      for(int i=0; i<10000; i++) {
    	  preparedStmt.execute();    	  
      }
      
      long endTime = System.nanoTime();
      System.out.println("terminei o ciclo " + System.currentTimeMillis());
      long totalTime = endTime - startTime;
      System.out.println("o total é " + totalTime);
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
    }
  }
}
