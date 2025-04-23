package br.misael.clinicamedica.model.vo;

public class VoUsuario {

	private long idUsuario;
	private String login;
	private String password;
	private Boolean ativo;
	
	public VoUsuario() {}

	public void setIdUsuario(long idUsuario, String login, String password, Boolean ativo) {
		this.idUsuario = idUsuario;
		this.login = login;
		this.password = password;
		this.ativo = ativo;
	}
	
	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean isAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
