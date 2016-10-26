package com.yimayhd.erpcenter.dal.sales.client.sales.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class TourGroupVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer bizId;
	private Date startTime;
	private Date endTime;
	private String dateStart_Search;
	private String dateEnd_Search;
	
	private Integer groupMode;
	private Integer selectDate;//对下拉列表选择日期的取值
	private Integer arrangementState;//团是否安排供应商
	private Integer totalAdult;
	private Integer totalChild;
	private Integer operatorId;
	private String operatorName;
	private Integer state;
	private String receiveMode;
	private String groupCode;
	//private Integer groupState;
	private String groupStatus;
	private String productBrandName;
	private Integer productBrandId;
	private String productName;
	

	private Integer pageSize;
	private Integer page;
	
	private Integer supplierType;
	private String guideName;//导游姓名
	private String guideMobile;//导游姓名
	private Integer supplierId;
	private String supplierName;
	private String supplierContent;//根据供应商名称或者司机/车牌号查询时输入内容
	private String driverName;
	private String carLisence;
	private Integer driverId;
	
	private String saleOperatorIds;
	private String saleOperatorName;
	private String orgIds;
	private String orgNames;
	private Long createTime;
	private String remark;//计调订单的备注
	private Set<Integer> groupIdSet;
		
	/*	以下三个字段是jqgrid 专用 */	
	protected Integer rows; //每页显示的行数 如：15
	protected String sidx;  //点击表格标题字段，排序字段， 如：userName
	protected String sord;  //排序方式： desc、asc
	
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	
	
	public String getDateStart_Search() {
		return dateStart_Search;
	}
	public void setDateStart_Search(String dateStart_Search) {
		this.dateStart_Search = dateStart_Search;
	}
	public String getDateEnd_Search() {
		return dateEnd_Search;
	}
	public void setDateEnd_Search(String dateEnd_Search) {
		this.dateEnd_Search = dateEnd_Search;
	}
	public Set<Integer> getGroupIdSet() {
		return groupIdSet;
	}
	public void setGroupIdSet(Set<Integer> groupIdSet) {
		this.groupIdSet = groupIdSet;
	}
	public Integer getArrangementState() {
		return arrangementState;
	}
	public void setArrangementState(Integer arrangementState) {
		this.arrangementState = arrangementState;
	}
	public Integer getSelectDate() {
		return selectDate;
	}
	public void setSelectDate(Integer selectDate) {
		this.selectDate = selectDate;
	}
	
	public Integer getProductBrandId() {
		return productBrandId;
	}
	public void setProductBrandId(Integer productBrandId) {
		this.productBrandId = productBrandId;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	public String getGroupStatus() {
		return groupStatus;
	}
	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}
	public String getGuideName() {
		return guideName;
	}
	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}
	public String getGuideMobile() {
		return guideMobile;
	}
	public void setGuideMobile(String guideMobile) {
		this.guideMobile = guideMobile;
	}
	
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getSupplierContent() {
		return supplierContent;
	}
	public void setSupplierContent(String supplierContent) {
		this.supplierContent = supplierContent;
	}
	public String getSaleOperatorName() {
		return saleOperatorName;
	}
	public void setSaleOperatorName(String saleOperatorName) {
		this.saleOperatorName = saleOperatorName;
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
		this.groupCode = groupCode;
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
		this.operatorName = operatorName;
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
		if(pageSize==null){
			if (rows != null)
				pageSize = rows;
			else
				pageSize = 15;
		}
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
	public Integer getSupplierType() {
		return supplierType;
	}
	public void setSupplierType(Integer supplierType) {
		this.supplierType = supplierType;
	}
	public String getSaleOperatorIds() {
		return saleOperatorIds;
	}
	public void setSaleOperatorIds(String saleOperatorIds) {
		this.saleOperatorIds = saleOperatorIds;
	}
	
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getCarLisence() {
		return carLisence;
	}
	public void setCarLisence(String carLisence) {
		this.carLisence = carLisence;
	}
	public Integer getDriverId() {
		return driverId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	


	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	public String getOrgNames() {
		return orgNames;
	}
	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}
	public String getReceiveMode() {
		return receiveMode;
	}
	public void setReceiveMode(String receiveMode) {
		this.receiveMode = receiveMode;
	}
	
}
