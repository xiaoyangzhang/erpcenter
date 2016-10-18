package com.yimayhd.erpcenter.dal.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.dao.SettleApplyMapper;
import com.yimayhd.erpcenter.dal.sys.dao.SettleApplyResultMapper;
import com.yimayhd.erpcenter.dal.sys.po.SettleApply;
import com.yimayhd.erpcenter.dal.sys.po.SettleApplyResult;
import com.yimayhd.erpcenter.dal.sys.service.SettleApplyDal;

public class SettleApplyDalImpl implements SettleApplyDal {

	@Autowired
	private SettleApplyMapper applyDao;
	@Autowired
	private SettleApplyResultMapper applyResultDao;

	@Override
	public int chkExistByName(String name) {
		if (StringUtils.isBlank(name)) {
			return 1;
		}
		return applyDao.getCountByName(name);
	}

	/**
	 * 新增申请后返回服务号
	 */
	@Override
	public String addApply(SettleApply apply) {
		apply.setCreateTime(System.currentTimeMillis());
		applyDao.insert(apply);
		apply.setServiceNo("SL" + String.format("%05d", apply.getId()));
		applyDao.updateServiceNoById(apply.getServiceNo(), apply.getId());
		return apply.getServiceNo();
	}

	public static void main(String[] args) {

		System.out.print(String.format("%05d", 1));
	}

	@Override
	public PageBean applyList(SettleApply settleApply, Integer page) {
		PageBean pageBean = new PageBean();
		pageBean.setPage(page);
		pageBean.setParameter(settleApply);
		pageBean.setPageSize(10);
		List<SettleApply> applyList = applyDao.getApplyListPage(pageBean);
		pageBean.setResult(applyList);
		return pageBean;
	}

	@Override
	public SettleApply getSettleApplyById(Integer id) {

		return applyDao.selectById(id);

	}

	@Override
	public List<SettleApplyResult> getSettleApplyResults() {

		return applyResultDao.getSettleApplyResults();
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
		applyDao.updateByIdSelective(apply);
	}

	@Override
	public List<SettleApplyResult> getResultByApplyid(Integer applyId) {
		
		return applyResultDao.getResultByApplyId(applyId);
	}

}
