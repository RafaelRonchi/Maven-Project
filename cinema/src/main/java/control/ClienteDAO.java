package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Interfaces.ICliente;
import conexao.ConexaoMySql;
import modelo.Cliente;

public class ClienteDAO implements ICliente {
	private static ClienteDAO instancia;
	private static ArrayList<Cliente> clientes = new ArrayList<>();

	private Connection conexao;

	// Construtor privado para impedir a criação de instâncias diretamente
	private ClienteDAO() {
		conexao = ConexaoMySql.getConexao();
		// Restante da lógica de inicialização
	}

	// Método estático para obter a instância única do FuncionarioDAO
	public static ClienteDAO getInstancia() {
		if (instancia == null) {
			instancia = new ClienteDAO();
			clientes = new ArrayList<>();
		}

		return instancia;
	}
@Override
	public boolean inserir(Cliente u, String sala, Integer row, Integer col) {

		String comando = "Select * from vendas inner join assento on assento_idassento = assento.idassento where col=?, row=?;  ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conexao.prepareStatement(comando);
			ps.setInt(1, Integer.valueOf(row));
			ps.setInt(2, Integer.valueOf(col));
			rs = ps.executeQuery();

			if (rs != null) {
				String insertSQL = "INSERT INTO FUNCIONARIO cliente_nome = ?, cliente_cpf = ?, cliente_meia_entrada = ? VALUES (?, ?, ?);";

				try {
					ps = conexao.prepareStatement(insertSQL);
					ps.setString(1, String.valueOf(u.getNome()));
					ps.setBoolean(2, Boolean.valueOf(u.getMeiaEntrada()));
					ps.setLong(3, Long.valueOf(u.getCpf()));

					rs = ps.executeQuery();
					ps.close();
					rs.close();

				} catch (Exception e) {
					// TODO: handle exception
				}

				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}
@Override
	public boolean remover(Cliente u, String sala, Integer row, Integer col) {

		String comando = "Select * from vendas inner join assento on assento_idassento = assento.idassento where col=?, row=?;  ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conexao.prepareStatement(comando);
			ps.setInt(1, Integer.valueOf(row));
			ps.setInt(2, Integer.valueOf(col));
			rs = ps.executeQuery();

			if (rs != null) {
				String deleteSQL = "DELETE FROM cliente WHERE cliente_cpf = ?";

				try {
					ps = conexao.prepareStatement(deleteSQL);
					ps.setString(1, String.valueOf(u.getNome()));
					ps.setBoolean(2, Boolean.valueOf(u.getMeiaEntrada()));
					ps.setLong(3, Long.valueOf(u.getCpf()));

					rs = ps.executeQuery();
					ps.close();
					rs.close();

				} catch (Exception e) {
					// TODO: handle exception
				}

				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}
@Override
	public boolean alterar(Cliente u, String sala, Integer row, Integer col) {

		// String updateSQL = "Update cliente SET cliente_nome = ?, cliente_cpf = ?,
		// cliente_meia_entrada = ? WHERE cliente_cpf = ?";

		String comando = "Select * from vendas inner join assento on assento_idassento = assento.idassento where col=?, row=?;  ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conexao.prepareStatement(comando);
			ps.setInt(1, Integer.valueOf(row));
			ps.setInt(2, Integer.valueOf(col));
			rs = ps.executeQuery();

			if (rs != null) {
				String updateSQL = "Update cliente  SET  cliente_nome = ?, cliente_meia_entrada = ?  WHERE cliente_cpf = ?";

				try {
					ps = conexao.prepareStatement(updateSQL);
					ps.setString(1, String.valueOf(u.getNome()));

					ps.setBoolean(2, Boolean.valueOf(u.getMeiaEntrada()));

					ps.setLong(3, Long.valueOf(u.getCpf()));

					rs = ps.executeQuery();
					ps.close();
					rs.close();

				} catch (Exception e) {
					// TODO: handle exception
				}

				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

	public ArrayList<Cliente> listarUsuarios(Integer row, Integer col, String salaN) {
		// Cliente
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		String comando = "Select * from vendas inner join assento on assento_idassento = assento.idassento where col=?, row=?;  ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conexao.prepareStatement(comando);
			rs = ps.executeQuery();

			while (rs.next()) {
				long cpf = rs.getLong("cliente_cpf");
				String nome = rs.getString("cliente_nome");
				Boolean meia = rs.getBoolean("cliente_meia_entrada");
				Cliente cliente = new Cliente(cpf, nome, meia);
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return clientes;
	}

}
