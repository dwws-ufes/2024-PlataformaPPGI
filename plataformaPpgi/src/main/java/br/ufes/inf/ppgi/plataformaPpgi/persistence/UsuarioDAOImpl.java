package br.ufes.inf.ppgi.plataformaPpgi.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Usuario;

@Repository
public class UsuarioDAOImpl extends GenericDAOImpl<Usuario> implements UsuarioDAO{

	@SuppressWarnings("unchecked")
	public Usuario recuperaCpfExistente(String cpf) {
		List<Usuario> usuario = new ArrayList<Usuario>();
		usuario = super.getEntityManager().createQuery("select u from Usuario u "
				+ "inner join Pessoa pe on pe.idPessoa = u.pessoa.idPessoa "
				+ "where pe.cpf = :codigo and u.dataValidade is null").setParameter("codigo", cpf).getResultList();
		if (usuario.size() > 0){
			return usuario.get(0);
		}else{
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public Usuario retornaUsuario(String login, String senha) {
		List<Usuario> usuario = new ArrayList<Usuario>();
		usuario = super.getEntityManager().createQuery("select u from Usuario u "
				+ "where u.login = :login and u.senha = :senha and u.dataValidade > now()").setParameter("login", login).setParameter("senha", senha).getResultList();
		if (usuario.size() > 0){
			return usuario.get(0);
		}else{
			return null;
		}
	}

}
