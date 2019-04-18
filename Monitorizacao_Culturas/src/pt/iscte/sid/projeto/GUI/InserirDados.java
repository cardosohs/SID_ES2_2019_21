package pt.iscte.sid.projeto.GUI;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class InserirDados {

	private JFrame frmConsultarVariveis;
	private JTable table;
	private JButton btnAdicionarVariavel;
	private JLabel lblSeleccionarIdDa;
	private JTextField textField;
	private JButton btnApagarVariavel;
	private JLabel lblNewLabel_1;
	private JButton btnVoltar;
	private JLabel lblSeleccionaridDaCultura;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserirDados window = new InserirDados();
					window.frmConsultarVariveis.setVisible(true);
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
		frmConsultarVariveis = new JFrame();
		frmConsultarVariveis.getContentPane().setBackground(Color.WHITE);
		frmConsultarVariveis.setTitle("Consultar Vari\u00E1veis");
		frmConsultarVariveis.setBounds(100, 100, 850, 600);
		frmConsultarVariveis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConsultarVariveis.getContentPane().setLayout(null);
		
		ImageIcon imagem = new ImageIcon (InserirDados.class.getResource("/images/listaVariaveis.png"));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(110, 204, 618, 262);
		frmConsultarVariveis.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnAdicionarVariavel = new JButton("Adicionar variavel");
		btnAdicionarVariavel.setBounds(340, 511, 149, 23);
		frmConsultarVariveis.getContentPane().add(btnAdicionarVariavel);
		
		lblSeleccionarIdDa = new JLabel("Seleccionar ID da Variavel");
		lblSeleccionarIdDa.setBounds(112, 496, 149, 16);
		frmConsultarVariveis.getContentPane().add(lblSeleccionarIdDa);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(271, 490, 47, 29);
		frmConsultarVariveis.getContentPane().add(textField);
		
		btnApagarVariavel = new JButton("Apagar variavel");
		btnApagarVariavel.setBounds(499, 511, 149, 23);
		frmConsultarVariveis.getContentPane().add(btnApagarVariavel);
		
		lblNewLabel_1 = new JLabel("Aqui fica a tabela Variaveis ver https://www.youtube.com/watch?v=hg1S3QHFNrE");
		lblNewLabel_1.setBounds(209, 166, 496, 14);
		frmConsultarVariveis.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("VER https://www.youtube.com/watch?v=hg1S3QHFNrE");
		lblNewLabel.setBounds(0, 0, 834, 485);
		
		Image img = imagem.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(img));
		
		frmConsultarVariveis.getContentPane().add(lblNewLabel);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(715, 526, 84, 23);
		frmConsultarVariveis.getContentPane().add(btnVoltar);
		
		lblSeleccionaridDaCultura = new JLabel("ID da Cultura");
		lblSeleccionaridDaCultura.setBounds(191, 529, 149, 16);
		frmConsultarVariveis.getContentPane().add(lblSeleccionaridDaCultura);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(271, 523, 47, 29);
		frmConsultarVariveis.getContentPane().add(textField_1);
	}
}

