package com.yimayhd.erpcenter.dal.sys.dao;

import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.SettleApplyResult;

public interface SettleApplyResultMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(SettleApplyResult record);

	int insertSelective(SettleApplyResult record);

	SettleApplyResult selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SettleApplyResult record);

	int updateByPrimaryKey(SettleApplyResult record);

	List<SettleApplyResult> getSettleApplyResults();
	 
	
	List<SettleApplyResult> getResultByApplyId(Integer applyId);
}