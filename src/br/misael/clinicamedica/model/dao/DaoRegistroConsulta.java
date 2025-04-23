package br.misael.clinicamedica.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.misael.clinicamedica.model.vo.VoAgendamento;
import br.misael.clinicamedica.model.vo.VoPaciente;
import br.misael.clinicamedica.model.vo.VoRegistroConsulta;

public class DaoRegistroConsulta {

	private Dao dao;

	public DaoRegistroConsulta() {

		dao = new Dao();

	}

	public void inserir(VoRegistroConsulta voRegistroConsulta) throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO REGISTRO_CONSULTA");
		sql.append("(ID_CONSULTA, PRONTUARIO, FINALIZADO, DATA_FIM_CONSULTA)");
		sql.append("VALUES");
		sql.append("(?, ?, ?, ?)");

		pstm = conn.prepareStatement(sql.toString());

		pstm.setLong(1, voRegistroConsulta.getVoAgendamento().getIdConsulta());
		pstm.setString(2, voRegistroConsulta.getProntuario());
		pstm.setBoolean(3, voRegistroConsulta.isConsultaFinalizada());
		pstm.setTimestamp(4, voRegistroConsulta.getDataFimConsulta());

		pstm.executeUpdate();

		dao.desconectar(conn, pstm, null);

	}

	public void alterar(VoRegistroConsulta voRegistroConsulta) throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE REGISTRO_CONSULTA");
		sql.append(" SET PRONTUARIO = ?, FINALIZADO = ?, DATA_FIM_CONSULTA = ?");
		sql.append(" WHERE");
		sql.append(" ID_CONSULTA = ?");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setString(1, voRegistroConsulta.getProntuario());
		pstm.setBoolean(2, voRegistroConsulta.isConsultaFinalizada());
		pstm.setTimestamp(3, voRegistroConsulta.getDataFimConsulta());
		pstm.setLong(4, voRegistroConsulta.getVoAgendamento().getIdConsulta());

		pstm.executeUpdate();

		dao.desconectar(conn, pstm, null);

	}

	public void excluir(VoRegistroConsulta voRegistroConsulta) throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		StringBuilder sql = new StringBuilder();

		sql.append("DELETE FROM REGISTRO_CONSULTA ");
		sql.append(" WHERE");
		sql.append(" ID_CONSULTA = ?");

		pstm = conn.prepareStatement(sql.toString());

		pstm.setLong(1, voRegistroConsulta.getVoAgendamento().getIdConsulta());

		pstm.executeUpdate();

		dao.desconectar(conn, pstm, null);

	}

	public List<VoRegistroConsulta> consultar() throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		List<VoRegistroConsulta> registrosConsultas = new ArrayList<VoRegistroConsulta>();
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT");
		sql.append(" RC.ID_CONSULTA");
		sql.append(", RC.PRONTUARIO");
		sql.append(", RC.FINALIZADO");
		sql.append(", RC.DATA_FIM_CONSULTA");
		sql.append(", A.ID_PACIENTE");
		sql.append(", A.DATA_CONSULTA");
		sql.append(", P.NOME");
		sql.append(", P.DATA_NASCIMENTO");
		sql.append(", P.ATIVO");
		sql.append(" FROM");
		sql.append(" REGISTRO_CONSULTA RC");
		sql.append(" JOIN AGENDAMENTO A");
		sql.append(" ON(RC.ID_CONSULTA = A.ID_CONSULTA)");
		sql.append(" JOIN PACIENTE P");
		sql.append(" ON(A.ID_PACIENTE = P.ID_PACIENTE)");

		pstm = conn.prepareStatement(sql.toString());
		rs = pstm.executeQuery();

		while (rs.next()) {

			VoRegistroConsulta voRegistroConsulta = new VoRegistroConsulta();
			VoAgendamento voAgendamento = new VoAgendamento();
			VoPaciente voPaciente = new VoPaciente();

			voRegistroConsulta.setVoAgendamento(voAgendamento);
			voRegistroConsulta.getVoAgendamento().setIdConsulta(rs.getLong("ID_CONSULTA"));

			voRegistroConsulta.getVoAgendamento().setPaciente(voPaciente);
			voRegistroConsulta.getVoAgendamento().getPaciente().setIdPaciente(rs.getLong("ID_PACIENTE"));
			voRegistroConsulta.getVoAgendamento().getPaciente().setNome(rs.getString("NOME"));
			voRegistroConsulta.getVoAgendamento().getPaciente().setDataNascimento(rs.getTimestamp("DATA_NASCIMENTO"));
			voRegistroConsulta.getVoAgendamento().getPaciente().setAtivo(rs.getBoolean("ATIVO"));

			voRegistroConsulta.getVoAgendamento().setDataConsulta(rs.getTimestamp("DATA_CONSULTA"));

			voRegistroConsulta.setProntuario(rs.getString("PRONTUARIO"));
			voRegistroConsulta.setConsultaFinalizada(rs.getBoolean("FINALIZADO"));
			voRegistroConsulta.setDataFimConsulta(rs.getTimestamp("DATA_FIM_CONSULTA"));

			registrosConsultas.add(voRegistroConsulta);

		}

		dao.desconectar(conn, pstm, rs);

		return registrosConsultas;

	}

	public List<VoRegistroConsulta> consultar(long idConsulta) throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		List<VoRegistroConsulta> registrosConsultas = new ArrayList<VoRegistroConsulta>();
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT");
		sql.append(" RC.ID_CONSULTA");
		sql.append(", RC.PRONTUARIO");
		sql.append(", RC.FINALIZADO");
		sql.append(", RC.DATA_FIM_CONSULTA");
		sql.append(", A.ID_PACIENTE");
		sql.append(", A.DATA_CONSULTA");
		sql.append(", P.NOME");
		sql.append(", P.DATA_NASCIMENTO");
		sql.append(", P.ATIVO");
		sql.append(" FROM");
		sql.append(" REGISTRO_CONSULTA RC");
		sql.append(" JOIN AGENDAMENTO A");
		sql.append(" ON(RC.ID_CONSULTA = A.ID_CONSULTA)");
		sql.append(" JOIN PACIENTE P");
		sql.append(" ON(A.ID_PACIENTE = P.ID_PACIENTE)");
		sql.append(" WHERE");
		sql.append(" RC.ID_CONSULTA = ?");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setLong(1, idConsulta);
		rs = pstm.executeQuery();

		while (rs.next()) {

			VoRegistroConsulta voRegistroConsulta = new VoRegistroConsulta();
			VoAgendamento voAgendamento = new VoAgendamento();
			VoPaciente voPaciente = new VoPaciente();

			voRegistroConsulta.setVoAgendamento(voAgendamento);
			voRegistroConsulta.getVoAgendamento().setIdConsulta(rs.getLong("ID_CONSULTA"));

			voRegistroConsulta.getVoAgendamento().setPaciente(voPaciente);
			voRegistroConsulta.getVoAgendamento().getPaciente().setIdPaciente(rs.getLong("ID_PACIENTE"));
			voRegistroConsulta.getVoAgendamento().getPaciente().setNome(rs.getString("NOME"));
			voRegistroConsulta.getVoAgendamento().getPaciente().setDataNascimento(rs.getTimestamp("DATA_NASCIMENTO"));
			voRegistroConsulta.getVoAgendamento().getPaciente().setAtivo(rs.getBoolean("ATIVO"));

			voRegistroConsulta.getVoAgendamento().setDataConsulta(rs.getTimestamp("DATA_CONSULTA"));

			voRegistroConsulta.setProntuario(rs.getString("PRONTUARIO"));
			voRegistroConsulta.setConsultaFinalizada(rs.getBoolean("FINALIZADO"));
			voRegistroConsulta.setDataFimConsulta(rs.getTimestamp("DATA_FIM_CONSULTA"));

			registrosConsultas.add(voRegistroConsulta);

		}

		dao.desconectar(conn, pstm, rs);

		return registrosConsultas;

	}

	public List<VoRegistroConsulta> consultar(String nome) throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		List<VoRegistroConsulta> registrosConsultas = new ArrayList<VoRegistroConsulta>();
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT");
		sql.append(" RC.ID_CONSULTA");
		sql.append(", RC.PRONTUARIO");
		sql.append(", RC.FINALIZADO");
		sql.append(", RC.DATA_FIM_CONSULTA");
		sql.append(", A.ID_PACIENTE");
		sql.append(", A.DATA_CONSULTA");
		sql.append(", P.NOME");
		sql.append(", P.DATA_NASCIMENTO");
		sql.append(", P.ATIVO");
		sql.append(" FROM");
		sql.append(" REGISTRO_CONSULTA RC");
		sql.append(" JOIN AGENDAMENTO A");
		sql.append(" ON(RC.ID_CONSULTA = A.ID_CONSULTA)");
		sql.append(" JOIN PACIENTE P");
		sql.append(" ON(A.ID_PACIENTE = P.ID_PACIENTE)");
		sql.append(" WHERE");
		sql.append(" P.NOME LIKE ?");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setString(1, "%" + nome + "%");
		rs = pstm.executeQuery();

		while (rs.next()) {

			VoRegistroConsulta voRegistroConsulta = new VoRegistroConsulta();
			VoAgendamento voAgendamento = new VoAgendamento();
			VoPaciente voPaciente = new VoPaciente();

			voRegistroConsulta.setVoAgendamento(voAgendamento);
			voRegistroConsulta.getVoAgendamento().setIdConsulta(rs.getLong("ID_CONSULTA"));

			voRegistroConsulta.getVoAgendamento().setPaciente(voPaciente);
			voRegistroConsulta.getVoAgendamento().getPaciente().setIdPaciente(rs.getLong("ID_PACIENTE"));
			voRegistroConsulta.getVoAgendamento().getPaciente().setNome(rs.getString("NOME"));
			voRegistroConsulta.getVoAgendamento().getPaciente().setDataNascimento(rs.getTimestamp("DATA_NASCIMENTO"));
			voRegistroConsulta.getVoAgendamento().getPaciente().setAtivo(rs.getBoolean("ATIVO"));

			voRegistroConsulta.getVoAgendamento().setDataConsulta(rs.getTimestamp("DATA_CONSULTA"));

			voRegistroConsulta.setProntuario(rs.getString("PRONTUARIO"));
			voRegistroConsulta.setConsultaFinalizada(rs.getBoolean("FINALIZADO"));
			voRegistroConsulta.setDataFimConsulta(rs.getTimestamp("DATA_FIM_CONSULTA"));

			registrosConsultas.add(voRegistroConsulta);

		}

		dao.desconectar(conn, pstm, rs);

		return registrosConsultas;

	}

}
