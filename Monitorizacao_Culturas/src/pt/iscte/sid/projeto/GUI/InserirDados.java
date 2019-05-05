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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;

public class InserirDados {
    
    private DatabaseMiddleManForInvestigador databaseConnection;
    private JFrame frame;
    
    
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InserirDados window = new InserirDados(new DatabaseMiddleManForInvestigador("svbro@iscte-iul.com", "123"));
                    //window.frame.setVisible(true);
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
    
    public InserirDados(DatabaseMiddleManForInvestigador databaseConnection) {
        this.databaseConnection = databaseConnection;
        initialize();
    }
    
    private void CloseWindow() {
        frame.setVisible(false);
    }
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setTitle("Consultar Vari\u00E1veis");
        frame.setBounds(100, 100, 850, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        frame.setVisible(true);
        
        
        
        ImageIcon imagem = new ImageIcon (InserirDados.class.getResource("/images/listaVariaveis.png"));
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(110, 204, 618, 262);
        frame.getContentPane().add(scrollPane);
        
        JTable table = new JTable();
        scrollPane.setViewportView(table);
        
        
        JLabel lblSeleccionarIdDa = new JLabel("Seleccionar ID da Variavel");
        lblSeleccionarIdDa.setBounds(112, 496, 149, 16);
        frame.getContentPane().add(lblSeleccionarIdDa);
        
        JTextField IdVariavel = new JTextField();
        IdVariavel.setColumns(10);
        IdVariavel.setBounds(271, 490, 47, 29);
        frame.getContentPane().add(IdVariavel);
        
        JButton btnApagarVariavel = new JButton("Apagar variavel");
        btnApagarVariavel.setBounds(499, 511, 149, 23);
        frame.getContentPane().add(btnApagarVariavel);
        
        JLabel lblNewLabel_1 = new JLabel("Aqui fica a tabela Variaveis ver https://www.youtube.com/watch?v=hg1S3QHFNrE");
        lblNewLabel_1.setBounds(209, 166, 496, 14);
        frame.getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel = new JLabel("VER https://www.youtube.com/watch?v=hg1S3QHFNrE");
        lblNewLabel.setBounds(0, 0, 834, 485);
        
        Image img = imagem.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
        lblNewLabel.setIcon(new ImageIcon(img));
        
        
        
        frame.getContentPane().add(lblNewLabel);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(715, 526, 84, 23);
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AreaInvestigador(databaseConnection);
                CloseWindow();//Problema aqui - perceber pq
            }
        });
        
        frame.getContentPane().add(btnVoltar);
        
        JLabel lblSeleccionaridDaCultura = new JLabel("ID da Cultura");
        lblSeleccionaridDaCultura.setBounds(191, 529, 149, 16);
        frame.getContentPane().add(lblSeleccionaridDaCultura);
        
        JTextField IdCultura = new JTextField();
        IdCultura.setColumns(10);
        IdCultura.setBounds(271, 523, 47, 29);
        frame.getContentPane().add(IdCultura);
        JButton btnAdicionarVariavel = new JButton("Adicionar variavel");
        btnAdicionarVariavel.setBounds(340, 511, 149, 23);
        btnAdicionarVariavel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*  if(!IdVariavel.getText().equals("") && !IdCultura.getText().equals(""))
                // if()
                else
                JOptionPane.showMessageDialog(frame.getContentPane(),
                "Os campos estao vazios",
                "Error",
                JOptionPane.ERROR_MESSAGE); */
                
            }
        });
        frame.getContentPane().add(btnAdicionarVariavel);
        
    }
}

