package br.com.leonardo.pre_dojo.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.leonardo.pre_dojo.entidade.base.AbstractEntity;
@Entity
public class ResumoJogadorPartida extends AbstractEntity {
	@Id
	@GeneratedValue
	private Integer id;
	@OneToOne
	private Partida partida;
	@OneToOne
	private Jogador jogador;
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
	
}  
