package br.misael.clinicamedica.model;

/**
 * Enumera��o para os tipos de m�scaras de campos.
 * 
 * @author Misael C. Homem
 * 
 */
public enum MascaraCampo {

	DATA
	, HORA
	, DATAHORA
	, CPF
	, CNPJ
	, RG
	, TELEFONE;
	
	public String getValor() {
		
		switch(this) {
		
			case DATA:
				return "##/##/####";
				
			case HORA:
				return "##:##:##";
			
			case DATAHORA:
				return "##/##/#### ##:##:##";
				
			case CPF:
				return "##.###.###-##";
				
			case CNPJ:
				return "##.###.###/####-##";
				
			case TELEFONE:
				return "(##) ####-####";
			
			default:
				return "";
				
		}
	}
	
}
