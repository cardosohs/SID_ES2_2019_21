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
 *
 * @author Sérgio
 */
public class UserIdentifier {
    
    private final String DatabaseName="g21origem";
    private String Username;
    private String Password;
    private boolean failed;
    private int Id;
    private Connection DatabaseConnection;
    private ArrayList<Integer> VariaviesDaDatabase = new ArrayList<>();
    
    /**
     * Vai ver se o utilizador e admin ou investigador
     * Retorna I se for investgador
     * retorna A se for admin
     * return F caso falhe o login, por exemplo, credenciais erradas
     * @param username
     * @param Password
     */
    public String WhatUserIsThis(String username, String Password)
    {
        
        this.Password=Password;
        this.Username=username;
        
        
        String DatabaseDriver = "com.mysql.cj.jdbc.Driver";
        String DatabaseURL = "jdbc:mysql://localhost/"+DatabaseName+"?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
        
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
     * @return
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
