package br.ufes.inf.ppgi.plataformaPpgi.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.inf.ppgi.plataformaPpgi.domain.PesquisadorProjeto;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.GenericDAO;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.PesquisadorProjetoDAO;

@Service
public class PesquisadorProjetoService extends CRUDService<PesquisadorProjeto> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5772185883215022103L;
	
	@Autowired
	PesquisadorProjetoDAO pesquisadorProjetoDAO;
	
	@Override
	public GenericDAO<PesquisadorProjeto> getGenericDAO() {
		return pesquisadorProjetoDAO;
	}

	public PesquisadorProjetoDAO getPesquisadorProjetoDAO() {
		return pesquisadorProjetoDAO;
	}

	public void setPesquisadorProjetoDAO(PesquisadorProjetoDAO pesquisadorProjetoDAO) {
		this.pesquisadorProjetoDAO = pesquisadorProjetoDAO;
	}
}
