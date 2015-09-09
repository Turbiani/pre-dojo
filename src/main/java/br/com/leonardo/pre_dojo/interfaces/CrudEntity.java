package br.com.leonardo.pre_dojo.interfaces;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.leonardo.pre_dojo.entidade.base.AbstractEntity;

/**
 * @author turbiani
 *
 * @param <T>
 */
public interface CrudEntity<T extends AbstractEntity> {
	
	T busca(Integer id);
	
	T busca(Integer id, EntityManager em);
	
	List<T> lista();
	
	List<T> lista(EntityManager em);
	
	void remove(T object);
	
	void remove(int id);
	
	void remove(T object, EntityManager em);

	void remove(int id, EntityManager em);
	
	void adiciona(T object);
	
	void adiciona(T object, EntityManager em);
	
	
	
	
}
