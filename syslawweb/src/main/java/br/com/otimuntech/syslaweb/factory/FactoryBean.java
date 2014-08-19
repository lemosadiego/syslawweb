package br.com.otimuntech.syslaweb.factory;

import br.com.otimuntech.syslaweb.persistencia.entidade.Usuario;

public class FactoryBean {

	public static Usuario createUsuario(){
		return new Usuario();
	}
	
	
}
