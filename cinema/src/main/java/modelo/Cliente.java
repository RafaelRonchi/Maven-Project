package modelo;

public class Cliente extends Funcionario{
	
	public Cliente(long cpf, String nome, Boolean meia) {
		super(cpf, nome);
		// TODO Auto-generated constructor stub
	}
	public Cliente() {}

	private int clienteId;
	private Boolean meiaEntrada;

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
