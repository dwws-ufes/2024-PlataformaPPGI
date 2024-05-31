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
@Table(schema = "plataformappgi", name="producaoacademica")
public class ProducaoAcademica extends ObjetoPersistente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6102964772951327325L;

	@Id
	@Column(name="idProducaoAcademica")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idProducaoAcademica;
	
	@NotNull
	@Column(name="tituloProducao")
	private String tituloProducao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idTipoProducaoAcademica")
	private TipoProducaoAcademica tipoProducaoAcademica;
	
	@ManyToOne
	@JoinColumn(name="idProjeto")
	private Projeto projeto;
	
	@Column(name="descricaoProducaoAcademica")
	private String descricaoProducaoAcademica;
	
	@NotNull
	@Column(name="dataPublicacao")
	private Date dataPublicacao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idAreaConhecimento")
	private AreaConhecimento areaConhecimento;
	
	@NotNull
	@Column(name="indProjetoIndependente")
	private Character indProjetoIndependente;

	public Integer getId() {
		return idProducaoAcademica;
	}

	public void setId(Integer idProducaoAcademica) {
		this.idProducaoAcademica = idProducaoAcademica;
	}

	public String getTituloProducao() {
		return tituloProducao;
	}

	public void setTituloProducao(String tituloProducao) {
		this.tituloProducao = tituloProducao;
	}

	public TipoProducaoAcademica getTipoProducaoAcademica() {
		return tipoProducaoAcademica;
	}

	public void setTipoProducaoAcademica(TipoProducaoAcademica tipoProducaoAcademica) {
		this.tipoProducaoAcademica = tipoProducaoAcademica;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public String getDescricaoProducaoAcademica() {
		return descricaoProducaoAcademica;
	}

	public void setDescricaoProducaoAcademica(String descricaoProducaoAcademica) {
		this.descricaoProducaoAcademica = descricaoProducaoAcademica;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public AreaConhecimento getAreaConhecimento() {
		return areaConhecimento;
	}

	public void setAreaConhecimento(AreaConhecimento areaConhecimento) {
		this.areaConhecimento = areaConhecimento;
	}

	public Character getIndProjetoIndependente() {
		return indProjetoIndependente;
	}

	public void setIndProjetoIndependente(Character indProjetoIndependente) {
		this.indProjetoIndependente = indProjetoIndependente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(areaConhecimento, dataPublicacao, descricaoProducaoAcademica, idProducaoAcademica,
				indProjetoIndependente, projeto, tipoProducaoAcademica, tituloProducao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProducaoAcademica other = (ProducaoAcademica) obj;
		return Objects.equals(areaConhecimento, other.areaConhecimento)
				&& Objects.equals(dataPublicacao, other.dataPublicacao)
				&& Objects.equals(descricaoProducaoAcademica, other.descricaoProducaoAcademica)
				&& Objects.equals(idProducaoAcademica, other.idProducaoAcademica)
				&& Objects.equals(indProjetoIndependente, other.indProjetoIndependente)
				&& Objects.equals(projeto, other.projeto)
				&& Objects.equals(tipoProducaoAcademica, other.tipoProducaoAcademica)
				&& Objects.equals(tituloProducao, other.tituloProducao);
	}

}
