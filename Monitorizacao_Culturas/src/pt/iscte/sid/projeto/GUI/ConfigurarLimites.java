package pt.iscte.sid.projeto.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;



/**
 * Esta classe constroi o interface gráfico para a configuração de limites 
 * 
 * @author grupo 21
 *
 */
public class ConfigurarLimites extends JFrame {
    
    private DatabaseMiddleManForAdministrador databaseConnection;
    private JFrame frame;
    private JPanel contentPanel;    
 
    
    /**
     * Metodo para lancar a interface com ligacao a base de dados
     * 
     * @param databaseConnection conexao a base de dados mysql
     */
    
    public ConfigurarLimites(DatabaseMiddleManForAdministrador databaseConnection) {
        this.databaseConnection = databaseConnection;
        startConfigurarLimites();
    }
    
 
    /**
     * Metodo para ocultar a janela de configuração de limites
     */
    
    private void closeWindow() {
        frame.setVisible(false);
    }

    
    /**
     * Cria a janela do administrador
     */
    public void startConfigurarLimites() {
        frame= new JFrame();
        frame.setTitle("Configurar Limites de Vari\u00E1veis de Sistema");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 850, 600);
        frame.setVisible(true);
        LoginWindow.centerWindow(frame);
        frame.setResizable(false);
        setTitle("Configurar Limites de Vari\u00E1veis de Sistema");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100,  850, 600);
        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
       // setContentPane(contentPanel);
        contentPanel.setLayout(null);
        
        ImageIcon img = new ImageIcon (ConfigurarLimites.class.getResource("/images/configurarLimites.png"));
        
        JTextField TmpMax = new JTextField();
        TmpMax.setBounds(648, 195, 86, 29);
        contentPanel.add(TmpMax);
        TmpMax.setColumns(10);
        
        JTextField TmpMin = new JTextField();
        TmpMin.setColumns(10);
        TmpMin.setBounds(648, 237, 86, 29);
        contentPanel.add(TmpMin);
        
        JTextField LumMax = new JTextField();
        LumMax.setColumns(10);
        LumMax.setBounds(648, 295, 86, 29);
        contentPanel.add(LumMax);
        
        JTextField  LumMin = new JTextField();
        LumMin.setColumns(10);
        LumMin.setBounds(648, 340, 86, 29);
        contentPanel.add(LumMin);
        
        JButton BotaoVoltar = new JButton("Voltar");
        BotaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AreaAdmin(databaseConnection);
                closeWindow();
            }
        });
        BotaoVoltar.setBounds(721, 509, 85, 30);
        contentPanel.add(BotaoVoltar);

        
        String[] Invesheader={"Id","LimiteInfTemp","LimiteSupTemp","LimiteInfLuz","LimiteSupLuz"};
        String[][] Invesdata=getList(databaseConnection.getSistema());
        DefaultTableModel Invesmodel = new DefaultTableModel(Invesdata,Invesheader);
        JTable table = new JTable(Invesmodel);
        table.setCellSelectionEnabled(true); 
       // Investable.setCellSelectionEnabled(true);
        ListSelectionModel selectInves= table.getSelectionModel();
        selectInves.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane Invesjs=new JScrollPane(table);
        Invesjs.setVisible(true);
        Invesjs.setBounds(46, 191, 475, 229);
        contentPanel.add(Invesjs);
        
        JButton btnAdicionarNovosLimites = new JButton("Adicionar Novos Limites");
        btnAdicionarNovosLimites.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!TmpMax.getText().equals("") && !TmpMin.getText().equals("")
                        && !LumMax.getText().equals("") && !LumMin.getText().equals("")
                        && Integer.parseInt(TmpMax.getText()) >= Integer.parseInt(TmpMin.getText())
                        && Integer.parseInt(LumMax.getText()) >= Integer.parseInt(LumMin.getText())){
                    
                    if(databaseConnection.createSistema(Integer.parseInt(TmpMin.getText()), Integer.parseInt(TmpMax.getText())
                            , Integer.parseInt(LumMin.getText()), Integer.parseInt(LumMax.getText())))
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Succeso",
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Ocorreu um erro",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(frame.getContentPane(),
                            "Os limites inferiores nao podem ser maiores que os inferiores",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
            }
        });
        
        
        JTextField IdtextField = new JTextField();
        IdtextField.setColumns(10);
        IdtextField.setBounds(202, 447, 70, 29);
        contentPanel.add(IdtextField);
        
        JLabel  lblIdDosLimites = new JLabel("ID dos Limites");
        lblIdDosLimites.setBounds(120, 453, 86, 16);
        contentPanel.add(lblIdDosLimites);
        
        btnAdicionarNovosLimites.setBounds(602, 390, 177, 30);
        contentPanel.add(btnAdicionarNovosLimites);
        
        JButton btnAlteracao = new JButton("Submeter Altera\u00E7\u00F5es");
        btnAlteracao.setBounds(281, 472, 162, 30);
        btnAlteracao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            if(!TmpMax.getText().equals("") && !TmpMin.getText().equals("")
                        && !LumMax.getText().equals("") && !LumMin.getText().equals("")
                        && !IdtextField.getText().equals("")
                        && Integer.parseInt(TmpMax.getText()) >= Integer.parseInt(TmpMin.getText())
                        && Integer.parseInt(LumMax.getText()) >= Integer.parseInt(LumMin.getText())){
                    
                    if(databaseConnection.updateSistema(Integer.parseInt(IdtextField.getText()),Integer.parseInt(TmpMin.getText()), Integer.parseInt(TmpMax.getText())
                            , Integer.parseInt(LumMin.getText()), Integer.parseInt(LumMax.getText())))
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Succeso",
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Ocorreu um erro",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(frame.getContentPane(),
                            "Os limites inferiores nao podem ser maiores que os inferiores",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
            
                
            }
        });
        contentPanel.add(btnAlteracao);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(281, 431, 162, 30);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(IdtextField.getText().equals(""))
                    JOptionPane.showMessageDialog(frame.getContentPane(),
                            "O campo ID esta vazio",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                else{
                    if(databaseConnection.deleteSistema(Integer.parseInt(IdtextField.getText())))
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Successo",
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Ocurreu um erro",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        contentPanel.add(btnEliminar);
        frame.getContentPane().add(contentPanel);
        
        JButton ButtonLoadTable = new JButton("Atualizar tabela");
        ButtonLoadTable.setBounds(612, 431, 162, 30);
        ButtonLoadTable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPanel.remove(Invesjs);
                String[] Invesheader={"Id","LimiteInfTemp","LimiteSupTemp","LimiteInfLuz","LimiteSupLuz"};
                String[][] Invesdata=getList(databaseConnection.getSistema());
                DefaultTableModel Invesmodel = new DefaultTableModel(Invesdata,Invesheader);
                JTable table = new JTable(Invesmodel);
                //table.setCellSelectionEnabled(true);
                // Investable.setCellSelectionEnabled(true);
                ListSelectionModel selectInves= table.getSelectionModel();
                selectInves.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                JScrollPane Invesjs=new JScrollPane(table);
                Invesjs.setVisible(true);
                Invesjs.setBounds(55, 195, 466, 225);
                contentPanel.add(Invesjs);
                
            }
        });
        contentPanel.add(ButtonLoadTable);
        
        JLabel imagemFundo = new JLabel("");
        imagemFundo.setBounds(0, 0, 834, 420);
        Image imgm = img.getImage().getScaledInstance(imagemFundo.getWidth(), imagemFundo.getHeight(), Image.SCALE_SMOOTH);
        
        
        
        imagemFundo.setIcon(new ImageIcon (imgm));
        
        contentPanel.add(imagemFundo);
    }
    
    /**
     * Este metodo devolve uma matriz de strings com os valores dos limites definidos no sistema
     * @param arg string com os valores do sistema concatenados
     * @return matriz de string
     */
      private String[][] getList(String arg)
    {
        String[] lines = arg.split("BREAKLINE");
        String[][] linesCsv = new String[lines.length][];
        
        for (int i=0; i<lines.length; i++) {
            linesCsv[i] = lines[i].split("BREAKCOLUMN");
        }
        return linesCsv;
    }
}
