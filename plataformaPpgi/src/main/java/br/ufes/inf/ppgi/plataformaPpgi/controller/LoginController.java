package br.ufes.inf.ppgi.plataformaPpgi.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Usuario;
import br.ufes.inf.ppgi.plataformaPpgi.service.MensagemService;
import br.ufes.inf.ppgi.plataformaPpgi.service.UsuarioService;

@ManagedBean
@SessionScoped
public class LoginController {
	
	@ManagedProperty(value="#{usuarioService}")
	private UsuarioService usuarioService;
	
	@ManagedProperty(value="#{mensagemService}")
	private MensagemService mensagemService;

	private Usuario usuario;
	
	@PostConstruct
	public void init() {
		
		usuario = new Usuario();
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public MensagemService getMensagemService() {
		return mensagemService;
	}

	public void setMensagemService(MensagemService mensagemService) {
		this.mensagemService = mensagemService;
	}

	public String efetuarLogin() {
		usuario = usuarioService.retornaUsuario(usuario.getLogin(), usuario.getSenha());
		if(usuario	!= null) {
			FacesContext context = FacesContext.getCurrentInstance();  
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			HttpSession session = request.getSession(false);
			
			session.setAttribute("idUsuario", usuario.getId());
			session.setAttribute("tipoUsuario", usuario.getTipoUsuario().getNomeTipoUsuario());
			session.setAttribute("login", usuario.getLogin());
			session.setAttribute("nome", usuario.getPessoa().getNomePessoa());
			
			return "/jsf/inicio.xhtml";
		} else {
			this.usuario = new Usuario(); 
			mensagemService.FatalErro("Falha de Autenticação", "Usuário e/ou senha inválidos.");
			return "/jsf/login.xhtml";
		}
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/jsf/login?faces-redirect=true";

	}
	
	public String verificaTipoUsuarioPesquisador() {
		FacesContext context = FacesContext.getCurrentInstance();  
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();  
		HttpSession session = request.getSession(false);
		
		String tipoUsuario = (String) session.getAttribute("tipoUsuario");
		
		if(tipoUsuario.equals("Pesquisador")) {
			return "/errorpages/permissao.xhtml";
		} else {
			return "";
		}
		
	}

}
