package br.com.otimuntech.syslaweb.persistencia.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Repository
public class GenericDAO<T> {
	
	private Log LOG = LogFactory.getLog(GenericDAO.class);
	
	@Autowired
	@Qualifier(value = "sessionFactory")
	private SessionFactory sessionFactory;
	

	public void incluir(T objeto) {
		
		Session session =  getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			session.save(objeto);
			transaction.commit();
		}catch(HibernateException e){
			LOG.error("Falha durante processo de inclusao. Mensagem :" + e.getMessage(), e);
			transaction.rollback();
			throw new HibernateException(e.getMessage());
		}finally{
			session.close();
		}
	}
	
	public void alterar(T objeto) {
		
		Session session =  getSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			session.save(objeto);
			transaction.commit();
		}catch(HibernateException e){
			LOG.error("Falha durante processo de alteração. Mensagem :" + e.getMessage(), e);
			transaction.rollback();
			throw new HibernateException(e.getMessage());
		}finally{
			session.close();
		}
	}
	
	public void excluir(T objeto) {

			Session session =  getSession();
			Transaction transaction = session.beginTransaction();
		try{			
			session.delete(objeto);
			transaction.commit();
		}catch(HibernateException e){
			LOG.error("Falha durante processo de alteração. Mensagem :" + e.getMessage(), e);
			transaction.rollback();
			throw new HibernateException(e.getMessage());
		}finally{
			session.close();
		}
		
	}

	public Session getSession() {	
		return sessionFactory.openSession();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
	}
	
}

