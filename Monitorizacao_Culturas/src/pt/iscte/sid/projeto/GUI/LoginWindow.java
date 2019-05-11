package pt.iscte.sid.projeto.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManGeneral;
import pt.iscte.sid.projeto.Machine.UserIdentifier;

public class LoginWindow extends JFrame {
    
    private JPanel contentPanel;
    private JTextField EmailInputField;
    private JButton LoginButton;
    private JFrame frame;
    //private JRadioButton UserSelectionRadioButton;
    private JPasswordField PasswordInputField;
    private UserIdentifier userIdentifier= new UserIdentifier();
    private JLabel PasswordLabel;
    private JLabel Emaillabel;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginWindow frame = new LoginWindow();
                    // frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public LoginWindow()
    {
        StartLoginWindow();
    }
    
    private void CloseWindow() {
        frame.setVisible(false);
    }
    
    /**
     * Create the frame.
     */
    public void StartLoginWindow() {
        frame= new JFrame();
        frame.setTitle("Login");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 850, 600);
        frame.setVisible(true);
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        //setContentPane(contentPanel);
        contentPanel.setLayout(null);
        
        PasswordLabel = new JLabel("Password");
        PasswordLabel.setForeground(new Color(255, 255, 255));
        PasswordLabel.setBounds(508, 227, 59, 25);
        contentPanel.add(PasswordLabel);
        
        Emaillabel = new JLabel("Email");
        Emaillabel.setForeground(new Color(255, 255, 255));
        Emaillabel.setBounds(508, 179, 59, 25);
        contentPanel.add(Emaillabel);
        
        
        
        EmailInputField = new JTextField();
        EmailInputField.setBounds(600, 179, 244, 37);
        contentPanel.add(EmailInputField);
        EmailInputField.setColumns(10);
        
        LoginButton = new JButton("Entrar");
        LoginButton.setForeground(Color.WHITE);
        LoginButton.setBackground(Color.DARK_GRAY);
        LoginButton.setBounds(652, 292, 95, 34);
        contentPanel.add(LoginButton);
        
        /*  JRadioButton radioButton = new JRadioButton("");
        radioButton.setForeground(new Color(64, 64, 64));
        radioButton.setBackground(Color.DARK_GRAY);
        radioButton.setBounds(640, 132, 28, 23);
        contentPane.add(radioButton);
        
        UserSelectionRadioButton = new JRadioButton("");
        UserSelectionRadioButton.setForeground(Color.DARK_GRAY);
        UserSelectionRadioButton.setBackground(Color.DARK_GRAY);
        UserSelectionRadioButton.setBounds(745, 132, 28, 23);
        contentPane.add(UserSelectionRadioButton);
        */
        PasswordInputField = new JPasswordField();
        PasswordInputField.setBounds(600, 227, 244, 37);
        contentPanel.add(PasswordInputField);
        
        JLabel fundoPagina = new JLabel("");
        fundoPagina.setBounds(0, 33, 844, 506);
        
        //ImageIcon imgFundo = new ImageIcon(SubscreverUtilizador.class.getResource("images/LoginWindow.png"));
        ImageIcon imgOne = new ImageIcon("/Monitorizacao_Culturas/src/images/LoginWindow.png");
        //imgFundo.getImage().getScaledInstance(fundoPagina.getWidth(), fundoPagina.getHeight(), Image.SCALE_SMOOTH);
        fundoPagina.setIcon(new ImageIcon(LoginWindow.class.getResource("/images/LoginWindow.png")));
        
        
        contentPanel.add(fundoPagina);
        
        frame.getContentPane().add(contentPanel);
        
        
        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(EmailInputField.getText().equals(""))
                    JOptionPane.showMessageDialog(contentPanel,
                            "Email não pode estar vazio",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                else if(PasswordInputField.getPassword().length==0)
                    JOptionPane.showMessageDialog(contentPanel,
                            "Password não pode estar vazia",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                else
                {
                    String PasswordHasString="";
                    char[] PasswordHasCharArray = PasswordInputField.getPassword();
                    for(int index=0;index<PasswordHasCharArray.length;index++)
                        PasswordHasString+=PasswordHasCharArray[index];
                    if(userIdentifier.WhatUserIsThis(EmailInputField.getText(), PasswordHasString).equals("I")){
                        new AreaInvestigador(new DatabaseMiddleManForInvestigador(EmailInputField.getText(),PasswordHasString));
                        CloseWindow();
                        
                    }else if(userIdentifier.WhatUserIsThis(EmailInputField.getText(), PasswordHasString).equals("A")){
                        new AreaAdmin(new DatabaseMiddleManForAdministrador(EmailInputField.getText(),PasswordHasString));
                        CloseWindow();
                    }else
                        JOptionPane.showMessageDialog(contentPanel,
                                "Erro ao fazer login",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                }
                
                
                
            }
            
            
        } );
    }
}



