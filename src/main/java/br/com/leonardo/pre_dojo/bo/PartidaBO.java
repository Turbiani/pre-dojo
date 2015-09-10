package br.com.leonardo.pre_dojo.bo;

import br.com.leonardo.pre_dojo.dao.AtividadeDAO;
import br.com.leonardo.pre_dojo.dao.PartidaDAO;
import br.com.leonardo.pre_dojo.entidade.Atividade;
import br.com.leonardo.pre_dojo.entidade.Jogador;
import br.com.leonardo.pre_dojo.entidade.Partida;
import br.com.leonardo.pre_dojo.enums.PartidaStatus;
import br.com.leonardo.pre_dojo.factory.DaoFactory;

public class PartidaBO {
	private DaoFactory daoFactory = DaoFactory.getInstance();
	private PartidaDAO partidaDAO;
	private AtividadeDAO atividadeDAO;
	
	public void iniciaPartida(Partida p){
		try {
			p.setStatus(PartidaStatus.INICIADA);
			partidaDAO = (PartidaDAO) daoFactory.createDao(Partida.class);
			partidaDAO.adiciona(p);
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void EncerrarPartida(Partida p){
		p.setStatus(PartidaStatus.ENCERRADA);
		try {
			partidaDAO = (PartidaDAO) daoFactory.createDao(Partida.class);
			partidaDAO.atualiza(p);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void matarJogador(Partida p, Jogador matador, Jogador assassinado, String arma){
		Atividade atividade = new Atividade();
		atividade.setJogadorMatador(matador);
		atividade.setJogadorMorto(assassinado);
		atividade.setPartida(p);
		atividade.setArma(arma);
		
		try {
			atividadeDAO = (AtividadeDAO) daoFactory.createDao(Atividade.class);
			atividadeDAO.adiciona(atividade);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//MORTO PELO <WORLD>
	public void matarJogador(Partida p, Jogador assassinado){
		Jogador matador = new Jogador();
		matador.setNome("<WORLD>");
		Atividade atividade = new Atividade();
		atividade.setJogadorMatador(matador);
		atividade.setJogadorMorto(assassinado);
		atividade.setPartida(p);
		atividade.setArma("<WORLD>");
		
		try {
			atividadeDAO = (AtividadeDAO) daoFactory.createDao(Atividade.class);
			atividadeDAO.adiciona(atividade);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
