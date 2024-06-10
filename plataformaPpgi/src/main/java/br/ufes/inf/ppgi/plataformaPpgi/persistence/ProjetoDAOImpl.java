package br.ufes.inf.ppgi.plataformaPpgi.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Pesquisador;
import br.ufes.inf.ppgi.plataformaPpgi.domain.Projeto;

@Repository
public class ProjetoDAOImpl extends GenericDAOImpl<Projeto> implements ProjetoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Projeto> recuperaPorPesquisador(Pesquisador pesquisador) {
		List<Projeto> resultado = new ArrayList<Projeto>();
		resultado = super.getEntityManager().createQuery("select p from Projeto p "
				+ "inner join PesquisadorProjeto pp on pp.pesquisador.idPesquisador = :codigo "
				+ "where pp.projeto.dataFim is null").setParameter("codigo", pesquisador.getId()).getResultList();
		if(resultado.size() > 0) {
			return resultado;
		} else {
			return new ArrayList<Projeto>();
		}
	}

}
