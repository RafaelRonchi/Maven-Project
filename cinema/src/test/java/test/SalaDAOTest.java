package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controller.SalaDAO;
import model.Sala;

public class SalaDAOTest {
	private SalaDAO dao = SalaDAO.getInstancia();
	
	@Test
	private void testVerSala() {
		ArrayList<Sala> salas = dao.verSala(); 
		assertNotNull(salas);
		assertEquals(false, salas.isEmpty());
	}
}
