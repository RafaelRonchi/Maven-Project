package modelo;

public class Cliente extends Funcionario{

	public Cliente(long cpf, String nome, Boolean meia) {
		super(cpf, nome);
		// TODO Auto-generated constructor stub
	}
	public Cliente() {}

	/**
	 * 
	 */
	private Boolean meiaEntrada;
	private Double precoIngresso;

	public Boolean getMeiaEntrada() {
		return meiaEntrada;
	}

	public void setMeiaEntrada(Boolean meiaEntrada) {
		this.meiaEntrada = meiaEntrada;
	}

	public Double getPrecoIngresso() {
		return precoIngresso;
	}

	public void setPrecoIngresso(Double precoIngresso) {
		this.precoIngresso = precoIngresso;
	}
	
}
