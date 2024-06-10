package br.ufes.inf.ppgi.plataformaPpgi.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.ValidationException;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Pesquisador;
import br.ufes.inf.ppgi.plataformaPpgi.domain.PesquisadorProjeto;
import br.ufes.inf.ppgi.plataformaPpgi.domain.Projeto;
import br.ufes.inf.ppgi.plataformaPpgi.service.PesquisadorProjetoService;
import br.ufes.inf.ppgi.plataformaPpgi.service.PesquisadorService;
import br.ufes.inf.ppgi.plataformaPpgi.service.ProjetoService;

@ManagedBean
@ViewScoped
public class ProjetoController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8110785882598496469L;

	@ManagedProperty(value="#{projetoService}")
	private ProjetoService projetoService;
	
	@ManagedProperty(value="#{pesquisadorService}")
	private PesquisadorService pesquisadorService;
	
	@ManagedProperty(value="#{pesquisadorProjetoService}")
	private PesquisadorProjetoService pesquisadorProjetoService;
	
	private Projeto projeto;
	private Projeto projetoSelecionado;
	private PesquisadorProjeto pesquisadorProjeto;
	private PesquisadorProjeto pesquisadorProjetoSelecionado;
	
	private List<Projeto> listaProjetos;
	private List<PesquisadorProjeto> listaPesquisadoresProjeto;
	private List<Pesquisador> listaPesquisador;
	
	private Date hoje;
	
	@PostConstruct
	public void init() {
		listaProjetos = new ArrayList<Projeto>();
		listaProjetos = projetoService.recuperarTodos();
		projeto = new Projeto();
		hoje = Calendar.getInstance().getTime();
		pesquisadorProjeto = new PesquisadorProjeto();
		pesquisadorProjetoSelecionado = new PesquisadorProjeto();
		listaPesquisadoresProjeto = new ArrayList<PesquisadorProjeto>();
		listaPesquisador = new ArrayList<Pesquisador>();
		listaPesquisador = pesquisadorService.recuperarTodos();
	}

	public ProjetoService getProjetoService() {
		return projetoService;
	}

	public void setProjetoService(ProjetoService projetoService) {
		this.projetoService = projetoService;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Projeto getProjetoSelecionado() {
		return projetoSelecionado;
	}

	public void setProjetoSelecionado(Projeto projetoSelecionado) {
		this.projetoSelecionado = projetoSelecionado;
	}

	public List<Projeto> getListaProjetos() {
		return listaProjetos;
	}

	public void setListaProjetos(List<Projeto> listaProjetos) {
		this.listaProjetos = listaProjetos;
	}
	
	public PesquisadorService getPesquisadorService() {
		return pesquisadorService;
	}

	public void setPesquisadorService(PesquisadorService pesquisadorService) {
		this.pesquisadorService = pesquisadorService;
	}	
	
	public PesquisadorProjetoService getPesquisadorProjetoService() {
		return pesquisadorProjetoService;
	}

	public void setPesquisadorProjetoService(PesquisadorProjetoService pesquisadorProjetoService) {
		this.pesquisadorProjetoService = pesquisadorProjetoService;
	}

	public List<PesquisadorProjeto> getListaPesquisadoresProjeto() {
		return listaPesquisadoresProjeto;
	}

	public void setListaPesquisadoresProjeto(List<PesquisadorProjeto> listaPesquisadoresProjeto) {
		this.listaPesquisadoresProjeto = listaPesquisadoresProjeto;
	}
	
	public PesquisadorProjeto getPesquisadorProjeto() {
		return pesquisadorProjeto;
	}

	public void setPesquisadorProjeto(PesquisadorProjeto pesquisadorProjeto) {
		this.pesquisadorProjeto = pesquisadorProjeto;
	}

	public PesquisadorProjeto getPesquisadorProjetoSelecionado() {
		return pesquisadorProjetoSelecionado;
	}

	public void setPesquisadorProjetoSelecionado(PesquisadorProjeto pesquisadorProjetoSelecionado) {
		this.pesquisadorProjetoSelecionado = pesquisadorProjetoSelecionado;
	}

	public List<Pesquisador> getListaPesquisador() {
		return listaPesquisador;
	}

	public void setListaPesquisador(List<Pesquisador> listaPesquisador) {
		this.listaPesquisador = listaPesquisador;
	}

	public void onRowSelectProjeto() {
		projeto = projetoService.recuperarPorId(projetoSelecionado.getId());
		listaPesquisadoresProjeto = pesquisadorProjetoService.recuperaPorProjeto(projeto);
		pesquisadorProjeto = new PesquisadorProjeto();
	}
	
	public void onRowSelectPesquisadorProjeto() {
		pesquisadorProjeto = pesquisadorProjetoService.recuperarPorId(pesquisadorProjetoSelecionado.getId());
	}

	public void novoProjeto() {
		projeto = new Projeto();
	}
	
	public void cancelarProjeto() {
		projeto = new Projeto();
	}
	
	public Date getHoje() {
		return hoje;
	}

	public void setHoje(Date hoje) {
		this.hoje = hoje;
	}
	
	public void novoPesquisadorProjeto() {
		pesquisadorProjeto = new PesquisadorProjeto();
		pesquisadorProjeto.setProjeto(projeto);
		pesquisadorProjeto.setPesquisador(new Pesquisador());
	}
	
	public void cancelarPesquisadorProjeto() {
		pesquisadorProjeto = new PesquisadorProjeto();
	}

	public void salvarProjeto(){
		try {
			projetoService.salvar(projeto);
			listaProjetos = projetoService.recuperarTodos();		
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Projeto salvo com sucesso.",
					"O projeto foi salvo com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (ValidationException e) {
			listaProjetos = projetoService.recuperarTodos();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar o projeto.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		} catch (Exception e) {

			listaProjetos = projetoService.recuperarTodos();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar o projeto.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}

	}
	
	public void validarDataFim() {
		if(projeto.getDataInicio() == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Escolha primeiro a data de início do projeto.", null);
			FacesContext.getCurrentInstance().addMessage(null, message);
			projeto.setDataFim(new Date());
		} else {
			if(projeto.getDataFim().before(projeto.getDataInicio())){
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "A data fim deve ser após a data de início.", null);
				FacesContext.getCurrentInstance().addMessage(null, message);
				projeto.setDataFim(new Date());
			}
		}
	}
	
	public void salvarPesquisadorProjeto(){
		try {
			
			pesquisadorProjetoService.salvar(pesquisadorProjeto);
			listaPesquisadoresProjeto = pesquisadorProjetoService.recuperaPorProjeto(projeto);
			pesquisadorProjeto = new PesquisadorProjeto();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pesquisador Projeto salvo com sucesso.",
					"O pesquisador projeto foi salvo com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (ValidationException e) {
			listaPesquisadoresProjeto = pesquisadorProjetoService.recuperaPorProjeto(projeto);
			pesquisadorProjeto = new PesquisadorProjeto();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar o pesquisador projeto.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		} catch (Exception e) {

			listaPesquisadoresProjeto = pesquisadorProjetoService.recuperaPorProjeto(projeto);
			pesquisadorProjeto = new PesquisadorProjeto();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar o pesquisador projeto.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}

	}
}
