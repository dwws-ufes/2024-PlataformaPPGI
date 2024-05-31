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
@Table(schema = "plataformappgi", name="pesquisadorprojeto")
public class PesquisadorProjeto extends ObjetoPersistente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3315978140382140384L;

	@Id
	@Column(name="idPesquisadorProjeto")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idPesquisadorProjeto;
	
	@NotNull
	@Column(name="dataInicio")
	private Date dataInicio;
	
	@Column(name="dataFim")
	private Date dataFim;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idPesquisador")
	private Pesquisador pesquisador;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idProjeto")
	private Projeto projeto;

	public Integer getId() {
		return idPesquisadorProjeto;
	}

	public void setId(Integer idPesquisadorProjeto) {
		this.idPesquisadorProjeto = idPesquisadorProjeto;
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

	public Pesquisador getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(Pesquisador pesquisador) {
		this.pesquisador = pesquisador;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataFim, dataInicio, idPesquisadorProjeto, pesquisador, projeto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PesquisadorProjeto other = (PesquisadorProjeto) obj;
		return Objects.equals(dataFim, other.dataFim) && Objects.equals(dataInicio, other.dataInicio)
				&& Objects.equals(idPesquisadorProjeto, other.idPesquisadorProjeto)
				&& Objects.equals(pesquisador, other.pesquisador) && Objects.equals(projeto, other.projeto);
	}
}
