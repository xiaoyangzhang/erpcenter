package com.yimayhd.erpcenter.dal.sales.client.tj.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TJGroupShop implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer bizId;
	private Integer groupId;
	private Integer groupMode;
	private String groupCode;
	private Integer groupState;
	private Integer productId;
	private String productName;
	private Integer prudctBrandId;
	private String productBrandName;
	private Date dateStart;
	private Date dateEnd;
	private Integer operatorId;
	private String operatorName;
	private Integer numAdult;
	private Integer numChild;
	private Integer numGuide;
	private Integer shopSupplierId;
	private String shopSupplierName;
	private String orderSupplierNames;
	private Date shopDate;
	private Integer guideId;
	private String guideName;
	private BigDecimal totalFact;
	private BigDecimal totalCash;
	private BigDecimal totalRepay;
	private BigDecimal personBuyAvg;
	private BigDecimal totalComm;
	private BigDecimal shopProfit;
	private Integer personNum;
	private Integer personNumFact;
	private Date createTime;
	private Integer operatorCompanyId;
    private Integer departmentId;
    private Integer guideManageId;
    private String guideManageName;
    private Integer saleOperatorId;
    
    public Integer getGuideManageId() {
		return guideManageId;
	}
	public void setGuideManageId(Integer guideManageId) {
		this.guideManageId = guideManageId;
	}
	public String getGuideManageName() {
		return guideManageName;
	}
	public void setGuideManageName(String guideManageName) {
		this.guideManageName = guideManageName;
	}
    public Integer getOperatorCompanyId() {
		return operatorCompanyId;
	}
	public void setOperatorCompanyId(Integer operatorCompanyId) {
		this.operatorCompanyId = operatorCompanyId;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
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
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
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
	public Integer getGroupState() {
		return groupState;
	}
	public void setGroupState(Integer groupState) {
		this.groupState = groupState;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
		this.productBrandName = productBrandName;
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
	public Integer getNumAdult() {
		return numAdult;
	}
	public void setNumAdult(Integer numAdult) {
		this.numAdult = numAdult;
	}
	public Integer getNumChild() {
		return numChild;
	}
	public void setNumChild(Integer numChild) {
		this.numChild = numChild;
	}
	public Integer getNumGuide() {
		return numGuide;
	}
	public void setNumGuide(Integer numGuide) {
		this.numGuide = numGuide;
	}
	public Integer getShopSupplierId() {
		return shopSupplierId;
	}
	public void setShopSupplierId(Integer shopSupplierId) {
		this.shopSupplierId = shopSupplierId;
	}
	public String getShopSupplierName() {
		return shopSupplierName;
	}
	public void setShopSupplierName(String shopSupplierName) {
		this.shopSupplierName = shopSupplierName;
	}
	public String getOrderSupplierNames() {
		return orderSupplierNames;
	}
	public void setOrderSupplierNames(String orderSupplierNames) {
		this.orderSupplierNames = orderSupplierNames;
	}
	public Date getShopDate() {
		return shopDate;
	}
	public void setShopDate(Date shopDate) {
		this.shopDate = shopDate;
	}
	public Integer getGuideId() {
		return guideId;
	}
	public void setGuideId(Integer guideId) {
		this.guideId = guideId;
	}
	public String getGuideName() {
		return guideName;
	}
	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}
	public BigDecimal getTotalFact() {
		return totalFact;
	}
	public void setTotalFact(BigDecimal totalFact) {
		this.totalFact = totalFact;
	}
	public BigDecimal getTotalCash() {
		return totalCash;
	}
	public void setTotalCash(BigDecimal totalCash) {
		this.totalCash = totalCash;
	}
	public BigDecimal getTotalRepay() {
		return totalRepay;
	}
	public void setTotalRepay(BigDecimal totalRepay) {
		this.totalRepay = totalRepay;
	}
	public BigDecimal getPersonBuyAvg() {
		return personBuyAvg;
	}
	public void setPersonBuyAvg(BigDecimal personBuyAvg) {
		this.personBuyAvg = personBuyAvg;
	}
	public BigDecimal getTotalComm() {
		return totalComm;
	}
	public void setTotalComm(BigDecimal totalComm) {
		this.totalComm = totalComm;
	}
	public BigDecimal getShopProfit() {
		return shopProfit;
	}
	public void setShopProfit(BigDecimal shopProfit) {
		this.shopProfit = shopProfit;
	}
	public Integer getPersonNum() {
		return personNum;
	}
	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
	}
	public Integer getPersonNumFact() {
		return personNumFact;
	}
	public void setPersonNumFact(Integer personNumFact) {
		this.personNumFact = personNumFact;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getSaleOperatorId() {
		return saleOperatorId;
	}
	public void setSaleOperatorId(Integer saleOperatorId) {
		this.saleOperatorId = saleOperatorId;
	}
	
}
