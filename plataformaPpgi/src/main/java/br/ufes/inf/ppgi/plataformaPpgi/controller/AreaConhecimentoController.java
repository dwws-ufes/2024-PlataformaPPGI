package br.ufes.inf.ppgi.plataformaPpgi.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.ValidationException;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;

import br.ufes.inf.ppgi.plataformaPpgi.domain.AreaConhecimento;
import br.ufes.inf.ppgi.plataformaPpgi.service.AreaConhecimentoService;


@ManagedBean
@ViewScoped
public class AreaConhecimentoController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6334707440285208226L;

	@ManagedProperty(value="#{areaConhecimentoService}")
	private AreaConhecimentoService areaConhecimentoService;
	
	private AreaConhecimento areaConhecimento;
	private AreaConhecimento areaConhecimentoSelecionado;
	
	private List<AreaConhecimento> listaAreaConhecimento;
	
	@PostConstruct
	public void init() {
		listaAreaConhecimento = new ArrayList<AreaConhecimento>();
		listaAreaConhecimento = areaConhecimentoService.recuperarTodos();
		areaConhecimento = new AreaConhecimento();
	}

	public AreaConhecimentoService getAreaConhecimentoService() {
		return areaConhecimentoService;
	}

	public void setAreaConhecimentoService(AreaConhecimentoService areaConhecimentoService) {
		this.areaConhecimentoService = areaConhecimentoService;
	}

	public AreaConhecimento getAreaConhecimento() {
		return areaConhecimento;
	}

	public void setAreaConhecimento(AreaConhecimento areaConhecimento) {
		this.areaConhecimento = areaConhecimento;
	}

	public AreaConhecimento getAreaConhecimentoSelecionado() {
		return areaConhecimentoSelecionado;
	}

	public void setAreaConhecimentoSelecionado(AreaConhecimento areaConhecimentoSelecionado) {
		this.areaConhecimentoSelecionado = areaConhecimentoSelecionado;
	}

	public List<AreaConhecimento> getListaAreaConhecimento() {
		return listaAreaConhecimento;
	}

	public void setListaAreaConhecimento(List<AreaConhecimento> listaAreaConhecimento) {
		this.listaAreaConhecimento = listaAreaConhecimento;
	}
	
	public void onRowSelectAreaConhecimento() {
		areaConhecimento = areaConhecimentoService.recuperarPorId(areaConhecimentoSelecionado.getId());
	}
	
	public void novoAreaConhecimento() {
		areaConhecimento = new AreaConhecimento();
	}
	
	public void cancelarAreaConhecimento() {
		areaConhecimento = new AreaConhecimento();
	}
	
	public void buscarDescricaoArea() {
		String busca = areaConhecimento.getDescricaoAreaConhecimento();
		String query = "";
		if(busca != null) {
			query = "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
					"PREFIX dbp: <http://dbpedia.org/property/>\n" +
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
					"SELECT ?desc\n" +
					"WHERE {\n" +
					" ?uri a dbo:City ;\n" +
					" dbp:name \"Rio de Janeiro\"@en ;\n" +
					" rdfs:comment ?desc .\n" +
					"FILTER (langMatches(lang(?desc), \"EN\"))\n" +
					"}";
		}
		
		QueryExecution queryExecution =	QueryExecutionFactory.sparqlService("https://dbpedia.org/sparql", query);
		
		ResultSet resultado = queryExecution.execSelect();
		
		if (resultado.hasNext()) {
			QuerySolution querySolution = resultado.next();
			Literal literal = querySolution.getLiteral("desc");
			areaConhecimento.setResumo("" + literal.getValue());
		}		
	}

	public void salvarAreaConhecimento(){
		try {
			areaConhecimentoService.salvar(areaConhecimento);
			listaAreaConhecimento = areaConhecimentoService.recuperarTodos();		
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Área de conhecimento salvo com sucesso.",
					"A área de conhecimento foi salvo com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (ValidationException e) {
			listaAreaConhecimento = areaConhecimentoService.recuperarTodos();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar a área de conhecimento.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		} catch (Exception e) {

			listaAreaConhecimento = areaConhecimentoService.recuperarTodos();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar a área de conhecimento.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}

	}
}
