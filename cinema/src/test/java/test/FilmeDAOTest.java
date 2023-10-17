package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controller.FilmeDAO;
import model.Filme;

public class FilmeDAOTest {
	private FilmeDAO dao = FilmeDAO.getInstancia();
	@Test
	private void testMostraFilme() {
		ArrayList<Filme> filmes = dao.mostraFilme();
		assertNotNull(filmes);
		assertEquals(false, filmes.isEmpty());
	}

}
