package br.misael.clinicamedica.model.vo;

/**
 * Classe que define os valores para medicamento.
 * 
 * @author Misael C. Homem
 *
 */
public class VoMedicamento {
    
    private long idMedicamento;
    private String nome;
    
    public VoMedicamento() {}
    
    public VoMedicamento(long idMedicamento, String nome) {        
        
        this.idMedicamento = idMedicamento;
        this.nome = nome;
        
    }

    public long getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(long idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
    	
    	return this.nome;
    	
    }
    
}
