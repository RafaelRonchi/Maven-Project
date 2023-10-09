package modelo;

public class Cliente extends Funcionario{
	
	 private Long cpf;
	    private String nome;
	    private Boolean meiaEntrada;
	    private int clienteId;
	    public Cliente() {}
	    public Cliente(Long cpf, String nome, Boolean meiaEntrada) {
	        this.cpf = cpf;
	        this.nome = nome;
	        this.meiaEntrada = meiaEntrada;
	    }

	    public Cliente(int clienteId, Long cpf, String nome, Boolean meiaEntrada) {
	        this.clienteId = clienteId;
	        this.cpf = cpf;
	        this.nome = nome;
	        this.meiaEntrada = meiaEntrada;
	    }

	    // Getters e setters para os atributos
	    public Long getCpf() {
	        return cpf;
	    }

	    public void setCpf(Long Cpf) {
	        this.cpf = Cpf;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public Boolean getMeiaEntrada() {
	        return meiaEntrada;
	    }

	    public void setMeiaEntrada(Boolean meiaEntrada) {
	        this.meiaEntrada = meiaEntrada;
	    }

	    public int getClienteId() {
	        return clienteId;
	    }

	    public void setClienteId(int clienteId) {
	        this.clienteId = clienteId;
	    }
	
}
