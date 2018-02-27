package com.ldh.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Address implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8364319449645007823L;
	private String aId;
	private String aDescribe;
	private int aSign;
	private Users aUId;
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")  
	@GeneratedValue(generator="systemUUID")
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getaDescribe() {
		return aDescribe;
	}
	public void setaDescribe(String aDescribe) {
		this.aDescribe = aDescribe;
	}
	
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="aUId",referencedColumnName="uId",insertable=false, updatable=false)
	public Users getaUId() {
		return aUId;
	}
	public void setaUId(Users aUId) {
		this.aUId = aUId;
	}
	public int getaSign() {
		return aSign;
	}
	public void setaSign(int aSign) {
		this.aSign = aSign;
	}

}
