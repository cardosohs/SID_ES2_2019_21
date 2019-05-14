package pt.iscte.sid.projeto.GUI;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;

/**
 * Esta classe e referente a interface grafico para o investigador poder inserir dados
 * @author Grupo 21
 *
 */
public class InserirDados {
    
    private DatabaseMiddleManForInvestigador databaseConnection;
    private JFrame frmConsultarListaVariveis;    
  
    
    
    /**
     * Lanca a interface para o investigador inserir dados
     * 
     * @param databaseConnection  esta parametro Ã© a ligacao da base de dados
     */
    public InserirDados(DatabaseMiddleManForInvestigador databaseConnection) {
        this.databaseConnection = databaseConnection;
        initialize();
    }
    
    
    /**
     * Metodo para fechar a janela
     */
    private void closeWindow() {
        frmConsultarListaVariveis.setVisible(false);
    }
    
    /**
     * Cria a janela para o Investigador poder inserir dados referentes as variaveis.
     */
    private void initialize() {

        frmConsultarListaVariveis = new JFrame();
        frmConsultarListaVariveis.getContentPane().setBackground(Color.WHITE);
        frmConsultarListaVariveis.setTitle("Consultar Lista Vari\u00E1veis");
        frmConsultarListaVariveis.setBounds(100, 100, 850, 600);
        frmConsultarListaVariveis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmConsultarListaVariveis.getContentPane().setLayout(null);        
        frmConsultarListaVariveis.setVisible(true);
        LoginWindow.centerWindow(frmConsultarListaVariveis);
        
        
        
        ImageIcon imagem = new ImageIcon (InserirDados.class.getResource("/images/listaVariaveis.png"));
       
        
        String[] Variaveisheader={"Id","IdNome"};
        String[][] Variaveisdata=getList(databaseConnection.getVariaveis());
        DefaultTableModel Variaveismodel = new DefaultTableModel(Variaveisdata,Variaveisheader);
        JTable table = new JTable(Variaveismodel);
        ListSelectionModel selectVariaveis= table.getSelectionModel();
        selectVariaveis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(112, 191, 620, 204);
        scrollPane.setViewportView(table);
        scrollPane.setVisible(true);
        scrollPane.setBounds(112, 191, 620, 165);
        frmConsultarListaVariveis.getContentPane().add(scrollPane);
        
        
        JLabel lblSeleccionarIdDa = new JLabel("ID da Variavel");
        lblSeleccionarIdDa.setBounds(39, 496, 149, 16);
        
        JTextField IdVariavel = new JTextField();
        IdVariavel.setColumns(10);
        IdVariavel.setBounds(181, 490, 47, 29);
        
        JButton btnApagarVariavel = new JButton("Apagar variavel");
        btnApagarVariavel.setBounds(540, 511, 149, 23);
        
        JLabel lblNewLabel_1 = new JLabel("Aqui fica a tabela Variaveis ver https://www.youtube.com/watch?v=hg1S3QHFNrE");
        lblNewLabel_1.setBounds(209, 166, 496, 14);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(725, 509, 84, 23);
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AreaInvestigador(databaseConnection);
                closeWindow();
            }
        });
        frmConsultarListaVariveis.getContentPane().add(btnVoltar);
        
        JTextField IdVarTextBox = new JTextField();
        IdVarTextBox.setBounds(276, 406, 95, 29);
        frmConsultarListaVariveis.getContentPane().add(IdVarTextBox);
        IdVarTextBox.setColumns(10);
        
        JLabel lblSeleccionaridDaVarivel = new JLabel("Seleccionar ID da Vari\u00E1vel");
        lblSeleccionaridDaVarivel.setBounds(111, 412, 150, 16);
        frmConsultarListaVariveis.getContentPane().add(lblSeleccionaridDaVarivel);
        
        JLabel lblSeleccionarIdDa_1 = new JLabel("Seleccionar ID da Cultura");
        lblSeleccionarIdDa_1.setBounds(112, 459, 149, 16);
        frmConsultarListaVariveis.getContentPane().add(lblSeleccionarIdDa_1);
        
        JTextField IdCulturaTextBox = new JTextField();
        IdCulturaTextBox.setColumns(10);
        IdCulturaTextBox.setBounds(276, 453, 95, 29);
        frmConsultarListaVariveis.getContentPane().add(IdCulturaTextBox);
        
     
        
        JLabel IdVarText = new JLabel(/*"VER https://www.youtube.com/watch?v=hg1S3QHFNrE"*/);
        IdVarText.setBounds(0, 0, 834, 485);
        
        Image img = imagem.getImage().getScaledInstance(IdVarText.getWidth(), IdVarText.getHeight(), Image.SCALE_SMOOTH);
        IdVarText.setIcon(new ImageIcon(img));
        
        
        
       
        
        JLabel lblSeleccionaridDaCultura = new JLabel("ID da Cultura");
        lblSeleccionaridDaCultura.setBounds(39, 529, 149, 16);
        
        JTextField IdCultura = new JTextField();
        IdCultura.setColumns(10);
        IdCultura.setBounds(181, 523, 47, 29); 
        JButton NovaVariavel = new JButton("Associar Nova Vari\u00E1vel \u00E0 Cultura\r\n");
        NovaVariavel.setBounds(402, 431, 250, 23);
        NovaVariavel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(!IdVarTextBox.getText().equals("") && !IdCulturaTextBox.getText().equals("")) {
            		new VariaveisMedidas(databaseConnection,Integer.parseInt(IdCulturaTextBox.getText()),Integer.parseInt(IdVarTextBox.getText()));
            		closeWindow();
            	}
            	else
            		JOptionPane.showMessageDialog(frmConsultarListaVariveis.getContentPane(),
                            "Os campos estão vazios",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
            }
        });
        frmConsultarListaVariveis.getContentPane().add(NovaVariavel);
        
        
        frmConsultarListaVariveis.getContentPane().add(IdVarText);
    }
    
    
    /**
     * Este metodo devolve uma matriz de strings com os valores das variaveis do investigador
     * @param arg recebe uma string
     * @return matriz de string
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

