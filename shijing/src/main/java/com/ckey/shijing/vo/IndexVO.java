package com.ckey.shijing.vo;

import java.util.ArrayList;
import java.util.List;

import com.ckey.shijing.domain.IndexData;

public class IndexVO {
	private List<IndexData> index = new ArrayList<IndexData>();
	private String userName;
	private String imgPath;
	private double rest;
	public List<IndexData> getIndex() {
		return index;
	}
	public void setIndex(List<IndexData> index) {
		this.index = index;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public double getRest() {
		return rest;
	}
	public void setRest(double rest) {
		this.rest = rest;
	}
}
