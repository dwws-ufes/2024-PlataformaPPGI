package br.ufes.inf.ppgi.plataformaPpgi.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Pesquisador;
import br.ufes.inf.ppgi.plataformaPpgi.domain.ProducaoAcademica;
import br.ufes.inf.ppgi.plataformaPpgi.domain.SolicitacaoHomologacaoProducaoAcademica;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.GenericDAO;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.ProducaoAcademicaDAO;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.SolicitacaoHomologacaoProducaoAcademicaDAO;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.UsuarioDAO;

@Service
public class ProducaoAcademicaService extends CRUDService<ProducaoAcademica> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5442255634534234626L;
	
	@Autowired
	ProducaoAcademicaDAO producaoAcademicaDAO;
	
	@Autowired
	SolicitacaoHomologacaoProducaoAcademicaDAO solicitacaoHomologacaoProducaoAcademicaDAO;
	
	@Autowired
	UsuarioDAO usuarioDAO;
	
	SolicitacaoHomologacaoProducaoAcademica solicitacaoHomologacaoProducaoAcademica;
	
	@Override
	public GenericDAO<ProducaoAcademica> getGenericDAO() {
		return producaoAcademicaDAO;
	}

	public ProducaoAcademicaDAO getProducaoAcademicaDAO() {
		return producaoAcademicaDAO;
	}

	public void setProducaoAcademicaDAO(ProducaoAcademicaDAO producaoAcademicaDAO) {
		this.producaoAcademicaDAO = producaoAcademicaDAO;
	}
	
	public SolicitacaoHomologacaoProducaoAcademicaDAO getSolicitacaoHomologacaoProducaoAcademicaDAO() {
		return solicitacaoHomologacaoProducaoAcademicaDAO;
	}

	public void setSolicitacaoHomologacaoProducaoAcademicaDAO(
			SolicitacaoHomologacaoProducaoAcademicaDAO solicitacaoHomologacaoProducaoAcademicaDAO) {
		this.solicitacaoHomologacaoProducaoAcademicaDAO = solicitacaoHomologacaoProducaoAcademicaDAO;
	}

	public SolicitacaoHomologacaoProducaoAcademica getSolicitacaoHomologacaoProducaoAcademica() {
		return solicitacaoHomologacaoProducaoAcademica;
	}

	public void setSolicitacaoHomologacaoProducaoAcademica(
			SolicitacaoHomologacaoProducaoAcademica solicitacaoHomologacaoProducaoAcademica) {
		this.solicitacaoHomologacaoProducaoAcademica = solicitacaoHomologacaoProducaoAcademica;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	public List<ProducaoAcademica> recuperaPorPesquisadorAtivo(Pesquisador pesquisador){
		return producaoAcademicaDAO.recuperaPorPesquisadorAtivo(pesquisador);
	}

	@Override
	protected void depoisSalvar(ProducaoAcademica objeto) throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();  
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();  
		HttpSession session = request.getSession(false);
		
		String tipoUsuario = (String) session.getAttribute("tipoUsuario");
		Integer idUsuario = (Integer) session.getAttribute("idUsuario");
		
		if(tipoUsuario.equals("Pesquisador")){
			solicitacaoHomologacaoProducaoAcademica = new SolicitacaoHomologacaoProducaoAcademica();
			solicitacaoHomologacaoProducaoAcademica.setProducaoAcademica(objeto);
			solicitacaoHomologacaoProducaoAcademica.setUsuarioPesquisador(usuarioDAO.recuperarPorId(idUsuario));
			solicitacaoHomologacaoProducaoAcademica.setDataSolicitacao(Calendar.getInstance().getTime());
			
			solicitacaoHomologacaoProducaoAcademicaDAO.salvar(solicitacaoHomologacaoProducaoAcademica);
		}
	}
}
