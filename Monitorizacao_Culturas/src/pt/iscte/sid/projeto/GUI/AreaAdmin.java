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

public class AreaAdmin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AreaAdmin frame = new AreaAdmin();
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
	public AreaAdmin() {
		setTitle("\u00C1rea do Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel fotoTopo = new JLabel("");
		fotoTopo.setBounds(0, 0, 834, 301);
		
		ImageIcon imgTopo = new ImageIcon(SubscreverInvestig.class.getResource("/images/areaAdmin.png"));
		Image imgOne =imgTopo.getImage().getScaledInstance(fotoTopo.getWidth(), fotoTopo.getHeight(), Image.SCALE_SMOOTH);
		
		fotoTopo.setIcon(new ImageIcon(imgOne));
		
		contentPane.add(fotoTopo);
		
		JButton btnCriaoDeUtilizadores = new JButton("Cria\u00E7\u00E3o de Utilizadores");
		btnCriaoDeUtilizadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCriaoDeUtilizadores.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCriaoDeUtilizadores.setBounds(116, 348, 181, 53);
		contentPane.add(btnCriaoDeUtilizadores);
		
		JButton btnManutenoDeUtilizadores = new JButton("Manuten\u00E7\u00E3o de Utilizadores");
		btnManutenoDeUtilizadores.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnManutenoDeUtilizadores.setBounds(307, 348, 201, 53);
		contentPane.add(btnManutenoDeUtilizadores);
		
		JButton btnManutenoDeVariveis = new JButton("Manuten\u00E7\u00E3o de Vari\u00E1veis");
		btnManutenoDeVariveis.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnManutenoDeVariveis.setBounds(518, 348, 181, 53);
		contentPane.add(btnManutenoDeVariveis);
		
		JButton button = new JButton("LogOut");
		button.setBounds(675, 499, 89, 23);
		contentPane.add(button);
	}

}

//JLabel lblNewLabel = new JLabel("Nome");
//lblNewLabel.setBounds(76, 198, 46, 14);
//frmSubscreverInvestigador.getContentPane().add(lblNewLabel);
//
//JLabel a = new JLabel("");
//a.setBounds(0, 0, 834, 144);
//
//ImageIcon imgTopo = new ImageIcon(SubscreverInvestig.class.getResource("/images/SubscricaoInvestigador.png"));
//Image imgOne =imgTopo.getImage().getScaledInstance(a.getWidth(), a.getHeight(), Image.SCALE_SMOOTH);
//
//imagTopo.setIcon(new ImageIcon(imgOne));
//
//frmSubscreverInvestigador.getContentPane().add(imagTopo);
//}}

