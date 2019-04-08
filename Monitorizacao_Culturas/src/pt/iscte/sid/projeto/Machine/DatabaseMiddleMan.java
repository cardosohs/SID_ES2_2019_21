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
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Sérgio
 */
public class DatabaseMiddleMan {
    public static void main(String[] args)
    {
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost/logdatabase?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
//here sonoo is database name, root is username and password
            Statement stmt=con.createStatement();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s = br.readLine();
            String query = "select * from " + s;
            ResultSet rs=stmt.executeQuery(query);
            // System.out.println("aaa");
            while(rs.next()){
                //System.out.println("bbb");
                System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            }
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
    
}


