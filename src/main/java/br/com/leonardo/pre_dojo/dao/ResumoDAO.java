package br.com.leonardo.pre_dojo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.leonardo.pre_dojo.dao.base.AbstractDAO;
import br.com.leonardo.pre_dojo.entidade.Resumo;

/**
 * @author turbiani
 *
 */
public final class ResumoDAO extends AbstractDAO<Resumo>{
	

	public ResumoDAO(EntityManager em) {
		super(em);
	}
	
	public ResumoDAO(){}
	
	@Override
	public Resumo busca(Integer id) {
		Resumo resumo = this.getEm().find(Resumo.class, id); 
		fechaEm();
		return resumo;
	}
	@Override
	public Resumo busca(Integer id, EntityManager em) {
		return em.find(Resumo.class, id); 
	}
	@Override
	public List<Resumo> lista() {
		List<Resumo> resumos =  this.getEm().createQuery("Select r from Resumo r", Resumo.class).getResultList();
		fechaEm();
		return resumos;
	}
	@Override
	public List<Resumo> lista(EntityManager em) {
		return em.createQuery("Select r from Resumo r", Resumo.class).getResultList();
	}
	@Override
	public void remove(Resumo resumo) {
		this.getEm().remove(resumo);
		fechaEm();		
	}
		
	@Override
	public void remove(int id) {
		Resumo resumo = this.busca(id);
		this.getEm().remove(resumo);
		fechaEm();
	}
	@Override
	public void remove(Resumo resumo, EntityManager em) {
		em.remove(resumo);
	}
	@Override
	public void remove(int id, EntityManager em) {
		Resumo resumo = this.busca(id);
		em.remove(resumo);
	}
	@Override
	public void adiciona(Resumo resumo) {
		this.getEm().getTransaction().begin();
		this.getEm().persist(resumo);
		this.getEm().getTransaction().commit();
		fechaEm();
	}
	
	@Override
	public void adiciona(Resumo resumo, EntityManager em) {
		em.persist(resumo);
	}
	
}
