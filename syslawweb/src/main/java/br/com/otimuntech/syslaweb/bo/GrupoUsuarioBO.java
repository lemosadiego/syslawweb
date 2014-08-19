package br.com.otimuntech.syslaweb.bo;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.otimuntech.syslaweb.exception.GrupoUsuarioException;
import br.com.otimuntech.syslaweb.persistencia.entidade.GrupoUsuario;

@Component
public interface GrupoUsuarioBO {
	
	public GrupoUsuario getGrupoUsuarioPorId(Long id) throws GrupoUsuarioException;
	@SuppressWarnings("rawtypes")
	public List getListaGrupos() throws GrupoUsuarioException;
	
	public void incluir(GrupoUsuario grupoUsuario) throws GrupoUsuarioException;
	
	public void alterar(GrupoUsuario grupoUsuario) throws GrupoUsuarioException;
	
	public void excluir(GrupoUsuario grupoUsuario) throws GrupoUsuarioException;
	
	public boolean isRegistroJaExiste(GrupoUsuario grupoUsuario) throws GrupoUsuarioException;
	
}
