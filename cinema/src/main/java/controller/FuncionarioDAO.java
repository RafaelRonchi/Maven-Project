package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.PreparableStatement;

import Interfaces.IFuncionario;
import connection.ConexaoMySql;
import main.Main;
import model.Funcionario;
import model.SessaoFuncionario;

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
		Funcionario funcionarioExist = getFuncionarioCPF(f.getCpf());
		
		if(funcionarioExist != null) {
			return false;
		}

		String insertSQL = "INSERT INTO FUNCIONARIO (cpf_funcionario, nome_funcionario, admin_funcionario) VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		
		try {			
			ps = conexao.prepareStatement(insertSQL);
			ps.setString( 1, String.valueOf(f.getCpf()));
			ps.setString( 2, f.getNome());
			ps.setBoolean(3, f.getAdmin());
			
			ps.execute();
			ps.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
	        try {
	            if (ps != null) {
	                ps.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		
		

	}
	@Override
	public boolean remover(Funcionario f) {
	    String deleteSQL = "DELETE FROM FUNCIONARIO WHERE cpf_funcionario = ? and nome_funcionario = ?";
	    PreparedStatement ps = null;
	    
	    try {
			ps = conexao.prepareStatement(deleteSQL);
			ps.setString(1, String.valueOf(f.getCpf()));
			ps.setString(2, f.getNome());
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
		} finally {
	        try {
	      
	            if (ps != null) {
	                ps.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
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
	    finally {
	        try {
	            
	            if (ps != null) {
	                ps.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	@Override
	public boolean verificarLogin(Funcionario funcionario) {
	    String selectSQL = "SELECT * FROM FUNCIONARIO WHERE cpf_funcionario = ? and nome_funcionario = ?";
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        ps = conexao.prepareStatement(selectSQL);
	        ps.setLong(1, funcionario.getCpf());
	        ps.setString(2, funcionario.getNome());
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            // Cria um novo objeto Funcionario com os dados do resultado da consulta
	            Funcionario funcionarioEncontrado = new Funcionario();
	            funcionarioEncontrado.setId(rs.getInt("id_funcionario"));
	            funcionarioEncontrado.setCpf(Long.parseLong( rs.getString("cpf_funcionario")));
	            funcionarioEncontrado.setNome(rs.getString("nome_funcionario"));
	            
	            SessaoFuncionario.setFuncionarioLogado(funcionarioEncontrado);
	            
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
	    
	    String selectSQL = "SELECT cpf_funcionario, nome_funcionario, admin_funcionario FROM FUNCIONARIO";
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
	        ps = conexao.prepareStatement(selectSQL);
	        rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            Long cpf = rs.getLong("cpf_funcionario");
	            String nome = rs.getString("nome_funcionario");
	            Boolean isAdmin = rs.getBoolean("admin_funcionario");
	            Funcionario funcionario = new Funcionario(cpf, nome, isAdmin);
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
		String selectSQL = "SELECT * FROM FUNCIONARIO WHERE cpf_funcionario = ? and nome_funcionario = ?";
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        ps = conexao.prepareStatement(selectSQL);
	        ps.setLong(1, f.getCpf());
	        ps.setString(2, f.getNome());
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            // Cria um novo objeto Funcionario com os dados do resultado da consulta
	            Funcionario funcionarioEncontrado = new Funcionario();
	            funcionarioEncontrado.setCpf(rs.getLong("cpf_funcionario"));
	            funcionarioEncontrado.setNome(rs.getString("nome_funcionario"));
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
	public Funcionario getFuncionarioCPF(Long cpf) {
		String query = "SELECT * FROM FUNCIONARIO WHERE cpf_funcionario = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
	         ps = conexao.prepareStatement(query);
	        ps.setString(1, cpf.toString());
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            Funcionario funcionarioEncontrado = new Funcionario();
	            funcionarioEncontrado.setId(rs.getInt("id_funcionario"));
	            funcionarioEncontrado.setCpf(Long.parseLong(rs.getString("cpf_funcionario")));
	            funcionarioEncontrado.setNome(rs.getString("nome_funcionario"));
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
	
	public Double pegarValorVendasFuncionario(Funcionario funcionario) {
		String query = "SELECT venda_valor FROM VENDA WHERE funcionario_id_funcionario = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Double valorVendas = 0.0;
		try {
	        ps = conexao.prepareStatement(query);
	        ps.setInt(1, funcionario.getId());
	        rs = ps.executeQuery();

	        while(rs.next()) {
	        	valorVendas += rs.getDouble("venda_valor");
	        }
	        return valorVendas;

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
	    
		return valorVendas;
		
	}


}
