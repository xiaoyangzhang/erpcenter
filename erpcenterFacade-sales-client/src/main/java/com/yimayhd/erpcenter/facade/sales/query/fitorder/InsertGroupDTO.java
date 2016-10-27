package com.yimayhd.erpcenter.facade.sales.query.fitorder;

import java.io.Serializable;

public class InsertGroupDTO implements Serializable {
	private static final long serialVersionUID = -7202755344787359559L;
	private Integer id;
	private String code;
	private String curUserName;
	private Integer curUserId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCurUserName() {
		return curUserName;
	}

	public void setCurUserName(String curUserName) {
		this.curUserName = curUserName;
	}

	public Integer getCurUserId() {
		return curUserId;
	}

	public void setCurUserId(Integer curUserId) {
		this.curUserId = curUserId;
	}
}
