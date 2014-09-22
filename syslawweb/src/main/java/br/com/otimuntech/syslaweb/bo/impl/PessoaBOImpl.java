package br.com.otimuntech.syslaweb.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.otimuntech.syslaweb.bo.PessoaBO;
import br.com.otimuntech.syslaweb.exception.PessoaException;
import br.com.otimuntech.syslaweb.persistencia.dao.PessoaDAO;
import br.com.otimuntech.syslaweb.persistencia.entidade.Pessoa;

@Component
public class PessoaBOImpl implements PessoaBO {

	@Autowired
	PessoaDAO pessoaDAO;
	
	
	@Override
	public Pessoa getPessoaPorId(Long id) throws PessoaException {
		
		return pessoaDAO.getPessoaPorId(id);
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getListaPessoas() throws PessoaException {
		return getPessoaDAO().getListaPessoas();
	}

	@Override
	public void incluir(Pessoa pessoa) throws PessoaException {
		getPessoaDAO().incluir(pessoa);
		
	}

	@Override
	public void alterar(Pessoa pessoa) throws PessoaException {
		getPessoaDAO().alterar(pessoa);
		
	}

	@Override
	public void excluir(Pessoa pessoa) throws PessoaException {
		getPessoaDAO().excluir(pessoa);
		
	}
	
	@Override
	public boolean isRegistroJaExiste(Pessoa pessoa) throws PessoaException {
		return getPessoaDAO().isRegistroJaExiste(pessoa);
		
	}

	public PessoaDAO getPessoaDAO() {
		return pessoaDAO;
	}

	public void setPessoaDAO(PessoaDAO pessoaDAO) {
		this.pessoaDAO = pessoaDAO;
	}
	
	

}
