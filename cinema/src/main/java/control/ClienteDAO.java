package control;

import java.sql.Connection;
import java.util.ArrayList;

import Interfaces.ICliente;
import conexao.ConexaoMySql;
import modelo.Funcionario;
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

    
    

    public boolean inserir(Cliente u, String sala, Integer cow, Integer col) {
    	
    }

    public boolean remover(Cliente usua, Integer i, Integer j, Integer salaN) {
               
    }

    public boolean alterar(Cliente novoUsuario, Integer i, Integer j, Integer salaN) {
                  
    
    }

    public static Cliente listarUsuarios(Integer i, Integer j, Integer salaN) {
    	
        
    }
}
