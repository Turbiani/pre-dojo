package br.com.leonardo.pre_dojo.bo;

import java.util.Calendar;

import br.com.leonardo.pre_dojo.dao.AtividadeDAO;
import br.com.leonardo.pre_dojo.dao.PartidaDAO;
import br.com.leonardo.pre_dojo.entidade.Atividade;
import br.com.leonardo.pre_dojo.entidade.Jogador;
import br.com.leonardo.pre_dojo.entidade.Partida;
import br.com.leonardo.pre_dojo.enums.PartidaStatus;
import br.com.leonardo.pre_dojo.exception.PartidaEmAndamentoException;
import br.com.leonardo.pre_dojo.exception.PreDojoDomainException;
import br.com.leonardo.pre_dojo.factory.DaoFactory;

/**
 * @author turbiani
 *
 */
public class PartidaBO {
	private static final PartidaBO INSTANCE = new PartidaBO();
	
	private DaoFactory 		daoFactory = DaoFactory.getInstance();
	private PartidaDAO 		partidaDAO;
	private AtividadeDAO 	atividadeDAO;
	private Partida			currentPartida; 
	
	//Construtor privado para Singleton
	private PartidaBO(){}
	
	public static PartidaBO getInstance(){
		return INSTANCE;
	}	
	
	/**
	 * @param p
	 * @return Partida iniciada
	 * @throws PartidaEmAndamentoException
	 */
	public Partida iniciaPartida(Partida p) throws PartidaEmAndamentoException{
		if(currentPartida==null){
			p.setStatus(PartidaStatus.INICIADA);
			try {
				partidaDAO = (PartidaDAO) daoFactory.createDao(Partida.class);
				partidaDAO.adiciona(p);
				currentPartida = (Partida) p.clone();
			}catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				e.printStackTrace();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}else if(currentPartida.getId().intValue()==p.getId().intValue()){
				throw new PartidaEmAndamentoException("ERROR PARTIDA [" + currentPartida.getId().toString() 
						+ "] JA ESTA INICIADA");
		}else{
			throw new PartidaEmAndamentoException("ERROR PARTIDA [" + currentPartida.getId().toString() 
					+ "] EM ANDAMENTO. PARA INICIAR UMA NOVA PARTIDA ENCERRE A PARTIDA " + currentPartida.getId().toString());
		}
		
		return currentPartida;
	}
	
	/**
	 * @param p
	 * @return TRUE Caso tenha ocorrido tudo certo - FALSE Caso algum erro nao tratado tenha sido gerado, ou problema desconhecido
	 * @throws PreDojoDomainException
	 */
	public boolean EncerrarPartida(Partida p) throws PreDojoDomainException{
		if(currentPartida==null){
			throw new PreDojoDomainException("NAO EXISTE NENHUMA PARTIDA PARA SER ENCERRADA");
		}else if ((currentPartida.getId().intValue()==p.getId().intValue()) 
				&& currentPartida.getStatus()==PartidaStatus.INICIADA){
			p.setStatus(PartidaStatus.ENCERRADA);
			try {
				partidaDAO = (PartidaDAO) daoFactory.createDao(Partida.class);
				partidaDAO.atualiza(p);
				
				return true;
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				e.printStackTrace();
			}
		}else{
			throw new PreDojoDomainException("A PARTIDA JA ESTA ENCERRADA OU AINDA NAO FOI INICIADA PARA PODER ENCERRAR");
		}
		return false;
	}
	
	/**
	 * @param p
	 * @param matador
	 * @param assassinado
	 * @param arma
	 * @throws PreDojoDomainException 
	 */
	public void matarJogador(Partida p, Jogador matador, Jogador assassinado, String arma, Calendar data) throws PreDojoDomainException{
		Atividade atividade = null;
		if(currentPartida==null){
			throw new PreDojoDomainException("NAO EXISTE PARTIDA INICIADA PARA PODER EXECUTAR ESTE COMANDO");
		}else if(currentPartida.getId().intValue()==p.getId().intValue()){
			atividade = new Atividade();
			atividade.setJogadorMatador(matador);
			atividade.setJogadorMorto(assassinado);
			atividade.setPartida(p);
			atividade.setArma(arma);
			atividade.setData(data);
			try {
				atividadeDAO = (AtividadeDAO) daoFactory.createDao(Atividade.class);
				atividadeDAO.adiciona(atividade);
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				e.printStackTrace();
			}
		}else{
			throw new PreDojoDomainException("PARTIDA INVALIDA, PARTIDA EM ANDAMENTO ID [" + currentPartida.getId().toString() + "]");
		}
	}
	
	//MORTO PELO <WORLD>
	/**
	 * @param p
	 * @param assassinado
	 * @throws PreDojoDomainException 
	 */
	public void matarJogador(Partida p, Jogador assassinado, Calendar data) throws PreDojoDomainException{
		Atividade atividade = null;
		if(currentPartida==null){
			throw new PreDojoDomainException("NAO EXISTE PARTIDA INICIADA PARA PODER EXECUTAR ESTE COMANDO");
		}else if(currentPartida.getId().intValue()==p.getId().intValue()){
			Jogador matador = new Jogador();
			matador.setNome("<WORLD>");
			atividade = new Atividade();
			atividade.setJogadorMatador(matador);
			atividade.setJogadorMorto(assassinado);
			atividade.setPartida(p);
			atividade.setArma("<WORLD>");
			atividade.setData(data);
			try {
				atividadeDAO = (AtividadeDAO) daoFactory.createDao(Atividade.class);
				atividadeDAO.adiciona(atividade);
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				e.printStackTrace();
			}
		}else{
			throw new PreDojoDomainException("PARTIDA INVALIDA, PARTIDA EM ANDAMENTO ID [" + currentPartida.getId().toString() + "]");
		}
	}
}
