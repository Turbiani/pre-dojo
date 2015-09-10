package br.com.leonardo.pre_dojo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.leonardo.pre_dojo.dao.base.AbstractDAO;
import br.com.leonardo.pre_dojo.entidade.Partida;

/**
 * @author turbiani
 *
 */
public final class PartidaDAO extends AbstractDAO<Partida>{
	

	public PartidaDAO(EntityManager em) {
		super(em);
	}
	
	public PartidaDAO(){}
	
	@Override
	public Partida busca(Integer id) {
		Partida partida = this.getEm().find(Partida.class, id); 
		fechaEm();
		return partida;
	}
	@Override
	public Partida busca(Integer id, EntityManager em) {
		return em.find(Partida.class, id); 
	}
	@Override
	public List<Partida> lista() {
		List<Partida> partidas =  this.getEm().createQuery("Select p from Partida p", Partida.class).getResultList();
		fechaEm();
		return partidas;
	}
	@Override
	public List<Partida> lista(EntityManager em) {
		return em.createQuery("Select p from Partida p", Partida.class).getResultList();
	}
	@Override
	public void remove(Partida partida) {
		this.getEm().remove(partida);
		fechaEm();		
	}
		
	@Override
	public void remove(int id) {
		Partida partida = this.busca(id);
		this.getEm().remove(partida);
		fechaEm();
	}
	@Override
	public void remove(Partida partida, EntityManager em) {
		em.remove(partida);
	}
	@Override
	public void remove(int id, EntityManager em) {
		Partida partida = this.busca(id);
		em.remove(partida);
	}
	@Override
	public void adiciona(Partida partida) {
		this.getEm().persist(partida);
		fechaEm();
	}
	
	@Override
	public void adiciona(Partida partida, EntityManager em) {
		em.persist(partida);
	}
	
}
