package pt.iscte.sid.projeto.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;

public class AreaInvestigador extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AreaInvestigador frame = new AreaInvestigador();
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
	public AreaInvestigador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel imagTopo = new JLabel("");
		imagTopo.setBackground(Color.WHITE);
		imagTopo.setBounds(0, 0, 834, 152);
		
		ImageIcon imgTopo = new ImageIcon(SubscreverInvestig.class.getResource("/images/area do investigador.png"));
		Image imgOne =imgTopo.getImage().getScaledInstance(imagTopo.getWidth(), imagTopo.getHeight(), Image.SCALE_SMOOTH);
		
		imagTopo.setIcon(new ImageIcon(imgOne));
		
		
		
		contentPane.add(imagTopo);
		
		JLabel Menu = new JLabel("");
		//Menu.setHorizontalAlignment(SwingConstants.RIGHT);
		Menu.setBounds(212, 219, 363, 75);
		ImageIcon menu = new ImageIcon(SubscreverInvestig.class.getResource("/images/MenuAreaInvest.png"));
		Image imgTwo =imgTopo.getImage().getScaledInstance(imagTopo.getWidth(), imagTopo.getHeight(), Image.SCALE_SMOOTH);
		
		imagTopo.setIcon(new ImageIcon(imgTwo));
		contentPane.add(Menu);
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
