package model;

public class SessaoFuncionario {
	private static Funcionario funcionarioLogado;
	
	 public static Funcionario getFuncionarioLogado() {
	        return funcionarioLogado;
	    }

	 public static void setFuncionarioLogado(Funcionario funcionario) {
	        funcionarioLogado = funcionario;
	    }
}
