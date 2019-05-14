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
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JEditorPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;


/**
 * Esta classe constroi o interface grafico para listar as culturas
 * @author Grupo 21
 */
public class CulturasLista extends JFrame {
    
    private JPanel culturasSobResponsabilidade;
    
    private JFrame frame;
    private DatabaseMiddleManForInvestigador databaseConnection;
    
   
    
    /**
     * Metodo para lancar a interface com ligacao a base de dados
     * @param databaseConnection conexao a base de dados mysql
     */
    public CulturasLista(DatabaseMiddleManForInvestigador databaseConnection) {
        this.databaseConnection = databaseConnection;
        startCulturasLista();
    }
    
    /**
     * Metodo para ocultar a janela
     */

    private void closeWindow() {
        frame.setVisible(false);
    }
    
    
    /**
     * Metodo para criar a janela da lista de culturas
     */
    private void startCulturasLista() {
        frame= new JFrame();
        frame.setTitle("Culturas Sob a Responsabilidade do Investigador");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 850, 600);
        frame.setVisible(true);
        LoginWindow.centerWindow(frame);
        frame.setResizable(false);
        culturasSobResponsabilidade = new JPanel();
        culturasSobResponsabilidade.setBackground(Color.WHITE);
        culturasSobResponsabilidade.setBorder(new EmptyBorder(5, 5, 5, 5));
        culturasSobResponsabilidade.setLayout(null);
        
        
        ImageIcon imgTopo = new ImageIcon(SubscreverUtilizador.class.getResource("/images/CulturasListagem.png"));
        
        
        String[] Invesheader={"IdCultura","IdInvestigador","NomeCultura","Descricao"};
        String[][] Invesdata=getList(databaseConnection.getCulturas());
        DefaultTableModel Invesmodel = new DefaultTableModel(Invesdata,Invesheader);
        JTable table = new JTable(Invesmodel);
        ListSelectionModel selectInves= table.getSelectionModel();
        selectInves.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane Invesjs=new JScrollPane(table);
        Invesjs.setVisible(true);
        Invesjs.setBounds(110, 184, 620, 305);
        culturasSobResponsabilidade.add(Invesjs);
        
        
        JButton Voltar = new JButton("Voltar");
        Voltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AreaInvestigador(databaseConnection);
                closeWindow();
            }
        });
        Voltar.setBounds(731, 527, 77, 23);
        culturasSobResponsabilidade.add(Voltar);
        
        
        JButton buttonAlterarCultura = new JButton("Alterar Cultura ");
        buttonAlterarCultura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CriarCultura(databaseConnection);
                closeWindow();
            }
        });
        buttonAlterarCultura.setBounds(402, 150, 149, 23);
        culturasSobResponsabilidade.add(buttonAlterarCultura);
        
        JButton buttonAddCultura = new JButton("Adicionar Nova Cultura");
        buttonAddCultura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CriarCultura(databaseConnection);
                closeWindow();
            }
        });
        buttonAddCultura.setBounds(227, 150, 165, 23);
        culturasSobResponsabilidade.add(buttonAddCultura);
        
        JButton buttonLoadTable = new JButton("Atualizar Info Culturas");
        buttonLoadTable.setFont(new Font("Tahoma", Font.BOLD, 11));
        buttonLoadTable.setBounds(563, 150, 165, 23);
        buttonLoadTable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                culturasSobResponsabilidade.remove(Invesjs);
                String[] Invesheader={"IdCultura","IdInvestigador","NomeCultura","Descricao"};
                String[][] Invesdata=getList(databaseConnection.getCulturas());
                DefaultTableModel Invesmodel = new DefaultTableModel(Invesdata,Invesheader);
                JTable table = new JTable(Invesmodel);
                ListSelectionModel selectInves= table.getSelectionModel();
                selectInves.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                JScrollPane Invesjs=new JScrollPane(table);
                Invesjs.setVisible(true);
                Invesjs.setBounds(110, 184, 620, 305);
                culturasSobResponsabilidade.add(Invesjs);
                
            }
        });
        culturasSobResponsabilidade.add(buttonLoadTable);
        
        JTextField txtInvestigador = new JTextField();
        txtInvestigador.setHorizontalAlignment(SwingConstants.CENTER);
        txtInvestigador.setBackground(new Color(0, 51, 102));
        txtInvestigador.setForeground(Color.WHITE);
        txtInvestigador.setFont(txtInvestigador.getFont().deriveFont(11f));
        txtInvestigador.setText("Investigador");
        txtInvestigador.setBounds(505, 71, 68, 21);
        culturasSobResponsabilidade.add(txtInvestigador);
        txtInvestigador.setColumns(10);
        
        JTextPane investigadorTextBox = new JTextPane();
        investigadorTextBox.setBackground(SystemColor.menu);
        investigadorTextBox.setBounds(582, 71, 129, 20);
        investigadorTextBox.setText(databaseConnection.getMyName());
        investigadorTextBox.setEditable(false);
        culturasSobResponsabilidade.add(investigadorTextBox);
        
        JEditorPane editorPane = new JEditorPane();
        editorPane.setBounds(164, 231, 402, 254);
        culturasSobResponsabilidade.add(editorPane);
        editorPane.setBackground(new Color(250, 235, 215));
        
        JTextField textField = new JTextField();
        textField.setText("ID");
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setForeground(Color.WHITE);
        textField.setFont(textField.getFont().deriveFont(11f));
        textField.setColumns(10);
        textField.setBackground(new Color(0, 51, 102));
        textField.setBounds(726, 71, 24, 21);
        culturasSobResponsabilidade.add(textField);
        
        JTextPane IdTextBox = new JTextPane();
        IdTextBox.setBackground(SystemColor.menu);
        IdTextBox.setBounds(760, 72, 36, 20);
        IdTextBox.setText(String.valueOf(databaseConnection.getMyId()));
        IdTextBox.setEditable(false);
        culturasSobResponsabilidade.add(IdTextBox);
        
        JTextField culturaId = new JTextField();
        culturaId.setColumns(10);
        culturaId.setBounds(154, 514, 47, 29);
        culturasSobResponsabilidade.add(culturaId);
        
        JLabel lblIdDaCultura = new JLabel("SeleccionarID da Cultura");
        lblIdDaCultura.setBounds(10, 520, 149, 16);
        culturasSobResponsabilidade.add(lblIdDaCultura);
        
        JButton btnEliminarCulturaSeleccionada = new JButton("Eliminar Cultura");
        btnEliminarCulturaSeleccionada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(culturaId.getText().equals(""))
                    JOptionPane.showMessageDialog(frame.getContentPane(),
                            "O campo esta vazio",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                else
                    if(!databaseConnection.deleteCultura(Integer.parseInt(culturaId.getText())))
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Está a mexer numa cultura que não lhe pertence",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(frame.getContentPane(),
                                "Successo",
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btnEliminarCulturaSeleccionada.setBounds(388, 517, 149, 23);
        culturasSobResponsabilidade.add(btnEliminarCulturaSeleccionada);
        
        JLabel imagemTopo = new JLabel("Investigador:");
        imagemTopo.setBounds(0, 0, 834, 165);
        Image imgOne =imgTopo.getImage().getScaledInstance(imagemTopo.getWidth(), imagemTopo.getHeight(), Image.SCALE_SMOOTH);
        
        imagemTopo.setIcon(new ImageIcon(imgOne));
        
        
        culturasSobResponsabilidade.add(imagemTopo);
        frame.getContentPane().add(culturasSobResponsabilidade);
        
        JButton btnNewButton = new JButton("Ver Variáveis Medidas");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
              if(culturaId.getText().equals(""))
              JOptionPane.showMessageDialog(frame.getContentPane(),
                      "O campo esta vazio",
                      "Error",
                      JOptionPane.ERROR_MESSAGE);
              else {
            	  int id = Integer.parseInt(culturaId.getText());
            	  new VariaveisMedidas(databaseConnection, id);
            	  closeWindow();
              }
        	}
        });
        btnNewButton.setBounds(211, 517, 167, 23);
        culturasSobResponsabilidade.add(btnNewButton);
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