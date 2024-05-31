package br.ufes.inf.ppgi.plataformaPpgi.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "plataformappgi", name="papelpesquisador")
public class PapelPesquisador extends ObjetoPersistente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1830954745407205891L;

	@Id
	@Column(name="idPapelPesquisador")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idPapelPesquisador;
	
	@NotNull
	@Column(name="nomePapel")
	private String nomePapel;
	
	@Column(name="descricaoPapel")
	private String descricaoPapel;

	public Integer getId() {
		return idPapelPesquisador;
	}

	public void setId(Integer idPapelPesquisador) {
		this.idPapelPesquisador = idPapelPesquisador;
	}

	public Integer getIdPapelPesquisador() {
		return idPapelPesquisador;
	}

	public void setIdPapelPesquisador(Integer idPapelPesquisador) {
		this.idPapelPesquisador = idPapelPesquisador;
	}

	public String getNomePapel() {
		return nomePapel;
	}

	public void setNomePapel(String nomePapel) {
		this.nomePapel = nomePapel;
	}

	public String getDescricaoPapel() {
		return descricaoPapel;
	}

	public void setDescricaoPapel(String descricaoPapel) {
		this.descricaoPapel = descricaoPapel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricaoPapel, idPapelPesquisador, nomePapel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PapelPesquisador other = (PapelPesquisador) obj;
		return Objects.equals(descricaoPapel, other.descricaoPapel)
				&& Objects.equals(idPapelPesquisador, other.idPapelPesquisador)
				&& Objects.equals(nomePapel, other.nomePapel);
	}
}
