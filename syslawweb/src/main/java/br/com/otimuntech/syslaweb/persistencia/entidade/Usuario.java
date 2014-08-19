package br.com.otimuntech.syslaweb.persistencia.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;



@Entity(name="USUARIOS")
public class Usuario {
	
	@Id
	@Column(name="IDUSUARIOS")
	private Long id;
	
	private String login;
	
	private String senha;
	
	@OneToOne
	private Pessoa pessoa;
	
	private Integer bloqueado;
	
	
	@Column(name="ACESSOS_INDEVIDOS")
	private Integer acessosIndevidos;


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


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
	 * @return the pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}


	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	/**
	 * @return the bloqueado
	 */
	public Integer getBloqueado() {
		return bloqueado;
	}


	/**
	 * @param bloqueado the bloqueado to set
	 */
	public void setBloqueado(Integer bloqueado) {
		this.bloqueado = bloqueado;
	}


	/**
	 * @return the acessosIndevidos
	 */
	public Integer getAcessosIndevidos() {
		return acessosIndevidos;
	}


	/**
	 * @param acessosIndevidos the acessosIndevidos to set
	 */
	public void setAcessosIndevidos(Integer acessosIndevidos) {
		this.acessosIndevidos = acessosIndevidos;
	}
	
	
	

}
