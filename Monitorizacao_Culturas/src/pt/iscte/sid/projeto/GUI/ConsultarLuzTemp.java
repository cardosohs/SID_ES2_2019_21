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
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;

public class ConsultarLuzTemp extends JFrame {
    
    private DatabaseMiddleManForAdministrador databaseConnection;
    private JPanel contentPanel;
    private JFrame frame;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ConsultarLuzTemp frame = new ConsultarLuzTemp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ConsultarLuzTemp() {
        StartConsultarLuzTemp();
    }

    public ConsultarLuzTemp(DatabaseMiddleManForAdministrador databaseConnection) {
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
        setContentPane(contentPanel);
        contentPanel.setLayout(null);
        
        ImageIcon background = new ImageIcon(ConsultarLuzTemp.class.getResource("/images/consultarLuzTemp.png"));
        
        JButton button = new JButton("Voltar");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AreaInvestigador ai = new AreaInvestigador();
               CloseWindow();
            }
        });
        button.setBounds(728, 469, 85, 30);
        contentPanel.add(button);
        
        JLabel backPhoto = new JLabel("");
        backPhoto.setBounds(0, 0, 834, 523);
        
        Image img = background.getImage().getScaledInstance(backPhoto.getWidth(), backPhoto.getHeight(), Image.SCALE_SMOOTH);
        backPhoto.setIcon(new ImageIcon(img));
        contentPanel.add(backPhoto);
        frame.add(contentPanel);
    }
    public ConsultarLuzTemp(DatabaseMiddleManForAdministrador databaseConnection) {
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
        setContentPane(contentPanel);
        contentPanel.setLayout(null);
        
        ImageIcon background = new ImageIcon(ConsultarLuzTemp.class.getResource("/images/consultarLuzTemp.png"));
        
        JButton button = new JButton("Voltar");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AreaInvestigador ai = new AreaInvestigador();
                ai.setVisible(true);
            }
        });
        button.setBounds(728, 469, 85, 30);
        contentPanel.add(button);
        
        JLabel backPhoto = new JLabel("");
        backPhoto.setBounds(0, 0, 834, 523);
        
        Image img = background.getImage().getScaledInstance(backPhoto.getWidth(), backPhoto.getHeight(), Image.SCALE_SMOOTH);
        backPhoto.setIcon(new ImageIcon(img));
        contentPanel.add(backPhoto);
        frame.add(contentPanel);
    }
}
