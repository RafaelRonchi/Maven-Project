package Interfaces;

import modelo.Assento;
import modelo.Cliente;
import modelo.Sala;

public interface IAssento {
	Assento cadastrarClienteNoAssento(Assento a, Cliente c, Sala s);
	Assento listarClienteCadastroNoAssento(Assento a);
}
