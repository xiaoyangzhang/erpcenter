package com.yihg.taobao.pojo;

import java.io.Serializable;

public class TaobaoSKU implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 155493752487030600L;

	private String property_alias;
	
	private String num_iid;

	public String getProperty_alias() {
		return property_alias;
	}

	public void setProperty_alias(String property_alias) {
		this.property_alias = property_alias;
	}

	public String getNum_iid() {
		return num_iid;
	}

	public void setNum_iid(String num_iid) {
		this.num_iid = num_iid;
	}
	
	
}
