package com.yimayhd.erpcenter.dal.sys.po;

import java.util.ArrayList;
import java.util.List;

public class AdditionalParameters {
	private List<Object> children = new ArrayList<Object>();
	private Integer id;
	
	
	public List<Object> getChildren() {
		return children;
	}
	public void setChildren(List<Object> children) {
		this.children = children;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

}
