package br.com.leonardo.pre_dojo.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.leonardo.pre_dojo.entidade.base.AbstractEntity;
@Entity
public class Atividade extends AbstractEntity{
	@Id
	@GeneratedValue
	private Integer id;
	@OneToOne
	private Jogador jogadorMatador;
	@OneToOne
	private Jogador jogadorMorto;
	private String  arma;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	

}
