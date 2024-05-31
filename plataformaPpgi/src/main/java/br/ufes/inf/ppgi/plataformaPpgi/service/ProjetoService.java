package br.ufes.inf.ppgi.plataformaPpgi.service;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Projeto;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.GenericDAO;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.ProjetoDAO;

@Service
public class ProjetoService extends CRUDService<Projeto> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8369218839233894381L;
	
	@Autowired
	ProjetoDAO projetoDAO;

	@Override
	public GenericDAO<Projeto> getGenericDAO() {
		return projetoDAO;
	}

	public ProjetoDAO getProjetoDAO() {
		return projetoDAO;
	}

	public void setProjetoDAO(ProjetoDAO projetoDAO) {
		this.projetoDAO = projetoDAO;
	}
}
