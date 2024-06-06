package br.ufes.inf.ppgi.plataformaPpgi.persistence;

import java.util.List;

import br.ufes.inf.ppgi.plataformaPpgi.domain.PesquisadorProducaoAcademica;
import br.ufes.inf.ppgi.plataformaPpgi.domain.ProducaoAcademica;

public interface PesquisadorProducaoAcademicaDAO extends GenericDAO<PesquisadorProducaoAcademica>{

	List<PesquisadorProducaoAcademica> recuperarPorProducaoAcademica(ProducaoAcademica producaoAcademica);
}
