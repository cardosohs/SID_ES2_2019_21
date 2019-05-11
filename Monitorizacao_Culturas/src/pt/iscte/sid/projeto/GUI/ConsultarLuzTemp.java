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

public class ConsultarLuzTemp extends JFrame {
    
    private DatabaseMiddleManForInvestigador databaseConnection;
    private JPanel contentPanel;
    private JFrame frame;
    /**
     * Launch the application.
     */
  /*  public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ConsultarLuzTemp frame = new ConsultarLuzTemp(new DatabaseMiddleManForInvestigador("svbro@iscte-iul.com", "123"));
                    // frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    */

    
    public ConsultarLuzTemp(DatabaseMiddleManForInvestigador databaseConnection) {
        this.databaseConnection = databaseConnection;
        StartConsultarLuzTemp();
    }
    
    
    private void CloseWindow() {
        frame.setVisible(false);
    }
    
    /**
     * Create the frame.
     */
    public void StartConsultarLuzTemp() {
        frame= new JFrame();
        frame.setTitle("Medi\u00E7\u00F5es de Sistema");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 850, 600);
        frame.setVisible(true);
        setTitle("Medi\u00E7\u00F5es de Sistema");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 600);
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        //setContentPane(contentPanel);
        contentPanel.setLayout(null);
        
        ImageIcon background = new ImageIcon(ConsultarLuzTemp.class.getResource("/images/consultarLuzTemp.png"));
        
        JButton button = new JButton("Voltar");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AreaInvestigador(databaseConnection);
                CloseWindow();
            }
        });
        button.setBounds(728, 469, 85, 30);
        contentPanel.add(button);
        
        JLabel backPhoto = new JLabel("");
        backPhoto.setBounds(0, 0, 834, 523);
        
        Image img = background.getImage().getScaledInstance(backPhoto.getWidth(), backPhoto.getHeight(), Image.SCALE_SMOOTH);
        backPhoto.setIcon(new ImageIcon(img));
        
        String[] headerLuz={"Id","Valor","Data/Hora"};
        String[][] dataLuz=GetList(databaseConnection.getMedicoesLuz());
        DefaultTableModel modelLuz = new DefaultTableModel(dataLuz,headerLuz);
        JTable tableLuz = new JTable(modelLuz);
        //Admintable.setCellSelectionEnabled(true);
        ListSelectionModel selectLuz= tableLuz.getSelectionModel();
        selectLuz.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane jsLuz=new JScrollPane(tableLuz);
        jsLuz.setVisible(true);
        jsLuz.setBounds(55, 218, 344, 204);
        
        contentPanel.add(jsLuz);
        
        String[] headerTemp={"Id","Valor","Data/Hora"};
        String[][] dataTemp=GetList(databaseConnection.getMedicoesTemperatura());
        DefaultTableModel modelTemp = new DefaultTableModel(dataTemp,headerTemp);
        JTable tableTemp = new JTable(modelTemp);
        //Admintable.setCellSelectionEnabled(true);
        ListSelectionModel selectTemp = tableTemp.getSelectionModel();
        selectTemp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane jsTemp=new JScrollPane(tableTemp);
        jsTemp.setVisible(true);
        jsTemp.setBounds(435, 221, 344, 197);
        
        contentPanel.add(jsTemp);
        
        String[] headerSistema={"TempInf","TempSup","LuzInf","LuzSup"};
        String[][] dataSistema=GetList(databaseConnection.getSistema());
        DefaultTableModel modelSistema = new DefaultTableModel(dataSistema,headerSistema);
        JTable tableSistema = new JTable(modelSistema);
        //Admintable.setCellSelectionEnabled(true);
        ListSelectionModel selectSistema = tableSistema.getSelectionModel();
        selectSistema.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane jsSistema=new JScrollPane(tableSistema);
        jsSistema.setVisible(true);
        jsSistema.setBounds(350, 450, 292, 50);
        
        contentPanel.add(jsSistema);
        
        
        
        
        
        contentPanel.add(backPhoto);
        frame.getContentPane().add(contentPanel);
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
