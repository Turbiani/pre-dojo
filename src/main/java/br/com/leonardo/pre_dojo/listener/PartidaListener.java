package br.com.leonardo.pre_dojo.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.leonardo.pre_dojo.dao.AtividadeDAO;
import br.com.leonardo.pre_dojo.dao.RankingDAO;
import br.com.leonardo.pre_dojo.entidade.Atividade;
import br.com.leonardo.pre_dojo.entidade.Partida;
import br.com.leonardo.pre_dojo.entidade.Ranking;
import br.com.leonardo.pre_dojo.enums.PartidaStatus;
import br.com.leonardo.pre_dojo.factory.DaoFactory;

/**
 * @author turbiani
 *
 */
public class PartidaListener {
	private RankingDAO rankingDAO;
	private AtividadeDAO atividadeDAO;
	
	@PreUpdate
	public void pretUpdate(Partida entidade){
		try {
			if(entidade.getStatus()==PartidaStatus.ENCERRADA){
				rankingDAO  = (RankingDAO) DaoFactory.getInstance().createDao(Ranking.class);
				atividadeDAO = (AtividadeDAO) DaoFactory.getInstance().createDao(Atividade.class);
				//Jogador esse sql em atividade
				//select jogadorMatador_id, max(qtdeMortes) from 
				//( select jogadorMatador_id, count(1) as qtdeMortes 
				//		from Atividade where partida_id = 11348967 group by jogadorMatador_id, arma order by qtdeMortes,id desc) as vencedor;
				
				
			}
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PrePersist
	public void prePersist(Partida entidade){
		//TODO - Colocar programacao aqui caso eu queira ter algum gatilho para insert de Partida
	}
}
