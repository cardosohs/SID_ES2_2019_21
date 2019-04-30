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
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;

public class CriarCultura extends JFrame {
   
    private DatabaseMiddleManForAdministrador databaseConnection;
    private JFrame frame;
    private JPanel contentPanel;
    private JTextField textField_2;
    private JTextField textField_1;
    private JButton btnNewButton;
    private JTextField textField;
    private JButton button;
    private JTextField textField_3;
    private JButton btnAdicionarVariavel;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CriarCultura frame = new CriarCultura();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CriarCultura() {
        StartCriarCultura();
    }


    public CriarCultura(DatabaseMiddleManForAdministrador databaseConnection) {
        this.databaseConnection = databaseConnection;
        StartCriarCultura();
    }
    
    
    private void CloseWindow() {
        frame.setVisible(false);
    }
    
    
    /**
     * Create the frame.
     */
    public void StartCriarCultura() {
        frame= new JFrame();
        frame.setTitle("Medi\u00E7\u00F5es de Sistema");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 850, 600);
        frame.setVisible(true);
        setTitle("Criar Nova Cultura");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100,  850, 600);
        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);
        
        ImageIcon img = new ImageIcon(ManutencaoVariaveis.class.getResource("/images/criarCultura.png"));
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(206, 299, 294, 27);
        contentPanel.add(textField_2);
        
        textField_1 = new JTextField();
        textField_1.setToolTipText("Apenas se deseja alterar uma cultura existente");
        textField_1.setColumns(10);
        textField_1.setBounds(167, 175, 126, 27);
        contentPanel.add(textField_1);
        
        btnNewButton = new JButton("Adicionar/Alterar Cultura");
        btnNewButton.setBounds(378, 435, 177, 29);
        contentPanel.add(btnNewButton);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(167, 364, 618, 27);
        contentPanel.add(textField);
        
        button = new JButton("Voltar");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CulturasLista cl = new CulturasLista();

                CloseWindow();

                cl.setVisible(true);


            }
        });
        button.setBounds(717, 500, 89, 23);
        contentPanel.add(button);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(205, 238, 126, 27);
        contentPanel.add(textField_3);
        
        btnAdicionarVariavel = new JButton("Adicionar Variavel");
        btnAdicionarVariavel.setBounds(228, 435, 140, 29);
        contentPanel.add(btnAdicionarVariavel);
        
        JLabel imagemFundo = new JLabel("");
        imagemFundo.setBounds(0, 0, 834, 462);
        Image imgm = img.getImage().getScaledInstance(imagemFundo.getWidth(), imagemFundo.getHeight(), Image.SCALE_SMOOTH);
        


        imagemFundo.setIcon(new ImageIcon (imgm));

        
        contentPanel.add(imagemFundo);
        frame.add(contentPanel);
    }
    
}
