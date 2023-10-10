package Interfaces;

import java.util.List;

import model.Assento;
import model.Cliente;
import model.Funcionario;
import model.Sala;

public interface IAssento {
	Assento cadastrarClienteNoAssento(Assento a, Cliente c);
	Assento listarClienteCadastroNoAssento(Assento a);
	Cliente cadastrarCliente(Cliente c);
	Assento removerClienteDoAssento(Assento a);
	Boolean pegarEstadoDoAssento(Assento a);
	Sala pegarSala(Sala s);
	boolean[][] pegarAssentosOcupados(Sala sala);
	Cliente alterarCliente(Cliente clienteAtualizado);
	Cliente pegarClientePorCPF(Long CPF);
	List<Cliente> pegarClientes();
}
