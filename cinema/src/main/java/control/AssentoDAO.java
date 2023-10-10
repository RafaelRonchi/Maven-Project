package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Interfaces.IAssento;
import conexao.ConexaoMySql;
import main.Main;
import modelo.Assento;
import modelo.Cliente;
import modelo.Funcionario;

import java.sql.Statement;

import modelo.Sala;
import modelo.Venda;

public class AssentoDAO implements IAssento {
	private static AssentoDAO instancia;
	private static Connection conexao;
	private static ArrayList<Assento> assento = new ArrayList<>();
	private VendaDAO vendaDAO = VendaDAO.getInstancia();
	private Funcionario FuncionarioLogado = Main.getFuncionarioLogado();
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
	    if (listarClienteCadastroNoAssento(a) != null) return null;

	    String insertSQLAssento = "INSERT INTO ASSENTO (`row`, col, sala_idsala, cliente_idcliente) VALUES (?, ?, ?, ?)";
	    PreparedStatement psAssento = null;

	    try {
	        psAssento = conexao.prepareStatement(insertSQLAssento, Statement.RETURN_GENERATED_KEYS);
	        psAssento.setInt(1, a.getRow());
	        psAssento.setInt(2, a.getCol());

	        Sala salaMetodo = pegarSala(a.getSala());
	        if (salaMetodo == null) return null;

	        a.setSala(salaMetodo);
	        psAssento.setInt(3, a.getSala().getSalaId());

	        Cliente clienteMetodo = cadastrarCliente(c);
	        if (clienteMetodo == null) return null;

	        a.setCliente(clienteMetodo);
	        psAssento.setInt(4, a.getCliente().getClienteId());

	        psAssento.execute();

	        // Obtenha as chaves geradas
	        ResultSet generatedKeys = psAssento.getGeneratedKeys();
	        if (generatedKeys.next()) {
	        	a.setAssentoId(generatedKeys.getInt(1));
	        }
	        
	        Double valor = c.getMeiaEntrada() ? 10.0 : 20.0;
	        Venda venda = new Venda(valor, FuncionarioLogado, c, a);
	        vendaDAO.cadastrarVenda(venda);
	        
	        psAssento.close();
	        return a;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	    finally {
	        try {
	         
	            if (psAssento != null) {
	            	psAssento.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	}

	@Override
	public Assento listarClienteCadastroNoAssento(Assento a) {
		String selectSQL = "SELECT C.cliente_nome, C.cliente_cpf, C.cliente_meia_entrada " +
				"FROM ASSENTO A " +
				"INNER JOIN CLIENTE C ON A.cliente_idcliente = C.idcliente " +
				"INNER JOIN SALA S ON A.sala_idsala = S.idsala " +
				"WHERE A.row = ? AND A.col = ? AND S.nome_sala = ?";
;

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
	
	@Override
	public Cliente cadastrarCliente(Cliente c) {

		// Cadatrar o cliente
		if(pegarClientePorCPF(c.getCpf()) != null) return null;
		
		int clienteId = -1;
		String insertSQLCliente = "INSERT INTO CLIENTE (cliente_nome, cliente_cpf, cliente_meia_entrada) VALUES (?, ?, ?)";
		try {
			conexao.prepareStatement(insertSQLCliente);
			PreparedStatement psCliente = conexao.prepareStatement(insertSQLCliente, Statement.RETURN_GENERATED_KEYS);
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
		finally {
	        // Já fechou
	    }

	}
	
	@Override
	public Assento removerClienteDoAssento(Assento a) {		
	    String deleteSQLAssento = "DELETE A FROM ASSENTO A " +
                "INNER JOIN SALA S ON A.sala_idsala = S.idsala " +
                "WHERE A.row = ? AND A.col = ? AND S.nome_sala = ? ";
	    PreparedStatement psAssento = null;

	    try {
	    	psAssento = conexao.prepareStatement(deleteSQLAssento);
	        psAssento.setInt(1, a.getRow());
	        psAssento.setInt(2, a.getCol());
	        psAssento.setString(3, a.getSala().getNome());
	        
	        psAssento.executeUpdate();
	        
	        psAssento.close();
	        return a;
	    } catch (Exception e) {
	        e.printStackTrace();
	      
	    }
	    finally {
			try {
				
				if (psAssento != null) {
					psAssento.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public Boolean pegarEstadoDoAssento(Assento a) {		
		if (listarClienteCadastroNoAssento(a) == null) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
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
	
	@Override
	public boolean[][] pegarAssentosOcupados(Sala sala) {
	    int row = 5;
	    int col = 6;
	    
	    boolean[][] assentosOcupados = new boolean[row][col];
	    Sala salaExist = pegarSala(sala);
	    
	    String selectSQL = "SELECT `row`, col FROM ASSENTO WHERE sala_idsala = ?";
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
	    try {
	        preparedStatement = conexao.prepareStatement(selectSQL);
	        preparedStatement.setInt(1, salaExist.getSalaId());
	        resultSet = preparedStatement.executeQuery();
	        
	        while (resultSet.next()) {
	            int rowRS = resultSet.getInt("row");
	            int colRS = resultSet.getInt("col");
	            
	            assentosOcupados[rowRS][colRS] = true;
	        }
	        
	        return assentosOcupados;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return assentosOcupados;
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

	@Override
	public Cliente alterarCliente(Cliente clienteAtualizado) {
		if(pegarClientePorCPF(clienteAtualizado.getCpf()) != null) return null;
		
	    String updateSQLCliente = "UPDATE CLIENTE SET cliente_nome = ?, cliente_meia_entrada = ? WHERE idcliente = ?";
	    PreparedStatement psCliente = null;
	    
	    Cliente existCliente = pegarClientePorCPF(clienteAtualizado.getCpf());
	    System.out.println(existCliente.getClienteId());
	    clienteAtualizado.setClienteId(existCliente.getClienteId());
	    try {
	        psCliente = conexao.prepareStatement(updateSQLCliente);
	        psCliente.setString(1, clienteAtualizado.getNome());
	        psCliente.setBoolean(2, clienteAtualizado.getMeiaEntrada());
	        psCliente.setInt(3, clienteAtualizado.getClienteId());

	        int rowsAffected = psCliente.executeUpdate();

	        if (rowsAffected > 0) {
	            return clienteAtualizado;
	        } else {
	            return null; 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        try {
	            if (psCliente != null) {
	                psCliente.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	@Override
	public Cliente pegarClientePorCPF(Long CPF) {
		 String getSQLCliente = "SELECT * FROM CLIENTE WHERE cliente_cpf = ?";
		    PreparedStatement psCliente = null;
		    ResultSet rS = null;
		    
		    try {
		        psCliente = conexao.prepareStatement(getSQLCliente);
		        psCliente.setString(1, CPF.toString());

		        rS = psCliente.executeQuery();

		        if(!rS.next()) return null;
		        Cliente cliente = new Cliente();
		        cliente.setClienteId(rS.getInt("idcliente"));
				cliente.setNome(rS.getString("cliente_nome"));
				cliente.setCpf(Long.parseLong(	rS.getString("cliente_cpf")));
				cliente.setMeiaEntrada(rS.getBoolean("cliente_meia_entrada"));
				return cliente;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return null;
		    } finally {
		        try {
		            if (psCliente != null) {
		                psCliente.close();
		            }
		            if (rS != null) {
		            	rS.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
	}
	
	

	@Override
	public List<Cliente> pegarClientes(){
		String selectSQL = "SELECT * FROM CLIENTE";
		PreparedStatement pS = null;
		ResultSet rS = null;
		List<Cliente> clientes = null;
		try {
			pS = conexao.prepareStatement(selectSQL);
			rS = pS.executeQuery();
			
			if(!rS.next()) return null;
			
			while(rS.next()) {
				clientes.add(new Cliente( rS.getInt("idcliente") ,Long.parseLong(rS.getString("cliente_cpf")), rS.getString("cliente_nome"), rS.getBoolean("cliente_meia_entrada")));
			}
			
			return clientes;
	
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				if (rS != null) {
					rS.close();
				}
				if (pS != null) {
					pS.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return clientes;
	}
	 
	
}
