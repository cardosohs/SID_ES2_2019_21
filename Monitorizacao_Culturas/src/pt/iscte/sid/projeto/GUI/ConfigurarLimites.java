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

public class ConfigurarLimites extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton button;
	private JTextField txtFaltaAdaptarPara;
	private JButton btnAdicionarNovosLimites;
	private JButton button_2;
	private JButton btnEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigurarLimites frame = new ConfigurarLimites();
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
	public ConfigurarLimites() {
		setTitle("Configurar Limites de Vari\u00E1veis de Sistema");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,  850, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon img = new ImageIcon (ConfigurarLimites.class.getResource("/images/configurarLimites.png"));
		
		textField = new JTextField();
		textField.setBounds(648, 195, 86, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(648, 237, 86, 29);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(648, 295, 86, 29);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(648, 340, 86, 29);
		contentPane.add(textField_3);
		
		button = new JButton("Voltar");
		button.setBounds(721, 509, 85, 30);
		contentPane.add(button);
		
		txtFaltaAdaptarPara = new JTextField();
		txtFaltaAdaptarPara.setText("Falta adaptar para receber a tabela Sistema; Ver como em: https://www.youtube.com/watch?v=6cNYUc2PIag");
		txtFaltaAdaptarPara.setColumns(10);
		txtFaltaAdaptarPara.setBounds(157, 260, 292, 30);
		contentPane.add(txtFaltaAdaptarPara);
		
		btnAdicionarNovosLimites = new JButton("Adicionar Novos Limites");
		btnAdicionarNovosLimites.setBounds(602, 390, 177, 30);
		contentPane.add(btnAdicionarNovosLimites);
		
		button_2 = new JButton("Submeter Altera\u00E7\u00F5es");
		button_2.setBounds(271, 429, 158, 30);
		contentPane.add(button_2);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(136, 429, 105, 30);
		contentPane.add(btnEliminar);
		
		JLabel imagemFundo = new JLabel("");
		imagemFundo.setBounds(0, 0, 834, 476);
		Image imgm = img.getImage().getScaledInstance(imagemFundo.getWidth(), imagemFundo.getHeight(), Image.SCALE_SMOOTH);
		
		
		
		imagemFundo.setIcon(new ImageIcon (imgm));
		
		contentPane.add(imagemFundo);
	}

}
