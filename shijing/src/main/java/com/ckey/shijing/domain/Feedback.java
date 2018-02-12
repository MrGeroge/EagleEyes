package com.ckey.shijing.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "feedback")
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "app_version")
	private double app_version;
	@Column(name = "contact")
	private String contact;
	@Lob
	@Column(name = "content")
	private String content;
	@Column(name = "time")
	private Date time = new Date();
	@OneToOne
	private PointRecord pointRecord; // 一条反馈对应于一条积分记录
	@ManyToOne
	private Account account;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getApp_version() {
		return app_version;
	}

	public void setApp_version(double app_version) {
		this.app_version = app_version;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public PointRecord getPointRecord() {
		return pointRecord;
	}

	public void setPointRecord(PointRecord pointRecord) {
		this.pointRecord = pointRecord;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
