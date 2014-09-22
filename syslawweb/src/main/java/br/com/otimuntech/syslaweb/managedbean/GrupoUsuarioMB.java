package br.com.otimuntech.syslaweb.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.otimuntech.syslaweb.bo.GrupoUsuarioBO;
import br.com.otimuntech.syslaweb.exception.GrupoUsuarioException;
import br.com.otimuntech.syslaweb.persistencia.entidade.GrupoUsuario;
import br.com.otimuntech.syslawweb.datamodel.GrupoUsuarioDataModel;
import br.com.otimuntech.syslawweb.util.Constantes;
import br.com.otimuntech.syslawweb.util.JSFUtil;


@Component
@ManagedBean
@RequestScoped
public class GrupoUsuarioMB {

	private Log LOG = LogFactory.getLog(GrupoUsuarioMB.class);

	@Autowired
	GrupoUsuarioBO grupoUsuarioBO;

	public Long id;
	public String descricao;

	GrupoUsuario grupoUsuarioDTO;

	GrupoUsuario grupoEscolhido;

	GrupoUsuarioDataModel grupoUsuarioDataModel;

	List<GrupoUsuario> listaGrupos = new ArrayList<>();


	public final String MSG_ERRO_LISTANDO_GRUPOS = "Falha ao consultar lista de grupos de usuarios na base de dados. Entre em contato com o administrador do sistema.";
	public final String MSG_CMP_DESCRICAO_INVALIDO = "O campo Descrição deve ser informado";


	public void novo(){
		limparCampos();
		RequestContext.getCurrentInstance().execute("PF('grupoUsuarioDialogInclusao').show();");

	}

	public void carregarItemSelecionado(){
		
		if(grupoUsuarioDTO != null){
			RequestContext.getCurrentInstance().execute("PF('grupoUsuarioDialogAlteracao').show();");
		}else{
			JSFUtil.exibirMensagem(FacesMessage.SEVERITY_WARN,"Atenção: É necessário selecionar um Grupo de Usuário para a alteração");
		}
		
	}

	
	

	private boolean isFormularioValido(GrupoUsuario grupoUsuario) {

			if(grupoUsuario.getDescricao() == null || grupoUsuario.getDescricao().isEmpty()){
				JSFUtil.exibirMensagem(FacesMessage.SEVERITY_ERROR, MSG_CMP_DESCRICAO_INVALIDO);
				return Boolean.FALSE;
			}

		return Boolean.TRUE;
	}

	private GrupoUsuario montarTO() {
		GrupoUsuario grupoUsuario = new GrupoUsuario();
		
		grupoUsuario.setDescricao(descricao.toUpperCase());
		grupoUsuario.setId(id);
		
		return grupoUsuario;
	}

	
	/**
	 * Metodo responsavel por incluir um Grupo de Usuario
	 */
	public void incluir(){

		grupoUsuarioDTO = montarTO();
		
		if(isFormularioValido(grupoUsuarioDTO)){
			
			try {
				if(!grupoUsuarioBO.isRegistroJaExiste(grupoUsuarioDTO)){
					grupoUsuarioBO.incluir(grupoUsuarioDTO);
					JSFUtil.exibirMensagem(FacesMessage.SEVERITY_INFO,Constantes.MSG_OPERACAO_REALIZADA_SUCESSO);
					RequestContext.getCurrentInstance().execute("PF('grupoUsuarioDialogInclusao').hide();");
				}else{
					JSFUtil.exibirMensagem(FacesMessage.SEVERITY_WARN,"Atenção: Já existe um Grupo de Usuario com a mesma descrição na base dados");
				}
			} catch (GrupoUsuarioException e) {
				JSFUtil.exibirMensagem(FacesMessage.SEVERITY_ERROR,"Falha ao incluir Grupo de Usuario [" + grupoUsuarioDTO.toString() + "]");
				LOG.error("Falha ao incluir Grupo de Usuario [" + grupoUsuarioDTO.toString() + "]", e);
			}finally{
				limparCampos();
			}
		}
		
	}
	
	
	/**
	 * Metodo responsavel por alterar um Grupo de Usuario
	 */
	public void alterar(){
		
		grupoUsuarioDTO = montarTO();
		
		if(isFormularioValido(grupoUsuarioDTO)){
			
			try {
				if(!grupoUsuarioBO.isRegistroJaExiste(grupoUsuarioDTO)){
					grupoUsuarioBO.alterar(grupoUsuarioDTO);
					JSFUtil.exibirMensagem(FacesMessage.SEVERITY_INFO,Constantes.MSG_OPERACAO_REALIZADA_SUCESSO);
					consultarGrupos();
					RequestContext.getCurrentInstance().execute("PF('grupoUsuarioDialogAlteracao').hide();");	
				}else{
					JSFUtil.exibirMensagem(FacesMessage.SEVERITY_WARN,"Atenção: Já existe um Grupo de Usuario com a mesma descrição na base dados");
				}
			} catch (GrupoUsuarioException e) {
				JSFUtil.exibirMensagem(FacesMessage.SEVERITY_ERROR,"Falha ao alterar Grupo de Usuario [" + grupoUsuarioDTO.toString() + "]");
				LOG.error("Falha ao alterar Grupo de Usuario [" + grupoUsuarioDTO.toString() + "]", e);
			}finally{
				limparCampos();
			}
			
		}

	}


	public void excluir(){
		
		try {
			if(grupoUsuarioDTO != null){
						grupoUsuarioBO.excluir(grupoUsuarioDTO);
						JSFUtil.exibirMensagem(FacesMessage.SEVERITY_INFO,Constantes.MSG_OPERACAO_REALIZADA_SUCESSO);
						consultarGrupos();
			}else{
				JSFUtil.exibirMensagem(FacesMessage.SEVERITY_WARN,"Atenção: É necessário selecionar um Grupo de Usuário para a exclusão");
			}
		} catch (GrupoUsuarioException e) {
			JSFUtil.exibirMensagem(FacesMessage.SEVERITY_ERROR,"Falha ao excluir Grupo de Usuario [" + grupoUsuarioDTO.toString() + "]");
			LOG.error("Falha ao excluir Grupo de Usuario [" + grupoUsuarioDTO.toString() + "]", e);
		}finally{
			limparCampos();
		}

	}

	
	public void limparCampos(){
		setId(null);
		setDescricao(null);
		setGrupoUsuarioDTO(null);
	}
	
	/**
	 * Metodo responsavel por obter a linha selecionado e atribuir ao objeto 
	 */
	public void selecionarLinha(SelectEvent event) {
		setGrupoUsuarioDTO((GrupoUsuario) event.getObject());
		setDescricao(grupoUsuarioDTO.getDescricao());
		setId(grupoUsuarioDTO.getId());
	}

	/**
	 * Metodo responsavel por consultar todos os grupos de usuarios
	 * no banco de dados e preencher a lista disponibilizada na pagina
	 * 
	 * @return
	 * @throws GrupoUsuarioException 
	 */
	@SuppressWarnings("unchecked")
	public void consultarGrupos() throws GrupoUsuarioException {

		listaGrupos = grupoUsuarioBO.getListaGrupos();

		grupoUsuarioDataModel = new GrupoUsuarioDataModel(listaGrupos);
	}



	/**
	 * @return the grupoUsuarioBO
	 */
	public GrupoUsuarioBO getGrupoUsuarioBO() {
		return grupoUsuarioBO;
	}

	/**
	 * @param grupoUsuarioBO the grupoUsuarioBO to set
	 */
	public void setGrupoUsuarioBO(GrupoUsuarioBO grupoUsuarioBO) {
		this.grupoUsuarioBO = grupoUsuarioBO;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;

	}
	
	


	public GrupoUsuario getGrupoUsuarioDTO() {
		return grupoUsuarioDTO;
	}

	public void setGrupoUsuarioDTO(GrupoUsuario grupoUsuarioDTO) {
		this.grupoUsuarioDTO = grupoUsuarioDTO;
	}

	/**
	 * @return the grupoUsuarioDataModel
	 */
	public GrupoUsuarioDataModel getGrupoUsuarioDataModel() {
		try {
			consultarGrupos();
		} catch (GrupoUsuarioException e) {
			LOG.error(e.getMessage(),e);
		}
		return grupoUsuarioDataModel;
	}

	/**
	 * @param grupoUsuarioDataModel the grupoUsuarioDataModel to set
	 */
	public void setGrupoUsuarioDataModel(GrupoUsuarioDataModel grupoUsuarioDataModel) {
		this.grupoUsuarioDataModel = grupoUsuarioDataModel;
	}

	/**
	 * @return the listaGrupos
	 */
	public List<GrupoUsuario> getListaGrupos() {

		try {
			consultarGrupos();
		} catch (GrupoUsuarioException e) {
			JSFUtil.exibirMensagem(FacesMessage.SEVERITY_ERROR, MSG_ERRO_LISTANDO_GRUPOS);
			LOG.error(MSG_ERRO_LISTANDO_GRUPOS,e);
		}

		return listaGrupos;
	}

	/**
	 * @param listaGrupos the listaGrupos to set
	 */
	public void setListaGrupos(List<GrupoUsuario> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}

	/**
	 * @return the grupoEscolhido
	 */
	public GrupoUsuario getGrupoEscolhido() {
		return grupoEscolhido;
	}

	/**
	 * @param grupoEscolhido the grupoEscolhido to set
	 */
	public void setGrupoEscolhido(GrupoUsuario grupoEscolhido) {
		this.grupoEscolhido = grupoEscolhido;
	}


}
