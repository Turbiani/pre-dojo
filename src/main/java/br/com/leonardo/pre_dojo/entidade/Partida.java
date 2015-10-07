package br.com.leonardo.pre_dojo.entidade;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.leonardo.pre_dojo.entidade.base.AbstractEntity;
import br.com.leonardo.pre_dojo.enums.PartidaStatus;
import br.com.leonardo.pre_dojo.listener.PartidaListener;
@Entity
@EntityListeners(PartidaListener.class)
public class Partida extends AbstractEntity implements Cloneable{
	@Id
	private Integer id;
	
	@OneToOne
	private	Ranking ranking;
	@OneToMany(mappedBy="partida")
	private List<Atividade> atividades;
	@Enumerated(EnumType.STRING)
	private PartidaStatus status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Ranking getRanking() {
		return ranking;
	}
	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}
	public List<Atividade> getAtividades() {
		return atividades;
	}
	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}
	public PartidaStatus getStatus() {
		return status;
	}
	public void setStatus(PartidaStatus status) {
		this.status = status;
	}
	
	public boolean isIniciada(){
		return this.status==PartidaStatus.INICIADA;
	}
	
	@Override		
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
	     
	
}
