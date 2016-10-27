package com.yimayhd.erpcenter.facade.sales.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.WebUtils;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderPriceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.dal.sales.client.sales.constants.Constants;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.sales.query.ProfitQueryByTourDTO;
import com.yimayhd.erpcenter.facade.sales.query.ToAddProfitChangeDTO;
import com.yimayhd.erpcenter.facade.sales.query.ToOrderLockTableDTO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;
import com.yimayhd.erpcenter.facade.sales.result.ProfitQueryByTourResult;
import com.yimayhd.erpcenter.facade.sales.result.ToProfitQueryTableResult;
import com.yimayhd.erpcenter.facade.sales.service.GroupProfitFacade;

public class GroupProfitFacadeImpl implements GroupProfitFacade {

	// @Autowired
	// private SupplierGuideService guideService;
	// @Autowired
	// private ProductGroupSupplierService productGroupSupplierService;
	 
	@Autowired
	private TourGroupBiz tourGroupService;
	 
	@Autowired
	private GroupOrderBiz groupOrderService;

	// @Autowired
	// private RegionService regionService;
	//
	
	@Autowired
	private GroupOrderPriceBiz groupOrderPriceService;
	
	//
	// @Autowired
	// private GroupOrderTransportService groupOrderTransportService;
	//
	// @Autowired
	// private GroupOrderGuestService groupOrderGuestService;
	//
	// @Autowired
	// private GroupRequirementService groupRequirementService;
	// @Autowired
	// private ProductGroupService productGroupService;
	// @Autowired
	// private DicService dicService;

	@Autowired
	private PlatformEmployeeBiz platformEmployeeBiz;

	// @Autowired
	// private PlatformOrgService orgService;
	//
	// @Autowired
	// private SupplierService supplierService;
	//
	// @Autowired
	// private GroupRouteService groupRouteService;
	//
	// @Autowired
	// private SysBizBankAccountService bizBankAccountService;
	// @Autowired
	// private CustomerService customerService;
	// @Autowired
	// private TravelHistoryRouteService travelHistoryRouteService;
	// @Autowired
	// private BookingGuideService bookingGuideService;
	// @Autowired
	// private SupplierGuideService supplierGuideService;
	// @Autowired
	// private SupplierDriverService supplierDriverService;
	// @Autowired
	// private BizSettingCommon settingCommon;
	//
	// @Autowired
	// private SupplierImgService imgService;
	// @Autowired
	// private BookingSupplierDetailService bookingSupplierDetailService;
	// @Autowired
	// private BookingSupplierService bookingSupplierService;
	// @Resource
	// private BizSettingCommon bizSettingCommon;
	//
	// @Autowired
	// private BookingShopService bookingShopService;
	//
	// @Autowired
	// private FinanceService financeService;

	@Override
	public ToProfitQueryTableResult toProfitQueryTable(ToOrderLockTableDTO orderLockTableDTO) {

		Integer bizId = orderLockTableDTO.getBizId();
		Set<Integer> userIdSet = orderLockTableDTO.getUserIdSet();
		GroupOrder order = orderLockTableDTO.getOrder();

		// FIXME 下面的这些代码可以重用
		PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>();
		pageBean.setPage(order.getPage());
		pageBean.setPageSize(order.getPageSize() == null ? Constants.PAGESIZE : order.getPageSize());
		pageBean.setParameter(order);

		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(order.getSaleOperatorIds()) && StringUtils.isNotBlank(order.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = order.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				order.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}
		pageBean = groupOrderService.selectProfitByConListPage(pageBean, bizId, userIdSet);
		GroupOrder staticInfo = groupOrderService.selectProfitByCon(pageBean, bizId, userIdSet);
		GroupOrder groupOrder = groupOrderService.selectProfitByConAndMode(pageBean, bizId, userIdSet);

		ToProfitQueryTableResult result = new ToProfitQueryTableResult();
		result.setGroupOrder(groupOrder);
		result.setPageBean(pageBean);
		result.setStaticInfo(staticInfo);

		return result;
	}

	@Override
	public ProfitQueryByTourResult toProfitQueryTableByTour(ProfitQueryByTourDTO profitQueryByTourDTO) {
		
		Integer bizId = profitQueryByTourDTO.getBizId();
		TourGroup tour = profitQueryByTourDTO.getTour();
		Set<Integer> userIdSet = profitQueryByTourDTO.getUserIdSet();
		Integer page = profitQueryByTourDTO.getPage();
		Integer pageSize = profitQueryByTourDTO.getPageSize();
		
		PageBean<TourGroup> pageBean = new PageBean<TourGroup>();
		pageBean.setPage(page);
		if (pageSize == null) {
			pageSize = Constants.PAGESIZE;
		}
		pageBean.setPageSize(pageSize);
		pageBean.setParameter(tour);
		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(tour.getSaleOperatorIds())&& StringUtils.isNotBlank(tour.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = tour.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeBiz.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				tour.setSaleOperatorIds(salesOperatorIds.substring(0,salesOperatorIds.length() - 1));
			}
		}
		pageBean = tourGroupService.selectProfitByTourConListPage(pageBean,bizId,userIdSet);
		
		// 统计成人、小孩、全陪
		PageBean<TourGroup> pb = tourGroupService.selectProfitByTourCon(pageBean,bizId,userIdSet);

		// 总成本、总收入
		TourGroup group = tourGroupService.selectProfitByTourConAndMode(pageBean,bizId,userIdSet);
		if (group == null) {
			group = new TourGroup();
			group.setIncome(new BigDecimal(0));
			group.setTotalBudget(new BigDecimal(0));
		}
		
		ProfitQueryByTourResult result=new ProfitQueryByTourResult();
		result.setPageBean(pageBean);
		result.setPb(pb);
		result.setGroup(group);
		
		return result;
	}
	
	@Override
	public BaseStateResult toAddProfitChange(ToAddProfitChangeDTO profitChangeDTO) {
		
		GroupOrderPrice gop = new GroupOrderPrice() ;
		gop.setOrderId(profitChangeDTO.getId());
		gop.setMode(1);
		gop.setRowState(1);
		gop.setPriceLockState(0);
		gop.setTotalPrice(profitChangeDTO.getPrice().doubleValue());
		gop.setItemName("其他");
		gop.setItemId(153);
		gop.setCreateTime(new Date().getTime());
		gop.setCreatorId(profitChangeDTO.getCreatorId());
		gop.setCreatorName(profitChangeDTO.getCreatorName());
		gop.setUnitPrice(profitChangeDTO.getPrice().doubleValue());
		gop.setNumTimes(new Double(1));
		gop.setNumPerson(new Double(1));
		
		groupOrderPriceService.insertSelective(gop) ;
		
		BaseStateResult result=new BaseStateResult();
		result.setSuccess(true);
		
		return result;
	}
}
