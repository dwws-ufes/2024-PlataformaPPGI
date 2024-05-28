package br.ufes.inf.ppgi.plataformaPpgi.persistence;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.ppgi.plataformaPpgi.domain.ObjetoPersistente;

public class GenericDAOImpl<T extends ObjetoPersistente> implements GenericDAO<T> {
	
	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(T objeto) {
		if (objeto.isPersistente()){
			entityManager.merge(objeto);
		}
		else {
			entityManager.persist(objeto);
		}
	}

	public void excluir(T objeto) {
		entityManager.remove(entityManager.find(this.getClasseDominio(),objeto.getId()));
	}

	@SuppressWarnings("unchecked")
	public List<T> recuperarTodos() {

		List<T> lista = entityManager.createQuery("from " + getClasseDominio().getSimpleName()).getResultList();
		return lista;
	}
	
	public T recuperarPorId(Integer id) {
		T objeto = null;
		objeto = (T) entityManager.find(getClasseDominio(), id);
		
		

		return objeto;
	}
	
	@SuppressWarnings("unchecked")
	public Class<T> getClasseDominio() {

		ParameterizedType parameterizedType = (ParameterizedType) getClass()
		.getGenericSuperclass();

		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
}
