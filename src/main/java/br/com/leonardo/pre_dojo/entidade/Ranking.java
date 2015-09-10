package br.com.leonardo.pre_dojo.entidade;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.leonardo.pre_dojo.entidade.base.AbstractEntity;
@Entity
public class Ranking extends AbstractEntity{
	@Id
	@GeneratedValue
	private Integer id;
	@OneToMany
	private List<Partida> partidas;
	private String 	nomeJogadorVencedor;
	private Integer qtdeAssassinatos;
	private String	qtdeMortes;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Partida> getPartidas() {
		return partidas;
	}
	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
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
