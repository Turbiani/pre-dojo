package br.com.leonardo.pre_dojo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.leonardo.pre_dojo.dao.base.AbstractDAO;
import br.com.leonardo.pre_dojo.entidade.Ranking;

/**
 * @author turbiani
 *
 */
public final class RankingDAO extends AbstractDAO<Ranking>{
	

	public RankingDAO(EntityManager em) {
		super(em);
	}
	
	public RankingDAO(){}
	
	@Override
	public Ranking busca(Integer id) {
		Ranking ranking = this.getEm().find(Ranking.class, id); 
		fechaEm();
		return ranking;
	}
	@Override
	public Ranking busca(Integer id, EntityManager em) {
		return em.find(Ranking.class, id); 
	}
	@Override
	public List<Ranking> lista() {
		List<Ranking> rankings =  this.getEm().createQuery("Select r from Ranking r", Ranking.class).getResultList();
		fechaEm();
		return rankings;
	}
	@Override
	public List<Ranking> lista(EntityManager em) {
		return em.createQuery("Select r from Ranking r", Ranking.class).getResultList();
	}
	@Override
	public void remove(Ranking ranking) {
		this.getEm().remove(ranking);
		fechaEm();		
	}
		
	@Override
	public void remove(int id) {
		Ranking ranking = this.busca(id);
		this.getEm().remove(ranking);
		fechaEm();
	}
	@Override
	public void remove(Ranking ranking, EntityManager em) {
		em.remove(ranking);
	}
	@Override
	public void remove(int id, EntityManager em) {
		Ranking ranking = this.busca(id);
		em.remove(ranking);
	}
	@Override
	public void adiciona(Ranking ranking) {
		this.getEm().getTransaction().begin();
		this.getEm().persist(ranking);
		this.getEm().getTransaction().commit();
		fechaEm();
	}
	
	@Override
	public void adiciona(Ranking ranking, EntityManager em) {
		em.persist(ranking);
	}
	
}
