package br.com.leonardo.pre_dojo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.leonardo.pre_dojo.dao.base.AbstractDAO;
import br.com.leonardo.pre_dojo.entidade.Atividade;

/**
 * @author turbiani
 *
 */
public final class AtividadeDAO extends AbstractDAO<Atividade>{
	

	public AtividadeDAO(EntityManager em) {
		super(em);
	}
	
	public AtividadeDAO(){}
	
	@Override
	public Atividade busca(Integer id) {
		Atividade atividade = this.getEm().find(Atividade.class, id); 
		fechaEm();
		return atividade;
	}
	@Override
	public Atividade busca(Integer id, EntityManager em) {
		return em.find(Atividade.class, id); 
	}
	@Override
	public List<Atividade> lista() {
		List<Atividade> atividades =  this.getEm().createQuery("Select a from Atividade a", Atividade.class).getResultList();
		fechaEm();
		return atividades;
	}
	@Override
	public List<Atividade> lista(EntityManager em) {
		return em.createQuery("Select a from Atividade a", Atividade.class).getResultList();
	}
	@Override
	public void remove(Atividade atividade) {
		this.getEm().remove(atividade);
		fechaEm();		
	}
		
	@Override
	public void remove(int id) {
		Atividade atividade = this.busca(id);
		this.getEm().remove(atividade);
		fechaEm();
	}
	@Override
	public void remove(Atividade atividade, EntityManager em) {
		em.remove(atividade);
	}
	@Override
	public void remove(int id, EntityManager em) {
		Atividade atividade = this.busca(id);
		em.remove(atividade);
	}
	@Override
	public void adiciona(Atividade atividade) {
		this.getEm().persist(atividade);
		fechaEm();
	}
	
	@Override
	public void adiciona(Atividade atividade, EntityManager em) {
		em.persist(atividade);
	}
	
}
