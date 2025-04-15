package br.misael.clinicamedica.model.vo;

import java.sql.Timestamp;

/**
 * Classe contendo uma definção para um registro de consnulta efetuado para
 * um pasciente.
 * 
 * @author Misael C. Homem
 *
 */
public class VoRegistroConsulta {
    
    private VoAgendamento voAgendamento;
    private String prontuario;
    private boolean consultaFinalizada;
    private Timestamp dataFimConsulta;

	public VoRegistroConsulta() {}
    
    public VoRegistroConsulta(VoAgendamento voAgendamento, String prontuario, boolean consultaFinalizada) {
    	
    	this.voAgendamento = voAgendamento;
    	this.prontuario = prontuario;
    	this.consultaFinalizada = consultaFinalizada;
    	
    }

	public VoAgendamento getVoAgendamento() {
		return voAgendamento;
	}

	public void setVoAgendamento(VoAgendamento voAgendamento) {
		this.voAgendamento = voAgendamento;
	}

	public String getProntuario() {
		return prontuario;
	}

	public void setProntuario(String prontuario) {
		this.prontuario = prontuario;
	}

	public boolean isConsultaFinalizada() {
		return consultaFinalizada;
	}

	public void setConsultaFinalizada(boolean consultaFinalizada) {
		this.consultaFinalizada = consultaFinalizada;
	}
	
    public Timestamp getDataFimConsulta() {
		return dataFimConsulta;
	}

	public void setDataFimConsulta(Timestamp dataFimConsulta) {
		this.dataFimConsulta = dataFimConsulta;
	}
}
