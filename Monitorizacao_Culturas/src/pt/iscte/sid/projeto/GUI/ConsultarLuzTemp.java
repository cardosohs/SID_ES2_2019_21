package pt.iscte.sid.projeto.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultarLuzTemp extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarLuzTemp frame = new ConsultarLuzTemp();
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
	public ConsultarLuzTemp() {
		setTitle("Medi\u00E7\u00F5es de Sistema");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon background = new ImageIcon(ConsultarLuzTemp.class.getResource("/images/consultarLuzTemp.png"));
		
		JButton button = new JButton("Voltar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AreaInvestigador ai = new AreaInvestigador();
				ai.setVisible(true);
			}
		});
		button.setBounds(728, 469, 85, 30);
		contentPane.add(button);
		
		JLabel backPhoto = new JLabel("");
		backPhoto.setBounds(0, 0, 834, 523);
		
		Image img = background.getImage().getScaledInstance(backPhoto.getWidth(), backPhoto.getHeight(), Image.SCALE_SMOOTH);
		backPhoto.setIcon(new ImageIcon(img));
		contentPane.add(backPhoto);
	}
}
