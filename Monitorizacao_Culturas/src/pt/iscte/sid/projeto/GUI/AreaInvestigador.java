package pt.iscte.sid.projeto.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;

public class AreaInvestigador extends JFrame {
    
    private JPanel contentPanel;
    private JFrame frame;
    private JTextField textField;
    private JTextField txtId;
    private DatabaseMiddleManForInvestigador databaseConnection;
    
    
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AreaInvestigador frame = new AreaInvestigador(new DatabaseMiddleManForInvestigador("svbro@iscte-iul.com", "123"));
                    // frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void CloseWindow() {
        frame.setVisible(false);
    }
    
    public AreaInvestigador(DatabaseMiddleManForInvestigador databaseConnection) {
        this.databaseConnection = databaseConnection;
        StartAreaInvestigador();
    }
    
    public AreaInvestigador() {
        // this.databaseConnection = databaseConnection;
        StartAreaInvestigador();
    }
    
    /**
     * Create the frame.
     */
    public void StartAreaInvestigador() {
        frame= new JFrame();
        frame.setTitle("\u00C1rea do Investigador");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 850, 600);
        frame.setVisible(true);
        setTitle("\u00C1rea do Investigador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 600);
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);
        
        ImageIcon imgTopo = new ImageIcon(SubscreverUtilizador.class.getResource("/images/areaInvestigador.png"));
        
        JLabel Menu = new JLabel("");
        Menu.setBounds(212, 219, 363, 75);
        ImageIcon menu = new ImageIcon(SubscreverUtilizador.class.getResource("/images/MenuAreaInvest.png"));
        contentPanel.add(Menu);
        
        JButton btnNewButton = new JButton("As Minhas Culturas");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CulturasLista(databaseConnection);
                CloseWindow();
                
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton.setBounds(157, 288, 162, 69);
        contentPanel.add(btnNewButton);
        
        JButton btnAppAndroid = new JButton("App Android");
        btnAppAndroid.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAppAndroid.setBounds(336, 288, 169, 69);
        contentPanel.add(btnAppAndroid);
        
        JButton btnVerificarRegistoPessoal = new JButton("Consultar Vari\u00E1veis");
        btnVerificarRegistoPessoal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new InserirDados(databaseConnection);
                CloseWindow();
            }
        });
        btnVerificarRegistoPessoal.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnVerificarRegistoPessoal.setBounds(515, 288, 169, 69);
        contentPanel.add(btnVerificarRegistoPessoal);
        
        JButton btnLogout = new JButton("LogOut");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginWindow();
                CloseWindow();
                databaseConnection.CloseConnection();
                
            }
        });
        btnLogout.setBounds(657, 498, 89, 23);
        contentPanel.add(btnLogout);
        
        textField = new JTextField();
        textField.setText("Investigador");
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setForeground(Color.WHITE);
        textField.setFont(textField.getFont().deriveFont(11f));
        textField.setColumns(10);
        textField.setBackground(new Color(0, 51, 102));
        textField.setBounds(508, 71, 68, 21);
        contentPanel.add(textField);
        
        JTextPane textPane = new JTextPane();
        textPane.setBackground(SystemColor.menu);
        textPane.setBounds(586, 72, 145, 20);
        contentPanel.add(textPane);
        
        txtId = new JTextField();
        txtId.setText("ID");
        txtId.setHorizontalAlignment(SwingConstants.CENTER);
        txtId.setForeground(Color.WHITE);
        txtId.setFont(txtId.getFont().deriveFont(11f));
        txtId.setColumns(10);
        txtId.setBackground(new Color(0, 51, 102));
        txtId.setBounds(741, 71, 24, 21);
        contentPanel.add(txtId);
        
        JTextPane textPane_1 = new JTextPane();
        textPane_1.setBackground(SystemColor.menu);
        textPane_1.setBounds(775, 71, 36, 20);
        contentPanel.add(textPane_1);
        
        JLabel fotoTopo = new JLabel("");
        fotoTopo.setBackground(Color.WHITE);
        fotoTopo.setBounds(0, 0, 834, 460);
        
        Image imgOne =imgTopo.getImage().getScaledInstance(fotoTopo.getWidth(), fotoTopo.getHeight(), Image.SCALE_SMOOTH);
        
        fotoTopo.setIcon(new ImageIcon(imgOne));
        
        
        
        contentPanel.add(fotoTopo);
        frame.add(contentPanel);
        
        Image imgTwo =imgTopo.getImage().getScaledInstance(fotoTopo.getWidth(), fotoTopo.getHeight(), Image.SCALE_SMOOTH);
        
        fotoTopo.setIcon(new ImageIcon(imgTwo));
        
    }
}

