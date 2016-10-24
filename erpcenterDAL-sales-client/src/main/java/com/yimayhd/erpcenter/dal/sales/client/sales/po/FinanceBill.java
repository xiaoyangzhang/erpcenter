package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.yihg.operation.po.BookingGuide;
import com.yihg.operation.po.BookingSupplier;
import com.yihg.operation.po.BookingSupplierDetail;

public class FinanceBill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer bizId;

	private Integer groupMode;

	private String groupCode;

	private Integer groupCodeSort;

	private Integer groupState;

	private Integer prudctBrandId;

	private String productBrandName;

	private Integer productId;

	private String productName;

	private String productDays;

	private Date dateStart;

	private Date dateEnd;

	private Integer daynum;
	private Integer orderNum;

	private BigDecimal totalIncome;
	private BigDecimal totalIncomeCash;
	private BigDecimal totalIncomeShop;
	private BigDecimal totalIncomeShopCash;
	private BigDecimal totalCost;
	private BigDecimal totalCostCash;

	private Integer totalAdult;

	private Integer totalChild;

	private Integer totalGuide;

	private String serviceStandard;

	private String remark;

	private String remarkInternal;

	private String notice;

	private String otherNotice;

	private String warmNotice;

	private Integer operatorId;

	private String operatorName;

	private Long createTime;

	private Date startTime;
	private Date endTime;
	private Integer pageSize=10;
	private Integer page=1;

	private String auditUser;
	private Integer auditUserId;
	private Date auditTime;
	private String saleOperatorIds;
	/**
	 * 申请人-来自finance_receive_order表
	 */
	private String applicant;
	/**
	 * 申请时间-来自finance_receive_order表
	 */
	private Date appli_time;
	/**
	 * 申请状态-来自finance_receive_order表
	 */
	private String appli_state;
	
	private List<FinanceBillDetail> financeBillDetailList;
	/**
	 * 单据商家的个数
	 */
	private Integer financeBillNum;
	
	private Integer groupId;
	
	private Integer guideId;
	
	private Date appliTime;

	private String appliState;
	
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getGuideId() {
		return guideId;
	}

	public void setGuideId(Integer guideId) {
		this.guideId = guideId;
	}
	
	public String getSaleOperatorIds() {
		return saleOperatorIds;
	}

	public void setSaleOperatorIds(String saleOperatorIds) {
		this.saleOperatorIds = saleOperatorIds;
	}

	public String getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	public Integer getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(Integer auditUserId) {
		this.auditUserId = auditUserId;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}

	public Integer getGroupMode() {
		return groupMode;
	}

	public void setGroupMode(Integer groupMode) {
		this.groupMode = groupMode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode == null ? null : groupCode.trim();
	}

	public Integer getGroupCodeSort() {
		return groupCodeSort;
	}

	public void setGroupCodeSort(Integer groupCodeSort) {
		this.groupCodeSort = groupCodeSort;
	}

	public Integer getGroupState() {
		return groupState;
	}

	public void setGroupState(Integer groupState) {
		this.groupState = groupState;
	}

	public Integer getPrudctBrandId() {
		return prudctBrandId;
	}

	public void setPrudctBrandId(Integer prudctBrandId) {
		this.prudctBrandId = prudctBrandId;
	}

	public String getProductBrandName() {
		return productBrandName;
	}

	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName == null ? null : productBrandName.trim();
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getDaynum() {
		return daynum;
	}

	public void setDaynum(Integer daynum) {
		this.daynum = daynum;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public BigDecimal getTotalIncomeCash() {
		return totalIncomeCash;
	}

	public void setTotalIncomeCash(BigDecimal totalIncomeCash) {
		this.totalIncomeCash = totalIncomeCash;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public BigDecimal getTotalCostCash() {
		return totalCostCash;
	}

	public void setTotalCostCash(BigDecimal totalCostCash) {
		this.totalCostCash = totalCostCash;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName == null ? null : productName.trim();
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Integer getTotalAdult() {
		return totalAdult;
	}

	public void setTotalAdult(Integer totalAdult) {
		this.totalAdult = totalAdult;
	}

	public Integer getTotalChild() {
		return totalChild;
	}

	public void setTotalChild(Integer totalChild) {
		this.totalChild = totalChild;
	}

	public Integer getTotalGuide() {
		return totalGuide;
	}

	public void setTotalGuide(Integer totalGuide) {
		this.totalGuide = totalGuide;
	}

	public String getServiceStandard() {
		return serviceStandard;
	}

	public void setServiceStandard(String serviceStandard) {
		this.serviceStandard = serviceStandard == null ? null : serviceStandard.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getRemarkInternal() {
		return remarkInternal;
	}

	public void setRemarkInternal(String remarkInternal) {
		this.remarkInternal = remarkInternal == null ? null : remarkInternal.trim();
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice == null ? null : notice.trim();
	}

	public String getOtherNotice() {
		return otherNotice;
	}

	public void setOtherNotice(String otherNotice) {
		this.otherNotice = otherNotice == null ? null : otherNotice.trim();
	}

	public String getWarmNotice() {
		return warmNotice;
	}

	public void setWarmNotice(String warmNotice) {
		this.warmNotice = warmNotice == null ? null : warmNotice.trim();
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName == null ? null : operatorName.trim();
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getProductDays() {
		return productDays;
	}

	public void setProductDays(String productDays) {
		this.productDays = productDays;
	}

	public BigDecimal getTotalIncomeShop() {
		return totalIncomeShop;
	}

	public void setTotalIncomeShop(BigDecimal totalIncomeShop) {
		this.totalIncomeShop = totalIncomeShop;
	}

	public BigDecimal getTotalIncomeShopCash() {
		return totalIncomeShopCash;
	}

	public void setTotalIncomeShopCash(BigDecimal totalIncomeShopCash) {
		this.totalIncomeShopCash = totalIncomeShopCash;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public Date getAppli_time() {
		return appli_time;
	}

	public void setAppli_time(Date appli_time) {
		this.appli_time = appli_time;
	}

	public String getAppli_state() {
		return appli_state;
	}

	public void setAppli_state(String appli_state) {
		this.appli_state = appli_state;
	}

	public List<FinanceBillDetail> getFinanceBillDetailList() {
		return financeBillDetailList;
	}

	public void setFinanceBillDetailList(
			List<FinanceBillDetail> financeBillDetailList) {
		this.financeBillDetailList = financeBillDetailList;
	}

	public Integer getFinanceBillNum() {
		return financeBillNum;
	}

	public void setFinanceBillNum(Integer financeBillNum) {
		this.financeBillNum = financeBillNum;
	}
	
	public Date getAppliTime() {
		return appliTime;
	}

	public void setAppliTime(Date appliTime) {
		this.appliTime = appliTime;
	}

	public String getAppliState() {
		return appliState;
	}

	public void setAppliState(String appliState) {
		this.appliState = appliState;
	}
}