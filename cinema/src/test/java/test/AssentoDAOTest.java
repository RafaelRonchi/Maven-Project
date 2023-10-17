package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import controller.AssentoDAO;
import model.Assento;
import model.Cliente;
import model.Filme;
import model.Sala;

public class AssentoDAOTest {
	private AssentoDAO dao = AssentoDAO.getInstancia();
	@Test
	private void testCadastrarCliente() {
		Cliente cliente = new Cliente(Long.parseLong("11111111111"), "Teste", true);
		Cliente clienteCadastro = dao.cadastrarCliente(cliente);
		
		assertNotNull(clienteCadastro);
		assertEquals(false, clienteCadastro.equals(null));

	
	}
	@Test
	private void testCadastrarClienteNoAssento() {
		Cliente cliente = new Cliente(Long.parseLong("11111111111"), "Teste", true);
		Sala sala = new Sala("A1", "10:00:00", new Filme("Jonh Wick"));
		Assento assento = new Assento(1, 1, sala , cliente);
		Assento assentoCadastro = dao.cadastrarClienteNoAssento(assento, cliente);
		
		assertNotNull(assentoCadastro);
		assertEquals(false, assentoCadastro.equals(null));

	}
	@Test
	private void listarClienteNoAssento() {
		Cliente cliente = new Cliente(Long.parseLong("11111111111"), "Teste", true);
		Sala sala = new Sala("A1", "10:00:00", new Filme("Jonh Wick"));
		Assento assento = new Assento(1, 1, sala , cliente);
		Assento assentoCliente = dao.listarClienteCadastroNoAssento(assento);
		
		assertNotNull(assentoCliente);
		assertEquals(false, assentoCliente.equals(null));

	}
	
	@Test
	private void testRemoverClienteDoAssento() {
		Cliente cliente = new Cliente(Long.parseLong("11111111111"), "Teste", true);
		Sala sala = new Sala("A1", "10:00:00", new Filme("Jonh Wick"));
		Assento assento = new Assento(1, 1, sala , cliente);
		Assento assentoRemover = dao.removerClienteDoAssento(assento);
		
		assertNotNull(assentoRemover);
		assertEquals(false, assentoRemover.equals(null));

	}
	@Test
	private void testPegarEstadoDoAssento() {
		Cliente cliente = new Cliente(Long.parseLong("11111111111"), "Teste", true);
		Sala sala = new Sala("A1", "10:00:00", new Filme("Jonh Wick"));
		Assento assento = new Assento(1, 1, sala , cliente);
		Boolean assentoEstado = dao.pegarEstadoDoAssento(assento);
		
		assertNotNull(assentoEstado);
		assertEquals(true, assentoEstado);

	}
	@Test
	private void testPegarSala() {
		Sala sala = new Sala("A1", "10:00:00", new Filme("Jonh Wick"));
		boolean[][] assentosOcupados = dao.pegarAssentosOcupados(sala);
		
		assertNotNull(assentosOcupados);
		assertEquals(false, assentosOcupados.equals(null));
	}
	
	

//	Cliente alterarCliente(Cliente clienteAtualizado);
//	Cliente pegarClientePorCPF(Long CPF);
//	List<Cliente> pegarClientes();
}
