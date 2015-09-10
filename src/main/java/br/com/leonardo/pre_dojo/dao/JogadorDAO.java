package br.com.leonardo.pre_dojo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.leonardo.pre_dojo.dao.base.AbstractDAO;
import br.com.leonardo.pre_dojo.entidade.Jogador;

/**
 * @author turbiani
 *
 */
public final class JogadorDAO extends AbstractDAO<Jogador>{
	

	public JogadorDAO(EntityManager em) {
		super(em);
	}
	
	public JogadorDAO(){}
	
	@Override
	public Jogador busca(Integer id) {
		Jogador jogador = this.getEm().find(Jogador.class, id); 
		fechaEm();
		return jogador;
	}
	@Override
	public Jogador busca(Integer id, EntityManager em) {
		return em.find(Jogador.class, id); 
	}
	@Override
	public List<Jogador> lista() {
		List<Jogador> jogadores =  this.getEm().createQuery("Select j from Jogador j", Jogador.class).getResultList();
		fechaEm();
		return jogadores;
	}
	@Override
	public List<Jogador> lista(EntityManager em) {
		return em.createQuery("Select c from Jogador c", Jogador.class).getResultList();
	}
	@Override
	public void remove(Jogador jogador) {
		this.getEm().remove(jogador);
		fechaEm();		
	}
		
	@Override
	public void remove(int id) {
		Jogador jogador = this.busca(id);
		this.getEm().remove(jogador);
		fechaEm();
	}
	@Override
	public void remove(Jogador jogador, EntityManager em) {
		em.remove(jogador);
	}
	@Override
	public void remove(int id, EntityManager em) {
		Jogador jogador = this.busca(id);
		em.remove(jogador);
	}
	@Override
	public void adiciona(Jogador jogador) {
		this.getEm().persist(jogador);
		fechaEm();
	}
	
	@Override
	public void adiciona(Jogador jogador, EntityManager em) {
		em.persist(jogador);
	}
	
}
