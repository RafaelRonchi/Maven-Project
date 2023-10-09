package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Interfaces.IAssento;
import conexao.ConexaoMySql;
import modelo.Assento;
import modelo.Cliente;
import modelo.Sala;

public class AssentoDAO implements IAssento {
	private static AssentoDAO instancia;
	private static Connection conexao;
	private static ArrayList<Assento> assento = new ArrayList<>();

	private AssentoDAO() {
		conexao = ConexaoMySql.getConexao();
	}

	public static AssentoDAO getInstancia() {
		if (instancia == null) {
			instancia = new AssentoDAO();
			assento = new ArrayList<Assento>();
		}
		return instancia;
	}

	@Override
	public Assento cadastrarClienteNoAssento(Assento a, Cliente c) {
		
		if(listarClienteCadastroNoAssento(a) != null) return null;

		String insertSQLAssento = "INSERT INTO ASSENTO (nome_sala, row, col, sala_idsala, cliente_idcliente) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement psAssento = null;

		try {
			psAssento = conexao.prepareStatement(insertSQLAssento);
			psAssento.setString(1, a.getSala().getNome());
			psAssento.setInt(2, a.getRow());
			psAssento.setInt(3, a.getCol());

			Sala salaMetodo = pegarSala(a.getSala());
			if (salaMetodo == null) return null;
			
			a.setSala(salaMetodo);
			psAssento.setInt(4, a.getSala().getSalaId());

			Cliente clienteMetodo = cadastrarCliente(c);
			if (clienteMetodo == null) return null;
			
			a.setCliente(clienteMetodo);
			psAssento.setInt(5, a.getCliente().getClienteId());

			psAssento.execute();
			psAssento.close();
			return a;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Assento listarClienteCadastroNoAssento(Assento a) {
		String selectSQL = "SELECT C.cliente_nome, C.cliente_cpf, C.cliente_meia_entrada " + "FROM ASSENTO A "
				+ "INNER JOIN CLIENTE C ON A.cliente_idcliente = C.idcliente "
				+ "INNER JOIN SALA S ON A.sala_idsala = S.idsala "
				+ "WHERE A.row = ? AND A.col = ? AND A.nome_sala = ?";

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = conexao.prepareStatement(selectSQL);
			preparedStatement.setInt(1, a.getRow());
			preparedStatement.setInt(2, a.getCol());
			preparedStatement.setString(3, a.getSala().getNome());

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				// Preencha os dados do cliente no assento
				Cliente cliente = new Cliente();
				cliente.setNome(resultSet.getString("cliente_nome"));
				cliente.setCpf(
					Long.parseLong(	resultSet.getString("cliente_cpf"))
					);
				cliente.setMeiaEntrada(resultSet.getBoolean("cliente_meia_entrada"));

				
				Sala sala = new Sala();
				sala.setNome(a.getSala().getNome()); 

				a.setCliente(cliente);
				a.setSala(sala);

				return a;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Cliente cadastrarCliente(Cliente c) {
		// Cadatrar o cliente
		int clienteId = -1;
		String insertSQLCliente = "INSERT INTO CLIENTE (cliente_nome, cliente_cpf, cliente_meia_entrada) VALUES (?, ?, ?)";
		try {
			conexao.prepareStatement(insertSQLCliente);
			PreparedStatement psCliente = conexao.prepareStatement(insertSQLCliente);
			psCliente.setString(1, c.getNome());
			psCliente.setString(2, c.getCpf().toString());
			psCliente.setBoolean(3, c.getMeiaEntrada());
			psCliente.execute();

			// Recupere o ID gerado para o cliente
			ResultSet generatedKeys = psCliente.getGeneratedKeys();
			if (generatedKeys.next()) {
				clienteId = generatedKeys.getInt(1);
				c.setClienteId(clienteId);
				return c;
			}

			psCliente.close();
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	public Sala pegarSala(Sala s) {
		String getSqlSala = "SELECT * FROM SALA WHERE nome_sala = ?";
		PreparedStatement psSala = null;
		ResultSet rs = null;

		try {
			psSala = conexao.prepareStatement(getSqlSala);
			psSala.setString(1, s.getNome());
			rs = psSala.executeQuery();

			if (rs.next()) {

				s.setSalaId(rs.getInt("idsala"));
				return s;
			}
			return null;
		} catch (SQLException e) {
			// exceção
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (psSala != null) {
					psSala.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

}
