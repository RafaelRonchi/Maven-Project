package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import controller.AssentoDAO;
import main.Main;
import model.Assento;
import model.Cliente;
import model.Filme;
import model.Funcionario;
import model.Sala;
import model.SessaoFuncionario;


public class AssentoDAOTest {
	private AssentoDAO dao = AssentoDAO.getInstancia();

	@Test
	public void testCadastrarCliente() {
		Cliente cliente = new Cliente(Long.parseLong("11111111111"), "Teste", true);
		Cliente clienteCadastro = dao.cadastrarCliente(cliente);

		assertNotNull(clienteCadastro);
		assertEquals(false, clienteCadastro.equals(null));

	}

	@Test
	public void testCadastrarClienteNoAssento() {
		Cliente cliente = new Cliente(Long.parseLong("11111111111"), "Teste", true);
		Sala sala = new Sala("A1", "10:00:00", new Filme("Jonh Wick"));
		Assento assento = new Assento(1, 1, sala, cliente);
		Assento assentoCadastro = dao.cadastrarClienteNoAssento(assento, cliente);
		
		Funcionario funcio = new Funcionario(Long.parseLong("11111111111"), "Teste", true);
		SessaoFuncionario.setFuncionarioLogado(funcio);
		assertNotNull(assentoCadastro);
		assertEquals(false, assentoCadastro.equals(null));

	}

	@Test
	public void listarClienteNoAssento() {
		Cliente cliente = new Cliente(Long.parseLong("11111111111"), "Teste", true);
		Sala sala = new Sala("A1", "10:00:00", new Filme("Jonh Wick"));
		Assento assento = new Assento(1, 1, sala, cliente);
		Assento assentoCliente = dao.listarClienteCadastroNoAssento(assento);

		assertNotNull(assentoCliente);
		assertEquals(false, assentoCliente.equals(null));

	}

	@Test
	public void testRemoverClienteDoAssento() {
		Cliente cliente = new Cliente(Long.parseLong("11111111111"), "Teste", true);
		Sala sala = new Sala("A1", "10:00:00", new Filme("Jonh Wick"));
		Assento assento = new Assento(1, 1, sala, cliente);
		Assento assentoRemover = dao.removerClienteDoAssento(assento);

		assertNotNull(assentoRemover);
		assertEquals(false, assentoRemover.equals(null));

	}

	@Test
	public void testPegarEstadoDoAssento() {
		Cliente cliente = new Cliente(Long.parseLong("11111111111"), "Teste", true);
		Sala sala = new Sala("A1", "10:00:00", new Filme("Jonh Wick"));
		Assento assento = new Assento(1, 1, sala, cliente);
		Boolean assentoEstado = dao.pegarEstadoDoAssento(assento);

		assertNotNull(assentoEstado);
		assertEquals(true, assentoEstado);

	}

	@Test
	public void testPegarSala() {
		Sala sala = new Sala("A1", "10:00:00", new Filme("Jonh Wick"));
		boolean[][] assentosOcupados = dao.pegarAssentosOcupados(sala);

		assertNotNull(assentosOcupados);
		assertEquals(false, assentosOcupados.equals(null));
	}

	public void alterarCliente() {

		Cliente cliente = new Cliente(Long.parseLong("11111111111"), "Teste", true);

		Cliente cAlterar = dao.alterarCliente(cliente);
		assertNotNull(cAlterar);
		assertEquals(false, cAlterar.equals(null));

	}

	public void pegarClientePorCPF() {
		Cliente cliente = new Cliente(Long.parseLong("11111111111"), "Teste", true);
		Cliente pegarCpf = dao.pegarClientePorCPF(cliente.getCpf());

		assertNotNull(pegarCpf);
		assertEquals(false, pegarCpf.equals(null));

	}

	public void pegarClientes() {

		List<Cliente> cListar = dao.pegarClientes();

		assertNotNull(cListar);
		assertEquals(false, cListar.isEmpty());

	}
}
