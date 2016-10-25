package com.yimayhd.erpcenter.biz.sales.service.impl.operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.common.exception.ClientException;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.service.BookingGuideDal;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingGuidesVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.mybatis.utility.PageBean;


public class BookingGuideServiceImpl implements BookingGuideBiz{

	@Autowired
	private BookingGuideDal bookingGuideDal;

	
	
	@Override
	public void deleteByPrimaryKey(Integer id)  throws ClientException {
		
		deleteGuideWithFinanceByPrimaryKey(id);
		//删除导游之前要先判断导游是否报账，如果已报账并且提交到财务，则不能删除。@author guohongfei
		/**
		timesMapper.deleteByBookingId(id);
		return bookingGuideMapper.deleteByPrimaryKey(id);
		**/
	}
	

	@Override
	@Transactional
	public void deleteGuideWithFinanceByPrimaryKey(Integer id) throws ClientException {
		bookingGuideDal.deleteGuideWithFinanceByPrimaryKey(id);
	}
	
	
	@Override
	public int insert(BookingGuide record) {
		return bookingGuideDal.insert(record);
	}


	@Override
	public BookingGuide selectByPrimaryKey(Integer id) {
		return bookingGuideDal.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(BookingGuide record) {
		return bookingGuideDal.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BookingGuide record) {
		return bookingGuideDal.updateByPrimaryKey(record);
	}

	
	@Override
	public BookingGuide selectByGroupId(Integer groupId) {

		return bookingGuideDal.selectByGroupId(groupId);
	}
	@Override
	public int insertSelective(BookingGuidesVO vo) {
		return bookingGuideDal.insertSelective(vo);
	}

	@Override
	public Integer getSelectCountByGruopId(Integer groupId) {
		return bookingGuideDal.getSelectCountByGruopId(groupId);

	}

	@Override
	public List<BookingGuidesVO> selectBookingGuideVoByGroupId(Integer groupId) {
		return bookingGuideDal.selectBookingGuideVoByGroupId(groupId);
	}

	@Override
	public BookingGuidesVO selectBookingGuideVoByGroupIdAndId(Integer id) {
		return bookingGuideDal.selectBookingGuideVoByGroupIdAndId(id);
	}

	@Override
	public List<BookingGuide> selectGuidesByGroupId(Integer groupId) {
		return bookingGuideDal.selectGuidesByGroupId(groupId);
	}

	@Override
	public int updateBygroupId(Integer groupId) {
		return bookingGuideDal.updateBygroupId(groupId);
	}

	@Override
	public void updateStateUnlock(Integer groupId) {
		bookingGuideDal.updateStateUnlock(groupId);
	}

	@Override
	public void updateStateLock(Integer groupId) {
		bookingGuideDal.updateStateLock(groupId);
	}
	
	
	@Override
	public int getBookingCountByTime() {
		return bookingGuideDal.getBookingCountByTime();
	}

	@Override
	public BookingGuide selectByGroupIdAndGuideId(Integer groupId,
			Integer guideId) {
		return bookingGuideDal.selectByGroupIdAndGuideId(groupId, guideId);
	}

	@Override
	public PageBean selectBookingGuideListCountListPage(PageBean pageBean,
			Integer bizId,Set<Integer> set) {
		return bookingGuideDal.selectBookingGuideListCountListPage(
				pageBean, bizId,set);
	}
	

	@Override
	public Map getBookingGuideTotalCount(PageBean pageBean, Integer curBizId,Set<Integer> set) {
		return bookingGuideDal.getBookingGuideTotalCount(pageBean, curBizId, set);
	}
	
	@Override
	public BookingGuide selectByGuideIdAndGroupId(Integer guideId,Integer groupId) {
		return bookingGuideDal.selectByGuideIdAndGroupId(guideId, groupId);
	}

	@Override
	public PageBean selectBookingGuideListSelectListPage(PageBean pageBean,
			Integer bizId,Set<Integer> set) {
		return bookingGuideDal.selectBookingGuideListSelectListPage(pageBean, bizId, set);
	}

	@Override
	public List<BookingGuide> selectGuidesByGroupId(Integer groupId,
			String guideName) {
	return 	bookingGuideDal.selectGuidesByGroupId(groupId, guideName);
	}

	@Override
	public List<BookingGuide> selectGuidesBySupplierIdAndSupplierType(
			Integer supplierId, Integer supplierType) {
		return bookingGuideDal.selectGuidesBySupplierIdAndSupplierType(supplierId, supplierType);
	}

	@Override
	public List<BookingGuide> selectDistinctListByGroupId(Integer groupId) {
		return bookingGuideDal.selectDistinctListByGroupId(groupId);
	}


	@Override
	public List<BookingGuide> selectByGroupId2(Integer groupId) {
		 return bookingGuideDal.selectByGroupId2(groupId);
	}


	@Override
	public void changeShop(Integer groupId, Integer guideId, Integer mguideId, Integer id) {
		BookingGuide bg = bookingGuideDal.selectByPrimaryKey(id);
		
	}

}
