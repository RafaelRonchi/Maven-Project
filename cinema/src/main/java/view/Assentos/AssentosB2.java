package view.Assentos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controller.AssentoDAO;
import main.Main;
import model.Assento;
import model.RoundedPopopMenu;
import model.Sala;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;
import view.Assentos.AssentosCompra.CadastroAssentos;
import view.Filmes.SelecionarFilme;
import view.Salas.SelecionarSala2;

import javax.swing.SwingConstants;

public class AssentosB2 extends JFrame {

	private JPanel contentPane;
	static JButton[][] assentos;
	public static boolean cor;
	public static boolean[][] assentosOcupados = new boolean[5][6];
	Sala salasAssentos = new Sala("B2");
	private AssentoDAO assentoDAO = AssentoDAO.getInstancia();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssentosB2 frame = new AssentosB2();
					frame.setVisible(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AssentosB2() {
		assentosOcupados = assentoDAO.pegarAssentosOcupados(salasAssentos);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AssentosA1.class.getResource("/Images/0609b1d7-4a7d-41be-bd18-081ecb35eb9e.png")));
		setBackground(Color.WHITE);
		Dimension tamanhoMinimo = new Dimension(860, 500);
		setTitle("Sistema de Cinema");
		 setMinimumSize(tamanhoMinimo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 500);
		

		contentPane = new JPanel();
	    contentPane.setBackground(new Color(0, 0, 64));
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(new MigLayout("", "[47px,grow][47px,grow][47px,grow][47px,grow][47px,grow][47px,grow][47px,grow][47px,grow][47px,grow][47px,grow][47px,grow][47px,grow][47px,grow][47px,grow]", "[49px,grow][49px,grow][49px,grow][49px,grow][49px,grow][49px,grow][49px,grow][49px,grow][49px,grow]"));
	    Main.JMenuAssentos(contentPane);
		
	    
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // fecha tela atual
				SelecionarSala2 selctSala = new SelecionarSala2();
				selctSala.setExtendedState(JFrame.MAXIMIZED_BOTH);
				selctSala.setVisible(true);
			}
		});
		
		btnNewButton.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 12));
		btnNewButton.setBackground(Color.WHITE);
		contentPane.add(btnNewButton, "cell 0 0,alignx left,aligny top");
		
	
		
		JLabel lblAssentosA = new JLabel("Assentos B2");
		lblAssentosA.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssentosA.setForeground(Color.WHITE);
		lblAssentosA.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 47));
		contentPane.add(lblAssentosA, "cell 0 1 14 1,grow");
		
		// Crie a matriz de botões
		assentos = new JButton[5][6];

		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 6; col++) {
				final int finalRow = row;
				final int finalCol = col;

				JButton btn = new JButton("");
				btn.setIcon(new ImageIcon(AssentosA1.class.getResource("/Images/24868_redmensioned.jpeg")));
				btn.setBackground(assentosOcupados[row][col] ? Color.RED : Color.WHITE);
				assentos[row][col] = btn;
				contentPane.add(btn, "cell " + (4 + col) + " " + (3 + row) + ",grow");

				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Assento assentoSelecionado = new Assento(finalRow, finalCol, salasAssentos);
						
						CadastroAssentos selctSala = new CadastroAssentos(assentoSelecionado);
						dispose();
						selctSala.setExtendedState(JFrame.MAXIMIZED_BOTH);
						selctSala.setVisible(true);

					}
				});
			}

		}
		
		
	}
}
