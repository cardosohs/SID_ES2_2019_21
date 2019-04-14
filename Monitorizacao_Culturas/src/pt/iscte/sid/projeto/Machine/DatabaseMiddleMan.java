/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pt.iscte.sid.projeto.Machine;   

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sérgio
 */
public class DatabaseMiddleMan {
    private final String DatabaseName="g21origem";
    private final static String DatabaseUser="root";
    private final static String UserPassword="";
    private String UsernameInvestigador;
    private String PasswordInvestigador;
    private boolean failed;
    
    private Connection DatabaseConnection;
    
    public DatabaseMiddleMan(String username, String Password)
    {
        StartConnection(username, Password);
    }
    
    public void StartConnection(String username, String Password)
    {
        this.failed=true;
        this.PasswordInvestigador=Password;
        this.UsernameInvestigador=username;
        String DatabaseDriver = "com.mysql.cj.jdbc.Driver";
        String DatabaseURL = "jdbc:mysql://localhost/"+DatabaseName+"?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
        try {
            Class.forName(DatabaseDriver);
            DatabaseConnection = DriverManager.getConnection(DatabaseURL, UsernameInvestigador, PasswordInvestigador);
            this.failed=false;
        } catch (ClassNotFoundException ex) {
             System.out.println("Failed to login ");
             this.failed=true;
           // Logger.getLogger(DatabaseMiddleMan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //Logger.getLogger(DatabaseMiddleMan.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed to login ");
            this.failed=true;
        }
    }
    
    public boolean CloseConnection()
    {
        try {
            DatabaseConnection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMiddleMan.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public String ReadFromDatabase(String table)
    {
        String TableResult="";
        try{
            Statement stmt=DatabaseConnection.createStatement();
            String query = "select * from " + table;
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
               TableResult += rs.toString();
                System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        
        return TableResult;
    }
    
    public boolean WriteInDatabase()
    {
        
        try {
            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
                        
            // the mysql insert statement
            String query1 = " insert into investigador (IdInvestigador, Email, NomeInvestigador, CategoriaProfe)"
                    + " values (?, ?, ?, ?)";
            PreparedStatement preparedStmt1 = DatabaseConnection.prepareStatement(query1);
            preparedStmt1.setNull (1, 1);
            preparedStmt1.setString (2, "Email"); // colocar dentro ciclo +i
            preparedStmt1.setString (3, "Alex" );
            preparedStmt1.setString (4, "STD" );
            preparedStmt1.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMiddleMan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean getFailed()
    {
        return failed;
    }
      
}

