package br.com.leonardo.pre_dojo.entidade;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.leonardo.pre_dojo.entidade.base.AbstractEntity;
@Entity
public class Ranking extends AbstractEntity{
	@Id
	@GeneratedValue
	private Integer id;
	@OneToOne
	private Partida partida;
	@OneToMany(mappedBy="partida")
	private List<ResumoJogadorPartida> resumoJogadorPartida;
	private String 	nomeJogadorVencedor;
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
	public List<ResumoJogadorPartida> getResumoJogadorPartida() {
		return resumoJogadorPartida;
	}
	public void setResumoJogadorPartida(
			List<ResumoJogadorPartida> resumoJogadorPartida) {
		this.resumoJogadorPartida = resumoJogadorPartida;
	}
	
}
