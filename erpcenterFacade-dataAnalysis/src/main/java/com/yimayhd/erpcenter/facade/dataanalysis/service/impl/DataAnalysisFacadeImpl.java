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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.CommonBiz;
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
import com.yimayhd.erpcenter.biz.sales.client.service.sales.GroupOrderBiz;
import com.yimayhd.erpcenter.biz.sales.client.service.sales.TourGroupBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.biz.sys.service.SysBizBankAccountBiz;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.basic.po.RegionInfo;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinancePay;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingAirTicket;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopSelect;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingGroup;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.PaymentExportVO;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.QueryShopInfo;
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
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.OpearteGroupListDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.PaymentStaticPreviewDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.SaleOperatorExcelDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ShopInfoDetailDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ShopSelectListDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToOperatorGroupStaticTableDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToOrdersPreviewDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToSaleOperatorOrderStaticTableDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.query.ToSaleOperatorPreviewDTO;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.AirTicketDetailQueriesResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.AllProvinceResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.BookingSupplierDetailListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.DeliveryDetailListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetAgeListByProductResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetAirTicketDetailResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetLevelNameResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetNumAndOrderResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetOrdersResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetOrgAndUserTreeJsonStrResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.GetPaymentDataResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.HotelQueriesResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.OpearteGroupListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.PaymentStaticPreviewResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.RestaurantQueriesResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.SaleOperatorExcelResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ShopDetailListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ShopInfoDetailResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ShopSelectListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToOperatorGroupStaticTableResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToOrdersPreviewResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToPaymentPreviewResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToSaleOperatorListResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToSaleOperatorOrderStaticTableResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToSaleOperatorPreviewResult;
import com.yimayhd.erpcenter.facade.dataanalysis.client.result.ToSaleOperatorTableResult;
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
	private CommonBiz getCommonService(String svc) {
		if (StringUtils.isBlank(svc)) {
			svc = "commonsaleBiz";
		}
		return appContext.getBean(svc, CommonBiz.class);
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
}
