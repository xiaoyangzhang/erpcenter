package com.yimayhd.erpcenter.biz.sales.service.impl.finance;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBillBiz;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.sales.client.finance.service.FinanceBillDal;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.FinanceBill;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.FinanceBillDetail;


public class FinanceBillBizImpl implements FinanceBillBiz{
	
	@Autowired
	private FinanceBillDal financeBillDal;

	@Override
	public PageBean<Map> statisBillList(PageBean<Map> pageBean){
		return financeBillDal.statisBillList(pageBean);
	}
	
	@Override
	public Map statisBillTotal(Map parameter){
		return financeBillDal.statisBillTotal(parameter);
	}
	
	/**
	 * 查询派单列表
	 */
	@Override
	public PageBean selectreceiveOrderListSelectPage(PageBean pageBean,
			Integer bizId, String type, Set<Integer> set) {
		return financeBillDal.selectreceiveOrderListSelectPage(pageBean, bizId, type, set);
	}
	
	/**
	 * 根据团id和导游id获取billList
	 * @param bizId
	 * @param id
	 * @param guideId
	 * @return
	 */
	@Override
	public List<FinanceBillDetail> getbillListByIdAndGuideId(Integer bizId, String id, String guideId) {
		return financeBillDal.getbillListByIdAndGuideId(bizId, id, guideId);
	}
	
	@Transactional
	@Override
	public void batchInsertBillDetail(Integer bizId, String userName, String json) {
		financeBillDal.batchInsertBillDetail(bizId, userName, json);
	}
	
	/**
	 * 删除领单
	 * @param bizId
	 * @param groupId
	 * @param guideId
	 */
	@Transactional
	@Override
	public void deleteBillOrder(Integer bizId, Integer groupId, Integer guideId) {
		financeBillDal.deleteBillOrder(bizId, groupId, guideId);
	}

	/**
	 * 派单保存
	 */
	public void saveDistributeBill(String billList){
		financeBillDal.saveDistributeBill(billList);
	}
	/**
	 * 销单保存
	 */
	public void saveVerifyBill(String billList){
		financeBillDal.saveVerifyBill(billList);
	}
	/**
	 * 修改派单表
	 */
	public void updateFinanceOrder(String userName,Integer groupId,Integer guideId,Date date,String type){
		financeBillDal.updateFinanceOrder(userName, groupId, guideId, date, type);
	}
	/**
	 * 获取申请人列表
	 */
	@Override
	public List<Map<String,String>> getFuzzyApplicantList(Integer bizId,String name) {
		return financeBillDal.getFuzzyApplicantList(bizId, name);
	}
	/**
	 * 获取操作员列表
	 */
	@Override
	public List<Map<String, String>> getUserNameList(Integer bizId, String name) {
		return financeBillDal.getUserNameList(bizId, name);
	}
	/**
	 * 取消销单
	 */
	@Override
	public void delVerify(String order_id,String type) {
		financeBillDal.delVerify(order_id, type);
	}
	/**
	 * 取消领单
	 */
	@Override
	public void delReceived(String group_id, String guide_id) {
		financeBillDal.delReceived(group_id, guide_id);
	}

	/**
	 * 根据导游安排张的groupID查询领单申请信息
	 */
	@Override
	public List<FinanceBill> findByGroupId(FinanceBill fBill) {
		return financeBillDal.findByGroupId(fBill);
	}
}
