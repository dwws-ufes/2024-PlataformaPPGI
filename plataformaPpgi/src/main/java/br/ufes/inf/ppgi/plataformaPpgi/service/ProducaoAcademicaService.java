package br.ufes.inf.ppgi.plataformaPpgi.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.inf.ppgi.plataformaPpgi.domain.ProducaoAcademica;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.GenericDAO;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.ProducaoAcademicaDAO;

@Service
public class ProducaoAcademicaService extends CRUDService<ProducaoAcademica> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5442255634534234626L;
	
	@Autowired
	ProducaoAcademicaDAO producaoAcademicaDAO;
	
	@Override
	public GenericDAO<ProducaoAcademica> getGenericDAO() {
		return producaoAcademicaDAO;
	}

	public ProducaoAcademicaDAO getProducaoAcademicaDAO() {
		return producaoAcademicaDAO;
	}

	public void setProducaoAcademicaDAO(ProducaoAcademicaDAO producaoAcademicaDAO) {
		this.producaoAcademicaDAO = producaoAcademicaDAO;
	}
}
