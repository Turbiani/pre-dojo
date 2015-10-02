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
public class EncerraPartidaExecutor implements Executable{
	private String command;
	private PartidaBO bo = PartidaBO.getInstance();
	
	public EncerraPartidaExecutor(String command) {
		this.command = command;
	}
	
	@Override
	public void execute() throws PreDojoDomainException {
		String partidaID = null;
		Pattern pattern = Pattern.compile(".*has ended.*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(command);
	
		if(matcher.find()){
			pattern = Pattern.compile(".*Match ([0-9]+)");
			matcher = pattern.matcher(command);
			if(matcher.find()) {
			      partidaID = matcher.group(1);
		    }
			
			if(partidaID!=null){
				bo.encerrarPartida(Integer.parseInt(partidaID));
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
}
