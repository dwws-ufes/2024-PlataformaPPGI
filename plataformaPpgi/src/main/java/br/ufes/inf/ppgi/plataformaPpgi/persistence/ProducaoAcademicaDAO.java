package br.ufes.inf.ppgi.plataformaPpgi.persistence;

import java.util.List;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Pesquisador;
import br.ufes.inf.ppgi.plataformaPpgi.domain.ProducaoAcademica;

public interface ProducaoAcademicaDAO extends GenericDAO<ProducaoAcademica>{

	public List<ProducaoAcademica> recuperaPorPesquisadorAtivo(Pesquisador pesquisador);
}
