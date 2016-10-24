package com.yimayhd.erpcenter.facade.sys.service;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.facade.sys.query.SettleApplyDTO;
import com.yimayhd.erpcenter.facade.sys.result.SettleApplyFacadeResult;
import com.yimayhd.erpcenter.facade.sys.result.SettleApplyResultListResult;

public interface SysSettleApplyFacade {

	/**
	 * 根据公司全称判断是否存在
	 * 
	 * @param name
	 * @return
	 */
	int chkExistByName(String name);

	/**
	 * 添加申请信息
	 * 
	 * @param apply
	 */
	String addApply(SettleApplyDTO apply);

	PageBean applyList(SettleApplyDTO settleApply, Integer page);

	SettleApplyFacadeResult getSettleApplyById(Integer id);

	SettleApplyResultListResult getSettleApplyResults();

	//void updateApplyResultById(SettleApplyResult result);

	 void updateApplyById(SettleApplyDTO apply);
	 SettleApplyResultListResult getResultByApplyid(Integer applyId);
}
