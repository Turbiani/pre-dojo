package br.com.leonardo.pre_dojo.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.leonardo.pre_dojo.entidade.base.AbstractEntity;
@Entity
public class Resumo extends AbstractEntity {
	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne
	private Partida partida;
	
	private String	nomeJogadorVencedor;
	private String 	maiorStreakPartida;
	private String	armaMaisLetalVencedor;
	
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
	public String getNomeJogadorVencedor() {
		return nomeJogadorVencedor;
	}
	public void setNomeJogadorVencedor(String nomeJogadorVencedor) {
		this.nomeJogadorVencedor = nomeJogadorVencedor;
	}
	public String getMaiorStreakPartida() {
		return maiorStreakPartida;
	}
	public void setMaiorStreakPartida(String maiorStreakPartida) {
		this.maiorStreakPartida = maiorStreakPartida;
	}
	public String getArmaMaisLetalVencedor() {
		return armaMaisLetalVencedor;
	}
	public void setArmaMaisLetalVencedor(String armaMaisLetalVencedor) {
		this.armaMaisLetalVencedor = armaMaisLetalVencedor;
	}
	
	
}
