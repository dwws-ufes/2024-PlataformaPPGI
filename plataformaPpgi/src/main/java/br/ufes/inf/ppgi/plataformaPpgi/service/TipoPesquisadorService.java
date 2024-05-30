package br.ufes.inf.ppgi.plataformaPpgi.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.inf.ppgi.plataformaPpgi.domain.TipoPesquisador;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.GenericDAO;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.TipoPesquisadorDAO;

@Service
public class TipoPesquisadorService extends CRUDService<TipoPesquisador> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2406934046159574751L;
	
	@Autowired
	TipoPesquisadorDAO tipoPesquisadorDAO;
	
	@Override
	public GenericDAO<TipoPesquisador> getGenericDAO() {
		return tipoPesquisadorDAO;
	}

	public TipoPesquisadorDAO getTipoPesquisadorDAO() {
		return tipoPesquisadorDAO;
	}

	public void setTipoPesquisadorDAO(TipoPesquisadorDAO tipoPesquisadorDAO) {
		this.tipoPesquisadorDAO = tipoPesquisadorDAO;
	}
}
