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

public class LonginWindow extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private JButton btnNewButton;
	private JRadioButton rdbtnInvestigador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LonginWindow frame = new LonginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LonginWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,  834, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon imgFundo = new ImageIcon(SubscreverInvestig.class.getResource("/images/LoginWindow.png"));
		
		JRadioButton rdbtnAdmini = new JRadioButton("Administrador");
		rdbtnAdmini.setForeground(Color.WHITE);
		rdbtnAdmini.setBackground(Color.DARK_GRAY);
		rdbtnAdmini.setBounds(592, 86, 109, 23);
		contentPane.add(rdbtnAdmini);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(590, 191, 228, 34);
		contentPane.add(passwordField);
			
			textField = new JTextField();
			textField.setBounds(590, 129, 228, 34);
			contentPane.add(textField);
			textField.setColumns(10);
			
			btnNewButton = new JButton("Entrar");
			btnNewButton.setForeground(Color.WHITE);
			btnNewButton.setBackground(Color.DARK_GRAY);
			btnNewButton.setBounds(662, 256, 86, 34);
			contentPane.add(btnNewButton);
			
			rdbtnInvestigador = new JRadioButton("Investigador");
			rdbtnInvestigador.setBackground(Color.DARK_GRAY);
			rdbtnInvestigador.setForeground(Color.WHITE);
			rdbtnInvestigador.setBounds(709, 86, 109, 23);
			contentPane.add(rdbtnInvestigador);
			
			JLabel fundoPagina = new JLabel("");
			fundoPagina.setBounds(0, 0, 834, 490);
			Image imgOne =imgFundo.getImage().getScaledInstance(fundoPagina.getWidth(), fundoPagina.getHeight(), Image.SCALE_SMOOTH);
			
			
			fundoPagina.setIcon(new ImageIcon(imgOne));
			
				
				contentPane.add(fundoPagina);
	}
}



