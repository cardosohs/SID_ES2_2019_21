/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pt.iscte.sid.projeto.Machine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *	Classe para identificar o utilizador
 *
 * @author Grupo 21
 */
public class UserIdentifier {
    
    private final String databaseName="g21origem";
    private String Username;
    private String Password;
    private boolean failed;
    private int id;
    private Connection DatabaseConnection;
    private ArrayList<Integer> variaveisDaDatabase = new ArrayList<>();
    
    /**
     * Vai ver se o utilizador e admin ou investigador
     * 
     * @param username recebe uma string com um username
     * @param password recebe uma string com uma password
     * @return retorna "I" se for investigador, "A" se for admim ou "F" caso falhe o login, por exemplo, credenciais erradas
     */
    public String whatUserIsThis(String username, String password){
        
        this.Password=password;
        this.Username=username;
        
        
        String DatabaseDriver = "com.mysql.cj.jdbc.Driver";
        String DatabaseURL = "jdbc:mysql://localhost/"+databaseName+"?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
        
        try {
            Class.forName(DatabaseDriver);
            DatabaseConnection = DriverManager.getConnection(DatabaseURL, Username, this.Password);
            String tmp = getInvestigador();
            if(tmp.equals(""))
                return "I";
            else
                return "A";
            
        } catch (ClassNotFoundException ex) {
            return "F";
        } catch (SQLException ex) {
            return "F";
        }
    }
    
    
    /**
     * vai buscar os Investigadores, caso nao consiga retorna uma string vazia
     * @return uma string com os investigadores que estiverem na tabela investigador
     */
    private String getInvestigador()
    {
        String TableResult="";
        try{
            Statement stmt=DatabaseConnection.createStatement();
            String query = "select * from investigador";
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                TableResult += rs.getString(1);
            }
        }catch(Exception e){
            return "";
        }
        
        return TableResult;
    }
    
}
