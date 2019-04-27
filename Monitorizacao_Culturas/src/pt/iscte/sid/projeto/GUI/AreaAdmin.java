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
	 * Launch the application 
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
		
		ImageIcon imgTopo = new ImageIcon(SubscreverUtilizador.class.getResource("/images/areaAdmin.png"));
		
		JButton btnCriaoDeUtilizadores = new JButton("Cria\u00E7\u00E3o de Utilizadores");
		btnCriaoDeUtilizadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubscreverUtilizador su = new SubscreverUtilizador();
	//			su.setVisible(true);
			}
		});
		btnCriaoDeUtilizadores.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCriaoDeUtilizadores.setBounds(34, 368, 175, 35);
		contentPane.add(btnCriaoDeUtilizadores);
		
		JButton btnManutenoDeUtilizadores = new JButton("Manuten\u00E7\u00E3o de Utilizadores");
		btnManutenoDeUtilizadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManutencaoUtilizadores mu = new ManutencaoUtilizadores();
				mu.setVisible(true);
			}
		});
		btnManutenoDeUtilizadores.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnManutenoDeUtilizadores.setBounds(222, 368, 195, 35);
		contentPane.add(btnManutenoDeUtilizadores);
		
		JButton btnManutenoDeVariveis = new JButton("Manuten\u00E7\u00E3o de Vari\u00E1veis");
		btnManutenoDeVariveis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManutencaoVariaveis mv = new ManutencaoVariaveis();
				mv.setVisible(true);
			}
		});
		btnManutenoDeVariveis.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnManutenoDeVariveis.setBounds(427, 368, 185, 35);
		contentPane.add(btnManutenoDeVariveis);
		
		JButton button = new JButton("LogOut");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginWindow lw = new LoginWindow();
				lw.setVisible(true);
			}
		});
		button.setBounds(675, 499, 89, 23);
		contentPane.add(button);
		
		JButton btnConfigurarLimites = new JButton("Configurar LimitesSistema");
		btnConfigurarLimites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfigurarLimites ls = new ConfigurarLimites();
				ls.setVisible(true);
			}
		});
		btnConfigurarLimites.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConfigurarLimites.setBounds(628, 368, 185, 35);
		contentPane.add(btnConfigurarLimites);
		
		JLabel fotoTopo = new JLabel("");
		fotoTopo.setBounds(0, 0, 834, 476);
		Image imgOne =imgTopo.getImage().getScaledInstance(fotoTopo.getWidth(), fotoTopo.getHeight(), Image.SCALE_SMOOTH);
		
		fotoTopo.setIcon(new ImageIcon(imgOne));
		
		contentPane.add(fotoTopo);
	}
}



