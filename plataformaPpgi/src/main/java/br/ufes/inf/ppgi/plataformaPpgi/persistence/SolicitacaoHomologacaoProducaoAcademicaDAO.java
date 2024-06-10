package br.ufes.inf.ppgi.plataformaPpgi.persistence;

import java.util.List;

import br.ufes.inf.ppgi.plataformaPpgi.domain.SolicitacaoHomologacaoProducaoAcademica;

public interface SolicitacaoHomologacaoProducaoAcademicaDAO extends GenericDAO<SolicitacaoHomologacaoProducaoAcademica>{
	
	public List<SolicitacaoHomologacaoProducaoAcademica> recuperarNaoHomologadas();

}
