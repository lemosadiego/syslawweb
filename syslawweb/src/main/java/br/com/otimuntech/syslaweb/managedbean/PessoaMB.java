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

import br.com.otimuntech.syslaweb.bo.PessoaBO;
import br.com.otimuntech.syslaweb.exception.PessoaException;
import br.com.otimuntech.syslaweb.persistencia.entidade.ComboBox;
import br.com.otimuntech.syslaweb.persistencia.entidade.Pessoa;
import br.com.otimuntech.syslawweb.datamodel.PessoaDataModel;
import br.com.otimuntech.syslawweb.util.Constantes;
import br.com.otimuntech.syslawweb.util.JSFUtil;


@Component
@ManagedBean
@RequestScoped
public class PessoaMB {

	private Log LOG = LogFactory.getLog(PessoaMB.class);

	@Autowired
	PessoaBO pessoaBO;

	private String nomeBusca;
	
	private String nome;
	
	private String cpf;
	
	private String cnpj;
	
	private String oab;
	
	private String logradouro;
	
	private String bairro;
	
	private String cidade;
	
	private String estado;
	
	private String cep;
	
	private String telefoneCasa;
	
	private String telefoneTrabalho;
	
	private String celular;
	
	private String email;
	
	private Long idTipoPessoa;
	
	private Long codigoExterno;
	
	

	Pessoa pessoaDTO;

	Pessoa pessoaEscolhida;

	PessoaDataModel pessoaDataModel;

	List<Pessoa> listaPessoas = new ArrayList<>();
	
	List<ComboBox> listaTiposDePessoas = new ArrayList<>();


	public final String MSG_ERRO_LISTANDO_PESSOAS = "Falha ao consultar lista de pessoas na base de dados. Entre em contato com o administrador do sistema.";
	public final String MSG_CMP_DESCRICAO_INVALIDO = "O campo Descrição deve ser informado";


	public void novo(){
		limparCampos();
		//RequestContext.getCurrentInstance().execute("PF('grupoUsuarioDialogInclusao').show();");

	}

	public void carregarItemSelecionado(){
		
		if(pessoaDTO != null){
			RequestContext.getCurrentInstance().execute("PF('grupoUsuarioDialogAlteracao').show();");
		}else{
			JSFUtil.exibirMensagem(FacesMessage.SEVERITY_WARN,"Atenção: É necessário selecionar uma Pessoa para a alteração");
		}
		
	}

	
	

	private boolean isFormularioValido(Pessoa pessoa) {

			/*if(grupoUsuario.getDescricao() == null || grupoUsuario.getDescricao().isEmpty()){
				JSFUtil.exibirMensagem(FacesMessage.SEVERITY_ERROR, MSG_CMP_DESCRICAO_INVALIDO);
				return Boolean.FALSE;
			}*/

		return Boolean.TRUE;
	}

	private Pessoa montarTO() {
		Pessoa pessoa = new Pessoa();
		
		/*grupoUsuario.setDescricao(descricao.toUpperCase());
		grupoUsuario.setId(id);*/
		
		return pessoa;
	}

	
	/**
	 * Metodo responsavel por incluir um Grupo de Usuario
	 */
	public void incluir(){

		pessoaDTO = montarTO();
		
		if(isFormularioValido(pessoaDTO)){
			
			try {
				if(!pessoaBO.isRegistroJaExiste(pessoaDTO)){
					pessoaBO.incluir(pessoaDTO);
					JSFUtil.exibirMensagem(FacesMessage.SEVERITY_INFO,Constantes.MSG_OPERACAO_REALIZADA_SUCESSO);
					//RequestContext.getCurrentInstance().execute("PF('grupoUsuarioDialogInclusao').hide();");
				}else{
					JSFUtil.exibirMensagem(FacesMessage.SEVERITY_WARN,"Atenção: Já existe uma Pessoa com a mesma descrição na base dados");
				}
			} catch (PessoaException e) {
				JSFUtil.exibirMensagem(FacesMessage.SEVERITY_ERROR,"Falha ao incluir Pessoa [" + pessoaDTO.toString() + "]");
				LOG.error("Falha ao incluir Pessoa [" + pessoaDTO.toString() + "]", e);
			}finally{
				limparCampos();
			}
		}
		
	}
	
	
	/**
	 * Metodo responsavel por alterar um Grupo de Usuario
	 */
	public void alterar(){
		
		pessoaDTO = montarTO();
		
		if(isFormularioValido(pessoaDTO)){
			
			try {
				if(!pessoaBO.isRegistroJaExiste(pessoaDTO)){
					pessoaBO.alterar(pessoaDTO);
					JSFUtil.exibirMensagem(FacesMessage.SEVERITY_INFO,Constantes.MSG_OPERACAO_REALIZADA_SUCESSO);
					consultarPessoas();
					RequestContext.getCurrentInstance().execute("PF('grupoUsuarioDialogAlteracao').hide();");	
				}else{
					JSFUtil.exibirMensagem(FacesMessage.SEVERITY_WARN,"Atenção: Já existe um Grupo de Usuario com a mesma descrição na base dados");
				}
			} catch (PessoaException e) {
				JSFUtil.exibirMensagem(FacesMessage.SEVERITY_ERROR,"Falha ao alterar Grupo de Usuario [" + pessoaDTO.toString() + "]");
				LOG.error("Falha ao alterar Grupo de Usuario [" + pessoaDTO.toString() + "]", e);
			}finally{
				limparCampos();
			}
			
		}

	}


	public void excluir(){
		
		try {
			if(pessoaDTO != null){
						pessoaBO.excluir(pessoaDTO);
						JSFUtil.exibirMensagem(FacesMessage.SEVERITY_INFO,Constantes.MSG_OPERACAO_REALIZADA_SUCESSO);
						consultarPessoas();
			}else{
				JSFUtil.exibirMensagem(FacesMessage.SEVERITY_WARN,"Atenção: É necessário selecionar um Grupo de Usuário para a exclusão");
			}
		} catch (PessoaException e) {
			JSFUtil.exibirMensagem(FacesMessage.SEVERITY_ERROR,"Falha ao excluir Grupo de Usuario [" + pessoaDTO.toString() + "]");
			LOG.error("Falha ao excluir Grupo de Usuario [" + pessoaDTO.toString() + "]", e);
		}finally{
			limparCampos();
		}

	}

	
	public void limparCampos(){
		/*setId(null);
		setDescricao(null);
		setpessoaDTO(null);*/
	}
	
	/**
	 * Metodo responsavel por obter a linha selecionado e atribuir ao objeto 
	 */
	public void selecionarLinha(SelectEvent event) {
		//setPessoaDTO((Pessoa) event.getObject());
		
	}

	/**
	 * Metodo responsavel por consultar todos os grupos de usuarios
	 * no banco de dados e preencher a lista disponibilizada na pagina
	 * 
	 * @return
	 * @throws PessoaException 
	 */
	@SuppressWarnings("unchecked")
	public void consultarPessoas() throws PessoaException {

		listaPessoas = pessoaBO.getListaPessoas();

		pessoaDataModel = new PessoaDataModel(listaPessoas);
	}

	public PessoaBO getPessoaBO() {
		return pessoaBO;
	}

	public void setPessoaBO(PessoaBO pessoaBO) {
		this.pessoaBO = pessoaBO;
	}

	public String getNomeBusca() {
		return nomeBusca;
	}

	public void setNomeBusca(String nomeBusca) {
		this.nomeBusca = nomeBusca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getOab() {
		return oab;
	}

	public void setOab(String oab) {
		this.oab = oab;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefoneCasa() {
		return telefoneCasa;
	}

	public void setTelefoneCasa(String telefoneCasa) {
		this.telefoneCasa = telefoneCasa;
	}

	public String getTelefoneTrabalho() {
		return telefoneTrabalho;
	}

	public void setTelefoneTrabalho(String telefoneTrabalho) {
		this.telefoneTrabalho = telefoneTrabalho;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getIdTipoPessoa() {
		return idTipoPessoa;
	}

	public void setIdTipoPessoa(Long idTipoPessoa) {
		this.idTipoPessoa = idTipoPessoa;
	}

	public Long getCodigoExterno() {
		return codigoExterno;
	}

	public void setCodigoExterno(Long codigoExterno) {
		this.codigoExterno = codigoExterno;
	}

	public Pessoa getPessoaDTO() {
		return pessoaDTO;
	}

	public void setPessoaDTO(Pessoa pessoaDTO) {
		this.pessoaDTO = pessoaDTO;
	}

	public Pessoa getPessoaEscolhida() {
		return pessoaEscolhida;
	}

	public void setPessoaEscolhida(Pessoa pessoaEscolhida) {
		this.pessoaEscolhida = pessoaEscolhida;
	}

	public PessoaDataModel getPessoaDataModel() {
		return pessoaDataModel;
	}

	public void setPessoaDataModel(PessoaDataModel pessoaDataModel) {
		this.pessoaDataModel = pessoaDataModel;
	}

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}

	public List<ComboBox> getListaTiposDePessoas() {
		return listaTiposDePessoas;
	}

	public void setListaTiposDePessoas(List<ComboBox> listaTiposDePessoas) {
		this.listaTiposDePessoas = listaTiposDePessoas;
	}

	
	



}
