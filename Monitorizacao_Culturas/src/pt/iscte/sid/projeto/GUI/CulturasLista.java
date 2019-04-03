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

public class CulturasLista extends JFrame {

	private JPanel CulturasSobResponsabilidade;
	private JTextField txtInvestigador;
	private JTextField txtParaApagarPara;

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

	/**
	 * Create the frame.
	 */
	public CulturasLista() {
		setTitle("Culturas Sob a Responsabilidade do Investigador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,  850, 600);
		CulturasSobResponsabilidade = new JPanel();
		CulturasSobResponsabilidade.setBackground(Color.WHITE);
		CulturasSobResponsabilidade.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(CulturasSobResponsabilidade);
		CulturasSobResponsabilidade.setLayout(null);
		
		ImageIcon imgTopo = new ImageIcon(SubscreverUtilizador.class.getResource("/images/CulturasListagem.png"));
		
		JButton btnEliminarCulturaSeleccionada = new JButton("Eliminar Cultura Seleccionada");
		btnEliminarCulturaSeleccionada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.setBounds(650, 500, 89, 23);
		CulturasSobResponsabilidade.add(btnNewButton_1);
		btnEliminarCulturaSeleccionada.setBounds(78, 500, 216, 23);
		CulturasSobResponsabilidade.add(btnEliminarCulturaSeleccionada);
		
		JButton btnNewButton = new JButton("Submeter Altera\u00E7\u00F5es");
		btnNewButton.setBounds(479, 500, 161, 23);
		CulturasSobResponsabilidade.add(btnNewButton);
		
		JButton ButtonAddCultura = new JButton("Adicionar Nova Cultura");
		ButtonAddCultura.setBounds(304, 500, 165, 23);
		CulturasSobResponsabilidade.add(ButtonAddCultura);
		
		JButton ButtonLoadTable = new JButton("Atualizar Info Culturas");
		ButtonLoadTable.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ButtonLoadTable.setBounds(563, 150, 165, 23);
		CulturasSobResponsabilidade.add(ButtonLoadTable);
		
		txtInvestigador = new JTextField();
		txtInvestigador.setHorizontalAlignment(SwingConstants.CENTER);
		txtInvestigador.setBackground(new Color(0, 51, 102));
		txtInvestigador.setForeground(Color.WHITE);
		txtInvestigador.setFont(txtInvestigador.getFont().deriveFont(11f));
		txtInvestigador.setText("Investigador");
		txtInvestigador.setBounds(594, 72, 68, 21);
		CulturasSobResponsabilidade.add(txtInvestigador);
		txtInvestigador.setColumns(10);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(673, 73, 129, 20);
		CulturasSobResponsabilidade.add(textPane);
		
		JLabel imagemTopo = new JLabel("Investigador:");
		imagemTopo.setBounds(0, 0, 834, 165);
		Image imgOne =imgTopo.getImage().getScaledInstance(imagemTopo.getWidth(), imagemTopo.getHeight(), Image.SCALE_SMOOTH);
		
		imagemTopo.setIcon(new ImageIcon(imgOne));
		
		CulturasSobResponsabilidade.add(imagemTopo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(110, 184, 620, 305);
		CulturasSobResponsabilidade.add(scrollPane);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(164, 231, 402, 254);
		CulturasSobResponsabilidade.add(editorPane);
		editorPane.setBackground(new Color(250, 235, 215));
		
		txtParaApagarPara = new JTextField();
		txtParaApagarPara.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtParaApagarPara.setText("PARA APAGAR: Para aqui ser\u00E1 transposta a tabela Cultura (apenas Info deste invest), a partir do SQL. Ver como em: https://www.youtube.com/watch?v=6cNYUc2PIag");
		txtParaApagarPara.setBounds(67, 514, 682, 36);
		CulturasSobResponsabilidade.add(txtParaApagarPara);
		txtParaApagarPara.setColumns(10);
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