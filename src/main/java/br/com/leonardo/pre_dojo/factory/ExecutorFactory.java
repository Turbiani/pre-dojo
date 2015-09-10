package br.com.leonardo.pre_dojo.factory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.leonardo.pre_dojo.executors.NovaPartidaExecutor;
import br.com.leonardo.pre_dojo.interfaces.Executable;

public final class ExecutorFactory {
	
	private static final ExecutorFactory INSTANCE = new ExecutorFactory();
	
	//Construtor privado para Singleton
	private ExecutorFactory(){}
	
	public static ExecutorFactory getInstance(){
		return INSTANCE;
	}
	
	
	public Executable createExecutor(String command){
		Pattern pattern = Pattern.compile(".*New match.*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(command);
	
		if(matcher.find()){
			return new NovaPartidaExecutor(command);
		}
		
		return null;
	}
	
}
