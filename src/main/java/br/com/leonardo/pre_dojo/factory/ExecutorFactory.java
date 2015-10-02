package br.com.leonardo.pre_dojo.factory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.leonardo.pre_dojo.executors.EncerraPartidaExecutor;
import br.com.leonardo.pre_dojo.executors.KillerExecutor;
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
		Executable executable = null;
		Pattern pattern = Pattern.compile(".*New match.*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(command);
	
		if(matcher.find()){
			executable = new NovaPartidaExecutor(command);
		}else{
			pattern = Pattern.compile(".*has ended.*", Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(command);
			if(matcher.find()){
				executable = new EncerraPartidaExecutor(command);
			}else{
				pattern = Pattern.compile(".*killed.*", Pattern.CASE_INSENSITIVE);
				matcher = pattern.matcher(command);
				if(matcher.find()){
					executable = new KillerExecutor(command);
				}
			}
		}
		
		return executable;
	}
}
