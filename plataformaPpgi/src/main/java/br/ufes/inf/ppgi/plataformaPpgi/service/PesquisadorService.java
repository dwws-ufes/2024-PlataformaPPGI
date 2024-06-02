package br.ufes.inf.ppgi.plataformaPpgi.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Pesquisador;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.GenericDAO;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.PesquisadorDAO;

@Service
public class PesquisadorService extends CRUDService<Pesquisador> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6853299707157631461L;
	
	@Autowired
	PesquisadorDAO pesquisadorDAO;

	@Override
	public GenericDAO<Pesquisador> getGenericDAO() {
		return pesquisadorDAO;
	}

	public PesquisadorDAO getPesquisadorDAO() {
		return pesquisadorDAO;
	}

	public void setPesquisadorDAO(PesquisadorDAO pesquisadorDAO) {
		this.pesquisadorDAO = pesquisadorDAO;
	}
}
