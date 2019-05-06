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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
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

public class ManutencaoUtilizadores extends JFrame {
    
    private DatabaseMiddleManForAdministrador databaseConnection;
    private JPanel contentPanel;
    private JFrame frame;
    private String[] AdminSelected= new String[3];
    private String[] InvesSelected= new String[4];
    private JTextField txtFaltaAdaptarPara;
    private JTextField txtFaltaAdaptarPara_1;
    
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ManutencaoUtilizadores(new DatabaseMiddleManForAdministrador("root", ""));
                    // frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public ManutencaoUtilizadores(DatabaseMiddleManForAdministrador databaseConnection) {
        this.databaseConnection = databaseConnection;
        StartManutencaoUtilizadores();
    }
    
    public ManutencaoUtilizadores() {
        StartManutencaoUtilizadores();
    }
    
    
    private void CloseWindow() {
        frame.setVisible(false);
    }
    
    /**
     * Create the frame.
     */
    public void StartManutencaoUtilizadores() {
        frame= new JFrame();
        frame.setTitle("Medi\u00E7\u00F5es de Sistema");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 850, 600);
        frame.setVisible(true);
        setTitle("Manuten\u00E7\u00E3o de Utilizadores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 600);
        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(null);
        
        ImageIcon image = new ImageIcon (ManutencaoUtilizadores.class.getResource("/images/manutencaoUtilizadores.png"));
        
        
        JButton btnAdicionarUtilizador = new JButton("Adicionar Novo Utilizador");
        btnAdicionarUtilizador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SubscreverUtilizador();
                CloseWindow();
            }
        });
        btnAdicionarUtilizador.setBounds(547, 497, 179, 30);
        contentPanel.add(btnAdicionarUtilizador);
        
        
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AreaAdmin(databaseConnection);
                CloseWindow();
            }
        });
        btnVoltar.setBounds(725, 497, 85, 30);
        contentPanel.add(btnVoltar);
        
        /*txtFaltaAdaptarPara = new JTextField();
        txtFaltaAdaptarPara.setText("Falta adaptar para receber a tabela Administradores; Ver como em: https://www.youtube.com/watch?v=6cNYUc2PIag");
        txtFaltaAdaptarPara.setBounds(80, 235, 292, 30);
        contentPanel.add(txtFaltaAdaptarPara);
        txtFaltaAdaptarPara.setColumns(10);
        txtFaltaAdaptarPara.setEditable(false);*/
        
        String[] Adminheader={"Id","Email","Nome"};
        String[][] Admindata=GetList(databaseConnection.getAdministradores());
        
        DefaultTableModel Adminmodel = new DefaultTableModel(Admindata,Adminheader);
        JTable Admintable = new JTable(Adminmodel);
        Admintable.setCellSelectionEnabled(true);
        ListSelectionModel selectAdmin= Admintable.getSelectionModel();
        selectAdmin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectAdmin.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int row = Admintable.getSelectedRow();
                AdminSelected[0] = Admintable.getModel().getValueAt(row, 0).toString();
                AdminSelected[1] = Admintable.getModel().getValueAt(row, 1).toString();
                AdminSelected[2] = Admintable.getModel().getValueAt(row, 2).toString();
            }
        });
        JScrollPane Adminjs=new JScrollPane(Admintable);
        Adminjs.setVisible(true);
        Adminjs.setBounds(80, 235, 292, 100);
        
        contentPanel.add(Adminjs);
        
        /*txtFaltaAdaptarPara_1 = new JTextField();
        txtFaltaAdaptarPara_1.setText("Falta adaptar para receber a tabela Investigadores");
        txtFaltaAdaptarPara_1.setColumns(10);
        txtFaltaAdaptarPara_1.setBounds(456, 244, 269, 39);
        contentPanel.add(txtFaltaAdaptarPara_1);*/
        String[] Invesheader={"Id","Email","Nome","Categoria"};
        String[][] Invesdata=GetList(databaseConnection.getInvestigador());
        DefaultTableModel Invesmodel = new DefaultTableModel(Invesdata,Invesheader);
        JTable Investable = new JTable(Invesmodel);
        Investable.setCellSelectionEnabled(true); 
        Investable.setCellSelectionEnabled(true);
        ListSelectionModel selectInves= Investable.getSelectionModel();
        selectInves.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectInves.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int row = Investable.getSelectedRow();
                InvesSelected[0] = Investable.getModel().getValueAt(row, 0).toString();
                InvesSelected[1] = Investable.getModel().getValueAt(row, 1).toString();
                InvesSelected[2] = Investable.getModel().getValueAt(row, 2).toString();
                InvesSelected[3] = Investable.getModel().getValueAt(row, 2).toString();
                System.out.println(Investable.getModel().getValueAt(row, 2).toString());
            }
        });
        JScrollPane Invesjs=new JScrollPane(Investable);
        Invesjs.setVisible(true);
        Invesjs.setBounds(456, 244, 269, 100);
        contentPanel.add(Invesjs);
        
        JTextField IdAdmin = new JTextField();
        IdAdmin.setColumns(10);
        IdAdmin.setBounds(143, 487, 31, 20);
        contentPanel.add(IdAdmin);
        
        JLabel imagemFundo = new JLabel("");
        imagemFundo.setBounds(0, 0, 834, 458);
        Image img = image.getImage().getScaledInstance(imagemFundo.getWidth(), imagemFundo.getHeight(), Image.SCALE_SMOOTH);
        
        imagemFundo.setIcon(new ImageIcon (img));
        
        contentPanel.add(imagemFundo);
        
        JTextField IdInves = new JTextField();
        IdInves.setColumns(10);
        IdInves.setBounds(143, 518, 31, 20);
        contentPanel.add(IdInves);
        
        JLabel lblIdAdministrador = new JLabel("ID Administrador");
        lblIdAdministrador.setBounds(48, 487, 97, 16);
        contentPanel.add(lblIdAdministrador);
        
        JLabel lblIdInvestigador = new JLabel("ID Investigador");
        lblIdInvestigador.setBounds(48, 518, 97, 16);
        contentPanel.add(lblIdInvestigador);
        
        
        JButton btnEliminarUtilizador = new JButton("Eliminar Utilizador com este ID");
        btnEliminarUtilizador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!IdAdmin.getText().equals(""))
                    if(databaseConnection.DeleteAdmin(Integer.parseInt(IdAdmin.getText())))
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Successo",
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Aconteceu um erro",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                else if(!IdInves.getText().equals(""))
                    if(databaseConnection.DeleteInvestigador(Integer.parseInt(IdInves.getText())))
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Successo",
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Aconteceu um erro",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(frame.getContentPane(),
                            "O campo nao pode estar vazio",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
            }
        });
        btnEliminarUtilizador.setBounds(185, 497, 206, 30);
        contentPanel.add(btnEliminarUtilizador);
        
        JButton btnSubmeterAlteraes = new JButton("Alterar Utilizador");
        btnSubmeterAlteraes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if(!AdminSelected[0].equals(null))
                    if(databaseConnection.UpdateAdministrador(Integer.parseInt(AdminSelected[0]), AdminSelected[1], AdminSelected[2]))
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Successo",
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Aconteceu um erro",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                else  if(!InvesSelected[0].equals(null))
                    if(databaseConnection.UpdateInvestigador(Integer.parseInt(InvesSelected[0]), InvesSelected[1], InvesSelected[2], InvesSelected[3]))
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Successo",
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Aconteceu um erro",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(frame.getContentPane(),
                            "O campo nao pode estar vazio",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
            }
        });
        btnSubmeterAlteraes.setBounds(390, 497, 158, 30);
        contentPanel.add(btnSubmeterAlteraes);
        
        
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
