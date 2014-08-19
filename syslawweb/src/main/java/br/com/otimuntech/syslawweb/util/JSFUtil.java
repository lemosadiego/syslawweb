package br.com.otimuntech.syslawweb.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {
	
	 /**
     * Metodo responsavel por montar a mensagem que será exibida na tela
     * 
     * @param severidade
     *            - Severidade da mensagem
     * @param msg
     *            - descricao da mensagem
     */
	public static void exibirMensagem(FacesMessage.Severity severidade, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severidade, msg, msg));
    }

}
