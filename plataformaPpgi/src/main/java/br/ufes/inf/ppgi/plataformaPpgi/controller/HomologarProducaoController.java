package br.ufes.inf.ppgi.plataformaPpgi.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

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
}