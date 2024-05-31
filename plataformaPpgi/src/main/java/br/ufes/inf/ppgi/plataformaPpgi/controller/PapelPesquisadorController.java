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

import br.ufes.inf.ppgi.plataformaPpgi.domain.PapelPesquisador;
import br.ufes.inf.ppgi.plataformaPpgi.service.PapelPesquisadorService;

@ManagedBean
@ViewScoped
public class PapelPesquisadorController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3075021368038008475L;

	@ManagedProperty(value="#{papelPesquisadorService}")
	private PapelPesquisadorService papelPesquisadorService;
	
	private PapelPesquisador papelPesquisador;
	private PapelPesquisador papelPesquisadorSelecionado;
	
	private List<PapelPesquisador> listaPapelPesquisador;
	
	@PostConstruct
	public void init() {
		listaPapelPesquisador = new ArrayList<PapelPesquisador>();
		listaPapelPesquisador = papelPesquisadorService.recuperarTodos();
		papelPesquisador = new PapelPesquisador();
	}

	public PapelPesquisadorService getPapelPesquisadorService() {
		return papelPesquisadorService;
	}

	public void setPapelPesquisadorService(PapelPesquisadorService papelPesquisadorService) {
		this.papelPesquisadorService = papelPesquisadorService;
	}

	public PapelPesquisador getPapelPesquisador() {
		return papelPesquisador;
	}

	public void setPapelPesquisador(PapelPesquisador papelPesquisador) {
		this.papelPesquisador = papelPesquisador;
	}

	public PapelPesquisador getPapelPesquisadorSelecionado() {
		return papelPesquisadorSelecionado;
	}

	public void setPapelPesquisadorSelecionado(PapelPesquisador papelPesquisadorSelecionado) {
		this.papelPesquisadorSelecionado = papelPesquisadorSelecionado;
	}

	public List<PapelPesquisador> getListaPapelPesquisador() {
		return listaPapelPesquisador;
	}

	public void setListaPapelPesquisador(List<PapelPesquisador> listaPapelPesquisador) {
		this.listaPapelPesquisador = listaPapelPesquisador;
	}
	
	public void onRowSelectPapelPesquisador() {
		papelPesquisador = papelPesquisadorService.recuperarPorId(papelPesquisadorSelecionado.getId());
	}
	
	public void novoPapelPesquisador() {
		papelPesquisador = new PapelPesquisador();
	}
	
	public void cancelarPapelPesquisador() {
		papelPesquisador = new PapelPesquisador();
	}
	
	public void salvarPapelPesquisador(){
		try {
			papelPesquisadorService.salvar(papelPesquisador);
			listaPapelPesquisador = papelPesquisadorService.recuperarTodos();		
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de papel de pesquisador salvo com sucesso.",
					"O tipo de papel de pesquisador foi salvo com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (ValidationException e) {
			listaPapelPesquisador = papelPesquisadorService.recuperarTodos();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar o tipo papel de pesquisador.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		} catch (Exception e) {

			listaPapelPesquisador = papelPesquisadorService.recuperarTodos();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar o tipo papel de pesquisador.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}

	}

}