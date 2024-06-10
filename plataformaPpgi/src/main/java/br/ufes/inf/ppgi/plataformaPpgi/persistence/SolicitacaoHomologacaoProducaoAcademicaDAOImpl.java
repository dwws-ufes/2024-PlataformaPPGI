package br.ufes.inf.ppgi.plataformaPpgi.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufes.inf.ppgi.plataformaPpgi.domain.SolicitacaoHomologacaoProducaoAcademica;

@Repository
public class SolicitacaoHomologacaoProducaoAcademicaDAOImpl extends GenericDAOImpl<SolicitacaoHomologacaoProducaoAcademica> implements SolicitacaoHomologacaoProducaoAcademicaDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitacaoHomologacaoProducaoAcademica> recuperarNaoHomologadas() {
		List<SolicitacaoHomologacaoProducaoAcademica> resultado = new ArrayList<SolicitacaoHomologacaoProducaoAcademica>();
		resultado = super.getEntityManager().createQuery("select s from SolicitacaoHomologacaoProducaoAcademica s "
				+ "inner join ProducaoAcademica p on p.idProducaoAcademica = s.producaoAcademica.idProducaoAcademica "
				+ "where p.indHomologado = 'N'").getResultList();
		if(resultado.size() > 0) {
			return resultado;
		} else {
			return new ArrayList<SolicitacaoHomologacaoProducaoAcademica>();
		}
	}

}
