package br.misael.clinicamedica.model.vo;

public class VoReceituario {
	
	private VoAgendamento voAgendamento;
	private VoMedicamento voMedicamento;
	private String posologia;
	
	public VoReceituario() {}
	
	public VoReceituario(VoAgendamento voAgendamento, VoMedicamento voMedicamento, String posologia) {
		
		this.voAgendamento = voAgendamento;
		this.voMedicamento = voMedicamento;
		this.posologia = posologia;
		
	}

	public VoAgendamento getVoAgendamento() {
		return voAgendamento;
	}

	public void setVoAgendamento(VoAgendamento voAgendamento) {
		this.voAgendamento = voAgendamento;
	}

	public VoMedicamento getVoMedicamento() {
		return voMedicamento;
	}

	public void setVoMedicamento(VoMedicamento voMedicamento) {
		this.voMedicamento = voMedicamento;
	}

	public String getPosologia() {
		return posologia;
	}

	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}

}
