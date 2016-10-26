package com.yimayhd.erpcenter.biz.sales.client.service.operation;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingGuidesVO;

public interface BookingGuideBiz {
	void deleteByPrimaryKey(Integer id) throws ClientException;
	
	void deleteGuideWithFinanceByPrimaryKey(Integer id) throws ClientException;

	int insert(BookingGuide record);

	int insertSelective(BookingGuidesVO record);
	
	BookingGuide selectByGroupIdAndGuideId(Integer groupId,Integer guideId);

	BookingGuide selectByPrimaryKey(Integer id);
	/**
	 * 查询默认导游
	 * @param groupId
	 * @return
	 */
	BookingGuide selectByGroupId(Integer groupId);
	List<BookingGuide> selectByGroupId2(Integer groupId);

	int updateByPrimaryKeySelective(BookingGuide record);

	int updateByPrimaryKey(BookingGuide record);
	
	Integer getSelectCountByGruopId(Integer groupId);
	
	List<BookingGuidesVO> selectBookingGuideVoByGroupId(Integer groupId);
	BookingGuidesVO selectBookingGuideVoByGroupIdAndId(Integer id);
	/**
	 * 根据groupId查询导游信息
	 * @param groupId
	 * @return
	 */
	List<BookingGuide> selectGuidesByGroupId(Integer groupId);
	List<BookingGuide> selectGuidesByGroupId(Integer groupId,String guideName);
	List<BookingGuide> selectGuidesBySupplierIdAndSupplierType(Integer supplierId,Integer supplierType);

	int updateBygroupId(Integer groupId);
	
	void updateStateUnlock(Integer groupId);
	
	void updateStateLock(Integer groupId);

	int getBookingCountByTime();
	
	/**
	 * 查询带团统计
	 * @param pageBean
	 * @param curBizId
	 * @return
	 */
	PageBean selectBookingGuideListCountListPage(PageBean pageBean,
			Integer curBizId,Set<Integer> set);
	
	Map getBookingGuideTotalCount(PageBean pageBean,
			Integer curBizId,Set<Integer> set);
	
	BookingGuide selectByGuideIdAndGroupId(Integer guideId,Integer groupId);
	/**
	 * 查询带团查询
	 * @param pageBean
	 * @param curBizId
	 * @return
	 */
	PageBean selectBookingGuideListSelectListPage(PageBean pageBean,
			Integer curBizId,Set<Integer> set);
	
	/**
	 * 查询团的导游
	 * @param groupId
	 * @return
	 */
	List<BookingGuide> selectDistinctListByGroupId(Integer groupId);

	void changeShop(Integer groupId, Integer guideId, Integer mguideId,
			Integer id);
}
