package br.ufes.inf.ppgi.plataformaPpgi.persistence;

import java.util.List;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Pesquisador;
import br.ufes.inf.ppgi.plataformaPpgi.domain.Pessoa;
import br.ufes.inf.ppgi.plataformaPpgi.domain.Projeto;

public interface PesquisadorDAO extends GenericDAO<Pesquisador>{
	
	public Pesquisador recuperaCpfExistente(String cpf);

	public List<Pesquisador> recuperarAtivosPorProjeto(Projeto projeto);
	
	public Pesquisador recuperaAtivoPorPessoa(Pessoa pessoa);
}
