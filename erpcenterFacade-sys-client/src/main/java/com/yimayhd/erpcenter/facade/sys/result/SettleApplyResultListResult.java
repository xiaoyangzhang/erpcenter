package com.yimayhd.erpcenter.facade.sys.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.SettleApplyResult;
/**
 * 
 * 描述：List<SettleApplyResult> 结果封装
 * @author liyong
 * 2016年10月21日
 */
public class SettleApplyResultListResult extends ResultSupport implements Serializable{
	
	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月21日 
	 */
	private static final long serialVersionUID = 1L;
	private List<SettleApplyResult> settleApplyResults = new ArrayList<SettleApplyResult>();
	
	public void setSettleApplyResults(List<SettleApplyResult> settleApplyResults) {
		this.settleApplyResults = settleApplyResults;
	}
	
	public List<SettleApplyResult> getSettleApplyResults() {
		return settleApplyResults;
	}

}
