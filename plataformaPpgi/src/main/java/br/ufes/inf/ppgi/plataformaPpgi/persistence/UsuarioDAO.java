package br.ufes.inf.ppgi.plataformaPpgi.persistence;

import br.ufes.inf.ppgi.plataformaPpgi.domain.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario>{
	
	public Usuario recuperaCpfExistente(String cpf);
	
	public Usuario retornaUsuario(String login, String senha);

}
