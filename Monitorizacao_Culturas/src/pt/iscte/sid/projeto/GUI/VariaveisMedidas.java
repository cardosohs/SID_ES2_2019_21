package pt.iscte.sid.projeto.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

/**
 * Esta classe e referente a interface grafico para consultar as variaveis medidas
 * 
 * @author Grupo 21
 *
 */

public class VariaveisMedidas extends JFrame {

	private JPanel contentPane;
	private DatabaseMiddleManForInvestigador databaseConnection;
	private int culturaId;
	private int variavelId;


	/**
	 * Construtor da classe
	 * @param databaseConnection
	 */
	public VariaveisMedidas(DatabaseMiddleManForInvestigador databaseConnection, int culturaId, int variavelId) {
		this.culturaId = culturaId;
		this.databaseConnection = databaseConnection;
		this.variavelId=variavelId;
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


		JButton AdicionarNovaVariavel = new JButton("Adicionar Nova Variável à Cultura");
		AdicionarNovaVariavel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InserirDados(databaseConnection);
				setVisible(false);
			}
		});
		AdicionarNovaVariavel.setBounds(190, 472, 231, 23);
		contentPane.add(AdicionarNovaVariavel);



		JButton Voltar = new JButton("Voltar");
		Voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CulturasLista(databaseConnection);
				setVisible(false);
			}
		});
		Voltar.setBounds(735, 507, 89, 31);
		contentPane.add(Voltar);

		JTextField IdVariavel = new JTextField();
		IdVariavel.setBounds(686, 199, 86, 31);
		contentPane.add(IdVariavel);
		IdVariavel.setColumns(10);

		JTextField LimSup = new JTextField();
		LimSup.setColumns(10);
		LimSup.setBounds(686, 249, 86, 31);
		contentPane.add(LimSup);

		JTextField LimInf = new JTextField();
		LimInf.setColumns(10);
		LimInf.setBounds(686, 285, 86, 31);
		contentPane.add(LimInf);

		JTextField NovaMedicao = new JTextField();
		NovaMedicao.setColumns(10);
		NovaMedicao.setEditable(false);;
		NovaMedicao.setBounds(686, 327, 86, 31);
		contentPane.add(NovaMedicao);

		JTextField txtCultura_1 = new JTextField();
		txtCultura_1.setText("Cultura");
		txtCultura_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtCultura_1.setForeground(Color.WHITE);
		txtCultura_1.setFont(txtCultura_1.getFont().deriveFont(11f));
		txtCultura_1.setColumns(10);
		txtCultura_1.setBackground(new Color(0, 51, 102));
		txtCultura_1.setBounds(512, 80, 68, 21);
		contentPane.add(txtCultura_1);

		JTextField txtCultura = new JTextField();
		txtCultura.setText("ID");
		txtCultura.setHorizontalAlignment(SwingConstants.CENTER);
		txtCultura.setForeground(Color.WHITE);
		txtCultura.setFont(txtCultura.getFont().deriveFont(11f));
		txtCultura.setColumns(10);
		txtCultura.setBackground(new Color(0, 51, 102));
		txtCultura.setBounds(733, 80, 39, 21);
		contentPane.add(txtCultura);

		JTextField txtNome = new JTextField();
		txtNome.setText(databaseConnection.getNomeCultura(culturaId));
		txtNome.setBounds(582, 80, 141, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		JTextField txtId = new JTextField();
		txtId.setText(culturaId+"");
		txtId.setBounds(772, 80, 52, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);



		String[] Invesheader={"Limite","TimeStamp","Valor"};
		String[][] Invesdata=getList(databaseConnection.getMedicoes());
		DefaultTableModel Invesmodel = new DefaultTableModel(Invesdata,Invesheader);
		JTable table = new JTable(Invesmodel);
		ListSelectionModel selectInves= table.getSelectionModel();
		selectInves.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane Invesjs=new JScrollPane(table);
		Invesjs.setVisible(true);
		Invesjs.setBounds(25, 205, 533, 111);
		contentPane.add(Invesjs);

		String[] Medheader={"IdVarMed","LimiteSuperior","LimiteInferior"};
		String[][] Meddata=getList(databaseConnection.getVariaveisMedidas(culturaId, variavelId));
		DefaultTableModel Medmodel = new DefaultTableModel(Meddata,Medheader);
		JTable Medtable = new JTable(Medmodel);
		ListSelectionModel selectMed= Medtable.getSelectionModel();
		selectMed.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane Medjs=new JScrollPane(Medtable);
		Medjs.setVisible(true);
		Medjs.setBounds(25, 344, 533, 111);
		contentPane.add(Medjs);


		JLabel fotoDeFundo = new JLabel("");
		fotoDeFundo.setBounds(0, 0, 834, 525);
		Image img = imagem.getImage().getScaledInstance(fotoDeFundo.getWidth(),fotoDeFundo.getHeight(), Image.SCALE_SMOOTH);

		JLabel lblVariaveismedidas = new JLabel("VariaveisMedidas");
		lblVariaveismedidas.setBounds(264, 319, 104, 14);
		contentPane.add(lblVariaveismedidas);

		JTextField VariavelEliminar = new JTextField();
		VariavelEliminar.setText("Id a Eliminar");
		VariavelEliminar.setBounds(562, 473, 86, 20);
		contentPane.add(VariavelEliminar);
		VariavelEliminar.setColumns(10);
		contentPane.add(VariavelEliminar);

		JButton SubmeterAlteracoes = new JButton("Submeter Alterações");
		SubmeterAlteracoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!IdVariavel.getText().equals("") && !LimSup.getText().equals("") && !LimInf.getText().equals(""))
					if(!(Integer.parseInt(LimSup.getText())<Integer.parseInt(LimInf.getText())))
						if(databaseConnection.updateVariaveisMedidas(Integer.parseInt(IdVariavel.getText()),
								Integer.parseInt(LimInf.getText()), Integer.parseInt(LimSup.getText()))) {
							contentPane.remove(Medjs);
							String[] Medheader={"IdVarMed","LimiteSuperior","LimiteInferior"};
							String[][] Meddata=getList(databaseConnection.getVariaveisMedidas(culturaId, variavelId));
							DefaultTableModel Medmodel = new DefaultTableModel(Meddata,Medheader);
							JTable Medtable = new JTable(Medmodel);
							ListSelectionModel selectMed= Medtable.getSelectionModel();
							selectMed.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							JScrollPane Medjs=new JScrollPane(Medtable);
							Medjs.setVisible(true);
							Medjs.setBounds(25, 344, 533, 111);
							contentPane.add(Medjs);

							JOptionPane.showMessageDialog(contentPane,
									"Sucesso",
									"Information",
									JOptionPane.INFORMATION_MESSAGE);
						}
						else
							JOptionPane.showMessageDialog(contentPane,
									"Ocorreu um erro ao alterar os dados",
									"Error",
									JOptionPane.ERROR_MESSAGE);
					else
						JOptionPane.showMessageDialog(contentPane,
								"O limite inferior nao pode ser maior que o superior",
								"Error",
								JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(contentPane,
							"Os campos estão vazios",
							"Error",
							JOptionPane.ERROR_MESSAGE);
			}
		});
		SubmeterAlteracoes.setBounds(657, 414, 167, 41);
		contentPane.add(SubmeterAlteracoes);

		JButton EliminarVariavel = new JButton("Eliminar Variável");
		EliminarVariavel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!VariavelEliminar.getText().equals(""))
					if(databaseConnection.deleteVariaveisMedidas(Integer.parseInt(VariavelEliminar.getText()))) {
						contentPane.remove(Medjs);
						String[] Medheader={"IdVarMed","LimiteSuperior","LimiteInferior"};
						String[][] Meddata=getList(databaseConnection.getVariaveisMedidas(culturaId, variavelId));
						DefaultTableModel Medmodel = new DefaultTableModel(Meddata,Medheader);
						JTable Medtable = new JTable(Medmodel);
						ListSelectionModel selectMed= Medtable.getSelectionModel();
						selectMed.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						JScrollPane Medjs=new JScrollPane(Medtable);
						Medjs.setVisible(true);
						Medjs.setBounds(25, 344, 533, 111);
						contentPane.add(Medjs);
						JOptionPane.showMessageDialog(contentPane,
								"Sucesso",
								"Information",
								JOptionPane.INFORMATION_MESSAGE);
						}
					else
						JOptionPane.showMessageDialog(contentPane,
								"Ocorreu um erro ao alterar os dados",
								"Error",
								JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(contentPane,
							"Os campos estão vazios",
							"Error",
							JOptionPane.ERROR_MESSAGE);
			}
		});
		EliminarVariavel.setBounds(658, 468, 144, 31);
		contentPane.add(EliminarVariavel);

		JTextField MedicaoTextBox = new JTextField();
		MedicaoTextBox.setColumns(10);
		MedicaoTextBox.setBounds(144, 524, 86, 14);
		contentPane.add(MedicaoTextBox);

		JTextField IdVarMedTextBox = new JTextField();
		IdVarMedTextBox.setColumns(10);
		IdVarMedTextBox.setBounds(144, 543, 86, 14);
		contentPane.add(IdVarMedTextBox);

		JLabel lblIdvarmed = new JLabel("IdVarMed");
		lblIdvarmed.setBounds(66, 546, 68, 14);
		contentPane.add(lblIdvarmed);

		JLabel LabelMedicao = new JLabel("Medi\u00E7\u00E3o");
		LabelMedicao.setBounds(66, 524, 68, 14);
		contentPane.add(LabelMedicao);

		JButton InserirMedicao = new JButton("InseriMedicao");
		InserirMedicao.setBounds(244, 520, 124, 31);
		InserirMedicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!MedicaoTextBox.getText().equals("") && !IdVarMedTextBox.getText().equals(""))
					if(databaseConnection.createMedicoes( Integer.parseInt(IdVarMedTextBox.getText()), 
							Integer.parseInt(MedicaoTextBox.getText()))) {
						contentPane.remove(Invesjs);
						String[] Invesheader={"Limite","TimeStamp","Valor"};
						String[][] Invesdata=getList(databaseConnection.getMedicoes());
						DefaultTableModel Invesmodel = new DefaultTableModel(Invesdata,Invesheader);
						JTable table = new JTable(Invesmodel);
						ListSelectionModel selectInves= table.getSelectionModel();
						selectInves.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						JScrollPane Invesjs=new JScrollPane(table);
						Invesjs.setVisible(true);
						Invesjs.setBounds(25, 205, 533, 111);
						contentPane.add(Invesjs);

						JOptionPane.showMessageDialog(contentPane,
								"Sucesso",
								"Information",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(contentPane,
								"Ocorreu um erro ao alterar os dados",
								"Error",
								JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(contentPane,
							"Os campos estão vazios",
							"Error",
							JOptionPane.ERROR_MESSAGE);
			}
		});
		contentPane.add(InserirMedicao);

		
		
		JTextField MedicaoTextField = new JTextField();
		MedicaoTextField.setText("Id a Eliminar");
		MedicaoTextField.setColumns(10);
		MedicaoTextField.setBounds(562, 369, 86, 20);
		contentPane.add(MedicaoTextField);
		
		JTextField ValorTextField = new JTextField();
		ValorTextField.setText("Valor");
		ValorTextField.setColumns(10);
		ValorTextField.setBounds(562, 390, 86, 20);
		contentPane.add(ValorTextField);

		JButton MedicaoAlterar = new JButton("Alterar Medicao");
		MedicaoAlterar.setBounds(657, 369, 167, 41);
		MedicaoAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!MedicaoTextField.getText().equals("") && !ValorTextField.getText().equals(""))
					if(databaseConnection.updateMedicao(Integer.parseInt(MedicaoTextField.getText()), 
							Integer.parseInt(ValorTextField.getText()))    ) {
						contentPane.remove(Invesjs);
						String[] Invesheader={"Limite","TimeStamp","Valor"};
						String[][] Invesdata=getList(databaseConnection.getMedicoes());
						DefaultTableModel Invesmodel = new DefaultTableModel(Invesdata,Invesheader);
						JTable table = new JTable(Invesmodel);
						ListSelectionModel selectInves= table.getSelectionModel();
						selectInves.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						JScrollPane Invesjs=new JScrollPane(table);
						Invesjs.setVisible(true);
						Invesjs.setBounds(25, 205, 533, 111);
						contentPane.add(Invesjs);

						JOptionPane.showMessageDialog(contentPane,
								"Sucesso",
								"Information",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(contentPane,
								"Ocorreu um erro ao alterar os dados",
								"Error",
								JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(contentPane,
							"Os campos estão vazios",
							"Error",
							JOptionPane.ERROR_MESSAGE);
			}
		});
		contentPane.add(MedicaoAlterar);
		fotoDeFundo.setIcon(new ImageIcon(img));
		contentPane.add(fotoDeFundo);
		this.setVisible(true);
		this.setResizable(false);
		LoginWindow.centerWindow(this);

	}

	/**
	 * Este metodo devolve uma matriz de strings com os valores da tabela culturas
	 * @param arg string com os valores concatenados
	 * @return matriz de strings
	 */
	private String[][] getList(String arg)
	{
		String[] lines = arg.split("BREAKLINE");
		String[][] linesCsv = new String[lines.length][];

		for (int i=0; i<lines.length; i++) {
			linesCsv[i] = lines[i].split("BREAKCOLUMN");
		}
		return linesCsv;
	}
}
