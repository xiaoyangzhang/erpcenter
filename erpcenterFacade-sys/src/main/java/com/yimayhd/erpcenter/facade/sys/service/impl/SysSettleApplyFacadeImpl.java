package com.yimayhd.erpcenter.facade.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sys.service.SettleApplyBiz;
import com.yimayhd.erpcenter.dal.sys.po.SettleApply;
import com.yimayhd.erpcenter.dal.sys.po.SettleApplyResult;
import com.yimayhd.erpcenter.facade.sys.query.SettleApplyDTO;
import com.yimayhd.erpcenter.facade.sys.result.SettleApplyFacadeResult;
import com.yimayhd.erpcenter.facade.sys.result.SettleApplyResultListResult;
import com.yimayhd.erpcenter.facade.sys.service.SysSettleApplyFacade;

public class SysSettleApplyFacadeImpl implements SysSettleApplyFacade {

	@Autowired
	private SettleApplyBiz settleApplyBiz;


	@Override
	public int chkExistByName(String name) {
		return settleApplyBiz.chkExistByName(name);
	}

	/**
	 * 新增申请后返回服务号
	 */
	@Override
	public String addApply(SettleApplyDTO apply) {
		return settleApplyBiz.addApply(apply.getSettleApply());
	}

	public static void main(String[] args) {

		System.out.print(String.format("%05d", 1));
	}

	@Override
	public PageBean applyList(SettleApplyDTO settleApply, Integer page) {
		return settleApplyBiz.applyList(settleApply.getSettleApply(), page);
	}

	@Override
	public SettleApplyFacadeResult getSettleApplyById(Integer id) {
		SettleApply settleApply = settleApplyBiz.getSettleApplyById(id);
		SettleApplyFacadeResult result = new SettleApplyFacadeResult();
		result.setSettleApply(settleApply);
		return result;

	}

	@Override
	public SettleApplyResultListResult getSettleApplyResults() {

		List<SettleApplyResult>  settleApplyResults= settleApplyBiz.getSettleApplyResults();
		SettleApplyResultListResult result = new SettleApplyResultListResult();
		result.setSettleApplyResults(settleApplyResults);
		return result;
	}

	/*@Override
	public void updateApplyResultById(SettleApplyResult result) {
		 SettleApply apply = applyDao.selectById(result.getApplyId());
		 
			// 如果isApprove==0,则初审不通过,状态值改为-1
			if (result.getIsApprove() == 0) {
				apply.setState(-1);
				result.setState(-1);
			}
			// 否则初审通过，待复审
			else {
				apply.setState((apply.getState() + 1));
				result.setState(apply.getState());
			}
		
		result.setOperateTime(System.currentTimeMillis());
		 //需要获取当前登录者--------------
		 result.setOperatorName("aa");
		 result.setOperatorId(1);
		applyResultDao.insert(result);
		applyDao.updateByIdSelective(apply);
	}
*/
	@Override
	public void updateApplyById(SettleApplyDTO apply) {
	    settleApplyBiz.updateApplyById(apply.getSettleApply());
	}

	@Override
	public SettleApplyResultListResult getResultByApplyid(Integer applyId) {
		List<SettleApplyResult>  settleApplyResults = settleApplyBiz.getResultByApplyid(applyId);
		SettleApplyResultListResult result = new SettleApplyResultListResult();
		result.setSettleApplyResults(settleApplyResults);
		return result;
	}

}
