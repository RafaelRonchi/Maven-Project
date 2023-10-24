package Interfaces;

import java.util.ArrayList;

import model.Funcionario;

public interface IFuncionario {
	
	 boolean inserir(Funcionario p);
	 boolean remover(Funcionario f);
	 boolean alterar(Funcionario novoFuncionario);
	 boolean verificarLogin(Funcionario f);
	 Funcionario verificarFuncionarioAdmin(Funcionario f);
	 Funcionario getFuncionarioCPF(Long cpf);
	 ArrayList<Funcionario> listarFuncionario();
}
