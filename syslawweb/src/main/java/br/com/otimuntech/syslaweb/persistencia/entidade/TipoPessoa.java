package br.com.otimuntech.syslaweb.persistencia.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="TIPOPESSOA")
public class TipoPessoa {
	
	@Id @GeneratedValue
	@Column(name="IDTIPO_PESSOA")
	private Long id;
	
	private String descricao;
	
	@Column(name="FUNCIONARIO")
	private byte colaborador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public byte getColaborador() {
		return colaborador;
	}

	public void setColaborador(byte colaborador) {
		this.colaborador = colaborador;
	}

	@Override
	public String toString() {
		return "TipoPessoa [id=" + id + ", descricao=" + descricao
				+ ", colaborador=" + colaborador + "]";
	}

}
