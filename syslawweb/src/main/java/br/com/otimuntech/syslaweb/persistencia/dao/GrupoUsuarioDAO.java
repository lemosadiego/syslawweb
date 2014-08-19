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

import br.com.otimuntech.syslaweb.exception.GrupoUsuarioException;
import br.com.otimuntech.syslaweb.persistencia.entidade.GrupoUsuario;

@Repository
@Transactional
public class GrupoUsuarioDAO extends GenericDAO<GrupoUsuario> {

	private Log LOG = LogFactory.getLog(GrupoUsuarioDAO.class);

	public GrupoUsuario getGrupoUsuarioPorId(Long id) throws GrupoUsuarioException{	

		Session session = getSession(); 
		Transaction transaction = session.beginTransaction();
		try{
			
			Criteria criteria = session.createCriteria(GrupoUsuario.class);
			criteria.add(Restrictions.eq("id",id));
			@SuppressWarnings("unchecked")
			List<GrupoUsuario> listaGruposUsuarios = criteria.list();
			transaction.commit();
			if(!listaGruposUsuarios.isEmpty()){
				return listaGruposUsuarios.get(0);
			}else{
				return null;
			}
		}catch(Exception e){
			LOG.error("Falha ao consultar Grupo de Usuario atraves do Codigo:[" + id +"]", e);
			transaction.rollback();
			throw new GrupoUsuarioException("Falha ao consultar Grupo de Usuario atraves do Codigo:[" + id +"]",e.getCause());
		}finally{
			session.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<GrupoUsuario> getListaGrupos() throws GrupoUsuarioException{	

		Session session = getSession(); 
		Transaction transaction = session.beginTransaction();
		try{
			Criteria criteria = session.createCriteria(GrupoUsuario.class);
			transaction.commit();
			return criteria.list();
		}catch(Exception e){
			LOG.error("Falha ao consultar Lista de Grupos de Usuarios", e);
			transaction.rollback();
			throw new GrupoUsuarioException("Falha ao consultar Lista de Grupos de Usuarios",e.getCause());
		}finally{
				session.close();
		}

	}
	
	public boolean isRegistroJaExiste(GrupoUsuario grupoUsuario) throws GrupoUsuarioException {
		
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try{
			transaction.begin();
			Criteria criteria = session.createCriteria(GrupoUsuario.class);
			criteria.add(Restrictions.eq("DESCRICAO",grupoUsuario.getDescricao()));
			transaction.commit();
			return criteria.list().isEmpty();
		}catch(Exception e){
			LOG.error("Falha ao consultar Grupo de Usuario pela Descricao [" + grupoUsuario.getDescricao() + "]", e);
			transaction.rollback();
			throw new GrupoUsuarioException("Falha ao consultar Grupo de Usuario pela Descricao [" + grupoUsuario.getDescricao() + "]",e.getCause());
		}finally{
			session.close();
		}
		
	}

}
