package br.ufes.inf.ppgi.plataformaPpgi.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;


import br.ufes.inf.ppgi.plataformaPpgi.domain.Pesquisador;

@Repository
public class PesquisadorDAOImpl extends GenericDAOImpl<Pesquisador> implements PesquisadorDAO{
	
	@SuppressWarnings("unchecked")
	public Pesquisador recuperaCpfExistente(String cpf) {
		List<Pesquisador> pesquisador = new ArrayList<Pesquisador>();
		pesquisador = super.getEntityManager().createQuery("select p from Pesquisador p "
				+ "inner join Pessoa pe on pe.idPessoa = p.pessoa.idPessoa "
				+ "where pe.cpf = :codigo and p.dataFimPrograma is null").setParameter("codigo", cpf).getResultList();
		if (pesquisador.size() > 0){
			return pesquisador.get(0);
		}else{
			return null;
		}

	}
}
