package br.misael.clinicamedica.model.bo;

import br.misael.clinicamedica.model.vo.VoPaciente;

public class BoAgendamento {

	/**
	 * Construtor padrão.
	 */
	public BoAgendamento( ) {}
	
	/**
	 * Verifica se o cadastro do paciente está ativo.s
	 * @param voPaciente - Um objeto do tipo paciente.
	 * @return True se o paciente está com o cadastro ativo.
	 */
	public Boolean verificarPacienteAtivo(VoPaciente voPaciente) {
		
		return voPaciente.isAtivo();
		
	}
	
}
