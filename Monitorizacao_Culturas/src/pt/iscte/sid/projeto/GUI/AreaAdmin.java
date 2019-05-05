package pt.iscte.sid.projeto.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;

public class AreaAdmin extends JFrame {
    
    private JPanel contentPanel;
    private JFrame frame;
    private DatabaseMiddleManForAdministrador databaseConnection;
    
    
    private void CloseWindow() {
        frame.setVisible(false);
    }
    
    /**
     * Launch the application
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AreaAdmin frame = new AreaAdmin(new DatabaseMiddleManForAdministrador("EmailAdmin", "12345"));
                    //frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public AreaAdmin(DatabaseMiddleManForAdministrador databaseConnection) {
        this.databaseConnection = databaseConnection;
        StartAreaAdmin() ;
    }
    
    public AreaAdmin() {
        StartAreaAdmin() ;
    }
    
    /**
     * Create the frame.
     */
    public void StartAreaAdmin() {
        frame= new JFrame();
        frame.setTitle("\u00C1rea do Administrador");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 850, 600);
        frame.setVisible(true);
        setTitle("\u00C1rea do Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 600);
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);
        
        ImageIcon imgTopo = new ImageIcon(SubscreverUtilizador.class.getResource("/images/areaAdmin.png"));
        
        JButton btnCriaoDeUtilizadores = new JButton("Cria\u00E7\u00E3o de Utilizadores");
        btnCriaoDeUtilizadores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SubscreverUtilizador(databaseConnection);
                CloseWindow();
            }
        });
        btnCriaoDeUtilizadores.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnCriaoDeUtilizadores.setBounds(34, 368, 175, 35);
        contentPanel.add(btnCriaoDeUtilizadores);
        
        JButton btnManutenoDeUtilizadores = new JButton("Manuten\u00E7\u00E3o de Utilizadores");
        btnManutenoDeUtilizadores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManutencaoUtilizadores(databaseConnection);
                CloseWindow();
            }
        });
        btnManutenoDeUtilizadores.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnManutenoDeUtilizadores.setBounds(222, 368, 195, 35);
        contentPanel.add(btnManutenoDeUtilizadores);
        
        JButton btnManutenoDeVariveis = new JButton("Manuten\u00E7\u00E3o de Vari\u00E1veis");
        btnManutenoDeVariveis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManutencaoVariaveis(databaseConnection);
                CloseWindow();
            }
        });
        btnManutenoDeVariveis.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnManutenoDeVariveis.setBounds(427, 368, 185, 35);
        contentPanel.add(btnManutenoDeVariveis);
        
        JButton button = new JButton("LogOut");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginWindow();
                CloseWindow();
                databaseConnection.CloseConnection();
                
            }
        });
        button.setBounds(675, 499, 89, 23);
        contentPanel.add(button);
        
        JButton btnConfigurarLimites = new JButton("Configurar LimitesSistema");
        btnConfigurarLimites.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ConfigurarLimites();
                CloseWindow();
            }
        });
        btnConfigurarLimites.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnConfigurarLimites.setBounds(628, 368, 185, 35);
        contentPanel.add(btnConfigurarLimites);
        
        JLabel fotoTopo = new JLabel("");
        fotoTopo.setBounds(0, 0, 834, 476);
        Image imgOne =imgTopo.getImage().getScaledInstance(fotoTopo.getWidth(), fotoTopo.getHeight(), Image.SCALE_SMOOTH);
        
        fotoTopo.setIcon(new ImageIcon(imgOne));
        
        contentPanel.add(fotoTopo);
        frame.add(contentPanel);
    }
}



