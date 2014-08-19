package br.com.otimuntech.syslaweb.persistencia.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.otimuntech.syslaweb.exception.LoginException;
import br.com.otimuntech.syslaweb.persistencia.entidade.Usuario;

@Repository
@Transactional
public class LoginDAO extends GenericDAO<Usuario>{
	
	
	public Usuario getUsuarioPorLogin(Usuario usuario) throws LoginException{
		
		try{
			getSession().getTransaction().begin();
			Criteria criteria = getSession().createCriteria(Usuario.class);
			criteria.add(Restrictions.eq("login",usuario.getLogin()));
			criteria.add(Restrictions.eq("senha",usuario.getSenha()));
			@SuppressWarnings("unchecked")
			List<Usuario> listaUsuarios = criteria.list();
			getSession().getTransaction().commit();
			if(!listaUsuarios.isEmpty()){
				return listaUsuarios.get(0);
			}else{
				return null;
			}
		}catch(Exception e){
			throw new LoginException("Falha ao consultar usuario atraves do Login:[" + usuario.toString() +"]",e.getCause());
		}
		
	}

}
