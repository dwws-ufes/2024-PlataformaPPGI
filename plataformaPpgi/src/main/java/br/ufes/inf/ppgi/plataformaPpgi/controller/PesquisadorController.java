package br.ufes.inf.ppgi.plataformaPpgi.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.ValidationException;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Pesquisador;
import br.ufes.inf.ppgi.plataformaPpgi.domain.Pessoa;
import br.ufes.inf.ppgi.plataformaPpgi.domain.TipoPesquisador;
import br.ufes.inf.ppgi.plataformaPpgi.service.PesquisadorService;
import br.ufes.inf.ppgi.plataformaPpgi.service.PessoaService;
import br.ufes.inf.ppgi.plataformaPpgi.service.TipoPesquisadorService;
import br.ufes.inf.utils.ValidarCpf;

@ManagedBean
@ViewScoped
public class PesquisadorController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 934505867357895786L;

	@ManagedProperty(value="#{pesquisadorService}")
	private PesquisadorService pesquisadorService;
	
	@ManagedProperty(value="#{pessoaService}")
	private PessoaService pessoaService;
	
	@ManagedProperty(value="#{tipoPesquisadorService}")
	private TipoPesquisadorService tipoPesquisadorService;
	
	private Pesquisador pesquisador;
	private Pesquisador pesquisadorSelecionado;
	private Pessoa pessoa;
	
	private List<Pesquisador> listaPesquisador;
	private List<TipoPesquisador> listaTipoPesquisador;	
	
	@PostConstruct
	public void init() {
		listaPesquisador = new ArrayList<Pesquisador>();
		listaPesquisador = pesquisadorService.recuperarTodos();
		pesquisador = new Pesquisador();
		pessoa = new Pessoa();
		listaTipoPesquisador = new ArrayList<TipoPesquisador>();
		listaTipoPesquisador = tipoPesquisadorService.recuperarTodos();
	}

	public PesquisadorService getPesquisadorService() {
		return pesquisadorService;
	}

	public void setPesquisadorService(PesquisadorService pesquisadorService) {
		this.pesquisadorService = pesquisadorService;
	}

	public PessoaService getPessoaService() {
		return pessoaService;
	}

	public void setPessoaService(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	public TipoPesquisadorService getTipoPesquisadorService() {
		return tipoPesquisadorService;
	}

	public void setTipoPesquisadorService(TipoPesquisadorService tipoPesquisadorService) {
		this.tipoPesquisadorService = tipoPesquisadorService;
	}

	public Pesquisador getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(Pesquisador pesquisador) {
		this.pesquisador = pesquisador;
	}

	public Pesquisador getPesquisadorSelecionado() {
		return pesquisadorSelecionado;
	}

	public void setPesquisadorSelecionado(Pesquisador pesquisadorSelecionado) {
		this.pesquisadorSelecionado = pesquisadorSelecionado;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pesquisador> getListaPesquisador() {
		return listaPesquisador;
	}

	public void setListaPesquisador(List<Pesquisador> listaPesquisador) {
		this.listaPesquisador = listaPesquisador;
	}
	
	public List<TipoPesquisador> getListaTipoPesquisador() {
		return listaTipoPesquisador;
	}

	public void setListaTipoPesquisador(List<TipoPesquisador> listaTipoPesquisador) {
		this.listaTipoPesquisador = listaTipoPesquisador;
	}

	public void onRowSelectPesquisador(){
		pesquisador = pesquisadorService.recuperarPorId(pesquisadorSelecionado.getId());
	}
	
	public void novoPesquisador() {
		pesquisador = new Pesquisador();
	}
	
	public void cancelarPesquisador() {
		pesquisador = new Pesquisador();
	}
	
	//Valida o numero do CPF, verifica se já existe pesquisador ativo cadastrado e se já existe Pessoa
	public void validarCpf() {
		if(!ValidarCpf.validarCpf(pesquisador.getPessoa().getCpf())) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF não é válido!","");
			FacesContext.getCurrentInstance().addMessage(null, message);
			pesquisador.getPessoa().setCpf("");
		} else {
			if(pesquisadorService.recuperaCpfExistente(pesquisador.getPessoa().getCpf()) != null) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "CPF já cadastrado para pesquisador ativo","");
				FacesContext.getCurrentInstance().addMessage(null, message);
				pesquisador.getPessoa().setCpf("");
			} else {
				pessoa = pessoaService.recuperaPorCpf(pesquisador.getPessoa().getCpf());
				if (pessoa != null){
					pesquisador.setPessoa(pessoa);
				}
			}
		}
		
	}

	public void salvarPesquisador(){
		try {
			pessoaService.salvar(pesquisador.getPessoa());
			pesquisadorService.salvar(pesquisador);
			listaPesquisador = pesquisadorService.recuperarTodos();
			pesquisador = new Pesquisador();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pesquisador salvo com sucesso.",
					"O pesquisador foi salvo com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (ValidationException e) {
			listaPesquisador = pesquisadorService.recuperarTodos();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar o pesquisador.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		} catch (Exception e) {

			listaPesquisador = pesquisadorService.recuperarTodos();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar o pesquisador.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}

	}
}
