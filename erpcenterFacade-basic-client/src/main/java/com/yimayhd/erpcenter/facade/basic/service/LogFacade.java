package com.yimayhd.erpcenter.facade.basic.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.LogOperator;
import com.yimayhd.erpcenter.facade.basic.result.SingleListResult;

/**
 * @author hongfei.guo
 * @create 2016年10月25日 上午10:55:42
 */
public interface LogFacade {
	
	SingleListResult singleList(LogOperator log, Integer bizId);
	
	void insert(List<LogOperator> list);
}
