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
@Table(schema = "plataformappgi", name="universidade")
public class Universidade extends ObjetoPersistente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1854209417805296348L;

	@Id
	@Column(name="idUniversidade")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idUniversidade;
	
	@NotNull
	@Column(name="nomeUniversidade")
	private String nomeUniversidade;
	
	@Column(name="descricaoUniversidade")
	private String descricaoUniversidade;
	
	@Column(name="local")
	private String local;

	public Integer getId() {
		return idUniversidade;
	}

	public void setId(Integer idUniversidade) {
		this.idUniversidade = idUniversidade;
	}

	public String getNomeUniversidade() {
		return nomeUniversidade;
	}

	public void setNomeUniversidade(String nomeUniversidade) {
		this.nomeUniversidade = nomeUniversidade;
	}

	public String getDescricaoUniversidade() {
		return descricaoUniversidade;
	}

	public void setDescricaoUniversidade(String descricaoUniversidade) {
		this.descricaoUniversidade = descricaoUniversidade;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricaoUniversidade, idUniversidade, local, nomeUniversidade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Universidade other = (Universidade) obj;
		return Objects.equals(descricaoUniversidade, other.descricaoUniversidade)
				&& Objects.equals(idUniversidade, other.idUniversidade) && Objects.equals(local, other.local)
				&& Objects.equals(nomeUniversidade, other.nomeUniversidade);
	}
	
}
