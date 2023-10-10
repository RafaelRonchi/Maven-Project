package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Interfaces.IVenda;
import conexao.ConexaoMySql;
import main.Main;
import modelo.Assento;
import modelo.Cliente;
import modelo.Funcionario;
import modelo.Venda;

public class VendaDAO implements IVenda{
	private static VendaDAO instancia;
	private static Connection conexao;
	private static ArrayList<Venda> vendas = new ArrayList<>();
	
	private VendaDAO() {
		conexao = ConexaoMySql.getConexao();
	}
	public static VendaDAO getInstancia() {
		if(instancia == null) {
			instancia = new VendaDAO();
			vendas = new ArrayList<Venda>();
		}
		return instancia;
	}
	@Override
	public Venda cadastrarVenda(Venda v) {
	    String insertSQL = "INSERT INTO VENDA (venda_valor, funcionario_id_funcionario, cliente_idcliente, assento_idassento)"
	            + " VALUES (?, ?, ?, ?)";
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        ps = conexao.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS); 
	        ps.setDouble(1, v.getVendaValor());
	        ps.setInt(2, v.getFuncionario().getId());
	        ps.setInt(3, v.getCliente().getClienteId());
	        ps.setInt(4, v.getAssento().getAssentoId());

	        int rowsAffected = ps.executeUpdate(); 

	        if (rowsAffected > 0) {
	            rs = ps.getGeneratedKeys();
	            if (rs.next()) {
	                v.setIdVenda(rs.getInt(1)); 
	            }
	            return v;
	        }
	    } catch (Exception e) {
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

	    return null;
	}


	@Override
	public List<Venda> pegarVendas() {
		String insertSQL = "SELECT * FROM VENDA";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Venda> vendas = null;
		
		
		try {
			ps = conexao.prepareStatement(insertSQL);

			rs = ps.executeQuery();
			while(rs.next()) {
				Venda venda = new Venda();
				venda.setAssento(new Assento(rs.getInt("assento_idassento")));
				venda.setFuncionario(new Funcionario(rs.getInt("funcionario_id_funcionario")));
				venda.setCliente(new Cliente(rs.getInt("cliente_idcliente")));
				venda.setIdVenda(rs.getInt("idvenda"));
				venda.setVendaValor(rs.getDouble("venda_valor"));
				
				vendas.add(venda);
			}
			
			return vendas;
		} catch (Exception e) {
			// TODO: handle exception
		}  finally {
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
		
		return null;
	}
}
