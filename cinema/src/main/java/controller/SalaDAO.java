package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Interfaces.ISala;
import connection.ConexaoMySql;
import model.Filme;
import model.Sala;

public class SalaDAO implements ISala{
	private static ArrayList<Sala> ListSalas;
	private static SalaDAO instancia;
	private static Connection conexao;
	
	private SalaDAO() {
		conexao = ConexaoMySql.getConexao();

	}
	
	public static SalaDAO getInstancia() {
		if(instancia == null) {
			instancia = new SalaDAO();
			ListSalas = new ArrayList<>();
		}
		return instancia;
	}
	@Override
	public ArrayList<Sala> verSala() {
		// TODO Auto-generated method stub
		ArrayList<Sala> salas = new ArrayList<>();
		
		String selectSQL = "Select nome_sala, sala_horario from SALA";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conexao.prepareStatement(selectSQL);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Sala s = new Sala(rs.getString("nome_sala"), rs.getString("sala_horario"));
				salas.add(s);
			}
			
			return salas;
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (ps != null) {
	                ps.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		return salas;
	}

	

}
