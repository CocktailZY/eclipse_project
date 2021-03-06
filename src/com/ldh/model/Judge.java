package com.ldh.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Judge implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -598597670598753097L;
	private String jId;
	private String jDescribe;
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	public String getjId() {
		return jId;
	}
	public void setjId(String jId) {
		this.jId = jId;
	}
	public String getjDescribe() {
		return jDescribe;
	}
	public void setjDescribe(String jDescribe) {
		this.jDescribe = jDescribe;
	}
	

}
