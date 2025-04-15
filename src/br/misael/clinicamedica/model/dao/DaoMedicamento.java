package br.misael.clinicamedica.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.misael.clinicamedica.model.vo.VoMedicamento;

public class DaoMedicamento {
	
	private Dao dao;
	
	public DaoMedicamento() {
		dao = new Dao();
	}
	
	public void inserir(VoMedicamento voMeidcamento) throws ClassNotFoundException, SQLException {
		
		Connection conn        = dao.conectar();
		PreparedStatement pstm = null;
		pstm                   = conn.prepareStatement("INSERT INTO MEDICAMENTO(NOME) VALUES(?)");
		pstm.setString(1, voMeidcamento.getNome());
		
		pstm.executeUpdate();
		
		dao.desconectar(conn, pstm, null);
		
	}
	
	public void alterar(VoMedicamento voMeidcamento)  throws ClassNotFoundException, SQLException {
		
		Connection conn        = dao.conectar();
		PreparedStatement pstm = null;
		pstm                   = conn.prepareStatement("UPDATE MEDICAMENTO SET NOME = ? WHERE ID_MEDICAMENTO = ?");
		pstm.setString(1, voMeidcamento.getNome());
		pstm.setLong(2, voMeidcamento.getIdMedicamento());
		
		pstm.executeUpdate();
		
		dao.desconectar(conn, pstm, null);
		
	}
	
	public void excluir(VoMedicamento voMeidcamento) throws ClassNotFoundException, SQLException {
		
		Connection conn        = dao.conectar();
		PreparedStatement pstm = null;
		pstm                   = conn.prepareStatement("DELETE FROM MEDICAMENTO WHERE ID_MEDICAMENTO = ?");
		pstm.setLong(1, voMeidcamento.getIdMedicamento());
		
		pstm.executeUpdate();
		
		dao.desconectar(conn, pstm, null);
		
	}
	
	public List<VoMedicamento> consultar() throws ClassNotFoundException, SQLException {
		
		Connection conn                   = dao.conectar();
		PreparedStatement pstm            = null;
		List<VoMedicamento>  medicamentos = new ArrayList<VoMedicamento>();
		ResultSet rs                      = null;
		pstm                              = conn.prepareStatement("SELECT * FROM MEDICAMENTO");
		rs                                = pstm.executeQuery();
		
		while(rs.next()) {
			
			VoMedicamento voMedicamento = new VoMedicamento();
			voMedicamento.setIdMedicamento(rs.getLong("ID_MEDICAMENTO"));
			voMedicamento.setNome(rs.getString("NOME"));
			
			medicamentos.add(voMedicamento);
			
		}
		
		dao.desconectar(conn, pstm, rs);
		
		return medicamentos;
		
	}
	
	public List<VoMedicamento> consultar(String valor ) throws ClassNotFoundException, SQLException {
		
		Connection conn                   = dao.conectar();
		PreparedStatement pstm            = null;
		List<VoMedicamento>  medicamentos = new ArrayList<VoMedicamento>();
		ResultSet rs                      = null;
		pstm                              = conn.prepareStatement("SELECT * FROM MEDICAMENTO WHERE NOME LIKE ?");
		pstm.setString(1, "%" + valor + "%");
		rs   = pstm.executeQuery();
		
		while(rs.next()) {
			
			VoMedicamento voMedicamento = new VoMedicamento();
			voMedicamento.setIdMedicamento(rs.getLong("ID_MEDICAMENTO"));
			voMedicamento.setNome(rs.getString("NOME"));
			
			medicamentos.add(voMedicamento);
			
		}
		
		dao.desconectar(conn, pstm, rs);
		
		return medicamentos;
		
	}

}
