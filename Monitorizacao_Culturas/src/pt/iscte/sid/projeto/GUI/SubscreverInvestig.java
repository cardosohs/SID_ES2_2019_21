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

public class SubscreverInvestig {

	private JFrame frmSubscreverInvestigador;

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
		
		imagTopo.setIcon(new ImageIcon(imgOne));
		
		frmSubscreverInvestigador.getContentPane().add(imagTopo);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setForeground(new Color(100, 149, 237));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(217, 200, 49, 27);
		frmSubscreverInvestigador.getContentPane().add(lblNewLabel);
		
		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setForeground(new Color(100, 149, 237));
		lblDepartamento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDepartamento.setBounds(217, 238, 110, 27);
		frmSubscreverInvestigador.getContentPane().add(lblDepartamento);
		
		JLabel lblCategoriaProfissional = new JLabel("Categoria Profissional");
		lblCategoriaProfissional.setForeground(new Color(100, 149, 237));
		lblCategoriaProfissional.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCategoriaProfissional.setBounds(217, 276, 156, 27);
		frmSubscreverInvestigador.getContentPane().add(lblCategoriaProfissional);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setForeground(new Color(100, 149, 237));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(217, 314, 49, 27);
		frmSubscreverInvestigador.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(100, 149, 237));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(217, 352, 74, 27);
		frmSubscreverInvestigador.getContentPane().add(lblPassword);
		
		JLabel lblConfirmarPassword = new JLabel("Confirmar Password");
		lblConfirmarPassword.setForeground(new Color(100, 149, 237));
		lblConfirmarPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConfirmarPassword.setBounds(217, 390, 143, 27);
		frmSubscreverInvestigador.getContentPane().add(lblConfirmarPassword);
		
		TextField textField = new TextField();
		textField.setBounds(288, 205, 357, 22);
		frmSubscreverInvestigador.getContentPane().add(textField);
		
		TextField textField_1 = new TextField();
		textField_1.setBounds(351, 243, 294, 22);
		frmSubscreverInvestigador.getContentPane().add(textField_1);
		
		TextField textField_2 = new TextField();
		textField_2.setBounds(398, 281, 247, 22);
		frmSubscreverInvestigador.getContentPane().add(textField_2);
		
		TextField textField_3 = new TextField();
		textField_3.setBounds(288, 319, 357, 22);
		frmSubscreverInvestigador.getContentPane().add(textField_3);
		
		TextField textField_4 = new TextField();
		textField_4.setBounds(315, 357, 330, 22);
		frmSubscreverInvestigador.getContentPane().add(textField_4);
		
		TextField textField_5 = new TextField();
		textField_5.setBounds(384, 395, 261, 22);
		frmSubscreverInvestigador.getContentPane().add(textField_5);
		
		JButton btnOk = new JButton("Submeter para aprova\u00E7\u00E3o do Admin");
		btnOk.setForeground(new Color(25, 25, 112));
		btnOk.setBackground(Color.WHITE);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnOk.setBounds(496, 499, 272, 27);
		frmSubscreverInvestigador.getContentPane().add(btnOk);
	}
}
