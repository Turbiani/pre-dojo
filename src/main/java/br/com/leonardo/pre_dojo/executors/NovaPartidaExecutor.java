package br.com.leonardo.pre_dojo.executors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.leonardo.pre_dojo.bo.PartidaBO;
import br.com.leonardo.pre_dojo.entidade.Partida;
import br.com.leonardo.pre_dojo.interfaces.Executable;

/**
 * @author turbiani
 *
 */
public class NovaPartidaExecutor implements Executable{
	private String command;
	
	public NovaPartidaExecutor(String command) {
		this.command = command;
	}
	
	@Override
	public void execute() {
		String partidaID = null;
		Pattern pattern = Pattern.compile(".*New match.*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(command);
	
		if(matcher.find()){
			pattern = Pattern.compile(".*New match ([0-9]+)");
			matcher = pattern.matcher(command);
			if(matcher.find()) {
			      partidaID = matcher.group(1);
		    }
			
			
			/*pattern = Pattern.compile("-?\\d+");
			matcher = pattern.matcher(command);
			if(matcher.find()) {
				System.out.println(matcher.group(0));
			}*/
			
			if(partidaID!=null){
				Partida partida = new Partida();
				partida.setId(new Integer(partidaID));
				
				PartidaBO bo = new PartidaBO();
				bo.iniciaPartida(partida);
				
			}
			
		}
		
		
	}
	
	public static void main(String[] args) {
		NovaPartidaExecutor executor = new NovaPartidaExecutor("23/04/2013 15:34:22 - New match 11348965 has started");
		executor.execute();
		
	}

}
