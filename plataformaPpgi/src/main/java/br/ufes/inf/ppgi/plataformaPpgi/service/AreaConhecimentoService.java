package br.ufes.inf.ppgi.plataformaPpgi.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.inf.ppgi.plataformaPpgi.domain.AreaConhecimento;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.AreaConhecimentoDAO;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.GenericDAO;

@Service
public class AreaConhecimentoService extends CRUDService<AreaConhecimento> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4602244662361636592L;
	
	@Autowired
	AreaConhecimentoDAO areaConhecimentoDAO;
	
	@Override
	public GenericDAO<AreaConhecimento> getGenericDAO() {
		return areaConhecimentoDAO;
	}

	public AreaConhecimentoDAO getAreaConhecimentoDAO() {
		return areaConhecimentoDAO;
	}

	public void setAreaConhecimentoDAO(AreaConhecimentoDAO areaConhecimentoDAO) {
		this.areaConhecimentoDAO = areaConhecimentoDAO;
	}
}
