package br.com.otimuntech.syslaweb.bo;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.otimuntech.syslaweb.exception.PessoaException;
import br.com.otimuntech.syslaweb.persistencia.entidade.Pessoa;

@Component
public interface PessoaBO {
	
	public Pessoa getPessoaPorId(Long id) throws PessoaException;
	@SuppressWarnings("rawtypes")
	public List getListaPessoas() throws PessoaException;
	
	public void incluir(Pessoa pessoa) throws PessoaException;
	
	public void alterar(Pessoa pessoa) throws PessoaException;
	
	public void excluir(Pessoa pessoa) throws PessoaException;
	
	public boolean isRegistroJaExiste(Pessoa pessoa) throws PessoaException;
	
}
