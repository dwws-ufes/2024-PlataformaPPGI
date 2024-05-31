package br.ufes.inf.ppgi.plataformaPpgi.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "plataformappgi", name="projeto")
public class Projeto extends ObjetoPersistente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5493149559257215423L;

	@Id
	@Column(name="idProjeto")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idProjeto;
	
	@NotNull
	@Column(name="nomeProjeto")
	private String nomeProjeto;
	
	@Column(name="descricaoProjeto")
	private String descricaoProjeto;
	
	@NotNull
	@Column(name="codigoProjeto")
	private String codigoProjeto;
	
	@NotNull
	@Column(name="dataInicio")
	private Date dataInicio;
	
	@Column(name="dataFim")
	private Date dataFim;

	public Integer getId() {
		return idProjeto;
	}

	public void setId(Integer idProjeto) {
		this.idProjeto = idProjeto;
	}

	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	public String getDescricaoProjeto() {
		return descricaoProjeto;
	}

	public void setDescricaoProjeto(String descricaoProjeto) {
		this.descricaoProjeto = descricaoProjeto;
	}

	public String getCodigoProjeto() {
		return codigoProjeto;
	}

	public void setCodigoProjeto(String codigoProjeto) {
		this.codigoProjeto = codigoProjeto;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoProjeto, dataFim, dataInicio, descricaoProjeto, idProjeto, nomeProjeto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		return Objects.equals(codigoProjeto, other.codigoProjeto) && Objects.equals(dataFim, other.dataFim)
				&& Objects.equals(dataInicio, other.dataInicio)
				&& Objects.equals(descricaoProjeto, other.descricaoProjeto)
				&& Objects.equals(idProjeto, other.idProjeto) && Objects.equals(nomeProjeto, other.nomeProjeto);
	}

}
