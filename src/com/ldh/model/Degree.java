package com.ldh.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Degree implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3171854866974534393L;
	private String deId;
	private String deDescribe;
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	public String getDeId() {
		return deId;
	}
	public void setDeId(String deId) {
		this.deId = deId;
	}
	public String getDeDescribe() {
		return deDescribe;
	}
	public void setDeDescribe(String deDescribe) {
		this.deDescribe = deDescribe;
	}
	

}
