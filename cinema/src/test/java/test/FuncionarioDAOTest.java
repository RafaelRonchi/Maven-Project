package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import controller.FuncionarioDAO;
import model.Funcionario;

public class FuncionarioDAOTest {
	@Test 
	private void testMetodoInserirFuncionario() {
		// TODO Auto-generated method stub
		Funcionario funcio = new Funcionario();
		funcio.setNome("teste");
		funcio.setCpf((long) 1111111);
		funcio.setVendasDouble(100.0);
		
		FuncionarioDAO dao = FuncionarioDAO.getInstancia();
		
		///Mudar declaração da interface para retorrnar um objeto
		//Funcionario fInserir = dao.inserir(funcio);
		Boolean fInserir = dao.inserir(funcio);
		
		assertNotNull(fInserir);
		assertEquals(true, fInserir);
	}
}
