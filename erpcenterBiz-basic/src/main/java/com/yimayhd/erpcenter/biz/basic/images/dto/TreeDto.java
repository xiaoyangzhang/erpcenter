package com.yimayhd.erpcenter.biz.basic.images.dto;

import java.io.Serializable;

public class TreeDto implements Serializable{

	private static final long serialVersionUID = -4050444376796201673L;
	
	
	private Integer id;
	private String name;
	private Integer pId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	
	@Override
	public String toString() {
		return "TreeDto [id=" + id + ", name=" + name + ", pId=" + pId + "]";
	}
	
	
}
