package com.yimayhd.erpcenter.dal.sales.client.sales.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yihg.basic.util.LogFieldAnno;
import com.yihg.operation.po.BookingGuide;

public class TourGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@LogFieldAnno(isKey = true)
	private Integer id;

	private Integer bizId;

	private Integer groupMode;
	@LogFieldAnno(description="团号", delOrIns = true)
	private String groupCode;

	private String groupCodeMark;

	private Integer groupCodeSort;
	@LogFieldAnno(description="团状态", needExtDescription = "[{\"key\":\"0\", \"value\":\"未确认\"},{\"key\":\"1\", \"value\":\"已确认\"},{\"key\":\"2\", \"value\":\"作废\"},{\"key\":\"3\", \"value\":\"已审核\"},{\"key\":\"4\", \"value\":\"封存\"}]")
	private Integer groupState;
	private Integer ifEntering;// 是否录入
	private Integer operType;

	private Integer prudctBrandId;
	
	@LogFieldAnno(description="品牌名称")
	private String productBrandName;

	private String productEndTime;

	private Integer productId;
	@LogFieldAnno(description="产品名称")
	private String productName;

	private String productDays;
	@LogFieldAnno(description="出团日期")
	private Date dateStart;
	@LogFieldAnno(description="散团名称")
	private Date dateEnd;
	@LogFieldAnno(description="天数")
	private Integer daynum;
	private Integer orderNum;

	private BigDecimal totalIncome;
	private BigDecimal totalIncomeCash;
	private BigDecimal totalIncomeShop;
	private BigDecimal totalIncomeShopCash;
	private BigDecimal totalCost;
	private BigDecimal totalCostCash;
	@LogFieldAnno(description="成人人数")
	private Integer totalAdult;
	@LogFieldAnno(description="儿童人数")
	private Integer totalChild;
	@LogFieldAnno(description="陪同人数")
	private Integer totalGuide;
	@LogFieldAnno(description="服务标准")
	private String serviceStandard;
	@LogFieldAnno(description="备注")
	private String remark;
	@LogFieldAnno(description="内部备注")
	private String remarkInternal;
	@LogFieldAnno(description="注意事项")
	private String notice;
	@LogFieldAnno(description="其它事项")
	private String otherNotice;
	@LogFieldAnno(description="温馨提示")
	private String warmNotice;

	private Integer operatorId;
	@LogFieldAnno(description="计调")
	private String operatorName;

	private Long createTime;
	private Integer updateId;
	private String updateName;
	private Long updateTime;

	private String createTimeStr;
	private String updateTimeStr;

	private Date startTime;
	private Date endTime;
	private Integer pageSize;
	private Integer page = 1;

	private String auditUser;
	private Integer auditUserId;
	private Date auditTime;
	
	private String saleOperatorIds;
	private String operatorIds;

	private BigDecimal total; // 收入--预算利润查询
	private BigDecimal budget; // 收入--预算利润查询

	private BigDecimal income; // 收入--团所有收入
	private BigDecimal totalBudget; // 收入--团所有成本

	private Integer supplierId; // 组团社ID，定制团时做数据冗余用
	private String supplierName; // 组团社名称，定制团时做数据冗余用
	private String orgNames;

	private String orgIds;

	private Map<Integer, BookingGuide> guideMap; // 导游

	private Date dateType ;
	
	private List<BookingGuide> guideList;
	
	private Boolean containSealedGroup;
	private List<GroupOrder> groupOrderList;
	
	public List<GroupOrder> getGroupOrderList() {
		return groupOrderList;
	}

	public void setGroupOrderList(List<GroupOrder> groupOrderList) {
		this.groupOrderList = groupOrderList;
	}

	public Boolean getContainSealedGroup() {
		return containSealedGroup;
	}

	public void setContainSealedGroup(Boolean containSealedGroup) {
		this.containSealedGroup = containSealedGroup;
	}

	public Date getDateType() {
		return dateType;
	}

	public void setDateType(Date dateType) {
		this.dateType = dateType;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public String getProductEndTime() {
		return productEndTime;
	}

	public void setProductEndTime(String productEndTime) {
		this.productEndTime = productEndTime;
	}

	public BigDecimal getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(BigDecimal totalBudget) {
		this.totalBudget = totalBudget;
	}

	public Map<Integer, BookingGuide> getGuideMap() {
		return guideMap;
	}

	public void setGuideMap(Map<Integer, BookingGuide> guideMap) {
		this.guideMap = guideMap;
	}

	public Integer getIfEntering() {
		return ifEntering;
	}

	public void setIfEntering(Integer ifEntering) {
		this.ifEntering = ifEntering;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public String getOrgNames() {
		return orgNames;
	}

	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

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

	public String getOperatorIds() {
		return operatorIds;
	}

	public void setOperatorIds(String operatorIds) {
		this.operatorIds = operatorIds;
	}

	private String operateLog;

	private Long operateLogTime;

	public Long getOperateLogTime() {
		return operateLogTime;
	}

	public void setOperateLogTime(Long operateLogTime) {
		this.operateLogTime = operateLogTime;
	}

	public String getOperateLog() {
		return operateLog;
	}

	public void setOperateLog(String operateLog) {
		this.operateLog = operateLog;
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

	public String getGroupCodeMark() {
		return groupCodeMark;
	}

	public void setGroupCodeMark(String groupCodeMark) {
		this.groupCodeMark = groupCodeMark;
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
		this.productBrandName = productBrandName == null ? null
				: productBrandName.trim();
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
		this.serviceStandard = serviceStandard == null ? null : serviceStandard
				.trim();
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
		this.remarkInternal = remarkInternal == null ? null : remarkInternal
				.trim();
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

	public Integer getOperType() {
		return operType;
	}

	public void setOperType(Integer operType) {
		this.operType = operType;
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

	public List<BookingGuide> getGuideList() {
		return guideList;
	}

	public void setGuideList(List<BookingGuide> guideList) {
		this.guideList = guideList;
	}

}