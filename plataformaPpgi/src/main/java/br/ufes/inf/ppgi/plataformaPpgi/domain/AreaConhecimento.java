package br.ufes.inf.ppgi.plataformaPpgi.domain;

import java.io.Serializable;
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
@Table(schema = "plataformappgi", name="areaconhecimento")
public class AreaConhecimento extends ObjetoPersistente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1518531240892134158L;

	@Id
	@Column(name="idAreaConhecimento")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idAreaConhecimento;
	
	@NotNull
	@Column(name="descricaoAreaConhecimento")
	private String descricaoAreaConhecimento;
	
	@ManyToOne
	@JoinColumn(name="idAreaConhecimentoSup")
	private AreaConhecimento areaConhecimentoSup;
	
	@Column(name="resumo")
	private String resumo;

	public Integer getId() {
		return idAreaConhecimento;
	}

	public void setId(Integer idAreaConhecimento) {
		this.idAreaConhecimento = idAreaConhecimento;
	}

	public String getDescricaoAreaConhecimento() {
		return descricaoAreaConhecimento;
	}

	public void setDescricaoAreaConhecimento(String descricaoAreaConhecimento) {
		this.descricaoAreaConhecimento = descricaoAreaConhecimento;
	}

	public AreaConhecimento getAreaConhecimentoSup() {
		return areaConhecimentoSup;
	}

	public void setAreaConhecimentoSup(AreaConhecimento areaConhecimentoSup) {
		this.areaConhecimentoSup = areaConhecimentoSup;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(areaConhecimentoSup, descricaoAreaConhecimento, idAreaConhecimento, resumo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AreaConhecimento other = (AreaConhecimento) obj;
		return Objects.equals(areaConhecimentoSup, other.areaConhecimentoSup)
				&& Objects.equals(descricaoAreaConhecimento, other.descricaoAreaConhecimento)
				&& Objects.equals(idAreaConhecimento, other.idAreaConhecimento) && Objects.equals(resumo, other.resumo);
	}

}
