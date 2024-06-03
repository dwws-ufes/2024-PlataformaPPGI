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

import br.ufes.inf.ppgi.plataformaPpgi.domain.Pessoa;
import br.ufes.inf.ppgi.plataformaPpgi.domain.TipoUsuario;
import br.ufes.inf.ppgi.plataformaPpgi.domain.Usuario;
import br.ufes.inf.ppgi.plataformaPpgi.service.PessoaService;
import br.ufes.inf.ppgi.plataformaPpgi.service.TipoUsuarioService;
import br.ufes.inf.ppgi.plataformaPpgi.service.UsuarioService;
import br.ufes.inf.utils.ValidarCpf;

@ManagedBean
@ViewScoped
public class UsuarioController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -916674150388919243L;

	@ManagedProperty(value="#{usuarioService}")
	private UsuarioService usuarioService;
	
	@ManagedProperty(value="#{pessoaService}")
	private PessoaService pessoaService;
	
	@ManagedProperty(value="#{tipoUsuarioService}")
	private TipoUsuarioService tipoUsuarioService;
	
	private Usuario usuario;
	private Usuario usuarioSelecionado;
	private Pessoa pessoa;
	
	private List<TipoUsuario> listaTipoUsuario;
	private List<Usuario> listaUsuario;
	
	@PostConstruct
	public void init() {
		listaUsuario = new ArrayList<Usuario>();
		listaUsuario = usuarioService.recuperarTodos();
		usuario = new Usuario();
		pessoa = new Pessoa();
		listaTipoUsuario = new ArrayList<TipoUsuario>();
		listaTipoUsuario = tipoUsuarioService.recuperarTodos();
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public PessoaService getPessoaService() {
		return pessoaService;
	}

	public void setPessoaService(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	public TipoUsuarioService getTipoUsuarioService() {
		return tipoUsuarioService;
	}

	public void setTipoUsuarioService(TipoUsuarioService tipoUsuarioService) {
		this.tipoUsuarioService = tipoUsuarioService;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<TipoUsuario> getListaTipoUsuario() {
		return listaTipoUsuario;
	}

	public void setListaTipoUsuario(List<TipoUsuario> listaTipoUsuario) {
		this.listaTipoUsuario = listaTipoUsuario;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
	
	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public void onRowSelectUsuario(){
		usuario = usuarioService.recuperarPorId(usuarioSelecionado.getId());
	}
	
	public void novoPesquisador() {
		usuario = new Usuario();
	}
	
	public void cancelarPesquisador() {
		usuario = new Usuario();
	}
	
	//Valida o numero do CPF, verifica se já existe pesquisador ativo cadastrado e se já existe Pessoa
	public void validarCpf() {
		if(!ValidarCpf.validarCpf(usuario.getPessoa().getCpf())) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF não é válido!","");
			FacesContext.getCurrentInstance().addMessage(null, message);
			usuario.getPessoa().setCpf("");
		} else {
			if(usuarioService.recuperaCpfExistente(usuario.getPessoa().getCpf()) != null) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "CPF já cadastrado para usuário ativo","");
				FacesContext.getCurrentInstance().addMessage(null, message);
				usuario.getPessoa().setCpf("");
			} else {
				pessoa = pessoaService.recuperaPorCpf(usuario.getPessoa().getCpf());
				if (pessoa != null){
					usuario.setPessoa(pessoa);
				}
			}
		}
		
	}
	
	public void salvarUsuario(){
		try {
			pessoaService.salvar(usuario.getPessoa());
			usuarioService.salvar(usuario);
			listaUsuario = usuarioService.recuperarTodos();
			usuario = new Usuario();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário salvo com sucesso.",
					"O usuário foi salvo com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (ValidationException e) {
			listaUsuario = usuarioService.recuperarTodos();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar o usuário.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		} catch (Exception e) {

			listaUsuario = usuarioService.recuperarTodos();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar o usuário.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}

	}
	
}
