package modelo;

import Interfaces.ISala;

public class Sala {
	private int salaId;
	private String Nome;
	private String Time;
	private Filme filme;
	public Sala(String nome, String time) {
		setNome(nome);
		setTime(time);
	}
	
	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public Sala() {
		
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		this.Nome = nome;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public int getSalaId() {
		return salaId;
	}

	public void setSalaId(int salaId) {
		this.salaId = salaId;
	}

	
}
