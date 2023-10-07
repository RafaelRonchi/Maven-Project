package Interfaces;

import java.util.ArrayList;

import modelo.Funcionario;

public interface IFuncionario {
	
	 boolean inserir(Funcionario p);
	 boolean remover(Funcionario f);
	 boolean alterar(Funcionario novoFuncionario);
	 boolean verificarLogin(Funcionario f);
	 Funcionario verificarFuncionarioAdmin(Funcionario f);
	 Funcionario getFuncionarioCPF(String cpf);
	ArrayList<Funcionario> listarFuncionario();
}
