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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ValidationException;

import br.ufes.inf.ppgi.plataformaPpgi.domain.AreaConhecimento;
import br.ufes.inf.ppgi.plataformaPpgi.domain.PapelPesquisador;
import br.ufes.inf.ppgi.plataformaPpgi.domain.Pesquisador;
import br.ufes.inf.ppgi.plataformaPpgi.domain.PesquisadorProducaoAcademica;
import br.ufes.inf.ppgi.plataformaPpgi.domain.ProducaoAcademica;
import br.ufes.inf.ppgi.plataformaPpgi.domain.Projeto;
import br.ufes.inf.ppgi.plataformaPpgi.domain.TipoProducaoAcademica;
import br.ufes.inf.ppgi.plataformaPpgi.domain.Usuario;
import br.ufes.inf.ppgi.plataformaPpgi.service.AreaConhecimentoService;
import br.ufes.inf.ppgi.plataformaPpgi.service.PapelPesquisadorService;
import br.ufes.inf.ppgi.plataformaPpgi.service.PesquisadorProducaoAcademicaService;
import br.ufes.inf.ppgi.plataformaPpgi.service.PesquisadorService;
import br.ufes.inf.ppgi.plataformaPpgi.service.ProducaoAcademicaService;
import br.ufes.inf.ppgi.plataformaPpgi.service.ProjetoService;
import br.ufes.inf.ppgi.plataformaPpgi.service.TipoProducaoAcademicaService;
import br.ufes.inf.ppgi.plataformaPpgi.service.UsuarioService;

@ManagedBean
@ViewScoped
public class ProducaoAcademicaController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1689092552488354150L;

	@ManagedProperty(value="#{producaoAcademicaService}")
	private ProducaoAcademicaService producaoAcademicaService;
	
	@ManagedProperty(value="#{tipoProducaoAcademicaService}")
	private TipoProducaoAcademicaService tipoProducaoAcademicaService;
	
	@ManagedProperty(value="#{pesquisadorService}")
	private PesquisadorService pesquisadorService;
	
	@ManagedProperty(value="#{areaConhecimentoService}")
	private AreaConhecimentoService areaConhecimentoService;
	
	@ManagedProperty(value="#{projetoService}")
	private ProjetoService projetoService;
	
	@ManagedProperty(value="#{papelPesquisadorService}")
	private PapelPesquisadorService papelPesquisadorService;
	
	@ManagedProperty(value="#{pesquisadorProducaoAcademicaService}")
	private PesquisadorProducaoAcademicaService pesquisadorProducaoAcademicaService;
	
	@ManagedProperty(value="#{usuarioService}")
	private UsuarioService usuarioService;
	
	private ProducaoAcademica producaoAcademica;
	private ProducaoAcademica producaoAcademicaSelecionada;
	
	private List<ProducaoAcademica> listaProducaoAcademica;
	private List<TipoProducaoAcademica> listaTipoProducaoAcademica;
	private List<Pesquisador> listaPesquisador;
	private Pesquisador pesquisador;
	private List<Projeto> listaProjetos;
	private List<AreaConhecimento> listaAreaConhecimento;
	private List<PesquisadorProducaoAcademica> listaPesquisadorProducaoAcademcia;
	private List<PapelPesquisador> listaPapelPesquisador;
	private String tipoUsuario;
	private Integer idUsuarioPesquisador;
	private Usuario usuarioPesquisador;
	private Pesquisador pesquisadorUsuario;
	
	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();  
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();  
		HttpSession session = request.getSession(false);
		
		tipoUsuario = (String) session.getAttribute("tipoUsuario");
		idUsuarioPesquisador = (Integer) session.getAttribute("idUsuario");
		if(tipoUsuario.equals("Pesquisador")) {
			setUsuarioPesquisador(usuarioService.recuperarPorId(idUsuarioPesquisador));
			pesquisadorUsuario = pesquisadorService.recuperaAtivoPorPessoa(usuarioPesquisador.getPessoa());
			listaProducaoAcademica = new ArrayList<ProducaoAcademica>();
			listaProducaoAcademica = producaoAcademicaService.recuperaPorPesquisadorAtivo(pesquisadorUsuario);
			listaProjetos = new ArrayList<Projeto>();
			listaProjetos = projetoService.recuperaPorPesquisador(pesquisadorUsuario);
			listaPesquisador = new ArrayList<Pesquisador>();
			listaPesquisador.add(pesquisadorUsuario);
		} else if(tipoUsuario.equals("Administrador")){
			listaProducaoAcademica = new ArrayList<ProducaoAcademica>();
			listaProducaoAcademica = producaoAcademicaService.recuperarTodos();			
			listaPesquisador = new ArrayList<Pesquisador>();
			listaProjetos = new ArrayList<Projeto>();
			listaProjetos = projetoService.recuperarTodos();
			listaPesquisador = new ArrayList<Pesquisador>();			
		}
		listaTipoProducaoAcademica = new ArrayList<TipoProducaoAcademica>();
		listaTipoProducaoAcademica = tipoProducaoAcademicaService.recuperarTodos();
		listaPapelPesquisador = new ArrayList<PapelPesquisador>();
		listaPapelPesquisador = papelPesquisadorService.recuperarTodos();
		listaAreaConhecimento = new ArrayList<AreaConhecimento>();
		listaAreaConhecimento = areaConhecimentoService.recuperarTodos();
		
		novoProducaoAcademica();
	}

	public ProjetoService getProjetoService() {
		return projetoService;
	}

	public void setProjetoService(ProjetoService projetoService) {
		this.projetoService = projetoService;
	}

	public List<Projeto> getListaProjetos() {
		return listaProjetos;
	}

	public void setListaProjetos(List<Projeto> listaProjetos) {
		this.listaProjetos = listaProjetos;
	}

	public ProducaoAcademicaService getProducaoAcademicaService() {
		return producaoAcademicaService;
	}

	public void setProducaoAcademicaService(ProducaoAcademicaService producaoAcademicaService) {
		this.producaoAcademicaService = producaoAcademicaService;
	}

	public ProducaoAcademica getProducaoAcademica() {
		return producaoAcademica;
	}

	public void setProducaoAcademica(ProducaoAcademica producaoAcademica) {
		this.producaoAcademica = producaoAcademica;
	}

	public ProducaoAcademica getProducaoAcademicaSelecionada() {
		return producaoAcademicaSelecionada;
	}

	public void setProducaoAcademicaSelecionada(ProducaoAcademica producaoAcademicaSelecionada) {
		this.producaoAcademicaSelecionada = producaoAcademicaSelecionada;
	}

	public List<ProducaoAcademica> getListaProducaoAcademica() {
		return listaProducaoAcademica;
	}

	public void setListaProducaoAcademica(List<ProducaoAcademica> listaProducaoAcademica) {
		this.listaProducaoAcademica = listaProducaoAcademica;
	}
	
	public TipoProducaoAcademicaService getTipoProducaoAcademicaService() {
		return tipoProducaoAcademicaService;
	}

	public void setTipoProducaoAcademicaService(TipoProducaoAcademicaService tipoProducaoAcademicaService) {
		this.tipoProducaoAcademicaService = tipoProducaoAcademicaService;
	}

	public List<TipoProducaoAcademica> getListaTipoProducaoAcademica() {
		return listaTipoProducaoAcademica;
	}

	public void setListaTipoProducaoAcademica(List<TipoProducaoAcademica> listaTipoProducaoAcademica) {
		this.listaTipoProducaoAcademica = listaTipoProducaoAcademica;
	}

	public PesquisadorService getPesquisadorService() {
		return pesquisadorService;
	}

	public void setPesquisadorService(PesquisadorService pesquisadorService) {
		this.pesquisadorService = pesquisadorService;
	}

	public List<Pesquisador> getListaPesquisador() {
		return listaPesquisador;
	}

	public void setListaPesquisador(List<Pesquisador> listaPesquisador) {
		this.listaPesquisador = listaPesquisador;
	}

	public AreaConhecimentoService getAreaConhecimentoService() {
		return areaConhecimentoService;
	}

	public void setAreaConhecimentoService(AreaConhecimentoService areaConhecimentoService) {
		this.areaConhecimentoService = areaConhecimentoService;
	}

	public List<AreaConhecimento> getListaAreaConhecimento() {
		return listaAreaConhecimento;
	}

	public void setListaAreaConhecimento(List<AreaConhecimento> listaAreaConhecimento) {
		this.listaAreaConhecimento = listaAreaConhecimento;
	}

	public List<PesquisadorProducaoAcademica> getListaPesquisadorProducaoAcademcia() {
		return listaPesquisadorProducaoAcademcia;
	}

	public void setListaPesquisadorProducaoAcademcia(List<PesquisadorProducaoAcademica> listaPesquisadorProducaoAcademcia) {
		this.listaPesquisadorProducaoAcademcia = listaPesquisadorProducaoAcademcia;
	}

	public List<PapelPesquisador> getListaPapelPesquisador() {
		return listaPapelPesquisador;
	}

	public void setListaPapelPesquisador(List<PapelPesquisador> listaPapelPesquisador) {
		this.listaPapelPesquisador = listaPapelPesquisador;
	}

	public PapelPesquisadorService getPapelPesquisadorService() {
		return papelPesquisadorService;
	}

	public void setPapelPesquisadorService(PapelPesquisadorService papelPesquisadorService) {
		this.papelPesquisadorService = papelPesquisadorService;
	}

	public PesquisadorProducaoAcademicaService getPesquisadorProducaoAcademicaService() {
		return pesquisadorProducaoAcademicaService;
	}

	public void setPesquisadorProducaoAcademicaService(
			PesquisadorProducaoAcademicaService pesquisadorProducaoAcademicaService) {
		this.pesquisadorProducaoAcademicaService = pesquisadorProducaoAcademicaService;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Usuario getUsuarioPesquisador() {
		return usuarioPesquisador;
	}

	public void setUsuarioPesquisador(Usuario usuarioPesquisador) {
		this.usuarioPesquisador = usuarioPesquisador;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Pesquisador getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(Pesquisador pesquisador) {
		this.pesquisador = pesquisador;
	}

	public Integer getIdUsuarioPesquisador() {
		return idUsuarioPesquisador;
	}

	public void setIdUsuarioPesquisador(Integer idUsuarioPesquisador) {
		this.idUsuarioPesquisador = idUsuarioPesquisador;
	}

	public Pesquisador getPesquisadorUsuario() {
		return pesquisadorUsuario;
	}

	public void setPesquisadorUsuario(Pesquisador pesquisadorUsuario) {
		this.pesquisadorUsuario = pesquisadorUsuario;
	}

	public void onRowSelectProducaoAcademica(){
		producaoAcademica = producaoAcademicaService.recuperarPorId(producaoAcademicaSelecionada.getId());
		listaPesquisadorProducaoAcademcia = pesquisadorProducaoAcademicaService.recuperarPorProducaoAcademica(producaoAcademicaSelecionada);
	}
	
	public void novoProducaoAcademica() {
		producaoAcademica = new ProducaoAcademica();
		listaPesquisadorProducaoAcademcia = new ArrayList<PesquisadorProducaoAcademica>();
		pesquisador = new Pesquisador();
		producaoAcademica.setIndProjetoIndependente('N');
		if(tipoUsuario.equals("Pesquisador")){
			producaoAcademica.setIndHomologado('N');
		} else {
			producaoAcademica.setIndHomologado('S');
		}
	}
	
	public void cancelarProducaoAcademica() {
		producaoAcademica = new ProducaoAcademica();
		producaoAcademica.setIndProjetoIndependente('N');
		if(tipoUsuario.equals("Pesquisador")){
			producaoAcademica.setIndHomologado('N');
		} else {
			producaoAcademica.setIndHomologado('S');
		}
		listaPesquisadorProducaoAcademcia = new ArrayList<PesquisadorProducaoAcademica>();
		pesquisador = new Pesquisador();
	}
	
	public void indicaProjetoIndependente() {
		if(producaoAcademica.getIndProjetoIndependente().equals('S')) {
			producaoAcademica.setProjeto(null);
			if(tipoUsuario.equals("Pesquisador")) {
				listaPesquisador = new ArrayList<Pesquisador>();
				listaPesquisador.add(pesquisadorUsuario);
			} else {
				listaPesquisador = new ArrayList<Pesquisador>();
				listaPesquisador = pesquisadorService.recuperarTodos();
			}
		} else {
			setListaPesquisador(new ArrayList<Pesquisador>());
			listaProjetos = new ArrayList<Projeto>();
			listaProjetos = projetoService.recuperaPorPesquisador(pesquisadorUsuario);
		}
	}
	
	public void onSelecetProjeto() {
		if(tipoUsuario.equals("Pesquisador")) {
			listaPesquisador = new ArrayList<Pesquisador>();
			listaPesquisador.add(pesquisadorUsuario);
		} else {
			listaPesquisador = pesquisadorService.recuperarAtivosPorProjeto(producaoAcademica.getProjeto());
			pesquisador = new Pesquisador();
		}
	}
	
	public void onSelecetPesquisador() {
		listaPesquisadorProducaoAcademcia.add(new PesquisadorProducaoAcademica());
		listaPesquisadorProducaoAcademcia.get(listaPesquisadorProducaoAcademcia.size()-1).setPesquisador(pesquisador);
		listaPesquisadorProducaoAcademcia.get(listaPesquisadorProducaoAcademcia.size()-1).setProducaoAcademica(producaoAcademica);
	}
	
	public void salvarProducaoAcademica(){
		try {
			
			producaoAcademicaService.salvar(producaoAcademica);
			for(PesquisadorProducaoAcademica pesquisadorProducaoAcademica : listaPesquisadorProducaoAcademcia) {
				pesquisadorProducaoAcademicaService.salvar(pesquisadorProducaoAcademica);
			}
			
			if(tipoUsuario.equals("Pesquisador")) {
				listaProducaoAcademica = producaoAcademicaService.recuperaPorPesquisadorAtivo(pesquisadorUsuario);
			} else {
				listaProducaoAcademica = producaoAcademicaService.recuperarTodos();
			}
			listaProducaoAcademica = producaoAcademicaService.recuperarTodos();
			novoProducaoAcademica();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Produção acadêmica salva com sucesso.",
					"Produção acadêmica foi salvo com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (ValidationException e) {
			if(tipoUsuario.equals("Pesquisador")) {
				listaProducaoAcademica = producaoAcademicaService.recuperaPorPesquisadorAtivo(pesquisadorUsuario);
			} else {
				listaProducaoAcademica = producaoAcademicaService.recuperarTodos();
			}
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar a producão acadêmica.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		} catch (Exception e) {

			if(tipoUsuario.equals("Pesquisador")) {
				listaProducaoAcademica = producaoAcademicaService.recuperaPorPesquisadorAtivo(pesquisadorUsuario);
			} else {
				listaProducaoAcademica = producaoAcademicaService.recuperarTodos();
			}

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar a produção acadêmica.",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}

	}
}
