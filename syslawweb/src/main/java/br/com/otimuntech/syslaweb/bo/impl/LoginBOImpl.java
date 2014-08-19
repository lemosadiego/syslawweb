package br.com.otimuntech.syslaweb.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.otimuntech.syslaweb.bo.LoginBO;
import br.com.otimuntech.syslaweb.exception.LoginException;
import br.com.otimuntech.syslaweb.persistencia.dao.LoginDAO;
import br.com.otimuntech.syslaweb.persistencia.entidade.Usuario;

@Component
public class LoginBOImpl implements LoginBO {

	@Autowired
	LoginDAO loginDAO;
	
	
	
	@Override
	public boolean isAcessoValido(Usuario usuario) throws LoginException {
		
		Usuario usuarioRetorno = getLoginDAO().getUsuarioPorLogin(usuario);
		
		if(usuarioRetorno != null){
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}



	/**
	 * @return the loginDAO
	 */
	public LoginDAO getLoginDAO() {
		return loginDAO;
	}



	/**
	 * @param loginDAO the loginDAO to set
	 */
	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}
	
	

}
