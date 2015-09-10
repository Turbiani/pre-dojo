package br.com.leonardo.pre_dojo.factory;

import br.com.leonardo.pre_dojo.entidade.base.AbstractEntity;
import br.com.leonardo.pre_dojo.interfaces.CrudEntity;

public final class DaoFactory {
	
	private static final DaoFactory INSTANCE = new DaoFactory();
	
	//Construtor privado para Singleton
	private DaoFactory(){}
	
	public static DaoFactory getInstance(){
		return INSTANCE;
	}
	
	
	@SuppressWarnings("unchecked")
	public <T extends AbstractEntity>CrudEntity<T> createDao(Class<T> typeParameterClass) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		//SUPER HARD CODE, POREM CONTANDO COM A CoC = CONVENTION OVER CONFIGURATION =]
    	String nomeDaClasse = typeParameterClass.getSimpleName();
    	String nomeDoDao    = "br.com.leonardo.pre_dojo.dao."  
    			+ nomeDaClasse 
    			+ "DAO";
    	
    	return (CrudEntity<T>) Class.forName(nomeDoDao).newInstance();
	} 
	
}
