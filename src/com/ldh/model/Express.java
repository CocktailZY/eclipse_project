package com.ldh.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Express implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 975349342960061372L;
	private String eId;
	private String eExpress;
	
	@Id
	@GeneratedValue(generator = "UUID")
	public String geteId() {
		return eId;
	}
	public void seteId(String eId) {
		this.eId = eId;
	}
	public String geteExpress() {
		return eExpress;
	}
	public void seteExpress(String eExpress) {
		this.eExpress = eExpress;
	}
	

}
