/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pt.iscte.sid.projeto.Machine;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Classe de interface entre a Gui para o administrador e a base de dados Mysql
 * @author Grupo 21
 *
 */
public class DatabaseMiddleManForAdministrador extends DatabaseMiddleManGeneral{
    
	
	
	/**
	 * Construtor da classe
	 * @param username string com o utilizador
	 * @param Password string de password
	 */
    public DatabaseMiddleManForAdministrador(String username, String Password) {
        super(username, Password);
    }
    
    
    
    /**
     * Metodo de suporte que vai buscar o email de um investigador dado um id especifico
     * @param id do utilizador da tabela de investigador
     * @return email do investigador
     */
    private String getinvestigadorEmail(int id)
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
    
    
    /**
     * Metodo de suporte que vai buscar o email de um administrador dado um id especifico
     * @param id do utilizador da tabela de administrador
     * @return email do administrador
     */
    private String getAdministradorEmail(int id)
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
    
    
    /**
     * Metodo para apagar um utilizador da base de dados dado um email especifico
     * @param Email do utilizador a apagar
     * @return boolean true se funcionar false se não
     * 
     */
    private boolean deleteUserNaBD(String Email){
        String DatabaseDriver = "com.mysql.cj.jdbc.Driver";
        String DatabaseURL = "jdbc:mysql://localhost/" + DatabaseName + "?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";

        try {
            Class.forName(DatabaseDriver);
            Connection DatabaseTMPConnection = DriverManager.getConnection(DatabaseURL, "root", "");
            Statement stmt=DatabaseTMPConnection.createStatement();
            String query= "DROP USER '"+Email+"'@'localhost'";
            int rs=stmt.executeUpdate(query);          
           // System.out.println(query);
            DatabaseTMPConnection.close();
            return true;
            
        } catch (ClassNotFoundException | SQLException ex) {
            //System.out.println("Failed ao buscar o id do investigador");
            Logger.getLogger(DatabaseMiddleManForInvestigador.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    
    }
    
    
    /**
     * Metodo para obter uma string concatenada dos valores da tabela investigador
     * @return string concatenada
     */

    public String getInvestigador()
    {
        String TableResult="";
        try{
            Statement stmt=DatabaseConnection.createStatement();
            String query = "select * from investigador";
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                TableResult += rs.getString(1)+"BREAKCOLUMN"+rs.getString(2)
                        +"BREAKCOLUMN"+rs.getString(3) +"BREAKCOLUMN" + rs.getString(4) + "BREAKLINE";
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        
        return TableResult;
    }
    
    /**
     * Metodo para atualizar a informação de um investigador
     * @param IdInvestigador int para pesquisa de utilizador a alterar
     * @param Nome string com nome novo
     * @param CategoriaProfe string com nova categoria profissional
     * @return boolean se funcionou true ou false se falhou
     */
    public boolean updateInvestigador(int IdInvestigador, String Nome, String CategoriaProfe)
    {
        try {
            String query =
                    " update investigador set NomeInvestigador='" + Nome+ "', CategoriaProfe='"+ CategoriaProfe
                    +"' where idInvestigador=" + IdInvestigador;
            PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
            preparedStmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao executar a accao");
            return false;
        }
    }
    
    /**
     * Metodo para atualizar apenas a password de um investigador
     * @param IdInvestigador int para pesquisa de utilizador a alterar
     * @param Password string com password nova
     * @return boolean se funcionou true ou false se falhou
     */
    public boolean updateInvestigadorPassword(int IdInvestigador, String Password)
    {
        try {
            String query="";
            String Email=getinvestigadorEmail(IdInvestigador);
            query="SET PASSWORD FOR '"+Email+"'@'localhost' = PASSWORD('"+Password+"');";
            PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
            preparedStmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMiddleManForAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    
    /**
     * Metodo para apagar um investigador
     * @param IdInvestigador int para pesquisa de utilizador a apagar
     * @return boolean se funcionou true ou false se falhou
     */
    public boolean deleteInvestigador(int IdInvestigador)
    {
        boolean success=false;
        try {
            success= deleteUserNaBD(getinvestigadorEmail(IdInvestigador));
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
     * Metodo para ir buscar uma string concatatenada da tabela Administradores
     * @return string com os valores concatenados
     */
    public String getAdministradores()
    {
        String TableResult="";
        try{
            Statement stmt=DatabaseConnection.createStatement();
            String query = "select * from administrador";
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                TableResult += rs.getString(1)+"BREAKCOLUMN"+rs.getString(2)
                        +"BREAKCOLUMN"+rs.getString(3)+ "BREAKLINE";
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        
        return TableResult;
    }
    
    /**
     * Metodo para atualizar o nome do administrador
     * @param IdAdmin int com o id do administrador a alterar
     * @param Nome string do novo nome
     * @return boolean que indica se foi feito update ao administrador
     */
    public boolean updateAdministrador(int IdAdmin, String Nome)
    {
        try {
            String query =
                    " update administrador set NomeAdmin='" + Nome
                    +"' where idAdmin=" + IdAdmin;
            PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
            preparedStmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao executar a acao");
            return false;
        }
    }
    
    
    /**
     * Metodo para atualizar a password do administrador
     * @param IdAdministrador int com o id do administrador a alterar
     * @param Password string da nova password
     * @return boolean se funcionou true ou false se falhou
     */
    public boolean updateAdministradorPassword(int IdAdministrador, String Password)
    {
        try {
            String query="";
            String Email=getAdministradorEmail(IdAdministrador);
            query=query="SET PASSWORD FOR '"+Email+"'@'localhost' = PASSWORD('"+Password+"');";
            PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
            preparedStmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMiddleManForAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    
    
    /**
     * Metodo para apagar administrador
     * @param IdAdmin int com o id do administrador a alterar
     * @return booleano se funcionou true ou false se falhou
     */
    public boolean deleteAdmin(int IdAdmin)
    {
        try {
            boolean  success= deleteUserNaBD(getAdministradorEmail(IdAdmin));
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
     * Metodo para criar uma variavel
     * @param NomeVariavel string com o nome da variavel a criar
     * @return booleano se funcionou true ou false se falhou
     */
    public boolean createVariavel(String NomeVariavel)
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
     * Metodo para apagar uma variavel
     * @param IdVariavel int com o id do variavel a apagar
     * @return booleano se funcionou true ou false se falhou
     */
    public boolean deleteVariavel(int IdVariavel)
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
     * Metodo para criar uma nova entrada na tabela sistema com os limites para o sensor
     * @param LimiteInferiorTemp int com limite inferior de temperatura
     * @param LimiteSuperiorTemp int com limite superior de temperatura
     * @param LimiteInferiorLuz int com limite inferior de luminosidade
     * @param LimiteSuperiorLuz int com limite superior de luminosidade
     * @return booleano se funcionou true ou false se falhou
     */
    public boolean createSistema(int LimiteInferiorTemp,int LimiteSuperiorTemp,
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
            return true;
        } catch (SQLException ex) {
            return false;
        }
        
    }
    
    /**
     * Metodo para atualizar uma entrada na tabela sistema
     * @param IdSistema int id do sistema a atualizar
     * @param LimiteInferiorTemp int com limite inferior de temperatura
     * @param LimiteSuperiorTemp int com limite superior de temperatura
     * @param LimiteInferiorLuz int com limite inferior de luminosidade
     * @param LimiteSuperiorLuz int com limite superior de luminosidade
     * @return booleano se funcionou true ou false se falhou
     */
    public boolean updateSistema(int IdSistema,int LimiteInferiorTemp,int LimiteSuperiorTemp,
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
     * Metodo para apagar uma entrada na tabela sistema
     * @param IdSistema int id do sistema a atualizar
     * @return booleano se funcionou true ou false se falhou
     */
    public boolean deleteSistema(int IdSistema)
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
    
    /**
     * Metodo para executar o storede procedure para criar utilizador SP_CriaUtilizador
     * @param InNome string com o nome do utilizador
     * @param InPassword string com a password do utilizador
     * @param InEmail string com o Email do utilizador
     * @param InCategoriaProfe string com a categoria profissional do utiizador
     * @param InTipo string com o tipo de utilizador admin ou investigador
     * @return booleano se funcionou true ou false se falhou
     */
    public boolean executeSP(String InNome, String InPassword, String InEmail, String InCategoriaProfe, String InTipo)
    {
        // PreparedStatement cs = null;
        try {
            PreparedStatement cs = DatabaseConnection.prepareCall("{call SP_CriaUtilizador(?,?,?,?,?)}");
            cs.setString(1, InNome);
            cs.setString(2, InPassword);
            cs.setString(3, InEmail);
            cs.setString(4, InCategoriaProfe);
            cs.setString(5, InTipo);
            cs.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            return false;
        }
    }
    
    
    
    
}