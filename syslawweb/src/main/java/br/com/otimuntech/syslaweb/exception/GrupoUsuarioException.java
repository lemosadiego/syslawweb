package br.com.otimuntech.syslaweb.exception;

public class GrupoUsuarioException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8513853159899398078L;
	
	public GrupoUsuarioException(String mensagem, Throwable causa){
		
		super(mensagem,causa);
		
	}

}
