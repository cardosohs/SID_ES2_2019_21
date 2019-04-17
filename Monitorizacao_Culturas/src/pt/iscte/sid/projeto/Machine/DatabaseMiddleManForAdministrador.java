/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pt.iscte.sid.projeto.Machine;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * FALTA TESTAR AS ALTERACOES DE PASSWORD E O LOGIN E TESTAR PROFUDAMENTE
 * 
 * @author Sérgio
 * Faz todas as operaçoes que o admin pode fazer na BD, isto é, login, logout, chamar SP e operacoes CRUD(quando aplicavel)
 *
 */
public class DatabaseMiddleManForAdministrador extends DatabaseMiddleManGeneral{
    
    public DatabaseMiddleManForAdministrador(String username, String Password) {
        super(username, Password);
    }
    
    
    private String getOneInvestigador(int id)
    {
        String TableResult="";
        try{
            Statement stmt=DatabaseConnection.createStatement();
            String query = "select * from investigador where IdInvestigador=" + id;
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                TableResult += rs.getString(2);
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        
        return TableResult;
    }
    
    private String getOneAdministrador(int id)
    {
        String TableResult="";
        try{
            Statement stmt=DatabaseConnection.createStatement();
            String query = "select * from administrador where IdAdmin=" + id;
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                TableResult += rs.getString(2);
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        
        return TableResult;
    }
    
    
    private boolean UpdateUserNaBD(String OldEmail, String NewEmail){
        try {
            String query="RENAME USER '"+OldEmail+"'@'localhost' to '"+NewEmail+"'@'localhost';";
            PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
            preparedStmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMiddleManForAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    private boolean DeleteUserNaBD(String Email)
    {
        try {
            String query= "DROP USER "+Email+"@localhost;";
            PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
            preparedStmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMiddleManForAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    /**
     * vai buscar os Investigadores
     * @return
     */
    public String getInvestigador()
    {
        String TableResult="";
        try{
            Statement stmt=DatabaseConnection.createStatement();
            String query = "select * from investigador";
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                TableResult += "\nInvestigador \nID do Investigador:" + rs.getString(1)+"\nEmail do Investigador:"+rs.getString(2)
                        +"\nNome do Investigador:"+rs.getString(3) +"\nCategoria do Investigador " + rs.getString(4) +"\n\n";
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        
        return TableResult;
    }
    
    /**
     * Atualiza um investigador
     * @param IdInvestigador
     * @param Email
     * @param Nome
     * @param CategoriaProfe
     * @return
     */
    //Esta por acabar, falta alterar o na tabela mysql.user
    public boolean UpdateInvestigador(int IdInvestigador, String Email, String Nome, String CategoriaProfe)
    {
        try {
            String query =
                    " update investigador set email= '" + Email
                    + "', NomeInvestigador='" + Nome+ "', CategoriaProfe='"+ CategoriaProfe
                    +"' where idInvestigador=" + IdInvestigador;
            PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
            preparedStmt.execute();
            return UpdateUserNaBD(getOneInvestigador(IdInvestigador),Email);
        } catch (SQLException ex) {
            System.err.println("Erro ao executar a accao");
            return false;
        }
    }
    
    
    public boolean UpdateInvestigadorPassword(int IdInvestigador, String Password)
    {
        try {
            String query="";
            String Email=getOneInvestigador(IdInvestigador);
            query="ALTER USER '"+Email+"'@'localhost' IDENTIFIED BY '"+Password+"'";
            PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
            preparedStmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMiddleManForAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    
    /**
     * Apaga um investigador
     * @param IdInvestigador
     * @return
     */
    //Falta remover o user da tabela mysql.user
    public boolean DeleteInvestigador(int IdInvestigador)
    {
        boolean success=false;
        try {
            success= DeleteUserNaBD(getOneInvestigador(IdInvestigador));
            if(success){
                String query = " delete from investigador where idinvestigador=" + IdInvestigador;
                PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
                preparedStmt.execute();
                return true;
            }
            return false;
        } catch (SQLException ex) {
            System.err.println("Erro ao executar a accao");
            return false;
        }
    }
    
    
    /**
     * Vai buscar os Administradores
     * @return
     */
    public String getAdministradores()
    {
        String TableResult="";
        try{
            Statement stmt=DatabaseConnection.createStatement();
            String query = "select * from Administrator";
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                TableResult += "\nIAdmin \nID do admin:" + rs.getString(1)+"\nEmail do admin:"+rs.getString(2)
                        +"\nNome do Admin:"+rs.getString(3)+"\n\n";
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        
        return TableResult;
    }
    
    /**
     * Atualiza um administrador
     * @param IdAdmin
     * @param Email
     * @param Nome
     * @return
     */
    
    //Esta como o UpdateInvestigador
    public boolean UpdateAdministrador(int IdAdmin, String Email, String Nome)
    {
        try {
            String query =
                    " update investigador set email= '" + Email
                    + "', NomeInvestigador='" + Nome
                    +"' where idInvestigador=" + IdAdmin;
            PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
            preparedStmt.execute();
            return UpdateUserNaBD(getOneAdministrador(IdAdmin),Email);
        } catch (SQLException ex) {
            System.err.println("Erro ao executar a accao");
            return false;
        }
    }
    
    public boolean UpdateAdministradorPassword(int IdAdministrador, String Password)
    {
        try {
            String query="";
            String Email=getOneAdministrador(IdAdministrador);
            query="ALTER USER '"+Email+"'@'localhost' IDENTIFIED BY '"+Password+"'";
            PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
            preparedStmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMiddleManForAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    
    
    /**
     * Apaga um administrador
     * @param IdAdmin
     * @return
     */
    
    //esta como o DeleteInvestigador
    public boolean DeleteAdmin(int IdAdmin)
    {
        boolean success=false;
        try {
            success= DeleteUserNaBD(getOneAdministrador(IdAdmin));
            if(success){
                String query = " delete from administrador where idadmin=" + IdAdmin;
                PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
                preparedStmt.execute();
                return success;
            }
            else
                return false;
        } catch (SQLException ex) {
            System.err.println("Erro ao executar a accao");
            return false;
        }
        
        
    }
    
    
    
    /**
     * Cria uma variavel
     * @param NomeVariavel
     * @return
     */
    public boolean CreateVariavel(String NomeVariavel)
    {
        try {
            String query1 = " insert into variaveis(NomeVariavel) values (?)";
            PreparedStatement preparedStmt1 = DatabaseConnection.prepareStatement(query1);
            preparedStmt1.setString(1, NomeVariavel);
            preparedStmt1.execute();
        } catch (SQLException ex) {
            return false;
        }
        return true;
        
    }
    
    
    /**
     * Apaga uma variavel
     * @param IdVariavel
     * @return
     */
    public boolean DeleteVariavel(int IdVariavel)
    {
        
        try {
            String query = " delete from variaveis where idvariavel=" + IdVariavel;
            PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
            preparedStmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao executar a accao");
            return false;
        }
        
    }
    
    /**
     * Cria uma nova entrada na tabela sistema
     * @param LimiteInferiorTemp
     * @param LimiteSuperiorTemp
     * @param LimiteInferiorLuz
     * @param LimiteSuperiorLuz
     * @return
     */
    public boolean CreateSistema(int LimiteInferiorTemp,int LimiteSuperiorTemp,
            int LimiteInferiorLuz,int LimiteSuperiorLuz)
    {
        try {
            String query1 = " insert into sistema(LimiteInferiorTemp,LimiteSuperiorTemp,"
                    + "LimiteInferiorLuz,LimiteSuperiorLuz) values (?,?,?,?)";
            PreparedStatement preparedStmt1 = DatabaseConnection.prepareStatement(query1);
            preparedStmt1.setInt(1, LimiteInferiorTemp);
            preparedStmt1.setInt(2, LimiteSuperiorTemp);
            preparedStmt1.setInt(3, LimiteInferiorLuz);
            preparedStmt1.setInt(4, LimiteSuperiorLuz);
            preparedStmt1.execute();
        } catch (SQLException ex) {
            return false;
        }
        return true;
        
    }
    
    /**
     * Faz update a uma entrada na tabela sistema
     * @param IdSistema
     * @param LimiteInferiorTemp
     * @param LimiteSuperiorTemp
     * @param LimiteInferiorLuz
     * @param LimiteSuperiorLuz
     * @return
     */
    public boolean UpdateSistema(int IdSistema,int LimiteInferiorTemp,int LimiteSuperiorTemp,
            int LimiteInferiorLuz,int LimiteSuperiorLuz)
    {
        try {
            String query =
                    " update Sistema set LimiteinferiorTemp= '" + LimiteInferiorTemp
                    + "', limiteSuperiorTemp='" + LimiteSuperiorTemp +
                    "', limiteinferiorluz='" + LimiteInferiorLuz +
                    "', limiteSuperiorluz='" + LimiteSuperiorLuz
                    + "' where idsistema=" + IdSistema;
            PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
            preparedStmt.execute();
            
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao executar a accao");
            return false;
        }
    }
    
    
    /**
     * Apaga uma entrada na tabela sistema
     * @param IdSistema
     * @return
     */
    public boolean DeleteSistema(int IdSistema)
    {
        
        try {
            String query = " delete from sistema where idsistema=" + IdSistema;
            PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
            preparedStmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao executar a accao");
            return false;
        }
        
    }
    
    
    public boolean ExecuteSP(String InNome, String InPassword, String InEmail, String InCategoriaProfe, String InTipo)
    {
        CallableStatement cs = null;
        try {
            cs = DatabaseConnection.prepareCall("{call SP_CriaUtilizador(?,?,?,?,?)}");
            cs.setString(1, InNome);
            cs.setString(2, InPassword);
            cs.setString(3, InEmail);
            cs.setString(4, InCategoriaProfe);
            cs.setString(5, InTipo);
            cs.execute();
            return cs.execute();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            return false;
        }
    }
    
    
    
    
}