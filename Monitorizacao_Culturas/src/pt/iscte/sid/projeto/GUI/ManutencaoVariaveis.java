package pt.iscte.sid.projeto.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import pt.iscte.sid.projeto.Machine.DatabaseMiddleManForAdministrador;

public class ManutencaoVariaveis extends JFrame {
    
    private DatabaseMiddleManForAdministrador databaseConnection;
    private JPanel contentPanel;
    private JTextField textField;
    private JTextField txtFaltaAdaptarPara;
    private JFrame frame;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ManutencaoVariaveis frame = new ManutencaoVariaveis();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ManutencaoVariaveis(DatabaseMiddleManForAdministrador databaseConnection) {
        this.databaseConnection = databaseConnection;
        StartManutencaoVariaveis();
    }

    public ManutencaoVariaveis() {
        StartManutencaoVariaveis();
    }
    
    
    private void CloseWindow() {
        frame.setVisible(false);
    }
    
    /**
     * Create the frame.
     */
    public void StartManutencaoVariaveis() {
        frame= new JFrame();
        frame.setTitle("Medi\u00E7\u00F5es de Sistema");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 850, 600);
        frame.setVisible(true);
        setTitle("Manuten\u00E7\u00E3o de Vari\u00E1veis");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100,  834, 530);
        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);
        
        ImageIcon img = new ImageIcon(ManutencaoVariaveis.class.getResource("/images/manutencaoVariaveis.png"));
        
        textField = new JTextField();
        textField.setBounds(165, 382, 350, 29);
        contentPanel.add(textField);
        textField.setColumns(10);
        
        JButton btnAdicionarVarivel = new JButton("Adicionar Vari\u00E1vel");
        btnAdicionarVarivel.setBounds(519, 382, 142, 29);
        contentPanel.add(btnAdicionarVarivel);
        
        JButton btnEliminarVarivel = new JButton("Eliminar Vari\u00E1vel");
        btnEliminarVarivel.setBounds(663, 381, 130, 30);
        contentPanel.add(btnEliminarVarivel);
        
        JButton button_1 = new JButton("Voltar");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AreaAdmin aa = new AreaAdmin();
<<<<<<< HEAD
                CloseWindow();
=======
                aa.setVisible(true);
>>>>>>> master
            }
        });
        button_1.setBounds(708, 450, 85, 30);
        contentPanel.add(button_1);
        
        txtFaltaAdaptarPara = new JTextField();
        txtFaltaAdaptarPara.setText("Falta adaptar para receber a tabela Variaveis; Ver como em: https://www.youtube.com/watch?v=6cNYUc2PIag");
        txtFaltaAdaptarPara.setColumns(10);
        txtFaltaAdaptarPara.setBounds(220, 243, 429, 30);
        contentPanel.add(txtFaltaAdaptarPara);
        
        JLabel imagemFundo = new JLabel("");
        imagemFundo.setBounds(0, 0, 818, 467);
        
        Image imgm = img.getImage().getScaledInstance(imagemFundo.getWidth(), imagemFundo.getHeight(), Image.SCALE_SMOOTH);
        
        imagemFundo.setIcon(new ImageIcon (imgm));
        
        contentPanel.add(imagemFundo);
        frame.add(contentPanel);
    }
    
}
