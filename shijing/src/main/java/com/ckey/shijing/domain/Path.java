package com.ckey.shijing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Path {
	@Id
	@GeneratedValue
	private int id;
	@Column
	private String longitude;
	@Column
	private String latitude;
	@Column
	private String des;
	@Column
	private boolean flag;// 是否签到的标志位
	@Column
	private String midSize;
	@ManyToOne(targetEntity = Account.class,fetch = FetchType.EAGER)
	private Account account;
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMidSize() {
		return midSize;
	}

	public void setMidSize(String midSize) {
		this.midSize = midSize;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
