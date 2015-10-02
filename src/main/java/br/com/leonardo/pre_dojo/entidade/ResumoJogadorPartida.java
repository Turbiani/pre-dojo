package br.com.leonardo.pre_dojo.entidade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.com.leonardo.pre_dojo.entidade.base.AbstractEntity;
@Entity
public class ResumoJogadorPartida extends AbstractEntity {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne(cascade=CascadeType.ALL)
	private Partida partida;
	@OneToOne
	private Jogador jogador;
	
	private Integer qtdeAssassinatos;
	private Integer qtdeMortes;
	//Streak
	private Integer qtdeMortesPorVida;
	//award
	private Integer qtdeAward;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Partida getPartida() {
		return partida;
	}
	public void setPartida(Partida partida) {
		this.partida = partida;
	}
	public Jogador getJogador() {
		return jogador;
	}
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	public Integer getQtdeMortesPorVida() {
		return qtdeMortesPorVida;
	}
	public void setQtdeMortesPorVida(Integer qtdeMortesPorVida) {
		this.qtdeMortesPorVida = qtdeMortesPorVida;
	}
	public Integer getQtdeAward() {
		return qtdeAward;
	}
	public void setQtdeAward(Integer qtdeAward) {
		this.qtdeAward = qtdeAward;
	}
	public Integer getQtdeAssassinatos() {
		return qtdeAssassinatos;
	}
	public void setQtdeAssassinatos(Integer qtdeAssassinatos) {
		this.qtdeAssassinatos = qtdeAssassinatos;
	}
	public Integer getQtdeMortes() {
		return qtdeMortes;
	}
	public void setQtdeMortes(Integer qtdeMortes) {
		this.qtdeMortes = qtdeMortes;
	}
	
	
}  
