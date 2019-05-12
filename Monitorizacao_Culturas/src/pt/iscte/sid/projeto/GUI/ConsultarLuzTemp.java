package pt.iscte.sid.projeto.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;

/**
 * 
 * @author Grupo 21
 * Esta classe e referente a interface grafico para a consulta da luz e temperatura
 *
 */
public class ConsultarLuzTemp extends JFrame {
    
    private DatabaseMiddleManForInvestigador databaseConnection;
    private JPanel contentPanel;
    private JFrame frame;

    /**
     * Lanca a interface referente a consulta de luz e temperatura
     * 
     * @param databaseConnection  esta parametro é a ligacao da base de dados
     */
    public ConsultarLuzTemp(DatabaseMiddleManForInvestigador databaseConnection) {
        this.databaseConnection = databaseConnection;
        startConsultarLuzTemp();
    }
    
    /*
     * Metodo para fechar a janela
     */
    private void closeWindow() {
        frame.setVisible(false);
    }
    
    /**
     * Cria a janela sobre a informacao da luz e temperatura
     */
    public void startConsultarLuzTemp() {
        frame= new JFrame();
        frame.setTitle("Medi\u00E7\u00F5es de Sistema");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 850, 600);
        frame.setVisible(true);
        LoginWindow.centerWindow(frame);
        frame.setResizable(false);
        setTitle("Medi\u00E7\u00F5es de Sistema");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 600);
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(null);
        
        ImageIcon background = new ImageIcon(ConsultarLuzTemp.class.getResource("/images/consultarLuzTemp.png"));
        
        JButton button = new JButton("Voltar");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AreaInvestigador(databaseConnection);
                closeWindow();
            }
        });
        button.setBounds(728, 469, 85, 30);
        contentPanel.add(button);
        
        JLabel backPhoto = new JLabel("");
        backPhoto.setBounds(0, 0, 834, 523);
        
        Image img = background.getImage().getScaledInstance(backPhoto.getWidth(), backPhoto.getHeight(), Image.SCALE_SMOOTH);
        backPhoto.setIcon(new ImageIcon(img));
        
        String[] headerLuz={"Id","Valor","Data/Hora"};
        String[][] dataLuz=getList(databaseConnection.getMedicoesLuz());
        DefaultTableModel modelLuz = new DefaultTableModel(dataLuz,headerLuz);
        JTable tableLuz = new JTable(modelLuz);
        ListSelectionModel selectLuz= tableLuz.getSelectionModel();
        selectLuz.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane jsLuz=new JScrollPane(tableLuz);
        jsLuz.setVisible(true);
        jsLuz.setBounds(48, 218, 359, 204);
        
        contentPanel.add(jsLuz);
        
        String[] headerTemp={"Id","Valor","Data/Hora"};
        String[][] dataTemp=getList(databaseConnection.getMedicoesTemperatura());
        DefaultTableModel modelTemp = new DefaultTableModel(dataTemp,headerTemp);
        JTable tableTemp = new JTable(modelTemp);
        ListSelectionModel selectTemp = tableTemp.getSelectionModel();
        selectTemp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane jsTemp=new JScrollPane(tableTemp);
        jsTemp.setVisible(true);
        jsTemp.setBounds(417, 218, 366, 204);
        
        contentPanel.add(jsTemp);
        
        String[] headerSistema={"TempInf","TempSup","LuzInf","LuzSup"};
        String[][] dataSistema=getList(databaseConnection.getSistema());
        DefaultTableModel modelSistema = new DefaultTableModel(dataSistema,headerSistema);
        JTable tableSistema = new JTable(modelSistema);
        ListSelectionModel selectSistema = tableSistema.getSelectionModel();
        selectSistema.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane jsSistema=new JScrollPane(tableSistema);
        jsSistema.setVisible(true);
        jsSistema.setBounds(342, 433, 359, 77);
        
        contentPanel.add(jsSistema);
        
        contentPanel.add(backPhoto);
        frame.getContentPane().add(contentPanel);
    }
    
    /**
     * Este metodo devolve uma matriz de strings com as tabelas das medicoes da luz e temperatura
     * @param arg recebe uma string 
     * @return devolve uma matriz de strings
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
