package com.yimayhd.erpcenter.facade.sales.query.fitorder;

import java.io.Serializable;
import java.util.Date;

import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;

public class ToAddGroupOrderDTO implements Serializable {
	private PlatformEmployeePo curUser;
	private Integer productId;
	private Date date;
	private Integer curBizId;

	public PlatformEmployeePo getCurUser() {
		return curUser;
	}

	public void setCurUser(PlatformEmployeePo curUser) {
		this.curUser = curUser;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getCurBizId() {
		return curBizId;
	}

	public void setCurBizId(Integer curBizId) {
		this.curBizId = curBizId;
	}
}
