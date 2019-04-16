/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pt.iscte.sid.projeto.Machine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * //RUD no investigador(Done e testado mas nao profundamente), RUD no administrador,  CD no variaveis , CUD no Sistema, X no Sp_CriarUtilizador
 * @author Sérgio
 */
public class DatabaseMiddleManForAdministrador extends DatabaseMiddleManGeneral{
    
    public DatabaseMiddleManForAdministrador(String username, String Password) {
        super(username, Password);
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
                        +"\nEmail do Investigador:"+rs.getString(3) +"\nCategoria do Investigador " + rs.getString(4) +"\n\n";
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        
        return TableResult;
    }
    
    /**
     * Atualiza um investigador
     * @param IdCultura
     * @param NomeCultura
     * @param DescricaoCultura
     * @return
     */
    public boolean UpdateInvestigador(int IdInvestigador, String Email, String Nome, String CategoriaProfe)
    {
        try {
            String query =
                    " update investigador set email= '" + Email
                    + "', NomeInvestigador='" + Nome+ "', CategoriaProfe='"+ CategoriaProfe
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
     * Apaga um investigador
     * @param IdCultura
     * @return
     */
    public boolean DeleteInvestigador(int IdInvestigador)
    {
        try {
            String query = " delete from investigador where idinvestigador=" + IdInvestigador;
            PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
            preparedStmt.execute();
            
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao executar a accao");
            return false;
        }
    }
    
}