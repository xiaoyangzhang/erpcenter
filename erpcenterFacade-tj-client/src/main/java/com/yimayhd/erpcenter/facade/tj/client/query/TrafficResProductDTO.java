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
	private int trafficResProductId;
	private Integer resId;
	private Integer productId;
	
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
	public int getTrafficResProductId() {
		return trafficResProductId;
	}
	public void setTrafficResProductId(int trafficResProductId) {
		this.trafficResProductId = trafficResProductId;
	}
	public Integer getResId() {
		return resId;
	}
	public void setResId(Integer resId) {
		this.resId = resId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
}
