package com.ckey.shijing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "account")
public class Account { // 账户信息
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "phone",unique=true)
	private String phone;
	@Column(name = "password")
	private String password;
	@Column(name = "img_path")
	private String imgPath;
	@Column(name = "nickname")
	private String nickname;
	@Column(name = "rest")
	private double rest; // 积分余额

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getRest() {
		return rest;
	}

	public void setRest(double rest) {
		this.rest = rest;
	}

}
