package br.com.leonardo.pre_dojo.factory;

import br.com.leonardo.pre_dojo.dao.JogadorDAO;
import br.com.leonardo.pre_dojo.dao.base.AbstractDAO;
import br.com.leonardo.pre_dojo.entidade.Jogador;
import br.com.leonardo.pre_dojo.interfaces.CrudEntity;

public final class DaoFactory {
	
	private static final DaoFactory INSTANCE = new DaoFactory();
	
	//Construtor privado para Singleton
	private DaoFactory(){}
	
	public static DaoFactory getInstance(){
		return INSTANCE;
	}
	
	
	@SuppressWarnings("rawtypes")
	public CrudEntity createDao(Class typeParameterClass) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		//SUPER HARD CODE, POREM CONTANDO COM A CoC = CONVENTION OVER CONFIGURATION =]
    	String nomeDaClasse = typeParameterClass.getSimpleName();
    	String nomeDoDao    = "br.com.leonardo.pre_dojo.dao."  
    			+ nomeDaClasse 
    			+ "DAO";
    	
    	return (CrudEntity) Class.forName(nomeDoDao).newInstance();
	} 
	
	/*public static void main(String[] args) {
		try {
			JogadorDAO jogadorDAO = null;
			jogadorDAO = (JogadorDAO) DaoFactory.getInstance().createDao(Jogador.class);
			System.out.println("CRIADO " + jogadorDAO.nome);
		}catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

}
