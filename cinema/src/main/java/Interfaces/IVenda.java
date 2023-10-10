package Interfaces;

import java.util.List;

import model.Venda;

public interface IVenda {
	Venda cadastrarVenda(Venda v);
	List<Venda> pegarVendas();
}
