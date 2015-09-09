package br.com.leonardo.pre_dojo.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author turbiani
 *
 */
public class JPAUtil {
	private static EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("preDojo");
	
	public EntityManager getEntityManager(){
		return factory.createEntityManager();
	}
}
