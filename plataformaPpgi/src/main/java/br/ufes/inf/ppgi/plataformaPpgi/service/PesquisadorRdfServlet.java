package br.ufes.inf.ppgi.plataformaPpgi.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Pesquisador;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.PesquisadorDAO;

@WebServlet(urlPatterns = { "/rdf/pesquisador" })
public class PesquisadorRdfServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5771950171798237649L;

	@Autowired
	PesquisadorDAO pesquisadorDAO;
	
	private List<Pesquisador> listaPesquisadores;

	public PesquisadorDAO getPesquisadorDAO() {
		return pesquisadorDAO;
	}

	public void setPesquisadorDAO(PesquisadorDAO pesquisadorDAO) {
		this.pesquisadorDAO = pesquisadorDAO;
	}

	public List<Pesquisador> getListaPesquisadores() {
		return listaPesquisadores;
	}

	public void setListaPesquisadores(List<Pesquisador> listaPesquisadores) {
		this.listaPesquisadores = listaPesquisadores;
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/xml");
		listaPesquisadores = pesquisadorDAO.recuperarTodos();
		
		Model model = ModelFactory.createDefaultModel();
		
		String myNS = "http://localhost:8080/plataformappgi/rdf/producaoPesquisador/";
		String grNS = "http://purl.org/goodrelations/v1#";		
		model.setNsPrefix("gr", grNS);
		
		Resource grPesquisador = ResourceFactory.createResource(grNS + "Pesquisador");
		Property grNomePesquisador = ResourceFactory.createProperty(grNS + "Nome");
		Property grTipoPesquisador = ResourceFactory.createProperty(grNS + "TipoPesquisador");
		Property grUniversidade = ResourceFactory.createProperty(grNS + "Universidade");
		Property grOrcid = ResourceFactory.createProperty(grNS + "Orcid");
		
		for(Pesquisador pesquisador : listaPesquisadores) {
			model.createResource(myNS + pesquisador.getId())
			.addProperty(RDF.type, grPesquisador)
			.addProperty(grNomePesquisador, pesquisador.getPessoa().getNomePessoa())
			.addProperty(grTipoPesquisador, pesquisador.getTipoPesquisador().getNomeTipoPesquisador())
			.addProperty(grUniversidade, pesquisador.getUniversidade().getNomeUniversidade())
			.addProperty(grOrcid, pesquisador.getPessoa().getOrcid());
		}
		
		try (PrintWriter out = resp.getWriter()) {
			model.write(out, "RDF/XML");
		}
	}

}
