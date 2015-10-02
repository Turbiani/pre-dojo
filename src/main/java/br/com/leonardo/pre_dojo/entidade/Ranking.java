package br.com.leonardo.pre_dojo.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.leonardo.pre_dojo.entidade.base.AbstractEntity;
@Entity
public class Ranking extends AbstractEntity{
	@Id
	@GeneratedValue
	private Integer id;
	@OneToOne
	private Partida partida;
	private String 	nomeJogadorVencedor;
	private Integer qtdeAssassinatos;
	private String	qtdeMortes;
	
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
	public Integer getQtdeAssassinatos() {
		return qtdeAssassinatos;
	}
	public void setQtdeAssassinatos(Integer qtdeAssassinatos) {
		this.qtdeAssassinatos = qtdeAssassinatos;
	}
	public String getQtdeMortes() {
		return qtdeMortes;
	}
	public void setQtdeMortes(String qtdeMortes) {
		this.qtdeMortes = qtdeMortes;
	}
	
	
}
