package br.com.leonardo.pre_dojo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.leonardo.pre_dojo.dao.base.AbstractDAO;
import br.com.leonardo.pre_dojo.entidade.Partida;
import br.com.leonardo.pre_dojo.entidade.ResumoJogadorPartida;

/**
 * @author turbiani
 *
 */
public final class ResumoJogadorPartidaDAO extends AbstractDAO<ResumoJogadorPartida>{
	

	public ResumoJogadorPartidaDAO(EntityManager em) {
		super(em);
	}
	
	public ResumoJogadorPartidaDAO(){}
	
	@Override
	public ResumoJogadorPartida busca(Integer id) {
		ResumoJogadorPartida resumoJogadorPartida = this.getEm().find(ResumoJogadorPartida.class, id); 
		fechaEm();
		return resumoJogadorPartida;
	}
	@Override
	public ResumoJogadorPartida busca(Integer id, EntityManager em) {
		return em.find(ResumoJogadorPartida.class, id); 
	}
	
	/**
	 * @param p
	 * @return
	 */
	public ResumoJogadorPartida findByPartida(Partida p){
		TypedQuery<ResumoJogadorPartida> query = this.getEm().createNamedQuery("ResumoJogadorPartida.findByPartida",
				ResumoJogadorPartida.class);
		query.setParameter("partidaId", p.getId());
		try{
			return query.getResultList().get(0);
		}catch(IndexOutOfBoundsException e){
			return null;
		}
	}
	
	public ResumoJogadorPartida findByPartida(Partida p, EntityManager em){
		TypedQuery<ResumoJogadorPartida> query = em.createNamedQuery("ResumoJogadorPartida.findByPartida",
				ResumoJogadorPartida.class);
		query.setParameter("partidaId", p.getId());
		try{
			return query.getResultList().get(0);
		}catch(IndexOutOfBoundsException e){
			return null;
		}
	}
	
	@Override
	public List<ResumoJogadorPartida> lista() {
		List<ResumoJogadorPartida> resumoJogadorPartidas =  this.getEm().createQuery("Select r from ResumoJogadorPartida r", ResumoJogadorPartida.class).getResultList();
		fechaEm();
		return resumoJogadorPartidas;
	}
	@Override
	public List<ResumoJogadorPartida> lista(EntityManager em) {
		return em.createQuery("Select r from ResumoJogadorPartida r", ResumoJogadorPartida.class).getResultList();
	}
	@Override
	public void remove(ResumoJogadorPartida resumoJogadorPartida) {
		this.getEm().remove(resumoJogadorPartida);
		fechaEm();		
	}
		
	@Override
	public void remove(int id) {
		ResumoJogadorPartida resumoJogadorPartida = this.busca(id);
		this.getEm().remove(resumoJogadorPartida);
		fechaEm();
	}
	@Override
	public void remove(ResumoJogadorPartida resumoJogadorPartida, EntityManager em) {
		em.remove(resumoJogadorPartida);
	}
	@Override
	public void remove(int id, EntityManager em) {
		ResumoJogadorPartida resumoJogadorPartida = this.busca(id);
		em.remove(resumoJogadorPartida);
	}
	@Override
	public void adiciona(ResumoJogadorPartida resumoJogadorPartida) {
		this.getEm().getTransaction().begin();
		this.getEm().persist(resumoJogadorPartida);
		this.getEm().getTransaction().commit();
		fechaEm();
	}
	
	@Override
	public void adiciona(ResumoJogadorPartida resumoJogadorPartida, EntityManager em) {
		em.persist(resumoJogadorPartida);
	}
	
}
