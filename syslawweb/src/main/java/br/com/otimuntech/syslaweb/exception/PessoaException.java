package br.com.otimuntech.syslaweb.exception;

public class PessoaException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3054406590360834097L;

	public PessoaException(String mensagem, Throwable causa){
		
		super(mensagem,causa);
		
	}

}
