package br.com.leonardo.pre_dojo.listener;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.leonardo.pre_dojo.dao.ResumoJogadorPartidaDAO;
import br.com.leonardo.pre_dojo.entidade.Atividade;
import br.com.leonardo.pre_dojo.entidade.ResumoJogadorPartida;
import br.com.leonardo.pre_dojo.factory.DaoFactory;
import br.com.leonardo.pre_dojo.utils.JPAUtil;

/**
 * @author turbiani
 *
 */
public class AtividadeListener {
	private ResumoJogadorPartidaDAO dao;
	
	@PreUpdate
	public void pretUpdate(Atividade entidade){
		try {
			if(dao==null){
				dao = (ResumoJogadorPartidaDAO) DaoFactory.getInstance().createDao(ResumoJogadorPartida.class);
			}
			//TODO - PRECISO CORRIGIR O SELECT, POIS ASSIM TEREI VARIOS RESUMOS PARA MESMA PARTIDA-----TENHO QUE BUSCAR POR JOGADOR TBM
			ResumoJogadorPartida r;
			EntityManager em = new JPAUtil().getEntityManager();
			
			
			em.getTransaction().begin();
			r = dao.findByPartida(entidade.getPartida(), em);
			//ATUALIZANDO RESUMO DO MATADOR
			r.setQtdeAssassinatos(r.getQtdeAssassinatos() + 1);
			dao.adiciona(r);
			em.getTransaction().commit();
			em.close();
			
			//ATUALIZANDO RESUMO DO MORTO
			em = new JPAUtil().getEntityManager();
			em.getTransaction().begin();
			r = dao.findByPartida(entidade.getPartida(), em);
			
			r.setQtdeAssassinatos(r.getQtdeAssassinatos() + 1);
			dao.adiciona(r);
			em.getTransaction().commit();
			em.close();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PrePersist
	public void prePersist(Atividade entidade){
		//TODO - Colocar programacao aqui caso eu queira ter algum gatilho para insert de Atividade
		try {
			if(dao==null){
				dao = (ResumoJogadorPartidaDAO) DaoFactory.getInstance().createDao(ResumoJogadorPartida.class);
			}
			
			//TODO - GERAR ATUALIZACAO DE DOIS RESUMOS...UM PARA O MATADOR E OUTRO PARA O MORTO
			//SO GEREI DO MATADOR AQUI, ESQUECI DO POBRE COITADO MORTO
			ResumoJogadorPartida r = new ResumoJogadorPartida();
			r.setJogador(entidade.getJogadorMatador());
			r.setPartida(entidade.getPartida());
			r.setQtdeAssassinatos(1);
			r.setQtdeMortes(0);
			r.setQtdeAward(0);
			r.setQtdeMortesPorVida(0);
			
			dao.adiciona(r);
			
			//ATUALIZANDO RESUMOPARTIDA DO MORTO
			r.setJogador(entidade.getJogadorMorto());
			r.setPartida(entidade.getPartida());
			r.setQtdeAssassinatos(0);
			r.setQtdeMortes(1);
			r.setQtdeAward(0);
			r.setQtdeMortesPorVida(1);
			
			dao.adiciona(r);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
