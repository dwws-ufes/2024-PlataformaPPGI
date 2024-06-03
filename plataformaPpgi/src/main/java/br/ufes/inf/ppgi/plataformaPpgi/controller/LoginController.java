package br.ufes.inf.ppgi.plataformaPpgi.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Usuario;
import br.ufes.inf.ppgi.plataformaPpgi.service.UsuarioService;

@ManagedBean
@SessionScoped
public class LoginController {
	
	@ManagedProperty(value="#{usuarioService}")
	private UsuarioService usuarioService;
	
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

	public String efetuarLogin() {
		
		if (usuarioService.retornaUsuario(usuario.getLogin(), usuario.getSenha()) != null) {
			return "/jsf/inicio.xhtml";
		} else {
			return "/jsf/login.xhtml";
		}
	}

}
