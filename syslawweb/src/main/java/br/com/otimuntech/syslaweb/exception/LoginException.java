package br.com.otimuntech.syslaweb.exception;

public class LoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8513853159899398078L;
	
	public LoginException(String mensagem, Throwable causa){
		
		super(mensagem,causa);
		
	}

}
