package com.yimayhd.erpcenter.facade.sales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.TypeUtils;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopDetailDeployBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetailDeploy;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingShopDetailDeployVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroupPriceAndPersons;
import com.yimayhd.erpcenter.facade.sales.query.BookingShopDTO;
import com.yimayhd.erpcenter.facade.sales.query.BookingShopDetailDeployDTO;
import com.yimayhd.erpcenter.facade.sales.query.BookingShopListDTO;
import com.yimayhd.erpcenter.facade.sales.result.LoadBookingShopInfoResult;
import com.yimayhd.erpcenter.facade.sales.result.LoadShopInfoResult;
import com.yimayhd.erpcenter.facade.sales.result.ToFactShopResult;
import com.yimayhd.erpcenter.facade.sales.service.BookingShopFacade;

public class BookingShopFacadeImpl implements BookingShopFacade{
	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz;
	@Autowired
	private TourGroupBiz tourGroupBiz;
	@Autowired
	private GroupOrderBiz groupOrderBiz;
	@Autowired
	private BookingShopBiz bookingShopBiz;
	@Autowired
	private BookingShopDetailDeployBiz bookingShopDetailDeployBiz;
	@Autowired
	private BookingShopDetailBiz bookingShopDetailBiz;
	@Autowired
	private BookingGuideBiz bookingGuideBiz;
	
	/**
	 * 客人购物录入查询
	 */
	@Override
	public PageBean bookingShopList(BookingShopListDTO bookingShopListDTO) {
		TourGroup group = bookingShopListDTO.getGroup();
		PageBean pageBean = new PageBean();
		if(group.getPage()==null){
			group.setPage(1);
		}
		if(group.getPageSize()==null){
			pageBean.setPageSize(Constants.PAGESIZE);
		}else{
			pageBean.setPageSize(group.getPageSize());
		}
		if(StringUtils.isBlank(bookingShopListDTO.getSaleOperatorIds()) && StringUtils.isNotBlank(bookingShopListDTO.getOrgIds())){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = bookingShopListDTO.getOrgIds().split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bookingShopListDTO.getBizId(), set);
			String salesOperatorIds="";
			for(Integer usrId : set){
				salesOperatorIds+=usrId+",";
			}
			if(!salesOperatorIds.equals("")){
				group.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length()-1));
			}
		}
		pageBean.setParameter(group);
		pageBean.setPage(group.getPage());
		
		pageBean = tourGroupBiz.selectBookingShopListPage(pageBean, bookingShopListDTO.getBizId(),bookingShopListDTO.getIds());
		return pageBean;
	}

	@Override
	public List<Map<String, Object>> toEditShop(BookingShopDTO bookingShopDTO) {
		int groupId = bookingShopDTO.getGroupId();
		int bizId = bookingShopDTO.getBizId();
		List<Map<String, Object>> bookingGroups = groupOrderBiz.getGroupInfoByGroupId(groupId, bizId);
		if(bookingGroups!=null && bookingGroups.size()>0){
			for(Map<String, Object> map : bookingGroups){				
				Integer orderId =TypeUtils.castToInt(map.get("orderId"));
				List<Map<String, Object>> bookingShops = bookingShopBiz.getBookingShops(groupId, orderId);				
				map.put("deploys", bookingShops);
			}
		}
		return bookingGroups;
	}

	@Override
	public void saveShopDetails(String shopDetails) {
		List<BookingShopDetailDeploy> shopDetails2 = JSON.parseArray(shopDetails, BookingShopDetailDeploy.class);
		BookingShopDetailDeployVO deployVO = new BookingShopDetailDeployVO();
		deployVO.setDetail(shopDetails2);
		bookingShopDetailDeployBiz.updateBookingShopDetailDeploy(deployVO);
	}

	@Override
	public List<BookingShop> shopDetailList(int groupId) {
		return bookingShopBiz.getShopListByGroupId(groupId);
	}

	@Override
	public ToFactShopResult toFactShop(BookingShopDTO bookingShopDTO) {
		ToFactShopResult result = new ToFactShopResult();
		BookingShop shop =bookingShopBiz.selectByPrimaryKey(bookingShopDTO.getShopId());
		//查询分摊
		List<BookingShopDetailDeploy> detailDeploys = selectShopDetail(bookingShopDTO.getShopId(),
				bookingShopDTO.getGroupId());
		result.setDetailDeploys(detailDeploys);
		result.setShop(shop);
		return result;
	}
	
	private List<BookingShopDetailDeploy> selectShopDetail(Integer id,
			Integer groupId) {
		List<BookingShopDetailDeploy> deploys = bookingShopDetailDeployBiz.selectByBookingId(id);
		List<GroupOrder> groupOrders = tourGroupBiz.selectOrderAndGuestInfoByGroupId(groupId);
		List<BookingShopDetailDeploy> detailDeploys = new ArrayList<BookingShopDetailDeploy>();
		for (GroupOrder g : groupOrders) {
			boolean exist = false;
			BookingShopDetailDeploy deploy = new BookingShopDetailDeploy();
			deploy.setOrderId(g.getId());//订单id
			deploy.setOrderNo(g.getOrderNo());//订单号
			deploy.setSupplierName(g.getSupplierName());//组团社
			deploy.setAdultNum(g.getNumAdult());
			deploy.setChildNum(g.getNumChild());
			deploy.setGuestNames(g.getReceiveMode());
			for (int i = 0; i < deploys.size()&& !exist; i++) {
				if(deploys.get(i).getOrderId().equals(g.getId())){
					deploy.setBookingId(id);
					deploy.setBuyTotal(deploys.get(i).getBuyTotal());
					deploy.setRemark(deploys.get(i).getRemark());
					detailDeploys.add(deploy);
					exist = true;
					break;
				}
			}
			if(!exist){
				deploy.setBookingId(id);
				deploy.setRemark(null);
				deploy.setBuyTotal(null);
				
				detailDeploys.add(deploy);
			}
		}
		return detailDeploys;
	}

	@Override
	public int saveDeploy(
			BookingShopDetailDeployDTO bookingShopDetailDeployDTO) {
		return bookingShopDetailDeployBiz.insertSelective(bookingShopDetailDeployDTO.getBookingShopDetailDeployVO());
	}

	@Override
	public int delBookingShop(int bookingId) {
		return bookingShopDetailDeployBiz.deleteByShopId(bookingId);
	}

	@Override
	public LoadBookingShopInfoResult loadBookingShopInfo(int groupId) {
		LoadBookingShopInfoResult result = new LoadBookingShopInfoResult();
		List<BookingShop> shoplist = bookingShopBiz.getShopListByGroupId(groupId);
		TourGroupPriceAndPersons tourGroupInfo = tourGroupBiz.selectTourGroupInfo(groupId);
		result.setBookingShops(shoplist);
		result.setTourGroupPriceAndPersons(tourGroupInfo);
		return result;
	}

	@Override
	public LoadShopInfoResult loadShopInfo(BookingShopDTO bookingShopDTO) {
		LoadShopInfoResult result = new LoadShopInfoResult();
		int groupId = bookingShopDTO.getGroupId();
		int shopId = bookingShopDTO.getShopId();
		if(shopId > 0){
			BookingShop shop = bookingShopBiz.selectByPrimaryKey(shopId);
			result.setBookingShop(shop);
			int count = bookingShopDetailDeployBiz.getCountByShopId(shopId);
			BigDecimal total = bookingShopDetailDeployBiz.getSumBuyTotalByBookingId(shopId);
			List<BookingShopDetail> lists = bookingShopDetailBiz.getShopDetailListByBookingId(shopId);
			if(!((count>0 && (total.compareTo(BigDecimal.ZERO))!=0) || lists.size()>0)){
				result.setIsEdit("edit");
			}
		}else{
			result.setIsEdit("edit");
		}
		//查询导游列表
		List<BookingGuide> guides = bookingGuideBiz.selectGuidesByGroupId(groupId);
		result.setBookingGuides(guides);
		return result;
	}

}
