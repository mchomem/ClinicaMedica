package br.misael.clinicamedica.model.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Classe que define os valores para Agendamentopara uma possível consulta de
 * pasciente.
 * 
 * @author Misael C. Homem
 *
 */
public class VoAgendamento {

	private long idConsulta;
	private VoPaciente paciente;
	private Timestamp dataConsulta;

	public VoAgendamento() {
	}

	public VoAgendamento(long idConsulta, VoPaciente paciente, Timestamp dataConsulta) {

		this.idConsulta = idConsulta;
		this.paciente = paciente;
		this.dataConsulta = dataConsulta;

	}

	public long getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(long idConsulta) {
		this.idConsulta = idConsulta;
	}

	public VoPaciente getPaciente() {
		return paciente;
	}

	public Timestamp getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Timestamp dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public void setPaciente(VoPaciente paciente) {
		this.paciente = paciente;
	}

	@Override
	public String toString() {

		return "Data: " + new SimpleDateFormat("dd/MM/yyyy").format(dataConsulta) + " - Cód: " + idConsulta + " - "
				+ paciente.getNome();

	}

}
