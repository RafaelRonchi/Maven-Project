package modelo;

public class Assento {
	private int assentoId;
	private String nomeSala;
	private int row;
	private int col;
	public int getAssentoId() {
		return assentoId;
	}
	public void setAssentoId(int assentoId) {
		this.assentoId = assentoId;
	}
	private Sala sala;
	private Cliente cliente;
	
	
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getNomeSala() {
		return nomeSala;
	}
	public void setNomeSala(String nomeSala) {
		this.nomeSala = nomeSala;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	
}
