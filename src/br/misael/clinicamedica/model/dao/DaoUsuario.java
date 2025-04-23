package br.misael.clinicamedica.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.misael.clinicamedica.model.vo.VoMedicamento;
import br.misael.clinicamedica.model.vo.VoUsuario;

public class DaoUsuario {
	private Dao dao;

	public DaoUsuario() {
		dao = new Dao();
	}

	public void inserir(VoUsuario voUsuario) throws ClassNotFoundException, SQLException {
		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		pstm = conn.prepareStatement("INSERT INTO USUARIO(LOGIN, PASSWORD) VALUES (?,?)");
		pstm.setString(1, voUsuario.getLogin());
		pstm.setString(2, voUsuario.getPassword());
		pstm.executeUpdate();
		dao.desconectar(conn, pstm, null);
	}

	public void alterarSenha(VoUsuario voUsuario) throws ClassNotFoundException, SQLException {
		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		pstm = conn.prepareStatement("UPDATE USUARIO SET PASSWORD = ? WHERE ID_USUARIO = ?");
		pstm.setString(1, voUsuario.getPassword());
		pstm.setLong(2, voUsuario.getIdUsuario());
		pstm.executeUpdate();
		dao.desconectar(conn, pstm, null);
	}

	public List<VoUsuario> consultar() throws ClassNotFoundException, SQLException {
		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		List<VoUsuario> usuarios = new ArrayList<VoUsuario>();
		ResultSet rs = null;
		pstm = conn.prepareStatement("SELECT * FROM USUARIO");
		rs = pstm.executeQuery();

		while (rs.next()) {
			VoUsuario voUsuario = new VoUsuario();
			voUsuario.setIdUsuario(rs.getLong("ID_USUARIO"));
			voUsuario.setLogin (rs.getString("LOGIN"));
			voUsuario.setAtivo(rs.getBoolean("ATIVO"));

			usuarios.add(voUsuario);
		}

		dao.desconectar(conn, pstm, rs);

		return usuarios;
	}
	
	public VoUsuario consultarLogin(String login, String password) throws ClassNotFoundException, SQLException {
		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		VoUsuario usuario = null;
		ResultSet rs = null;
		pstm = conn.prepareStatement("SELECT ID_USUARIO, LOGIN, ATIVO FROM USUARIO WHERE LOGIN = ? AND PASSWORD = ?");
		pstm.setString(1,login);
		pstm.setString(2, password);
		rs = pstm.executeQuery();
		
		while (rs.next()) {
			usuario = new VoUsuario();
			usuario.setIdUsuario(rs.getLong("ID_USUARIO"));
			usuario.setLogin(rs.getString("LOGIN"));
			usuario.setAtivo(rs.getBoolean("ATIVO"));
		}
		
		dao.desconectar(conn, pstm, rs);
		
		return usuario;
	}

}
