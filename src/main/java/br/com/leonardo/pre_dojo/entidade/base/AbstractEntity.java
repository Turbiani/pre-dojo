package br.com.leonardo.pre_dojo.entidade.base;

import java.math.BigInteger;

/**
 * @author turbiani
 *
 */
public abstract class AbstractEntity {
	
	private BigInteger id;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}
	

}
