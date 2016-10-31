package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.product.po.TrafficResProduct;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;

public class TrafficResProductDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TrafficResProduct productBean;
	private PlatformEmployeePo curUser;
	public TrafficResProduct getProductBean() {
		return productBean;
	}
	public void setProductBean(TrafficResProduct productBean) {
		this.productBean = productBean;
	}
	public PlatformEmployeePo getCurUser() {
		return curUser;
	}
	public void setCurUser(PlatformEmployeePo curUser) {
		this.curUser = curUser;
	}
	
	
	
}
