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
@Table(schema = "plataformappgi", name="pesquisador")
public class Pesquisador extends ObjetoPersistente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4684623770969982498L;

	@Id
	@Column(name="idPesquisador")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idPesquisador;
	

	@NotNull
	@Column(name="dataInicioPrograma")
	private Date dataInicioPrograma;
	
	@Column(name="dataFimPrograma")
	private Date dataFimPrograma;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idPessoa")
	private Pessoa pessoa;	
		
	@NotNull
	@ManyToOne
	@JoinColumn(name="idTipoPesquisador")
	private TipoPesquisador tipoPesquisador;
	
	@ManyToOne
	@JoinColumn(name="idUniversidade")
	private Universidade universidade;
	
	public Pesquisador() {
		this.pessoa = new Pessoa();
		this.tipoPesquisador = new TipoPesquisador();
	}

	public Integer getId() {
		return idPesquisador;
	}

	public void setId(Integer idPesquisador) {
		this.idPesquisador = idPesquisador;
	}

	public Integer getIdPesquisador() {
		return idPesquisador;
	}

	public void setIdPesquisador(Integer idPesquisador) {
		this.idPesquisador = idPesquisador;
	}

	public Date getDataInicioPrograma() {
		return dataInicioPrograma;
	}

	public void setDataInicioPrograma(Date dataInicioPrograma) {
		this.dataInicioPrograma = dataInicioPrograma;
	}

	public Date getDataFimPrograma() {
		return dataFimPrograma;
	}

	public void setDataFimPrograma(Date dataFimPrograma) {
		this.dataFimPrograma = dataFimPrograma;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public TipoPesquisador getTipoPesquisador() {
		return tipoPesquisador;
	}

	public void setTipoPesquisador(TipoPesquisador tipoPesquisador) {
		this.tipoPesquisador = tipoPesquisador;
	}

	public Universidade getUniversidade() {
		return universidade;
	}

	public void setUniversidade(Universidade universidade) {
		this.universidade = universidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataFimPrograma, dataInicioPrograma, idPesquisador, pessoa, tipoPesquisador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pesquisador other = (Pesquisador) obj;
		return Objects.equals(dataFimPrograma, other.dataFimPrograma)
				&& Objects.equals(dataInicioPrograma, other.dataInicioPrograma)
				&& Objects.equals(idPesquisador, other.idPesquisador) && Objects.equals(pessoa, other.pessoa)
				&& Objects.equals(tipoPesquisador, other.tipoPesquisador);
	}

	
}