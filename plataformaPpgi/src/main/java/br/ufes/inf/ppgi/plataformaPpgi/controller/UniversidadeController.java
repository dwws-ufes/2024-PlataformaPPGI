package br.ufes.inf.ppgi.plataformaPpgi.controller;

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
import org.apache.jena.rdf.model.RDFNode;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Universidade;
import br.ufes.inf.ppgi.plataformaPpgi.service.UniversidadeService;

@ManagedBean
@ViewScoped
public class UniversidadeController {
	
	@ManagedProperty(value="#{universidadeService}")
	private UniversidadeService universidadeService;
	
	private Universidade universidade;
	private Universidade universidadeSelecionada;
	
	private List<Universidade> listaUniversidades;
	
	@PostConstruct
	public void init() {
		listaUniversidades = new ArrayList<Universidade>();
		listaUniversidades = universidadeService.recuperarTodos();
		universidade = new Universidade();
	}

	public UniversidadeService getUniversidadeService() {
		return universidadeService;
	}

	public void setUniversidadeService(UniversidadeService universidadeService) {
		this.universidadeService = universidadeService;
	}

	public Universidade getUniversidade() {
		return universidade;
	}

	public void setUniversidade(Universidade universidade) {
		this.universidade = universidade;
	}

	public Universidade getUniversidadeSelecionada() {
		return universidadeSelecionada;
	}

	public void setUniversidadeSelecionada(Universidade universidadeSelecionada) {
		this.universidadeSelecionada = universidadeSelecionada;
	}

	public List<Universidade> getListaUniversidades() {
		return listaUniversidades;
	}

	public void setListaUniversidades(List<Universidade> listaUniversidades) {
		this.listaUniversidades = listaUniversidades;
	}
	
	public void onRowSelectUniversidade() {
		universidade = universidadeService.recuperarPorId(universidadeSelecionada.getId());
	}
	
	public void novaUniversidade() {
		universidade = new Universidade();
	}
	
	public void cancelarUniversidade() {
		universidade = new Universidade();
	}
	
	public void buscarUniversidade() {
		String busca = universidade.getNomeUniversidade();
		
		String query = "";
		if(busca != null) {
			query = "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
					"PREFIX dbp: <http://dbpedia.org/property/>\n" +
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
					"PREFIX dbr: <http://dbpedia.org/resource/>\n" +
					"SELECT ?desc ?country\n" +
					"WHERE {\n" +
					"?uri a dbo:University;\n" +
					"rdfs:label \"" + busca + "\"@pt;\n" +
					"rdfs:comment ?desc;\n" +
					"dbp:country ?country.\n" +
					"FILTER (langMatches(lang(?desc), \"PT\"))\n" +
					"}";
		}
		
		QueryExecution queryExecution =	QueryExecutionFactory.sparqlService("https://dbpedia.org/sparql", query);
		
		ResultSet resultado = queryExecution.execSelect();
		
		if (resultado.hasNext()) {
			QuerySolution querySolution = resultado.next();
			Literal descricao;
			Literal country;
			RDFNode nodeDesc = querySolution.get("desc");
			RDFNode nodeCountry = querySolution.get("country");
			if(nodeDesc.isLiteral()) {
				descricao = querySolution.getLiteral("desc");
				universidade.setDescricaoUniversidade("" + descricao.getValue());
			} else if(nodeDesc.isResource()){
				universidade.setDescricaoUniversidade(nodeDesc.toString());
			}
			if(nodeCountry.isLiteral()) {
				country = querySolution.getLiteral("country");
				universidade.setLocal("" + country.getValue());
			} else if(nodeCountry.isResource()){	
				universidade.setLocal(nodeCountry.toString());
				String[] aux = universidade.getLocal().split("/");
				universidade.setLocal(aux[aux.length -1].trim().replace("_", " "));
			}

			/*
			 * if(universidade.getLocal().contains("/")) {
			 * universidade.setLocal(universidade.getLocal().su) }
			 */
		}
	}	
	
	public void salvarUniversidade(){
		try {
			universidadeService.salvar(universidade);
			listaUniversidades = universidadeService.recuperarTodos();		
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Universidade salva com sucesso.",
					"A universidade foi salva com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (ValidationException e) {
			listaUniversidades = universidadeService.recuperarTodos();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar a universidade.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		} catch (Exception e) {

			listaUniversidades = universidadeService.recuperarTodos();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar a universidade.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}

	}

}
