package br.com.leonardo.pre_dojo.dao.base;

import javax.persistence.EntityManager;

import br.com.leonardo.pre_dojo.entidade.base.AbstractEntity;
import br.com.leonardo.pre_dojo.interfaces.CrudEntity;
import br.com.leonardo.pre_dojo.utils.JPAUtil;


/**
 * @author turbiani
 *
 * @param <T>
 */
public abstract class AbstractDAO<T extends AbstractEntity> implements CrudEntity<T>{
	
	protected EntityManager em;
	
	/**
	 * Default Constructor
	 */
	public AbstractDAO(){}
	
	/**
	 * @param em
	 */
	public AbstractDAO(EntityManager em){
		this.em = em;
	}
	
	protected EntityManager getEm(){
		if(this.em==null){
			this.em = new JPAUtil().getEntityManager();
		}
		return this.em;
	}
	
	protected void fechaEm(){
		this.getEm().close();
	}	

}
