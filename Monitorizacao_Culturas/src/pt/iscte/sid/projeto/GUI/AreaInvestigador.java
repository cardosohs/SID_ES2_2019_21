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

public class AreaInvestigador extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtId;

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
		
		ImageIcon imgTopo = new ImageIcon(SubscreverUtilizador.class.getResource("/images/areaInvestigador.png"));
		
		JLabel Menu = new JLabel("");
		Menu.setBounds(212, 219, 363, 75);
		ImageIcon menu = new ImageIcon(SubscreverUtilizador.class.getResource("/images/MenuAreaInvest.png"));
		contentPane.add(Menu);
		
		JButton btnNewButton = new JButton("As Minhas Culturas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CulturasLista cl = new CulturasLista();
				cl.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(157, 288, 162, 69);
		contentPane.add(btnNewButton);
		
		JButton btnAppAndroid = new JButton("App Android");
		btnAppAndroid.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAppAndroid.setBounds(336, 288, 169, 69);
		contentPane.add(btnAppAndroid);
		
		JButton btnVerificarRegistoPessoal = new JButton("Consultar Vari\u00E1veis");
		btnVerificarRegistoPessoal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserirDados clt = new InserirDados();
			//	clt.setVisible(true);                                                             //Problema aqui - perceber pq
				
			}
		});
		btnVerificarRegistoPessoal.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVerificarRegistoPessoal.setBounds(515, 288, 169, 69);
		contentPane.add(btnVerificarRegistoPessoal);
		
		JButton btnLogout = new JButton("LogOut");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						LoginWindow lw = new LoginWindow();
						lw.setVisible(true);
			}
		});
		btnLogout.setBounds(657, 498, 89, 23);
		contentPane.add(btnLogout);
		
		textField = new JTextField();
		textField.setText("Investigador");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.WHITE);
		textField.setFont(textField.getFont().deriveFont(11f));
		textField.setColumns(10);
		textField.setBackground(new Color(0, 51, 102));
		textField.setBounds(508, 71, 68, 21);
		contentPane.add(textField);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(SystemColor.menu);
		textPane.setBounds(586, 72, 145, 20);
		contentPane.add(textPane);
		
		txtId = new JTextField();
		txtId.setText("ID");
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setForeground(Color.WHITE);
		txtId.setFont(txtId.getFont().deriveFont(11f));
		txtId.setColumns(10);
		txtId.setBackground(new Color(0, 51, 102));
		txtId.setBounds(741, 71, 24, 21);
		contentPane.add(txtId);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBackground(SystemColor.menu);
		textPane_1.setBounds(775, 71, 36, 20);
		contentPane.add(textPane_1);
		
		JLabel imagTopo = new JLabel("");
		imagTopo.setBackground(Color.WHITE);
		imagTopo.setBounds(0, 0, 834, 460);
		Image imgOne =imgTopo.getImage().getScaledInstance(imagTopo.getWidth(), imagTopo.getHeight(), Image.SCALE_SMOOTH);
		
		imagTopo.setIcon(new ImageIcon(imgOne));
		
		
		
		contentPane.add(imagTopo);
		Image imgTwo =imgTopo.getImage().getScaledInstance(imagTopo.getWidth(), imagTopo.getHeight(), Image.SCALE_SMOOTH);
		
		imagTopo.setIcon(new ImageIcon(imgTwo));
	}
}

