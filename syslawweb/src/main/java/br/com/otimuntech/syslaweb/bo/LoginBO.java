package br.com.otimuntech.syslaweb.bo;

import org.springframework.stereotype.Component;

import br.com.otimuntech.syslaweb.exception.LoginException;
import br.com.otimuntech.syslaweb.persistencia.entidade.Usuario;

@Component
public interface LoginBO {
	
	public boolean isAcessoValido(Usuario usuario) throws LoginException;
	
}
