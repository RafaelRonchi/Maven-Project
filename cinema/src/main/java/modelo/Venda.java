package modelo;

public class Venda {
	private int idVenda;
	private Double vendaValor;
	private Funcionario funcionario;
	private Cliente cliente;
	private Assento assento;
	
	
	public Venda() {
		super();
	}
	public Venda( Double vendaValor, Funcionario funcionario, Cliente cliente, Assento assento) {
		super();
		this.vendaValor = vendaValor;
		this.funcionario = funcionario;
		this.cliente = cliente;
		this.assento = assento;
	}
	public int getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}
	public Double getVendaValor() {
		return vendaValor;
	}
	public void setVendaValor(Double vendaValor) {
		this.vendaValor = vendaValor;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Assento getAssento() {
		return assento;
	}
	public void setAssento(Assento assento) {
		this.assento = assento;
	}
}
