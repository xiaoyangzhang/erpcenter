package com.yimayhd.erpcenter.dal.sales.tj.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.ShopItems;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.TJGroupProfit;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.TJGroupQueryId;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.TJGroupShop;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.TJRecord;
import com.yimayhd.erpcenter.dal.sales.client.tj.po.TJShopProject;

public interface TJMapper {
	void clearTjShopProject();
		
	List<Map<String,Object>> selectTjShopProjestListPage(@Param("page") PageBean pageBean, @Param("start")String start);

	void insertTjShopProjest(TJShopProject tjShopProject);

	List<Map<String,Object>> selectShopProjectListPage(@Param("page") PageBean pageBean, @Param("bizId")Integer bizId);

	List<Map<String,Object>> getShopProjectListByShopId(@Param("page") PageBean pageBean, @Param("id")Integer id);

	Map<String, Object> selectCount(@Param("page") PageBean pageBean, @Param("bizId")Integer bizId);

	Map<String, Object> selectDetailCount(@Param("page") PageBean pageBean, @Param("bizId")Integer bizId);


	List<TJGroupQueryId> findAllGroupIdList();
	List<TJGroupQueryId> findUpdatedGroupIdList(@Param("type")String type);
	void clearAllGroup();
	void clearUpdatedGroup(List<TJGroupQueryId> groupIdList);
	void clearUpdatedGroupShop(List<TJGroupQueryId> groupIdList);
	void insertGroupProfit(TJGroupProfit gp);

	Date selectRecordEndTime(@Param("type")String type, @Param("bizId")Integer bizId);

	void insetTJRecord(TJRecord tJRecord);
	
	List<TJGroupShop> selectTJGroupShopByGroupId(@Param("groupId")Integer groupId);
	
	List<Map<String,Object>> selectTJGroupListPage(@Param("page") PageBean pageBean);

	List<TJShopProject> selectTjShopProjest(@Param("bizId")Integer bizId, @Param("dateType")String dateType,
			@Param("startMin")String startMin, @Param("startMax")String startMax, 
			@Param("shopStore")String shopStore, @Param("shopItem")String shopItem);


	List<Map<String,Object>> selectTJShopOfGroup(@Param("parameter") Map<String, Object> params);

	void insertTJGroupShop(TJGroupShop groupShop);
	
	void clearTJGroupShop();
	
	Map<String, Object> selectTJGroupShopTotal(@Param("page") PageBean pageBean);
	
	Map<String, Object> selectTJGroupShopCommTotal(@Param("page") PageBean pageBean);
	
	List<TJGroupProfit> selectGroupProfitListPage(PageBean pageBean);
	TJGroupProfit selectTotalGroupProfit(PageBean pageBean);
	
	/*单团利润统计（实时统计）ou*/
	List<TJGroupProfit> selectGroupProfitListPageOu(@Param("page") PageBean pageBean, @Param("bizId") Integer bizId, @Param("dataUser") String dataUser);
	TJGroupProfit selectTotalGroupProfitOu(@Param("page") PageBean pageBean, @Param("bizId") Integer bizId, @Param("dataUser") String dataUser);
	
	List<Map<String, Object>> selectTJShopListPage(@Param("page") PageBean pageBean);
	
	Map<String, Object> selectTJShopTotal(@Param("page") PageBean pageBean);
	

	Map<String, Object> selectLineProfitCount(@Param("page") PageBean pageBean);

	List<TJShopProject> selectTJShopProjecectByDetailId(@Param("groupId")Integer groupId,@Param("supplierId")Integer supplierId,@Param("detailId")Integer detailId);

	void updateTjShopProject(TJShopProject tjShopProject);
	
	void updateTjShopProjectByBookingId(TJShopProject tjShopProject);

	void updateTjShopProjectByDetailId(TJShopProject tjShopProject);
	
	void deleteByDetailId(Integer detailId);
	
	void deleteByBookingId(Integer bookingId);
	
	List<Map<String, Object>> selectLineProfitsPageAbc(@Param("page") PageBean pageBean);
	
	List<Map<String, Object>> selectLineProfitListPage(@Param("page") PageBean pageBean);

	List<ShopItems> selectShopItemsListPage(PageBean<ShopItems> pageBean);

	List<ShopItems> selectShopListPage(PageBean<ShopItems> pageBean);

}
