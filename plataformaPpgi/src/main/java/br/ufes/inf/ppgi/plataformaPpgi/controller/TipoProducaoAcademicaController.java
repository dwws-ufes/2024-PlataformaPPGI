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

import br.ufes.inf.ppgi.plataformaPpgi.domain.TipoProducaoAcademica;
import br.ufes.inf.ppgi.plataformaPpgi.service.TipoProducaoAcademicaService;

@ManagedBean
@ViewScoped
public class TipoProducaoAcademicaController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3651150766102215614L;

	@ManagedProperty(value="#{tipoProducaoAcademicaService}")
	private TipoProducaoAcademicaService tipoProducaoAcademicaService;
	
	private TipoProducaoAcademica tipoProducaoAcademica;
	private TipoProducaoAcademica tipoProducaoAcademicaSelecionado;
	
	private List<TipoProducaoAcademica> listaTipoProducaoAcademica;
	
	@PostConstruct
	public void init() {
		listaTipoProducaoAcademica = new ArrayList<TipoProducaoAcademica>();
		listaTipoProducaoAcademica = tipoProducaoAcademicaService.recuperarTodos();
		tipoProducaoAcademica = new TipoProducaoAcademica();
	}

	public TipoProducaoAcademicaService getTipoProducaoAcademicaService() {
		return tipoProducaoAcademicaService;
	}

	public void setTipoProducaoAcademicaService(TipoProducaoAcademicaService tipoProducaoAcademicaService) {
		this.tipoProducaoAcademicaService = tipoProducaoAcademicaService;
	}

	public TipoProducaoAcademica getTipoProducaoAcademica() {
		return tipoProducaoAcademica;
	}

	public void setTipoProducaoAcademica(TipoProducaoAcademica tipoProducaoAcademica) {
		this.tipoProducaoAcademica = tipoProducaoAcademica;
	}

	public TipoProducaoAcademica getTipoProducaoAcademicaSelecionado() {
		return tipoProducaoAcademicaSelecionado;
	}

	public void setTipoProducaoAcademicaSelecionado(TipoProducaoAcademica tipoProducaoAcademicaSelecionado) {
		this.tipoProducaoAcademicaSelecionado = tipoProducaoAcademicaSelecionado;
	}

	public List<TipoProducaoAcademica> getListaTipoProducaoAcademica() {
		return listaTipoProducaoAcademica;
	}

	public void setListaTipoProducaoAcademica(List<TipoProducaoAcademica> listaTipoProducaoAcademica) {
		this.listaTipoProducaoAcademica = listaTipoProducaoAcademica;
	}
	
	public void onRowSelectTipoProducaoAcademica() {
		tipoProducaoAcademica = tipoProducaoAcademicaService.recuperarPorId(tipoProducaoAcademicaSelecionado.getId());
	}
	
	public void novoTipoProducaoAcademica() {
		tipoProducaoAcademica = new TipoProducaoAcademica();
	}
	
	public void cancelarTipoProducaoAcademica() {
		tipoProducaoAcademica = new TipoProducaoAcademica();
	}
	
	public void salvarTipoProducaoAcademica(){
		try {
			tipoProducaoAcademicaService.salvar(tipoProducaoAcademica);
			listaTipoProducaoAcademica = tipoProducaoAcademicaService.recuperarTodos();			
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de produção acadêmica salvo com sucesso.",
					"O tipo de produção acadêmica foi salvo com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (ValidationException e) {
			listaTipoProducaoAcademica = tipoProducaoAcademicaService.recuperarTodos();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar o tipo de produção acadêmica.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		} catch (Exception e) {

			listaTipoProducaoAcademica = tipoProducaoAcademicaService.recuperarTodos();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar o tipo de produção acadêmica.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}

	}

}
