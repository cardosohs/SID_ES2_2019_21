package pt.iscte.sid.projeto.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CriarCultura extends JFrame {

	private JPanel contentPane;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton;
	private JButton btnAdicionarsMinhas;
	private JButton btnVoltar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CriarCultura frame = new CriarCultura();
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
	public CriarCultura() {
		setTitle("Criar Nova Cultura");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,  850, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon img = new ImageIcon(ManutencaoVariaveis.class.getResource("/images/criarCultura.png"));
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(199, 242, 230, 27);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(152, 291, 240, 27);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(303, 344, 434, 27);
		contentPane.add(textField_4);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(154, 191, 58, 27);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(357, 191, 58, 27);
		contentPane.add(textField_1);
		
		btnNewButton = new JButton("OK");
		btnNewButton.setBounds(740, 344, 58, 27);
		contentPane.add(btnNewButton);
		
		JLabel imagemFundo = new JLabel("");
		imagemFundo.setBounds(0, 0, 834, 462);
		Image imgm = img.getImage().getScaledInstance(imagemFundo.getWidth(), imagemFundo.getHeight(), Image.SCALE_SMOOTH);
		
		imagemFundo.setIcon(new ImageIcon (imgm));
		
		contentPane.add(imagemFundo);
		
		btnAdicionarsMinhas = new JButton("Adicionar \u00E0s minhas Culturas");
		btnAdicionarsMinhas.setBounds(498, 498, 211, 30);
		contentPane.add(btnAdicionarsMinhas);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(719, 498, 90, 30);
		contentPane.add(btnVoltar);
	}

}
