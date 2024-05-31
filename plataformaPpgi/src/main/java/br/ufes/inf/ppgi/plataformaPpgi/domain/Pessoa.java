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
@Table(schema = "plataformappgi", name="pessoa")
public class Pessoa extends ObjetoPersistente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1719194838147660513L;

	@Id
	@Column(name="idPessoa")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idPessoa;
	
	@NotNull
	@Column(name="nomePessoa")
	private String nomePessoa;
	
	@NotNull
	@Column(name="cpf")
	private String cpf;
	
	@NotNull
	@Column(name="orcid")
	private String orcid;
	
	@NotNull
	@Column(name="email")
	private String email;

	public Integer getId() {
		return idPessoa;
	}

	public void setId(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, email, idPessoa, nomePessoa, orcid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email)
				&& Objects.equals(idPessoa, other.idPessoa) && Objects.equals(nomePessoa, other.nomePessoa)
				&& Objects.equals(orcid, other.orcid);
	}

}
