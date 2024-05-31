package br.ufes.inf.ppgi.plataformaPpgi.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.inf.ppgi.plataformaPpgi.domain.PapelPesquisador;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.GenericDAO;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.PapelPesquisadorDAO;

@Service
public class PapelPesquisadorService extends CRUDService<PapelPesquisador> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3101198398348014351L;
	
	@Autowired
	PapelPesquisadorDAO papelPesquisadorDAO;
	
	@Override
	public GenericDAO<PapelPesquisador> getGenericDAO() {
		return papelPesquisadorDAO;
	}

	public PapelPesquisadorDAO getPapelPesquisadorDAO() {
		return papelPesquisadorDAO;
	}

	public void setPapelPesquisadorDAO(PapelPesquisadorDAO papelPesquisadorDAO) {
		this.papelPesquisadorDAO = papelPesquisadorDAO;
	}
}
