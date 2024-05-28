package br.ufes.inf.ppgi.plataformaPpgi.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

public class Pesquisador extends ObjetoPersistente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4684623770969982498L;

	@Id
	@Column(name="idPesquisador")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idPesquisador;
	
	@NotNull
	@Column(name="nomePesquisador")
	private String nomePesquisador;
	
	@NotNull
	@Column(name="cpf")
	private String cpf;
	
	@NotNull
	@Column(name="orcid")
	private String orcid;
	
	@NotNull
	@Column(name="dataInicioPrograma")
	private Date dataInicioPrograma;
	
	@Column(name="dataFimPrograma")
	private Date dataFimPrograma;
	
	@NotNull
	@Column(name="email")
	private String email;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idTipoPesquisador")
	private TipoPesquisador tipoPesquisador;

	public Integer getId() {
		return idPesquisador;
	}

	public void setId(Integer idPesquisador) {
		this.idPesquisador = idPesquisador;
	}

	public String getNomePesquisador() {
		return nomePesquisador;
	}

	public void setNomePesquisador(String nomePesquisador) {
		this.nomePesquisador = nomePesquisador;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getOrcid() {
		return orcid;
	}

	public void setOrcid(String orcid) {
		this.orcid = orcid;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoPesquisador getTipoPesquisador() {
		return tipoPesquisador;
	}

	public void setTipoPesquisador(TipoPesquisador tipoPesquisador) {
		this.tipoPesquisador = tipoPesquisador;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, dataFimPrograma, dataInicioPrograma, email, idPesquisador, nomePesquisador, orcid,
				tipoPesquisador);
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
		return Objects.equals(cpf, other.cpf) && Objects.equals(dataFimPrograma, other.dataFimPrograma)
				&& Objects.equals(dataInicioPrograma, other.dataInicioPrograma) && Objects.equals(email, other.email)
				&& Objects.equals(idPesquisador, other.idPesquisador)
				&& Objects.equals(nomePesquisador, other.nomePesquisador) && Objects.equals(orcid, other.orcid)
				&& Objects.equals(tipoPesquisador, other.tipoPesquisador);
	}

}
