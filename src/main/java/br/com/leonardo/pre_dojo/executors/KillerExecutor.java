package br.com.leonardo.pre_dojo.executors;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.leonardo.pre_dojo.bo.PartidaBO;
import br.com.leonardo.pre_dojo.exception.PreDojoDomainException;
import br.com.leonardo.pre_dojo.interfaces.Executable;

/**
 * @author turbiani
 *
 */
public class KillerExecutor implements Executable{
	private String command;
	private PartidaBO bo = PartidaBO.getInstance();
	
	public KillerExecutor(String command) {
		this.command = command;
	}
	
	@Override
	public void execute() throws PreDojoDomainException {
		String nomeMatador;
		String nomeDoMorto;
		String tipoArma;
		Pattern pattern = Pattern.compile(".*killed.*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(command);
	
		if(matcher.find()){
			pattern = Pattern.compile(".*WORLD.*");
			matcher = pattern.matcher(command);
			if(matcher.find()) {
				nomeDoMorto = command.substring(command.indexOf("killed") + 6, command.indexOf("by"));
				bo.matarJogador(nomeDoMorto, getDataFromCommand(command));
		    }else{
		    	nomeMatador = command.substring(command.indexOf("-") + 1, command.indexOf("killed"));
		    	nomeDoMorto = command.substring(command.indexOf("killed") + 6, command.indexOf("using"));
		    	tipoArma 	= command.substring(command.indexOf("using") + 5, command.length());
		    	bo.matarJogador(nomeMatador, nomeDoMorto, tipoArma, getDataFromCommand(command));
		    }
			
		}
	}
	
	/**
	 * @param command
	 * @return Calendar
	 */
	private Calendar getDataFromCommand(String command){
		int count 		= 0;
		Calendar data	= Calendar.getInstance();
		Pattern pattern = Pattern.compile("-?\\d+");
		Matcher matcher = pattern.matcher(command);
		while(matcher.find() && count < 6) {
			if(count==0)data.set(Calendar.DAY_OF_MONTH, Integer.parseInt(matcher.group()));
			if(count==1)data.set(Calendar.MONTH, Integer.parseInt(matcher.group()));
			if(count==2)data.set(Calendar.YEAR, Integer.parseInt(matcher.group()));
			if(count==3)data.set(Calendar.HOUR_OF_DAY, Integer.parseInt(matcher.group()));
			if(count==4)data.set(Calendar.MINUTE, Integer.parseInt(matcher.group()));
			if(count==5)data.set(Calendar.SECOND, Integer.parseInt(matcher.group()));
			count++;
		}
		return data;
	}
	
	public static void main(String[] args) {
		KillerExecutor executor = new KillerExecutor("23/04/2013 15:36:33 - WORLD killed Nick by DROWN");
		try {
			executor.execute();
		} catch (PreDojoDomainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
