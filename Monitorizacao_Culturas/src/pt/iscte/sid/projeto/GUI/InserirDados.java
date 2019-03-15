package pt.iscte.sid.projeto.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class InserirDados {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserirDados window = new InserirDados();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InserirDados() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 792, 506);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInserirDados = new JLabel("Inserir Dados");
		lblInserirDados.setBounds(332, 137, 87, 16);
		frame.getContentPane().add(lblInserirDados);
		
		JLabel lblNewLabel = new JLabel("(inserir Imagem)");
		lblNewLabel.setBounds(558, 44, 121, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID Cultura");
		lblNewLabel_1.setBounds(38, 219, 67, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblPhMeio = new JLabel("ph Meio");
		lblPhMeio.setBounds(38, 253, 135, 16);
		frame.getContentPane().add(lblPhMeio);
		
		JLabel lblConcentraoO = new JLabel("Concentracao O2");
		lblConcentraoO.setBounds(38, 282, 121, 16);
		frame.getContentPane().add(lblConcentraoO);
		
		JLabel lblConcentracaoCo = new JLabel("Concentracao CO2");
		lblConcentracaoCo.setBounds(38, 311, 121, 16);
		frame.getContentPane().add(lblConcentracaoCo);
		
		JLabel lblTempoGeracao = new JLabel("Tempo Geracao");
		lblTempoGeracao.setBounds(38, 340, 121, 16);
		frame.getContentPane().add(lblTempoGeracao);
		
		JLabel lblContaminantes = new JLabel("Contaminantes?");
		lblContaminantes.setBounds(38, 368, 121, 16);
		frame.getContentPane().add(lblContaminantes);
		
		JLabel lblEspecieEmCrescimento = new JLabel("Especie em Crescimento");
		lblEspecieEmCrescimento.setBounds(396, 219, 155, 16);
		frame.getContentPane().add(lblEspecieEmCrescimento);
		
		JLabel lblColonias = new JLabel("# Colonias");
		lblColonias.setBounds(396, 253, 155, 16);
		frame.getContentPane().add(lblColonias);
		
		JLabel lblNutrientesEmFalta = new JLabel("Nutrientes em falta");
		lblNutrientesEmFalta.setBounds(396, 282, 155, 16);
		frame.getContentPane().add(lblNutrientesEmFalta);
		
		JLabel lblNutrientes = new JLabel("Nutrientes?");
		lblNutrientes.setBounds(396, 311, 155, 16);
		frame.getContentPane().add(lblNutrientes);
		
		JLabel lblColniasAmostra = new JLabel("# colnias amostra");
		lblColniasAmostra.setBounds(396, 340, 155, 16);
		frame.getContentPane().add(lblColniasAmostra);
		
		JButton btnAssociar = new JButton("Associar");
		btnAssociar.setBounds(664, 414, 97, 25);
		frame.getContentPane().add(btnAssociar);
		
		textField = new JTextField();
		textField.setBounds(185, 219, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(185, 253, 116, 22);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(185, 285, 116, 22);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(185, 314, 116, 22);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(185, 343, 116, 22);
		frame.getContentPane().add(textField_4);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(563, 219, 116, 22);
		frame.getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(563, 253, 116, 22);
		frame.getContentPane().add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(563, 282, 116, 22);
		frame.getContentPane().add(textField_8);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("sim");
		chckbxNewCheckBox.setBounds(566, 307, 113, 25);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(563, 340, 116, 22);
		frame.getContentPane().add(textField_9);
		
		JCheckBox chckbxSim = new JCheckBox("sim");
		chckbxSim.setBounds(185, 367, 113, 25);
		frame.getContentPane().add(chckbxSim);
	}
}
