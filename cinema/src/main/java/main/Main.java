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

import control.FuncionarioDAO;
import modelo.Funcionario;
import modelo.RoundedPopopMenu;
import view.JFrameMain;
import view.Filmes.SelecionarFilme;

public class Main {

	public static Funcionario FuncionarioLogado ;
	private static FuncionarioDAO funcionarioDAO = FuncionarioDAO.getInstancia();
	
	public static void main(String[] args) {

		JFrameMain JMain = new JFrameMain();
		JMain.main(args);
	}

	public static Funcionario getFuncionarioLogado() {
		return FuncionarioLogado;
	}

	public static void setFuncionarioLogado(Funcionario funcionarioLogado) {
		FuncionarioLogado = funcionarioLogado;
	}
	public static void JMenu(JPanel contentPane) {
		// Profiller
					JLabel imgLogin = new JLabel("");
					contentPane.add(imgLogin, "cell 8 0,alignx right,aligny top");
					
					ImageIcon imageIcon1 = new ImageIcon(SelecionarFilme.class.getResource("/Images/perfil.png"));
					imgLogin.setIcon(imageIcon1);
			
					// Ajusta o tamanho do JLabel para corresponder ao tamanho da imagem redimensionada
					Image img = imageIcon1.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
					ImageIcon imageResized = new ImageIcon(img);
					imgLogin.setIcon(imageResized);
					imgLogin.setSize(80, 80);
			
					// Criação do JPopupMenu
					JPopupMenu popupMenu = new RoundedPopopMenu();
			
					String nomeUsuario = Main.getFuncionarioLogado().getNome();
					
					// CPF mascara
					StringBuilder cpfFormatado = new StringBuilder();
					String numeros = String.valueOf(Main.getFuncionarioLogado().getCpf());
					cpfFormatado.append(numeros.substring(0, 3));
					cpfFormatado.append(".");
					cpfFormatado.append(numeros.substring(3, 6));
					cpfFormatado.append(".");
					cpfFormatado.append(numeros.substring(6, 9));
					cpfFormatado.append("-");
					cpfFormatado.append(numeros.substring(9, 11));

					cpfFormatado.toString();
					String cpfUsuario = String.valueOf(cpfFormatado);
					Double valorVendas = funcionarioDAO.pegarValorVendasFuncionario(funcionarioDAO.getFuncionarioCPF(FuncionarioLogado.getCpf()));
			
					// Criação dos JLabels para exibir as informações
					JLabel labelNome = new JLabel("Nome: " + nomeUsuario);
					JLabel labelCPF = new JLabel("CPF: " + cpfUsuario);
					JLabel labelVendas = new JLabel("Valor total de vendas: R$" + valorVendas.toString());
			
					// Configurações visuais
					Font labelFont = new Font("Yu Gothic UI Light", Font.BOLD, 20);
					
					Color labelColor = new Color(50, 50, 50);
					Color verde = new Color(0, 128, 0); // R:0, G:128, B:0
					Color roxo = new Color(128, 0, 128); // R:128, G:0, B:128
					Color azul = new Color(0, 0, 255); // R:0, G:0, B:255
			
					
					
					
					// Define o espaçamento interno (padding) desejado
					int padding = 10;
			
					// Cria um objeto EmptyBorder com o padding especificado
					Border emptyBorder = BorderFactory.createEmptyBorder(padding, padding, padding, padding);
			
					// Define o border do JPopupMenu com o EmptyBorder
					popupMenu.setBorder(emptyBorder);
					
					// Aplica as configurações visuais aos JLabels
					labelNome.setFont(labelFont);
					labelCPF.setFont(labelFont);
					labelVendas.setFont(labelFont);
					labelNome.setForeground(roxo);
					labelCPF.setForeground(azul);
					labelVendas.setForeground(verde);
					labelNome.setPreferredSize(new Dimension(300, 100));
					labelCPF.setPreferredSize(new Dimension(300, 100));
					labelVendas.setPreferredSize(new Dimension(300, 100));
			
					// Adiciona os JLabels ao JPopupMenu
					popupMenu.add(new JLabel("Olá funcionário!"));
					popupMenu.addSeparator();
					popupMenu.add(labelNome);
					popupMenu.add(labelCPF);
					popupMenu.add(labelVendas);
			
					// Configura a aparência dos itens do menu
					for (Component menuItem : popupMenu.getComponents()) {
					    if (menuItem instanceof JLabel) {
					        ((JLabel) menuItem).setFont(labelFont);
					        ((JLabel) menuItem).setForeground(labelColor);
					    }
					}
			
					// Define a cor de fundo do JPopupMenu
					popupMenu.setBackground(Color.WHITE);
			
					// Listener para exibir o menu ao clicar no ícone
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
