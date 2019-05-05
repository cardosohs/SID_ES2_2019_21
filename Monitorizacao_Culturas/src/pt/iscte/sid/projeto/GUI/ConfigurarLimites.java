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

public class ConfigurarLimites extends JFrame {
    
    private DatabaseMiddleManForAdministrador databaseConnection;
    private JFrame frame;
    private JPanel contentPanel;
    
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DatabaseMiddleManForAdministrador d = new DatabaseMiddleManForAdministrador("EmailAdmin", "12345");
                    
                    ConfigurarLimites frame = new ConfigurarLimites(d);
                    //frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private void CloseWindow() {
        frame.setVisible(false);
    }
    
    public ConfigurarLimites(DatabaseMiddleManForAdministrador databaseConnection) {
        this.databaseConnection = databaseConnection;
        StartConfigurarLimites();
    }
    
    public ConfigurarLimites() {
        StartConfigurarLimites();
    }
    
    
    
    
    
    /**
     * Create the frame.
     */
    public void StartConfigurarLimites() {
        frame= new JFrame();
        frame.setTitle("Configurar Limites de Vari\u00E1veis de Sistema");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 850, 600);
        frame.setVisible(true);
        setTitle("Configurar Limites de Vari\u00E1veis de Sistema");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100,  850, 600);
        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
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
                CloseWindow();
            }
        });
        BotaoVoltar.setBounds(721, 509, 85, 30);
        contentPanel.add(BotaoVoltar);
        
       /* JTextField  txtFaltaAdaptarPara = new JTextField();
        txtFaltaAdaptarPara.setText("Falta adaptar para receber a tabela Sistema; Ver como em: https://www.youtube.com/watch?v=6cNYUc2PIag");
        txtFaltaAdaptarPara.setColumns(10);
        txtFaltaAdaptarPara.setBounds(157, 260, 292, 30);
        contentPanel.add(txtFaltaAdaptarPara);*/
        
        
        String[] Invesheader={"IdVariavel","Nome"};
        String[][] Invesdata=GetList(databaseConnection.getVariaveis());
        DefaultTableModel Invesmodel = new DefaultTableModel(Invesdata,Invesheader);
        JTable table = new JTable(Invesmodel);
        table.setCellSelectionEnabled(true); 
       // Investable.setCellSelectionEnabled(true);
        ListSelectionModel selectInves= table.getSelectionModel();
        selectInves.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane Invesjs=new JScrollPane(table);
        Invesjs.setVisible(true);
        Invesjs.setBounds(157, 260, 292, 100);
        contentPanel.add(Invesjs);
        
        JButton btnAdicionarNovosLimites = new JButton("Adicionar Novos Limites");
        btnAdicionarNovosLimites.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!TmpMax.getText().equals("") && !TmpMin.getText().equals("")
                        && !LumMax.getText().equals("") && !LumMin.getText().equals("")
                        && Integer.parseInt(TmpMax.getText()) >= Integer.parseInt(TmpMin.getText())
                        && Integer.parseInt(LumMax.getText()) >= Integer.parseInt(LumMin.getText())){
                    
                    if(databaseConnection.CreateSistema(Integer.parseInt(TmpMin.getText()), Integer.parseInt(TmpMax.getText())
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
        
        JLabel imagemFundo = new JLabel("");
        imagemFundo.setBounds(0, 0, 834, 420);
        Image imgm = img.getImage().getScaledInstance(imagemFundo.getWidth(), imagemFundo.getHeight(), Image.SCALE_SMOOTH);
        
        
        
        imagemFundo.setIcon(new ImageIcon (imgm));
        
        contentPanel.add(imagemFundo);
        
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
                    
                    if(databaseConnection.UpdateSistema(Integer.parseInt(IdtextField.getText()),Integer.parseInt(TmpMin.getText()), Integer.parseInt(TmpMax.getText())
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
                    if(databaseConnection.DeleteSistema(Integer.parseInt(IdtextField.getText())))
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
        frame.add(contentPanel);
    }
      private String[][] GetList(String arg)
    {
        String[] lines = arg.split("BREAKLINE");
        String[][] linesCsv = new String[lines.length][];
        
        for (int i=0; i<lines.length; i++) {
            linesCsv[i] = lines[i].split("BREAKCOLUMN");
        }
        return linesCsv;
    }
}
