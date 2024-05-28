package br.ufes.inf.ppgi.plataformaPpgi.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import br.ufes.inf.ppgi.plataformaPpgi.domain.ObjetoPersistente;
import br.ufes.inf.ppgi.plataformaPpgi.persistence.GenericDAO;

@Service
public abstract class CRUDService <T extends ObjetoPersistente>{

	public abstract GenericDAO<T> getGenericDAO();

	public void excluir(T objeto) throws Exception {

		// Executa as ações necessárias antes da exclusão de um objeto.
		antesExcluir(objeto);

		// Exclui o objeto
		getGenericDAO().excluir(objeto);

		// Executa as ações necessárias depois da exclusão de um objeto.
		depoisExcluir(objeto);

	}

	public void excluir(Set<T> objetos) throws Exception  {
		
		Iterator<T> itItensSelecionados = objetos.iterator();

		while (itItensSelecionados.hasNext()) {
			T objeto = itItensSelecionados.next();
			excluir(objeto);

		}

	}	

	/**
	 * Executa as ações necessárias antes da exclusão de um objeto. As
	 * aplicações que herdam desta classe devem sobrescrever este método para
	 * obter um comportamento específico antes da exclusão.
	 * 
	 * @param objeto
	 *            Objeto sendo excluído.
	 * @throws Exception
	 *             Caso ocorra alguma exceção.
	 */
	protected void antesExcluir(T objeto) throws Exception  {
	}

	/**
	 * Executa as ações necessárias depois da exclusão de um objeto. As
	 * aplicações que herdam desta classe devem sobrescrever este método para
	 * obter um comportamento específico depois da exclusão.
	 * 
	 * @param objeto
	 *            Objeto sendo excluído.
	 * @throws Exception
	 *             Caso ocorra alguma exceção.
	 */
	protected void depoisExcluir(T objeto) throws Exception  {
	}

	public T recuperarPorId(Integer id) {
		return getGenericDAO().recuperarPorId(id);
	}

	public List<T> recuperarTodos() {
		return getGenericDAO().recuperarTodos();
	}

	public T salvar(T objeto) throws Exception  {
		// Executa a ação necessária antes do salvamento do objeto
		antesSalvar(objeto);

		// Salva
		this.getGenericDAO().salvar(objeto);

		// Executa a ação necessária depois do salvamento do objeto
		depoisSalvar(objeto);

		return objeto;
	}

	/**
	 * Executa as ações necessárias antes da gravação (inclusão ou alteração de
	 * dados) de um objeto. As aplicações que herdam desta classe devem
	 * sobrescrever este método para obter um comportamento específico antes da
	 * gravação.
	 * 
	 * @param objeto
	 *            Objeto sendo gravado.
	 * @throws Exception
	 *             Caso ocorra alguma exceção.
	 */
	protected void antesSalvar(T objeto) throws Exception {
	}

	/**
	 * Executa as ações necessárias depois da gravação (inclusão ou alteração de
	 * dados) de um objeto. As aplicações que herdam desta classe devem
	 * sobrescrever este método para obter um comportamento específico depois da
	 * gravação.
	 * 
	 * @param objeto
	 *            Objeto sendo gravado.
	 * @throws Exception
	 *             Caso ocorra alguma exceção.
	 */
	protected void depoisSalvar(T objeto) throws Exception  {
	}

	
}
