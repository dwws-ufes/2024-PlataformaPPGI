package br.ufes.inf.ppgi.plataformaPpgi.persistence;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;


/**
 * Interface GenericDAO. Contém métodos genéricos para serem utilizados por qualquer objeto persistente.
 *
 */
@Transactional
public interface GenericDAO <T extends Object> {

	/** Salva um objeto de domínio na mídia persistente.
	 *
	 * @param objeto
	 *            Objeto de domínio.
	 */
	public void salvar(T objeto);

	/**
	 * Exclui um objeto de domínio da mídia persistente.
	 *
	 * @param objeto
	 *            Objeto de domínio.
	 */
	public void excluir(T objeto);

	/**
	 * Recupera todos os objetos persistentes da classe de domínio T.
	 *
	 * @return Lista de todos os objetos persistentes da classe de domínio T.
	 */
	public List<T> recuperarTodos();

	/**
	 * Obtém um objeto persistente da classe de domínio T, de acordo com seu identificador.
	 *
	 * @param id
	 *            Identificador do objeto persistente.
	 * @return O objeto persistente cujo identificador foi dado.
	 */
	public T recuperarPorId(Integer id);

	/**
	 * Recupera a classe do objeto T.
	 * @return Classe do objeto T.
	 */
	public Class<T> getClasseDominio();
}
