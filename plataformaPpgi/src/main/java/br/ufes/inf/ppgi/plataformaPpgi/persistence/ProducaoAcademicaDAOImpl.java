package br.ufes.inf.ppgi.plataformaPpgi.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Pesquisador;
import br.ufes.inf.ppgi.plataformaPpgi.domain.ProducaoAcademica;

@Repository
public class ProducaoAcademicaDAOImpl extends GenericDAOImpl<ProducaoAcademica> implements ProducaoAcademicaDAO{

	@SuppressWarnings("unchecked")
	public List<ProducaoAcademica> recuperaPorPesquisadorAtivo(Pesquisador pesquisador) {
		List<ProducaoAcademica> resultado = new ArrayList<ProducaoAcademica>();
		resultado = super.getEntityManager().createQuery("select p from ProducaoAcademica p "
				+ "inner join PesquisadorProducaoAcademica ppa on ppa.producaoAcademica.idProducaoAcademica = p.idProducaoAcademica "
				+ "where ppa.pesquisador.idPesquisador = :codigo").setParameter("codigo", pesquisador.getId()).getResultList();
		if(resultado.size() > 0) {
			return resultado;
		} else {
			return new ArrayList<ProducaoAcademica>();
		}
	}
}
