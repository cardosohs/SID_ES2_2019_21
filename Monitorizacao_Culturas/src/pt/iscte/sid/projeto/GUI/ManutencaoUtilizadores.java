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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManutencaoUtilizadores extends JFrame {

	private JPanel contentPane;
	private JTextField txtFaltaAdaptarPara;
	private JTextField txtFaltaAdaptarPara_1;
	private JTextField textField;
	private JTextField textField_1;

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
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon image = new ImageIcon (ManutencaoUtilizadores.class.getResource("/images/manutencaoUtilizadores.png"));
		
		JButton btnSubmeterAlteraes = new JButton("Alterar Utilizador");
		btnSubmeterAlteraes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmeterAlteraes.setBounds(390, 497, 158, 30);
		contentPane.add(btnSubmeterAlteraes);
		
		JButton btnAdicionarUtilizador = new JButton("Adicionar Novo Utilizador");
		btnAdicionarUtilizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubscreverUtilizador goWindow= new SubscreverUtilizador();
			//	goWindow.setVisible(true);
			}
		});
		btnAdicionarUtilizador.setBounds(547, 497, 179, 30);
		contentPane.add(btnAdicionarUtilizador);
		
		JButton btnEliminarUtilizador = new JButton("Eliminar Utilizador com este ID");
		btnEliminarUtilizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarUtilizador.setBounds(185, 497, 206, 30);
		contentPane.add(btnEliminarUtilizador);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AreaAdmin ad = new AreaAdmin();
				ad.setVisible(true);
			}
		});
		btnVoltar.setBounds(725, 497, 85, 30);
		contentPane.add(btnVoltar);
		
		txtFaltaAdaptarPara = new JTextField();
		txtFaltaAdaptarPara.setText("Falta adaptar para receber a tabela Administradores; Ver como em: https://www.youtube.com/watch?v=6cNYUc2PIag");
		txtFaltaAdaptarPara.setBounds(80, 235, 292, 30);
		contentPane.add(txtFaltaAdaptarPara);
		txtFaltaAdaptarPara.setColumns(10);
		
		txtFaltaAdaptarPara_1 = new JTextField();
		txtFaltaAdaptarPara_1.setText("Falta adaptar para receber a tabela Investigadores");
		txtFaltaAdaptarPara_1.setColumns(10);
		txtFaltaAdaptarPara_1.setBounds(456, 244, 269, 39);
		contentPane.add(txtFaltaAdaptarPara_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(143, 487, 31, 20);
		contentPane.add(textField);
		
		JLabel imagemFundo = new JLabel("");
		imagemFundo.setBounds(0, 0, 834, 458);
		Image img = image.getImage().getScaledInstance(imagemFundo.getWidth(), imagemFundo.getHeight(), Image.SCALE_SMOOTH);
		
		imagemFundo.setIcon(new ImageIcon (img));
		
		contentPane.add(imagemFundo);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(143, 518, 31, 20);
		contentPane.add(textField_1);
		
		JLabel lblIdAdministrador = new JLabel("ID Administrador");
		lblIdAdministrador.setBounds(48, 487, 97, 16);
		contentPane.add(lblIdAdministrador);
		
		JLabel lblIdInvestigador = new JLabel("ID Investigador");
		lblIdInvestigador.setBounds(48, 518, 97, 16);
		contentPane.add(lblIdInvestigador);
	}
}
