package pt.iscte.sid.projeto.Machine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Grupo 21
 * Faz todas as operaçoes que o investigador pode fazer na BD, isto é, login, logout e operacoes CRUD(quando aplicavel)
 */
public class DatabaseMiddleManForInvestigador extends DatabaseMiddleManGeneral{
 
    private ArrayList<Integer> CulturasDoInvestigador = new ArrayList<>();


    public DatabaseMiddleManForInvestigador(String username, String Password) {
        super(username, Password);
    }
 
    
    /**
     * Cria uma nova cultura associada ao investigador
     * @param NomeDaCultura recebe como argumento uma cultura
     * @param DescricaoDaCultura recebe como argumento uma descricao
     * @return boolean para verificar se foi criado uma cultura ou nao
     */
    public boolean createCultura(String NomeDaCultura, String DescricaoDaCultura)
    {
        try {
            String query1 = " insert into cultura(IdInvestigador,NomeCultura,DescricaoCultura) values (?, ?, ?)";
            PreparedStatement preparedStmt1 = DatabaseConnection.prepareStatement(query1);
            
            preparedStmt1.setInt(1, Id);
            preparedStmt1.setString (2, NomeDaCultura);
            preparedStmt1.setString (3, DescricaoDaCultura);
            
            preparedStmt1.execute();
        } catch (SQLException ex) {
            return false;
        }
        return true;
        
    }
    
    /**
     * vai buscar as culturas do investigador
     * @return string com as culturas do investigador
     */
    public String getCulturas()
    {
        String TableResult="";
        try{
            Statement stmt=DatabaseConnection.createStatement();
            String query = "select * from cultura where idinvestigador=" + Id;
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                TableResult +=rs.getString(1)+"BREAKCOLUMN"+rs.getString(2)+"BREAKCOLUMN"+rs.getString(3)+"BREAKCOLUMN"+rs.getString(4)+ "BREAKLINE";
                if(!CulturasDoInvestigador.contains(Integer.parseInt(rs.getString(1))))
                    CulturasDoInvestigador.add(Integer.parseInt(rs.getString(1)));
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        
        return TableResult;
    }
    
    /**
     * Atualiza uma cultura do investigador
     * @param IdCultura recebe um id de uma cultura
     * @param NomeCultura recebe o nome de uma cultura
     * @param DescricaoCultura recebe uma descricao de uma cultura
     * @return
     */
    
    public boolean updateCultura(int IdCultura, String NomeCultura, String DescricaoCultura)
    {
        getCulturas();
        if(CulturasDoInvestigador.contains(IdCultura)){
            try {
                String query =
                        " update cultura set NomeCultura= '" + NomeCultura
                        + "', DescricaoCultura='" + DescricaoCultura+ "' where idcultura=" + IdCultura;
                PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
                preparedStmt.execute();
                
                return true;
            } catch (SQLException ex) {
                System.err.println("Erro ao executar a accao");
                return false;
            }
        }
        else{
            System.err.println("Erro, esta a mexer numa cultura que nao lhe pertence");
            return false;
        }
    }
    
    
    /**
     * Apaga uma cultura do investigador
     * @param IdCultura recebe um id de uma cultura
     * @return um boolean para saber se foi bem sucedido um delete de uma cultura
     */
    public boolean deleteCultura(int IdCultura)
    {
        getCulturas();
        if(CulturasDoInvestigador.contains(IdCultura)){
            try {
                String query = " delete from cultura where idcultura=" + IdCultura;
                PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
                preparedStmt.execute();
                removeFromList(CulturasDoInvestigador,IdCultura);
                return true;
            } catch (SQLException ex) {
                System.err.println("Erro ao executar a accao");
                return false;
            }
        }
        else{
            System.err.println("Erro, esta a mexer numa cultura que nao lhe pertence");
            return false;
        }
    }
    
    
    
    
    
    
    /**
     * Cria uma Variavel Medida associada à cultura do Investigador
     * @param IdCultura recebe um id de uma cultura
     * @param IdVariavel recebe um id de uma variavel
     * @param LimiteInferior recebe um limite inferior para uma variavel medida
     * @param LimiteSuperior recebe um limite superior para uma variavel medida
     * @return um boolean para saber se foi criado uma variavel medida
     */
    public boolean createVariavelMedida(int IdCultura, int IdVariavel, int LimiteInferior, int LimiteSuperior)
    {
        if(LimiteSuperior>LimiteInferior){
            try {
                getCulturas();
                getVariaveis();
                if(CulturasDoInvestigador.contains(IdCultura) && VariaviesDaDatabase.contains(IdVariavel) ){
                    String query1 = " insert into variaveismedidas(IdCultura,IdVariavel,LimiteInferior,LimiteSuperior) values (?, ?, ?,?)";
                    PreparedStatement preparedStmt1 = DatabaseConnection.prepareStatement(query1);
                    preparedStmt1.setInt(1, IdCultura);
                    preparedStmt1.setInt(2, IdVariavel);
                    preparedStmt1.setInt(3, LimiteInferior);
                    preparedStmt1.setInt(4, LimiteSuperior);
                    
                    preparedStmt1.execute();
                }
                else{
                    if(!VariaviesDaDatabase.contains(IdVariavel))
                        System.err.println("Esta variavel nao existe");
                    else
                        System.err.println("Esta a mexer numa cultura que nao lhe pertence");
                    return false;
                }
                
            } catch (SQLException ex) {
                return false;
            }
            return true;
        }
        else
            return false;
    }
    
    
    /**
     * Vai buscar as Variaveis Medidas associadas a uma cultura
     * @param IdCultura recebe um id de uma cultura
     * @param IdVariavel recebe um id de uma variavel
     * @return uma string com as variveis medidas
     */
    public String getVariaveisMedidas(int IdCultura, int IdVariavel)
    {
        getCulturas();
        getVariaveis();
        if(CulturasDoInvestigador.contains(IdCultura) && VariaviesDaDatabase.contains(IdVariavel) ){
            String TableResult="";
            try{
                Statement stmt=DatabaseConnection.createStatement();
                String query = "select * from variaveismedidas";
                ResultSet rs=stmt.executeQuery(query);
                while(rs.next()){
                    if(CulturasDoInvestigador.contains(Integer.parseInt(rs.getString(1))))
                        TableResult +=  rs.getString(1)+"BREAKCOLUMN"+rs.getString(2)
                                +"BREAKCOLUMN"+rs.getString(3)+"BREAKCOLUMN"+rs.getString(4)+ "BREAKLINE";
                }
            }catch(Exception e){
                System.out.println(e);
                return null;
            }
            
            return TableResult;
        }
        else{
            if(!VariaviesDaDatabase.contains(IdVariavel))
                System.err.println("Esta variavel nao existe");
            else
                System.err.println("Esta a mexer numa cultura que nao lhe pertence");
            return "";
        }
    }
    
    
    /**
     * Atualiza uma variavel medida
     * @param IdCultura recebe um id de uma cultura
     * @param IdVariavel recebe um id de uma variavel
     * @param LimiteInferior recebe um limite inferior para uma variavel medida
     * @param LimiteSuperior recebe um limite superior para uma variavel medida
     * @return um boolean para saber se foi feito um update a uma variavel medida
     */
    public boolean updateVariaveisMedidas(int IdCultura, int IdVariavel, int LimiteInferior, int LimiteSuperior)
    {
        if(LimiteSuperior>LimiteInferior){
            try {
                getCulturas();
                getVariaveis();
                if(CulturasDoInvestigador.contains(IdCultura) && VariaviesDaDatabase.contains(IdVariavel) ){
                    String query1 =
                            "update variaveismedidas set Limiteinferior= " + LimiteInferior +
                            " , LimiteSuperior=" + LimiteSuperior
                            + " where idcultura=" + IdCultura + " and idvariavel=" + IdVariavel;
                    PreparedStatement preparedStmt1 = DatabaseConnection.prepareStatement(query1);
                    preparedStmt1.execute();
                }
                else{
                    if(!VariaviesDaDatabase.contains(IdVariavel))
                        System.err.println("Esta variavel nao existe");
                    else
                        System.err.println("Esta a mexer numa cultura que nao lhe pertence");
                    return false;
                }
                
            } catch (SQLException ex) {
                return false;
            }
            return true;
        }
        else
            return false;
    }
    
    
    /**
     * Apaga uma variavel medida
     * @param IdCultura recebe um id de uma cultura
     * @param IdVariavel recebe um ide de uma variavel
     * @return um boolean para saber se foi bem sucedido um delete de uma variavel medida
     */
    public boolean deleteVariaveisMedidas(int IdCultura, int IdVariavel)
    {
        getCulturas();
        getVariaveis();
        if(CulturasDoInvestigador.contains(IdCultura) && VariaviesDaDatabase.contains(IdVariavel) ){
            try {
                String query = " delete from variaveismedidas where idcultura=" + IdCultura + " and IdVariavel=" + IdVariavel;
                PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
                preparedStmt.execute();
                
                return true;
            } catch (SQLException ex) {
                System.err.println("Erro ao executar a accao");
                return false;
            }
        }
        else{
            if(!VariaviesDaDatabase.contains(IdVariavel))
                System.err.println("Esta variavel nao existe");
            else
                System.err.println("Esta a mexer numa cultura que nao lhe pertence");
            return false;
        }
    }
   
 
    /**
     * Vai buscar os dados da tabela MedicoesTemperatura
     * @return uma string com as medicoes de temperatura
     */
    public String getMedicoesTemperatura()
    {
        String TableResult="";
        try{
            Statement stmt=DatabaseConnection.createStatement();
            String query = "select * from medicoestemp";
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                VariaviesDaDatabase.add(Integer.parseInt(rs.getString(1)));
                TableResult +=
                        rs.getString(1)
                        +"BREAKCOLUMN"+rs.getString(2)
                        +"BREAKCOLUMN"+rs.getString(3)
                        + "BREAKLINE";
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        
        return TableResult;
    }
    
    
    /**
     * Vai buscar os dados da tabela MedicoesLuz 
     * @return uma string com os dados da luminosidade
     */
    public String getMedicoesLuz()
    {
        String TableResult="";
        try{
            Statement stmt=DatabaseConnection.createStatement();
            String query = "select * from medicoesluz";
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                VariaviesDaDatabase.add(Integer.parseInt(rs.getString(1)));
                TableResult +=
                         rs.getString(1)
                        +"BREAKCOLUMN"+rs.getString(2)
                        +"BREAKCOLUMN"+rs.getString(3)
                        + "BREAKLINE";
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        
        return TableResult;
    }
    
    
    /**
     * Permite ao Investigador inserir dados na tabela Medicoes
     * @param IdCultura recebe um id de uma cultura
     * @param IdVariavel recebe um id de uma variavel
     * @param ValorMed recebe um valor medicao
     * @return um boolean para saber se foi conseguido a criacao de uma medicao
     */
    public boolean createMedicoes(int IdCultura, int IdVariavel,double ValorMed)
    {
        getCulturas();
        getVariaveis();
        if(CulturasDoInvestigador.contains(IdCultura) && VariaviesDaDatabase.contains(IdVariavel) ){
            String TableResult="";
            try{
                Statement stmt=DatabaseConnection.createStatement();
                String query1 = "insert into medicoes(IdCultura,IdVariavel,ValorMed) values(?,?,?)";
                PreparedStatement preparedStmt1 = DatabaseConnection.prepareStatement(query1);
                preparedStmt1.setInt(1, IdCultura);
                preparedStmt1.setInt(2, IdVariavel);
                preparedStmt1.setDouble(3, ValorMed);
                
                preparedStmt1.execute();
                
                return true;
            }catch(Exception e){
                System.out.println(e);
                return false;
            }
        }
        else{
            if(!VariaviesDaDatabase.contains(IdVariavel))
                System.err.println("Esta variavel nao existe");
            else
                System.err.println("Esta a mexer numa cultura que nao lhe pertence");
            return false;
        }
    }
    
    
    
    /**
     * Vai buscar os dados da tabela Medicoes
     * @param IdCultura recebe um id de uma cultura
     * @param IdVariavel recebe um id de uma variavel
     * @return uma string com as medicoes de uma variavel medida
     */
    public String getMedicoes(int IdCultura, int IdVariavel)
    {
        getCulturas();
        getVariaveis();
        if(CulturasDoInvestigador.contains(IdCultura) && VariaviesDaDatabase.contains(IdVariavel) ){
            String TableResult="";
            try{
                Statement stmt=DatabaseConnection.createStatement();
                String query = "select * from medicoes where IdCultura=" + IdCultura + " and IdVariavel=" + IdVariavel;
                ResultSet rs=stmt.executeQuery(query);
                while(rs.next()){
                    TableResult += rs.getString(1) +"BREAKCOLUMN" 
                            + rs.getString(4)+"BREAKCOLUMN"+rs.getString(5)+"BREAKLINE";
                }
            }catch(Exception e){
                System.out.println(e);
                return null;
            }
            
            return TableResult;
        }
        else{
            if(!VariaviesDaDatabase.contains(IdVariavel))
                System.err.println("Esta variavel nao existe");
            else
                System.err.println("Esta a mexer numa cultura que nao lhe pertence");
            return "";
        }
    }
    
    
    /**
     * Permite atualizar dados da tabela medicoes
     * @param IdMed recebe um id de uma medicao
     * @param IdCultura recebe um id de uma cultura
     * @param IdVariavel recebe um id de uma variavel
     * @param ValorMed recebe um valor medicao
     * @return um boolean para saber se foi bem sucedido um update a medicao
     */
    public boolean updateMedicao(int IdMed ,int IdCultura, int IdVariavel,double ValorMed)
    {
        getCulturas();
        getVariaveis();
        if(CulturasDoInvestigador.contains(IdCultura) && VariaviesDaDatabase.contains(IdVariavel) ){
            try {
                String query =
                        " update medicoes set ValorMed= '" + ValorMed
                        + "' where IdMed=" + IdMed;
                PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
                preparedStmt.execute();
                
                return true;
            } catch (SQLException ex) {
                System.err.println("Erro ao executar a accao");
                return false;
            }
        }
        else{
            if(!VariaviesDaDatabase.contains(IdVariavel))
                System.err.println("Esta variavel nao existe");
            else
                System.err.println("Esta a mexer numa cultura que nao lhe pertence");
            return false;
        }
    }
    
    
    /**
     * Permite ao Investigador apagar medicoes
     * @param IdMed recebe um id de uma medicao
     * @param IdCultura recebe um id de uma cultura
     * @param IdVariavel recebe um id de uma variavel
     * @return um boolean para saber se foi bem sucedido um delete a uma medicao
     */
    public boolean deleteMedicao(int IdMed,int IdCultura, int IdVariavel)
    {
        
        getCulturas();
        getVariaveis();
        if(CulturasDoInvestigador.contains(IdCultura) && VariaviesDaDatabase.contains(IdVariavel) ){
            try {
                String query =
                        " delete from medicoes where IdMed=" + IdMed;
                PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
                preparedStmt.execute();
                
                return true;
            } catch (SQLException ex) {
                System.err.println("Erro ao executar a accao");
                return false;
            }
        }
        else{
            if(!VariaviesDaDatabase.contains(IdVariavel))
                System.err.println("Esta variavel nao existe");
            else
                System.err.println("Esta a mexer numa cultura que nao lhe pertence");
            return false;
        }
    }
    
    /**
     * metodo para remover valores de um arraylis
     * @param array recebe um arraylist 
     * @param valor recebe um valor do arraylist
     */
    private void removeFromList(ArrayList array,int valor )
    {
        for(int i=0;i<array.size();i++)
            if(array.get(i).equals(valor))
                array.remove(i);
    }
}

