package com.yihg.operation.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.finance.api.FinanceService;
import com.yihg.images.util.DateUtils;
import com.yihg.mybatis.utility.PageBean;
import com.yihg.operation.api.BookingShopDetailService;
import com.yihg.operation.api.BookingShopService;
import com.yihg.operation.dao.BookingGuideMapper;
import com.yihg.operation.dao.BookingShopMapper;
import com.yihg.operation.po.BookingShop;
import com.yihg.operation.po.BookingShopDet;
import com.yihg.operation.po.BookingShopDetail;
import com.yihg.operation.po.BookingShopSelect;
import com.yihg.operation.vo.QueryGuideShop;
import com.yihg.operation.vo.QueryShopInfo;
import com.yihg.sales.dao.GroupOrderMapper;
import com.yihg.sales.dao.TourGroupMapper;
import com.yihg.sales.po.FinanceBill;
import com.yihg.sales.po.FinanceBillDetail;
import com.yihg.sales.po.GroupOrder;
import com.yihg.supplier.constants.Constants;
import com.yihg.tj.dao.TJMapper;
import com.yihg.tj.po.TJShopProject;

public class BookingShopServiceImpl implements BookingShopService{

	@Autowired
	private BookingShopMapper shopMapper;
	@Autowired
	private TJMapper tjMapper;
	@Autowired
	private BookingShopDetailService bookingShopDetailService;
	@Autowired
	private FinanceService financeService;
	@Autowired
	private TourGroupMapper tourGroupMapper;
	@Autowired
	private BookingGuideMapper bookingGuideMapper;
	@Autowired
	private GroupOrderMapper groupOrderMapper;

	
	@Override
	public List<BookingShop> getShopListByGroupId(Integer groupId) {
		
		return shopMapper.getShopListByGroupId(groupId);
	}

	@Override
	public int save(BookingShop shop) {
		int i = 0;
		if(null!=shop.getId()){
			 i = shopMapper.updateByPrimaryKeySelective(shop);
		}else{
			shop.setBookingDate(new Date());
			shop.setCreateTime(System.currentTimeMillis());
			i = shopMapper.insertSelective(shop);
		}
		return shop.getId() ;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
//		BookingShop bookingShop = shopMapper.selectByPrimaryKey(id);
//		TJShopProject tjShopProject = new TJShopProject();
//		tjShopProject.setBookingId(bookingShop.getId());
//		tjShopProject.setSupplierId(bookingShop.getSupplierId());
//		tjShopProject.setSupplierName(bookingShop.getSupplierName());
//		tjShopProject.setGuideId(bookingShop.getGuideId());
//		tjShopProject.setShopDate(DateUtils.parse(bookingShop.getShopDate(), "yyyy-MM-dd"));
//		tjShopProject.setPersonNumFact(bookingShop.getPersonNumFact());
//		tjShopProject.setPersonNum(bookingShop.getPersonNum());
//		tjShopProject.setTotalFace(bookingShop.getTotalFace());
//		tjShopProject.setTotalMoney(bookingShop.getTotalMoney());
		int result = shopMapper.deleteByPrimaryKey(id);
		if(result==1){
			tjMapper.deleteByBookingId(id);
		}
		return result;
	}

	@Override
	public BookingShop selectByPrimaryKey(Integer id) {
		
		return  shopMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer getSelectCountByGruopId(Integer groupId) {
		return shopMapper.getSelectCountByGruopId(groupId);
		
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
		return shopMapper.getBookingCountByTime(curDay, nextDay);
	}

	@Override
	public List<BookingShopDet> getShopListByGroupIdAndShopDate(Integer groupId,
			String shopDate) {
		return shopMapper.getShopListByGroupIdAndShopDate(groupId, shopDate);
	}

	@Override
	public int updateByPrimaryKeySelective(BookingShop record) {
		return shopMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public PageBean getGuideShop(PageBean pageBean,Set<Integer> set) {
		List<QueryGuideShop> result = shopMapper.getGuideShopListPage(pageBean,set);
		pageBean.setResult(result);
		return pageBean;
	}

	@Override
	public BookingShopSelect getShopSelect(Integer groupId) {
		
		return shopMapper.getShopSelect(groupId);
	}

	@Override
	public PageBean getshopInfoDetail(PageBean pageBean) {
		List<QueryShopInfo> result = shopMapper.getshopInfoDetailListPage(pageBean);
		pageBean.setResult(result);
		return pageBean;
	}

	@Override
	public int updatetotalFace(int bId) {
		
		return shopMapper.updatetotalFace(bId);
	}

	@Override
	public PageBean selectShopListPage(PageBean pageBean, Integer bizId) {
		StringBuffer str=new StringBuffer();
		List<BookingShop> result = shopMapper.selectShopListPage(pageBean, bizId);
		
		for (int i=0;i<result.size();i++) {
			List<Map> fDetail = new ArrayList<Map>();
			Map bookingShop = new HashMap<String, Object>();
			bookingShop = (Map) result.get(i);
			
			List<BookingShopDetail> bookingShopDetailList = shopMapper.getShopDetailListByShopId(pageBean, Integer.parseInt(bookingShop.get("supplier_id").toString()));
			for(int j=0;j<bookingShopDetailList.size();j++){
				Map bookingShopDetail = new HashMap<String, Object>();
				bookingShopDetail = (Map) bookingShopDetailList.get(j);
				fDetail.add(bookingShopDetail);
			}
			bookingShop.put("bookingShopDetailList", fDetail);
			bookingShop.put("bookingShopDetailNum", fDetail.size());
		}
		pageBean.setResult(result);
		return pageBean;
	}

	@Override
	public PageBean selectShopVerifyListPage(PageBean pageBean, Integer bizId) {
		StringBuffer str=new StringBuffer();
		List<BookingShop> result = shopMapper.selectShopVerifyListPage(pageBean, bizId);
		
		for (int i=0;i<result.size();i++) {
			List<Map> fDetail = new ArrayList<Map>();
			Map bookingShop = new HashMap<String, Object>();
			bookingShop = (Map) result.get(i);
			
			List<BookingShopDetail> bookingShopDetailList = shopMapper.getShopVerifyDetailListByShopId(pageBean, Integer.parseInt(bookingShop.get("id").toString()),"");
			for(int j=0;j<bookingShopDetailList.size();j++){
				Map bookingShopDetail = new HashMap<String, Object>();
				bookingShopDetail = (Map) bookingShopDetailList.get(j);
				fDetail.add(bookingShopDetail);
			}
			bookingShop.put("bookingShopVerifyDetailList", fDetail);
			bookingShop.put("bookingShopVerifyDetailNum", fDetail.size());
		}
		pageBean.setResult(result);
		return pageBean;
	}
	
	@Override
	public Map<String,Object> selectShopVerifySum(PageBean pageBean, Integer bizId) {
		Map<String,Object> map =new HashMap<String, Object>();
		Integer total_person = shopMapper.selectShopVerifySum(pageBean, bizId);
		map.put("total_person", total_person);
		List<BookingShopDetail> bookingShopDetailList = shopMapper.getShopVerifyDetailListByShopId(pageBean, null,"sum");
		if(bookingShopDetailList.size()>0){
			Map bookingShopDetail = new HashMap<String, Object>();
			bookingShopDetail = (Map) bookingShopDetailList.get(0);
			if(null!=bookingShopDetail){
				map.put("buy_total", bookingShopDetail.get("sum_buy_total"));
				map.put("repay_total", bookingShopDetail.get("sum_repay_total"));
			}
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> getBookingShops(Integer groupId,
			Integer bizId) {
		return shopMapper.getBookingShopList(groupId, bizId);
	}

	@Override
	@Transactional
	public void saveShopAndDetail(String bizCode,Integer userId,String userName,List<BookingShopDetail> bShopDetails,BookingShop shop) {
		//保存购物
		
		List<BookingShop> bookingShops = shopMapper.getShopListByGroupIdAndSupplierId(shop.getGroupId(),shop.getSupplierId(),shop.getGuideId());
		if(bookingShops!=null && bookingShops.size()>0){
			shop.setId(bookingShops.get(0).getId());
		}
		if(null==shop.getId()){
			int No = this.getBookingCountByTime();
			shop.setBookingNo(bizCode+Constants.SHOPPING+new SimpleDateFormat("yyMMdd").format(new Date())+(No+100));
			shop.setUserId(userId);
			shop.setUserName(userName);
			shop.setPersonNumFact(shop.getPersonNum());
		}
		
		Integer id = this.save(shop);
		//保存实际消费
		if(bShopDetails!=null && bShopDetails.size()>0){
			for (BookingShopDetail bShopDetail : bShopDetails) {
				bShopDetail.setType((byte)1);
				bShopDetail.setBookingId(id);
				bookingShopDetailService.save(bShopDetail);
			}
		}
		//整理团金额
		if(shop!=null && shop.getGroupId()!=null){
			financeService.calcTourGroupAmount(shop.getGroupId());
		}
	}

	@Override
	public PageBean selectShopTJListPage(PageBean pageBean) {
		List<Map<String,Object>> result = tourGroupMapper.selectGroupOrderListPage(pageBean);
		
		for (int i=0;i<result.size();i++) {
			Map group = (Map) result.get(i);
			List<Map<String,Object>> guideTj = bookingGuideMapper.selectShopTjByGroupId((Integer)group.get("id"));
			
			for(int j=0;j<guideTj.size();j++){
				Map guide = (Map) guideTj.get(j);
				if(null!=guide && null!=guide.get("guide_id")){
					List<BookingShop> shop = shopMapper.getShopListByGroupIdAndGuideId(pageBean,(Integer)group.get("id"),(Integer)guide.get("guide_id"));
					guide.put("shop", shop);
				}
			}
			group.put("guideTj", guideTj);
			
			List<GroupOrder> orders = groupOrderMapper.selectOrderByGroupId((Integer)group.get("id"));
			StringBuffer str =new StringBuffer();
			for(int j=0;j<orders.size();j++){
				if(j>0){
					str.append("<br/>");
				}
				str.append(j+1+" "+orders.get(j).getReceiveMode()+" "+orders.get(j).getNumAdult()+"+"+orders.get(j).getNumChild());
			}
			group.put("orders", str);
		}
		pageBean.setResult(result);
		return pageBean;
	}

	@Override
	public Integer existBookingShop(Integer supplierId) {
		return shopMapper.existBookingShop(supplierId);
	}


}
