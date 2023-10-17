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
	private void testMetodoInserirFuncionario() {
		Funcionario funcionaro = new Funcionario();
		funcionaro.setNome("teste");
		funcionaro.setCpf(Long.parseLong("12312312312"));
		funcionaro.setVendasDouble(0.0);
		funcionaro.setAdmin(true);

		Boolean fInserir = dao.inserir(funcionaro);
		assertNotNull(fInserir);
		assertEquals(true, fInserir);
	}
	
	@Test
	private void testMetodoInserirGetFuncionarioCPF() {
		Funcionario funcionaro = new Funcionario();
		funcionaro.setNome("teste");
		funcionaro.setCpf(Long.parseLong("12312312312"));
		funcionaro.setVendasDouble(0.0);
		funcionaro.setAdmin(true);

		Funcionario fCPF = dao.getFuncionarioCPF(funcionaro.getCpf());
		assertNotNull(fCPF);
		assertEquals(funcionaro, fCPF);
	}
	
	@Test
	private void testMetodoGetFuncionarios() {
		ArrayList<Funcionario> listaFuncionarios = dao.listarFuncionario();
		assertNotNull(listaFuncionarios);
		assertEquals(false, listaFuncionarios.isEmpty());
	}

	@Test
	private void testMetodoAlterarFuncionario() {
		Funcionario funcionaro = new Funcionario();
		funcionaro.setNome("teste novo nome");
		funcionaro.setCpf(Long.parseLong("12312312312"));
		funcionaro.setVendasDouble(0.0);
		funcionaro.setAdmin(true);

		Boolean fAlterar = dao.alterar(funcionaro);
		assertNotNull(fAlterar);
		assertEquals(true, fAlterar);
	}

	@Test
	private void testMetodoVerificarLogin() {
		Funcionario funcionaro = new Funcionario();
		funcionaro.setNome("teste novo nome");
		funcionaro.setCpf(Long.parseLong("12312312312"));
		funcionaro.setVendasDouble(0.0);
		funcionaro.setAdmin(true);

		Boolean fLogin = dao.verificarLogin(funcionaro);
		assertNotNull(fLogin);
		assertEquals(true, fLogin);
	}

	@Test
	private void testMetodoVerificarAdmin() {
		Funcionario funcionaro = new Funcionario();
		funcionaro.setNome("teste novo nome");
		funcionaro.setCpf(Long.parseLong("12312312312"));
		funcionaro.setVendasDouble(0.0);
		funcionaro.setAdmin(true);

		Funcionario fLoginAdmin = dao.verificarFuncionarioAdmin(funcionaro);
		assertNotNull(fLoginAdmin);
		assertEquals(fLoginAdmin, funcionaro);
	}

	@Test
	private void testMetodoRemoverFuncionario() {
		Funcionario funcionaro = new Funcionario();
		funcionaro.setNome("teste novo nome");
		funcionaro.setCpf(Long.parseLong("12312312312"));
		funcionaro.setVendasDouble(0.0);
		funcionaro.setAdmin(true);

		Boolean fRemover = dao.remover(funcionaro);
		assertNotNull(fRemover);
		assertEquals(true, fRemover);
	}
}
