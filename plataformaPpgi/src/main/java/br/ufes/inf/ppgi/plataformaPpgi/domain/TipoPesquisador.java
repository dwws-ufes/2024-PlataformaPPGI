package br.ufes.inf.ppgi.plataformaPpgi.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class TipoPesquisador extends ObjetoPersistente implements Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7149615211741382343L;

	@Id
	@Column(name="idTipoPesquisador")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idTipoPesquisador;
	
	@NotNull
	@Column(name="nomeTipoPesquisador")
	private String nomeTipoPesquisador;
	
	@Column(name="descricaoTipoPesquisador")
	private String descricaoTipoPesquisador;

	public Integer getId() {
		return idTipoPesquisador;
	}

	public void setId(Integer idTipoPesquisador) {
		this.idTipoPesquisador = idTipoPesquisador;
	}

	public String getNomeTipoPesquisador() {
		return nomeTipoPesquisador;
	}

	public void setNomeTipoPesquisador(String nomeTipoPesquisador) {
		this.nomeTipoPesquisador = nomeTipoPesquisador;
	}

	public String getDescricaoTipoPesquisador() {
		return descricaoTipoPesquisador;
	}

	public void setDescricaoTipoPesquisador(String descricaoTipoPesquisador) {
		this.descricaoTipoPesquisador = descricaoTipoPesquisador;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricaoTipoPesquisador, idTipoPesquisador, nomeTipoPesquisador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoPesquisador other = (TipoPesquisador) obj;
		return Objects.equals(descricaoTipoPesquisador, other.descricaoTipoPesquisador)
				&& Objects.equals(idTipoPesquisador, other.idTipoPesquisador)
				&& Objects.equals(nomeTipoPesquisador, other.nomeTipoPesquisador);
	}

}
