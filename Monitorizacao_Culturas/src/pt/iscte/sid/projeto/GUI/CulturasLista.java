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
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;

public class CulturasLista extends JFrame {
    
    private JPanel CulturasSobResponsabilidade;
    private JTextField txtInvestigador;
    private JTextField txtParaApagarPara;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JFrame frame;
    private DatabaseMiddleManForInvestigador databaseConnection;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CulturasLista frame = new CulturasLista();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
    
    public CulturasLista(DatabaseMiddleManForInvestigador databaseConnection) {
        this.databaseConnection = databaseConnection;
        StartCulturasLista();
    }
    
    public CulturasLista() {
        StartCulturasLista();
    }
    
    private void CloseWindow() {
        frame.setVisible(false);
    }
    
    
    /**
     * Create the frame.
     */
    public void StartCulturasLista() {
        frame= new JFrame();
        frame.setTitle("Medi\u00E7\u00F5es de Sistema");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 850, 600);
        frame.setVisible(true);
        setTitle("Culturas Sob a Responsabilidade do Investigador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100,  850, 600);
        CulturasSobResponsabilidade = new JPanel();
        CulturasSobResponsabilidade.setBackground(Color.WHITE);
        CulturasSobResponsabilidade.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(CulturasSobResponsabilidade);
        CulturasSobResponsabilidade.setLayout(null);
        
       ImageIcon imgTopo = new ImageIcon(SubscreverUtilizador.class.getResource("/images/CulturasListagem.png"));
        
        JButton btnEliminarCulturaSeleccionada = new JButton("Eliminar Cultura");
        btnEliminarCulturaSeleccionada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(110, 184, 620, 305);
        CulturasSobResponsabilidade.add(scrollPane);
        
        txtParaApagarPara = new JTextField();
        scrollPane.setViewportView(txtParaApagarPara);
        txtParaApagarPara.setFont(new Font("Tahoma", Font.PLAIN, 9));
        txtParaApagarPara.setText("PARA APAGAR: Para aqui ser\u00E1 transposta a tabela Cultura + VariaveisMedidas + Medicoes (JOIN) (apenas Info deste invest), a partir do SQL. Ver como em: https://www.youtube.com/watch?v=6cNYUc2PIag");
        txtParaApagarPara.setColumns(10);
        
        JButton btnNewButton_1 = new JButton("Voltar");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AreaInvestigador ai = new AreaInvestigador();
                CloseWindow();
            }
        });
        btnNewButton_1.setBounds(731, 527, 77, 23);
        CulturasSobResponsabilidade.add(btnNewButton_1);
        btnEliminarCulturaSeleccionada.setBounds(211, 500, 149, 23);
        CulturasSobResponsabilidade.add(btnEliminarCulturaSeleccionada);
        
        JButton btnNewButton = new JButton("Alterar Cultura ");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CriarCultura cc = new CriarCultura();
                cc.setVisible(true);;
            }
        });
        btnNewButton.setBounds(211, 527, 149, 23);
        CulturasSobResponsabilidade.add(btnNewButton);
        
        JButton ButtonAddCultura = new JButton("Adicionar Nova Cultura");
        ButtonAddCultura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CriarCultura cc = new CriarCultura();
                cc.setVisible(true);
            }
        });
        ButtonAddCultura.setBounds(222, 150, 165, 23);
        CulturasSobResponsabilidade.add(ButtonAddCultura);
        
        JButton ButtonLoadTable = new JButton("Atualizar Info Culturas");
        ButtonLoadTable.setFont(new Font("Tahoma", Font.BOLD, 11));
        ButtonLoadTable.setBounds(563, 150, 165, 23);
        CulturasSobResponsabilidade.add(ButtonLoadTable);
        
        txtInvestigador = new JTextField();
        txtInvestigador.setHorizontalAlignment(SwingConstants.CENTER);
        txtInvestigador.setBackground(new Color(0, 51, 102));
        txtInvestigador.setForeground(Color.WHITE);
        txtInvestigador.setFont(txtInvestigador.getFont().deriveFont(11f));
        txtInvestigador.setText("Investigador");
        txtInvestigador.setBounds(505, 71, 68, 21);
        CulturasSobResponsabilidade.add(txtInvestigador);
        txtInvestigador.setColumns(10);
        
        JTextPane textPane = new JTextPane();
        textPane.setBackground(SystemColor.menu);
        textPane.setBounds(582, 71, 129, 20);
        CulturasSobResponsabilidade.add(textPane);
        
        JEditorPane editorPane = new JEditorPane();
        editorPane.setBounds(164, 231, 402, 254);
        CulturasSobResponsabilidade.add(editorPane);
        editorPane.setBackground(new Color(250, 235, 215));
        
        textField = new JTextField();
        textField.setText("ID");
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setForeground(Color.WHITE);
        textField.setFont(textField.getFont().deriveFont(11f));
        textField.setColumns(10);
        textField.setBackground(new Color(0, 51, 102));
        textField.setBounds(726, 71, 24, 21);
        CulturasSobResponsabilidade.add(textField);
        
        JTextPane textPane_1 = new JTextPane();
        textPane_1.setBackground(SystemColor.menu);
        textPane_1.setBounds(760, 72, 36, 20);
        CulturasSobResponsabilidade.add(textPane_1);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(154, 514, 47, 29);
        CulturasSobResponsabilidade.add(textField_1);
        
        JLabel lblIdDaCultura = new JLabel("SeleccionarID da Cultura");
        lblIdDaCultura.setBounds(10, 520, 149, 16);
        CulturasSobResponsabilidade.add(lblIdDaCultura);
        
        JLabel lblSeleccionaridDaVariavel = new JLabel("SeleccionarID da Variavel");
        lblSeleccionaridDaVariavel.setBounds(370, 520, 149, 16);
        CulturasSobResponsabilidade.add(lblSeleccionaridDaVariavel);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(517, 514, 47, 29);
        CulturasSobResponsabilidade.add(textField_2);
        
        JButton btnEliminarVariavel = new JButton("Eliminar Variavel");
        btnEliminarVariavel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnEliminarVariavel.setBounds(572, 500, 149, 23);
        CulturasSobResponsabilidade.add(btnEliminarVariavel);
        
        JButton btnAlterarVariavel = new JButton("Alterar Variavel ");
        btnAlterarVariavel.setBounds(572, 527, 149, 23);
        CulturasSobResponsabilidade.add(btnAlterarVariavel);
        
        JButton btnAdicionarNovaVariavel = new JButton("Adicionar Nova Variavel");
        btnAdicionarNovaVariavel.setBounds(388, 150, 172, 23);
        CulturasSobResponsabilidade.add(btnAdicionarNovaVariavel);
        
        JLabel imagemTopo = new JLabel("Investigador:");
        imagemTopo.setBounds(0, 0, 834, 165);
       Image imgOne =imgTopo.getImage().getScaledInstance(imagemTopo.getWidth(), imagemTopo.getHeight(), Image.SCALE_SMOOTH);
        
       imagemTopo.setIcon(new ImageIcon(imgOne));
        
        CulturasSobResponsabilidade.add(imagemTopo);
        frame.add(CulturasSobResponsabilidade);
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