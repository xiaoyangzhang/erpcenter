package com.yimayhd.erpcenter.dal.sales.tj.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceCommission;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.TJGroupProfit;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.TJGroupQueryId;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.TJGroupShop;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.TJRecord;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.TJShopProject;
import com.yimayhd.erpcenter.dal.sales.client.tj.service.TJDal;
import com.yimayhd.erpcenter.dal.sales.finance.dao.FinanceCommissionMapper;
import com.yimayhd.erpcenter.dal.sales.tj.dao.TJMapper;

public class TJDalImpl implements TJDal {
	@Autowired
	private TJMapper tjMapper;
	
	@Autowired
	private FinanceCommissionMapper commissionMapper;
	
	@Override
	public void clearTjShopProject() {
		tjMapper.clearTjShopProject();
	}
	
	@Override
	public PageBean tjShopProject(PageBean pageBean,String start) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> result = tjMapper.selectTjShopProjestListPage(pageBean,start);
		pageBean.setResult(result);
		return pageBean;
	}

	@Override
	public void insertTjShopProject(TJShopProject tjShopProject) {
		tjMapper.insertTjShopProjest(tjShopProject);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageBean selectShopProjectListPage(PageBean pageBean, Integer bizId) {
		List<Map<String,Object>> result = tjMapper.selectShopProjectListPage(pageBean, bizId);
		for (int i=0;i<result.size();i++) {
			Map map = (Map) result.get(i);
			List<Map<String,Object>> bookingShopDetailList = tjMapper.getShopProjectListByShopId(pageBean, Integer.parseInt(map.get("supplier_id").toString()));
			map.put("bookingShopDetailList", bookingShopDetailList);
		}
		pageBean.setResult(result);
		return pageBean;
	}

	@Override
	public Map<String,Object> selectCount(PageBean pageBean, Integer bizId) {
		return tjMapper.selectCount(pageBean, bizId);
	}
	@Override
	public Map<String,Object> selectDetailCount(PageBean pageBean, Integer bizId) {
		return tjMapper.selectDetailCount(pageBean, bizId);
	}
	
	@Override
	public Date selectRecordEndTime(String type,Integer bizId) {
		return tjMapper.selectRecordEndTime(type,bizId);
	}
	

	@Override
	public List<TJGroupQueryId> findAllGroupIdList(){
		return tjMapper.findAllGroupIdList();
	}
	@Override
	public List<TJGroupQueryId> findUpdatedGroupIdList(String type){
		return tjMapper.findUpdatedGroupIdList(type);
	}
	@Override
	public void clearAllGroup(){
		tjMapper.clearAllGroup();
	}
	@Override
	public void clearUpdatedGroup(List<TJGroupQueryId> groupIdList){
		tjMapper.clearUpdatedGroup(groupIdList);
	}
	@Override
	public void clearUpdatedGroupShop(List<TJGroupQueryId> groupIdList){
		tjMapper.clearUpdatedGroupShop(groupIdList);
	}
	@Override
	public void insertGroupProfit(TJGroupProfit gp){
		tjMapper.insertGroupProfit(gp);
	}
	@Override
	public void insetTJRecord(TJRecord tjRecord) {
		tjMapper.insetTJRecord(tjRecord);
	}
	
	@Override
	public List<TJGroupShop> selectTJGroupShop(Integer groupId){
		
		return tjMapper.selectTJGroupShopByGroupId(groupId);
	}

	private BigDecimal getCommsSumTotal(List<FinanceCommission> comms){
		
		BigDecimal sumTotal = new BigDecimal(0);
		if(comms == null || comms.size() == 0){
			return sumTotal;
		}
		
		FinanceCommission comm = null;
		for(int i = 0; i < comms.size(); i++){
			comm = comms.get(i);
			if(comm.getTotal() == null){
				continue;
			}
			sumTotal = sumTotal.add(comm.getTotal());
		}
		return sumTotal;
	}

	@Override
	public void insertTJGroupShop(TJGroupShop groupShop) {
		tjMapper.insertTJGroupShop(groupShop);
	}

	@Override
	public void clearTJGroupShop() {
		tjMapper.clearTJGroupShop();
	}

	@Override
	public PageBean selectTJGroupListPage(PageBean pageBean) {
		List<Map<String, Object>> list = tjMapper.selectTJGroupListPage(pageBean);
		pageBean.setResult(list);
		return pageBean;
	}

	@Override
	public List<Map<String, Object>> selectTJShopOfGroup(Map<String, Object> params) {
		List<Map<String, Object>> list = tjMapper.selectTJShopOfGroup(params);
		return list;
	}

	@Override
	public Map<String,Object> selectTJGroupShopTotal(PageBean pageBean) {
		return tjMapper.selectTJGroupShopTotal(pageBean);
	}
	
	@Override
	public Map<String,Object> selectTJGroupShopCommTotal(PageBean pageBean) {
		return tjMapper.selectTJGroupShopCommTotal(pageBean);
	}
	
	@Override
	public PageBean<TJGroupProfit> selectGroupProfitListPage(PageBean<TJGroupProfit> pageBean){
		List<TJGroupProfit> result = tjMapper.selectGroupProfitListPage(pageBean);
		pageBean.setResult(result);
		return pageBean;
	}
	@Override
	public TJGroupProfit selectTotalGroupProfit(PageBean pageBean){
		return tjMapper.selectTotalGroupProfit(pageBean);
	}
	
	//单团利润统计（实时统计）ou
	@Override
	public PageBean<TJGroupProfit> selectGroupProfitListPageOu(PageBean<TJGroupProfit> pageBean, Integer bizId, String dataUser) {
		List<TJGroupProfit> result = tjMapper.selectGroupProfitListPageOu(pageBean, bizId, dataUser);
		pageBean.setResult(result);
		return pageBean;
	}

	@Override
	public TJGroupProfit selectTotalGroupProfitOu(PageBean<TJGroupProfit> pageBean, Integer bizId, String dataUser) {
		return tjMapper.selectTotalGroupProfitOu(pageBean, bizId, dataUser);
	}

	@Override
	public PageBean selectTJShopListPage(PageBean pageBean){
		List<Map<String, Object>> list = tjMapper.selectTJShopListPage(pageBean);
		pageBean.setResult(list);
		return pageBean;
	}
	
	@Override
	public Map<String, Object> selectTJShopTotal(PageBean pageBean){
		return tjMapper.selectTJShopTotal(pageBean);
	}

	@Override
	public Map<String, Object> selectLineProfitCount(PageBean pageBean) {
		return tjMapper.selectLineProfitCount(pageBean);
	}

	@Override
	public List<TJShopProject> selectTJShopProjecectByDetailId(Integer groupId,Integer supplierId,Integer detailId) {
		return tjMapper.selectTJShopProjecectByDetailId(groupId,supplierId,detailId);
	}

	@Override
	public void updateTjShopProject(TJShopProject tjShopProject) {
		tjMapper.updateTjShopProject(tjShopProject);
		
	}
	
	@Override
	public PageBean selectLineProfitsPageAbc(PageBean pageBean) {
		List<Map<String,Object>> result = tjMapper.selectLineProfitsPageAbc(pageBean);
		pageBean.setResult(result);
		return pageBean;
	}
	
	@Override
	public PageBean selectLineProfitListPage(PageBean pageBean) {
		List<Map<String,Object>> result = tjMapper.selectLineProfitListPage(pageBean);
		pageBean.setResult(result);
		return pageBean;
	}

}
