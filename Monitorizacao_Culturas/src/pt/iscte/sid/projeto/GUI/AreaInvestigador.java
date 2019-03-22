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

public class AreaInvestigador extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AreaInvestigador frame = new AreaInvestigador();
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
	public AreaInvestigador() {
		setTitle("\u00C1rea do Investigador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel imagTopo = new JLabel("");
		imagTopo.setBackground(Color.WHITE);
		imagTopo.setBounds(0, 0, 834, 303);
		
		ImageIcon imgTopo = new ImageIcon(SubscreverInvestig.class.getResource("/images/area do investigador.png"));
		Image imgOne =imgTopo.getImage().getScaledInstance(imagTopo.getWidth(), imagTopo.getHeight(), Image.SCALE_SMOOTH);
		
		imagTopo.setIcon(new ImageIcon(imgOne));
		
		
		
		contentPane.add(imagTopo);
		
		JLabel Menu = new JLabel("");
		Menu.setBounds(212, 219, 363, 75);
		ImageIcon menu = new ImageIcon(SubscreverInvestig.class.getResource("/images/MenuAreaInvest.png"));
		Image imgTwo =imgTopo.getImage().getScaledInstance(imagTopo.getWidth(), imagTopo.getHeight(), Image.SCALE_SMOOTH);
		
		imagTopo.setIcon(new ImageIcon(imgTwo));
		contentPane.add(Menu);
		
		JButton btnNewButton = new JButton("As Minhas Culturas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(165, 321, 165, 53);
		contentPane.add(btnNewButton);
		
		JButton btnAppAndroid = new JButton("App Android");
		btnAppAndroid.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAppAndroid.setBounds(340, 321, 158, 53);
		contentPane.add(btnAppAndroid);
		
		JButton btnVerificarRegistoPessoal = new JButton("Verificar Registo Pessoal");
		btnVerificarRegistoPessoal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVerificarRegistoPessoal.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVerificarRegistoPessoal.setBounds(508, 321, 177, 53);
		contentPane.add(btnVerificarRegistoPessoal);
		
		JButton btnLogout = new JButton("LogOut");
		btnLogout.setBounds(657, 498, 89, 23);
		contentPane.add(btnLogout);
	}
}

