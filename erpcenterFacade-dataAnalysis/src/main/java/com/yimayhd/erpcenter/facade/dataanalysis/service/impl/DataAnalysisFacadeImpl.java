package com.yimayhd.erpcenter.facade.dataanalysis.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.util.TypeUtils;
import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.basic.service.RegionBiz;
import com.yimayhd.erpcenter.biz.product.service.ProductGroupPriceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.finance.FinanceBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingGuideBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingShopDetailDeployBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.operation.BookingSupplierDetailBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.query.QueryBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.CommonSaleBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.biz.sys.service.SysBizBankAccountBiz;
import com.yimayhd.erpcenter.common.util.DateUtils;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingAirTicket;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopSelect;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingGroup;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.GroupBookingInfo;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.PaymentExportVO;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.QueryShopInfo;
import com.yimayhd.erpcenter.dal.sales.client.query.vo.ProductGuestCondition;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.OperatorGroupStatic;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SaleOperatorOrderStatic;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SaleOperatorVo;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.AirTicketDetailQueriesDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.DeliveryDetailListDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetAgeListByProductDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetAirTicketDetailDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetEmployeeIdsDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetNumAndOrderDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.GetPaymentDataDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.HotelDetailPreviewDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.OpearteGroupListDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.PaymentStaticPreviewDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ProductGuestStaticsDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.QueryGroupNumberDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.SaleOperatorExcelDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ShopInfoDetailDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ShopSelectListDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToBookingShopListDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToOperatorGroupStaticTableDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToOrdersPreviewDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToSaleOperatorOrderStaticTableDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToSaleOperatorPreviewDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.AirTicketDetailQueriesResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.AllProvinceResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.BookingSupplierDetailListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.DeliveryDetailListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ExpGroupNumberResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetAgeListByProductResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetAirTicketDetailResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetLevelNameResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetNumAndOrderResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetOrdersResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetOrgAndUserTreeJsonStrResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetPaymentDataResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GroupBookingListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GroupInfoListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.HotelDetailPreviewResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.HotelQueriesResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.OpearteGroupListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.PaymentStaticPreviewResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ProductGuestStaticsResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.QueryGroupNumberResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.RestaurantQueriesResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.SaleOperatorExcelResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ShopDetailListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ShopInfoDetailResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ShopSelectListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToBookingShopListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToOperatorGroupStaticTableResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToOrdersPreviewResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToPaymentPreviewResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToProductListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToSaleOperatorListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToSaleOperatorOrderStaticTableResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToSaleOperatorPreviewResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToSaleOperatorTableResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToSubsidiaryDebtResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.TranportListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.service.DataAnalysisFacade;
import com.yimayhd.erpresource.biz.service.SupplierBiz;
import com.yimayhd.erpresource.dal.constants.BasicConstants;
import com.yimayhd.erpresource.dal.constants.Constants;
import com.yimayhd.erpresource.dal.po.SupplierInfo;

public class DataAnalysisFacadeImpl implements DataAnalysisFacade {

	@Autowired
	private BookingSupplierDetailBiz detailService;

	@Autowired
	private BookingSupplierBiz bookingSupplierService;

	@Autowired
	private DicBiz dicService;

	@Autowired
	private TourGroupBiz tourGroupService;

	@Autowired
	private GroupOrderBiz groupOrderService;

	@Autowired
	private PlatformEmployeeBiz platformEmployeeService;

	@Autowired
	private RegionBiz regionService;

	@Autowired
	private SupplierBiz supplierSerivce;

	@Autowired
	private BookingShopBiz bookingShopService;

	@Autowired
	private BookingShopDetailBiz bookingShopDetailService;

	@Autowired
	private SysBizBankAccountBiz bizBankAccountService;

	@Autowired
	private BookingGuideBiz bGuideService;

	@Autowired
	private QueryBiz queryService;

	@Autowired
	private PlatformOrgBiz orgService;

	@Autowired
	private BookingShopDetailDeployBiz detailDeployService;

	@Autowired
	private FinanceBiz financeService;

	@Autowired
	private ProductGroupPriceBiz groupPriceService;

	@Autowired
	private BookingGuideBiz bookingGuideService;
	
	@Autowired
	private ApplicationContext appContext;

	public GetOrgAndUserTreeJsonStrResult getOrgAndUserTreeJsonStr(Integer bizId) {

		GetOrgAndUserTreeJsonStrResult result = new GetOrgAndUserTreeJsonStrResult();

		result.setOrgJsonStr(orgService.getComponentOrgTreeJsonStr(bizId));
		result.setOrgUserJsonStr(platformEmployeeService.getComponentOrgUserTreeJsonStr(bizId));

		return result;
	}

	public ToSaleOperatorPreviewResult toSaleOperatorPreview(ToSaleOperatorPreviewDTO toSaleOperatorPreviewDTO) {

		SaleOperatorVo order = toSaleOperatorPreviewDTO.getOrder();
		Integer bizId = toSaleOperatorPreviewDTO.getBizId();
		Set<Integer> userIdSet = toSaleOperatorPreviewDTO.getUserIdSet();

		// 酒店星级
		List<DicInfo> jdxjList = dicService.getListByTypeCode(BasicConstants.GYXX_JDXJ);
		// model.addAttribute("jdxjList", jdxjList);

		PageBean<SaleOperatorVo> pageBean = new PageBean<SaleOperatorVo>();
		pageBean.setPage(1);
		pageBean.setPageSize(10000);
		pageBean.setParameter(order);
		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(order.getSaleOperatorIds()) && StringUtils.isNotBlank(order.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = order.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				order.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}
		pageBean = queryService.selectSaleOperatorByConListPage(pageBean, bizId, userIdSet);

		List<SaleOperatorVo> orders = pageBean.getResult();
		for (int i = 0; i < orders.size(); i++) {
			SaleOperatorVo vo = orders.get(i);
			List<GroupOrderGuest> guests = new ArrayList<GroupOrderGuest>();
			if (vo.getGuestNames() != null) {
				GroupOrderGuest guest = null;
				String[] guestsString = vo.getGuestNames().split(",");
				for (String s : guestsString) {
					String[] ss = s.split("@");
					if (ss.length == 2) {
						guest = new GroupOrderGuest();
						guest.setName(ss[0]);
						guest.setCertificateNum(ss[1]);
					} else if (ss.length == 3) {
						guest = new GroupOrderGuest();
						guest.setName(ss[0]);
						guest.setCertificateNum(ss[1]);
						guest.setMobile(ss[2]);
					}
					guests.add(guest);
				}
			}
			vo.setGuests(guests);
		}

		ToSaleOperatorPreviewResult result = new ToSaleOperatorPreviewResult();
		result.setJdxjList(jdxjList);
		result.setOrder(order);
		result.setPageBean(pageBean);

		return result;
	}

	public ToSaleOperatorListResult toSaleOperatorList() {

		// 没有用，查它干嘛？
		// List<RegionInfo> allProvince = regionService.getAllProvince();

		// 酒店星级
		List<DicInfo> jdxjList = dicService.getListByTypeCode(BasicConstants.GYXX_JDXJ);

		ToSaleOperatorListResult result = new ToSaleOperatorListResult();
		result.setJdxjList(jdxjList);
		return result;

	}

	public ToSaleOperatorTableResult toSaleOperatorTable(ToSaleOperatorPreviewDTO toSaleOperatorPreviewDTO) {

		SaleOperatorVo order = toSaleOperatorPreviewDTO.getOrder();
		Integer bizId = toSaleOperatorPreviewDTO.getBizId();
		Set<Integer> userIdSet = toSaleOperatorPreviewDTO.getUserIdSet();

		PageBean<SaleOperatorVo> pageBean = new PageBean<SaleOperatorVo>();
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
			set = platformEmployeeService.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				order.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}
		pageBean = queryService.selectSaleOperatorByConListPage(pageBean, bizId, userIdSet);

		Map<String, Object> sumPerson = queryService.selectSaleOperatorTotalPerson(pageBean, bizId, userIdSet);

		List<SaleOperatorVo> orders = pageBean.getResult();
		for (SaleOperatorVo vo : orders) {
			List<GroupOrderGuest> guests = new ArrayList<GroupOrderGuest>();
			if (vo.getGuestNames() != null) {
				String[] guestsString = vo.getGuestNames().split(",");
				for (String s : guestsString) {
					GroupOrderGuest guest = null;
					String[] ss = s.split("@");
					if (ss.length == 2) {
						guest = new GroupOrderGuest();
						guest.setName(ss[0]);
						guest.setCertificateNum(ss[1]);
					} else if (ss.length == 3) {
						guest = new GroupOrderGuest();
						guest.setName(ss[0]);
						guest.setCertificateNum(ss[1]);
						guest.setMobile(ss[2]);
					}
					guests.add(guest);
				}
			}
			vo.setGuests(guests);
		}

		ToSaleOperatorTableResult result = new ToSaleOperatorTableResult();
		result.setPageBean(pageBean);
		result.setSumPerson(sumPerson);

		return result;
	}

	public GetLevelNameResult getLevelName(String code) {
		GetLevelNameResult result = new GetLevelNameResult();
		List<DicInfo> jdxjList = dicService.getListByTypeCode(BasicConstants.GYXX_JDXJ);
		result.setJdxjList(jdxjList);
		return result;
	}

	// FIXME 代码冗余
	public SaleOperatorExcelResult saleOperatorExcel(SaleOperatorExcelDTO saleOperatorExcelDTO) {

		SaleOperatorVo order = saleOperatorExcelDTO.getOrder();

		PageBean<SaleOperatorVo> pageBean = new PageBean<SaleOperatorVo>();
		pageBean.setPage(1);
		pageBean.setPageSize(10000);
		pageBean.setParameter(order);

		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(order.getSaleOperatorIds()) && StringUtils.isNotBlank(order.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = order.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(saleOperatorExcelDTO.getBizId(), set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				order.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}
		pageBean = queryService.selectSaleOperatorByConListPage(pageBean, saleOperatorExcelDTO.getBizId(),
				saleOperatorExcelDTO.getUserIdSet());

		SaleOperatorExcelResult result = new SaleOperatorExcelResult();
		result.setPageBean(pageBean);
		return result;
	}

	public ToSaleOperatorOrderStaticTableResult toSaleOperatorOrderStaticTable(
			ToSaleOperatorOrderStaticTableDTO toSaleOperatorOrderStaticTableDTO) {

		SaleOperatorOrderStatic soos = toSaleOperatorOrderStaticTableDTO.getSoos();
		Integer page = toSaleOperatorOrderStaticTableDTO.getPage();
		Integer pageSize = toSaleOperatorOrderStaticTableDTO.getPageSize();
		Integer bizId = toSaleOperatorOrderStaticTableDTO.getBizId();
		Set<Integer> userIdSet = toSaleOperatorOrderStaticTableDTO.getUserIdSet();

		PageBean<SaleOperatorOrderStatic> pageBean = new PageBean<SaleOperatorOrderStatic>();

		pageBean.setPage(page);
		pageBean.setPageSize(pageSize == null ? 15 : pageSize);
		pageBean.setParameter(soos);
		if (StringUtils.isBlank(soos.getSaleOperatorIds()) && StringUtils.isNotBlank(soos.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = soos.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				soos.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}
		pageBean = groupOrderService.selectSaleOperatorOrderStaticListPage(pageBean, bizId, userIdSet);
		SaleOperatorOrderStatic sum = groupOrderService.selectSaleOperatorOrderStaticCon(pageBean, bizId, userIdSet);

		ToSaleOperatorOrderStaticTableResult result = new ToSaleOperatorOrderStaticTableResult();
		result.setPageBean(pageBean);
		result.setSum(sum);
		return result;
	}

	public ToOperatorGroupStaticTableResult toOperatorGroupStaticTable(
			ToOperatorGroupStaticTableDTO toOperatorGroupStaticTableDTO) {

		OperatorGroupStatic ogs = toOperatorGroupStaticTableDTO.getOgs();
		Integer page = toOperatorGroupStaticTableDTO.getPage();
		Integer pageSize = toOperatorGroupStaticTableDTO.getPageSize();
		Integer bizId = toOperatorGroupStaticTableDTO.getBizId();
		Set<Integer> userIdSet = toOperatorGroupStaticTableDTO.getUserIdSet();

		PageBean<OperatorGroupStatic> pageBean = new PageBean<OperatorGroupStatic>();

		pageBean.setPage(page);
		pageBean.setPageSize(pageSize == null ? 15 : pageSize);
		pageBean.setParameter(ogs);
		if (StringUtils.isBlank(ogs.getOperatorIds()) && StringUtils.isNotBlank(ogs.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = ogs.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				ogs.setOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}
		pageBean = tourGroupService.selectOperatorGroupStaticListPage(pageBean, bizId, userIdSet);
		OperatorGroupStatic sum = tourGroupService.selectOperatorGroupStaticCon(pageBean, bizId, userIdSet);

		ToOperatorGroupStaticTableResult result = new ToOperatorGroupStaticTableResult();
		result.setPageBean(pageBean);
		result.setSum(sum);

		return result;
	}

	public AllProvinceResult getAllProvince() {

		List<RegionInfo> allProvince = regionService.getAllProvince();

		AllProvinceResult result = new AllProvinceResult();
		result.setAllProvince(allProvince);

		return result;
	}

	public ToOrdersPreviewResult toOrdersPreview(ToOrdersPreviewDTO toOrdersPreviewDTO) {

		PaymentExportVO vo = toOrdersPreviewDTO.getVo();
		Integer bizId = toOrdersPreviewDTO.getBizId();
		Map parameters = toOrdersPreviewDTO.getParameters();

		PageBean<PaymentExportVO> pageBean = new PageBean<PaymentExportVO>();
		if (StringUtils.isBlank(vo.getOperatorIds()) && StringUtils.isNotBlank(vo.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = vo.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				vo.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
				parameters.put("saleOperatorIds", salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}

		pageBean.setParameter(vo);
		List<GroupOrder> orders = groupOrderService.selectPaymentDetailList(pageBean, bizId,
				toOrdersPreviewDTO.getUserIdSet());

		ToOrdersPreviewResult result = new ToOrdersPreviewResult();
		result.setOrders(orders);
		result.setParameters(parameters);

		return result;
	}

	public ToPaymentPreviewResult toPaymentPreview(ToOrdersPreviewDTO toOrdersPreviewDTO) {

		PaymentExportVO vo = toOrdersPreviewDTO.getVo();
		Integer bizId = toOrdersPreviewDTO.getBizId();
		Map parameters = toOrdersPreviewDTO.getParameters();
		Set<Integer> userIdSet = toOrdersPreviewDTO.getUserIdSet();

		PageBean<PaymentExportVO> pageBean = new PageBean<PaymentExportVO>();
		if (StringUtils.isBlank(vo.getOperatorIds()) && StringUtils.isNotBlank(vo.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = vo.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				vo.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
				parameters.put("saleOperatorIds", salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}
		pageBean.setParameter(vo);
		List<GroupOrder> orders = groupOrderService.selectPaymentDetailList(pageBean, bizId, userIdSet);

		/**
		 * 查询当前用户的收款明细
		 */
		List<FinancePay> payDetailList = financeService.getFinancePayBySupplierId(vo.getSupplierId(), bizId);

		List<SysBizBankAccount> sysBizBankAccountList = bizBankAccountService.getListByBizId(bizId);

		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		for (SysBizBankAccount sysBizBankAccount : sysBizBankAccountList) {
			sb.append("类别:" + (sysBizBankAccount.getAccountType() == 1 ? "个人账户" : "对公账户") + " 银行名称："
					+ sysBizBankAccount.getBankName() + sysBizBankAccount.getBankAccount() + " 开户全称："
					+ sysBizBankAccount.getAccountName() + " 公司账号：" + sysBizBankAccount.getAccountNo() + "\n");
		}
		sb1.append("组团社盖章：" + "\n" + "签字：_________" + "\n" + "日期：_________" + "\n");
		sb2.append("组团社盖章：" + "\n" + "签字：_________" + "\n" + "日期：_________" + "\n");

		// 本期发生的应收已收
		GroupOrder orderMiddle = groupOrderService.selectTotalStatic(pageBean, bizId, userIdSet);
		vo.setEndTime(null);
		pageBean.setParameter(vo);
		// 期初余额
		GroupOrder orderPre = groupOrderService.selectTotalStatic(pageBean, bizId, userIdSet);

		ToPaymentPreviewResult result = new ToPaymentPreviewResult();
		result.setOrderMiddle(orderMiddle);
		result.setOrderPre(orderPre);
		result.setOrders(orders);
		result.setParameters(parameters);
		result.setPayDetailList(payDetailList);
		result.setSb1(sb1.toString());
		result.setSb2(sb2.toString());

		return result;
	}

	public GetOrdersResult getOrders(ToOrdersPreviewDTO toOrdersPreviewDTO) {

		PaymentExportVO vo = toOrdersPreviewDTO.getVo();
		Integer bizId = toOrdersPreviewDTO.getBizId();
		Set<Integer> userIdSet = toOrdersPreviewDTO.getUserIdSet();

		PageBean<PaymentExportVO> pageBean = new PageBean<PaymentExportVO>();
		pageBean.setParameter(vo);
		List<GroupOrder> orders = groupOrderService.selectPaymentDetailList(pageBean, bizId, userIdSet);

		GetOrdersResult result = new GetOrdersResult();
		result.setOrders(orders);
		return result;
	}

	public GetPaymentDataResult getPaymentData(GetPaymentDataDTO getPaymentDataDTO) {

		PaymentExportVO vo = getPaymentDataDTO.getVo();
		Integer page = getPaymentDataDTO.getPage();
		Integer pageSize = getPaymentDataDTO.getPageSize();
		Integer bizId = getPaymentDataDTO.getBizId();
		Set<Integer> userIdSet = getPaymentDataDTO.getUserIdSet();

		List<SysBizBankAccount> sysBizBankAccountList = bizBankAccountService.getListByBizId(bizId);

		PageBean<PaymentExportVO> pageBean = new PageBean<PaymentExportVO>();
		pageBean.setParameter(vo);
		List<GroupOrder> orders = groupOrderService.selectPaymentDetailList(pageBean, bizId, userIdSet);

		List<FinancePay> payDetailList = financeService.getFinancePayBySupplierId(vo.getSupplierId(), bizId);

		// 本期发生的应收已收
		GroupOrder orderMiddle = groupOrderService.selectTotalStatic(pageBean, bizId, userIdSet);
		vo.setEndTime(null);
		pageBean.setParameter(vo);

		// 期初余额
		GroupOrder orderPre = groupOrderService.selectTotalStatic(pageBean, bizId, userIdSet);

		GetPaymentDataResult result = new GetPaymentDataResult();
		result.setOrderMiddle(orderMiddle);
		result.setOrderPre(orderPre);
		result.setOrders(orders);
		result.setPayDetailList(payDetailList);
		result.setSysBizBankAccountList(sysBizBankAccountList);

		return result;
	}

	private void download(String path, String fileName, HttpServletRequest request, HttpServletResponse response) {
		try {
			// path是指欲下载的文件的路径。
			File file = new File(path);
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();

			/*
			 * //解决IE浏览器下下载文件名乱码问题 if
			 * (request.getHeader("USER-AGENT").indexOf("msie") > -1){ fileName
			 * = new URLEncoder().encode(fileName) ; }
			 */
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/vnd.ms-excel;charset=gb2312");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
			file.delete();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private List<String> getTotal(List<GroupOrder> orders) {
		DecimalFormat format = new DecimalFormat("#.##");
		List<String> list = new ArrayList<String>();
		double numAdult = 0;
		double numChild = 0;
		double numGuide = 0;
		double total = 0;
		double totalCash = 0;
		double notCash = 0;
		for (GroupOrder order : orders) {
			numAdult += order.getNumAdult() == null ? 0 : order.getNumAdult();
			numChild += order.getNumChild() == null ? 0 : order.getNumChild();
			numGuide += order.getNumGuide() == null ? 0 : order.getNumGuide();
			total += (order.getTotal() == null ? 0 : order.getTotal().doubleValue());
			totalCash += (order.getTotalCash() == null ? 0 : order.getTotalCash().doubleValue());
			notCash += (order.getTotal() == null ? 0 : order.getTotal().doubleValue())
					- (order.getTotalCash() == null ? 0 : order.getTotalCash().doubleValue());
		}
		list.add(format.format(numAdult));
		list.add(format.format(numChild));
		list.add(format.format(numGuide));
		list.add(format.format(total));
		list.add(format.format(totalCash));
		list.add(format.format(notCash));
		return list;
	}

	public ShopInfoDetailResult shopInfoDetail(ShopInfoDetailDTO shopInfoDetailDTO) {

		QueryShopInfo shop = shopInfoDetailDTO.getShop();

		PageBean pageBean = new PageBean();
		if (shop.getPage() == null) {
			shop.setPage(1);
		}
		if (shop.getPageSize() == null) {
			pageBean.setPageSize(Constants.PAGESIZE);
		} else {
			pageBean.setPageSize(shop.getPageSize());
		}

		pageBean.setParameter(shopInfoDetailDTO.getQueryParamters());
		pageBean.setPage(shop.getPage());
		pageBean = bookingShopService.getshopInfoDetail(pageBean);
		if (pageBean.getResult() != null && pageBean.getResult().size() > 0) {
			for (QueryShopInfo qsi : (List<QueryShopInfo>) pageBean.getResult()) {

				List<GroupOrder> gOrders = groupOrderService.selectOrderByGroupId(qsi.getGroupId());
				qsi.setGroupOrders(gOrders);
				// List<BookingSupplierPO> supplierPOs =
				// bookingSupplierService.getBookingSupplierByGroupIdAndSupplierType(bGroup.getGroupId(),
				// supplierType, null, null, null);
				// bGroup.setBookingSuppliers(supplierPOs);

			}
		}

		ShopInfoDetailResult result = new ShopInfoDetailResult();
		result.setPageBean(pageBean);

		return result;
	}

	public ShopDetailListResult shopDetailList(Integer id) {
		List<BookingShopDetail> bookingShopDetail = bookingShopDetailService.getShopDetailListByBookingId(id);

		ShopDetailListResult result = new ShopDetailListResult();
		result.setBookingShopDetail(bookingShopDetail);

		return result;
	}

	public ShopSelectListResult shopSelectList(ShopSelectListDTO shopSelectListDTO) {

		TourGroupVO group = shopSelectListDTO.getGroup();

		PageBean pageBean = new PageBean();
		if (group.getPage() == null) {
			group.setPage(1);
		}
		if (group.getPageSize() == null) {
			pageBean.setPageSize(Constants.PAGESIZE);
		} else {
			pageBean.setPageSize(group.getPageSize());
		}
		group.setSupplierType(Constants.SHOPPING);
		group.setBizId(shopSelectListDTO.getBizId());
		pageBean.setParameter(group);
		pageBean.setPage(group.getPage());

		pageBean = tourGroupService.getGroupInfoList(pageBean, group, shopSelectListDTO.getUserIdSet());
		fillData(pageBean.getResult());
		if (pageBean.getResult() != null && pageBean.getResult().size() > 0) {
			for (BookingGroup bGroup : (List<BookingGroup>) pageBean.getResult()) {

				List<GroupOrder> gOrders = groupOrderService.selectOrderByGroupId(bGroup.getGroupId());
				bGroup.setGroupOrderList(gOrders);
			}
		}

		ShopSelectListResult result = new ShopSelectListResult();
		result.setPageBean(pageBean);

		return result;
	}

	private void fillData(List<BookingGroup> bookingGroupList) {
		if (bookingGroupList != null && bookingGroupList.size() > 0) {
			for (BookingGroup group : bookingGroupList) {
				if (group.getProductBrandName() != null) {
					group.setProductName("【" + group.getProductBrandName() + "】" + group.getProductName());
				}
				// 填充定制团的组团社名称
				if (group.getSupplierId() != null) {
					SupplierInfo supplierInfo = supplierSerivce.selectBySupplierId(group.getSupplierId());
					if (supplierInfo != null) {
						group.setSupplierName(supplierInfo.getNameFull());
					}
				}
				// 购物查询
				BookingShopSelect b = bookingShopService.getShopSelect(group.getGroupId());
				group.setBookingShopSelect(b);

			}
		}
	}

	public String getEmployeeIds(GetEmployeeIdsDTO getEmployeeIdsDTO) {

		String orgIds = getEmployeeIdsDTO.getOrgIds();
		Integer bizId = getEmployeeIdsDTO.getBizId();

		String employeeIds = "";
		Set<Integer> set = new HashSet<Integer>();
		String[] orgIdArr = orgIds.split(",");
		for (String orgIdStr : orgIdArr) {
			set.add(Integer.valueOf(orgIdStr));
		}
		set = platformEmployeeService.getUserIdListByOrgIdList(bizId, set);
		String salesOperatorIds = "";
		for (Integer usrId : set) {
			salesOperatorIds += usrId + ",";
		}
		if (!salesOperatorIds.equals("")) {
			employeeIds = salesOperatorIds.substring(0, salesOperatorIds.length() - 1);
		}

		return employeeIds;
	}

	public PaymentStaticPreviewResult paymentStaticPreview(PaymentStaticPreviewDTO paymentStaticPreviewDTO) {

		GroupOrder order = paymentStaticPreviewDTO.getOrder();

		PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>();

		Map<String, Object> pms = paymentStaticPreviewDTO.getPms();

		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(order.getOperatorIds()) && StringUtils.isNotBlank(order.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = order.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(paymentStaticPreviewDTO.getBizId(), set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				order.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
				pms.put("saleOperatorIds", salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}

		pageBean.setPage(1);
		pageBean.setPageSize(10000);
		pageBean.setParameter(pms);
		// pageBean.setParameter(parameters);
		List<GroupOrder> orders = groupOrderService.selectPaymentStaticData2(pageBean,
				paymentStaticPreviewDTO.getBizId(), paymentStaticPreviewDTO.getUserIdSet());

		PaymentStaticPreviewResult result = new PaymentStaticPreviewResult();
		result.setOrders(orders);
		result.setPms(pms);

		return result;
	}

	public PaymentStaticPreviewResult paymentStaticExport(PaymentStaticPreviewDTO paymentStaticPreviewDTO) {

		GroupOrder groupOrder = paymentStaticPreviewDTO.getOrder();

		PageBean<GroupOrder> pageBean = new PageBean<GroupOrder>();
		pageBean.setPage(1);
		pageBean.setPageSize(10000);

		Map<String, Object> pms = paymentStaticPreviewDTO.getPms();

		// pageBean.setParameter(groupOrder);
		pageBean.setParameter(pms);
		List<GroupOrder> orders = groupOrderService.selectPaymentStaticData2(pageBean,
				paymentStaticPreviewDTO.getBizId(), paymentStaticPreviewDTO.getUserIdSet());

		PaymentStaticPreviewResult result = new PaymentStaticPreviewResult();
		result.setOrders(orders);
		return result;
	}

	public RestaurantQueriesResult restaurantQueries(Integer bizId) {

		// 餐类型
		List<DicInfo> type1 = dicService.getListByTypeCode(Constants.RESTAURANT_TYPE_CODE);
		List<RegionInfo> allProvince = regionService.getAllProvince();

		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		// 获取餐厅类别
		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_RESTAURANT);

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);
		result.setType1(type1);

		return result;
	}

	public RestaurantQueriesResult restaurantBooking(Integer bizId) {

		// 从字典中查询结算方式
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		// 获取类别
		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_RESTAURANT);

		List<RegionInfo> allProvince = regionService.getAllProvince();

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);

		return result;
	}

	public RestaurantQueriesResult restaurantJSFS(Integer bizId) {

		// 从字典中查询结算方式
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		// 获取商家类别
		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_RESTAURANT);

		List<RegionInfo> allProvince = regionService.getAllProvince();

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);

		return result;
	}

	public RestaurantQueriesResult restaurantDetailList(Integer bizId) {

		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_RESTAURANT);
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);
		List<RegionInfo> allProvince = regionService.getAllProvince();

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);

		return result;
	}

	public HotelQueriesResult hotelQueries(Integer bizId) {

		// 酒店类型
		List<DicInfo> hotelType1 = dicService.getListByTypeCode(Constants.HOTEL_TYPE_CODE_1);
		List<RegionInfo> allProvince = regionService.getAllProvince();
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		// 获取酒店类别
		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.GYXX_JDXJ);

		HotelQueriesResult result = new HotelQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);
		result.setHotelType1(hotelType1);

		return result;
	}

	public RestaurantQueriesResult hotelBookingQueries(Integer bizId) {

		// 从字典中查询结算方式
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		// 获取酒店类别
		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.GYXX_JDXJ);

		List<RegionInfo> allProvince = regionService.getAllProvince();

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);

		return result;
	}

	public RestaurantQueriesResult hotelJSFS(Integer bizId) {

		// 从字典中查询结算方式
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		// 获取酒店类别
		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.GYXX_JDXJ);

		List<RegionInfo> allProvince = regionService.getAllProvince();

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);

		return result;
	}

	public RestaurantQueriesResult hoteldetailQueries(Integer bizId) {

		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.GYXX_JDXJ);
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);
		List<RegionInfo> allProvince = regionService.getAllProvince();

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);

		return result;
	}

	public RestaurantQueriesResult fleetQueries(Integer bizId) {

		// 费用项目类型
		List<DicInfo> type1 = dicService.getListByTypeCode(Constants.FLEET_TYPE_CODE);

		List<RegionInfo> allProvince = regionService.getAllProvince();

		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		// 获取类别
		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_FLEET);

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);
		result.setType1(type1);

		return result;
	}

	public RestaurantQueriesResult fleetDetailList(Integer bizId) {

		// 从字典中查询结算方式
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		// 获取类别
		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_FLEET);

		List<RegionInfo> allProvince = regionService.getAllProvince();

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);

		return result;
	}

	public RestaurantQueriesResult fleetJSFSList(Integer bizId) {

		// 从字典中查询结算方式
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		// 获取商家类别
		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_FLEET);

		List<RegionInfo> allProvince = regionService.getAllProvince();

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);

		return result;
	}

	public RestaurantQueriesResult entertainmentDetailQueries(Integer bizId) {

		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setCashTypes(cashTypes);

		return result;
	}

	public RestaurantQueriesResult sightList(Integer bizId) {
		// 类型
		List<DicInfo> type1 = dicService.getListByTypeCode(Constants.SCENICSPOT_TYPE_CODE);

		List<RegionInfo> allProvince = regionService.getAllProvince();

		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		// 获取餐厅类别
		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_SCENICSPOT);

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);
		result.setType1(type1);

		return result;
	}

	public RestaurantQueriesResult sightBookingList(Integer bizId) {

		// 从字典中查询结算方式
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		// 获取类别
		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_SCENICSPOT);

		List<RegionInfo> allProvince = regionService.getAllProvince();

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);

		return result;
	}

	public RestaurantQueriesResult sightJSFS(Integer bizId) {

		// 从字典中查询结算方式
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		// 获取商家类别
		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_SCENICSPOT);

		List<RegionInfo> allProvince = regionService.getAllProvince();

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);

		return result;
	}

	public RestaurantQueriesResult sightDetailList(Integer bizId) {

		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_SCENICSPOT);
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);
		List<RegionInfo> allProvince = regionService.getAllProvince();

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);

		return result;
	}

	public RestaurantQueriesResult airTicketQueries(Integer bizId) {

		// 餐类型
		List<DicInfo> type1 = dicService.getListByTypeCode(Constants.AIRTICKET_TYPE_CODE);

		List<RegionInfo> allProvince = regionService.getAllProvince();

		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		// 获取餐厅类别
		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_AIRTICKETAGENT);

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);
		result.setType1(type1);

		return result;
	}

	public RestaurantQueriesResult airTicketBookingQueries(Integer bizId) {

		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setCashTypes(cashTypes);

		return result;
	}

	public RestaurantQueriesResult airTicketJSFS(Integer bizId) {

		// 从字典中查询结算方式
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		// 获取商家类别
		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_AIRTICKETAGENT);

		List<RegionInfo> allProvince = regionService.getAllProvince();

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);

		return result;
	}

	public AirTicketDetailQueriesResult airTicketDetailQueries(AirTicketDetailQueriesDTO airTicketDetailQueriesDTO) {

		Integer bizId = airTicketDetailQueriesDTO.getBizId();
		Map parameters = airTicketDetailQueriesDTO.getParameters();

		if (null == parameters.get("startTime") && null == parameters.get("endTime")) {
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			c.set(year, month, 1, 0, 0, 0);
			// condition.setStartTime(df.format(c.getTime()));
			parameters.put("startTime", df.format(c.getTime()));
			// c.set(year, month, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			parameters.put("endTime", df.format(c.getTime()));
			// condition.setEndTime(df.format(c.getTime()));

		}

		// condition.setSupplierType(Constants.AIRTICKETAGENT);
		if (StringUtils.isNotBlank((String) parameters.get("orgIds"))) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = ((String) parameters.get("orgIds")).split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			List<PlatformOrgPo> orgList = orgService.getOrgListByIdSet(bizId, set);
			StringBuilder sb = new StringBuilder();
			for (PlatformOrgPo orgPo : orgList) {
				sb.append(orgPo.getName() + ",");
			}
			// condition.setOrgNames(sb.substring(0, sb.length()-1));
			parameters.put("orgNames", sb.substring(0, sb.length() - 1));

		}
		// 如果计调不为null，查询计调名字
		if (StringUtils.isNotBlank((String) parameters.get("saleOperatorIds"))) {
			Set<Integer> set = new HashSet<Integer>();
			String[] userIdArr = ((String) parameters.get("saleOperatorIds")).split(",");
			for (String userIdStr : userIdArr) {
				set.add(Integer.valueOf(userIdStr));
			}
			List<PlatformEmployeePo> empList = platformEmployeeService.getEmpList(bizId, set);
			StringBuilder sb = new StringBuilder();
			for (PlatformEmployeePo employeePo : empList) {
				sb.append(employeePo.getName() + "");
			}
			// condition.setSaleOperatorName(sb.substring(0, sb.length()-1));
			parameters.put("saleOperatorName", sb.substring(0, sb.length() - 1));
		}
		parameters.put("supplierType", Constants.AIRTICKETAGENT);
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		AirTicketDetailQueriesResult result = new AirTicketDetailQueriesResult();
		result.setCashTypes(cashTypes);
		result.setParameters(parameters);

		return result;
	}

	public RestaurantQueriesResult trainTicketQueries(Integer bizId) {

		// 餐类型
		List<DicInfo> type1 = dicService.getListByTypeCode(Constants.TRAINTICKET_TYPE_CODE);

		List<RegionInfo> allProvince = regionService.getAllProvince();

		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setType1(type1);

		return result;
	}

	public RestaurantQueriesResult trainTicketBookingQueries(Integer bizId) {

		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setCashTypes(cashTypes);

		return result;
	}

	public RestaurantQueriesResult trainTicketJSFS(Integer bizId) {

		// 从字典中查询结算方式
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		// 获取商家类别
		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_TRAINTICKETAGENT);

		List<RegionInfo> allProvince = regionService.getAllProvince();

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);

		return result;
	}

	public AirTicketDetailQueriesResult trainTicketDetailQueries(AirTicketDetailQueriesDTO airTicketDetailQueriesDTO) {

		Integer bizId = airTicketDetailQueriesDTO.getBizId();
		Map parameters = airTicketDetailQueriesDTO.getParameters();

		if (null == parameters.get("startTime") && null == parameters.get("endTime")) {
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			c.set(year, month, 1, 0, 0, 0);
			// condition.setStartTime(df.format(c.getTime()));
			parameters.put("startTime", df.format(c.getTime()));
			// c.set(year, month, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			parameters.put("endTime", df.format(c.getTime()));
			// condition.setEndTime(df.format(c.getTime()));

		}
		// condition.setSupplierType(Constants.AIRTICKETAGENT);
		if (StringUtils.isNotBlank((String) parameters.get("orgIds"))) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = ((String) parameters.get("orgIds")).split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			List<PlatformOrgPo> orgList = orgService.getOrgListByIdSet(bizId, set);
			StringBuilder sb = new StringBuilder();
			for (PlatformOrgPo orgPo : orgList) {
				sb.append(orgPo.getName() + ",");
			}
			// condition.setOrgNames(sb.substring(0, sb.length()-1));
			parameters.put("orgNames", sb.substring(0, sb.length() - 1));

		}
		// 如果计调不为null，查询计调名字
		if (StringUtils.isNotBlank((String) parameters.get("saleOperatorIds"))) {
			Set<Integer> set = new HashSet<Integer>();
			String[] userIdArr = ((String) parameters.get("saleOperatorIds")).split(",");
			for (String userIdStr : userIdArr) {
				set.add(Integer.valueOf(userIdStr));
			}
			List<PlatformEmployeePo> empList = platformEmployeeService.getEmpList(bizId, set);
			StringBuilder sb = new StringBuilder();
			for (PlatformEmployeePo employeePo : empList) {
				sb.append(employeePo.getName() + "");
			}
			// condition.setSaleOperatorName(sb.substring(0, sb.length()-1));
			parameters.put("saleOperatorName", sb.substring(0, sb.length() - 1));
		}
		parameters.put("supplierType", Constants.TRAINTICKETAGENT);
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		AirTicketDetailQueriesResult result = new AirTicketDetailQueriesResult();
		result.setCashTypes(cashTypes);
		result.setParameters(parameters);

		return result;
	}

	public RestaurantQueriesResult insuranceQueries(Integer bizId) {

		// 餐类型
		List<DicInfo> type1 = dicService.getListByTypeCode(Constants.INSURANCE_TYPE_CODE);

		List<RegionInfo> allProvince = regionService.getAllProvince();

		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		// 获取餐厅类别
		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_INSURANCE);

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);
		result.setType1(type1);

		return result;
	}

	public RestaurantQueriesResult insuranceDetailQueries(Integer bizId) {

		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_INSURANCE);
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);
		List<RegionInfo> allProvince = regionService.getAllProvince();

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);

		return result;
	}

	public RestaurantQueriesResult insuranceBookingQueries(Integer bizId) {

		// 从字典中查询结算方式
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		// 获取类别
		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_INSURANCE);

		List<RegionInfo> allProvince = regionService.getAllProvince();

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);

		return result;
	}

	public RestaurantQueriesResult insuranceJSFS(Integer bizId) {

		// 从字典中查询结算方式
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		// 获取商家类别
		List<DicInfo> levelList = dicService.getListByTypeCode(BasicConstants.SUPPLIER_LEVEL_INSURANCE);

		List<RegionInfo> allProvince = regionService.getAllProvince();

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setAllProvince(allProvince);
		result.setCashTypes(cashTypes);
		result.setLevelList(levelList);

		return result;
	}

	public RestaurantQueriesResult incomeQueries(Integer bizId) {

		// 类型
		List<DicInfo> type1 = dicService.getListByTypeCode(Constants.OTHER_TYPE_CODE);

		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.QTSR_JSFS, bizId);

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setCashTypes(cashTypes);
		result.setType1(type1);

		return result;
	}

	public RestaurantQueriesResult incomeDetailQueries(Integer bizId){

		// 从字典中查询结算方式
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.QTSR_JSFS, bizId);

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setCashTypes(cashTypes);

		return result;
	}

	public RestaurantQueriesResult outcomeQueries(Integer bizId){

		// 类型
		List<DicInfo> type1 = dicService.getListByTypeCode(Constants.OTHER_TYPE_CODE);
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setCashTypes(cashTypes);
		result.setType1(type1);

		return result;
	}

	public RestaurantQueriesResult outcomeDetailQueries(Integer bizId){
		// 从字典中查询结算方式
		List<DicInfo> cashTypes = dicService.getListByTypeCode(BasicConstants.GYXX_JSFS, bizId);

		RestaurantQueriesResult result = new RestaurantQueriesResult();
		result.setCashTypes(cashTypes);

		return result;
	}

	public DeliveryDetailListResult deliveryDetailList(DeliveryDetailListDTO deliveryDetailListDTO){

		Map paramters = deliveryDetailListDTO.getParamters();
		Integer bizId= deliveryDetailListDTO.getBizId();
		
		if (null == paramters.get("start_min") && null == paramters.get("start_max")) {
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			c.set(year, month, 1, 0, 0, 0);
			// condition.setStartTime(c.getTime()+"");
			paramters.put("start_min", df.format(c.getTime()));
			c.set(year, month, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			paramters.put("start_max", df.format(c.getTime()));
			// condition.setEndTime(c.getTime()+"");

		}
		if (StringUtils.isNotBlank((String) paramters.get("orgIds"))) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = ((String) paramters.get("orgIds")).split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			List<PlatformOrgPo> orgList = orgService.getOrgListByIdSet(bizId, set);
			StringBuilder sb = new StringBuilder();
			for (PlatformOrgPo orgPo : orgList) {
				sb.append(orgPo.getName() + ",");
			}
			// condition.setOrgNames(sb.substring(0, sb.length()-1));
			paramters.put("orgNames", sb.substring(0, sb.length() - 1));

		}
		// 如果计调不为null，查询计调名字
		if (StringUtils.isNotBlank((String) paramters.get("saleOperatorIds"))) {
			Set<Integer> set = new HashSet<Integer>();
			String[] userIdArr = ((String) paramters.get("saleOperatorIds")).split(",");
			for (String userIdStr : userIdArr) {
				set.add(Integer.valueOf(userIdStr));
			}
			List<PlatformEmployeePo> empList = platformEmployeeService.getEmpList(bizId, set);
			StringBuilder sb = new StringBuilder();
			for (PlatformEmployeePo employeePo : empList) {
				sb.append(employeePo.getName() + "");
			}
			// condition.setSaleOperatorName(sb.substring(0, sb.length()-1));
			paramters.put("saleOperatorName", sb.substring(0, sb.length() - 1));
		}
		
		DeliveryDetailListResult result=new DeliveryDetailListResult();
		result.setParamters(paramters);
		return result;
	}

	public GetAirTicketDetailResult getAirTicketDetail(GetAirTicketDetailDTO getAirTicketDetailDTO){
		
		PageBean<BookingAirTicket> pageBean = new PageBean<BookingAirTicket>();
		pageBean.setPage(getAirTicketDetailDTO.getPage());
		pageBean.setPageSize(getAirTicketDetailDTO.getPageSize());
		pageBean.setParameter(getAirTicketDetailDTO.getParameter());

		HashMap<String, BigDecimal> sum = bookingSupplierService.sumAirTicketBooking(pageBean);
		pageBean = bookingSupplierService.selectAirTicketBookingListPage(pageBean);
		
		GetAirTicketDetailResult result=new GetAirTicketDetailResult();
		result.setPageBean(pageBean);
		result.setSum(sum);
		
		return result;
	}

	public GetAirTicketDetailResult getTrainTicketDetail(GetAirTicketDetailDTO getAirTicketDetailDTO){

		PageBean<BookingAirTicket> pageBean = new PageBean<BookingAirTicket>();
		pageBean.setPage(getAirTicketDetailDTO.getPage());
		pageBean.setPageSize(getAirTicketDetailDTO.getPageSize());
		pageBean.setParameter(getAirTicketDetailDTO.getParameter());

		HashMap<String, BigDecimal> sum = bookingSupplierService.sumAirTicketBooking(pageBean);
		pageBean = bookingSupplierService.selectAirTicketBookingListPage(pageBean);
		
		GetAirTicketDetailResult result=new GetAirTicketDetailResult();
		result.setPageBean(pageBean);
		result.setSum(sum);
		
		return result;
	}

	public GetAgeListByProductResult getAgeListByProduct(GetAgeListByProductDTO getAgeListByProductDTO){
		
		List<Map<String, Object>> ageMaps = tourGroupService.selectAgeCount(getAgeListByProductDTO.getQueryParamters());
		
		Map<Object, Object> ageMap = new HashMap<Object, Object>();
		for (Map<String, Object> map2 : ageMaps) {
			ageMap.put(map2.get("ageRanges"), map2.get("agecnt"));
		}
	
		Map personMap = getAgeListByProductDTO.getQueryParamters();
		
		GetAgeListByProductResult result=new GetAgeListByProductResult();
		result.setAgeMaps(ageMaps);
		result.setPersonMap(personMap);
		
		return result;
	}
	
	@Override
	public BookingSupplierDetailListResult getBookingSupplierDetailList(Integer id) {
		List<BookingSupplierDetail> detailList = detailService.selectByPrimaryBookId(id);
		BookingSupplierDetailListResult result=new BookingSupplierDetailListResult();
		result.setDetailList(detailList);
		return result;
	}
	
	//FIXME 下面几个方法重复，但是为了不动逻辑还是单写
	public GetNumAndOrderResult getNumAndOrder(GetNumAndOrderDTO getNumAndOrderDTO){
		
		String sl=getNumAndOrderDTO.getSl();
		String ssl=getNumAndOrderDTO.getSsl();
		Integer page=getNumAndOrderDTO.getPage();
		Integer pageSize=getNumAndOrderDTO.getPageSize();
		String svc=getNumAndOrderDTO.getSvc();
		Integer bizId=getNumAndOrderDTO.getBizId();
		Map paramters=getNumAndOrderDTO.getParamters();
		
		GetNumAndOrderResult result=new GetNumAndOrderResult();
		
		// 如果选择了【省份】作为查询条件
		Map map = new HashMap<String, Object>();
		map.put("provinceId", paramters.get("provinceId"));
		map.put("cityId", paramters.get("cityId"));
		map.put("supplierType", paramters.get("supplierType"));
		map.put("level", paramters.get("level"));
		map.put("bizId", paramters.get("bizId"));
		
		if (paramters.get("provinceId") != null) {
			StringBuilder supplierIds = new StringBuilder();
			
			// map需要包含 procinceId, cityId,upplier三个参数
			List<Map<String, Integer>> supplier_province = supplierSerivce.searchSupplierByArea(map);
			
			if (supplier_province != null && supplier_province.size() > 0) {
				for (Map<String, Integer> item : supplier_province) {
					supplierIds.append(item.get("id") + ",");
				}
			}
			if (supplierIds.length() > 0){
//				request.setAttribute("citysSupplierIds", supplierIds.toString()
//						.substring(0, supplierIds.toString().length() - 1));
				result.setCitysSupplierIds(supplierIds.toString().substring(0, supplierIds.toString().length() - 1));
			}else{
				//request.setAttribute("citysSupplierIds", "0");
				result.setCitysSupplierIds("0");
			}
		}
		
		// 如果选择了【商家类别】作为查询条件
		if (paramters.get("level") != null) {
			StringBuilder supplierIds = new StringBuilder();
			
			List<SupplierInfo> supplier_level = supplierSerivce.findSupplierLevelCode(map);
			
			if (supplier_level != null && supplier_level.size() > 0) {
				Set<Integer> set = new HashSet<Integer>();
				for (SupplierInfo item : supplier_level) {
					supplierIds.append(item.getId() + ",");
				}
			}
			if (supplierIds.length() > 0){
				//request.setAttribute("supplierLevel", supplierIds.toString()
				//		.substring(0, supplierIds.toString().length() - 1));
				result.setSupplierLevel(supplierIds.toString().substring(0, supplierIds.toString().length() - 1));
			}else{
				//request.setAttribute("supplierLevel", "0");
				result.setSupplierLevel("0");
			}
		}

		PageBean pb = commonQuery(result,paramters,bizId,sl, page, pageSize, svc);
		
		// 总计查询
		if (StringUtils.isNotBlank(ssl)) {
			Map<String, Object> pm = (Map<String, Object>) pb.getParameter();
			pm.put("parameter", pm);
			//model.addAttribute("sum", getCommonService(svc).queryOne(ssl, pm));
			result.setSum(getCommonService(svc).queryOne(ssl, pm));
		}
		
		return result;
	}

	public GetNumAndOrderResult getSupplierOrder(GetNumAndOrderDTO getNumAndOrderDTO){

		String sl=getNumAndOrderDTO.getSl();
		String ssl=getNumAndOrderDTO.getSsl();
		Integer page=getNumAndOrderDTO.getPage();
		Integer pageSize=getNumAndOrderDTO.getPageSize();
		String svc=getNumAndOrderDTO.getSvc();
		Integer bizId=getNumAndOrderDTO.getBizId();
		Map paramters=getNumAndOrderDTO.getParamters();
		
		GetNumAndOrderResult result=new GetNumAndOrderResult();
		
		// 如果选择了【省份】作为查询条件
		Map map = new HashMap<String, Object>();
		map.put("provinceId", paramters.get("provinceId"));
		map.put("cityId", paramters.get("cityId"));
		map.put("supplierType", paramters.get("supplierType"));
		map.put("level", paramters.get("level"));
		map.put("bizId", paramters.get("bizId"));
		if (paramters.get("provinceId") != null) {
			StringBuilder supplierIds = new StringBuilder();
			List<Map<String, Integer>> supplier_province = supplierSerivce
					.searchSupplierByArea(map);
			if (supplier_province != null && supplier_province.size() > 0) {
				for (Map<String, Integer> item : supplier_province) {
					supplierIds.append(item.get("id") + ",");
				}
			}
			if (supplierIds.length() > 0){
//				request.setAttribute("citysSupplierIds", supplierIds.toString()
//						.substring(0, supplierIds.toString().length() - 1));
				result.setCitysSupplierIds(supplierIds.toString().substring(0, supplierIds.toString().length() - 1));
			}else{
				//request.setAttribute("citysSupplierIds", "0");
				result.setSupplierLevel("0");
			}
		}
		// 如果选择了【商家类别】作为查询条件
		if (paramters.get("level") != null) {
			StringBuilder supplierIds = new StringBuilder();
			List<SupplierInfo> supplier_level = supplierSerivce
					.findSupplierLevelCode(map);
			if (supplier_level != null && supplier_level.size() > 0) {
				Set<Integer> set = new HashSet<Integer>();
				for (SupplierInfo item : supplier_level) {
					supplierIds.append(item.getId() + ",");
				}
			}
			if (supplierIds.length() > 0){
//				request.setAttribute("supplierLevel", supplierIds.toString()
//						.substring(0, supplierIds.toString().length() - 1));
				result.setSupplierLevel(supplierIds.toString().substring(0, supplierIds.toString().length() - 1));
			}else{
//				request.setAttribute("supplierLevel", "0");
				result.setSupplierLevel("0");
			}
		}

		PageBean pb = commonQuery(result,paramters,bizId,sl, page, pageSize, svc);

		// 总计查询
		if (StringUtils.isNotBlank(ssl)) {
			Map<String, Object> pm = (Map<String, Object>) pb.getParameter();
			pm.put("parameter", pm);
			//model.addAttribute("sum", getCommonService(svc).queryOne(ssl, pm));
			result.setSum(getCommonService(svc).queryOne(ssl, pm));
		}

		return result;
	}

	public GetNumAndOrderResult getSupplierDetails(GetNumAndOrderDTO getNumAndOrderDTO){
		
		String sl=getNumAndOrderDTO.getSl();
		String ssl=getNumAndOrderDTO.getSsl();
		Integer page=getNumAndOrderDTO.getPage();
		Integer pageSize=getNumAndOrderDTO.getPageSize();
		String svc=getNumAndOrderDTO.getSvc();
		Integer bizId=getNumAndOrderDTO.getBizId();
		Map paramters=getNumAndOrderDTO.getParamters();
		
		GetNumAndOrderResult result=new GetNumAndOrderResult();
		
		// 如果选择了【省份】作为查询条件
		Map map = new HashMap<String, Object>();
		map.put("provinceId", paramters.get("provinceId"));
		map.put("cityId", paramters.get("cityId"));
		map.put("supplierType", paramters.get("supplierType"));
		map.put("level", paramters.get("level"));
		map.put("bizId", paramters.get("bizId"));
		if (paramters.get("provinceId") != null) {
			StringBuilder supplierIds = new StringBuilder();
			List<Map<String, Integer>> supplier_province = supplierSerivce
				.searchSupplierByArea(map); // map需要包含 procinceId, cityId,
												// supplier三个参数
			
			if (supplier_province != null && supplier_province.size() > 0) {
				for (Map<String, Integer> item : supplier_province) {
					supplierIds.append(item.get("id") + ",");
				}
			}
			if (supplierIds.length() > 0){
//				request.setAttribute("citysSupplierIds", supplierIds.toString()
//						.substring(0, supplierIds.toString().length() - 1));
				result.setCitysSupplierIds(supplierIds.toString().substring(0, supplierIds.toString().length() - 1));
			}else{
				//request.setAttribute("citysSupplierIds", "0");
				result.setCitysSupplierIds("0");
			}
		}
		// 如果选择了【商家类别】作为查询条件
		if (paramters.get("level") != null) {
			StringBuilder supplierIds = new StringBuilder();
			List<SupplierInfo> supplier_level = supplierSerivce
					.findSupplierLevelCode(map);
			if (supplier_level != null && supplier_level.size() > 0) {
				Set<Integer> set = new HashSet<Integer>();
				for (SupplierInfo item : supplier_level) {
					supplierIds.append(item.getId() + ",");
				}
			}
			if (supplierIds.length() > 0){
//				request.setAttribute("supplierLevel", supplierIds.toString()
//						.substring(0, supplierIds.toString().length() - 1));
				result.setSupplierLevel(supplierIds.toString().substring(0, supplierIds.toString().length() - 1));
			}else{
				//request.setAttribute("supplierLevel", "0");
				result.setSupplierLevel("0");
			}
		}

		@SuppressWarnings("rawtypes")
		PageBean pb = commonQuery(result,paramters,bizId, sl, page, pageSize, svc);

		// 总计查询
		if (StringUtils.isNotBlank(ssl)) {
			Map<String, Object> pm = (Map<String, Object>) pb.getParameter();
			pm.put("parameter", pm);
			//model.addAttribute("sum", getCommonService(svc).queryOne(ssl, pm));
			result.setSum(getCommonService(svc).queryOne(ssl, pm));
		}
		
		return result;
	}

	@RequestMapping("ageQueryList.htm")
	public String ageList(HttpServletRequest request, Model model) {
		return "queries/byAge/queryList";
	}

	// 业务查询公共方法
	private PageBean commonQuery(GetNumAndOrderResult result,
			Map paramters,
			Integer bizId,
			String sl, 
			Integer page, 
			Integer pageSize, 
			String svc) {
		
		PageBean pb = new PageBean();
		
		if (page == null) {
			pb.setPage(1);
		} else {
			pb.setPage(page);
		}
		if (pageSize == null) {
			pageSize = Constants.PAGESIZE;
		}
		
		pb.setPageSize(pageSize);
		//Map paramters = WebUtils.getQueryParamters(request);

		//paramters.put("citysSupplierIds",request.getAttribute("citysSupplierIds"));
		//paramters.put("supplierLevel", request.getAttribute("supplierLevel"));
		paramters.put("citysSupplierIds",result.getCitysSupplierIds());
		paramters.put("supplierLevel",result.getSupplierLevel());
		
		// paramters.put("supplierDetailLevel",
		// request.getAttribute("supplierDetailLevel"));

		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank((String) paramters.get("saleOperatorIds"))
				&& StringUtils.isNotBlank((String) paramters.get("orgIds"))) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = paramters.get("orgIds").toString().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(bizId, set);
			 
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				paramters.put("saleOperatorIds", salesOperatorIds.substring(0,salesOperatorIds.length() - 1));
			}
		}
		pb.setParameter(paramters);
		pb = getCommonService(svc).queryListPage(sl, pb);
		//model.addAttribute("pageBean", pb);
		result.setPb(pb);
		return pb;
	}

	public GetNumAndOrderResult getDetail2(GetNumAndOrderDTO getNumAndOrderDTO){

		String sl=getNumAndOrderDTO.getSl();
		String ssl=getNumAndOrderDTO.getSsl();
		Integer page=getNumAndOrderDTO.getPage();
		Integer pageSize=getNumAndOrderDTO.getPageSize();
		String svc=getNumAndOrderDTO.getSvc();
		Integer bizId=getNumAndOrderDTO.getBizId();
		Map paramters=getNumAndOrderDTO.getParamters();
		
		GetNumAndOrderResult returnResult=new GetNumAndOrderResult();
		
		PageBean pb = commonQuery(returnResult,paramters,bizId,sl, page, pageSize, svc);
		
		List result = pb.getResult();
		if (result != null && result.size() > 0) {
			for (int i = 0; i < result.size(); i++) {
				Map map = (Map) result.get(i);
				
				List<BookingSupplierDetail> detailList = detailService.selectByPrimaryBookId((Integer)map.get("id"));
				map.put("detailList", detailList);
				Long createTime = (Long) map.get("createTime");
				if (createTime != null) {
					map.put("create_Time", new SimpleDateFormat("yyyy-MM-dd").format(new Date(createTime)));
				}
			}
		}

		// 总计查询
		if (StringUtils.isNotBlank(ssl)) {
			Map pm = (Map) pb.getParameter();
			pm.put("parameter", pm);
			//model.addAttribute("sum", getCommonService(svc).queryOne(ssl, pm));
			returnResult.setSum( getCommonService(svc).queryOne(ssl, pm));
		}
		
		return returnResult;
	}

	//FIXME 这里应该是个问题
	/**
	 * 获取查询服务
	 * 
	 * @author Jing.Zhuo
	 * @create 2015年8月18日 上午9:34:25
	 * @param svc
	 * @return
	 */
	private CommonSaleBiz getCommonService(String svc) {
		if (StringUtils.isBlank(svc)) {
			svc = "commonSaleBiz";
		}
		return appContext.getBean(svc, CommonSaleBiz.class);
	}
	
	public TranportListResult tranportList(Integer bizId){
	
		// 交通类型
		List<DicInfo> transportTypeList = dicService.getListByTypeCode(BasicConstants.GYXX_JTFS, bizId);
		
		TranportListResult result=new TranportListResult();
		result.setTransportTypeList(transportTypeList);
		
		return result;
	}

	public OpearteGroupListResult opearteGroupList(OpearteGroupListDTO opearteGroupListDTO){
		
		TourGroupVO tourGroup = opearteGroupListDTO.getTourGroup();
		
		PageBean pageBean = new PageBean();
		if (tourGroup.getPage() == null) {
			tourGroup.setPage(1);
		} else {
			pageBean.setPage(tourGroup.getPage());
		}
		if (tourGroup.getPageSize() == null) {
			pageBean.setPageSize(Constants.PAGESIZE);
		} else {
			pageBean.setPageSize(tourGroup.getPageSize());
		}
		
		if (StringUtils.isBlank(tourGroup.getSaleOperatorIds()) && StringUtils.isNotBlank(tourGroup.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = tourGroup.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(opearteGroupListDTO.getBizId(), set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				tourGroup.setSaleOperatorIds(salesOperatorIds.substring(0, salesOperatorIds.length() - 1));
			}
		}
		tourGroup.setBizId(opearteGroupListDTO.getBizId());
		pageBean.setParameter(tourGroup);
		pageBean = tourGroupService.getGroupOperateList(pageBean, tourGroup,opearteGroupListDTO.getUserIdSet());
		
		OpearteGroupListResult result=new OpearteGroupListResult();
		result.setPageBean(pageBean);
		result.setTourGroup(tourGroup);
		
		return result;
	}
	
	
	public GroupBookingListResult groupBookingList(ShopSelectListDTO shopSelectListDTO){
		
		GroupBookingListResult returnResult=new GroupBookingListResult();
		
		TourGroupVO tourGroup=shopSelectListDTO.getGroup();
		Integer bizId = shopSelectListDTO.getBizId();

		PageBean pageBean = new PageBean();
		
		//model.addAttribute("tourGroup", tourGroup);
		//model.addAttribute("pageNum", tourGroup.getPage());
		returnResult.setTourGroup(tourGroup);
		
		if (tourGroup.getPage() == null) {
			tourGroup.setPage(1);
		} else {
			pageBean.setPage(tourGroup.getPage());
		}
		if (tourGroup.getPageSize() == null) {
			pageBean.setPageSize(Constants.PAGESIZE);
		} else {
			pageBean.setPageSize(tourGroup.getPageSize());
		}

		if (tourGroup != null
				&& StringUtils.isNotEmpty(tourGroup.getReceiveMode())) {
			if (tourGroup.getGroupMode() != null
					&& tourGroup.getGroupMode().intValue() == 0) {
				// 如果为散客，输入接站牌查询为空
				pageBean.setResult(null);
				//model.addAttribute("pageBean", pageBean);
				//return "operation/group-booking-list-table";
				returnResult.setPageBean(pageBean);
				return returnResult;
			}
		}

		if (StringUtils.isBlank(tourGroup.getSaleOperatorIds())
				&& StringUtils.isNotBlank(tourGroup.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = tourGroup.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				tourGroup.setSaleOperatorIds(salesOperatorIds.substring(0,
						salesOperatorIds.length() - 1));
			}
		}
		//Integer bizId = WebUtils.getCurBizId(request);
		tourGroup.setBizId(bizId);
		pageBean.setParameter(tourGroup);

		List<Map<String, Object>> mapList = groupOrderService
				.selectGroupIdsByReceiveMode(tourGroup);
		if (mapList != null && mapList.size() > 0) {
			Set<Integer> groupIdSet = new HashSet<Integer>();
			for (Map<String, Object> map : mapList) {
				groupIdSet.add(TypeUtils.castToInt(map.get("group_id")));
			}
			if (groupIdSet.size() == 0) {
				groupIdSet.add(-1);
			}
			tourGroup.setGroupIdSet(groupIdSet);
		}

		//Set<Integer> set = WebUtils.getDataUserIdSet(request);
		Set<Integer> set=shopSelectListDTO.getUserIdSet();
		
		pageBean = queryService
				.selectGroupBookingListPage(pageBean, bizId, set);
		List result = pageBean.getResult();
		if (result != null && result.size() > 0) {

			GroupBookingInfo bookingInfo = null;
			StringBuilder groupIds = new StringBuilder();
			for (int i = 0; i < result.size(); i++) {
				if (i > 0) {
					groupIds.append(",");
				}
				bookingInfo = (GroupBookingInfo) result.get(i);
				groupIds.append(bookingInfo.getGroupId());
			}

			String groupIdStr = groupIds.toString();

			List<Map<String, Object>> sightList = queryService
					.selectBookingSupplierCountForGroups(groupIdStr,
							Constants.SCENICSPOT, tourGroup.getSupplierName());
			List<Map<String, Object>> hotelList = queryService
					.selectBookingSupplierCountForGroups(groupIdStr,
							Constants.HOTEL, tourGroup.getSupplierName());
			List<Map<String, Object>> eatList = queryService
					.selectBookingSupplierCountForGroups(groupIdStr,
							Constants.RESTAURANT, tourGroup.getSupplierName());
			List<Map<String, Object>> carList = queryService
					.selectBookingSupplierCountForGroups(groupIdStr,
							Constants.FLEET, tourGroup.getSupplierName());
			List<Map<String, Object>> airList = queryService
					.selectBookingSupplierCountForGroups(groupIdStr,
							Constants.AIRTICKETAGENT,
							tourGroup.getSupplierName());
			List<Map<String, Object>> trainList = queryService
					.selectBookingSupplierCountForGroups(groupIdStr,
							Constants.TRAINTICKETAGENT,
							tourGroup.getSupplierName());
			List<Map<String, Object>> insuranceList = queryService
					.selectBookingSupplierCountForGroups(groupIdStr,
							Constants.INSURANCE, tourGroup.getSupplierName());
			List<Map<String, Object>> shopList = queryService
					.selectBookingShopCountForGroups(groupIdStr,
							tourGroup.getSupplierName());
			List<Map<String, Object>> inList = queryService
					.selectBookingSupplierCountForGroups(groupIdStr,
							Constants.OTHERINCOME, tourGroup.getSupplierName());
			List<Map<String, Object>> outList = queryService
					.selectBookingSupplierCountForGroups(groupIdStr,
							Constants.OTHEROUTCOME, tourGroup.getSupplierName());

			List<Map<String, Object>> guideList = queryService
					.selectBookingGuideForGroups(groupIdStr,
							tourGroup.getSupplierName());
			List<Map<String, Object>> deliveryList = queryService
					.selectBookingDeliveryForGroups(groupIdStr,
							tourGroup.getSupplierName());

			for (int i = 0; i < result.size(); i++) {
				bookingInfo = (GroupBookingInfo) result.get(i);
				Integer groupId = bookingInfo.getGroupId();
				bookingInfo.setSightCnt(getCountByGroupId(sightList, groupId));
				bookingInfo.setHotelCnt(getCountByGroupId(hotelList, groupId));
				bookingInfo.setEatCnt(getCountByGroupId(eatList, groupId));
				bookingInfo.setCarCnt(getCountByGroupId(carList, groupId));
				bookingInfo.setAirCnt(getCountByGroupId(airList, groupId));
				bookingInfo.setTrainCnt(getCountByGroupId(trainList, groupId));
				bookingInfo.setInsuranceCnt(getCountByGroupId(insuranceList,
						groupId));
				bookingInfo.setShopCnt(getCountByGroupId(shopList, groupId));
				bookingInfo.setInCnt(getCountByGroupId(inList, groupId));
				bookingInfo.setOutCnt(getCountByGroupId(outList, groupId));
				bookingInfo
						.setGuideNames(getNamesByGroupId(guideList, groupId));
				bookingInfo.setDeliveryNames(getNamesByGroupId(deliveryList,
						groupId));
			}
		}
		//model.addAttribute("pageBean", pageBean);
		//model.addAttribute("sum",queryService.getPersonCount(pageBean, bizId, set));
		returnResult.setPageBean(pageBean);
		returnResult.setSum(queryService.getPersonCount(pageBean, bizId, set));
		
		return returnResult;
	}

	private Integer getCountByGroupId(List<Map<String, Object>> list,
			Integer groupId) {
		if (list == null || list.size() == 0 || groupId == null) {
			return 0;
		}

		Map<String, Object> map = null;
		for (int i = 0; i < list.size(); i++) {
			map = list.get(i);
			Integer mapGroupId = (Integer) map.get("group_id");
			if (mapGroupId == null) {
				continue;
			}
			if (groupId.intValue() == mapGroupId.intValue()) {
				return Integer.parseInt(map.get("count").toString());
			}
		}
		return 0;
	}

	private String getNamesByGroupId(List<Map<String, Object>> list,
			Integer groupId) {
		if (list == null || list.size() == 0 || groupId == null) {
			return null;
		}

		Map<String, Object> map = null;
		for (int i = 0; i < list.size(); i++) {
			map = list.get(i);
			Integer mapGroupId = (Integer) map.get("group_id");
			if (mapGroupId == null) {
				continue;
			}
			if (groupId.intValue() == mapGroupId.intValue()) {
				return map.get("bookSupplierName").toString();
			}
		}
		return null;
	}

	public GroupInfoListResult groupInfoList(ShopSelectListDTO shopSelectListDTO){
		
		TourGroupVO tourGroup = shopSelectListDTO.getGroup();
		Integer bizId=shopSelectListDTO.getBizId();
		
		GroupInfoListResult returnResult=new GroupInfoListResult();
		
		PageBean pageBean = new PageBean();
		//model.addAttribute("tourGroup", tourGroup);
		//model.addAttribute("pageNum", tourGroup.getPage());
		returnResult.setTourGroup(tourGroup);
		
		if (tourGroup.getPage() == null) {
			tourGroup.setPage(1);
		} else {
			pageBean.setPage(tourGroup.getPage());
		}
		if (tourGroup.getPageSize() == null) {
			pageBean.setPageSize(Constants.PAGESIZE);
		} else {
			pageBean.setPageSize(tourGroup.getPageSize());
		}
		if (StringUtils.isBlank(tourGroup.getSaleOperatorIds())
				&& StringUtils.isNotBlank(tourGroup.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = tourGroup.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				tourGroup.setSaleOperatorIds(salesOperatorIds.substring(0,
						salesOperatorIds.length() - 1));
			}
		}
		pageBean.setParameter(tourGroup);
		tourGroup.setBizId(bizId);
		pageBean = tourGroupService.getGroupInfoList(pageBean, tourGroup,shopSelectListDTO.getUserIdSet());
		//model.addAttribute("pageBean", pageBean);
		returnResult.setPageBean(pageBean);
		
		return returnResult;

	}
	
	public ToSubsidiaryDebtResult toSubsidiaryDebt(){
		
		String lj = platformEmployeeService.findByOrgPath("1-3-");
		String lm = platformEmployeeService.findByOrgPath("1-47-");
		String yx = platformEmployeeService.findByOrgPath("1-5-");
		String yp = platformEmployeeService.findByOrgPath("1-4-");
		String llt = platformEmployeeService.findByOrgPath("1-6-");
		
		ToSubsidiaryDebtResult result=new ToSubsidiaryDebtResult();
		result.setLj(lj);
		result.setLlt(llt);
		result.setLm(lm);
		result.setYp(yp);
		result.setYx(yx);
		
		return result;
	}

	public GetNumAndOrderResult toSubsidiaryDebt(GetNumAndOrderDTO getNumAndOrderDTO){
		
		String sl=getNumAndOrderDTO.getSl();
		String ssl=getNumAndOrderDTO.getSsl();
		Integer page=getNumAndOrderDTO.getPage();
		Integer pageSize=getNumAndOrderDTO.getPageSize();
		String svc=getNumAndOrderDTO.getSvc();
		Integer bizId=getNumAndOrderDTO.getBizId();
		TourGroupVO group = getNumAndOrderDTO.getGroup();
		
		GetNumAndOrderResult result=new GetNumAndOrderResult();
		
		PageBean pb = new PageBean();
		pb.setPage(page);
		if (pageSize == null) {
			pageSize = Constants.PAGESIZE;
		}
		pb.setPageSize(pageSize);
		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(group.getSaleOperatorIds())
				&& StringUtils.isNotBlank(group.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = group.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				group.setSaleOperatorIds(salesOperatorIds.substring(0,
						salesOperatorIds.length() - 1));
			}
		}
		Map<String, Object> pms = getNumAndOrderDTO.getParamters();
		if (null != group.getSaleOperatorIds()
				&& !"".equals(group.getSaleOperatorIds())) {
			pms.put("operatorIds", group.getSaleOperatorIds());
		}
		// pms.put("set", WebUtils.getDataUserIdSet(request));
		pb.setParameter(pms);
		pb = getCommonService(svc).queryListPage(sl, pb);
		//model.addAttribute("pageBean", pb);
		result.setPb(pb);
		if (StringUtils.isNotBlank(ssl)) {
			Map pm = (Map) pb.getParameter();
			pm.put("parameter", pm);
			//model.addAttribute("sum", getCommonService(svc).queryOne(ssl, pm));
			result.setSum(getCommonService(svc).queryOne(ssl, pm));
		}
		
		return result;
	}

	public ToProductListResult toProductList(Integer curBizId){

		// 产品品牌下拉列表数据
		List<DicInfo> pp = dicService.getListByTypeCode(BasicConstants.CPXL_PP,curBizId);
		
		ToProductListResult result=new ToProductListResult();
		result.setPp(pp);
		
		return result;
	}

	public ProductGuestStaticsResult productGuestStatics(ProductGuestStaticsDTO productGuestStaticsDTO){
		
		ProductGuestCondition condition=productGuestStaticsDTO.getCondition();
		Integer bizId = productGuestStaticsDTO.getBizId();
		
		
		
		if (StringUtils.isBlank(condition.getOperatorIds())
				&& StringUtils.isNotBlank(condition.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = condition.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				condition.setOperatorIds(salesOperatorIds.substring(0,
						salesOperatorIds.length() - 1));
			}
		}
		String jsonStr=queryProductGuestStatics(productGuestStaticsDTO.getBizId(),
				productGuestStaticsDTO.getUserIdSet(),
				condition);
		
		ProductGuestStaticsResult returnResult=new ProductGuestStaticsResult();
		returnResult.setJsonStr(jsonStr);
		return returnResult;
	}

	private String queryProductGuestStatics(Integer bizId,Set<Integer> userIdSet,
			ProductGuestCondition condition) {
		
		condition.setBizId(bizId);
		
		// if (condition.getStartDate() != null) {
		// condition.setStartDateNum(condition.getStartDate().getTime());
		// }
		// if (condition.getEndDate() != null) {
		// condition.setEndDateNum(condition.getEndDate().getTime());
		// }

		if (condition.getDateType() != null && condition.getDateType() == 1) {
			if (!"".equals(condition.getStartDate())) {
				condition.setStartDateNum(condition.getStartDate().getTime());
			}
			if (!"".equals(condition.getEndDate())) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(condition.getEndDate());
				calendar.add(Calendar.DAY_OF_MONTH, +1);// 让日期加1
				condition.setEndDateNum(calendar.getTime().getTime());
			}
		}

		/*
		 * List<ProductGuestStaticsVo> list =
		 * queryService.productGuestStatics(condition); Integer totalCnt = 0;
		 * if(list!=null&& list.size()>0){ for(ProductGuestStaticsVo vo :list){
		 * totalCnt+=vo.getGuestCnt(); } } model.addAttribute("total",
		 * totalCnt); model.addAttribute("list", list);
		 */
		String jsonStr = queryService.productGuestStatics(condition,userIdSet);
		
		//model.addAttribute("jsonStr", jsonStr);
		return jsonStr;
	}

	public QueryGroupNumberResult queryGroupNumber(QueryGroupNumberDTO queryGroupNumberDTO){
		
		GroupOrder groupOrder=queryGroupNumberDTO.getGroupOrder();
		Integer type=queryGroupNumberDTO.getType();
		Integer dataType=queryGroupNumberDTO.getDataType();
		Integer bizId=queryGroupNumberDTO.getBizId();
		Set<Integer> userIdSet=queryGroupNumberDTO.getUserIdSet();

		// 如果人员为空并且部门不为空，则取部门下的人id
		if (StringUtils.isBlank(groupOrder.getSaleOperatorIds())
				&& StringUtils.isNotBlank(groupOrder.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = groupOrder.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				groupOrder.setSaleOperatorIds(salesOperatorIds.substring(0,
						salesOperatorIds.length() - 1));
			}
		}
		if (type == 0) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.DAY_OF_MONTH, 1);
			groupOrder.setStartTime(new SimpleDateFormat("yyyy-MM-dd")
					.format(cal.getTime()));
			cal.roll(Calendar.DAY_OF_MONTH, -1);
			groupOrder.setEndTime(new SimpleDateFormat("yyyy-MM-dd").format(cal
					.getTime()));
			groupOrder.setShowNum(10);
			dataType = 0;
			groupOrder.setDateType(1);
		}
		if (groupOrder.getDateType() != null && groupOrder.getDateType() == 2) {
			if (groupOrder.getStartTime() != null) {
			//	groupOrder.setStartTime(new SimpleDateFormat("yyyy-MM-dd")
			//			.parse(groupOrder.getStartTime()).getTime() + "");
			}
			if (groupOrder.getEndTime() != null) {
				Calendar calendar = new GregorianCalendar();
			//	calendar.setTime(new SimpleDateFormat("yyyy-MM-dd")
			//			.parse(groupOrder.getEndTime()));
				calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
				groupOrder.setEndTime(calendar.getTime().getTime() + "");
			}

		}
		List<GroupOrder> allGroupOrder = groupOrderService
				.selectGroupNumForQuery(groupOrder,
						bizId,
						userIdSet);

		if (groupOrder.getDateType() != null && groupOrder.getDateType() == 2) {
			if (groupOrder.getStartTime() != null) {
				groupOrder.setStartTime((new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date(
								Long.valueOf(groupOrder.getStartTime())))));
			}
			if (groupOrder.getEndTime() != null) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTimeInMillis(Long.valueOf(groupOrder.getEndTime()));
				calendar.add(calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动
				groupOrder.setEndTime((new SimpleDateFormat("yyyy-MM-dd")
						.format(calendar.getTime())));
			}
		}

		//String jsonStr = queryService.getGroupNumStatics(allGroupOrder,dataType);
				
		QueryGroupNumberResult result=new QueryGroupNumberResult();
		result.setDataType(dataType);
		result.setGroupOrder(groupOrder);
	//	result.setJsonStr(jsonStr);
		
		return result;
	}

	public ExpGroupNumberResult expGroupNumber(QueryGroupNumberDTO queryGroupNumberDTO) throws ParseException{
		
		GroupOrder groupOrder=queryGroupNumberDTO.getGroupOrder();
		Integer bizId = queryGroupNumberDTO.getBizId();
		Set<Integer> userIdSet = queryGroupNumberDTO.getUserIdSet();
				
		if (StringUtils.isBlank(groupOrder.getSaleOperatorIds())
				&& StringUtils.isNotBlank(groupOrder.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = groupOrder.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				groupOrder.setSaleOperatorIds(salesOperatorIds.substring(0,
						salesOperatorIds.length() - 1));
			}
		}
		groupOrder.setShowNum(null);
		if (groupOrder.getDateType() != null && groupOrder.getDateType() == 2) {
			if (groupOrder.getStartTime() != null) {
				groupOrder.setStartTime(new SimpleDateFormat("yyyy-MM-dd")
						.parse(groupOrder.getStartTime()).getTime() + "");
			}
			if (groupOrder.getEndTime() != null) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(new SimpleDateFormat("yyyy-MM-dd")
						.parse(groupOrder.getEndTime()));
				calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
				groupOrder.setEndTime(calendar.getTime().getTime() + "");
			}

		}
		List<GroupOrder> allGroupOrder = groupOrderService
				.selectGroupNumForQuery(groupOrder,bizId,userIdSet);
		
		
		ExpGroupNumberResult result=new ExpGroupNumberResult();
		result.setAllGroupOrder(allGroupOrder);
		return result;
	}

	

	public ProductGuestStaticsResult guestSourceStatics(ProductGuestStaticsDTO productGuestStaticsDTO){
			
		ProductGuestCondition condition=productGuestStaticsDTO.getCondition();
		Integer bizId = productGuestStaticsDTO.getBizId();
		Set<Integer> userIdSet = productGuestStaticsDTO.getUserIdSet();
			
		if (StringUtils.isBlank(condition.getOperatorIds())
				&& StringUtils.isNotBlank(condition.getOrgIds())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orgIdArr = condition.getOrgIds().split(",");
			for (String orgIdStr : orgIdArr) {
				set.add(Integer.valueOf(orgIdStr));
			}
			set = platformEmployeeService.getUserIdListByOrgIdList(bizId, set);
			String salesOperatorIds = "";
			for (Integer usrId : set) {
				salesOperatorIds += usrId + ",";
			}
			if (!salesOperatorIds.equals("")) {
				condition.setOperatorIds(salesOperatorIds.substring(0,
						salesOperatorIds.length() - 1));
			}
		}
		String jsonStr=queryGuestSourceStatics(bizId, userIdSet, condition);
		
		ProductGuestStaticsResult returnResult=new ProductGuestStaticsResult();
		returnResult.setJsonStr(jsonStr);
		return returnResult;
	}

	private String queryGuestSourceStatics(Integer bizId,Set<Integer> userIdSet,ProductGuestCondition condition) {
		
		condition.setBizId(bizId);
		if (condition.getStartDate() != null) {
			condition.setStartDateNum(condition.getStartDate().getTime());
		}
		if (condition.getEndDate() != null) {
			// 添加时间时，结束时间需要加一天
			condition.setEndDateNum(DateUtils.addDay(condition.getEndDate(), 1).getTime());
		}
		return queryService.guestSourceStatics(condition,userIdSet);
	}

	public ToBookingShopListResult toBookingShopList(ToBookingShopListDTO toBookingShopListDTO){
		
		Integer page = toBookingShopListDTO.getPage();
		Integer pageSize = toBookingShopListDTO.getPageSize();
		
		PageBean pageBean = new PageBean();
		if (page == null) {
			pageBean.setPage(1);
		} else {
			pageBean.setPage(page);
		}
		if (pageSize == null) {
			pageBean.setPageSize(Constants.PAGESIZE);
		} else {
			pageBean.setPageSize(pageSize);
		}
		pageBean.setParameter(toBookingShopListDTO.getQueryParamters());
		pageBean = bookingShopService.selectShopListPage(pageBean,
				toBookingShopListDTO.getBizId());
		
		ToBookingShopListResult result=new ToBookingShopListResult();
		result.setPageBean(pageBean);
		
		return result;
	}

	public HotelDetailPreviewResult hotelDetailPreview(HotelDetailPreviewDTO hotelDetailPreviewDTO){
	   
		String sl=hotelDetailPreviewDTO.getSl();
		Integer page=hotelDetailPreviewDTO.getPage();
		Integer pageSize=hotelDetailPreviewDTO.getPageSize();
		String svc=hotelDetailPreviewDTO.getSvc();
		Map paramters = hotelDetailPreviewDTO.getParamters();
		Integer bizId=hotelDetailPreviewDTO.getBizId();
			
		PageBean pb = commonQuery(new GetNumAndOrderResult(),paramters,bizId,sl, page, pageSize, svc);
	
		HotelDetailPreviewResult result=new HotelDetailPreviewResult();
		result.setPb(pb);
		return result;
	}
}
