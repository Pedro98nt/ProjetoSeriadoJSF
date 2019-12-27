package br.edu.faculdadedelta.projetoseriadojsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.projetoseriadojsf.modelo.Seriado;
import br.edu.faculdadedelta.projetoseriadojsf.util.Conexao;

public class SeriadoDao {

	public void incluir(Seriado seriado) 
			throws ClassNotFoundException, SQLException 
			 {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO seriados (nome_seriado, nota_seriado, valor_seriado) "
				+ " VALUES (?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, seriado.getNome().trim());
		ps.setInt(2, seriado.getNota());
		ps.setDouble(3, seriado.getValor());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public void alterar(Seriado seriado) 
			throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDeDados();
		String sql = "UPDATE seriados "
				+ " SET nome_seriado = ?, "
				+ " nota_seriado = ?, "
				+ " valor_seriado = ? "
				+ " WHERE id_seriado = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, seriado.getNome().trim());
		ps.setInt(2, seriado.getNota());
		ps.setDouble(3, seriado.getValor());
		ps.setLong(4, seriado.getId());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public void excluir(Seriado seriado) 
			throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM seriados WHERE id_seriado = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, seriado.getId());
		
		ps.executeUpdate(); 
		
		ps.close();
		conn.close();
	}
	
	public List<Seriado> listar() 
				throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_seriado, nome_seriado, nota_seriado, valor_seriado "
				+ " FROM seriados";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Seriado> listaRetorno = new ArrayList<>();
		while (rs.next()) {
			Seriado seriado = new Seriado();
			seriado.setId(rs.getLong("id_seriado"));
			seriado.setNome(rs.getString("nome_seriado").trim());
			seriado.setNota(rs.getInt("nota_seriado"));
			seriado.setValor(rs.getDouble("valor_seriado"));
			listaRetorno.add(seriado);
		}
		rs.close();
		ps.close();
		conn.close();
		return listaRetorno;
	}
}









