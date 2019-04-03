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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class ManutencaoUtilizadores extends JFrame {

	private JPanel contentPane;
	private JTextField txtFaltaAdaptarPara;
	private JTextField txtFaltaAdaptarPara_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManutencaoUtilizadores frame = new ManutencaoUtilizadores();
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
	public ManutencaoUtilizadores() {
		setTitle("Manuten\u00E7\u00E3o de Utilizadores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,  834, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon image = new ImageIcon (ManutencaoUtilizadores.class.getResource("/images/manutencaoUtilizadores.png"));
		
		JButton btnSubmeterAlteraes = new JButton("Submeter Altera\u00E7\u00F5es");
		btnSubmeterAlteraes.setBounds(235, 438, 158, 30);
		contentPane.add(btnSubmeterAlteraes);
		
		JButton btnAdicionarUtilizador = new JButton("Adicionar Utilizador");
		btnAdicionarUtilizador.setBounds(393, 438, 158, 30);
		contentPane.add(btnAdicionarUtilizador);
		
		JButton btnEliminarUtilizador = new JButton("Eliminar Utilizador");
		btnEliminarUtilizador.setBounds(551, 438, 152, 30);
		contentPane.add(btnEliminarUtilizador);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(703, 438, 85, 30);
		contentPane.add(btnVoltar);
		
		txtFaltaAdaptarPara = new JTextField();
		txtFaltaAdaptarPara.setText("Falta adaptar para receber a tabela Administradores");
		txtFaltaAdaptarPara.setBounds(103, 235, 269, 39);
		contentPane.add(txtFaltaAdaptarPara);
		txtFaltaAdaptarPara.setColumns(10);
		
		txtFaltaAdaptarPara_1 = new JTextField();
		txtFaltaAdaptarPara_1.setText("Falta adaptar para receber a tabela Investigadores");
		txtFaltaAdaptarPara_1.setColumns(10);
		txtFaltaAdaptarPara_1.setBounds(456, 244, 269, 39);
		contentPane.add(txtFaltaAdaptarPara_1);
		
		JLabel imagemFundo = new JLabel("");
		imagemFundo.setBounds(0, 0, 818, 468);
		Image img = image.getImage().getScaledInstance(imagemFundo.getWidth(), imagemFundo.getHeight(), Image.SCALE_SMOOTH);
		
		imagemFundo.setIcon(new ImageIcon (img));
		
		contentPane.add(imagemFundo);
	}
}
