package com.yimayhd.erpcenter.dal.sales.client.tj.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yihg.tj.po.TJGroupProfit;
import com.yihg.tj.po.TJGroupQueryId;
import com.yihg.tj.po.TJGroupShop;
import com.yihg.tj.po.TJRecord;
import com.yihg.tj.po.TJShopProject;

public interface TJService {

	PageBean  tjShopProject(PageBean pageBean,String start);
	
	void insertTjShopProject(TJShopProject tjShopProject);
	
	PageBean selectShopProjectListPage(PageBean pageBean, Integer bizId);
	Map<String,Object> selectCount(PageBean pageBean, Integer bizId);
	Map<String, Object> selectDetailCount(PageBean pageBean, Integer bizId);
	/**
	 * 根据统计类型查询记录表中最后一次统计的结束时间
	 * @param type
	 * @return
	 */
	Date selectRecordEndTime(String type,Integer bizId);
	/**
	 * 插入统计记录
	 * @param record
	 */
	void insetTJRecord(TJRecord record);

	List<TJGroupQueryId> findAllGroupIdList();
	List<TJGroupQueryId> findUpdatedGroupIdList(String type);
	void clearAllGroup();
	void clearUpdatedGroup(List<TJGroupQueryId> groupIdList);
	void clearUpdatedGroupShop(List<TJGroupQueryId> groupIdList);
	void insertGroupProfit(TJGroupProfit gp);
	
	List<TJGroupShop> selectTJGroupShop(Integer groupId);
	
	PageBean selectTJGroupListPage(PageBean pageBean);

	void clearTjShopProject();


	List<Map<String, Object>> selectTJShopOfGroup(Map<String, Object> params);

	void insertTJGroupShop(TJGroupShop groupShop);

	void clearTJGroupShop();
	
	
	
	Map<String,Object> selectTJGroupShopTotal(PageBean pageBean);
	
	Map<String,Object> selectTJGroupShopCommTotal(PageBean pageBean);
	
	PageBean<TJGroupProfit> selectGroupProfitListPage(PageBean<TJGroupProfit> pageBean);
	TJGroupProfit selectTotalGroupProfit(PageBean pageBean);

	/*单团利润统计（实时统计）*/
	PageBean<TJGroupProfit> selectGroupProfitListPageOu(PageBean<TJGroupProfit> pageBean, Integer bizId, String dataUser);
	TJGroupProfit selectTotalGroupProfitOu(PageBean<TJGroupProfit> pageBean, Integer bizId, String dataUser);
	
	PageBean selectTJShopListPage(PageBean pageBean);
	
	Map<String, Object> selectTJShopTotal(PageBean pageBean);

	Map<String, Object> selectLineProfitCount(PageBean pageBean);
	/**
	 * 根据detail_id查询表中数据
	 * @param detailId
	 * @return
	 */
	List<TJShopProject> selectTJShopProjecectByDetailId(Integer groupId,Integer supplierId,Integer detailId);
	
	void updateTjShopProject(TJShopProject tjShopProject);
	
	PageBean selectLineProfitsPageAbc(PageBean pageBean);
	
	PageBean selectLineProfitListPage(PageBean pageBean);
}
