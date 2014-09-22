package br.com.otimuntech.syslaweb.persistencia.entidade;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name="PESSOAS")
public class Pessoa {
	
	@Id @GeneratedValue
	@Column(name="IDPESSOA")
	private Long id;
	
	private String nome;
	
	private String cpf;
	
	private String cnpj;
	
	private String oab;
	
	private String logradouro;
	
	private String bairro;
	
	private String cidade;
	
	private String estado;
	
	private String cep;
	
	private String telefoneCasa;
	
	private String telefoneTrabalho;
	
	private String celular;
	
	private String email;
	
	@OneToOne(targetEntity=TipoPessoa.class, cascade=CascadeType.REFRESH)
	@JoinColumn(name="IDTIPO_PESSOA")
	private TipoPessoa tipoPessoa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getOab() {
		return oab;
	}

	public void setOab(String oab) {
		this.oab = oab;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefoneCasa() {
		return telefoneCasa;
	}

	public void setTelefoneCasa(String telefoneCasa) {
		this.telefoneCasa = telefoneCasa;
	}

	public String getTelefoneTrabalho() {
		return telefoneTrabalho;
	}

	public void setTelefoneTrabalho(String telefoneTrabalho) {
		this.telefoneTrabalho = telefoneTrabalho;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", cpf=" + cpf
				+ ", cnpj=" + cnpj + ", oab=" + oab + ", logradouro="
				+ logradouro + ", bairro=" + bairro + ", cidade=" + cidade
				+ ", estado=" + estado + ", cep=" + cep + ", telefoneCasa="
				+ telefoneCasa + ", telefoneTrabalho=" + telefoneTrabalho
				+ ", celular=" + celular + ", email=" + email + ", tipoPessoa="
				+ tipoPessoa + "]";
	}
	
	
	
	
	

}
