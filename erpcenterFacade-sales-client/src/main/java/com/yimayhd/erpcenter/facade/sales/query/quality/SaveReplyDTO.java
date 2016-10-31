package com.yimayhd.erpcenter.facade.sales.query.quality;

import java.io.Serializable;

public class SaveReplyDTO implements Serializable {
	private static final long serialVersionUID = 6900531113221312986L;
	private Integer id;
	private String reply;
	private Integer employeeId;
	private String userName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
