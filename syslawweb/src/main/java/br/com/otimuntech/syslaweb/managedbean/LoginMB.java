package br.com.otimuntech.syslaweb.managedbean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.otimuntech.syslaweb.bo.impl.LoginBOImpl;
import br.com.otimuntech.syslaweb.exception.LoginException;
import br.com.otimuntech.syslaweb.factory.FactoryBean;
import br.com.otimuntech.syslaweb.persistencia.entidade.Usuario;


@Component
@ManagedBean
@SessionScoped
public class LoginMB {
	
	@Autowired
	LoginBOImpl loginBO;
	
	public String login;
	public String senha;
	
	Usuario usuario;
	
	public final String MSG_ACESSO_NAO_PERMITIDO = "Usuário ou Senha inválidos.";
	public final String MSG_PAGINA_NAO_ENCONTRADA = "Página não encontrada.";
	public final String MSG_FALHA_CONSULTA_USUARIO = "Houve falha durante a consulta do usuario na base de dados. Entre em contato com o administrador do sistema";
	
	public void logar(){
		
		usuario = popularUsuarioBean();
		
		try {
			if(!loginBO.isAcessoValido(usuario)){
				exibirMensagem(FacesMessage.SEVERITY_ERROR, MSG_ACESSO_NAO_PERMITIDO);
			}else{
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("principal.faces");
				} catch (IOException e) {				
					exibirMensagem(FacesMessage.SEVERITY_ERROR, MSG_PAGINA_NAO_ENCONTRADA );
				}
			}
		} catch (LoginException e) {
			exibirMensagem(FacesMessage.SEVERITY_ERROR, MSG_FALHA_CONSULTA_USUARIO );
		}
		
	}
	
	private Usuario popularUsuarioBean() {
		Usuario usuario = FactoryBean.createUsuario();
		usuario.setLogin(getLogin());
		usuario.setSenha(getSenha());
		return usuario;
	}


	 /**
     * Metodo responsavel por montar a mensagem que será exibida na tela
     * 
     * @param severidade
     *            - Severidade da mensagem
     * @param msg
     *            - descricao da mensagem
     */
    private void exibirMensagem(FacesMessage.Severity severidade, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severidade, msg, msg));
    }


	/**
	 * @return the loginBO
	 */
	public LoginBOImpl getLoginBO() {
		return loginBO;
	}
	/**
	 * @param loginBO the loginBO to set
	 */
	public void setLoginBO(LoginBOImpl loginBO) {
		this.loginBO = loginBO;
	}

	

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}
	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
