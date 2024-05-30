package br.ufes.inf.ppgi.plataformaPpgi.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.ValidationException;

import java.util.ArrayList;
import java.util.List;

import br.ufes.inf.ppgi.plataformaPpgi.domain.TipoPesquisador;
import br.ufes.inf.ppgi.plataformaPpgi.service.TipoPesquisadorService;

@ManagedBean
@ViewScoped
public class TipoPesquisadorController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8249678206277726286L;
	

	@ManagedProperty(value="#{tipoPesquisadorService}")
	private TipoPesquisadorService tipoPesquisadorService;
	
	private TipoPesquisador tipoPesquisador;
	private TipoPesquisador tipoPesquisadorSelecionado;
	
	private List<TipoPesquisador> listaTipoPesquisador;
	
	@PostConstruct
	public void init() {
		listaTipoPesquisador = new ArrayList<TipoPesquisador>();
		listaTipoPesquisador = tipoPesquisadorService.recuperarTodos();
		tipoPesquisador = new TipoPesquisador();
	}

	public TipoPesquisadorService getTipoPesquisadorService() {
		return tipoPesquisadorService;
	}

	public void setTipoPesquisadorService(TipoPesquisadorService tipoPesquisadorService) {
		this.tipoPesquisadorService = tipoPesquisadorService;
	}

	public TipoPesquisador getTipoPesquisador() {
		return tipoPesquisador;
	}

	public void setTipoPesquisador(TipoPesquisador tipoPesquisador) {
		this.tipoPesquisador = tipoPesquisador;
	}

	public List<TipoPesquisador> getListaTipoPesquisador() {
		return listaTipoPesquisador;
	}

	public void setListaTipoPesquisador(List<TipoPesquisador> listaTipoPesquisador) {
		this.listaTipoPesquisador = listaTipoPesquisador;
	}
	
	public TipoPesquisador getTipoPesquisadorSelecionado() {
		return tipoPesquisadorSelecionado;
	}

	public void setTipoPesquisadorSelecionado(TipoPesquisador tipoPesquisadorSelecionado) {
		this.tipoPesquisadorSelecionado = tipoPesquisadorSelecionado;
	}

	public void onRowSelectTipoPesquisador() {
		tipoPesquisador = tipoPesquisadorService.recuperarPorId(tipoPesquisadorSelecionado.getId());
	}
	
	public void novoTipoPesquisador() {
		tipoPesquisador = new TipoPesquisador();
	}
	
	public void cancelarTipoPesquisador() {
		tipoPesquisador = new TipoPesquisador();
	}
	
	public void salvarTipoPesquisador(){
		try {
			tipoPesquisadorService.salvar(tipoPesquisador);
			listaTipoPesquisador = tipoPesquisadorService.recuperarTodos();			
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de pesquisador salvo com sucesso.",
					"O tipo de pesquisador foi salvo com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (ValidationException e) {
			listaTipoPesquisador = tipoPesquisadorService.recuperarTodos();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar o tipo de pesquisador.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		} catch (Exception e) {

			listaTipoPesquisador = tipoPesquisadorService.recuperarTodos();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar o tipo de pesquisador.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}

	}
}