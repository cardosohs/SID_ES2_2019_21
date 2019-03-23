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
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;

public class LonginWindow extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private JButton btnNewButton;
	private JRadioButton rdbtnInvestigador;
	private JButton btnSubscrever;

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
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,  834, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon imgFundo = new ImageIcon(SubscreverInvestig.class.getResource("/images/LoginWindow.png"));
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.GRAY);
		passwordField.setToolTipText("Apenas necess\u00E1ria se for Admin");
		passwordField.setBounds(590, 191, 228, 34);
		contentPane.add(passwordField);
		
		JRadioButton rdbtnAdmini = new JRadioButton("Administrador");
		rdbtnAdmini.setForeground(Color.WHITE);
		rdbtnAdmini.setBackground(Color.DARK_GRAY);
		rdbtnAdmini.setBounds(592, 86, 109, 23);
		contentPane.add(rdbtnAdmini);
			
			textField = new JTextField();
			textField.setBounds(590, 129, 228, 34);
			contentPane.add(textField);
			textField.setColumns(10);
			
			btnNewButton = new JButton("Entrar");
			btnNewButton.setForeground(Color.WHITE);
			btnNewButton.setBackground(Color.DARK_GRAY);
			btnNewButton.setBounds(562, 256, 104, 34);
			contentPane.add(btnNewButton);
			
			rdbtnInvestigador = new JRadioButton("Investigador");
			rdbtnInvestigador.setBackground(Color.DARK_GRAY);
			rdbtnInvestigador.setForeground(Color.WHITE);
			rdbtnInvestigador.setBounds(709, 86, 109, 23);
			contentPane.add(rdbtnInvestigador);
				
				btnSubscrever = new JButton("Admin - Subscrever");
				btnSubscrever.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnSubscrever.setForeground(Color.WHITE);
				btnSubscrever.setBackground(Color.DARK_GRAY);
				btnSubscrever.setBounds(665, 256, 153, 34);
				contentPane.add(btnSubscrever);
							
							JEditorPane dtrpnHjhkjkh = new JEditorPane();
							dtrpnHjhkjkh.setForeground(Color.PINK);
							dtrpnHjhkjkh.setFont(new Font("Tahoma", Font.ITALIC, 9));
							dtrpnHjhkjkh.setBackground(Color.DARK_GRAY);
							dtrpnHjhkjkh.setText("!Password - Apenas se aplica a user  Admin");
							dtrpnHjhkjkh.setBounds(592, 222, 217, 20);
							contentPane.add(dtrpnHjhkjkh);
							
							JLabel fundoPagina = new JLabel("Apenas se Admin");
							fundoPagina.setForeground(Color.GRAY);
							fundoPagina.setToolTipText("(Apenas se Admin)");
							fundoPagina.setBounds(0, 0, 834, 490);
							Image imgOne =imgFundo.getImage().getScaledInstance(fundoPagina.getWidth(), fundoPagina.getHeight(), Image.SCALE_SMOOTH);
							
							
							fundoPagina.setIcon(new ImageIcon(imgOne));
							
								
								contentPane.add(fundoPagina);
	}
}



