package Interfaces;

import java.util.List;

import modelo.Venda;

public interface IVenda {
	Venda cadastrarVenda(Venda v);
	List<Venda> pegarVendas();
}
