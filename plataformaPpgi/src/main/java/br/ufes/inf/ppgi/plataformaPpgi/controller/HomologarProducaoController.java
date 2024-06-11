package br.ufes.inf.ppgi.plataformaPpgi.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.ValidationException;

import br.ufes.inf.ppgi.plataformaPpgi.domain.PesquisadorProducaoAcademica;
import br.ufes.inf.ppgi.plataformaPpgi.domain.SolicitacaoHomologacaoProducaoAcademica;
import br.ufes.inf.ppgi.plataformaPpgi.service.ProducaoAcademicaService;
import br.ufes.inf.ppgi.plataformaPpgi.service.SolicitacaoHomologacaoProducaoAcademicaService;

@ManagedBean
@ViewScoped
public class HomologarProducaoController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{producaoAcademicaService}")
	private ProducaoAcademicaService producaoAcademicaService;
	
	@ManagedProperty(value="#{solicitacaoHomologacaoProducaoAcademicaService}")
	private SolicitacaoHomologacaoProducaoAcademicaService solicitacaoHomologacaoProducaoAcademicaService;
	
	List<SolicitacaoHomologacaoProducaoAcademica> listaSolicitacao;
	SolicitacaoHomologacaoProducaoAcademica solicitacao;
	SolicitacaoHomologacaoProducaoAcademica solicitacaoSelecionada;
	
	@PostConstruct
	public void init() {
		listaSolicitacao = solicitacaoHomologacaoProducaoAcademicaService.recuperarNaoHomologadas();
	}

	public ProducaoAcademicaService getProducaoAcademicaService() {
		return producaoAcademicaService;
	}

	public void setProducaoAcademicaService(ProducaoAcademicaService producaoAcademicaService) {
		this.producaoAcademicaService = producaoAcademicaService;
	}

	public SolicitacaoHomologacaoProducaoAcademicaService getSolicitacaoHomologacaoProducaoAcademicaService() {
		return solicitacaoHomologacaoProducaoAcademicaService;
	}

	public void setSolicitacaoHomologacaoProducaoAcademicaService(
			SolicitacaoHomologacaoProducaoAcademicaService solicitacaoHomologacaoProducaoAcademicaService) {
		this.solicitacaoHomologacaoProducaoAcademicaService = solicitacaoHomologacaoProducaoAcademicaService;
	}

	public List<SolicitacaoHomologacaoProducaoAcademica> getListaSolicitacao() {
		return listaSolicitacao;
	}

	public void setListaSolicitacao(List<SolicitacaoHomologacaoProducaoAcademica> listaSolicitacao) {
		this.listaSolicitacao = listaSolicitacao;
	}

	public SolicitacaoHomologacaoProducaoAcademica getSolicitacaoSelecionada() {
		return solicitacaoSelecionada;
	}

	public void setSolicitacaoSelecionada(SolicitacaoHomologacaoProducaoAcademica solicitacaoSelecionada) {
		this.solicitacaoSelecionada = solicitacaoSelecionada;
	}

	public SolicitacaoHomologacaoProducaoAcademica getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(SolicitacaoHomologacaoProducaoAcademica solicitacao) {
		this.solicitacao = solicitacao;
	}
	
	public void onRowSelectSolicitacao() {
		solicitacao = solicitacaoHomologacaoProducaoAcademicaService.recuperarPorId(solicitacaoSelecionada.getId());
	}
	
	public void cancelarSolicitacao() {
		solicitacao = new SolicitacaoHomologacaoProducaoAcademica();
	}
	
	public void salvarSolicitacao() {
		try {
			
			solicitacaoHomologacaoProducaoAcademicaService.salvar(solicitacao);
			producaoAcademicaService.salvar(solicitacao.getProducaoAcademica());
			listaSolicitacao = solicitacaoHomologacaoProducaoAcademicaService.recuperarNaoHomologadas();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitação de homologação de Produção acadêmica salva com sucesso.",
					"Solicitação de homologação de Produção foi salvo com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (ValidationException e) {
			listaSolicitacao = solicitacaoHomologacaoProducaoAcademicaService.recuperarNaoHomologadas();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar a solicitação de homologação de Produção.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		} catch (Exception e) {
			listaSolicitacao = solicitacaoHomologacaoProducaoAcademicaService.recuperarNaoHomologadas();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar a solicitação de homologação de Produção",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
	}
}