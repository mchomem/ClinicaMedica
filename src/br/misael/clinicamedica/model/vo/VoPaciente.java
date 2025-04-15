package br.misael.clinicamedica.model.vo;

import java.sql.Timestamp;

/**
 * Classe contendo a definição para um Paciente.
 * 
 * @author Misael C. Homem
 *
 */
public class VoPaciente {

    private long idPaciente;
    private String nome;
    private Timestamp dataNascimento;
    private String sexo;
    private String endereco;
    private String telefone;
    private boolean ativo;
    private byte[] foto;

    public VoPaciente() {}

    public VoPaciente(long idPaciente, String nome, Timestamp dataNascimento, String sexo, String endereco, String telefone, boolean ativo, byte[] foto) {

        this.idPaciente     = idPaciente;
        this.nome           = nome;
        this.dataNascimento = dataNascimento;
        this.sexo           = sexo;
        this.endereco       = endereco;
        this.telefone       = telefone;
        this.ativo          = ativo;
        this.foto           = foto;

    }

    public long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Timestamp getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Timestamp dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public byte[] getFoto() {
		return foto;
	}
	
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	@Override
    public String toString() {
    	return nome;
    }

}