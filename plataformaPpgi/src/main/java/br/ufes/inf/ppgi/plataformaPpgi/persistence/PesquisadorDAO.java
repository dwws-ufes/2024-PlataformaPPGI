package br.ufes.inf.ppgi.plataformaPpgi.persistence;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Pesquisador;

public interface PesquisadorDAO extends GenericDAO<Pesquisador>{
	
	public Pesquisador recuperaCpfExistente(String cpf);

}
