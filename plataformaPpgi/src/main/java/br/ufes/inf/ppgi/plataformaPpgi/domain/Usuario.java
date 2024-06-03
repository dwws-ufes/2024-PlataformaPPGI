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
@Table(schema = "plataformappgi", name="usuario")
public class Usuario extends ObjetoPersistente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1650470895987769366L;

	@Id
	@Column(name="idUsuario")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idUsuario;
	
	@NotNull
	@Column(name="login")
	private String login;
	
	@NotNull
	@Column(name="senha")
	private String senha;
	
	@NotNull
	@Column(name="dataValidade")
	private Date dataValidade;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idPessoa")
	private Pessoa pessoa;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idTipoUsuario")
	private TipoUsuario tipoUsuario;
	
	public Usuario() {
		this.pessoa = new Pessoa();
		this.tipoUsuario = new TipoUsuario();
	}

	public Integer getId() {
		return idUsuario;
	}

	public void setId(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataValidade, idUsuario, login, pessoa, senha, tipoUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(dataValidade, other.dataValidade) && Objects.equals(idUsuario, other.idUsuario)
				&& Objects.equals(login, other.login) && Objects.equals(pessoa, other.pessoa)
				&& Objects.equals(senha, other.senha) && Objects.equals(tipoUsuario, other.tipoUsuario);
	}
}
