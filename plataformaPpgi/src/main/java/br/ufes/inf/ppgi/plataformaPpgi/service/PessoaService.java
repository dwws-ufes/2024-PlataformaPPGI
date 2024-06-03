package br.ufes.inf.ppgi.plataformaPpgi.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Pessoa;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.GenericDAO;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.PessoaDAO;

@Service
public class PessoaService extends CRUDService<Pessoa> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6048427936161835020L;
	
	@Autowired
	PessoaDAO pessoaDAO;

	@Override
	public GenericDAO<Pessoa> getGenericDAO() {
		return pessoaDAO;
	}

	public PessoaDAO getPessoaDAO() {
		return pessoaDAO;
	}

	public void setPessoaDAO(PessoaDAO pessoaDAO) {
		this.pessoaDAO = pessoaDAO;
	}
	
	public Pessoa recuperaPorCpf(String cpf) {
		return pessoaDAO.recuperaPorCpf(cpf);
	}
}
