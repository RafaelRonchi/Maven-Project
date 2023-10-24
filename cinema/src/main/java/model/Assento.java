package model;

public class Assento {
	private int assentoId;
	private int row;
	private int col;
	private Sala sala;
	private Cliente cliente;

	
	public Assento() {
		// TODO Auto-generated constructor stub
	}
	
	public Assento(int AssentoId) {
		setAssentoId(AssentoId);
	}
	
	public Assento(int Row, int Col, Sala Sala) {
		setRow(Row); 
		setCol(Col);
		setSala(Sala);
	}
	
	public Assento(int Row, int Col, Sala Sala, Cliente cliente) {
		setRow(Row); 
		setCol(Col);
		setSala(Sala);
		setCliente(cliente);
	}
	
	
	
	public int getAssentoId() {
		return assentoId;
	}
	public void setAssentoId(int assentoId) {
		this.assentoId = assentoId;
	}
	
	
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
