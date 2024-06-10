package br.ufes.inf.ppgi.plataformaPpgi.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "plataformappgi", name="solicitacaohomologacaoproducaoacademica")
public class SolicitacaoHomologacaoProducaoAcademica extends ObjetoPersistente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6826405423536442377L;

	@Id
	@Column(name="idSolicitacaoHomologacaoProdAcad")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idSolicitacaoHomologacaoProdAcad;
	
	@NotNull
	@Column(name="dataSolicitacao")
	private Date dataSolicitacao;
	
	@Column(name="dataHomologacao")
	private Date dataHomologacao;
	
	@Column(name="justificativa")
	private String justificativa;
	
	@ManyToOne
	@JoinColumn(name="idUsuarioHomologacao")
	private Usuario usuarioHomologacao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idUsuarioPesquisador")
	private Usuario usuarioPesquisador;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idProducaoAcademica")
	private ProducaoAcademica producaoAcademica;

	public Integer getId() {
		return idSolicitacaoHomologacaoProdAcad;
	}

	public void setId(Integer idSolicitacaoHomologacaoProdAcad) {
		this.idSolicitacaoHomologacaoProdAcad = idSolicitacaoHomologacaoProdAcad;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public Date getDataHomologacao() {
		return dataHomologacao;
	}

	public void setDataHomologacao(Date dataHomologacao) {
		this.dataHomologacao = dataHomologacao;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public Usuario getUsuarioHomologacao() {
		return usuarioHomologacao;
	}

	public void setUsuarioHomologacao(Usuario usuarioHomologacao) {
		this.usuarioHomologacao = usuarioHomologacao;
	}

	public Usuario getUsuarioPesquisador() {
		return usuarioPesquisador;
	}

	public void setUsuarioPesquisador(Usuario usuarioPesquisador) {
		this.usuarioPesquisador = usuarioPesquisador;
	}

	public ProducaoAcademica getProducaoAcademica() {
		return producaoAcademica;
	}

	public void setProducaoAcademica(ProducaoAcademica producaoAcademica) {
		this.producaoAcademica = producaoAcademica;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataHomologacao, dataSolicitacao, idSolicitacaoHomologacaoProdAcad, justificativa,
				producaoAcademica, usuarioHomologacao, usuarioPesquisador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SolicitacaoHomologacaoProducaoAcademica other = (SolicitacaoHomologacaoProducaoAcademica) obj;
		return Objects.equals(dataHomologacao, other.dataHomologacao)
				&& Objects.equals(dataSolicitacao, other.dataSolicitacao)
				&& Objects.equals(idSolicitacaoHomologacaoProdAcad, other.idSolicitacaoHomologacaoProdAcad)
				&& Objects.equals(justificativa, other.justificativa)
				&& Objects.equals(producaoAcademica, other.producaoAcademica)
				&& Objects.equals(usuarioHomologacao, other.usuarioHomologacao)
				&& Objects.equals(usuarioPesquisador, other.usuarioPesquisador);
	}
}