package com.ckey.shijing.vo;

import java.util.Date;

public class PointRecordVO {
	private Date time;
	private double grade;
	private String behave;
	private String username;
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public String getBehave() {
		return behave;
	}
	public void setBehave(String behave) {
		this.behave = behave;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
