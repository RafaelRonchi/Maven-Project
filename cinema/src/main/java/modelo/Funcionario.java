package modelo;


public class Funcionario {
	private Long cpf;
	private String nome;
	private Double vendasDouble;	
	private Boolean admin;
	
	public Funcionario(long Cpf, String Nome) {
		cpf = Cpf;
		nome = Nome;
		vendasDouble = 0.00;
	}
	
	public Funcionario(long Cpf, String Nome, Double Vendas) {
		cpf = Cpf;
		nome = Nome;
		vendasDouble = Vendas;
	}
	public Funcionario() {
		
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long Cpf) {
		cpf = Cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String Nome) {
		nome = Nome;
	}
	public Double getVendasDouble() {
		return vendasDouble;
	}
	public void setVendasDouble(Double vendasDouble) {
		this.vendasDouble = vendasDouble;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
}
	
	
	
	
	
	
	

