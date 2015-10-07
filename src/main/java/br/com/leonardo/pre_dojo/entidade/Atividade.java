package br.com.leonardo.pre_dojo.entidade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.leonardo.pre_dojo.entidade.base.AbstractEntity;
import br.com.leonardo.pre_dojo.listener.AtividadeListener;
@Entity
@EntityListeners(AtividadeListener.class)
public class Atividade extends AbstractEntity{
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne(cascade=CascadeType.ALL)
	private Partida partida;
	@ManyToOne(cascade=CascadeType.ALL)
	private Jogador jogadorMatador;
	@ManyToOne(cascade=CascadeType.ALL)
	private Jogador jogadorMorto;
	private String  arma;
	
	public Integer getId() {
		return id;
	}
	public Jogador getJogadorMatador() {
		return jogadorMatador;
	}
	public void setJogadorMatador(Jogador jogadorMatador) {
		this.jogadorMatador = jogadorMatador;
	}
	public Jogador getJogadorMorto() {
		return jogadorMorto;
	}
	public void setJogadorMorto(Jogador jogadorMorto) {
		this.jogadorMorto = jogadorMorto;
	}
	public String getArma() {
		return arma;
	}
	public void setArma(String arma) {
		this.arma = arma;
	}
	public Partida getPartida() {
		return partida;
	}
	public void setPartida(Partida partida) {
		this.partida = partida;
	}
	

}
