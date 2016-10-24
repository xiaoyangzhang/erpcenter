package com.yihg.operation.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.basic.exception.ClientException;
import com.yihg.finance.dao.FinanceBillMapper;
import com.yihg.finance.dao.FinanceCommissionMapper;
import com.yihg.finance.dao.FinanceGuideMapper;
import com.yihg.finance.po.FinanceGuide;
import com.yihg.mybatis.utility.PageBean;
import com.yihg.operation.api.BookingGuideService;
import com.yihg.operation.dao.BookingGuideMapper;
import com.yihg.operation.dao.BookingGuideTimesMapper;
import com.yihg.operation.dao.BookingShopMapper;
import com.yihg.operation.dao.BookingSupplierDetailMapper;
import com.yihg.operation.po.BookingGuide;
import com.yihg.operation.po.BookingGuideListCount;
import com.yihg.operation.po.BookingGuideListSelect;
import com.yihg.operation.po.BookingGuideTimes;
import com.yihg.operation.po.BookingShop;
import com.yihg.operation.po.BookingSupplierDetail;
import com.yihg.operation.vo.BookingGuidesVO;
import com.yihg.sales.po.FinanceBill;

public class BookingGuideServiceImpl implements BookingGuideService{

	@Autowired
	private BookingGuideMapper bookingGuideMapper;
	@Autowired
	private BookingGuideTimesMapper timesMapper;
	@Autowired
	private BookingSupplierDetailMapper bookingSupplierDetailMapper;
	@Autowired
	private FinanceCommissionMapper financeCommissionMapper;
	@Autowired
	private FinanceGuideMapper financeGuideMapper;
	@Autowired
	private FinanceBillMapper financeBillMapper;
	
	@Autowired
	private BookingShopMapper bookingShopMapper;
	
	
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
		
		BookingGuide bg = bookingGuideMapper.selectByPrimaryKey(id);
		if(bg==null){
			throw new ClientException("该导游已不存在！");
		}
		
		if(bg.getStateBooking() == (byte)2){
			throw new ClientException("此导游已报账并且提交财务，不能删除！");
		}
		/**
		 * 删除导游之前先判断领单申请和购物信息是否存在，若存在，则不允许删除；若不存在，则可以删除。
		 */
		//根据导游安排张的groupID查询领单申请信息
		FinanceBill fBill = new FinanceBill();
		fBill.setGroupId(bg.getGroupId());
		fBill.setGuideId(bg.getGuideId());
		List<FinanceBill> fBList = financeBillMapper.selectByGroupId(fBill);
		//根据导游安排中的groupID查询购物信息
		BookingShop bs = new BookingShop();
		bs.setGroupId(bg.getGroupId());
		bs.setGuideId(bg.getGuideId());
		List<BookingShop> bSList = bookingShopMapper.selectByGroupId(bs);
		if(fBList != null && !fBList.isEmpty() || bSList != null && !bSList.isEmpty()){
			throw new ClientException("该导游已经存在领单申请或购物记录，删除需将领单转移到其它导游！");
		}	
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		financeGuideMapper.deleteByBookingId(id);
		timesMapper.deleteByBookingId(id);
		bookingGuideMapper.deleteByPrimaryKey(id);
		
		
		
		
		
	}
	
	
	@Override
	public int insert(BookingGuide record) {
		return bookingGuideMapper.insert(record);
	}


	@Override
	public BookingGuide selectByPrimaryKey(Integer id) {
		return bookingGuideMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(BookingGuide record) {
		return bookingGuideMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BookingGuide record) {
		return bookingGuideMapper.updateByPrimaryKey(record);
	}

	
	@Override
	public BookingGuide selectByGroupId(Integer groupId) {
		List<BookingGuide> listByGroupId = bookingGuideMapper.selectListByGroupId(groupId);
		if(listByGroupId != null && listByGroupId.size()>0){
			return listByGroupId.get(0);
		}
		return null;
	}
	@Override
	public int insertSelective(BookingGuidesVO vo) {
		int i = 0;
			BookingGuide guide = vo.getGuide();
			if(guide.getDriverId()==null){
				guide.setDriverName("");
			}
			if(guide.getIsDefault().intValue()==1){//如果是默认导游
				//将其他导游置为非默认
				bookingGuideMapper.updateBygroupId(guide.getGroupId());
			}
			if(guide != null){
				if(null==guide.getId()){
					bookingGuideMapper.insertSelective(guide);
					i = guide.getId();
				}else{
					bookingGuideMapper.updateByPrimaryKeySelective(guide);
					i = guide.getId();
					timesMapper.deleteByBookingId(guide.getId());
				}
				
				List<BookingGuideTimes> guideTimes = vo.getGuideTimes();
				
				if(guideTimes!=null&&!guideTimes.isEmpty()){
					for (BookingGuideTimes bookingGuideTimes : guideTimes) {
					if(StringUtils.isNotBlank(bookingGuideTimes.getTimeEnd())
								||StringUtils.isNotBlank(bookingGuideTimes.getTimeStart())){
							bookingGuideTimes.setBookingId(guide.getId());
							timesMapper.insertSelective(bookingGuideTimes);
						}
					}
				}
			}

		return i;
	}

	@Override
	public Integer getSelectCountByGruopId(Integer groupId) {
		return bookingGuideMapper.getSelectCountByGruopId(groupId);

	}

	@Override
	public List<BookingGuidesVO> selectBookingGuideVoByGroupId(Integer groupId) {
		BookingGuidesVO vo = null;
		List<BookingGuidesVO> voList = new ArrayList<BookingGuidesVO>();
		List<BookingGuide> bList = bookingGuideMapper.selectByGroupId(groupId);
		for (BookingGuide bookingGuide : bList) {
			vo = new BookingGuidesVO();
			BookingSupplierDetail bookingSupplierDetail = bookingSupplierDetailMapper.selectByPrimaryKey(bookingGuide.getBookingDetailId());
			if(bookingSupplierDetail!=null){
				bookingGuide.setDriverName(bookingSupplierDetail.getDriverName()+"-"+bookingSupplierDetail.getDriverTel()+"-"+bookingSupplierDetail.getCarLisence());
			}
		
			List<BookingGuideTimes> tList = timesMapper.selectListByGroupId(bookingGuide.getId());
			vo.setGuide(bookingGuide);
			vo.setGuideTimes(tList);
			voList.add(vo);
			
		}
		
		return voList;
	}

	@Override
	public BookingGuidesVO selectBookingGuideVoByGroupIdAndId(Integer id) {
			BookingGuide guide = bookingGuideMapper.selectByPrimaryKey(id);
			BookingGuidesVO vo = new BookingGuidesVO();
			List<BookingGuideTimes> tList = timesMapper.selectListByGroupId(guide.getId());
			vo.setGuide(guide);
			vo.setGuideTimes(tList);
		return vo;
	}

	@Override
	public List<BookingGuide> selectGuidesByGroupId(Integer groupId) {
		return bookingGuideMapper.selectByGroupId(groupId);
	}

	@Override
	public int updateBygroupId(Integer groupId) {
		
		return bookingGuideMapper.updateBygroupId(groupId);
	}

	@Override
	public void updateStateUnlock(Integer groupId) {
		
		 bookingGuideMapper.updateStateUnlock(groupId);
	}

	@Override
	public void updateStateLock(Integer groupId) {
		
		 bookingGuideMapper.updateStateLock(groupId);
	}
	
	
	@Override
	public int getBookingCountByTime() {
		Date date=new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		//System.out.println(date.getTime());
		Long curDay=date.getTime();
		date.setDate(date.getDate()+1);
		Long nextDay=date.getTime();
		return bookingGuideMapper.getBookingCountByTime(curDay, nextDay);
	}

	@Override
	public BookingGuide selectByGroupIdAndGuideId(Integer groupId,
			Integer guideId) {
		return bookingGuideMapper.selectByGroupIdAndGuideId(groupId, guideId);
	}

	@Override
	public PageBean selectBookingGuideListCountListPage(PageBean pageBean,
			Integer bizId,Set<Integer> set) {
		List<BookingGuideListCount> result = bookingGuideMapper.selectBookingGuideListCountListPage(
				pageBean, bizId,set);
		pageBean.setResult(result);
		return pageBean;
	}
	

	@Override
	public Map getBookingGuideTotalCount(PageBean pageBean, Integer curBizId,Set<Integer> set) {
		return bookingGuideMapper.selectBookingGuideTotalCount(pageBean, curBizId,set);
	}
	
	@Override
	public BookingGuide selectByGuideIdAndGroupId(Integer guideId,Integer groupId) {
		List<BookingGuide> guideList = bookingGuideMapper.selectByGuideIdAndGroupId(guideId,groupId);
		if(guideList!=null && guideList.size()>0){
			return guideList.get(0);
		}
		return null;
	}

	@Override
	public PageBean selectBookingGuideListSelectListPage(PageBean pageBean,
			Integer bizId,Set<Integer> set) {
		List<BookingGuideListSelect> result = bookingGuideMapper.selectBookingGuideListSelectListPage(
				pageBean, bizId,set);
		pageBean.setResult(result);
		return pageBean;
	}

	@Override
	public List<BookingGuide> selectGuidesByGroupId(Integer groupId,
			String guideName) {
	return 	bookingGuideMapper.selectListByGroupIdAndName(groupId, guideName);
	}

	@Override
	public List<BookingGuide> selectGuidesBySupplierIdAndSupplierType(
			Integer supplierId, Integer supplierType) {
		List<BookingGuide> bookingGuides = bookingGuideMapper.selectListBySupplierIdAndSupplierType(supplierId, supplierType);
		return bookingGuides;
	}

	@Override
	public List<BookingGuide> selectDistinctListByGroupId(Integer groupId) {
		List<BookingGuide> bookingGuides = bookingGuideMapper.selectDistinctListByGroupId(groupId);
		return bookingGuides;
	}


	@Override
	public List<BookingGuide> selectByGroupId2(Integer groupId) {
		 return bookingGuideMapper.selectByGroupId(groupId);
	}


	@Override
	public void changeShop(Integer groupId, Integer guideId, Integer mguideId, Integer id) {
		BookingGuide bg = bookingGuideMapper.selectByPrimaryKey(id);
		
	}

}
