package br.ufes.inf.ppgi.plataformaPpgi.persistence;

import java.util.List;

import br.ufes.inf.ppgi.plataformaPpgi.domain.PesquisadorProjeto;
import br.ufes.inf.ppgi.plataformaPpgi.domain.Projeto;

public interface PesquisadorProjetoDAO extends GenericDAO<PesquisadorProjeto>{

	public List<PesquisadorProjeto> recuperaPorProjeto(Projeto projeto);
}
