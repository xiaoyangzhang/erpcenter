package com.yimayhd.erpcenter.facade.sales;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.zookeeper.proto.op_result_t;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.TypeUtils;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopDetailDeployBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetailDeploy;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingGroup;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingShopDetailDeployVO;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.QueryGuideShop;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroupPriceAndPersons;
import com.yimayhd.erpcenter.facade.sales.query.BookingShopDTO;
import com.yimayhd.erpcenter.facade.sales.query.BookingShopDetailDeployDTO;
import com.yimayhd.erpcenter.facade.sales.query.BookingShopListDTO;
import com.yimayhd.erpcenter.facade.sales.query.ContractPriceExtDTO;
import com.yimayhd.erpcenter.facade.sales.result.BookingShopInputResult;
import com.yimayhd.erpcenter.facade.sales.result.GuestShopListResult;
import com.yimayhd.erpcenter.facade.sales.result.GuestShopResult;
import com.yimayhd.erpcenter.facade.sales.result.LoadBookingShopInfoResult;
import com.yimayhd.erpcenter.facade.sales.result.LoadShopInfoResult;
import com.yimayhd.erpcenter.facade.sales.result.ToAddShopResult;
import com.yimayhd.erpcenter.facade.sales.result.ToFactShopResult;
import com.yimayhd.erpcenter.facade.sales.service.BookingShopFacade;
import com.yimayhd.erpresource.biz.service.ContractBiz;
import com.yimayhd.erpresource.biz.service.SupplierItemBiz;
import com.yimayhd.erpresource.dal.po.SupplierContractPriceDateInfo;
import com.yimayhd.erpresource.dal.po.SupplierItem;

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
	@Autowired
	private RegionBiz regionBiz;
	@Autowired
	private DicBiz dicBiz;
	@Autowired
	private BookingSupplierDetailBiz bookingSupplierDetailBiz;
	@Autowired
	private SupplierItemBiz supplierItemBiz;
	@Autowired
	private ContractBiz contractBiz;
	
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
		
		pageBean = tourGroupBiz.selectBookingShopListPage(pageBean, bookingShopListDTO.getBizId(),bookingShopListDTO.getDataUserIds());
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

	@Override
	public GuestShopListResult toGuestShopList(int bizId) {
		GuestShopListResult result = new GuestShopListResult();
		List<RegionInfo> allProvince = regionBiz.getAllProvince();
		result.setAllProvince(allProvince);
		List<DicInfo> sourceTypeList = dicBiz.getListByTypeCode(
				Constants.GUEST_SOURCE_TYPE, bizId);
		result.setSourceTypeList(sourceTypeList);
		return result;
	}

	@Override
	public GuestShopResult guestShopList(BookingShopListDTO bookingShopListDTO) {
		GuestShopResult result = new GuestShopResult();
		QueryGuideShop shop = bookingShopListDTO.getQueryGuideShop();
		int bizId = bookingShopListDTO.getBizId();
		String orgIds = bookingShopListDTO.getOrgIds();
		String saleOperatorIds = bookingShopListDTO.getSaleOperatorIds();
		Set<Integer> dataUserIds = bookingShopListDTO.getDataUserIds();
		PageBean pageBean = new PageBean();
		if (shop.getPage() == null) {
			shop.setPage(1);
		}
		if (shop.getPageSize() == null) {
			pageBean.setPageSize(Constants.PAGESIZE);
		} else {
			pageBean.setPageSize(shop.getPageSize());
		}
		shop.setBizId(bizId);
		if (StringUtils.isBlank(saleOperatorIds)
				&& StringUtils.isNotBlank(orgIds)) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = orgIds.split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(
					bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				shop.setSaleOperatorIds(salesOperatorIds.substring(0,
						salesOperatorIds.length() - 1));
			}
		}
		pageBean.setParameter(shop);
		pageBean.setPage(shop.getPage());
		pageBean = bookingShopBiz.getGuideShop(pageBean,dataUserIds);
		result.setPageBean(pageBean);
		result.setShoppingDataState(shop.getShoppingDataState());
		
		return result;
	}

	@Override
	public BookingShopInputResult bookingShopInputList(
			BookingShopListDTO bookingShopListDTO) {
		BookingShopInputResult result = new BookingShopInputResult();
		TourGroup group = bookingShopListDTO.getGroup();
		int bizId = bookingShopListDTO.getBizId();
		String orgIds = bookingShopListDTO.getOrgIds();
		String saleOperatorIds = bookingShopListDTO.getSaleOperatorIds();
		Set<Integer> dataUserIds = bookingShopListDTO.getDataUserIds();
		
		PageBean pageBean = new PageBean();
		if(group.getPage()==null){
			group.setPage(1);
		}
		if(group.getPageSize()==null){
			pageBean.setPageSize(Constants.PAGESIZE);
		}else{
			pageBean.setPageSize(group.getPageSize());
		}
		if(StringUtils.isBlank(saleOperatorIds) && StringUtils.isNotBlank(orgIds)){
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = orgIds.split(",");
			for(String orgIdStr : orgIdArr){
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
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
		pageBean = tourGroupBiz.selectBookingFinanceShopGroupListPage(pageBean, bizId,dataUserIds);		
		BookingGroup bookingGroup = tourGroupBiz.selectBookingFinanceShopGroupListPageSum(pageBean, bizId,dataUserIds);
		result.setPageBean(pageBean);
		if(null!=bookingGroup){
			result.setBookingGroup(bookingGroup);
		}
		return result;
	}

	@Override
	public ToAddShopResult toAddShop(int groupId) {
		ToAddShopResult result = new ToAddShopResult();
		//查询导游列表
		List<BookingGuide> guides = bookingGuideBiz.selectGuidesByGroupId(groupId);
		result.setGuides(guides);
		List<BookingSupplierDetail> driverList = bookingSupplierDetailBiz.getDriversByGroupIdAndType(groupId, null);
		result.setDriverList(driverList);
		result.setGroupId(groupId);
		TourGroup tourGroup = tourGroupBiz.selectByPrimaryKey(groupId);
		result.setTourGroup(tourGroup);
		return result;
	}

	@Override
	public Map<String, Object> getMatchedDriver(BookingShopDTO bookingShopDTO) {
		BookingGuide driverGuide = bookingGuideBiz.selectByGuideIdAndGroupId(bookingShopDTO.getGuideId(), bookingShopDTO.getGroupId());
		BookingSupplierDetail driver = bookingSupplierDetailBiz.selectByPrimaryKey(driverGuide.getBookingDetailId());
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("driverId", null==driver?"":driver.getDriverId());
		map.put("driverName", null==driver?"":driver.getDriverName());
		return map;
	}

	@Override
	public List<SupplierItem> selectItems(int supplierId) {
		return supplierItemBiz.findSupplierItemBySupplierId(supplierId);
	}

	@Override
	public String contractPriceExt(ContractPriceExtDTO contractPriceExtDTO) {
		Date curDate;
		try {
			curDate = DateUtils.parseDate(contractPriceExtDTO.getDate(), new String[]{"yyyy-MM-dd"});
		} catch (ParseException e) {
			return "[]";
		}
		List<Date> dateList = new ArrayList<Date>();
		dateList.add(curDate);
		List<SupplierContractPriceDateInfo> priceList = contractBiz.getContractPriceByPramas(contractPriceExtDTO.getBizId()
				, contractPriceExtDTO.getSupplierId(),contractPriceExtDTO.getGoodsId(), dateList);
		if(priceList==null){
			return "[]";
		}
		return JSON.toJSONString(priceList);
	}

}
