package com.yimayhd.erpcenter.dal.basic.client.basic.po;

import java.io.Serializable;

public class RegionInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5879285702321138127L;
	private Integer id;
	private String name;

	private Integer pid;

	private int orderid;

	private int status;

	private int level;

	public RegionInfo() {
	}

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

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
