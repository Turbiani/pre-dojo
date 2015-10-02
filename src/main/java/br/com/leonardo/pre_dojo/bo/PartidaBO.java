package br.com.leonardo.pre_dojo.bo;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.leonardo.pre_dojo.dao.AtividadeDAO;
import br.com.leonardo.pre_dojo.dao.JogadorDAO;
import br.com.leonardo.pre_dojo.dao.PartidaDAO;
import br.com.leonardo.pre_dojo.entidade.Atividade;
import br.com.leonardo.pre_dojo.entidade.Jogador;
import br.com.leonardo.pre_dojo.entidade.Partida;
import br.com.leonardo.pre_dojo.enums.PartidaStatus;
import br.com.leonardo.pre_dojo.exception.PartidaEmAndamentoException;
import br.com.leonardo.pre_dojo.exception.PreDojoDomainException;
import br.com.leonardo.pre_dojo.factory.DaoFactory;
import br.com.leonardo.pre_dojo.utils.JPAUtil;

/**
 * @author turbiani
 *
 */
public class PartidaBO {
	private static final PartidaBO INSTANCE = new PartidaBO();
	
	private DaoFactory 		daoFactory = DaoFactory.getInstance();
	private PartidaDAO 		partidaDAO;
	private AtividadeDAO 	atividadeDAO;
	private JogadorDAO	 	jogadorDAO;
	private Partida			currentPartida; 
	private final JPAUtil   JPAUtil = new JPAUtil();
	
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
	public void encerrarPartida(Integer partidaID) throws PreDojoDomainException{
		if(currentPartida==null){
			throw new PreDojoDomainException("NAO EXISTE NENHUMA PARTIDA PARA SER ENCERRADA");
		}else if ((currentPartida.getId().intValue()==partidaID.intValue()) 
				&& currentPartida.getStatus()==PartidaStatus.INICIADA){
			//Iniciando o entityManager
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			//buscando jogadores e partida
			Partida p = partidaDAO.busca(partidaID, em);
			
			p.setStatus(PartidaStatus.ENCERRADA);
			em.getTransaction().commit();
			em.close();
			
		}else{
			throw new PreDojoDomainException("A PARTIDA JA ESTA ENCERRADA OU AINDA NAO FOI INICIADA PARA PODER ENCERRAR");
		}
	}
	
	/**
	 * @param matador
	 * @param assassinado
	 * @param arma
	 * @throws PreDojoDomainException 
	 */
	public void matarJogador(String nomeMatador, String nomeAssassinado, String arma, Calendar data) throws PreDojoDomainException{
		Atividade atividade = null;
		if(currentPartida==null){
			throw new PreDojoDomainException("NAO EXISTE PARTIDA INICIADA PARA PODER EXECUTAR ESTE COMANDO");
		}else if(currentPartida.isIniciada()){
			//Iniciando o entityManager
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			//buscando jogadores e partida
			Jogador matador = buscaJogador(nomeMatador, em);
			Jogador assassinado = buscaJogador(nomeAssassinado, em);
			Partida p = partidaDAO.busca(currentPartida.getId(), em);
			
			atividade = new Atividade();
			atividade.setJogadorMatador(matador);
			atividade.setJogadorMorto(assassinado);
			atividade.setPartida(p);
			atividade.setArma(arma);
			atividade.setData(data);
			try {
				atividadeDAO = (AtividadeDAO) daoFactory.createDao(Atividade.class);
				atividadeDAO.adiciona(atividade, em);
				em.getTransaction().commit();
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				e.printStackTrace();
			}finally{
				em.close();
			}
		}else{
			throw new PreDojoDomainException("PARTIDA ENCERRADA");
		}
	}
	
	//MORTO PELO <WORLD>
	/**
	 * @param assassinado
	 * @throws PreDojoDomainException 
	 */
	public void matarJogador(String nomeAssassinado, Calendar data) throws PreDojoDomainException{
		Atividade atividade = null;
		if(currentPartida==null){
			throw new PreDojoDomainException("NAO EXISTE PARTIDA INICIADA PARA PODER EXECUTAR ESTE COMANDO");
		}else if(currentPartida.isIniciada()){
			//Iniciando o entityManager
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			
			Jogador matador = new Jogador();
			matador.setNome("<WORLD>");
			//buscando assassinado no banco de dados e a partida corrente
			Jogador assassinado = buscaJogador(nomeAssassinado, em);
			Partida p = partidaDAO.busca(currentPartida.getId(), em);
			
			atividade = new Atividade();
			atividade.setJogadorMatador(matador);
			atividade.setJogadorMorto(assassinado);
			atividade.setPartida(p);
			atividade.setArma("<WORLD>");
			atividade.setData(data);
			try {
				atividadeDAO = (AtividadeDAO) daoFactory.createDao(Atividade.class);
				atividadeDAO.adiciona(atividade, em);
				em.getTransaction().commit();
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				e.printStackTrace();
			}finally{
				em.close();
			}
		}else{
			throw new PreDojoDomainException("PARTIDA ENCERRADA");
		}
	}
	
	private Jogador buscaJogador(String nome, EntityManager em){
		Jogador jogador = null;
		try {
			jogadorDAO = (JogadorDAO) daoFactory.createDao(Jogador.class);
			jogador = jogadorDAO.findByNome(nome, em);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(jogador==null){
			jogador = new Jogador();
			jogador.setNome(nome);
			jogador.setData(Calendar.getInstance());
			
			jogadorDAO.adiciona(jogador, em);
		}
		
		return jogador;
		
	}
}
