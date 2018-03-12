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
public class Goods implements Serializable{

	/**
	 *  序列化
	 */
	private static final long serialVersionUID = 7050644268046358208L;
	private String gId;
	private String gName;
	private String gPrice;
	private String gDescribe;
	private int gSign;
	private Brand gBId;
	private Degree gDeId;
	private Users gUId;
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	public String getgId() {
		return gId;
	}
	public void setgId(String gId) {
		this.gId = gId;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public String getgPrice() {
		return gPrice;
	}
	public void setgPrice(String gPrice) {
		this.gPrice = gPrice;
	}
	public String getgDescribe() {
		return gDescribe;
	}
	public void setgDescribe(String gDescribe) {
		this.gDescribe = gDescribe;
	}
	public int getgSign() {
		return gSign;
	}
	public void setgSign(int gSign) {
		this.gSign = gSign;
	}
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="gBId")
	public Brand getgBId() {
		return gBId;
	}
	public void setgBId(Brand gBId) {
		this.gBId = gBId;
	}
	@OneToOne
	@JoinColumn(name="gDeId")
	public Degree getgDeId() {
		return gDeId;
	}
	public void setgDeId(Degree gDeId) {
		this.gDeId = gDeId;
	}
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="gUId")
	public Users getgUId() {
		return gUId;
	}
	public void setgUId(Users gUId) {
		this.gUId = gUId;
	}
	

	
}
