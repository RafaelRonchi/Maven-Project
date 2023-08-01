package Interfaces;

import modelo.Cliente;

public interface ICliente {
	public boolean inserir(Cliente u, Integer i, Integer j, Integer salaN);

    public boolean remover(Cliente usua, Integer i, Integer j, Integer salaN);

    public boolean alterar(Cliente novoUsuario, Integer i, Integer j, Integer salaN);

    public static Cliente listarUsuarios(Integer i, Integer j, Integer salaN) {
		return null;
	}
}
