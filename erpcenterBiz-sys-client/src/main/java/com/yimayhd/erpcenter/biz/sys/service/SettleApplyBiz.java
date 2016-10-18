package com.yimayhd.erpcenter.biz.sys.service;

import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.po.SettleApply;
import com.yimayhd.erpcenter.dal.sys.po.SettleApplyResult;

public interface SettleApplyBiz {

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
	String addApply(SettleApply apply);

	PageBean applyList(SettleApply settleApply, Integer page);

	SettleApply getSettleApplyById(Integer id);

	List<SettleApplyResult> getSettleApplyResults();

	//void updateApplyResultById(SettleApplyResult result);

	 void updateApplyById(SettleApply apply);
	 List<SettleApplyResult> getResultByApplyid(Integer applyId);
}
