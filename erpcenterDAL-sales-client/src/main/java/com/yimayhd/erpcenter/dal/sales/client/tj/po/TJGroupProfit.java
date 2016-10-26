package com.yimayhd.erpcenter.dal.sales.client.tj.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class TJGroupProfit implements Serializable{

	public static  final Integer STATUS_AUDITED=1;
	public static  final Integer STATUS_NOT_AUDITED=0;
	public static  final Integer GROUP_STATUS_NOT_AUDITED=1; // group_status的已确认
	public static  final Integer GROUP_STATUS_AUDITED=3; // group_status的已确认
	public static  final Integer GROUP_STATUS_SEALED=4; // group_status的已确认
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer bizId;
	private Integer groupId;
	private Integer groupMode;
	private String groupCode;
	private Integer groupState;
	@JSONField(format="yyyy-MM-dd")
	private Date dateStart;
	@JSONField(format="yyyy-MM-dd")
	private Date dateEnd;
	private String productBrandName;
	private String productName;
	private Integer totalAdult=0;
	private Integer totalChild=0;
	private Integer totalGuide=0;
	private Integer operatorId;
	private String operatorName;
	private Integer operatorCompanyId;
	private String orderSupplierNames;
	private String orderSupplierIds;
	private String orderIds;
	private String deliveryNames;
	private String deliveryIds;
	private BigDecimal incomeOrder = new BigDecimal(0);
	private Integer incomeOrderState = 0;
	private BigDecimal incomeOther = new BigDecimal(0);
	private Integer incomeOtherState = 0;
	private BigDecimal incomeShop = new BigDecimal(0);
	private Integer incomeShopState = 0;
	private BigDecimal expenseTravelagency = new BigDecimal(0);
	private Integer expenseTravelagencyState = 0;
	private BigDecimal expenseRestaurant = new BigDecimal(0);
	private Integer expenseRestaurantState = 0;
	private BigDecimal expenseHotel = new BigDecimal(0);
	private Integer expenseHotelState = 0;
	private BigDecimal expenseFleet = new BigDecimal(0);
	private Integer expenseFleetState = 0;
	private BigDecimal expenseScenicspot = new BigDecimal(0);
	private Integer expenseScenicspotState = 0;
	private BigDecimal expenseAirticket = new BigDecimal(0);
	private Integer expenseAirticketState = 0;
	private BigDecimal expenseTrainticket = new BigDecimal(0);
	private Integer expenseTrainticketState = 0;
	private BigDecimal expenseInsurance = new BigDecimal(0);
	private Integer expenseInsuranceState = 0;
	private BigDecimal expenseOther = new BigDecimal(0);
	private Integer expenseOtherState = 0;
	private BigDecimal totalIncome = new BigDecimal(0);
	private BigDecimal totalExpense = new BigDecimal(0);
	private BigDecimal totalProfit = new BigDecimal(0);
	private BigDecimal profitPerGuest = new BigDecimal(0);
	private BigDecimal shopSales = new BigDecimal(0);
	private Integer shopSalesState = 0;
	private BigDecimal shopRepay = new BigDecimal(0);
	private Integer shopRepayState = 0;
	private BigDecimal shopCommission = new BigDecimal(0);
	private Integer shopCommissionState = 0;
	private BigDecimal personAvgCost = new BigDecimal(0);
	private String orderDetails;
	
	public BigDecimal getPersonAvgCost() {
		return personAvgCost;
	}
	public void setPersonAvgCost(BigDecimal personAvgCost) {
		this.personAvgCost = personAvgCost;
	}
	private Integer saleOperatorId;
	
	public String getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}
	public String getOrderIds() {
		return orderIds;
	}
	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
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
	public Integer getOperatorCompanyId() {
		return operatorCompanyId;
	}
	public void setOperatorCompanyId(Integer operatorCompanyId) {
		this.operatorCompanyId = operatorCompanyId;
	}
	public String getOrderSupplierNames() {
		return orderSupplierNames;
	}
	public void setOrderSupplierNames(String orderSupplierNames) {
		this.orderSupplierNames = orderSupplierNames;
	}
	public String getOrderSupplierIds() {
		return orderSupplierIds;
	}
	public void setOrderSupplierIds(String orderSupplierIds) {
		this.orderSupplierIds = orderSupplierIds;
	}
	public String getDeliveryNames() {
		return deliveryNames;
	}
	public void setDeliveryNames(String deliveryNames) {
		this.deliveryNames = deliveryNames;
	}
	public String getDeliveryIds() {
		return deliveryIds;
	}
	public void setDeliveryIds(String deliveryIds) {
		this.deliveryIds = deliveryIds;
	}
	public BigDecimal getIncomeOrder() {
		return incomeOrder;
	}
	public void setIncomeOrder(BigDecimal incomeOrder) {
		this.incomeOrder = incomeOrder;
	}
	public Integer getIncomeOrderState() {
		return incomeOrderState;
	}
	public void setIncomeOrderState(Integer incomeOrderState) {
		this.incomeOrderState = incomeOrderState;
	}
	public BigDecimal getIncomeOther() {
		return incomeOther;
	}
	public void setIncomeOther(BigDecimal incomeOther) {
		this.incomeOther = incomeOther;
	}
	public Integer getIncomeOtherState() {
		return incomeOtherState;
	}
	public void setIncomeOtherState(Integer incomeOtherState) {
		this.incomeOtherState = incomeOtherState;
	}
	public BigDecimal getIncomeShop() {
		return incomeShop;
	}
	public void setIncomeShop(BigDecimal incomeShop) {
		this.incomeShop = incomeShop;
	}
	public Integer getIncomeShopState() {
		return incomeShopState;
	}
	public void setIncomeShopState(Integer incomeShopState) {
		this.incomeShopState = incomeShopState;
	}
	public BigDecimal getExpenseTravelagency() {
		return expenseTravelagency;
	}
	public void setExpenseTravelagency(BigDecimal expenseTravelagency) {
		this.expenseTravelagency = expenseTravelagency;
	}
	public Integer getExpenseTravelagencyState() {
		return expenseTravelagencyState;
	}
	public void setExpenseTravelagencyState(Integer expenseTravelagencyState) {
		this.expenseTravelagencyState = expenseTravelagencyState;
	}
	public BigDecimal getExpenseRestaurant() {
		return expenseRestaurant;
	}
	public void setExpenseRestaurant(BigDecimal expenseRestaurant) {
		this.expenseRestaurant = expenseRestaurant;
	}
	public Integer getExpenseRestaurantState() {
		return expenseRestaurantState;
	}
	public void setExpenseRestaurantState(Integer expenseRestaurantState) {
		this.expenseRestaurantState = expenseRestaurantState;
	}
	public BigDecimal getExpenseHotel() {
		return expenseHotel;
	}
	public void setExpenseHotel(BigDecimal expenseHotel) {
		this.expenseHotel = expenseHotel;
	}
	public Integer getExpenseHotelState() {
		return expenseHotelState;
	}
	public void setExpenseHotelState(Integer expenseHotelState) {
		this.expenseHotelState = expenseHotelState;
	}
	public BigDecimal getExpenseFleet() {
		return expenseFleet;
	}
	public void setExpenseFleet(BigDecimal expenseFleet) {
		this.expenseFleet = expenseFleet;
	}
	public Integer getExpenseFleetState() {
		return expenseFleetState;
	}
	public void setExpenseFleetState(Integer expenseFleetState) {
		this.expenseFleetState = expenseFleetState;
	}
	public BigDecimal getExpenseScenicspot() {
		return expenseScenicspot;
	}
	public void setExpenseScenicspot(BigDecimal expenseScenicspot) {
		this.expenseScenicspot = expenseScenicspot;
	}
	public Integer getExpenseScenicspotState() {
		return expenseScenicspotState;
	}
	public void setExpenseScenicspotState(Integer expenseScenicspotState) {
		this.expenseScenicspotState = expenseScenicspotState;
	}
	public BigDecimal getExpenseAirticket() {
		return expenseAirticket;
	}
	public void setExpenseAirticket(BigDecimal expenseAirticket) {
		this.expenseAirticket = expenseAirticket;
	}
	public Integer getExpenseAirticketState() {
		return expenseAirticketState;
	}
	public void setExpenseAirticketState(Integer expenseAirticketState) {
		this.expenseAirticketState = expenseAirticketState;
	}
	public BigDecimal getExpenseTrainticket() {
		return expenseTrainticket;
	}
	public void setExpenseTrainticket(BigDecimal expenseTrainticket) {
		this.expenseTrainticket = expenseTrainticket;
	}
	public Integer getExpenseTrainticketState() {
		return expenseTrainticketState;
	}
	public void setExpenseTrainticketState(Integer expenseTrainticketState) {
		this.expenseTrainticketState = expenseTrainticketState;
	}
	public BigDecimal getExpenseInsurance() {
		return expenseInsurance;
	}
	public void setExpenseInsurance(BigDecimal expenseInsurance) {
		this.expenseInsurance = expenseInsurance;
	}
	public Integer getExpenseInsuranceState() {
		return expenseInsuranceState;
	}
	public void setExpenseInsuranceState(Integer expenseInsuranceState) {
		this.expenseInsuranceState = expenseInsuranceState;
	}
	public BigDecimal getExpenseOther() {
		return expenseOther;
	}
	public void setExpenseOther(BigDecimal expenseOther) {
		this.expenseOther = expenseOther;
	}
	public Integer getExpenseOtherState() {
		return expenseOtherState;
	}
	public void setExpenseOtherState(Integer expenseOtherState) {
		this.expenseOtherState = expenseOtherState;
	}
	public BigDecimal getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}
	public BigDecimal getTotalExpense() {
		return totalExpense;
	}
	public void setTotalExpense(BigDecimal totalExpense) {
		this.totalExpense = totalExpense;
	}
	public BigDecimal getTotalProfit() {
		return totalProfit;
	}
	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}
	public BigDecimal getProfitPerGuest() {
		return profitPerGuest;
	}
	public void setProfitPerGuest(BigDecimal profitPerGuest) {
		this.profitPerGuest = profitPerGuest;
	}
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getShopSales() {
		return shopSales;
	}
	public void setShopSales(BigDecimal shopSales) {
		this.shopSales = shopSales;
	}
	public BigDecimal getShopRepay() {
		return shopRepay;
	}
	public void setShopRepay(BigDecimal shopRepay) {
		this.shopRepay = shopRepay;
	}
	public BigDecimal getShopCommission() {
		return shopCommission;
	}
	public void setShopCommission(BigDecimal shopCommission) {
		this.shopCommission = shopCommission;
	}
	public Integer getShopSalesState() {
		return shopSalesState;
	}
	public void setShopSalesState(Integer shopSalesState) {
		this.shopSalesState = shopSalesState;
	}
	public Integer getShopRepayState() {
		return shopRepayState;
	}
	public void setShopRepayState(Integer shopRepayState) {
		this.shopRepayState = shopRepayState;
	}
	public Integer getShopCommissionState() {
		return shopCommissionState;
	}
	public void setShopCommissionState(Integer shopCommissionState) {
		this.shopCommissionState = shopCommissionState;
	}
	public Integer getSaleOperatorId() {
		return saleOperatorId;
	}
	public void setSaleOperatorId(Integer saleOperatorId) {
		this.saleOperatorId = saleOperatorId;
	}
	
}
