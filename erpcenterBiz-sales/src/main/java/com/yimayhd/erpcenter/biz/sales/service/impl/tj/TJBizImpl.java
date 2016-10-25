package com.yimayhd.erpcenter.biz.sales.service.impl.tj;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.tj.TJBiz;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.TJGroupProfit;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.TJGroupQueryId;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.TJGroupShop;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.TJRecord;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.TJShopProject;
import com.yimayhd.erpcenter.dal.sales.client.tj.service.TJDal;

public class TJBizImpl implements TJBiz {
	@Autowired
	private TJDal tjDal;
	
	
	@Override
	public void clearTjShopProject() {
		tjDal.clearTjShopProject();
	}
	
	@Override
	public PageBean tjShopProject(PageBean pageBean,String start) {
		return tjDal.tjShopProject(pageBean, start);
	}

	@Override
	public void insertTjShopProject(TJShopProject tjShopProject) {
		tjDal.insertTjShopProject(tjShopProject);
	}

	@Override
	public PageBean selectShopProjectListPage(PageBean pageBean, Integer bizId) {
		return tjDal.selectShopProjectListPage(pageBean, bizId);
	}

	@Override
	public Map<String,Object> selectCount(PageBean pageBean, Integer bizId) {
		return tjDal.selectCount(pageBean, bizId);
	}
	@Override
	public Map<String,Object> selectDetailCount(PageBean pageBean, Integer bizId) {
		return tjDal.selectDetailCount(pageBean, bizId);
	}
	
	@Override
	public Date selectRecordEndTime(String type,Integer bizId) {
		return tjDal.selectRecordEndTime(type,bizId);
	}
	

	@Override
	public List<TJGroupQueryId> findAllGroupIdList(){
		return tjDal.findAllGroupIdList();
	}
	@Override
	public List<TJGroupQueryId> findUpdatedGroupIdList(String type){
		return tjDal.findUpdatedGroupIdList(type);
	}
	@Override
	public void clearAllGroup(){
		tjDal.clearAllGroup();
	}
	@Override
	public void clearUpdatedGroup(List<TJGroupQueryId> groupIdList){
		tjDal.clearUpdatedGroup(groupIdList);
	}
	@Override
	public void clearUpdatedGroupShop(List<TJGroupQueryId> groupIdList){
		tjDal.clearUpdatedGroupShop(groupIdList);
	}
	@Override
	public void insertGroupProfit(TJGroupProfit gp){
		tjDal.insertGroupProfit(gp);
	}
	@Override
	public void insetTJRecord(TJRecord tjRecord) {
		tjDal.insetTJRecord(tjRecord);
	}
	
	@Override
	public List<TJGroupShop> selectTJGroupShop(Integer groupId){
		return tjDal.selectTJGroupShop(groupId);
	}

//	private BigDecimal getCommsSumTotal(List<FinanceCommission> comms){
//		
//		BigDecimal sumTotal = new BigDecimal(0);
//		if(comms == null || comms.size() == 0){
//			return sumTotal;
//		}
//		
//		FinanceCommission comm = null;
//		for(int i = 0; i < comms.size(); i++){
//			comm = comms.get(i);
//			if(comm.getTotal() == null){
//				continue;
//			}
//			sumTotal = sumTotal.add(comm.getTotal());
//		}
//		return sumTotal;
//	}

	@Override
	public void insertTJGroupShop(TJGroupShop groupShop) {
		tjDal.insertTJGroupShop(groupShop);
	}

	@Override
	public void clearTJGroupShop() {
		tjDal.clearTJGroupShop();
	}

	@Override
	public PageBean selectTJGroupListPage(PageBean pageBean) {
		return tjDal.selectTJGroupListPage(pageBean);
	}

	@Override
	public List<Map<String, Object>> selectTJShopOfGroup(Map<String, Object> params) {
		return tjDal.selectTJShopOfGroup(params);
	}

	@Override
	public Map<String,Object> selectTJGroupShopTotal(PageBean pageBean) {
		return tjDal.selectTJGroupShopTotal(pageBean);
	}
	
	@Override
	public Map<String,Object> selectTJGroupShopCommTotal(PageBean pageBean) {
		return tjDal.selectTJGroupShopCommTotal(pageBean);
	}
	
	@Override
	public PageBean<TJGroupProfit> selectGroupProfitListPage(PageBean<TJGroupProfit> pageBean){
		return tjDal.selectGroupProfitListPage(pageBean);
	}
	@Override
	public TJGroupProfit selectTotalGroupProfit(PageBean pageBean){
		return tjDal.selectTotalGroupProfit(pageBean);
	}
	
	//单团利润统计（实时统计）ou
	@Override
	public PageBean<TJGroupProfit> selectGroupProfitListPageOu(PageBean<TJGroupProfit> pageBean, Integer bizId, String dataUser) {
		return tjDal.selectGroupProfitListPageOu(pageBean, bizId, dataUser);
	}

	@Override
	public TJGroupProfit selectTotalGroupProfitOu(PageBean<TJGroupProfit> pageBean, Integer bizId, String dataUser) {
		return tjDal.selectTotalGroupProfitOu(pageBean, bizId, dataUser);
	}

	@Override
	public PageBean selectTJShopListPage(PageBean pageBean){
		return tjDal.selectTJShopListPage(pageBean);
	}
	
	@Override
	public Map<String, Object> selectTJShopTotal(PageBean pageBean){
		return tjDal.selectTJShopTotal(pageBean);
	}

	@Override
	public Map<String, Object> selectLineProfitCount(PageBean pageBean) {
		return tjDal.selectLineProfitCount(pageBean);
	}

	@Override
	public List<TJShopProject> selectTJShopProjecectByDetailId(Integer groupId,Integer supplierId,Integer detailId) {
		return tjDal.selectTJShopProjecectByDetailId(groupId,supplierId,detailId);
	}

	@Override
	public void updateTjShopProject(TJShopProject tjShopProject) {
		tjDal.updateTjShopProject(tjShopProject);
		
	}
	
	@Override
	public PageBean selectLineProfitsPageAbc(PageBean pageBean) {
		return tjDal.selectLineProfitsPageAbc(pageBean);
	}
	
	@Override
	public PageBean selectLineProfitListPage(PageBean pageBean) {
		return  tjDal.selectLineProfitListPage(pageBean);
	}

}
