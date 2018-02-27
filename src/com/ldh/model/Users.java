package com.ldh.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Users implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8841947288266107836L;
	private String uId;
	private String uName;
	private String uPassword;
	private String uPhone;
	private String uMail;
	private String uPicture;
	private String uFraction;
	private String uMoney;
	private int uSign;
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuPassword() {
		return uPassword;
	}
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	public String getuPhone() {
		return uPhone;
	}
	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}
	public String getuMail() {
		return uMail;
	}
	public void setuMail(String uMail) {
		this.uMail = uMail;
	}
	public String getuPicture() {
		return uPicture;
	}
	public void setuPicture(String uPicture) {
		this.uPicture = uPicture;
	}
	public String getuFraction() {
		return uFraction;
	}
	public void setuFraction(String uFraction) {
		this.uFraction = uFraction;
	}
	public String getuMoney() {
		return uMoney;
	}
	public void setuMoney(String uMoney) {
		this.uMoney = uMoney;
	}
	public int getuSign() {
		return uSign;
	}
	public void setuSign(int uSign) {
		this.uSign = uSign;
	}
}
