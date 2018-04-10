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
public class OrderDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7718362802541559451L;
	private String dId;
	private String dSubTotal;
	private String dNumber;
	private Goods dGId;
	private Express dExId;
	private OrderInfo dOId;
	private Users dUId;
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	public String getdId() {
		return dId;
	}
	public void setdId(String dId) {
		this.dId = dId;
	}
	public String getdSubTotal() {
		return dSubTotal;
	}
	public void setdSubTotal(String dSubTotal) {
		this.dSubTotal = dSubTotal;
	}
	public String getdNumber() {
		return dNumber;
	}
	public void setdNumber(String dNumber) {
		this.dNumber = dNumber;
	}
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="dUId")
	public Users getdUId() {
		return dUId;
	}
	public void setdUId(Users dUId) {
		this.dUId = dUId;
	}
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="dGId")
	public Goods getdGId() {
		return dGId;
	}
	public void setdGId(Goods dGId) {
		this.dGId = dGId;
	}
	@OneToOne
	@JoinColumn(name="dExId")
	public Express getdExId() {
		return dExId;
	}
	public void setdExId(Express dExId) {
		this.dExId = dExId;
	}
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="dOId")
	public OrderInfo getdOId() {
		return dOId;
	}
	public void setdOId(OrderInfo dOId) {
		this.dOId = dOId;
	}
	
	

}
