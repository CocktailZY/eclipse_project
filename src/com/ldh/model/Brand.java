package com.ldh.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Brand implements Serializable{

	/**
	 *  序列化
	 */
	private static final long serialVersionUID = -7714546936604670969L;
	private String bId;
	private String bDescribe;
	
	@Id
	@GeneratedValue(generator = "UUID")
	public String getbId() {
		return bId;
	}
	public void setbId(String bId) {
		this.bId = bId;
	}
	public String getbDescribe() {
		return bDescribe;
	}
	public void setbDescribe(String bDescribe) {
		this.bDescribe = bDescribe;
	}

}
