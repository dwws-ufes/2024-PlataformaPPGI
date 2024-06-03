package br.ufes.inf.ppgi.plataformaPpgi.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Pessoa;

@Repository
public class PessoaDAOImpl extends GenericDAOImpl<Pessoa> implements PessoaDAO{
	
	@SuppressWarnings("unchecked")
	public Pessoa recuperaPorCpf(String cpf) {
		List<Pessoa> pessoa = new ArrayList<Pessoa>();
		pessoa = super.getEntityManager().createQuery("select p from Pessoa p "
				+ "where p.cpf = :codigo").setParameter("codigo", cpf).getResultList();
		if (pessoa.size() > 0){
			return pessoa.get(0);
		}else{
			return null;
		}

	}

}
