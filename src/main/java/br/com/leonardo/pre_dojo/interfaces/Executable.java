package br.com.leonardo.pre_dojo.interfaces;

import br.com.leonardo.pre_dojo.exception.PreDojoDomainException;

/**
 * @author turbiani
 *
 */
public interface Executable {
	void execute() throws PreDojoDomainException;
}
