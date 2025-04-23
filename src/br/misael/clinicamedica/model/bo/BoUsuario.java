package br.misael.clinicamedica.model.bo;

import br.misael.clinicamedica.model.vo.VoUsuario;

public class BoUsuario {
	
	public BoUsuario() {}
	
	public Boolean verificarUsuarioAtivo(VoUsuario voUsuario) {
		return voUsuario.isAtivo();
	}
}
