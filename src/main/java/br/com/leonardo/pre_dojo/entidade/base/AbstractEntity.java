package br.com.leonardo.pre_dojo.entidade.base;

import java.util.Calendar;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author turbiani
 *
 */
@MappedSuperclass
public abstract class AbstractEntity {
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}
	
}
