package com.yihg.finance.impl;

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
import com.yihg.basic.exception.ClientException;
import com.yihg.finance.api.FinanceBillService;
import com.yihg.finance.dao.FinanceBillMapper;
import com.yihg.mybatis.utility.PageBean;
import com.yihg.sales.dao.TourGroupMapper;
import com.yihg.sales.po.FinanceBill;
import com.yihg.sales.po.FinanceBillDetail;


public class FinanceBillServiceImpl implements FinanceBillService{
	
	@Autowired
	private FinanceBillMapper bsMapper;
	
	@Autowired
	private TourGroupMapper tourGroupMapper;

	@Override
	public PageBean<Map> statisBillList(PageBean<Map> pageBean){
		List<Map> result = bsMapper.statisBillListPage(pageBean);
		pageBean.setResult(result);
		return pageBean;
	}
	
	@Override
	public Map statisBillTotal(Map parameter){
		return bsMapper.statisBillTotal(parameter);
	}
	
	/**
	 * 查询派单列表
	 */
	@Override
	public PageBean selectreceiveOrderListSelectPage(PageBean pageBean,
			Integer bizId, String type, Set<Integer> set) {
		StringBuffer str=new StringBuffer();
		
		Map<String, Object> parameters = (Map<String, Object>)pageBean.getParameter();
		String dateType = parameters.get("dateType") != null ? parameters.get("dateType").toString() : "";
		if("appliDate".equals(dateType)){
			Object startTime = parameters.get("startTime");
			if(startTime != null){
				parameters.put("startTime", startTime.toString() + " 00:00:00");
			}
			Object endTime = parameters.get("endTime");
			if(endTime != null){
				parameters.put("endTime", endTime.toString() + " 59:59:59");
			}
		}
		List<FinanceBill> result = bsMapper.selectreceiveOrderListPage(pageBean, bizId, type, set);
		
		for (int i=0;i<result.size();i++) {
			List<Map> tourFinanceBillDetail = new ArrayList<Map>();
			Map financeBill = new HashMap<String, Object>();
			financeBill = (Map) result.get(i);
			
			List<FinanceBillDetail> billList = bsMapper.getbillListByIdAndGuideId(bizId, financeBill.get("id").toString(), financeBill.get("guide_id").toString());
			for(int j=0;j<billList.size();j++){
				Map financeBillDetail = new HashMap<String, Object>();
				financeBillDetail = (Map) billList.get(j);
				tourFinanceBillDetail.add(financeBillDetail);
			}
			financeBill.put("financeBillDetailList", tourFinanceBillDetail);
			financeBill.put("financeBillNum", tourFinanceBillDetail.size());
		}
		pageBean.setResult(result);
		return pageBean;
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
	    List<FinanceBillDetail> list = bsMapper.getbillListByIdAndGuideId(bizId, id, guideId);
		return list;
	}
	
	@Transactional
	@Override
	public void batchInsertBillDetail(Integer bizId, String userName, String json) {
		
		if(StringUtils.isEmpty(json)){
			throw new ClientException("请输入正确的数据");
		}
		
		JSONObject obj = JSON.parseObject(json);
		Integer groupId = Integer.parseInt(obj.getString("groupId"));
		Integer guideId = Integer.parseInt(obj.getString("guideId"));
		String notIds = obj.getString("notIds");
		JSONArray list = obj.getJSONArray("list");
		
		
		FinanceBill billOrder = bsMapper.selectBillOrder(bizId, groupId, guideId);
		
		if(billOrder == null){
			FinanceBill order = new FinanceBill();
			order.setBizId(bizId);
			order.setGroupId(groupId);
			order.setGuideId(guideId);
			order.setApplicant(userName);
			order.setAppliState("APPLIED");
			bsMapper.insertBillOrder(order);
		}
		
		//添加领单
		FinanceBillDetail detail = null;
		JSONObject item = null;
		
		//删除领单详情
		bsMapper.deleteBillDetailByIds(bizId, groupId, guideId,notIds);
		
		for(int i = 0; i < list.size(); i++){
			item = list.getJSONObject(i);
			if(item == null){
				continue;
			}
			if(StringUtils.isEmpty(item.getString("id"))){
				detail = new FinanceBillDetail();
				detail.setBizId(bizId);
				detail.setGroupId(groupId);
				detail.setGuideId(guideId);
				detail.setBillType(item.getString("billType"));
				detail.setBillBusiness(item.getString("billBusiness"));
				detail.setNum(item.getInteger("num"));
				detail.setRemark(item.getString("remark"));
				
				bsMapper.insertBillDetail(detail);
			}else if(!StringUtils.isEmpty(item.getString("id"))){
				//修改领单详情
				bsMapper.saveApplyBill(item.getInteger("id"),item.getString("billType"),item.getInteger("num"),item.getString("remark"));
			}
		}
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
		bsMapper.deleteBillOrder(bizId, groupId, guideId);
		bsMapper.deleteBillDetail(bizId, groupId, guideId);
	}

	/**
	 * 派单保存
	 */
	public void saveDistributeBill(String billList){
		List<FinanceBillDetail> legs = JSON.parseArray(billList, FinanceBillDetail.class);
		for (FinanceBillDetail financeBillDetail : legs) {
			bsMapper.saveDistributeBill(financeBillDetail.getId(),financeBillDetail.getReceivedNum()
					,financeBillDetail.getBillNumReceive(),financeBillDetail.getRemarkReceive());
		}
	}
	/**
	 * 销单保存
	 */
	public void saveVerifyBill(String billList){
		List<FinanceBillDetail> legs = JSON.parseArray(billList, FinanceBillDetail.class);
		for (FinanceBillDetail financeBillDetail : legs) {
			bsMapper.saveVerifyBill(financeBillDetail.getId(),financeBillDetail.getReturndNum()
					,financeBillDetail.getBillNumReturn(),financeBillDetail.getRemarkReturn());
		}
	}
	/**
	 * 修改派单表
	 */
	public void updateFinanceOrder(String userName,Integer groupId,Integer guideId,Date date,String type){
		bsMapper.updateFinanceOrder(userName,groupId,guideId,date,type);
	}
	/**
	 * 获取申请人列表
	 */
	@Override
	public List<Map<String,String>> getFuzzyApplicantList(Integer bizId,String name) {
		
		return bsMapper.getFuzzyApplicantList(bizId,name);
	}
	/**
	 * 获取操作员列表
	 */
	@Override
	public List<Map<String, String>> getUserNameList(Integer bizId, String name) {
		return bsMapper.getUserNameList(bizId,name);
	}
	/**
	 * 取消销单
	 */
	@Override
	public void delVerify(String order_id,String type) {
		bsMapper.delVerify(order_id,type);
	}
	/**
	 * 取消领单
	 */
	@Override
	public void delReceived(String group_id, String guide_id) {
		bsMapper.delReceived(group_id,guide_id);
	}

	/**
	 * 根据导游安排张的groupID查询领单申请信息
	 */
	@Override
	public List<FinanceBill> findByGroupId(FinanceBill fBill) {
		return bsMapper.selectByGroupId(fBill);
	}
}
