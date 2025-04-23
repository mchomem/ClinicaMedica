package br.misael.clinicamedica.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.misael.clinicamedica.model.vo.VoAgendamento;
import br.misael.clinicamedica.model.vo.VoMedicamento;
import br.misael.clinicamedica.model.vo.VoPaciente;
import br.misael.clinicamedica.model.vo.VoReceituario;

public class DaoReceituario {

	private Dao dao;

	public DaoReceituario() {

		this.dao = new Dao();

	}

	public void inserir(VoReceituario voReceituario) throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO RECEITUARIO");
		sql.append("(ID_CONSULTA, ID_MEDICAMENTO, POSOLOGIA)");
		sql.append("VALUES");
		sql.append("(?, ?, ?)");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setLong(1, voReceituario.getVoAgendamento().getIdConsulta());
		pstm.setLong(2, voReceituario.getVoMedicamento().getIdMedicamento());
		pstm.setString(3, voReceituario.getPosologia());

		pstm.executeUpdate();

		dao.desconectar(conn, pstm, null);

	}

	public void alterar(VoReceituario voReceituario) throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE RECEITUARIO");
		sql.append(" SET POSOLOGIA = ?");
		sql.append(" WHERE");
		sql.append(" ID_CONSULTA = ?");
		sql.append(" AND ID_MEDICAMENTO = ?");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setString(1, voReceituario.getPosologia());
		pstm.setLong(2, voReceituario.getVoAgendamento().getIdConsulta());
		pstm.setLong(3, voReceituario.getVoMedicamento().getIdMedicamento());

		pstm.executeUpdate();

		dao.desconectar(conn, pstm, null);

	}

	public void excluir(VoReceituario voReceituario) throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		StringBuilder sql = new StringBuilder();

		sql.append("DELETE FROM RECEITUARIO");
		sql.append(" WHERE");
		sql.append(" ID_CONSULTA = ?");
		sql.append(" AND ID_MEDICAMENTO = ?");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setLong(1, voReceituario.getVoAgendamento().getIdConsulta());
		pstm.setLong(2, voReceituario.getVoMedicamento().getIdMedicamento());

		pstm.executeUpdate();

		dao.desconectar(conn, pstm, null);

	}

	public List<VoReceituario> consultar(VoAgendamento pVoAgendamento, VoMedicamento pVoMedicamento)
			throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		List<VoReceituario> receituarios = new ArrayList<VoReceituario>();
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT");
		sql.append(" R.ID_CONSULTA");
		sql.append(", P.ID_PACIENTE");
		sql.append(", P.NOME");
		sql.append(", P.DATA_NASCIMENTO");
		sql.append(", P.SEXO");
		sql.append(", P.ENDERECO");
		sql.append(", P.TELEFONE");
		sql.append(", P.ATIVO");
		sql.append(", P.FOTO");
		sql.append(", A.DATA_CONSULTA");
		sql.append(", R.ID_MEDICAMENTO");
		sql.append(", M.NOME");
		sql.append(", R.POSOLOGIA");
		sql.append(" FROM");
		sql.append(" RECEITUARIO R");
		sql.append(" JOIN AGENDAMENTO A ON (A.ID_CONSULTA = R.ID_CONSULTA)");
		sql.append(" JOIN PACIENTE P ON (P.ID_PACIENTE = A.ID_PACIENTE)");
		sql.append(" JOIN MEDICAMENTO M ON (M.ID_MEDICAMENTO = R.ID_MEDICAMENTO)");
		sql.append(" WHERE");
		sql.append(" R.ID_CONSULTA = ?");
		sql.append(" AND R.ID_MEDICAMENTO = ?");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setLong(1, pVoAgendamento.getIdConsulta());
		pstm.setLong(2, pVoMedicamento.getIdMedicamento());

		rs = pstm.executeQuery();

		while (rs.next()) {

			VoReceituario voReceituario = new VoReceituario();
			VoAgendamento voAgendamento = new VoAgendamento();
			VoPaciente voPaciente = new VoPaciente();
			VoMedicamento voMedicamento = new VoMedicamento();

			voAgendamento.setIdConsulta(rs.getLong("ID_CONSULTA"));

			voPaciente.setIdPaciente(rs.getLong("ID_PACIENTE"));
			voPaciente.setNome(rs.getString("NOME"));
			voPaciente.setDataNascimento(rs.getTimestamp("DATA_NASCIMENTO"));
			voPaciente.setSexo(rs.getString("SEXO"));
			voPaciente.setEndereco(rs.getString("ENDERECO"));
			voPaciente.setTelefone(rs.getString("TELEFONE"));
			voPaciente.setAtivo(rs.getBoolean("ATIVO"));
			voPaciente.setFoto(
					(rs.getBlob("FOTO") != null) ? rs.getBlob("FOTO").getBytes(1, (int) rs.getBlob("FOTO").length())
							: null);

			voAgendamento.setPaciente(voPaciente);
			voAgendamento.setDataConsulta(rs.getTimestamp("DATA_CONSULTA"));

			voMedicamento.setIdMedicamento(rs.getLong("ID_MEDICAMENTO"));
			voMedicamento.setNome(rs.getString("NOME"));

			voReceituario.setVoAgendamento(voAgendamento);
			voReceituario.setPosologia(rs.getString("POSOLOGIA"));

			receituarios.add(voReceituario);

		}

		dao.desconectar(conn, pstm, rs);

		return receituarios;

	}

}
