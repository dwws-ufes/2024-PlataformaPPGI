package br.ufes.inf.ppgi.plataformaPpgi.persistence;

import java.util.List;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Pesquisador;
import br.ufes.inf.ppgi.plataformaPpgi.domain.Projeto;

public interface ProjetoDAO extends GenericDAO<Projeto>{
	
	public List<Projeto> recuperaPorPesquisador(Pesquisador pesquisador);

}
