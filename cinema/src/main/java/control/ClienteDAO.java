package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    
    

    public boolean inserir(Cliente u, String sala, Integer row, Integer col) {
    	
    	

    	String comando ="Select * from vendas inner join assento on assento_idassento = assento.idassento where col=?, row=?  ";
    			PreparedStatement ps=null;
    			ResultSet rs = null;
    		try {
				ps= conexao.prepareStatement(comando);
				ps.setInt(1, Integer.valueOf (row));							
				ps.setInt(2, Integer.valueOf (col));
				rs = ps.executeQuery();
			
				if(rs!=null){
					
					
				}
				
				
				
				
				//ps.execute();
				//ps.close();
			} catch (Exception e) {
				// TODO: handle exception
			}	
    		
    	return true;
    }

    public boolean remover(Cliente usua, Integer i, Integer j, Integer salaN) {
      
    	
    	
    	
    	return true;       
    }

    public boolean alterar(Cliente novoUsuario, Integer i, Integer j, Integer salaN) {
         return true;
    }

    public static Cliente listarUsuarios(Integer i, Integer j, Integer salaN) {
    	Cliente
    	
    	return cliente
        
    }
}
