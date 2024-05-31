package br.ufes.inf.ppgi.plataformaPpgi.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.inf.ppgi.plataformaPpgi.domain.TipoUsuario;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.GenericDAO;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.TipoUsuarioDAO;

@Service
public class TipoUsuarioService extends CRUDService<TipoUsuario> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3432851498128117370L;
	
	@Autowired
	TipoUsuarioDAO tipoUsuarioDAO;
	
	@Override
	public GenericDAO<TipoUsuario> getGenericDAO() {
		return tipoUsuarioDAO;
	}

	public TipoUsuarioDAO getTipoUsuarioDAO() {
		return tipoUsuarioDAO;
	}

	public void setTipoUsuarioDAO(TipoUsuarioDAO tipoUsuarioDAO) {
		this.tipoUsuarioDAO = tipoUsuarioDAO;
	}
}
