package br.com.otimuntech.syslaweb.persistencia.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.otimuntech.syslaweb.exception.PessoaException;
import br.com.otimuntech.syslaweb.persistencia.entidade.Pessoa;

@Repository
@Transactional
public class PessoaDAO extends GenericDAO<Pessoa> {

	private Log LOG = LogFactory.getLog(PessoaDAO.class);

	public Pessoa getPessoaPorId(Long id) throws PessoaException{	

		Session session = getSession(); 
		Transaction transaction = session.beginTransaction();
		try{
			
			Criteria criteria = session.createCriteria(Pessoa.class);
			criteria.add(Restrictions.eq("id",id));
			@SuppressWarnings("unchecked")
			List<Pessoa> listaPessoas = criteria.list();
			transaction.commit();
			if(!listaPessoas.isEmpty()){
				return listaPessoas.get(0);
			}else{
				return null;
			}
		}catch(Exception e){
			LOG.error("Falha ao consultar Pessoa atraves do Codigo:[" + id +"]", e);
			transaction.rollback();
			throw new PessoaException("Falha ao consultar Pessoa atraves do Codigo:[" + id +"]",e.getCause());
		}finally{
			session.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> getListaPessoas() throws PessoaException{	

		Session session = getSession(); 
		Transaction transaction = session.beginTransaction();
		try{
			Criteria criteria = session.createCriteria(Pessoa.class);
			transaction.commit();
			return criteria.list();
		}catch(Exception e){
			LOG.error("Falha ao consultar Lista de Pessoas", e);
			transaction.rollback();
			throw new PessoaException("Falha ao consultar Lista de Pessoas",e.getCause());
		}finally{
				session.close();
		}

	}
	
	public boolean isRegistroJaExiste(Pessoa pessoa) throws PessoaException {
		
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteriaNome;
		Criteria criteriaCPF;
		Criteria criteriaCNPJ;
		try{
			criteriaNome = session.createCriteria(Pessoa.class);
			criteriaNome.add(Restrictions.eq("nome",pessoa.getNome()));
			transaction.commit();
			
			if(criteriaNome.list().isEmpty()){
				criteriaCPF = session.createCriteria(Pessoa.class);
				criteriaCPF.add(Restrictions.eq("cpf",pessoa.getCpf()));
				transaction.commit();
			}else{
				return Boolean.TRUE;
			}
			
			
			if(criteriaCPF.list().isEmpty()){
				criteriaCNPJ = session.createCriteria(Pessoa.class);
				criteriaCNPJ.add(Restrictions.eq("cnpj",pessoa.getCpf()));
				transaction.commit();
			}else{
				return Boolean.TRUE;
			}
			
			
			if(criteriaCNPJ.list().isEmpty()){
				return Boolean.FALSE;
			}else{
				return Boolean.TRUE;
			}
			
		}catch(Exception e){
			LOG.error("Falha ao consultar Pessoa [" + pessoa.toString() + "]", e);
			transaction.rollback();
			throw new PessoaException("Falha ao consultar Pessoa [" + pessoa.toString() + "]",e.getCause());
		}finally{
			session.close();
		}
		
	}

}
