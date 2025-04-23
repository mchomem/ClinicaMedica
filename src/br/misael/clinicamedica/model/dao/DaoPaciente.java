package br.misael.clinicamedica.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.misael.clinicamedica.model.vo.VoPaciente;

/**
 * Contém os comandos CRUD para a entidade Paciente.
 * 
 * @author Misael C. Homem
 *
 */
public class DaoPaciente {

	private Dao dao;

	/**
	 * Construtor padrão.
	 */
	public DaoPaciente() {

		this.dao = new Dao();

	}

	/**
	 * Insere um registro da entidade Paciente.
	 * 
	 * @param voPaciente O objeto VoPaciente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void inserir(VoPaciente voPaciente) throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO PACIENTE");
		sql.append("(NOME, DATA_NASCIMENTO, SEXO, ENDERECO, TELEFONE, ATIVO, FOTO)");
		sql.append("VALUES");
		sql.append("(?, ?, ?, ?, ?, ?, ?)");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setString(1, voPaciente.getNome());
		pstm.setTimestamp(2, voPaciente.getDataNascimento());
		pstm.setString(3, voPaciente.getSexo());
		pstm.setString(4, voPaciente.getEndereco());
		pstm.setString(5, voPaciente.getTelefone());
		pstm.setBoolean(6, voPaciente.isAtivo());
		pstm.setObject(7, (voPaciente.getFoto() != null) ? voPaciente.getFoto() : null);

		pstm.executeUpdate();

		dao.desconectar(conn, pstm, null);

	}

	/**
	 * Altera um registro da entidade Paciente selecionado.
	 * 
	 * @param voPaciente O objeto VoPaciente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void alterar(VoPaciente voPaciente) throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE PACIENTE");
		sql.append(" SET NOME = ?");
		sql.append(", DATA_NASCIMENTO = ?");
		sql.append(", SEXO = ?");
		sql.append(", ENDERECO = ?");
		sql.append(", TELEFONE = ?");
		sql.append(", ATIVO = ?");
		sql.append(", FOTO = ?");
		sql.append(" WHERE");
		sql.append(" ID_PACIENTE = ?");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setString(1, voPaciente.getNome());
		pstm.setTimestamp(2, voPaciente.getDataNascimento());
		pstm.setString(3, voPaciente.getSexo());
		pstm.setString(4, voPaciente.getEndereco());
		pstm.setString(5, voPaciente.getTelefone());
		pstm.setBoolean(6, voPaciente.isAtivo());
		pstm.setObject(7, (voPaciente.getFoto() != null ? voPaciente.getFoto() : null));
		pstm.setLong(8, voPaciente.getIdPaciente());

		pstm.executeUpdate();

		dao.desconectar(conn, pstm, null);

	}

	/**
	 * Excluir um registro da entidade Paciente.
	 * 
	 * @param voPaciente O objeto VoPaciente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void excluir(VoPaciente voPaciente) throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		StringBuilder sql = new StringBuilder();

		sql.append("DELETE FROM PACIENTE");
		sql.append(" WHERE");
		sql.append(" ID_PACIENTE = ?");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setLong(1, voPaciente.getIdPaciente());

		pstm.executeUpdate();

		dao.desconectar(conn, pstm, null);

	}

	/**
	 * Retorna todos os registros existentes na tabela Paciente.
	 * 
	 * @return Retorna uma lista populado com objetos VoPaciente.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<VoPaciente> consultar() throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		List<VoPaciente> pacientes = new ArrayList<VoPaciente>();
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT");
		sql.append(" ID_PACIENTE");
		sql.append(", NOME");
		sql.append(", DATA_NASCIMENTO");
		sql.append(", SEXO");
		sql.append(", ENDERECO");
		sql.append(", TELEFONE");
		sql.append(", ATIVO");
		sql.append(", FOTO");
		sql.append(" FROM");
		sql.append(" PACIENTE");
		sql.append(" ORDER BY NOME ASC");

		pstm = conn.prepareStatement(sql.toString());
		rs = pstm.executeQuery();

		while (rs.next()) {

			VoPaciente voPaciente = new VoPaciente();
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

			pacientes.add(voPaciente);

		}

		dao.desconectar(conn, pstm, rs);

		return pacientes;

	}

	/**
	 * Retorna um ou mais registros existentes na tabela Paciente.
	 * 
	 * @param nome O nome do Paciente, que será utilizado como filtro na consulta.
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<VoPaciente> consultar(String nome) throws ClassNotFoundException, SQLException {

		Connection conn = dao.conectar();
		PreparedStatement pstm = null;
		List<VoPaciente> pacientes = new ArrayList<VoPaciente>();
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT");
		sql.append(" ID_PACIENTE");
		sql.append(", NOME");
		sql.append(", DATA_NASCIMENTO");
		sql.append(", SEXO");
		sql.append(", ENDERECO");
		sql.append(", TELEFONE");
		sql.append(", ATIVO");
		sql.append(", FOTO");
		sql.append(" FROM");
		sql.append(" PACIENTE");
		sql.append(" WHERE");
		sql.append(" NOME LIKE ?");
		sql.append(" ORDER BY NOME ASC");

		pstm = conn.prepareStatement(sql.toString());
		pstm.setString(1, "%" + nome + "%");
		rs = pstm.executeQuery();

		while (rs.next()) {

			VoPaciente voPaciente = new VoPaciente();
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

			pacientes.add(voPaciente);

		}

		dao.desconectar(conn, pstm, rs);

		return pacientes;

	}

}
