package view.Filmes;

import java.awt.EventQueue;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import control.FilmeDAO;
import main.Main;
import modelo.Filme;
import modelo.RoundedPopopMenu;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import net.miginfocom.swing.MigLayout;
import view.JFrameMain;
import view.Salas.SelecionarSala1;
import view.Salas.SelecionarSala2;
import view.Salas.SelecionarSala3;

import javax.swing.SwingConstants;

public class SelecionarFilme extends JFrame {

	private JPanel contentPane;
	private FilmeDAO filmeDAO = FilmeDAO.getInstancia();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelecionarFilme frame = new SelecionarFilme();
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
	public SelecionarFilme() {
		ArrayList<Filme> filmesLista = filmeDAO.mostraFilme();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelecionarFilme.class.getResource("/Images/0609b1d7-4a7d-41be-bd18-081ecb35eb9e.png")));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1139, 604);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnFilme1 = new JButton("");
		btnFilme1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // fecha tela atual
				SelecionarSala1 sl1 = new SelecionarSala1();
				sl1.setExtendedState(JFrame.MAXIMIZED_BOTH);
				sl1.setVisible(true);
			}
		});
		contentPane.setLayout(new MigLayout("", "[36.00,grow][200.00px,grow][36.00px][36.00px][252.00px,grow][36.00px][36.00][252px,grow][36.00,grow]", "[27.00,grow][][54.00px][36.00][337.00px,grow][][49.00,grow]"));
		Main.JMenu(contentPane);
		
		
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // fecha tela atual
				JFrameMain Jmain = new JFrameMain();
				Jmain.setExtendedState(JFrame.MAXIMIZED_BOTH);
				Jmain.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 12));
		btnNewButton.setBackground(Color.WHITE);
		contentPane.add(btnNewButton, "cell 0 0,alignx left,aligny top");
		
		
		
		JLabel Filme1_1 = new JLabel(filmesLista.get(0).getNomeFilme());
		Filme1_1.setHorizontalAlignment(SwingConstants.LEFT);
		Filme1_1.setForeground(Color.WHITE);
		Filme1_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		contentPane.add(Filme1_1, "cell 1 2,alignx center,aligny center");
		
		JLabel Filme1_2 = new JLabel(filmesLista.get(1).getNomeFilme());
		Filme1_2.setHorizontalAlignment(SwingConstants.LEFT);
		Filme1_2.setForeground(Color.WHITE);
		Filme1_2.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		contentPane.add(Filme1_2, "cell 7 2,alignx center");


		
		btnFilme1.setBackground(Color.WHITE);
		btnFilme1.setIcon(new ImageIcon(SelecionarFilme.class.getResource("/Images/filme1_resized.jpeg")));
		contentPane.add(btnFilme1, "cell 1 4,grow");
		
		JButton btnFilme1_1 = new JButton("");
		btnFilme1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // fecha tela atual
				SelecionarSala2 sl2 = new SelecionarSala2();
				sl2.setExtendedState(JFrame.MAXIMIZED_BOTH);
				sl2.setVisible(true);
			}
		});
		btnFilme1_1.setBackground(Color.WHITE);
		btnFilme1_1.setIcon(new ImageIcon(SelecionarFilme.class.getResource("/Images/filme2_resized.jpeg")));
		contentPane.add(btnFilme1_1, "cell 4 4,grow");
		
		JButton btnFilme1_1_1 = new JButton("");
		btnFilme1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // fecha tela atual
				SelecionarSala3 sl3 = new SelecionarSala3();
				sl3.setExtendedState(JFrame.MAXIMIZED_BOTH);
				sl3.setVisible(true);
			}
		});
		btnFilme1_1_1.setBackground(Color.WHITE);
		btnFilme1_1_1.setIcon(new ImageIcon(SelecionarFilme.class.getResource("/Images/filmes_16093_01_resized.png")));
		contentPane.add(btnFilme1_1_1, "cell 7 4,grow");
		
		JLabel Filme1 = new JLabel(filmesLista.get(2).getNomeFilme());
		Filme1.setHorizontalAlignment(SwingConstants.LEFT);
		Filme1.setForeground(new Color(255, 255, 255));
		Filme1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		contentPane.add(Filme1, "flowx,cell 4 2,alignx center");
		
		JLabel lblNewLabel = new JLabel("Selecione um Filme");
		lblNewLabel.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 46));
		lblNewLabel.setForeground(Color.WHITE);
		contentPane.add(lblNewLabel, "cell 2 0 5 3,alignx center,aligny center");
		
		
	}
}
