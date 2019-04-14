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
 *  // CRUD culturas(done), R variavel(done), CRUD VM  (done)  CRUD medicoes R sistema(done) R medicoestmp e R medicoesluz
 * @author Sérgio
 */
public class DatabaseMiddleManForInvestigador {
    private final String DatabaseName="g21origem";
    private String UsernameInvestigador;
    private String PasswordInvestigador;
    private boolean failed;
    private int IdInvestigador;
    private ArrayList<Integer> CulturasDoInvestigador = new ArrayList<>();
    private ArrayList<Integer> VariaviesDaDatabase = new ArrayList<>();
    
    private Connection DatabaseConnection;
    
    public DatabaseMiddleManForInvestigador(String username, String Password)
    {
        StartConnection(username, Password);
        GetInvetigadorId();
    }
    
    
    /**
     * Inicia a ligacao a BD g21origem
     * @param username
     * @param Password
     */
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
            
            
        } catch (SQLException ex) {
            System.out.println("Failed to login ");
            this.failed=true;
        }
    }
    
    
    /**
     * Termina a ligacao a BD
     * @return
     */
    public boolean CloseConnection()
    {
        try {
            DatabaseConnection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMiddleManForInvestigador.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Vai buscar o Id do investigador
     */
    private void GetInvetigadorId() {
        String DatabaseDriver = "com.mysql.cj.jdbc.Driver";
        String DatabaseURL = "jdbc:mysql://localhost/"+DatabaseName+"?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
        
        try {
            
            Class.forName(DatabaseDriver);
            Connection DatabaseTMPConnection = DriverManager.getConnection(DatabaseURL, "root", "");
            Statement stmt=DatabaseTMPConnection.createStatement();
            
            
            String query = "select * from investigador where email='" + UsernameInvestigador+"';";
            ResultSet rs=stmt.executeQuery(query);
            
            
            while(rs.next()){
                this.IdInvestigador=rs.getInt(1);
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
     * @return
     */
    public boolean Failed()
    {
        return failed;
    }
    
    
    /**
     * Retorna o id do investigador
     * @return
     */
    public int getMyId()
    {
        return IdInvestigador;
    }
    
    
    /**
     * Cria uma nova cultura associada ao investigador
     * @param NomeDaCultura
     * @param DescricaoDaCultura
     * @return
     */
    public boolean CreateCultura(String NomeDaCultura, String DescricaoDaCultura)
    {
        try {
            String query1 = " insert into cultura(IdInvestigador,NomeCultura,DescricaoCultura) values (?, ?, ?)";
            PreparedStatement preparedStmt1 = DatabaseConnection.prepareStatement(query1);
            
            preparedStmt1.setInt(1, IdInvestigador);
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
     * @return
     */
    public String getCulturas()
    {
        String TableResult="";
        try{
            Statement stmt=DatabaseConnection.createStatement();
            String query = "select * from cultura where idinvestigador=" + IdInvestigador;
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                TableResult += "\nCultura \nID da cultura:" + rs.getString(1)+"\nNome da cultura:"+rs.getString(3)+"\nDescricao da cultura:"+rs.getString(4) + "\n\n";
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
     * @param IdCultura
     * @param NomeCultura
     * @param DescricaoCultura
     * @return
     */
    
    public boolean UpdateCultura(int IdCultura, String NomeCultura, String DescricaoCultura)
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
     * @param IdCultura
     * @return
     */
    public boolean DeleteCultura(int IdCultura)
    {
        getCulturas();
        if(CulturasDoInvestigador.contains(IdCultura)){
            try {
                String query = " delete from cultura where idcultura=" + IdCultura;
                PreparedStatement preparedStmt = DatabaseConnection.prepareStatement(query);
                preparedStmt.execute();
                CulturasDoInvestigador.remove(IdCultura);
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
     * vai buscar as variaveis
     * @return
     */
    public String getVariaveis()
    {
        String TableResult="";
        try{
            Statement stmt=DatabaseConnection.createStatement();
            String query = "select * from variaveis";
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                if(!VariaviesDaDatabase.contains(Integer.parseInt(rs.getString(1))))
                    VariaviesDaDatabase.add(Integer.parseInt(rs.getString(1)));
                TableResult += "\nVariavel \nID da variavel:" + rs.getString(1)+"\nNome da variavel:"+rs.getString(2) +"\n\n";
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        
        return TableResult;
    }
    
    public boolean CreateVariavelMedida(int IdCultura, int IdVariavel, int LimiteInferior, int LimiteSuperior)
    {
        if(LimiteSuperior>LimiteInferior){
            try {
                getCulturas();
                getVariaveis();
                if(CulturasDoInvestigador.contains(IdCultura) && VariaviesDaDatabase.contains(IdVariavel) ){
                    String query1 = " insert into variaveismedidas(IdCultura,IdVariavel,LimiteInferior,LimiteSuperior) values (?, ?, ?,?)";
                    PreparedStatement preparedStmt1 = DatabaseConnection.prepareStatement(query1);
                    // System.out.println(query1);
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
                        TableResult += "\nVariavel Medida \nID da cultura:" + rs.getString(1)+"\nId da variavel:"+rs.getString(2)+"\nLimite Inferior:"+rs.getString(3)+"\nLimite Superior:"+rs.getString(4) + "\n\n";
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
    
    
    
    public boolean UpdateVariaveisMedidas(int IdCultura, int IdVariavel, int LimiteInferior, int LimiteSuperior)
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
    
    
    
    public boolean DeleteVariaveisMedidas(int IdCultura, int IdVariavel)
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
    public String getSistema()
    {
        String TableResult="";
        try{
            Statement stmt=DatabaseConnection.createStatement();
            String query = "select * from sistema";
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                TableResult +=
                        "\nSistema \nID do Sistema:" + rs.getString(1)+"\nLimite inferior da temperatira:"
                        +rs.getString(2)+ "\nLimite inferior da temperatira:"
                        +rs.getString(3)+ "\nLimite superior da temperatira:"
                        +rs.getString(4)+ "\nLimite inferior da Luz:"
                        +rs.getString(5)+ "\nLimite superior da Luz:"
                        +"\n\n";
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        
        return TableResult;
    }
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
                        "\nMedicao Temperatura \nID da medicao:" + rs.getString(1)
                        +"\nData da Medicao:"+rs.getString(2)
                        +"\nvalor da medicao:"+rs.getString(3) +"\n\n";
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        
        return TableResult;
    }
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
                        "\nMedicao Luz \nID da medicao:" + rs.getString(1)
                        +"\nData da Medicao:"+rs.getString(2)
                        +"\nvalor da medicao:"+rs.getString(3) +"\n\n";
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        
        return TableResult;
    }
    
    
    
    public boolean CreateMedicoes(int IdCultura, int IdVariavel,double ValorMed)
    {
        getCulturas();
        getVariaveis();
        if(CulturasDoInvestigador.contains(IdCultura) && VariaviesDaDatabase.contains(IdVariavel) ){
            String TableResult="";
            try{
                Statement stmt=DatabaseConnection.createStatement();
                String query1 = "insert into medicoes(IdCultura,IdVariavel,ValorMed) values(?,?,?)";
                PreparedStatement preparedStmt1 = DatabaseConnection.prepareStatement(query1);
                // System.out.println(query1);
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
                    TableResult += "\nMedicao\nId da Medicao"+rs.getString(1) +"DataHora:" + rs.getString(4)+"\nValor:"+rs.getString(5)+"\n\n";
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
    
    public boolean UpdateMedicao(int IdMed ,int IdCultura, int IdVariavel,double ValorMed)
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
    
    
    
    public boolean DeleteMedicao(int IdMed,int IdCultura, int IdVariavel)
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
    
    
    
}

