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
    
    private JPanel CulturasSobResponsabilidade;
    
    private JFrame frame;
    private DatabaseMiddleManForInvestigador databaseConnection;
    
  
    /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new CulturasLista(new DatabaseMiddleManForInvestigador("NovoInvestigador", "12345"));
                    // frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    */
    
    /**
     * Metodo para lancar a interface com ligacao a base de dados
     * @param databaseConnection conexao a base de dados mysql
     */
    public CulturasLista(DatabaseMiddleManForInvestigador databaseConnection) {
        this.databaseConnection = databaseConnection;
        StartCulturasLista();
    }
    
    /**
     * Metodo para ocultar a janela
     */

    private void CloseWindow() {
        frame.setVisible(false);
    }
    
    
    /**
     * Metodo para criar a janela da lista de culturas
     */
    public void StartCulturasLista() {
        frame= new JFrame();
        frame.setTitle("Medi\u00E7\u00F5es de Sistema");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 850, 600);
        frame.setVisible(true);
        frame.setResizable(false);
        setTitle("Culturas Sob a Responsabilidade do Investigador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100,  850, 600);
        CulturasSobResponsabilidade = new JPanel();
        CulturasSobResponsabilidade.setBackground(Color.WHITE);
        CulturasSobResponsabilidade.setBorder(new EmptyBorder(5, 5, 5, 5));
        CulturasSobResponsabilidade.setLayout(null);
        
        
        ImageIcon imgTopo = new ImageIcon(SubscreverUtilizador.class.getResource("/images/CulturasListagem.png"));
        
        
        
        
        /*JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(110, 184, 620, 305);
        CulturasSobResponsabilidade.add(scrollPane);
        
        JTextField txtParaApagarPara = new JTextField();
        scrollPane.setViewportView(txtParaApagarPara);
        txtParaApagarPara.setFont(new Font("Tahoma", Font.PLAIN, 9));
        txtParaApagarPara.setText("PARA APAGAR: Para aqui ser\u00E1 transposta a tabela Cultura + VariaveisMedidas + Medicoes (JOIN) (apenas Info deste invest), a partir do SQL. Ver como em: https://www.youtube.com/watch?v=6cNYUc2PIag");
        txtParaApagarPara.setColumns(10);
        */
        String[] Invesheader={"IdCultura","IdInvestigador","NomeCultura","Descricao"};
        String[][] Invesdata=GetList(databaseConnection.getCulturas());
        DefaultTableModel Invesmodel = new DefaultTableModel(Invesdata,Invesheader);
        JTable table = new JTable(Invesmodel);
        //table.setCellSelectionEnabled(true);
        // Investable.setCellSelectionEnabled(true);
        ListSelectionModel selectInves= table.getSelectionModel();
        selectInves.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane Invesjs=new JScrollPane(table);
        Invesjs.setVisible(true);
        Invesjs.setBounds(110, 184, 620, 305);
        CulturasSobResponsabilidade.add(Invesjs);
        
        
        JButton Voltar = new JButton("Voltar");
        Voltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AreaInvestigador(databaseConnection);
                CloseWindow();
            }
        });
        Voltar.setBounds(731, 527, 77, 23);
        CulturasSobResponsabilidade.add(Voltar);
        
        
        JButton ButtonAlterarrCultura = new JButton("Alterar Cultura ");
        ButtonAlterarrCultura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CriarCultura(databaseConnection);
                CloseWindow();
            }
        });
        ButtonAlterarrCultura.setBounds(402, 150, 149, 23);
        CulturasSobResponsabilidade.add(ButtonAlterarrCultura);
        
        JButton ButtonAddCultura = new JButton("Adicionar Nova Cultura");
        ButtonAddCultura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CriarCultura(databaseConnection);
                CloseWindow();
            }
        });
        ButtonAddCultura.setBounds(227, 150, 165, 23);
        CulturasSobResponsabilidade.add(ButtonAddCultura);
        
        JButton ButtonLoadTable = new JButton("Atualizar Info Culturas");
        ButtonLoadTable.setFont(new Font("Tahoma", Font.BOLD, 11));
        ButtonLoadTable.setBounds(563, 150, 165, 23);
        ButtonLoadTable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CulturasSobResponsabilidade.remove(Invesjs);
                String[] Invesheader={"IdCultura","IdInvestigador","NomeCultura","Descricao"};
                String[][] Invesdata=GetList(databaseConnection.getCulturas());
                DefaultTableModel Invesmodel = new DefaultTableModel(Invesdata,Invesheader);
                JTable table = new JTable(Invesmodel);
                //table.setCellSelectionEnabled(true);
                // Investable.setCellSelectionEnabled(true);
                ListSelectionModel selectInves= table.getSelectionModel();
                selectInves.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                JScrollPane Invesjs=new JScrollPane(table);
                Invesjs.setVisible(true);
                Invesjs.setBounds(110, 184, 620, 305);
                CulturasSobResponsabilidade.add(Invesjs);
                
            }
        });
        CulturasSobResponsabilidade.add(ButtonLoadTable);
        
        JTextField txtInvestigador = new JTextField();
        txtInvestigador.setHorizontalAlignment(SwingConstants.CENTER);
        txtInvestigador.setBackground(new Color(0, 51, 102));
        txtInvestigador.setForeground(Color.WHITE);
        txtInvestigador.setFont(txtInvestigador.getFont().deriveFont(11f));
        txtInvestigador.setText("Investigador");
        txtInvestigador.setBounds(505, 71, 68, 21);
        CulturasSobResponsabilidade.add(txtInvestigador);
        txtInvestigador.setColumns(10);
        
        JTextPane InvestigadorTextBox = new JTextPane();
        InvestigadorTextBox.setBackground(SystemColor.menu);
        InvestigadorTextBox.setBounds(582, 71, 129, 20);
        InvestigadorTextBox.setText(databaseConnection.getMyName());
        InvestigadorTextBox.setEditable(false);
        CulturasSobResponsabilidade.add(InvestigadorTextBox);
        
        JEditorPane editorPane = new JEditorPane();
        editorPane.setBounds(164, 231, 402, 254);
        CulturasSobResponsabilidade.add(editorPane);
        editorPane.setBackground(new Color(250, 235, 215));
        
        JTextField textField = new JTextField();
        textField.setText("ID");
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setForeground(Color.WHITE);
        textField.setFont(textField.getFont().deriveFont(11f));
        textField.setColumns(10);
        textField.setBackground(new Color(0, 51, 102));
        textField.setBounds(726, 71, 24, 21);
        CulturasSobResponsabilidade.add(textField);
        
        JTextPane IdTextBox = new JTextPane();
        IdTextBox.setBackground(SystemColor.menu);
        IdTextBox.setBounds(760, 72, 36, 20);
        IdTextBox.setText(String.valueOf(databaseConnection.getMyId()));
        IdTextBox.setEditable(false);
        CulturasSobResponsabilidade.add(IdTextBox);
        
        JTextField CulturaId = new JTextField();
        CulturaId.setColumns(10);
        CulturaId.setBounds(154, 514, 47, 29);
        CulturasSobResponsabilidade.add(CulturaId);
        
        JLabel lblIdDaCultura = new JLabel("SeleccionarID da Cultura");
        lblIdDaCultura.setBounds(10, 520, 149, 16);
        CulturasSobResponsabilidade.add(lblIdDaCultura);
        
        JButton btnEliminarCulturaSeleccionada = new JButton("Eliminar Cultura");
        btnEliminarCulturaSeleccionada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(CulturaId.getText().equals(""))
                    JOptionPane.showMessageDialog(frame.getContentPane(),
                            "O campo esta vazio",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                else
                    if(!databaseConnection.DeleteCultura(Integer.parseInt(CulturaId.getText())))
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
        btnEliminarCulturaSeleccionada.setBounds(208, 517, 149, 23);
        CulturasSobResponsabilidade.add(btnEliminarCulturaSeleccionada);
        
        JLabel imagemTopo = new JLabel("Investigador:");
        imagemTopo.setBounds(0, 0, 834, 165);
        Image imgOne =imgTopo.getImage().getScaledInstance(imagemTopo.getWidth(), imagemTopo.getHeight(), Image.SCALE_SMOOTH);
        
        imagemTopo.setIcon(new ImageIcon(imgOne));
        
        
        CulturasSobResponsabilidade.add(imagemTopo);
        frame.getContentPane().add(CulturasSobResponsabilidade);
    }
    
    
    /**
     * Este metodo devolve uma matriz de strings com os valores da tabela culturas
     * @param arg string com os valores concatenados
     * @return matriz de strings
     */
    private String[][] GetList(String arg)
    {
        String[] lines = arg.split("BREAKLINE");
        String[][] linesCsv = new String[lines.length][];
        
        for (int i=0; i<lines.length; i++) {
            linesCsv[i] = lines[i].split("BREAKCOLUMN");
        }
        return linesCsv;
    }
}


//JLabel lblNewLabel = new JLabel("Nome");
//lblNewLabel.setBounds(76, 198, 46, 14);
//frmSubscreverInvestigador.getContentPane().add(lblNewLabel);
//
//JLabel imagTopo = new JLabel("");
//imagTopo.setBounds(0, 0, 834, 144);
//
//ImageIcon imgTopo = new ImageIcon(SubscreverInvestig.class.getResource("/images/SubscricaoInvestigador.png"));
//Image imgOne =imgTopo.getImage().getScaledInstance(imagTopo.getWidth(), imagTopo.getHeight(), Image.SCALE_SMOOTH);
//
//imagTopo.setIcon(new ImageIcon(imgOne));
//
//frmSubscreverInvestigador.getContentPane().add(imagTopo);
//}}