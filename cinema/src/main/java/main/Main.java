package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;

import controller.FuncionarioDAO;
import model.Funcionario;
import model.RoundedPopopMenu;
import model.SessaoFuncionario;
import view.JFrameMain;
import view.Filmes.SelecionarFilme;

public class Main {

	private static FuncionarioDAO funcionarioDAO = FuncionarioDAO.getInstancia();

	public static void main(String[] args) {

		JFrameMain JMain = new JFrameMain();
		JMain.main(args);
	}

	public static void JMenu(JPanel contentPane) {

		JLabel imgLogin = new JLabel("");
		contentPane.add(imgLogin, "cell 8 0,alignx right,aligny top");

		ImageIcon imageIcon1 = new ImageIcon(SelecionarFilme.class.getResource("/Images/perfil.png"));
		imgLogin.setIcon(imageIcon1);

		Image img = imageIcon1.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon imageResized = new ImageIcon(img);
		imgLogin.setIcon(imageResized);
		imgLogin.setSize(80, 80);

		JPopupMenu popupMenu = new RoundedPopopMenu();

		String nomeUsuario = SessaoFuncionario.getFuncionarioLogado().getNome();

		StringBuilder cpfFormatado = new StringBuilder();
		String numeros = String.valueOf(SessaoFuncionario.getFuncionarioLogado().getCpf());
		cpfFormatado.append(numeros.substring(0, 3));
		cpfFormatado.append(".");
		cpfFormatado.append(numeros.substring(3, 6));
		cpfFormatado.append(".");
		cpfFormatado.append(numeros.substring(6, 9));
		cpfFormatado.append("-");
		cpfFormatado.append(numeros.substring(9, 11));

		cpfFormatado.toString();
		String cpfUsuario = String.valueOf(cpfFormatado);
		Double valorVendas = funcionarioDAO
				.pegarValorVendasFuncionario(funcionarioDAO.getFuncionarioCPF(SessaoFuncionario.getFuncionarioLogado().getCpf()));

		JLabel labelNome = new JLabel("Nome: " + nomeUsuario);
		JLabel labelCPF = new JLabel("CPF: " + cpfUsuario);
		JLabel labelVendas = new JLabel("Valor total de vendas: R$" + valorVendas.toString());

		Font labelFont = new Font("Yu Gothic UI Light", Font.BOLD, 20);

		Color labelColor = new Color(50, 50, 50);
		Color verde = new Color(0, 128, 0); // R:0, G:128, B:0
		Color roxo = new Color(128, 0, 128); // R:128, G:0, B:128
		Color azul = new Color(0, 0, 255); // R:0, G:0, B:255

		int padding = 10;

		Border emptyBorder = BorderFactory.createEmptyBorder(padding, padding, padding, padding);

		popupMenu.setBorder(emptyBorder);

		labelNome.setFont(labelFont);
		labelCPF.setFont(labelFont);
		labelVendas.setFont(labelFont);
		labelNome.setForeground(roxo);
		labelCPF.setForeground(azul);
		labelVendas.setForeground(verde);
		labelNome.setPreferredSize(new Dimension(300, 100));
		labelCPF.setPreferredSize(new Dimension(300, 100));
		labelVendas.setPreferredSize(new Dimension(300, 100));

		popupMenu.add(new JLabel("Olá funcionário!"));
		popupMenu.addSeparator();
		popupMenu.add(labelNome);
		popupMenu.add(labelCPF);
		popupMenu.add(labelVendas);

		for (Component menuItem : popupMenu.getComponents()) {
			if (menuItem instanceof JLabel) {
				((JLabel) menuItem).setFont(labelFont);
				((JLabel) menuItem).setForeground(labelColor);
			}
		}

		popupMenu.setBackground(Color.WHITE);

		imgLogin.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				popupMenu.show(imgLogin, 0, imgLogin.getHeight());
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				popupMenu.setVisible(false);
			}
		});
	}

	public static void JMenuAssentos(JPanel contentPane) {

		JLabel imgLogin = new JLabel("");
		contentPane.add(imgLogin, "cell 14 0,alignx right,aligny top");

		ImageIcon imageIcon1 = new ImageIcon(SelecionarFilme.class.getResource("/Images/perfil.png"));
		imgLogin.setIcon(imageIcon1);

		Image img = imageIcon1.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon imageResized = new ImageIcon(img);
		imgLogin.setIcon(imageResized);
		imgLogin.setSize(80, 80);

		JPopupMenu popupMenu = new RoundedPopopMenu();

		String nomeUsuario = SessaoFuncionario.getFuncionarioLogado().getNome();

		StringBuilder cpfFormatado = new StringBuilder();
		String numeros = String.valueOf(SessaoFuncionario.getFuncionarioLogado().getCpf());
		cpfFormatado.append(numeros.substring(0, 3));
		cpfFormatado.append(".");
		cpfFormatado.append(numeros.substring(3, 6));
		cpfFormatado.append(".");
		cpfFormatado.append(numeros.substring(6, 9));
		cpfFormatado.append("-");
		cpfFormatado.append(numeros.substring(9, 11));

		cpfFormatado.toString();
		String cpfUsuario = String.valueOf(cpfFormatado);
		Double valorVendas = funcionarioDAO
				.pegarValorVendasFuncionario(funcionarioDAO.getFuncionarioCPF(SessaoFuncionario.getFuncionarioLogado().getCpf()));

		JLabel labelNome = new JLabel("Nome: " + nomeUsuario);
		JLabel labelCPF = new JLabel("CPF: " + cpfUsuario);
		JLabel labelVendas = new JLabel("Valor total de vendas: R$" + valorVendas.toString());

		Font labelFont = new Font("Yu Gothic UI Light", Font.BOLD, 20);

		Color labelColor = new Color(50, 50, 50);
		Color verde = new Color(0, 128, 0); // R:0, G:128, B:0
		Color roxo = new Color(128, 0, 128); // R:128, G:0, B:128
		Color azul = new Color(0, 0, 255); // R:0, G:0, B:255

		int padding = 10;

		Border emptyBorder = BorderFactory.createEmptyBorder(padding, padding, padding, padding);

		popupMenu.setBorder(emptyBorder);

		labelNome.setFont(labelFont);
		labelCPF.setFont(labelFont);
		labelVendas.setFont(labelFont);
		labelNome.setForeground(roxo);
		labelCPF.setForeground(azul);
		labelVendas.setForeground(verde);
		labelNome.setPreferredSize(new Dimension(300, 100));
		labelCPF.setPreferredSize(new Dimension(300, 100));
		labelVendas.setPreferredSize(new Dimension(300, 100));

		popupMenu.add(new JLabel("Olá funcionário!"));
		popupMenu.addSeparator();
		popupMenu.add(labelNome);
		popupMenu.add(labelCPF);
		popupMenu.add(labelVendas);

		for (Component menuItem : popupMenu.getComponents()) {
			if (menuItem instanceof JLabel) {
				((JLabel) menuItem).setFont(labelFont);
				((JLabel) menuItem).setForeground(labelColor);
			}
		}

		popupMenu.setBackground(Color.WHITE);

		imgLogin.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				popupMenu.show(imgLogin, 0, imgLogin.getHeight());
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				popupMenu.setVisible(false);
			}
		});
	}

}
