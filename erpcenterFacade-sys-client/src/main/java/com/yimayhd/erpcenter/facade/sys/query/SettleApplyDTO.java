package com.yimayhd.erpcenter.facade.sys.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sys.po.SettleApply;

public class SettleApplyDTO implements Serializable {

	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	
	private SettleApply settleApply = new SettleApply();
	
	public void setSettleApply(SettleApply settleApply) {
		this.settleApply = settleApply;
	}
	
	public SettleApply getSettleApply() {
		return settleApply;
	}

}
