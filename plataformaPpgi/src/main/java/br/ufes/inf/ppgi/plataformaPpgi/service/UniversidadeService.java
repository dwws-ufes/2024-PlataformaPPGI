package br.ufes.inf.ppgi.plataformaPpgi.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Universidade;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.GenericDAO;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.UniversidadeDAO;

@Service
public class UniversidadeService extends CRUDService<Universidade> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1818536794611370451L;
	
	@Autowired
	UniversidadeDAO universidadeDAO;
	
	@Override
	public GenericDAO<Universidade> getGenericDAO() {
		return universidadeDAO;
	}

	public UniversidadeDAO getUniversidadeDAO() {
		return universidadeDAO;
	}

	public void setUniversidadeDAO(UniversidadeDAO universidadeDAO) {
		this.universidadeDAO = universidadeDAO;
	}

}
