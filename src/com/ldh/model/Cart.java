package com.ldh.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Cart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5876678829192963148L;
	private String cId;
	private int cNumber;
	private String cSubTotal;
	private Goods cGId;
	private Users cUId;
	
	@Id
	@GeneratedValue(generator = "UUID")
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public int getcNumber() {
		return cNumber;
	}
	public void setcNumber(int cNumber) {
		this.cNumber = cNumber;
	}
	public String getcSubTotal() {
		return cSubTotal;
	}
	public void setcSubTotal(String cSubTotal) {
		this.cSubTotal = cSubTotal;
	}
	
	@OneToOne
	@JoinColumn(name="cGId")
	public Goods getcGId() {
		return cGId;
	}
	public void setcGId(Goods cGId) {
		this.cGId = cGId;
	}
	
	@OneToOne
	@JoinColumn(name="cUId")
	public Users getcUId() {
		return cUId;
	}
	public void setcUId(Users cUId) {
		this.cUId = cUId;
	}

}
