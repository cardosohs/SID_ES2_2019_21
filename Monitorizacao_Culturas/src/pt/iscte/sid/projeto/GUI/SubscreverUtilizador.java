package pt.iscte.sid.projeto.GUI;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;

/**
 * Esta classe e referente a interface grafico para subscrever um utilizador
 * 
 * @author Grupo 21
 *
 */
public class SubscreverUtilizador {
    
    private DatabaseMiddleManForAdministrador databaseConnection;
    private JFrame frame;
    private JPasswordField passwordField;
    private boolean create=true;
    private JPasswordField ConfirmPassword;
    
    
 /*   public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                    DatabaseMiddleManForAdministrador d = new DatabaseMiddleManForAdministrador("EmailAdmin", "12345");
                    
                    SubscreverUtilizador window = new SubscreverUtilizador(d,false);
                    //  window.frame.setVisible(true);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    */
    /**
     * Create the application.
     */
    public SubscreverUtilizador() {
        initialize();
    }
    
    /**
     * Lanca a interface para o administrados poder subscrever um utilizador
     * 
     * @param databaseConnection  esta parametro é a ligacao da base de dados
     */
    public SubscreverUtilizador(DatabaseMiddleManForAdministrador databaseConnection, boolean create) {
        this.databaseConnection = databaseConnection;
        this.create=create;
        initialize();
    }
    
    /*
     * Metodo para fechar a janela
     */
    private void CloseWindow() {
        frame.setVisible(false);
    }
    
    
    /**
     * Cria a janela para o Administrador poder criar utilizadores
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setForeground(new Color(30, 144, 255));
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setTitle("Subscrever Investigador");
        frame.setBounds(100, 100, 850, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        JLabel imagTopo = new JLabel("");
        imagTopo.setBounds(0, 0, 834, 151);
        
        ImageIcon imgTopo = new ImageIcon(SubscreverUtilizador.class.getResource("/images/SubscricaoInvestigador.png"));
        Image imgOne =imgTopo.getImage().getScaledInstance(imagTopo.getWidth(), imagTopo.getHeight(), Image.SCALE_SMOOTH);
        
        
        String[] categoriasProfissionais = {" ","Phd Student", "PostDoc Student", "Investigador auxiliar", "Investigador principal", "Investigador Coordenador"};
        
        
        JComboBox CategoriaProfcomboBox = new JComboBox(categoriasProfissionais);
        CategoriaProfcomboBox.setToolTipText("Se Investigador");
        CategoriaProfcomboBox.setAutoscrolls(true);
        CategoriaProfcomboBox.setFocusTraversalPolicyProvider(true);
        CategoriaProfcomboBox.setFocusCycleRoot(true);
        CategoriaProfcomboBox.setDoubleBuffered(true);
        CategoriaProfcomboBox.setEditable(true);
        CategoriaProfcomboBox.setBounds(373, 290, 272, 22);
        frame.getContentPane().add(CategoriaProfcomboBox);
        
        
        imagTopo.setIcon(new ImageIcon(imgOne));
        
        frame.getContentPane().add(imagTopo);
        
        JLabel lblNewLabel = new JLabel("Nome");
        lblNewLabel.setForeground(new Color(100, 149, 237));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(217, 249, 49, 27);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblCategoriaProfissional = new JLabel("Categoria Profissional");
        lblCategoriaProfissional.setForeground(new Color(100, 149, 237));
        lblCategoriaProfissional.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblCategoriaProfissional.setBounds(217, 287, 156, 27);
        frame.getContentPane().add(lblCategoriaProfissional);
        
        JLabel lblEmail = new JLabel("E-mail");
        lblEmail.setForeground(new Color(100, 149, 237));
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblEmail.setBounds(217, 322, 49, 27);
        frame.getContentPane().add(lblEmail);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(new Color(100, 149, 237));
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPassword.setBounds(217, 360, 74, 27);
        frame.getContentPane().add(lblPassword);
        
        JLabel lblConfirmarPassword = new JLabel("Confirmar Password");
        lblConfirmarPassword.setForeground(new Color(100, 149, 237));
        lblConfirmarPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblConfirmarPassword.setBounds(217, 398, 143, 27);
        frame.getContentPane().add(lblConfirmarPassword);
        
        
        TextField NameTextField = new TextField();
        NameTextField.setBounds(272, 252, 373, 22);
        frame.getContentPane().add(NameTextField);
        
        TextField Email = new TextField();
        Email.setBounds(270, 332, 375, 22);
        Email.setEditable(create);
        frame.getContentPane().add(Email);
        
        
        
        TextField IdTextField = new TextField();
        IdTextField.setBounds(272, 211, 373, 22);
        IdTextField.setEditable(!create);
        frame.getContentPane().add(IdTextField);
        
        /*    JButton btnOk = new JButton("Adicionar Utilizador");
        btnOk.setForeground(new Color(25, 25, 112));
        btnOk.setBackground(Color.WHITE);
        btnOk.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        ManutencaoUtilizadores n= new ManutencaoUtilizadores();
        n.setVisible(true);
        }
        });
        btnOk.setBounds(612, 499, 156, 27);
        frame.getContentPane().add(btnOk);*/
        JList list = new JList();
        list.setBounds(484, 293, 1, 1);
        frame.getContentPane().add(list);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(295, 365, 350, 22);
        passwordField.setEditable(create);
        frame.getContentPane().add(passwordField);
        
        
        
        ConfirmPassword = new JPasswordField();
        ConfirmPassword.setBounds(361, 401, 284, 22);
        ConfirmPassword.setEditable(create);
        frame.getContentPane().add(ConfirmPassword);
        
        JRadioButton rdbtnNewRadioButton = new JRadioButton("Investigador");
        rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 13));
        rdbtnNewRadioButton.setForeground(new Color(25, 25, 112));
        rdbtnNewRadioButton.setBackground(Color.WHITE);
        rdbtnNewRadioButton.setBounds(299, 174, 109, 23);
        frame.getContentPane().add(rdbtnNewRadioButton);
        
        JRadioButton rdbtnAdministrador = new JRadioButton("Administrador");
        rdbtnAdministrador.setFont(new Font("Tahoma", Font.BOLD, 13));
        rdbtnAdministrador.setForeground(new Color(25, 25, 112));
        rdbtnAdministrador.setBackground(Color.WHITE);
        rdbtnAdministrador.setBounds(471, 174, 124, 23);
        
        
        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnAdministrador);
        group.add(rdbtnNewRadioButton);
        frame.getContentPane().add(rdbtnAdministrador);
        
        
        JButton ButtonConfirmar = new JButton("Confirmar");
        ButtonConfirmar.setForeground(new Color(25, 25, 112));
        ButtonConfirmar.setBackground(Color.WHITE);
        ButtonConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(create) {
                    DatabaseMiddleManForAdministrador tmp = new DatabaseMiddleManForAdministrador("root", "");
                    if(!tmp.failed){
                        //ManutencaoUtilizadores n= new ManutencaoUtilizadores();
                        String PasswordHasString="";
                        char[] PasswordHasCharArray = passwordField.getPassword();
                        for(int index=0;index<PasswordHasCharArray.length;index++)
                            PasswordHasString+=PasswordHasCharArray[index];
                        
                        String PasswordConfirmHasString="";
                        char[] PasswordConfirmHasCharArray = passwordField.getPassword();
                        for(int index=0;index<PasswordConfirmHasCharArray.length;index++)
                            PasswordConfirmHasString+=PasswordConfirmHasCharArray[index];
                        if(!(PasswordHasString.equals(PasswordConfirmHasString))) {
                            JOptionPane.showMessageDialog(frame.getContentPane(),
                                    "Passwords nao sao iguais",
                                    "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        else if(!rdbtnAdministrador.isSelected() && !rdbtnNewRadioButton.isSelected())
                            JOptionPane.showMessageDialog(frame.getContentPane(),
                                    "Escolha admin ou invesstigador",
                                    "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                        
                        else if(rdbtnAdministrador.isSelected() && !NameTextField.getText().equals("") &&!Email.getText().equals("")
                                && !passwordField.getPassword().equals(null)) {
                            
                            if(tmp.ExecuteSP(NameTextField.getText(), PasswordHasString, Email.getText(), "", "A")){
                                JOptionPane.showMessageDialog(frame.getContentPane(),
                                        "Succeso",
                                        "Information",
                                        JOptionPane.INFORMATION_MESSAGE);
                                new ManutencaoUtilizadores(databaseConnection);
                                CloseWindow();
                            }
                            else
                                JOptionPane.showMessageDialog(frame.getContentPane(),
                                        "Ocorreu um erro",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            
                        }
                        else if(rdbtnNewRadioButton.isSelected() && !NameTextField.getText().equals("") &&!Email.getText().equals("")
                                && !passwordField.getPassword().equals(null)) {
                            
                            if(tmp.ExecuteSP(NameTextField.getText(), PasswordHasString, Email.getText(), CategoriaProfcomboBox.getSelectedItem().toString(), "I")){
                                JOptionPane.showMessageDialog(frame.getContentPane(),
                                        "Succeso",
                                        "Information",
                                        JOptionPane.INFORMATION_MESSAGE);
                                new ManutencaoUtilizadores(databaseConnection);
                                CloseWindow();
                            }
                            else
                                JOptionPane.showMessageDialog(frame.getContentPane(),
                                        "Ocorreu um erro",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            
                        }
                        else
                            JOptionPane.showMessageDialog(frame.getContentPane(),
                                    "Generic Error",
                                    "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                    }
                    
                    else
                        System.out.println("Connection Down");
                }
                
                else
                {
                    
                    if(!rdbtnAdministrador.isSelected() && !rdbtnNewRadioButton.isSelected())
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Escolha admin ou investigador",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    
                    else if(rdbtnAdministrador.isSelected() && !NameTextField.getText().equals("")) {
                        
                        if(databaseConnection.UpdateAdministrador(Integer.parseInt(IdTextField.getText()),NameTextField.getText())){
                            JOptionPane.showMessageDialog(frame.getContentPane(),
                                    "Succeso",
                                    "Information",
                                    JOptionPane.INFORMATION_MESSAGE);
                            new ManutencaoUtilizadores(databaseConnection);
                            CloseWindow();
                        }
                        else
                            JOptionPane.showMessageDialog(frame.getContentPane(),
                                    "Ocorreu um erro",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        
                    }
                    else if(rdbtnNewRadioButton.isSelected() && !NameTextField.getText().equals("")) {
                        
                        if(databaseConnection.UpdateInvestigador(Integer.parseInt(IdTextField.getText()),NameTextField.getText(),CategoriaProfcomboBox.getSelectedItem().toString())){
                            JOptionPane.showMessageDialog(frame.getContentPane(),
                                    "Succeso",
                                    "Information",
                                    JOptionPane.INFORMATION_MESSAGE);
                            new ManutencaoUtilizadores(databaseConnection);
                            CloseWindow();
                        }
                        else
                            JOptionPane.showMessageDialog(frame.getContentPane(),
                                    "Ocorreu um erro",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        
                    }
                    else
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Generic Error",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    
                }
            }
            
        });
        ButtonConfirmar.setBounds(612, 499, 156, 27);
        frame.getContentPane().add(ButtonConfirmar);
        
        
        
        JButton logout = new JButton("Voltar");
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AreaAdmin(databaseConnection);
                CloseWindow();
            }
        });
        logout.setBackground(Color.WHITE);
        logout.setBounds(100, 499, 156, 27);
        frame.getContentPane().add(logout);
        
        JLabel lblId = new JLabel("Id");
        lblId.setForeground(new Color(100, 149, 237));
        lblId.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblId.setBounds(217, 206, 49, 27);
        frame.getContentPane().add(lblId);
        
        
        
        
        String[] departamentos = {"Biologia", "Química"};
    }
}
