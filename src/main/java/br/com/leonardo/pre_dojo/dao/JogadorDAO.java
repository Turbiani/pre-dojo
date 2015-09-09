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
	

	@Override
	public Jogador busca(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Jogador busca(Integer id, EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Jogador> lista() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Jogador> lista(EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Jogador object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Jogador object, EntityManager em) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(int id, EntityManager em) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adiciona(Jogador object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adiciona(Jogador object, EntityManager em) {
		// TODO Auto-generated method stub
		
	}
	

	/*public ContaDAO(EntityManager em) {
		super(em);
	}
	
	*//**
	 * Default Constructor
	 *//*
	public ContaDAO(){}

	public Conta busca(Integer id) {
		Conta conta = this.getEm().find(Conta.class, id); 
		fechaEm();
		return conta;
	}

	public Conta busca(Integer id, EntityManager em) {
		return em.find(Conta.class, id); 
	}

	public List<Conta> lista() {
		List<Conta> contas =  this.getEm().createQuery("Select c from Conta c", Conta.class).getResultList();
		fechaEm();
		return contas;
	}

	public List<Conta> lista(EntityManager em) {
		return em.createQuery("Select c from Conta c", Conta.class).getResultList();
	}

	public void remove(Conta conta) {
		this.getEm().remove(conta);
		fechaEm();		
	}

	public void remove(int id) {
		Conta c = this.busca(id);
		this.getEm().remove(c);
		fechaEm();
	}

	public void remove(Conta conta, EntityManager em) {
		em.remove(conta);
	}

	public void remove(int id, EntityManager em) {
		Conta c = this.busca(id);
		em.remove(c);
	}

	public void adiciona(Conta conta) {
		this.getEm().persist(conta);
		fechaEm();
	}

	public void adiciona(Conta conta, EntityManager em) {
		em.persist(conta);
	}*/
	
}
