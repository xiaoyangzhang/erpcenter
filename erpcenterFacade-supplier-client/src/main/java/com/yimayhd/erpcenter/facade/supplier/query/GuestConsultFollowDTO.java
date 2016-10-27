package com.yimayhd.erpcenter.facade.supplier.query;

import java.io.Serializable;

import com.yimayhd.erpresource.dal.po.GuestConsultFollow;
/**
 * 咨询跟进情况对象参数
 * @author liyong
 * 2016年10月26日 描述：
 */
public class GuestConsultFollowDTO implements Serializable {
	
	private GuestConsultFollow guestConsultFollow;
	
	public void setGuestConsultFollow(GuestConsultFollow guestConsultFollow) {
		this.guestConsultFollow = guestConsultFollow;
	}
	
	public GuestConsultFollow getGuestConsultFollow() {
		return guestConsultFollow;
	}

}
