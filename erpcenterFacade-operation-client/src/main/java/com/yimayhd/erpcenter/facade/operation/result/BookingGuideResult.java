/**
 * 
 */
package com.yimayhd.erpcenter.facade.operation.result;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceCommission;
import com.yimayhd.erpcenter.dal.sales.client.finance.po.FinanceGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingDelivery;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShop;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;
import com.yimayhd.erpcenter.dal.sales.client.operation.vo.BookingGuidesVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupGuidePrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderTransport;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRequirement;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;
import com.yimayhd.erpresource.dal.po.SupplierGuide;

/**
 * @ClassName: BookingGuideResult
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangxiaoyang
 * @date 2016年10月27日
 */
public class BookingGuideResult extends ResultSupport implements Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	private List<GroupRequirement> groupRequirements;
	private List<BookingGuidesVO> bookingGuidesVOs;
	private TourGroup tourGroup;
	private boolean groupAbleEdit;
	private BookingGuidesVO guidesVO;
	private List<BookingSupplierDetail> supplierDetails;
	private BigDecimal count =  new BigDecimal(0);
	private List<FinanceGuide> financeGuides;
	private List<GroupOrder> orderList;
	private List<BookingSupplier> bookingSuppliers;
	private BookingGuide bookingGuide;
	private PageBean pageBean;
	private Map<Object, Object> map;
	private String driverString;
	private List<GroupOrderTransport> orderTransports;
	private List<GroupOrderGuest> guestGuides;
	private List<GroupOrderGuest> guestLeaders;
	private List<BookingGuide> bookingGuides;
	private String guideString;
	private List<BookingDelivery> bookingDeliveries;
	private String mobile;
	private SupplierGuide supplierGuide;
	private List<GroupRoute> groupRoutes;
	private List<FinanceCommission> financeCommissions;
	private List<FinanceCommission> guideCommDeductions;
	private List<BookingShop> bookingShops;
	private List<GroupGuidePrintPo> groupGuides;
	private List<GroupOrderPrintPo> groupOrderPrints;


	List<FinanceCommission> guideComms;
	List<DicInfo> dicInfoList;
	List<DicInfo> bankList;
	List<DicInfo> payTypeList;
	List<SysBizBankAccount> bizAccountList;


	public List<GroupOrderPrintPo> getGroupOrderPrints() {
		return groupOrderPrints;
	}
	public void setGroupOrderPrints(List<GroupOrderPrintPo> groupOrderPrints) {
		this.groupOrderPrints = groupOrderPrints;
	}
	public List<GroupGuidePrintPo> getGroupGuides() {
		return groupGuides;
	}
	public void setGroupGuides(List<GroupGuidePrintPo> groupGuides) {
		this.groupGuides = groupGuides;
	}
	public List<BookingShop> getBookingShops() {
		return bookingShops;
	}
	public void setBookingShops(List<BookingShop> bookingShops) {
		this.bookingShops = bookingShops;
	}
	public List<FinanceCommission> getGuideCommDeductions() {
		return guideCommDeductions;
	}
	public void setGuideCommDeductions(List<FinanceCommission> guideCommDeductions) {
		this.guideCommDeductions = guideCommDeductions;
	}
	public List<FinanceCommission> getFinanceCommissions() {
		return financeCommissions;
	}
	public void setFinanceCommissions(List<FinanceCommission> financeCommissions) {
		this.financeCommissions = financeCommissions;
	}
	public List<GroupRoute> getGroupRoutes() {
		return groupRoutes;
	}
	public void setGroupRoutes(List<GroupRoute> groupRoutes) {
		this.groupRoutes = groupRoutes;
	}
	public SupplierGuide getSupplierGuide() {
		return supplierGuide;
	}
	public void setSupplierGuide(SupplierGuide supplierGuide) {
		this.supplierGuide = supplierGuide;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public List<BookingDelivery> getBookingDeliveries() {
		return bookingDeliveries;
	}
	public void setBookingDeliveries(List<BookingDelivery> bookingDeliveries) {
		this.bookingDeliveries = bookingDeliveries;
	}
	public String getGuideString() {
		return guideString;
	}
	public void setGuideString(String guideString) {
		this.guideString = guideString;
	}
	public List<BookingGuide> getBookingGuides() {
		return bookingGuides;
	}
	public void setBookingGuides(List<BookingGuide> bookingGuides) {
		this.bookingGuides = bookingGuides;
	}
	public List<GroupOrderGuest> getGuestGuides() {
		return guestGuides;
	}
	public void setGuestGuides(List<GroupOrderGuest> guestGuides) {
		this.guestGuides = guestGuides;
	}
	public List<GroupOrderGuest> getGuestLeaders() {
		return guestLeaders;
	}
	public void setGuestLeaders(List<GroupOrderGuest> guestLeaders) {
		this.guestLeaders = guestLeaders;
	}
	public String getDriverString() {
		return driverString;
	}
	public void setDriverString(String driverString) {
		this.driverString = driverString;
	}
	public List<GroupOrderTransport> getOrderTransports() {
		return orderTransports;
	}
	public void setOrderTransports(List<GroupOrderTransport> orderTransports) {
		this.orderTransports = orderTransports;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public Map<Object, Object> getMap() {
		return map;
	}
	public void setMap(Map<Object, Object> map) {
		this.map = map;
	}
	public BookingGuide getBookingGuide() {
		return bookingGuide;
	}
	public void setBookingGuide(BookingGuide bookingGuide) {
		this.bookingGuide = bookingGuide;
	}
	public List<BookingSupplier> getBookingSuppliers() {
		return bookingSuppliers;
	}
	public void setBookingSuppliers(List<BookingSupplier> bookingSuppliers) {
		this.bookingSuppliers = bookingSuppliers;
	}
	public List<GroupOrder> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<GroupOrder> orderList) {
		this.orderList = orderList;
	}
	public BigDecimal getCount() {
		return count;
	}
	public void setCount(BigDecimal count) {
		this.count = count;
	}
	public List<FinanceGuide> getFinanceGuides() {
		return financeGuides;
	}
	public void setFinanceGuides(List<FinanceGuide> financeGuides) {
		this.financeGuides = financeGuides;
	}
	public BookingGuidesVO getGuidesVO() {
		return guidesVO;
	}
	public void setGuidesVO(BookingGuidesVO guidesVO) {
		this.guidesVO = guidesVO;
	}
	public List<BookingSupplierDetail> getSupplierDetails() {
		return supplierDetails;
	}
	public void setSupplierDetails(List<BookingSupplierDetail> supplierDetails) {
		this.supplierDetails = supplierDetails;
	}
	public boolean isGroupAbleEdit() {
		return groupAbleEdit;
	}
	public void setGroupAbleEdit(boolean groupAbleEdit) {
		this.groupAbleEdit = groupAbleEdit;
	}
	public TourGroup getTourGroup() {
		return tourGroup;
	}
	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}
	public List<GroupRequirement> getGroupRequirements() {
		return groupRequirements;
	}
	public void setGroupRequirements(List<GroupRequirement> groupRequirements) {
		this.groupRequirements = groupRequirements;
	}
	public List<BookingGuidesVO> getBookingGuidesVOs() {
		return bookingGuidesVOs;
	}
	public void setBookingGuidesVOs(List<BookingGuidesVO> bookingGuidesVOs) {
		this.bookingGuidesVOs = bookingGuidesVOs;
	}

	public List<FinanceCommission> getGuideComms() {
		return guideComms;
	}

	public void setGuideComms(List<FinanceCommission> guideComms) {
		this.guideComms = guideComms;
	}

	public List<DicInfo> getDicInfoList() {
		return dicInfoList;
	}

	public void setDicInfoList(List<DicInfo> dicInfoList) {
		this.dicInfoList = dicInfoList;
	}

	public List<DicInfo> getBankList() {
		return bankList;
	}

	public void setBankList(List<DicInfo> bankList) {
		this.bankList = bankList;
	}

	public List<DicInfo> getPayTypeList() {
		return payTypeList;
	}

	public void setPayTypeList(List<DicInfo> payTypeList) {
		this.payTypeList = payTypeList;
	}

	public List<SysBizBankAccount> getBizAccountList() {
		return bizAccountList;
	}

	public void setBizAccountList(List<SysBizBankAccount> bizAccountList) {
		this.bizAccountList = bizAccountList;
	}
}
