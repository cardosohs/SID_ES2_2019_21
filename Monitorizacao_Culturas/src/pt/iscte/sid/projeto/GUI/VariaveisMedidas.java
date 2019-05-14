package pt.iscte.sid.projeto.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Esta classe e referente a interface grafico para consultar as variaveis medidas
 * 
 * @author Grupo 21
 *
 */

public class VariaveisMedidas extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField txtCultura_1;
	private JTextField txtCultura;
	private JTextField txtNome;
	private JTextField txtId;
	private DatabaseMiddleManForInvestigador databaseConnection;
	private int culturaId;
	
    
    /**
     * Construtor da classe
     * @param databaseConnection
     */
    public VariaveisMedidas(DatabaseMiddleManForInvestigador databaseConnection, int culturaId) {
    	this.culturaId = culturaId;
    	this.databaseConnection = databaseConnection;
        initialize();
    }
    
    /**
     * Inicializa a interface
     */
	private void initialize() {
		setTitle("Consultar Vari\u00E1veis Medidas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon imagem = new ImageIcon(VariaveisMedidas.class.getResource("/images/variaveisMedidas.png"));
		
		JButton btnNewButton = new JButton("Submeter Alterações");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(645, 376, 167, 31);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Adicionar Nova Variável à Cultura");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 new InserirDados(databaseConnection);
				 setVisible(false);
			}
		});
		btnNewButton_1.setBounds(190, 472, 231, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Eliminar Variável");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(657, 415, 144, 31);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Voltar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 new CulturasLista(databaseConnection);
				 setVisible(false);
			}
		});
		btnNewButton_3.setBounds(735, 507, 89, 31);
		contentPane.add(btnNewButton_3);
		
		textField = new JTextField();
		textField.setBounds(686, 199, 86, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(686, 249, 86, 31);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(686, 285, 86, 31);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(686, 327, 86, 31);
		contentPane.add(textField_3);
		
		txtCultura_1 = new JTextField();
		txtCultura_1.setText("Cultura");
		txtCultura_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtCultura_1.setForeground(Color.WHITE);
		txtCultura_1.setFont(txtCultura_1.getFont().deriveFont(11f));
		txtCultura_1.setColumns(10);
		txtCultura_1.setBackground(new Color(0, 51, 102));
		txtCultura_1.setBounds(512, 80, 68, 21);
		contentPane.add(txtCultura_1);
		
		txtCultura = new JTextField();
		txtCultura.setText("ID");
		txtCultura.setHorizontalAlignment(SwingConstants.CENTER);
		txtCultura.setForeground(Color.WHITE);
		txtCultura.setFont(txtCultura.getFont().deriveFont(11f));
		txtCultura.setColumns(10);
		txtCultura.setBackground(new Color(0, 51, 102));
		txtCultura.setBounds(733, 80, 39, 21);
		contentPane.add(txtCultura);
		
		txtNome = new JTextField();
		txtNome.setText(databaseConnection.getNomeCultura(culturaId));
		txtNome.setBounds(582, 80, 141, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtId = new JTextField();
		txtId.setText(culturaId+"");
		txtId.setBounds(772, 80, 52, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		
		JLabel fotoDeFundo = new JLabel("");
		fotoDeFundo.setBounds(0, 0, 834, 525);
		Image img = imagem.getImage().getScaledInstance(fotoDeFundo.getWidth(),fotoDeFundo.getHeight(), Image.SCALE_SMOOTH);
		
		fotoDeFundo.setIcon(new ImageIcon(img));
		contentPane.add(fotoDeFundo);
		this.setVisible(true);
		LoginWindow.centerWindow(this);
		
	}
}
