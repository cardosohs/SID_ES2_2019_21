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

public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JRadioButton radioButton_1;
	private JRadioButton radioButton_2;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
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
	public LoginWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,  834, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon imgFundo = new ImageIcon(SubscreverUtilizador.class.getResource("/images/LoginWindow.png"));
			
			textField = new JTextField();
			textField.setBounds(600, 146, 218, 34);
			contentPane.add(textField);
			textField.setColumns(10);
			
			btnNewButton = new JButton("Entrar");
			btnNewButton.setForeground(Color.WHITE);
			btnNewButton.setBackground(Color.DARK_GRAY);
			btnNewButton.setBounds(649, 253, 95, 34);
			contentPane.add(btnNewButton);
					
					JRadioButton radioButton = new JRadioButton("");
					radioButton.setForeground(new Color(64, 64, 64));
					radioButton.setBackground(Color.DARK_GRAY);
					radioButton.setBounds(570, 86, 28, 23);
					contentPane.add(radioButton);
						
						radioButton_1 = new JRadioButton("");
						radioButton_1.setForeground(Color.DARK_GRAY);
						radioButton_1.setBackground(Color.DARK_GRAY);
						radioButton_1.setBounds(680, 86, 28, 23);
						contentPane.add(radioButton_1);
						
						radioButton_2 = new JRadioButton("");
						radioButton_2.setForeground(Color.DARK_GRAY);
						radioButton_2.setBackground(Color.DARK_GRAY);
						radioButton_2.setBounds(784, 86, 28, 23);
						contentPane.add(radioButton_2);
							
							passwordField_1 = new JPasswordField();
							passwordField_1.setBounds(600, 191, 218, 30);
							contentPane.add(passwordField_1);
							
							JLabel fundoPagina = new JLabel("");
							fundoPagina.setBounds(0, 0, 834, 490);
							Image imgOne =imgFundo.getImage().getScaledInstance(fundoPagina.getWidth(), fundoPagina.getHeight(), Image.SCALE_SMOOTH);
							
							
							fundoPagina.setIcon(new ImageIcon(imgOne));
							
								
								contentPane.add(fundoPagina);
	}
}



