package pt.iscte.sid.projeto.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;

public class ManutencaoVariaveis extends JFrame {
    
    private DatabaseMiddleManForAdministrador databaseConnection;
    private JPanel contentPanel;
    //  private JTextField textField;
    // private JTextField txtFaltaAdaptarPara;
    private JFrame frame;
    
    /**
     * Launch the application.
     */
   /* public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DatabaseMiddleManForAdministrador d = new DatabaseMiddleManForAdministrador("EmailAdmin", "12345");
                    
                    ManutencaoVariaveis frame = new ManutencaoVariaveis(d);
                    //  frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    */
    public ManutencaoVariaveis(DatabaseMiddleManForAdministrador databaseConnection) {
        this.databaseConnection = databaseConnection;
        StartManutencaoVariaveis();
    }
    

    
    private void CloseWindow() {
        frame.setVisible(false);
    }
    
    /**
     * Create the frame.
     */
    public void StartManutencaoVariaveis() {
        frame= new JFrame();
        frame.setTitle("Medi\u00E7\u00F5es de Sistema");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 850, 600);
        frame.setVisible(true);
        frame.setResizable(false);
        setTitle("Manuten\u00E7\u00E3o de Vari\u00E1veis");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100,  834, 530);
        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
       //ZZ setContentPane(contentPanel);
        contentPanel.setLayout(null);
        
        ImageIcon img = new ImageIcon(ManutencaoVariaveis.class.getResource("/images/manutencaoVariaveis.png"));
        
        
        JTextField textField = new JTextField();
        textField.setBounds(176, 394, 350, 29);
        contentPanel.add(textField);
        textField.setColumns(10);
        
        JButton btnAdicionarVarivel = new JButton("Adicionar Vari\u00E1vel");
        btnAdicionarVarivel.setBounds(529, 394, 142, 29);
        btnAdicionarVarivel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!textField.getText().equals(""))
                    if(databaseConnection.CreateVariavel(textField.getText()))
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Sucesso",
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Ocorreu um erro",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(frame.getContentPane(),
                            "O campo nao pode estar vazio",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
            }
        });
        
        contentPanel.add(btnAdicionarVarivel);
        
        JButton btnEliminarVarivel = new JButton("Eliminar Vari\u00E1vel");
        btnEliminarVarivel.setBounds(681, 393, 130, 30);
        btnEliminarVarivel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!textField.getText().equals(""))
                    if(databaseConnection.DeleteVariavel(Integer.parseInt(textField.getText())))
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Sucesso",
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Ocorreu um erro",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(frame.getContentPane(),
                            "O campo nao pode estar vazio",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
            }
        });
        contentPanel.add(btnEliminarVarivel);
        
        JButton button_1 = new JButton("Voltar");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AreaAdmin(databaseConnection);
                CloseWindow();
            }
        });
        button_1.setBounds(708, 450, 85, 30);
        contentPanel.add(button_1);
        
        /*JTextField txtFaltaAdaptarPara = new JTextField();
        txtFaltaAdaptarPara.setText("Falta adaptar para receber a tabela Variaveis; Ver como em: https://www.youtube.com/watch?v=6cNYUc2PIag");
        txtFaltaAdaptarPara.setColumns(10);
        txtFaltaAdaptarPara.setBounds(220, 243, 429, 30);
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
        Invesjs.setBounds(176, 194, 495, 182);
        contentPanel.add(Invesjs);
        
        JLabel imagemFundo = new JLabel("");
        imagemFundo.setBounds(10, 13, 818, 467);
        
        Image imgm = img.getImage().getScaledInstance(imagemFundo.getWidth(), imagemFundo.getHeight(), Image.SCALE_SMOOTH);
        
        imagemFundo.setIcon(new ImageIcon (imgm));
        
        contentPanel.add(imagemFundo);
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
