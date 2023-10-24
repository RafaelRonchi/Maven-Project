package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import controller.VendaDAO;
import model.Assento;
import model.Cliente;
import model.Filme;
import model.Sala;
import model.Venda;

public class VendaDAOTest {
	VendaDAO dao = VendaDAO.getInstancia();
	@Test
	public void testCadastrarVenda() {
		Cliente cliente = new Cliente(Long.parseLong("11111111111"), "Teste", true);
		Sala sala = new Sala("A1", "10:00:00", new Filme("Jonh Wick"));
		Assento assento = new Assento(1, 1, sala , cliente);
		Venda cadastrarVenda = dao.cadastrarVenda(new Venda(10.0, cliente, cliente, assento));
		assertNotNull(cadastrarVenda);
		assertEquals( false, cadastrarVenda.equals(null));
	}
	
	@Test
	public void testGetVenda() {
		List<Venda> vendas = dao.pegarVendas();
		assertNotNull(vendas);
		assertEquals(false, vendas.isEmpty());
	}
}
