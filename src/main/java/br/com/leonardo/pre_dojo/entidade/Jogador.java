package br.com.leonardo.pre_dojo.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import br.com.leonardo.pre_dojo.entidade.base.AbstractEntity;
@Entity
@NamedQueries({
	@NamedQuery(name="Jogador.findByNome",	query="Select j from Jogador j where j.nome = :nome")
})
public class Jogador extends AbstractEntity{
	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	
	public Integer getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
