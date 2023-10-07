package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.PreparableStatement;

import Interfaces.IFuncionario;
import conexao.ConexaoMySql;
import main.Main;
import modelo.Funcionario;

public class FuncionarioDAO implements IFuncionario{
	  private static FuncionarioDAO instancia;
	  private static ArrayList<Funcionario> funcionario = new ArrayList<>();
	  private Connection conexao;

	// Construtor privado para impedir a criação de instâncias diretamente
	    private FuncionarioDAO() {
	        conexao = ConexaoMySql.getConexao();
	        // Restante da lógica de inicialização
	    }
	    
	 // Método estático para obter a instância única do FuncionarioDAO
	    public static FuncionarioDAO getInstancia() {
	        if (instancia == null) {
	            instancia = new FuncionarioDAO();
	            funcionario = new ArrayList<>();
	        }
	        
	        return instancia;
	    }

	@Override
	public boolean inserir(Funcionario f) {
		Funcionario funcionarioExist = getFuncionarioCPF(f.getCpf().toString());
		
		if(funcionarioExist != null) {
			return false;
		}

		String insertSQL = "INSERT INTO FUNCIONARIO (cpf_funcionario, nome_funcionario, funcionario_valor_vendas, admin_funcionario) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = null;
		
		try {			
			ps = conexao.prepareStatement(insertSQL);
			ps.setString( 1, String.valueOf(f.getCpf()));
			double valor = 0.00;
			ps.setString( 2, f.getNome());
			ps.setDouble(3, valor);
			ps.setBoolean(4, f.getAdmin());
			
			ps.execute();
			ps.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
		

	}
	@Override
	public boolean remover(Funcionario f) {
	    String deleteSQL = "DELETE FROM FUNCIONARIO WHERE cpf_funcionario = ?";
	    PreparedStatement ps = null;
	    
	    try {
			ps = conexao.prepareStatement(deleteSQL);
			ps.setString(1, String.valueOf(f.getCpf()));
			
			int rowsAffected = ps.executeUpdate();
	        ps.close();
	        
	        if (rowsAffected > 0) {
	            return true;
	        } else {
	        	return false;
	        }
		} catch (Exception e) {
			 e.printStackTrace();	
			 return false;
		}
	}

	@Override
	public boolean alterar(Funcionario novoFuncionario) {
		String updateSQL = "UPDATE FUNCIONARIO SET nome_funcionario = ?, admin_funcionario = ? WHERE cpf_funcionario = ?";
	    PreparedStatement ps = null;
	    try {            
	        ps = conexao.prepareStatement(updateSQL);
	        ps.setString(1, novoFuncionario.getNome());
	        ps.setBoolean(2, novoFuncionario.getAdmin());
	        ps.setString(3, String.valueOf(novoFuncionario.getCpf()));
	        
	        int rowsAffected = ps.executeUpdate();
	        ps.close();
	        
	        if (rowsAffected > 0) {
	            return true;
	        } else {
	        	return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	@Override
	public boolean verificarLogin(Funcionario funcionario) {
	    String selectSQL = "SELECT * FROM FUNCIONARIO WHERE cpf_funcionario = ? ";
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        ps = conexao.prepareStatement(selectSQL);
	        ps.setLong(1, funcionario.getCpf());
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            // Cria um novo objeto Funcionario com os dados do resultado da consulta
	            Funcionario funcionarioEncontrado = new Funcionario();
	            funcionarioEncontrado.setCpf(rs.getLong("cpf_funcionario"));
	            funcionarioEncontrado.setNome(rs.getString("nome_funcionario"));
	            funcionarioEncontrado.setVendasDouble(rs.getDouble("funcionario_valor_vendas"));
	            
	            Main.setFuncionarioLogado(funcionarioEncontrado);
	            
	            return true;
	        }
	        return false;

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
	    
	    return false; // Retorna null se o login for inválido ou ocorrer uma exceção
	}

	
	@Override
	public ArrayList<Funcionario> listarFuncionario() {
	    ArrayList<Funcionario> lista = new ArrayList<>();
	    
	    String selectSQL = "SELECT cpf_funcionario, nome_funcionario, funcionario_valor_vendas, admin_funcionario FROM FUNCIONARIO";
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
	        ps = conexao.prepareStatement(selectSQL);
	        rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            long cpf = rs.getLong("cpf_funcionario");
	            String nome = rs.getString("nome_funcionario");
	            Double vendas = rs.getDouble("funcionario_valor_vendas");
	            Boolean isAdmin = rs.getBoolean("admin_funcionario");
	            Funcionario funcionario = new Funcionario(cpf, nome, vendas, isAdmin);
	            lista.add(funcionario);
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
	    
	    return lista;
	}

	@Override
	public Funcionario verificarFuncionarioAdmin(Funcionario f) {
		String selectSQL = "SELECT * FROM FUNCIONARIO WHERE cpf_funcionario = ?";
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        ps = conexao.prepareStatement(selectSQL);
	        ps.setLong(1, f.getCpf());
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            // Cria um novo objeto Funcionario com os dados do resultado da consulta
	            Funcionario funcionarioEncontrado = new Funcionario();
	            funcionarioEncontrado.setCpf(rs.getLong("cpf_funcionario"));
	            funcionarioEncontrado.setNome(rs.getString("nome_funcionario"));
	            funcionarioEncontrado.setVendasDouble(rs.getDouble("funcionario_valor_vendas"));
	            funcionarioEncontrado.setAdmin(rs.getBoolean("admin_funcionario"));
	            
	            if(funcionarioEncontrado.getAdmin() == true) {
	            	
	            	return funcionarioEncontrado;
	            }
		        return null;

	        }
	        return null;

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
	    
	    return null; 
	}

	@Override
	public Funcionario getFuncionarioCPF(String cpf) {
		String query = "SELECT * FROM FUNCIONARIO WHERE cpf_funcionario = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
	         ps = conexao.prepareStatement(query);
	        ps.setString(1, cpf);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            Funcionario funcionarioEncontrado = new Funcionario();
	            funcionarioEncontrado.setCpf(rs.getLong("cpf_funcionario"));
	            funcionarioEncontrado.setNome(rs.getString("nome_funcionario"));
	            funcionarioEncontrado.setVendasDouble(rs.getDouble("funcionario_valor_vendas"));
	            funcionarioEncontrado.setAdmin(rs.getBoolean("admin_funcionario"));
	            
	            	
	            	return funcionarioEncontrado;
	        
	        }
	        return null;

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
	    
	    return null; 
	}


}
