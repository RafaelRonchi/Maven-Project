package Interfaces;

import java.util.ArrayList;

import model.Cliente;

public interface ICliente {
	public boolean inserir(Cliente u, String sala, Integer row, Integer col);

	public boolean remover(Cliente u, String sala, Integer row, Integer col);

	public boolean alterar(Cliente u, String sala, Integer row, Integer col);

	public ArrayList<Cliente> listarUsuarios(Integer row, Integer col, String salaN);

}
