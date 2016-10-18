package com.yimayhd.erpcenter.biz.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sys.service.SettleApplyBiz;
import com.yimayhd.erpcenter.dal.sys.po.SettleApply;
import com.yimayhd.erpcenter.dal.sys.po.SettleApplyResult;
import com.yimayhd.erpcenter.dal.sys.service.SettleApplyDal;

public class SettleApplyBizImpl implements SettleApplyBiz {

	@Autowired
	private SettleApplyDal settleApplyDal;


	@Override
	public int chkExistByName(String name) {
		return settleApplyDal.chkExistByName(name);
	}

	/**
	 * 新增申请后返回服务号
	 */
	@Override
	public String addApply(SettleApply apply) {
		return settleApplyDal.addApply(apply);
	}

	public static void main(String[] args) {

		System.out.print(String.format("%05d", 1));
	}

	@Override
	public PageBean applyList(SettleApply settleApply, Integer page) {
		return settleApplyDal.applyList(settleApply, page);
	}

	@Override
	public SettleApply getSettleApplyById(Integer id) {
		return settleApplyDal.getSettleApplyById(id);

	}

	@Override
	public List<SettleApplyResult> getSettleApplyResults() {

		return settleApplyDal.getSettleApplyResults();
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
	public void updateApplyById(SettleApply apply) {
	    settleApplyDal.updateApplyById(apply);
	}

	@Override
	public List<SettleApplyResult> getResultByApplyid(Integer applyId) {
		return settleApplyDal.getResultByApplyid(applyId);
	}

}
