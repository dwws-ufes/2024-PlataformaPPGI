package br.ufes.inf.ppgi.plataformaPpgi.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.inf.ppgi.plataformaPpgi.domain.SolicitacaoHomologacaoProducaoAcademica;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.GenericDAO;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.SolicitacaoHomologacaoProducaoAcademicaDAO;

@Service
public class SolicitacaoHomologacaoProducaoAcademicaService extends CRUDService<SolicitacaoHomologacaoProducaoAcademica> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5254293862123894667L;
	
	@Autowired
	SolicitacaoHomologacaoProducaoAcademicaDAO solicitacaoHomologacaoProducaoAcademicaDAO;

	@Override
	public GenericDAO<SolicitacaoHomologacaoProducaoAcademica> getGenericDAO() {
		return solicitacaoHomologacaoProducaoAcademicaDAO;
	}

	public SolicitacaoHomologacaoProducaoAcademicaDAO getSolicitacaoHomologacaoProducaoAcademicaDAO() {
		return solicitacaoHomologacaoProducaoAcademicaDAO;
	}

	public void setSolicitacaoHomologacaoProducaoAcademicaDAO(
			SolicitacaoHomologacaoProducaoAcademicaDAO solicitacaoHomologacaoProducaoAcademicaDAO) {
		this.solicitacaoHomologacaoProducaoAcademicaDAO = solicitacaoHomologacaoProducaoAcademicaDAO;
	}

}
