package br.misael.clinicamedica.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.misael.clinicamedica.model.vo.VoAgendamento;
import br.misael.clinicamedica.model.vo.VoPaciente;

public class DaoAgendamento {

	private Dao dao;

	public DaoAgendamento() {
		dao = new Dao();
	}

	public void inserir(VoAgendamento voAgendamento) throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO AGENDAMENTO");
		sql.append("(ID_PACIENTE, DATA_CONSULTA)");
		sql.append(" VALUES(?, ?)");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setLong(1, voAgendamento.getPaciente().getIdPaciente());
		pstm.setTimestamp(2, voAgendamento.getDataConsulta());

		pstm.executeUpdate();

		dao.desconectar(conn, pstm, null);

	}

	public void alterar(VoAgendamento voAgendamento) throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE AGENDAMENTO");
		sql.append(" SET ID_PACIENTE = ?, DATA_CONSULTA = ?");
		sql.append(" WHERE ID_CONSULTA = ?");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setLong(1, voAgendamento.getPaciente().getIdPaciente());
		pstm.setTimestamp(2, voAgendamento.getDataConsulta());
		pstm.setLong(3, voAgendamento.getIdConsulta());

		pstm.executeUpdate();

		dao.desconectar(conn, pstm, null);

	}

	public void excluir(VoAgendamento voAgendamento) throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		StringBuilder sql = new StringBuilder();

		sql.append("DELETE FROM AGENDAMENTO");
		sql.append(" WHERE");
		sql.append(" ID_CONSULTA = ?");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setLong(1, voAgendamento.getIdConsulta());

		pstm.executeUpdate();

		dao.desconectar(conn, pstm, null);

	}

	public List<VoAgendamento> consultar() throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		List<VoAgendamento> agendamentos = new ArrayList<VoAgendamento>();
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT");
		sql.append(" A.ID_CONSULTA");
		sql.append(", P.ID_PACIENTE");
		sql.append(", P.NOME");
		sql.append(", P.DATA_NASCIMENTO");
		sql.append(", P.ATIVO");
		sql.append(", A.DATA_CONSULTA");
		sql.append(" FROM");
		sql.append(" AGENDAMENTO A");
		sql.append(" JOIN PACIENTE P");
		sql.append(" ON(A.ID_PACIENTE = P.ID_PACIENTE)");
		sql.append(" ORDER BY");
		sql.append(" A.ID_CONSULTA");

		pstm = conn.prepareStatement(sql.toString());
		rs = pstm.executeQuery();

		while (rs.next()) {

			VoAgendamento voAgendamento = new VoAgendamento();
			VoPaciente voPaciente = new VoPaciente();
			voAgendamento.setPaciente(voPaciente);

			voAgendamento.setIdConsulta(rs.getLong("ID_CONSULTA"));

			voAgendamento.getPaciente().setIdPaciente(rs.getLong("ID_PACIENTE"));
			voAgendamento.getPaciente().setNome(rs.getString("NOME"));
			voAgendamento.getPaciente().setDataNascimento(rs.getTimestamp("DATA_NASCIMENTO"));
			voAgendamento.getPaciente().setAtivo(rs.getBoolean("ATIVO"));

			voAgendamento.setDataConsulta(rs.getTimestamp("DATA_CONSULTA"));

			agendamentos.add(voAgendamento);

		}

		dao.desconectar(conn, pstm, rs);

		return agendamentos;

	}

	public List<VoAgendamento> consultar(String nomePaciente) throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		List<VoAgendamento> agendamentos = new ArrayList<VoAgendamento>();
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT");
		sql.append(" A.ID_CONSULTA");
		sql.append(", P.ID_PACIENTE");
		sql.append(", P.NOME");
		sql.append(", P.DATA_NASCIMENTO");
		sql.append(", P.ATIVO");
		sql.append(", A.DATA_CONSULTA");
		sql.append(" FROM");
		sql.append(" AGENDAMENTO A");
		sql.append(" JOIN PACIENTE P");
		sql.append(" ON(A.ID_PACIENTE = P.ID_PACIENTE)");
		sql.append(" WHERE");
		sql.append(" P.NOME LIKE ?");
		sql.append(" ORDER BY");
		sql.append(" A.ID_CONSULTA");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setString(1, "%" + nomePaciente + "%");
		rs = pstm.executeQuery();

		while (rs.next()) {

			VoAgendamento voAgendamento = new VoAgendamento();
			VoPaciente voPaciente = new VoPaciente();
			voAgendamento.setPaciente(voPaciente);

			voAgendamento.setIdConsulta(rs.getLong("ID_CONSULTA"));

			voAgendamento.getPaciente().setIdPaciente(rs.getLong("ID_PACIENTE"));
			voAgendamento.getPaciente().setNome(rs.getString("NOME"));
			voAgendamento.getPaciente().setDataNascimento(rs.getTimestamp("DATA_NASCIMENTO"));
			voAgendamento.getPaciente().setAtivo(rs.getBoolean("ATIVO"));

			voAgendamento.setDataConsulta(rs.getTimestamp("DATA_CONSULTA"));

			agendamentos.add(voAgendamento);

		}

		dao.desconectar(conn, pstm, rs);

		return agendamentos;

	}

	public List<VoAgendamento> consultar(VoPaciente paciente) throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		List<VoAgendamento> agendamentos = new ArrayList<VoAgendamento>();
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT");
		sql.append(" A.ID_CONSULTA");
		sql.append(", P.ID_PACIENTE");
		sql.append(", P.NOME");
		sql.append(", P.DATA_NASCIMENTO");
		sql.append(", P.ATIVO");
		sql.append(", A.DATA_CONSULTA");
		sql.append(" FROM");
		sql.append(" AGENDAMENTO A");
		sql.append(" JOIN PACIENTE P");
		sql.append(" ON(A.ID_PACIENTE = P.ID_PACIENTE)");
		sql.append(" WHERE");
		sql.append(" P.ID_PACIENTE = ?");
		sql.append(" ORDER BY");
		sql.append(" A.ID_CONSULTA");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setLong(1, paciente.getIdPaciente());
		rs = pstm.executeQuery();

		while (rs.next()) {

			VoAgendamento voAgendamento = new VoAgendamento();
			VoPaciente voPaciente = new VoPaciente();
			voAgendamento.setPaciente(voPaciente);

			voAgendamento.setIdConsulta(rs.getLong("ID_CONSULTA"));

			voAgendamento.getPaciente().setIdPaciente(rs.getLong("ID_PACIENTE"));
			voAgendamento.getPaciente().setNome(rs.getString("NOME"));
			voAgendamento.getPaciente().setDataNascimento(rs.getTimestamp("DATA_NASCIMENTO"));
			voAgendamento.getPaciente().setAtivo(rs.getBoolean("ATIVO"));

			voAgendamento.setDataConsulta(rs.getTimestamp("DATA_CONSULTA"));

			agendamentos.add(voAgendamento);

		}

		dao.desconectar(conn, pstm, rs);

		return agendamentos;

	}

}
