package model;


public class Funcionario {
	private int id;
	private Long cpf;
	private String nome;
	private Double vendasDouble;	
	private Boolean admin;
	
	public Funcionario(Long Cpf, String Nome) {
		cpf = Cpf;
		nome = Nome;
		vendasDouble = 0.00;
	}
	
	public Funcionario(Long Cpf, String Nome, Double Vendas) {
		cpf = Cpf;
		nome = Nome;
		vendasDouble = Vendas;
	}
	public Funcionario(Long Cpf, String Nome, Double Vendas, Boolean Admin) {
		cpf = Cpf;
		nome = Nome;
		vendasDouble = Vendas;
		admin = Admin;
	}
	
	public Funcionario(Long Cpf, String Nome, Boolean Admin) {
		cpf = Cpf;
		nome = Nome;
		admin = Admin;
		vendasDouble = 0.00;
	}
	
	public Funcionario(int Id) {
		setId(Id);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
	
	
	
	
	
	
	

