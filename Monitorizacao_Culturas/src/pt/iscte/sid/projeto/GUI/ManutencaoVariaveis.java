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

public class ManutencaoVariaveis extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtFaltaAdaptarPara;

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

	/**
	 * Create the frame.
	 */
	public ManutencaoVariaveis() {
		setTitle("Manuten\u00E7\u00E3o de Vari\u00E1veis");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,  834, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon img = new ImageIcon(ManutencaoVariaveis.class.getResource("/images/manutencaoVariaveis.png"));
		
		textField = new JTextField();
		textField.setBounds(165, 382, 350, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAdicionarVarivel = new JButton("Adicionar Vari\u00E1vel");
		btnAdicionarVarivel.setBounds(519, 382, 142, 29);
		contentPane.add(btnAdicionarVarivel);
		
		JButton btnEliminarVarivel = new JButton("Eliminar Vari\u00E1vel");
		btnEliminarVarivel.setBounds(663, 381, 130, 30);
		contentPane.add(btnEliminarVarivel);
		
		JButton button_1 = new JButton("Voltar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AreaAdmin aa = new AreaAdmin();
				aa.setVisible(true);
			}
		});
		button_1.setBounds(708, 450, 85, 30);
		contentPane.add(button_1);
		
		txtFaltaAdaptarPara = new JTextField();
		txtFaltaAdaptarPara.setText("Falta adaptar para receber a tabela Variaveis; Ver como em: https://www.youtube.com/watch?v=6cNYUc2PIag");
		txtFaltaAdaptarPara.setColumns(10);
		txtFaltaAdaptarPara.setBounds(220, 243, 429, 30);
		contentPane.add(txtFaltaAdaptarPara);
		
		JLabel imagemFundo = new JLabel("");
		imagemFundo.setBounds(0, 0, 818, 467);
		
		Image imgm = img.getImage().getScaledInstance(imagemFundo.getWidth(), imagemFundo.getHeight(), Image.SCALE_SMOOTH);
		
		imagemFundo.setIcon(new ImageIcon (imgm));
		
		contentPane.add(imagemFundo);
	}

}
