package br.ufes.inf.ppgi.plataformaPpgi.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufes.inf.ppgi.plataformaPpgi.domain.PesquisadorProjeto;
import br.ufes.inf.ppgi.plataformaPpgi.domain.Projeto;

@Repository
public class PesquisadorProjetoDAOImpl extends GenericDAOImpl<PesquisadorProjeto> implements PesquisadorProjetoDAO{

	@SuppressWarnings("unchecked")
	public List<PesquisadorProjeto> recuperaPorProjeto(Projeto projeto) {
		List<PesquisadorProjeto> resultado = new ArrayList<PesquisadorProjeto>();
		resultado = super.getEntityManager().createQuery("select p from PesquisadorProjeto p "
				+ "where p.projeto.idProjeto = :codigo").setParameter("codigo", projeto.getId()).getResultList();
		if(resultado.size() > 0) {
			return resultado;
		} else {
			return new ArrayList<PesquisadorProjeto>();
		}
	}

}
