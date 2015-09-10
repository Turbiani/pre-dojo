package br.com.leonardo.pre_dojo.entidade;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.leonardo.pre_dojo.entidade.base.AbstractEntity;
@Entity
public class Partida extends AbstractEntity{
	@Id
	private Integer id;
	
	@OneToOne
	private	Resumo resumo;
	@OneToMany
	private List<Atividade> atividades;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Resumo getResumo() {
		return resumo;
	}
	public void setResumo(Resumo resumo) {
		this.resumo = resumo;
	}
	public List<Atividade> getAtividades() {
		return atividades;
	}
	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}
	
	
}
