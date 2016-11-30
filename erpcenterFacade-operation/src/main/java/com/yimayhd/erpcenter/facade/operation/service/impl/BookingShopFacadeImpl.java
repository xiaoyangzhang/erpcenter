package com.yimayhd.erpcenter.facade.operation.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.TypeUtils;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopDetailDeployBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
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
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpcenter.facade.operation.errorcode.OperationErrorCode;
import com.yimayhd.erpcenter.facade.operation.query.BookingShopDTO;
import com.yimayhd.erpcenter.facade.operation.query.BookingShopDetailDeployDTO;
import com.yimayhd.erpcenter.facade.operation.query.BookingShopListDTO;
import com.yimayhd.erpcenter.facade.operation.query.ContractPriceExtDTO;
import com.yimayhd.erpcenter.facade.operation.result.BookingShopInputResult;
import com.yimayhd.erpcenter.facade.operation.result.BookingShopResult;
import com.yimayhd.erpcenter.facade.operation.result.GuestShopResult;
import com.yimayhd.erpcenter.facade.operation.result.LoadBookingShopInfoResult;
import com.yimayhd.erpcenter.facade.operation.result.LoadShopInfoResult;
import com.yimayhd.erpcenter.facade.operation.result.ResultSupport;
import com.yimayhd.erpcenter.facade.operation.result.ToAddShopResult;
import com.yimayhd.erpcenter.facade.operation.result.ToFactShopResult;
import com.yimayhd.erpcenter.facade.operation.result.WebResult;
import com.yimayhd.erpcenter.facade.operation.service.BookingShopFacade;
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
	@Autowired
	private FinanceGuideBiz financeGuideBiz;
	@Autowired
	private FinanceBiz financeBiz;
	@Autowired
	private PlatformOrgBiz platformOrgBiz;
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
		List<Map<String, Object>> bookingGroups = groupOrderBiz.getGroupInfoByGroupId( bizId,groupId);
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
		Integer groupId = bookingShopDTO.getGroupId();
		Integer shopId = bookingShopDTO.getShopId();
		if(shopId != null && shopId > 0){
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
	public GuestShopResult guestShopList(PageBean pageBean ,BookingShopListDTO bookingShopListDTO) {
		GuestShopResult result = new GuestShopResult();
		QueryGuideShop shop = bookingShopListDTO.getQueryGuideShop();
		Set<Integer> dataUserIds = bookingShopListDTO.getDataUserIds();
		
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

	@Override
	public PageBean getShopGroupList(PageBean pageBean, TourGroupVO groupVO,
			Set<Integer> set) {
		PageBean page = tourGroupBiz.getShopGroupList(pageBean, groupVO, set);
		return page;
	}

	@Override
	public BookingShopResult groupShopBookingList(Integer groupId) {
		BookingShopResult result = new BookingShopResult();
		List<BookingShop> bookingShops=bookingShopBiz.getShopListByGroupId(groupId);
		result.setBookingShops(bookingShops);
		Boolean checkGroupCanEdit = checkGroupCanEdit(groupId);
		result.setGroupAbleEdit(checkGroupCanEdit);
		return result;
	}

	@Override
	public boolean checkGroupCanEdit(Integer groupId) {
		Boolean checkGroupCanEdit = tourGroupBiz.checkGroupCanEdit(groupId);
		return checkGroupCanEdit;
	}

	@Override
	public ResultSupport saveShopInfo(BookingShop bookingShop, String bizCode) {
		ResultSupport resultSupport = new ResultSupport();
		if(null == bookingShop.getId()){
			int No = bookingShopBiz.getBookingCountByTime();
			bookingShop.setBookingNo(bizCode+Constants.SHOPPING+new SimpleDateFormat("yyMMdd").format(new Date())+(No+100));
		}
		int saveResult = bookingShopBiz.save(bookingShop);
		if(bookingShop.getId()!=null){
			financeBiz.calcTourGroupAmount(bookingShop.getGroupId());
		}
		if (saveResult < 1) {
			resultSupport.setErrorCode(OperationErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}

	@Override
	public WebResult<Map<String, Boolean>> deldetailGuide(Integer bookingShopId) {
		WebResult<Map<String, Boolean>> result = new WebResult<Map<String, Boolean>>();
		int count = bookingShopDetailDeployBiz.getCountByShopId(bookingShopId);
		BigDecimal total = bookingShopDetailDeployBiz.getSumBuyTotalByBookingId(bookingShopId);
		List<BookingShopDetail> lists = bookingShopDetailBiz.getShopDetailListByBookingId(bookingShopId);
		if((count>0 && (total.compareTo(BigDecimal.ZERO))!=0) || lists.size()>0){
			result.setErrorCode(OperationErrorCode.MODIFY_ERROR);
			Map<String, Boolean> map = new HashMap<String, Boolean>();
			map.put("fail", true);
			result.setValue(map);
			return result;
		}else{
			int delResult = bookingShopBiz.deleteByPrimaryKey(bookingShopId);
			if (delResult < 1) {
				result.setErrorCode(OperationErrorCode.MODIFY_ERROR);
			}
		}
		return result;
	}

	@Override
	public BookingShopResult toFactShop(Integer bookingShopId) {
		BookingShopResult result = new BookingShopResult();
		BookingShop shop =bookingShopBiz.selectByPrimaryKey(bookingShopId);
		result.setBookingShop(shop);
		//查询实际消费返款列表
		List<BookingShopDetail> shopDetails =bookingShopDetailBiz.getShopDetailListByBookingId(bookingShopId);
		result.setShopDetails(shopDetails);
		return result;
	}

	@Override
	public BookingShopResult editFactShop(Integer orderId,
			Integer bookingShopDetailId) {
		BookingShopResult result = new BookingShopResult();
		List<BookingShopDetailDeploy> deploys = bookingShopDetailDeployBiz.selectByDetailId(bookingShopDetailId);
		result.setShopDetailDeploys(deploys);
		List<GroupOrder> groupOrders = tourGroupBiz.selectOrderAndGuestInfoByGroupId(orderId);
		result.setGroupOrders(groupOrders);
		return result;
	}

	@Override
	public BookingShopDetail getShopDetailById(Integer bookingShopDetailId) {
		BookingShopDetail shopDetail = bookingShopDetailBiz.getShopDetailById(bookingShopDetailId);
		return shopDetail;
	}

	@Override
	public ResultSupport saveShopDetail(BookingShopDetail shopDetail) {
		ResultSupport resultSupport = new ResultSupport();
		int saveResult = bookingShopDetailBiz.save(shopDetail);
		
		if(shopDetail.getId()!=null){
			financeBiz.calcTourGroupAmount(shopDetail.getBookingId());
		}
		if (saveResult < 1) {
			resultSupport.setErrorCode(OperationErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}

	@Override
	public ResultSupport delShopDetail(Integer bookingShopDetailId,
			Integer groupId) {
		ResultSupport resultSupport = new ResultSupport();
		int delResult = bookingShopDetailBiz.deleteByPrimaryKey(bookingShopDetailId);
		financeBiz.calcTourGroupAmount(groupId);
		if (delResult < 1) {
			resultSupport.setErrorCode(OperationErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}

	@Override
	public ResultSupport saveShopDetail(BookingShopDetailDeployVO vo) {
		ResultSupport resultSupport = new ResultSupport();
		int insertResult = bookingShopDetailDeployBiz.insertSelective(vo);
		if(insertResult < 1) {
			resultSupport.setErrorCode(OperationErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}

	@Override
	public PageBean shopTJList(PageBean pageBean, Integer bizId) {
		BookingShopResult result = new BookingShopResult();
		PageBean page= bookingShopBiz.selectShopTJListPage(pageBean);
		List<Map<String,Object>> lists = pageBean.getResult();
		for (Map<String, Object> map : lists) {
			PlatformOrgPo platformOrgPo = platformOrgBiz.getCompanyByEmployeeId(bizId, (Integer)map.get("operator_id"));
			if(null!=platformOrgPo){
				map.put("company", platformOrgPo.getName());
			}
		}
		return page;
	}

}
