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


import br.ufes.inf.ppgi.plataformaPpgi.domain.TipoUsuario;
import br.ufes.inf.ppgi.plataformaPpgi.service.TipoUsuarioService;

@ManagedBean
@ViewScoped
public class TipoUsuarioController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -196413051551586700L;

	@ManagedProperty(value="#{tipoUsuarioService}")
	private TipoUsuarioService tipoUsuarioService;
	
	private TipoUsuario tipoUsuario;
	private TipoUsuario tipoUsuarioSelecionado;
	
	private List<TipoUsuario> listaTipoUsuario;
	
	@PostConstruct
	public void init() {
		listaTipoUsuario = new ArrayList<TipoUsuario>();
		listaTipoUsuario = tipoUsuarioService.recuperarTodos();
		tipoUsuario = new TipoUsuario();
	}

	public TipoUsuarioService getTipoUsuarioService() {
		return tipoUsuarioService;
	}

	public void setTipoUsuarioService(TipoUsuarioService tipoUsuarioService) {
		this.tipoUsuarioService = tipoUsuarioService;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public TipoUsuario getTipoUsuarioSelecionado() {
		return tipoUsuarioSelecionado;
	}

	public void setTipoUsuarioSelecionado(TipoUsuario tipoUsuarioSelecionado) {
		this.tipoUsuarioSelecionado = tipoUsuarioSelecionado;
	}

	public List<TipoUsuario> getListaTipoUsuario() {
		return listaTipoUsuario;
	}

	public void setListaTipoUsuario(List<TipoUsuario> listaTipoUsuario) {
		this.listaTipoUsuario = listaTipoUsuario;
	}
	
	public void onRowSelectTipoUsuario() {
		tipoUsuario = tipoUsuarioService.recuperarPorId(tipoUsuarioSelecionado.getId());
	}
	
	public void novoTipoUsuario() {
		tipoUsuario = new TipoUsuario();
	}
	
	public void cancelarTipoUsuario() {
		tipoUsuario = new TipoUsuario();
	}
	
	public void salvarTipoUsuario(){
		try {
			tipoUsuarioService.salvar(tipoUsuario);
			listaTipoUsuario = tipoUsuarioService.recuperarTodos();			
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de usuário salvo com sucesso.",
					"O tipo usuário foi salvo com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (ValidationException e) {
			listaTipoUsuario = tipoUsuarioService.recuperarTodos();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar o tipo de usuário.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		} catch (Exception e) {

			listaTipoUsuario = tipoUsuarioService.recuperarTodos();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar o tipo de usuário.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}

	}
}
