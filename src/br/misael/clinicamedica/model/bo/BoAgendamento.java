package br.misael.clinicamedica.model.bo;

import br.misael.clinicamedica.model.vo.VoPaciente;

public class BoAgendamento {

	/**
	 * Construtor padr�o.
	 */
	public BoAgendamento( ) {}
	
	/**
	 * Verifica se o cadastro do paciente est� ativo.s
	 * @param voPaciente - Um objeto do tipo paciente.
	 * @return True se o paciente est� com o cadastro ativo.
	 */
	public Boolean verificarPacienteAtivo(VoPaciente voPaciente) {
		
		return voPaciente.isAtivo();
		
	}
	
}
