package com.ckey.shijing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class IndexData {
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
	private String midSize;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getMidSize() {
		return midSize;
	}
	public void setMidSize(String midSize) {
		this.midSize = midSize;
	}
}
