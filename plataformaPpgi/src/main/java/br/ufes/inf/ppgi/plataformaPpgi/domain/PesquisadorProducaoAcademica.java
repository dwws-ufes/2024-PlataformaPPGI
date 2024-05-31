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
@Table(schema = "plataformappgi", name="pesquisadorproducaoacademica")
public class PesquisadorProducaoAcademica extends ObjetoPersistente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2682836296941854528L;

	@Id
	@Column(name="idPesquisadorProducao")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idPesquisadorProducao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idPesquisador")
	private Pesquisador pesquisador;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idPapelPesquisador")
	private PapelPesquisador papelPesquisador;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idProducaoAcademica")
	private ProducaoAcademica producaoAcademica;

	public Integer getId() {
		return idPesquisadorProducao;
	}

	public void setId(Integer idPesquisadorProducao) {
		this.idPesquisadorProducao = idPesquisadorProducao;
	}

	public Pesquisador getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(Pesquisador pesquisador) {
		this.pesquisador = pesquisador;
	}

	public PapelPesquisador getPapelPesquisador() {
		return papelPesquisador;
	}

	public void setPapelPesquisador(PapelPesquisador papelPesquisador) {
		this.papelPesquisador = papelPesquisador;
	}

	public ProducaoAcademica getProducaoAcademica() {
		return producaoAcademica;
	}

	public void setProducaoAcademica(ProducaoAcademica producaoAcademica) {
		this.producaoAcademica = producaoAcademica;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPesquisadorProducao, papelPesquisador, pesquisador, producaoAcademica);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PesquisadorProducaoAcademica other = (PesquisadorProducaoAcademica) obj;
		return Objects.equals(idPesquisadorProducao, other.idPesquisadorProducao)
				&& Objects.equals(papelPesquisador, other.papelPesquisador)
				&& Objects.equals(pesquisador, other.pesquisador)
				&& Objects.equals(producaoAcademica, other.producaoAcademica);
	}

}
