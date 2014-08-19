package br.com.otimuntech.syslaweb.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.otimuntech.syslaweb.bo.GrupoUsuarioBO;
import br.com.otimuntech.syslaweb.exception.GrupoUsuarioException;
import br.com.otimuntech.syslaweb.persistencia.dao.GrupoUsuarioDAO;
import br.com.otimuntech.syslaweb.persistencia.entidade.GrupoUsuario;

@Component
public class GrupoUsuarioBOImpl implements GrupoUsuarioBO {

	@Autowired
	GrupoUsuarioDAO grupoUsuarioDAO;
	
	
	@Override
	public GrupoUsuario getGrupoUsuarioPorId(Long id) throws GrupoUsuarioException {
		
		return grupoUsuarioDAO.getGrupoUsuarioPorId(id);
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getListaGrupos() throws GrupoUsuarioException {
		return grupoUsuarioDAO.getListaGrupos();
	}

	@Override
	public void incluir(GrupoUsuario grupoUsuario) throws GrupoUsuarioException {
		grupoUsuarioDAO.incluir(grupoUsuario);
		
	}

	@Override
	public void alterar(GrupoUsuario grupoUsuario) throws GrupoUsuarioException {
		grupoUsuarioDAO.alterar(grupoUsuario);
		
	}

	@Override
	public void excluir(GrupoUsuario grupoUsuario) throws GrupoUsuarioException {
		grupoUsuarioDAO.excluir(grupoUsuario);
		
	}
	
	@Override
	public boolean isRegistroJaExiste(GrupoUsuario grupoUsuario) throws GrupoUsuarioException {
		return grupoUsuarioDAO.isRegistroJaExiste(grupoUsuario);
		
	}
	
	
	/**
	 * @return the grupoUsuarioDAO
	 */
	public GrupoUsuarioDAO getGrupoUsuarioDAO() {
		return grupoUsuarioDAO;
	}

	/**
	 * @param grupoUsuarioDAO the grupoUsuarioDAO to set
	 */
	public void setGrupoUsuarioDAO(GrupoUsuarioDAO grupoUsuarioDAO) {
		this.grupoUsuarioDAO = grupoUsuarioDAO;
	}

	

}
