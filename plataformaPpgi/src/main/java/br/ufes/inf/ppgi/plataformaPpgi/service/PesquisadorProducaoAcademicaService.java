package br.ufes.inf.ppgi.plataformaPpgi.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.inf.ppgi.plataformaPpgi.domain.PesquisadorProducaoAcademica;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.GenericDAO;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.PesquisadorProducaoAcademicaDAO;

@Service
public class PesquisadorProducaoAcademicaService extends CRUDService<PesquisadorProducaoAcademica> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2911704615652151417L;
	
	@Autowired
	PesquisadorProducaoAcademicaDAO pesquisadorProducaoAcademicaDAO;

	@Override
	public GenericDAO<PesquisadorProducaoAcademica> getGenericDAO() {
		return pesquisadorProducaoAcademicaDAO;
	}

	public PesquisadorProducaoAcademicaDAO getPesquisadorProducaoAcademicaDAO() {
		return pesquisadorProducaoAcademicaDAO;
	}

	public void setPesquisadorProducaoAcademicaDAO(PesquisadorProducaoAcademicaDAO pesquisadorProducaoAcademicaDAO) {
		this.pesquisadorProducaoAcademicaDAO = pesquisadorProducaoAcademicaDAO;
	}
}
