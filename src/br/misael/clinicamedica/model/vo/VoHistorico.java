package br.misael.clinicamedica.model.vo;

/**
 * Classe que cont�m a defini��o de dados para Hist�rico de Pacientes
 * que efetuaram um agendamento finalizando a consulta.
 * 
 * @author Misael C. Homem
 *
 */
public class VoHistorico {
    
	private VoPaciente voPaciente;
	private VoAgendamento voAgendamento;
	private VoRegistroConsulta voRegistroConsulta;
	
	public VoHistorico() {}
	
	public VoHistorico(VoPaciente voPaciente, VoAgendamento voAgendamento, VoRegistroConsulta voRegistroConsulta) {
		
		this.voPaciente = voPaciente;
		this.voAgendamento = voAgendamento;
		this.voRegistroConsulta = voRegistroConsulta;
		
	}

	public VoPaciente getVoPaciente() {
		return voPaciente;
	}

	public void setVoPaciente(VoPaciente voPaciente) {
		this.voPaciente = voPaciente;
	}

	public VoAgendamento getVoAgendamento() {
		return voAgendamento;
	}

	public void setVoAgendamento(VoAgendamento voAgendamento) {
		this.voAgendamento = voAgendamento;
	}

	public VoRegistroConsulta getVoRegistroConsulta() {
		return voRegistroConsulta;
	}

	public void setVoRegistroConsulta(VoRegistroConsulta voRegistroConsulta) {
		this.voRegistroConsulta = voRegistroConsulta;
	}
	
}
