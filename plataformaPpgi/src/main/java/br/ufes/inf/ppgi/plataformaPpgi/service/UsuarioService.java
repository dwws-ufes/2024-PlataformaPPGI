package br.ufes.inf.ppgi.plataformaPpgi.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Usuario;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.GenericDAO;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.UsuarioDAO;

@Service
public class UsuarioService extends CRUDService<Usuario> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4303670275901961000L;
	
	@Autowired
	UsuarioDAO usuarioDAO;

	@Override
	public GenericDAO<Usuario> getGenericDAO() {
		return usuarioDAO;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	public Usuario recuperaCpfExistente(String cpf) {
		return usuarioDAO.recuperaCpfExistente(cpf);
	}
	
	public Usuario retornaUsuario(String login, String senha) {
		return usuarioDAO.retornaUsuario(login, senha);
	}
}
