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
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
//import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForInvestigador;

/**
 * Esta classe e referente a interface grafico para o investigador poder criar uma cultura
 * @author Grupo 21
 * 
 *
 */
public class CriarCultura extends JFrame {
    
    private DatabaseMiddleManForInvestigador databaseConnection;
    private JFrame frame;
    private JPanel contentPanel;
    
      
    
    /**
     * Lanca a interface da criacao da cultura
     * 
     * @param databaseConnection  esta parametro é a ligacao da base de dados
     */
    public CriarCultura(DatabaseMiddleManForInvestigador databaseConnection) {
        this.databaseConnection = databaseConnection;
        startCriarCultura();
    }
    
    /**
     * Metodo para fechar a janela
     */
    private void closeWindow() {
        frame.setVisible(false);
    }
    
    
    /**
     * Cria a janela para o Investigador poder criar as suas culturas.
     */
    private void startCriarCultura() {
        frame= new JFrame();
        frame.setTitle("Medi\u00E7\u00F5es de Sistema");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 850, 600);
        frame.setVisible(true);
        LoginWindow.centerWindow(frame);
        frame.setResizable(false);
        setTitle("Criar Nova Cultura");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100,  850, 600);
        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        contentPanel.setLayout(null);
        
        ImageIcon img = new ImageIcon(ManutencaoVariaveis.class.getResource("/images/criarCultura.png"));
        
        JTextField NomeCultura = new JTextField();
        NomeCultura.setColumns(10);
        NomeCultura.setBounds(206, 299, 294, 27);
        contentPanel.add(NomeCultura);
        
        JTextField IdCultura = new JTextField();
        IdCultura.setToolTipText("Apenas se deseja alterar uma cultura existente");
        IdCultura.setColumns(10);
        IdCultura.setBounds(167, 175, 126, 27);
        contentPanel.add(IdCultura);
        
        
        JTextField Descricao = new JTextField();
        Descricao.setColumns(10);
        Descricao.setBounds(167, 364, 618, 27);
        contentPanel.add(Descricao);
        
        JButton Return = new JButton("Voltar");
        Return.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CulturasLista(databaseConnection);
                closeWindow();
            }
        });
        Return.setBounds(717, 500, 89, 23);
        contentPanel.add(Return);
        
        JTextField IdInvestigador = new JTextField();
        IdInvestigador.setColumns(10);
        IdInvestigador.setBounds(205, 238, 126, 27);
        IdInvestigador.setText(String.valueOf(databaseConnection.getMyId()));
        IdInvestigador.setEditable(false);
        contentPanel.add(IdInvestigador);
        
        JButton btnAdicionarVariavel = new JButton("Adicionar Variavel");
        btnAdicionarVariavel.setBounds(228, 435, 140, 29);
        
        JLabel imagemFundo = new JLabel("");
        imagemFundo.setBounds(0, 0, 834, 462);
        Image imgm = img.getImage().getScaledInstance(imagemFundo.getWidth(), imagemFundo.getHeight(), Image.SCALE_SMOOTH);
        
        
        imagemFundo.setIcon(new ImageIcon (imgm));
        JButton ACultura = new JButton("Adicionar/Alterar Cultura");
        ACultura.setBounds(378, 435, 177, 29);
        ACultura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!IdInvestigador.getText().equals("")
                        && !NomeCultura.getText().equals("") && !Descricao.getText().equals(""))
                    if(IdCultura.getText().equals(""))
                        if(databaseConnection.createCultura(NomeCultura.getText(), Descricao.getText()))
                            JOptionPane.showMessageDialog(frame.getContentPane(),
                                    "Succeso",
                                    "Information",
                                    JOptionPane.INFORMATION_MESSAGE);
                        else
                            JOptionPane.showMessageDialog(frame.getContentPane(),
                                    "Erro ao criar uma cultura",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                    else
                        if(databaseConnection.updateCultura(Integer.parseInt(IdCultura.getText()),NomeCultura.getText(), Descricao.getText()))
                            JOptionPane.showMessageDialog(frame.getContentPane(),
                                    "Succeso",
                                    "Information",
                                    JOptionPane.INFORMATION_MESSAGE);
                        else
                            JOptionPane.showMessageDialog(frame.getContentPane(),
                                    "Erro ao criar uma cultura",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(frame.getContentPane(),
                            "Os campos estao vazios",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                
            }
        });
        contentPanel.add(ACultura);
        
        
        contentPanel.add(imagemFundo);
        frame.add(contentPanel);
    }
    
}
