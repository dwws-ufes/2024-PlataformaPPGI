package br.ufes.inf.ppgi.plataformaPpgi.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.inf.ppgi.plataformaPpgi.domain.TipoProducaoAcademica;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.GenericDAO;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.TipoProducaoAcademicaDAO;

@Service
public class TipoProducaoAcademicaService extends CRUDService<TipoProducaoAcademica> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8171084180124009208L;
	
	@Autowired
	TipoProducaoAcademicaDAO tipoProducaoAcademicaDAO;

	@Override
	public GenericDAO<TipoProducaoAcademica> getGenericDAO() {
		return tipoProducaoAcademicaDAO;
	}

	public TipoProducaoAcademicaDAO getTipoProducaoAcademicaDAO() {
		return tipoProducaoAcademicaDAO;
	}

	public void setTipoProducaoAcademicaDAO(TipoProducaoAcademicaDAO tipoProducaoAcademicaDAO) {
		this.tipoProducaoAcademicaDAO = tipoProducaoAcademicaDAO;
	}	
}
