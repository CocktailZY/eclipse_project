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

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class OrderInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6472300158094200494L;
	private String oId;
	private String oTotal;
	private String oDetermine;
	private String oComplete;
	private int	oSign;
	private Users oUId;
	private Address oAId;
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	public String getoId() {
		return oId;
	}
	public void setoId(String oId) {
		this.oId = oId;
	}
	public String getoTotal() {
		return oTotal;
	}
	public void setoTotal(String oTotal) {
		this.oTotal = oTotal;
	}
	public String getoDetermine() {
		return oDetermine;
	}
	public void setoDetermine(String oDetermine) {
		this.oDetermine = oDetermine;
	}
	public String getoComplete() {
		return oComplete;
	}
	public void setoComplete(String oComplete) {
		this.oComplete = oComplete;
	}
	public int getoSign() {
		return oSign;
	}
	public void setoSign(int oSign) {
		this.oSign = oSign;
	}
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="oUId",referencedColumnName="uId",insertable=false, updatable=false)
	public Users getoUId() {
		return oUId;
	}
	public void setoUId(Users oUId) {
		this.oUId = oUId;
	}
	
	@OneToOne
	@JoinColumn(name="oAId")
	public Address getoAId() {
		return oAId;
	}
	public void setoAId(Address oAId) {
		this.oAId = oAId;
	}
	

}
