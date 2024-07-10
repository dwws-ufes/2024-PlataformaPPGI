package br.ufes.inf.ppgi.plataformaPpgi.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import br.ufes.inf.ppgi.plataformaPpgi.domain.PesquisadorProducaoAcademica;
import br.ufes.inf.ppgi.plataformaPpgi.domain.ProducaoAcademica;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.PesquisadorProducaoAcademicaDAO;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.ProducaoAcademicaDAO;

@WebServlet(urlPatterns = { "/rdf/producaoPesquisador" })
public class PesquisadoresProducaoRdfServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8632755148678061508L;
	
	@Autowired
	ProducaoAcademicaDAO producaoAcademicaDAO;
	
	@Autowired
	PesquisadorProducaoAcademicaDAO pesquisadorProducaoAcademicaDAO;
	
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	private List<ProducaoAcademica> listaProducaoAcademica;
	
	public ProducaoAcademicaDAO getProducaoAcademicaDAO() {
		return producaoAcademicaDAO;
	}

	public void setProducaoAcademicaDAO(ProducaoAcademicaDAO producaoAcademicaDAO) {
		this.producaoAcademicaDAO = producaoAcademicaDAO;
	}

	public PesquisadorProducaoAcademicaDAO getPesquisadorProducaoAcademicaDAO() {
		return pesquisadorProducaoAcademicaDAO;
	}

	public void setPesquisadorProducaoAcademicaDAO(PesquisadorProducaoAcademicaDAO pesquisadorProducaoAcademicaDAO) {
		this.pesquisadorProducaoAcademicaDAO = pesquisadorProducaoAcademicaDAO;
	}

	public List<ProducaoAcademica> getListaProducaoAcademica() {
		return listaProducaoAcademica;
	}

	public void setListaProducaoAcademica(List<ProducaoAcademica> listaProducaoAcademica) {
		this.listaProducaoAcademica = listaProducaoAcademica;
	}

	public static DateFormat getDf() {
		return df;
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/xml");
		listaProducaoAcademica = producaoAcademicaDAO.recuperarTodos();
		for(ProducaoAcademica producao : listaProducaoAcademica) {
			producao.setListaPesquisadorProducaoAcademica(pesquisadorProducaoAcademicaDAO.recuperarPorProducaoAcademica(producao));
		}
		
		Model model = ModelFactory.createDefaultModel();
		
		String myNS = "http://localhost:8080/plataformappgi/rdf/producaoPesquisador/";
		String grNS = "http://purl.org/goodrelations/v1#";		
		model.setNsPrefix("gr", grNS);
		
		Resource grProducao = ResourceFactory.createResource(grNS + "Produção");
		Resource grPesquisador = ResourceFactory.createResource(grNS + "Pesquisador");
		Property grPossuiPesquisador = ResourceFactory.createProperty(grNS + "grPossuiPesquisador");
		Property grPapelPesquisador = ResourceFactory.createProperty(grNS + "PapelPesquisador");
		Property grNome = ResourceFactory.createProperty(grNS + "NomePesquisador");
		Property grTituloProducao = ResourceFactory.createProperty(grNS + "TítuloProdução");
		Property grDescricaoProducao = ResourceFactory.createProperty(grNS + "DescriçãoProdução");
		Property grTipoProducao = ResourceFactory.createProperty(grNS + "TipoProdução");
		
		for(ProducaoAcademica producao : listaProducaoAcademica) {
			for(PesquisadorProducaoAcademica pesquisadorProducao : producao.getListaPesquisadorProducaoAcademica()) {
				model.createResource(myNS + producao.getId())
				.addProperty(RDF.type, grProducao)
				.addProperty(grTituloProducao, producao.getTituloProducao())
				.addProperty(grDescricaoProducao, producao.getDescricaoProducaoAcademica())
				.addProperty(grTipoProducao, producao.getTipoProducaoAcademica().getNomeTipoProducaoAcademica())
				.addProperty(grPossuiPesquisador, model.createResource().addProperty(RDF.type, grPesquisador)
				.addProperty(grNome, pesquisadorProducao.getPesquisador().getPessoa().getNomePessoa()).addProperty(grPapelPesquisador, pesquisadorProducao.getPapelPesquisador().getNomePapel()));
			}
		}
		
		try (PrintWriter out = resp.getWriter()) {
			model.write(out, "RDF/XML");
		}
	}	
}
