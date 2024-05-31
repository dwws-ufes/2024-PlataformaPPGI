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
@Table(schema = "plataformappgi", name="tipoproducaoacademica")
public class TipoProducaoAcademica extends ObjetoPersistente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6074431674751886376L;

	@Id
	@Column(name="idTipoProducaoAcademica")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idTipoProducaoAcademica;
	
	@NotNull
	@Column(name="nomeTipoProducaoAcademica")
	private String nomeTipoProducaoAcademica;
	
	@Column(name="descricaoTipoProducaoAcademica")
	private String descricaoTipoProducaoAcademica;

	public Integer getId() {
		return idTipoProducaoAcademica;
	}

	public void setId(Integer idTipoProducaoAcademica) {
		this.idTipoProducaoAcademica = idTipoProducaoAcademica;
	}

	public String getNomeTipoProducaoAcademica() {
		return nomeTipoProducaoAcademica;
	}

	public void setNomeTipoProducaoAcademica(String nomeTipoProducaoAcademica) {
		this.nomeTipoProducaoAcademica = nomeTipoProducaoAcademica;
	}

	public String getDescricaoTipoProducaoAcademica() {
		return descricaoTipoProducaoAcademica;
	}

	public void setDescricaoTipoProducaoAcademica(String descricaoTipoProducaoAcademica) {
		this.descricaoTipoProducaoAcademica = descricaoTipoProducaoAcademica;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricaoTipoProducaoAcademica, idTipoProducaoAcademica, nomeTipoProducaoAcademica);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoProducaoAcademica other = (TipoProducaoAcademica) obj;
		return Objects.equals(descricaoTipoProducaoAcademica, other.descricaoTipoProducaoAcademica)
				&& Objects.equals(idTipoProducaoAcademica, other.idTipoProducaoAcademica)
				&& Objects.equals(nomeTipoProducaoAcademica, other.nomeTipoProducaoAcademica);
	}
	
}
