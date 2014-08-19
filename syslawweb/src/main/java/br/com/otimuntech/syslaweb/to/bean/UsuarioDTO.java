package br.com.otimuntech.syslaweb.to.bean;

import java.util.Date;

public class UsuarioDTO {
	
	private String login;
	private String senha;
	private Date dataUltimoAcesso;
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}
	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	/**
	 * @return the dataUltimoAcesso
	 */
	public Date getDataUltimoAcesso() {
		return dataUltimoAcesso;
	}
	/**
	 * @param dataUltimoAcesso the dataUltimoAcesso to set
	 */
	public void setDataUltimoAcesso(Date dataUltimoAcesso) {
		this.dataUltimoAcesso = dataUltimoAcesso;
	}
	
	

}
