package br.com.otimuntech.syslawweb.datamodel;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.otimuntech.syslaweb.persistencia.entidade.GrupoUsuario;

public class GrupoUsuarioDataModel extends ListDataModel<GrupoUsuario>  implements SelectableDataModel<GrupoUsuario>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5416179550632736282L;
	
	/**
	 * Construtor GrupoUsuarioDataModel
	 * @param listaGrupos
	 */
	public GrupoUsuarioDataModel(List<GrupoUsuario> listaGrupos) {
		super(listaGrupos);
	}
	
	

	@Override
	public Object getRowKey(GrupoUsuario grupoUsuario) {
		return grupoUsuario.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public GrupoUsuario getRowData(String rowKey) {
		
		List<GrupoUsuario> listaGrupos = (List<GrupoUsuario>) getWrappedData();  
        
        for(GrupoUsuario grupoUsuario : listaGrupos) {  
            if(grupoUsuario.getId() == Integer.parseInt(rowKey))  
                return grupoUsuario;  
        }  
          
        return null;
	}

}
