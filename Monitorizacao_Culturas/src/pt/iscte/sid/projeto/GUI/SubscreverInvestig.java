package pt.iscte.sid.projeto.GUI;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

public class SubscreverInvestig {

	private JFrame frmSubscreverInvestigador;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubscreverInvestig window = new SubscreverInvestig();
					window.frmSubscreverInvestigador.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SubscreverInvestig() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSubscreverInvestigador = new JFrame();
		frmSubscreverInvestigador.getContentPane().setForeground(new Color(30, 144, 255));
		frmSubscreverInvestigador.getContentPane().setBackground(Color.WHITE);
		frmSubscreverInvestigador.setTitle("Subscrever Investigador");
		frmSubscreverInvestigador.setBounds(100, 100, 850, 600);
		frmSubscreverInvestigador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSubscreverInvestigador.getContentPane().setLayout(null);
		
		JLabel imagTopo = new JLabel("");
		imagTopo.setBounds(0, 0, 834, 151);
		
		ImageIcon imgTopo = new ImageIcon(SubscreverInvestig.class.getResource("/images/SubscricaoInvestigador.png"));
		Image imgOne =imgTopo.getImage().getScaledInstance(imagTopo.getWidth(), imagTopo.getHeight(), Image.SCALE_SMOOTH);
		
		
		String[] categoriasProfissionais = {"Phd Student", "PostDoc Student", "Investigador auxiliar", "Investigador principal", "Investigador Coordenador"};
		JComboBox comboBox = new JComboBox(categoriasProfissionais);
		comboBox.setAutoscrolls(true);
		comboBox.setFocusTraversalPolicyProvider(true);
		comboBox.setFocusCycleRoot(true);
		comboBox.setDoubleBuffered(true);
		comboBox.setEditable(true);
		comboBox.setBounds(373, 238, 272, 22);
		frmSubscreverInvestigador.getContentPane().add(comboBox);
		
		imagTopo.setIcon(new ImageIcon(imgOne));
		
		frmSubscreverInvestigador.getContentPane().add(imagTopo);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setForeground(new Color(100, 149, 237));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(217, 200, 49, 27);
		frmSubscreverInvestigador.getContentPane().add(lblNewLabel);
		
		JLabel lblCategoriaProfissional = new JLabel("Categoria Profissional");
		lblCategoriaProfissional.setForeground(new Color(100, 149, 237));
		lblCategoriaProfissional.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCategoriaProfissional.setBounds(217, 238, 156, 27);
		frmSubscreverInvestigador.getContentPane().add(lblCategoriaProfissional);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setForeground(new Color(100, 149, 237));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(217, 276, 49, 27);
		frmSubscreverInvestigador.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(100, 149, 237));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(217, 314, 74, 27);
		frmSubscreverInvestigador.getContentPane().add(lblPassword);
		
		JLabel lblConfirmarPassword = new JLabel("Confirmar Password");
		lblConfirmarPassword.setForeground(new Color(100, 149, 237));
		lblConfirmarPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConfirmarPassword.setBounds(217, 352, 143, 27);
		frmSubscreverInvestigador.getContentPane().add(lblConfirmarPassword);
		
		TextField textField = new TextField();
		textField.setBounds(272, 205, 373, 22);
		frmSubscreverInvestigador.getContentPane().add(textField);
		
		TextField textField_3 = new TextField();
		textField_3.setBounds(270, 281, 375, 22);
		frmSubscreverInvestigador.getContentPane().add(textField_3);
		
		JButton btnOk = new JButton("Submeter para aprova\u00E7\u00E3o do Admin");
		btnOk.setForeground(new Color(25, 25, 112));
		btnOk.setBackground(Color.WHITE);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnOk.setBounds(496, 499, 272, 27);
		frmSubscreverInvestigador.getContentPane().add(btnOk);
		
		JList list = new JList();
		list.setBounds(484, 293, 1, 1);
		frmSubscreverInvestigador.getContentPane().add(list);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(295, 317, 350, 22);
		frmSubscreverInvestigador.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(373, 357, 272, 22);
		frmSubscreverInvestigador.getContentPane().add(passwordField_1);
		
		
		String[] departamentos = {"Biologia", "Química"};
	}
}
