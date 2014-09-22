package br.com.otimuntech.syslawweb.datamodel;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.otimuntech.syslaweb.persistencia.entidade.Pessoa;

public class PessoaDataModel extends ListDataModel<Pessoa>  implements SelectableDataModel<Pessoa>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5416179550632736282L;
	
	/**
	 * Construtor GrupoUsuarioDataModel
	 * @param listaGrupos
	 */
	public PessoaDataModel(List<Pessoa> listaPessoas) {
		super(listaPessoas);
	}
	
	

	@Override
	public Object getRowKey(Pessoa pessoa) {
		return pessoa.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pessoa getRowData(String rowKey) {
		
		List<Pessoa> listaPessoas = (List<Pessoa>) getWrappedData();  
        
        for(Pessoa pessoa : listaPessoas) {  
            if(pessoa.getId() == Integer.parseInt(rowKey))  
                return pessoa;  
        }  
          
        return null;
	}

}
