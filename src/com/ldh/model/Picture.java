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
public class Picture implements Serializable{

	/**
	 *  序列化
	 */
	private static final long serialVersionUID = -6871521862129438003L;
	private String pId;
	private String pUrl;
	private Goods pGId;
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getpUrl() {
		return pUrl;
	}
	public void setpUrl(String pUrl) {
		this.pUrl = pUrl;
	}
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="pGId")
	public Goods getpGId() {
		return pGId;
	}
	public void setpGId(Goods pGId) {
		this.pGId = pGId;
	}
	
}
