package br.ufes.inf.ppgi.plataformaPpgi.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufes.inf.ppgi.plataformaPpgi.domain.PesquisadorProducaoAcademica;
import br.ufes.inf.ppgi.plataformaPpgi.domain.ProducaoAcademica;

@Repository
public class PesquisadorProducaoAcademicaDAOImpl extends GenericDAOImpl<PesquisadorProducaoAcademica> implements PesquisadorProducaoAcademicaDAO{

	@SuppressWarnings("unchecked")
	public List<PesquisadorProducaoAcademica> recuperarPorProducaoAcademica(ProducaoAcademica producaoAcademica) {
		List<PesquisadorProducaoAcademica> pesquisador = new ArrayList<PesquisadorProducaoAcademica>();
		pesquisador = super.getEntityManager().createQuery("select p from PesquisadorProducaoAcademica p "
				+ "where p.producaoAcademica.idProducaoAcademica = :id").setParameter("id", producaoAcademica.getId()).getResultList();
		if (pesquisador.size() > 0){
			return pesquisador;
		}else{
			return new ArrayList<PesquisadorProducaoAcademica>();
		}
	}
}
