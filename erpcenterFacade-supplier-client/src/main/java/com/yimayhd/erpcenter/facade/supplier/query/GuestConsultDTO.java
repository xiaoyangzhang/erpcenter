package com.yimayhd.erpcenter.facade.supplier.query;

import java.io.Serializable;

import com.yimayhd.erpresource.dal.po.GuestConsult;
/**
 * 咨询客户参数对象
 * @author liyong
 * 2016年10月26日 
 */
public class GuestConsultDTO implements Serializable{
	
	/**
	 * @author liyong
	 * 2016年10月26日 描述：
	 */
	private static final long serialVersionUID = 1L;
	private GuestConsult guestConsult;
	
	public void setGuestConsult(GuestConsult guestConsult) {
		this.guestConsult = guestConsult;
	}
	
	public GuestConsult getGuestConsult() {
		return guestConsult;
	}

}
