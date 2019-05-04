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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sérgio
 */
public class DatabaseMiddleManGeneral {

    public final String DatabaseName = "g21origem";
    public String Username;
    public String Password;
    public boolean failed;
    public int Id;
    public Connection DatabaseConnection;
    public ArrayList<Integer> VariaviesDaDatabase = new ArrayList<>();

    public DatabaseMiddleManGeneral(String username, String Password) {
        StartConnection(username, Password);
        GetId();
    }

    /**
     * Inicia a ligacao a BD g21origem
     *
     * @param username
     * @param Password
     */
    private void StartConnection(String username, String Password) {
        this.failed = true;
        this.Password = Password;
        this.Username = username;

        String DatabaseDriver = "com.mysql.cj.jdbc.Driver";
        String DatabaseURL = "jdbc:mysql://localhost/" + DatabaseName + "?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";

        try {
            Class.forName(DatabaseDriver);
            DatabaseConnection = DriverManager.getConnection(DatabaseURL, Username, this.Password);
            this.failed = false;

        } catch (ClassNotFoundException ex) {
            this.failed = true;

        } catch (SQLException ex) {
            this.failed = true;
        }
    }

    /**
     * Termina a ligacao a BD
     *
     * @return
     */
    public boolean CloseConnection() {
        try {
            DatabaseConnection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMiddleManForInvestigador.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Vai buscar o Id do Utilizador
     */
    private void GetId() {
        String DatabaseDriver = "com.mysql.cj.jdbc.Driver";
        String DatabaseURL = "jdbc:mysql://localhost/" + DatabaseName + "?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";

        try {

            Class.forName(DatabaseDriver);
            Connection DatabaseTMPConnection = DriverManager.getConnection(DatabaseURL, "root", "");
            Statement stmt = DatabaseTMPConnection.createStatement();

            String query = "select * from investigador where email='" + Username + "';";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                this.Id = rs.getInt(1);
            }

            DatabaseTMPConnection.close();

        } catch (ClassNotFoundException ex) {
            System.out.println("Failed ao buscar o id do investigador");
            Logger.getLogger(DatabaseMiddleManForInvestigador.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMiddleManForInvestigador.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed ao buscar o id do investigador");
        }
    }

    /**
     * Retorna se a ligacao a BD falhou
     *
     * @return
     */
    public boolean Failed() {
        return failed;
    }

    /**
     * Retorna o id do Utilizador
     *
     * @return
     */
    public int getMyId() {
        return Id;
    }

    /**
     * vai buscar as variaveis
     *
     * @return
     */
    public String getVariaveis() {
        String TableResult = "";
        try {
            Statement stmt = DatabaseConnection.createStatement();
            String query = "select * from variaveis";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (!VariaviesDaDatabase.contains(Integer.parseInt(rs.getString(1)))) {
                    VariaviesDaDatabase.add(Integer.parseInt(rs.getString(1)));
                }
                TableResult += rs.getString(1) + "BREAKCOLUMN" + rs.getString(2)+"BREAKLINE";
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return TableResult;
    }

    /**
     * Vai buscar os dados da tabela sistema
     *
     * @return
     */
    public String getSistema() {
        String TableResult = "";
        try {
            Statement stmt = DatabaseConnection.createStatement();
            String query = "select * from sistema";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                TableResult
                        +=rs.getString(2) + "BREAKCOLUMN"
                        + rs.getString(3) + "BREAKCOLUMN"
                        + rs.getString(4) + "BREAKCOLUMN"
                        + rs.getString(5) + "BREAKLINE";
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return TableResult;
    }

}
