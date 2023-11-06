package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controller.FuncionarioDAO;
import model.Funcionario;

public class FuncionarioDAOTest {
	private FuncionarioDAO dao = FuncionarioDAO.getInstancia();;

	@Test
	public void testMetodoInserirFuncionario() {
		Funcionario funcionaro = new Funcionario(Long.parseLong("12312312312"), "teste", 0.0, true);

		Boolean fInserir = dao.inserir(funcionaro);
		Boolean fRemover = dao.remover(funcionaro);
		assertNotNull(fInserir);
		assertEquals(true, fInserir);
	}
	
	@Test
	public void testMetodoGetFuncionarioCPF() {
		Funcionario funcionaro = new Funcionario(Long.parseLong("12312312312"), "teste", 0.0, true);
		Boolean fInserir = dao.inserir(funcionaro);
		Funcionario fCPF = dao.getFuncionarioCPF(funcionaro.getCpf());
		Boolean fRemover = dao.remover(funcionaro);
		
		assertNotNull(fCPF);
		assertEquals(false, fCPF.equals(null));
	}
	
	@Test
	public void testMetodoGetFuncionarios() {
		ArrayList<Funcionario> listaFuncionarios = dao.listarFuncionario();
		assertNotNull(listaFuncionarios);
		assertEquals(false, listaFuncionarios.isEmpty());
	}

	@Test
	public void testMetodoAlterarFuncionario() {
		Funcionario funcionaro = new Funcionario(Long.parseLong("12312312312"), "teste", 0.0, true);
		Boolean fInserir = dao.inserir(funcionaro);
		funcionaro.setNome("tester");
		
		Boolean fAlterar = dao.alterar(funcionaro);
		Boolean fRemover = dao.remover(funcionaro);
		assertNotNull(fAlterar);
		assertEquals(true, fAlterar);
	}

	@Test
	public void testMetodoVerificarLogin() {
		Funcionario funcionaro = new Funcionario(Long.parseLong("12312312312"), "teste", 0.0, true);
		Boolean fInserir = dao.inserir(funcionaro);

		Boolean fLogin = dao.verificarLogin(funcionaro);
		Boolean fRemover = dao.remover(funcionaro);
		assertNotNull(fLogin);
		assertEquals(true, fLogin);
	}

	@Test
	public void testMetodoVerificarAdmin() {
		Funcionario funcionaro = new Funcionario(Long.parseLong("12312312312"), "teste", 0.0, true);
		Boolean fInserir = dao.inserir(funcionaro);

		Funcionario fLoginAdmin = dao.verificarFuncionarioAdmin(funcionaro);
		Boolean fRemover = dao.remover(funcionaro);
		assertNotNull(fLoginAdmin);
		assertEquals(false, fLoginAdmin.equals(null));
	}

	@Test
	public void testMetodoRemoverFuncionario() {
		Funcionario funcionaro = new Funcionario(Long.parseLong("12312312312"), "teste", 0.0, true);
		dao.inserir(funcionaro);

		Boolean fRemover = dao.remover(funcionaro);
		assertNotNull(fRemover); 
		assertEquals(true, fRemover);
	}
}
