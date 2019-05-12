package pt.iscte.sid.projeto.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManGeneral;
import pt.iscte.sid.projeto.Machine.UserIdentifier;

/**
 * Esta classe e referente a interface grafico para o utilizador poder fazer login
 * @author Grupo 21
 *
 */
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
     * metodo main para correr a janela loginwindow
     * 
     * @param args
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
    
    /*
     * Metodo para correr a janela
     */
    public LoginWindow()
    {
        StartLoginWindow();
    }
    
    /*
     * Metodo para fechar a janela
     */
    private void CloseWindow() {
        frame.setVisible(false);
    }
    
    /**
     * Cria a janela para o utilizador fazer login
     */
    public void StartLoginWindow() {
        frame= new JFrame();
        frame.setTitle("Login");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 850, 600);
        frame.setVisible(true);
        centerWindow(frame);
        contentPanel = new JPanel();
        contentPanel.setBounds(0, 0, 834, 561);
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        //setContentPane(contentPanel);
        contentPanel.setLayout(null);
        
        PasswordLabel = new JLabel("");
        PasswordLabel.setForeground(new Color(255, 255, 255));
        PasswordLabel.setBounds(508, 227, 59, 25);
        contentPanel.add(PasswordLabel);
        
        Emaillabel = new JLabel("");
        Emaillabel.setForeground(new Color(255, 255, 255));
        Emaillabel.setBounds(508, 179, 59, 25);
        contentPanel.add(Emaillabel);
        
        
        
        EmailInputField = new JTextField();
        EmailInputField.setBounds(592, 154, 242, 37);
        contentPanel.add(EmailInputField);
        EmailInputField.setColumns(10);
        
        LoginButton = new JButton("Entrar");
        LoginButton.setForeground(Color.WHITE);
        LoginButton.setBackground(Color.DARK_GRAY);
        LoginButton.setBounds(648, 270, 95, 34);
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
        PasswordInputField.setBounds(592, 202, 242, 37);
        contentPanel.add(PasswordInputField);
        
        JLabel fundoPagina = new JLabel("");
        fundoPagina.setBounds(-31, 0, 875, 521);
        
        //ImageIcon imgFundo = new ImageIcon(SubscreverUtilizador.class.getResource("images/LoginWindow.png"));
        ImageIcon imgOne = new ImageIcon(LoginWindow.class.getResource("/images/LoginWindow.png"));
        Image img = imgOne.getImage().getScaledInstance(fundoPagina.getWidth(), fundoPagina.getHeight(), Image.SCALE_SMOOTH);
        
        fundoPagina.setIcon(new ImageIcon(img));
       
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
	public static void centerWindow (JFrame frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
}



