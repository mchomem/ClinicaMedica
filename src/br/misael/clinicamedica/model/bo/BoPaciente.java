package br.misael.clinicamedica.model.bo;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import br.misael.clinicamedica.model.dao.DaoAgendamento;
import br.misael.clinicamedica.model.vo.VoAgendamento;
import br.misael.clinicamedica.model.vo.VoPaciente;

/**
 * Classe contendo as definições de regras de negócio para entidades do tipo Paciente.
 * 
 * @author Misael C. Homem
 *
 */
public class BoPaciente {

	/**
	 * Construtor padrão.
	 */
	public BoPaciente() {}
	
	/**
	 * Valida a idade de um paciente, intervalo admitido compreende de 0 a 100 anos.
	 * 
	 * @param voPaciente O objeto do tipo paciente.
	 * @throws Exception Disparado quando os critérios da idade não forem atendidos.
	 */
	public void validarIdade(VoPaciente voPaciente) throws Exception {
		
		Timestamp dataNascimento = voPaciente.getDataNascimento();
		java.util.Date dataHoje  = Date.from(Instant.now());
		SimpleDateFormat ano     = new SimpleDateFormat("yyyy");
		int dtInicial            = Integer.parseInt(ano.format(dataHoje));
		int dtFinal              = Integer.parseInt(ano.format(dataNascimento));
		int diferencaAno         = dtInicial - dtFinal;
		
		// TODO avaliar se a mensagem deve mesmo ficar aqui e se deve ser exibida desta forma (throw)
		if(diferencaAno < 0  || diferencaAno > 100) {
			throw new Exception("Idade não válida. A idade deve ser de 0 até 100 anos.");
		}
		
	}
	
	public int existeAgendamento(VoPaciente voPaciente) throws Exception {
		
		DaoAgendamento voAgendamentoDao  = new DaoAgendamento();
		List<VoAgendamento> agendamentos = new ArrayList<VoAgendamento>();
					
		agendamentos = voAgendamentoDao.consultar(voPaciente);
		
		return agendamentos.size();
		
	}
	
}
