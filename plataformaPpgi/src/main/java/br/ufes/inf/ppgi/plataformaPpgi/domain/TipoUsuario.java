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
@Table(schema = "plataformappgi", name="tipousuario")
public class TipoUsuario extends ObjetoPersistente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7137838359009707410L;

	@Id
	@Column(name="idTipoUsuario")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idTipoUsuario;
	
	@NotNull
	@Column(name="nomeTipoUsuario")
	private String nomeTipoUsuario;
	
	@Column(name="descricaoTipoUsuario")
	private String descricaoTipoUsuario;

	public Integer getId() {
		return idTipoUsuario;
	}

	public void setId(Integer idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getNomeTipoUsuario() {
		return nomeTipoUsuario;
	}

	public void setNomeTipoUsuario(String nomeTipoUsuario) {
		this.nomeTipoUsuario = nomeTipoUsuario;
	}

	public String getDescricaoTipoUsuario() {
		return descricaoTipoUsuario;
	}

	public void setDescricaoTipoUsuario(String descricaoTipoUsuario) {
		this.descricaoTipoUsuario = descricaoTipoUsuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricaoTipoUsuario, idTipoUsuario, nomeTipoUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoUsuario other = (TipoUsuario) obj;
		return Objects.equals(descricaoTipoUsuario, other.descricaoTipoUsuario)
				&& Objects.equals(idTipoUsuario, other.idTipoUsuario)
				&& Objects.equals(nomeTipoUsuario, other.nomeTipoUsuario);
	}

}
