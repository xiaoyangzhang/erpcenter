package com.yimayhd.erpcenter.dal.product.po;


import com.yimayhd.erpcenter.common.util.LogFieldAnno;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TrafficResProduct implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2513836269602637365L;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.id
	 * @mbggenerated
	 */
	@LogFieldAnno(isKey = true)
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.res_id
	 * @mbggenerated
	 */
	private Integer resId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.product_name
	 * @mbggenerated
	 */
	@LogFieldAnno(description="产品名称", delOrIns = true)
	private String productName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.product_code
	 * @mbggenerated
	 */
	private Integer productCode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.num_stock
	 * @mbggenerated
	 */
	@LogFieldAnno(description="库存数量", delOrIns = true)
	private Integer numStock;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.num_sold
	 * @mbggenerated
	 */
	@LogFieldAnno(description="已售数量", delOrIns = true)
	private Integer numSold;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.adult_suggest_price
	 * @mbggenerated
	 */
	@LogFieldAnno(description="成本价（成人）", delOrIns = true)
	private BigDecimal adultCostPrice;
	@LogFieldAnno(description="零售价（成人）", delOrIns = true)
	private BigDecimal adultSuggestPrice;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.adult_same_pay
	 * @mbggenerated
	 */
	@LogFieldAnno(description="同行返（成人）", delOrIns = true)
	private BigDecimal adultSamePay;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.adult_proxy_pay
	 * @mbggenerated
	 */
	@LogFieldAnno(description="代理返（成人）", delOrIns = true)
	private BigDecimal adultProxyPay;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.adult_min_deposit
	 * @mbggenerated
	 */
	@LogFieldAnno(description="最低定金（成人）", delOrIns = true)
	private BigDecimal adultMinDeposit;
	@LogFieldAnno(description="成本价（儿童）", delOrIns = true)
	private BigDecimal childCostPrice;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.child_suggest_price
	 * @mbggenerated
	 */
	@LogFieldAnno(description="零售价（儿童）", delOrIns = true)
	private BigDecimal childSuggestPrice;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.child_same_pay
	 * @mbggenerated
	 */
	@LogFieldAnno(description="同行返（儿童）", delOrIns = true)
	private BigDecimal childSamePay;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.child_proxy_pay
	 * @mbggenerated
	 */
	@LogFieldAnno(description="代理返（儿童）", delOrIns = true)
	private BigDecimal childProxyPay;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.child_min_deposit
	 * @mbggenerated
	 */
	@LogFieldAnno(description="最低定金（儿童）", delOrIns = true)
	private BigDecimal childMinDeposit;
	

	@LogFieldAnno(description="成本价（婴儿）", delOrIns = true)
	private BigDecimal babyCostPrice;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.baby_suggest_price
	 * @mbggenerated
	 */
	@LogFieldAnno(description="零售价（婴儿）", delOrIns = true)
	private BigDecimal babySuggestPrice;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.baby_same_pay
	 * @mbggenerated
	 */
	@LogFieldAnno(description="同行返（婴儿）", delOrIns = true)
	private BigDecimal babySamePay;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.bady_proxy_pay
	 * @mbggenerated
	 */
	@LogFieldAnno(description="代理返（婴儿）", delOrIns = true)
	private BigDecimal badyProxyPay;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.bady_min_deposit
	 * @mbggenerated
	 */
	@LogFieldAnno(description="最低定金（婴儿）", delOrIns = true)
	private BigDecimal badyMinDeposit;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.reserve_time
	 * @mbggenerated
	 */
	@LogFieldAnno(description="最长预留时长", delOrIns = true)
	private Integer reserveTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.reserve_stock_limit
	 * @mbggenerated
	 */
	@LogFieldAnno(description="取消预留库存下限", delOrIns = true)
	private Integer reserveStockLimit;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.user_id
	 * @mbggenerated
	 */
	private Integer userId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.user_name
	 * @mbggenerated
	 */
	private String userName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.time_create
	 * @mbggenerated
	 */
	private Date timeCreate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column traffic_res_product.time_update
	 * @mbggenerated
	 */
	private Date timeUpdate;
	
	private String dateStart;

	private BigDecimal adultCostHotel;
	private BigDecimal adultCostTicket;
	private BigDecimal adultCostJs;
	private BigDecimal adultCostOther;

	private BigDecimal childCostHotel;
	private BigDecimal childCostTicket;
	private BigDecimal childCostJs;
	private BigDecimal childCostOther;

	private BigDecimal babyCostHotel;
	private BigDecimal babyCostTicket;
	private BigDecimal babyCostJs;
	private BigDecimal babyCostOther;

	public BigDecimal getChildCostHotel() {
		return childCostHotel;
	}

	public void setChildCostHotel(BigDecimal childCostHotel) {
		this.childCostHotel = childCostHotel;
	}

	public BigDecimal getChildCostTicket() {
		return childCostTicket;
	}

	public void setChildCostTicket(BigDecimal childCostTicket) {
		this.childCostTicket = childCostTicket;
	}

	public BigDecimal getChildCostJs() {
		return childCostJs;
	}

	public void setChildCostJs(BigDecimal childCostJs) {
		this.childCostJs = childCostJs;
	}

	public BigDecimal getChildCostOther() {
		return childCostOther;
	}

	public void setChildCostOther(BigDecimal childCostOther) {
		this.childCostOther = childCostOther;
	}

	public BigDecimal getBabyCostHotel() {
		return babyCostHotel;
	}

	public void setBabyCostHotel(BigDecimal babyCostHotel) {
		this.babyCostHotel = babyCostHotel;
	}

	public BigDecimal getBabyCostTicket() {
		return babyCostTicket;
	}

	public void setBabyCostTicket(BigDecimal babyCostTicket) {
		this.babyCostTicket = babyCostTicket;
	}

	public BigDecimal getBabyCostJs() {
		return babyCostJs;
	}

	public void setBabyCostJs(BigDecimal babyCostJs) {
		this.babyCostJs = babyCostJs;
	}

	public BigDecimal getBabyCostOther() {
		return babyCostOther;
	}

	public void setBabyCostOther(BigDecimal babyCostOther) {
		this.babyCostOther = babyCostOther;
	}
	
	private Integer numBalance;
	private String resName; 
	private String lineInfo; 
	private String remark; 
	private Integer bizId;
	
	private BigDecimal adultSame;
	private BigDecimal adultProxy;
	private BigDecimal childSame;
	private BigDecimal childProxy;
	private BigDecimal babySame;
	private BigDecimal babyProxy;
	private Integer operatorId; 
	private String operatorName; 
	private String productAttach;
	private String dateLatest;
	private Integer confirm;
	private Integer unconfirm;
	private BigDecimal sumCost;
	private List<TrafficResProduct> trafficResProducts;

	public List<TrafficResProduct> getTrafficResProducts() {
		return trafficResProducts;
	}

	public void setTrafficResProducts(List<TrafficResProduct> trafficResProducts) {
		this.trafficResProducts = trafficResProducts;
	}

	public BigDecimal getSumCost() {
		return sumCost;
	}

	public void setSumCost(BigDecimal sumCost) {
		this.sumCost = sumCost;
	}

	public BigDecimal getAdultCostHotel() {
		return adultCostHotel;
	}

	public void setAdultCostHotel(BigDecimal adultCostHotel) {
		this.adultCostHotel = adultCostHotel;
	}

	public BigDecimal getAdultCostTicket() {
		return adultCostTicket;
	}

	public void setAdultCostTicket(BigDecimal adultCostTicket) {
		this.adultCostTicket = adultCostTicket;
	}

	public BigDecimal getAdultCostJs() {
		return adultCostJs;
	}

	public void setAdultCostJs(BigDecimal adultCostJs) {
		this.adultCostJs = adultCostJs;
	}

	public BigDecimal getAdultCostOther() {
		return adultCostOther;
	}

	public void setAdultCostOther(BigDecimal adultCostOther) {
		this.adultCostOther = adultCostOther;
	}
	
	public Integer getConfirm() {
		return confirm;
	}

	public void setConfirm(Integer confirm) {
		this.confirm = confirm;
	}

	public Integer getUnconfirm() {
		return unconfirm;
	}

	public void setUnconfirm(Integer unconfirm) {
		this.unconfirm = unconfirm;
	}

	public String getDateLatest() {
		return dateLatest;
	}

	public void setDateLatest(String dateLatest) {
		this.dateLatest = dateLatest;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public BigDecimal getAdultSame() {
		return adultSame;
	}

	public void setAdultSame(BigDecimal adultSame) {
		this.adultSame = adultSame;
	}

	public BigDecimal getAdultProxy() {
		return adultProxy;
	}

	public void setAdultProxy(BigDecimal adultProxy) {
		this.adultProxy = adultProxy;
	}

	public BigDecimal getChildSame() {
		return childSame;
	}

	public void setChildSame(BigDecimal childSame) {
		this.childSame = childSame;
	}

	public BigDecimal getChildProxy() {
		return childProxy;
	}

	public void setChildProxy(BigDecimal childProxy) {
		this.childProxy = childProxy;
	}

	public BigDecimal getBabySame() {
		return babySame;
	}

	public void setBabySame(BigDecimal babySame) {
		this.babySame = babySame;
	}

	public BigDecimal getBabyProxy() {
		return babyProxy;
	}

	public void setBabyProxy(BigDecimal babyProxy) {
		this.babyProxy = babyProxy;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getLineInfo() {
		return lineInfo;
	}

	public void setLineInfo(String lineInfo) {
		this.lineInfo = lineInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}

	public Integer getNumBalance() {
		return numBalance;
	}

	public void setNumBalance(Integer numBalance) {
		this.numBalance = numBalance;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.id
	 * @return  the value of traffic_res_product.id
	 * @mbggenerated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.id
	 * @param id  the value for traffic_res_product.id
	 * @mbggenerated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.res_id
	 * @return  the value of traffic_res_product.res_id
	 * @mbggenerated
	 */
	public Integer getResId() {
		return resId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.res_id
	 * @param resId  the value for traffic_res_product.res_id
	 * @mbggenerated
	 */
	public void setResId(Integer resId) {
		this.resId = resId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.product_name
	 * @return  the value of traffic_res_product.product_name
	 * @mbggenerated
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.product_name
	 * @param productName  the value for traffic_res_product.product_name
	 * @mbggenerated
	 */
	public void setProductName(String productName) {
		this.productName = productName == null ? null : productName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.product_code
	 * @return  the value of traffic_res_product.product_code
	 * @mbggenerated
	 */
	public Integer getProductCode() {
		return productCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.product_code
	 * @param productCode  the value for traffic_res_product.product_code
	 * @mbggenerated
	 */
	public void setProductCode(Integer productCode) {
		this.productCode = productCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.num_stock
	 * @return  the value of traffic_res_product.num_stock
	 * @mbggenerated
	 */
	public Integer getNumStock() {
		return numStock;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.num_stock
	 * @param numStock  the value for traffic_res_product.num_stock
	 * @mbggenerated
	 */
	public void setNumStock(Integer numStock) {
		this.numStock = numStock;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.num_sold
	 * @return  the value of traffic_res_product.num_sold
	 * @mbggenerated
	 */
	public Integer getNumSold() {
		return numSold;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.num_sold
	 * @param numSold  the value for traffic_res_product.num_sold
	 * @mbggenerated
	 */
	public void setNumSold(Integer numSold) {
		this.numSold = numSold;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.adult_suggest_price
	 * @return  the value of traffic_res_product.adult_suggest_price
	 * @mbggenerated
	 */
	public BigDecimal getAdultSuggestPrice() {
		return adultSuggestPrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.adult_suggest_price
	 * @param adultSuggestPrice  the value for traffic_res_product.adult_suggest_price
	 * @mbggenerated
	 */
	public void setAdultSuggestPrice(BigDecimal adultSuggestPrice) {
		this.adultSuggestPrice = adultSuggestPrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.adult_same_pay
	 * @return  the value of traffic_res_product.adult_same_pay
	 * @mbggenerated
	 */
	public BigDecimal getAdultSamePay() {
		return adultSamePay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.adult_same_pay
	 * @param adultSamePay  the value for traffic_res_product.adult_same_pay
	 * @mbggenerated
	 */
	public void setAdultSamePay(BigDecimal adultSamePay) {
		this.adultSamePay = adultSamePay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.adult_proxy_pay
	 * @return  the value of traffic_res_product.adult_proxy_pay
	 * @mbggenerated
	 */
	public BigDecimal getAdultProxyPay() {
		return adultProxyPay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.adult_proxy_pay
	 * @param adultProxyPay  the value for traffic_res_product.adult_proxy_pay
	 * @mbggenerated
	 */
	public void setAdultProxyPay(BigDecimal adultProxyPay) {
		this.adultProxyPay = adultProxyPay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.adult_min_deposit
	 * @return  the value of traffic_res_product.adult_min_deposit
	 * @mbggenerated
	 */
	public BigDecimal getAdultMinDeposit() {
		return adultMinDeposit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.adult_min_deposit
	 * @param adultMinDeposit  the value for traffic_res_product.adult_min_deposit
	 * @mbggenerated
	 */
	public void setAdultMinDeposit(BigDecimal adultMinDeposit) {
		this.adultMinDeposit = adultMinDeposit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.child_suggest_price
	 * @return  the value of traffic_res_product.child_suggest_price
	 * @mbggenerated
	 */
	public BigDecimal getChildSuggestPrice() {
		return childSuggestPrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.child_suggest_price
	 * @param childSuggestPrice  the value for traffic_res_product.child_suggest_price
	 * @mbggenerated
	 */
	public void setChildSuggestPrice(BigDecimal childSuggestPrice) {
		this.childSuggestPrice = childSuggestPrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.child_same_pay
	 * @return  the value of traffic_res_product.child_same_pay
	 * @mbggenerated
	 */
	public BigDecimal getChildSamePay() {
		return childSamePay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.child_same_pay
	 * @param childSamePay  the value for traffic_res_product.child_same_pay
	 * @mbggenerated
	 */
	public void setChildSamePay(BigDecimal childSamePay) {
		this.childSamePay = childSamePay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.child_proxy_pay
	 * @return  the value of traffic_res_product.child_proxy_pay
	 * @mbggenerated
	 */
	public BigDecimal getChildProxyPay() {
		return childProxyPay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.child_proxy_pay
	 * @param childProxyPay  the value for traffic_res_product.child_proxy_pay
	 * @mbggenerated
	 */
	public void setChildProxyPay(BigDecimal childProxyPay) {
		this.childProxyPay = childProxyPay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.child_min_deposit
	 * @return  the value of traffic_res_product.child_min_deposit
	 * @mbggenerated
	 */
	public BigDecimal getChildMinDeposit() {
		return childMinDeposit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.child_min_deposit
	 * @param childMinDeposit  the value for traffic_res_product.child_min_deposit
	 * @mbggenerated
	 */
	public void setChildMinDeposit(BigDecimal childMinDeposit) {
		this.childMinDeposit = childMinDeposit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.baby_suggest_price
	 * @return  the value of traffic_res_product.baby_suggest_price
	 * @mbggenerated
	 */
	public BigDecimal getBabySuggestPrice() {
		return babySuggestPrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.baby_suggest_price
	 * @param babySuggestPrice  the value for traffic_res_product.baby_suggest_price
	 * @mbggenerated
	 */
	public void setBabySuggestPrice(BigDecimal babySuggestPrice) {
		this.babySuggestPrice = babySuggestPrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.baby_same_pay
	 * @return  the value of traffic_res_product.baby_same_pay
	 * @mbggenerated
	 */
	public BigDecimal getBabySamePay() {
		return babySamePay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.baby_same_pay
	 * @param babySamePay  the value for traffic_res_product.baby_same_pay
	 * @mbggenerated
	 */
	public void setBabySamePay(BigDecimal babySamePay) {
		this.babySamePay = babySamePay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.bady_proxy_pay
	 * @return  the value of traffic_res_product.bady_proxy_pay
	 * @mbggenerated
	 */
	public BigDecimal getBadyProxyPay() {
		return badyProxyPay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.bady_proxy_pay
	 * @param badyProxyPay  the value for traffic_res_product.bady_proxy_pay
	 * @mbggenerated
	 */
	public void setBadyProxyPay(BigDecimal badyProxyPay) {
		this.badyProxyPay = badyProxyPay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.bady_min_deposit
	 * @return  the value of traffic_res_product.bady_min_deposit
	 * @mbggenerated
	 */
	public BigDecimal getBadyMinDeposit() {
		return badyMinDeposit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.bady_min_deposit
	 * @param badyMinDeposit  the value for traffic_res_product.bady_min_deposit
	 * @mbggenerated
	 */
	public void setBadyMinDeposit(BigDecimal badyMinDeposit) {
		this.badyMinDeposit = badyMinDeposit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.reserve_time
	 * @return  the value of traffic_res_product.reserve_time
	 * @mbggenerated
	 */
	public Integer getReserveTime() {
		return reserveTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.reserve_time
	 * @param reserveTime  the value for traffic_res_product.reserve_time
	 * @mbggenerated
	 */
	public void setReserveTime(Integer reserveTime) {
		this.reserveTime = reserveTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.reserve_stock_limit
	 * @return  the value of traffic_res_product.reserve_stock_limit
	 * @mbggenerated
	 */
	public Integer getReserveStockLimit() {
		return reserveStockLimit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.reserve_stock_limit
	 * @param reserveStockLimit  the value for traffic_res_product.reserve_stock_limit
	 * @mbggenerated
	 */
	public void setReserveStockLimit(Integer reserveStockLimit) {
		this.reserveStockLimit = reserveStockLimit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.user_id
	 * @return  the value of traffic_res_product.user_id
	 * @mbggenerated
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.user_id
	 * @param userId  the value for traffic_res_product.user_id
	 * @mbggenerated
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.user_name
	 * @return  the value of traffic_res_product.user_name
	 * @mbggenerated
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.user_name
	 * @param userName  the value for traffic_res_product.user_name
	 * @mbggenerated
	 */
	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.time_create
	 * @return  the value of traffic_res_product.time_create
	 * @mbggenerated
	 */
	public Date getTimeCreate() {
		return timeCreate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.time_create
	 * @param timeCreate  the value for traffic_res_product.time_create
	 * @mbggenerated
	 */
	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column traffic_res_product.time_update
	 * @return  the value of traffic_res_product.time_update
	 * @mbggenerated
	 */
	public Date getTimeUpdate() {
		return timeUpdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column traffic_res_product.time_update
	 * @param timeUpdate  the value for traffic_res_product.time_update
	 * @mbggenerated
	 */
	public void setTimeUpdate(Date timeUpdate) {
		this.timeUpdate = timeUpdate;
	}

	public BigDecimal getAdultCostPrice() {
		return adultCostPrice;
	}

	public void setAdultCostPrice(BigDecimal adultCostPrice) {
		this.adultCostPrice = adultCostPrice;
	}

	public BigDecimal getChildCostPrice() {
		return childCostPrice;
	}

	public void setChildCostPrice(BigDecimal childCostPrice) {
		this.childCostPrice = childCostPrice;
	}

	public BigDecimal getBabyCostPrice() {
		return babyCostPrice;
	}

	public void setBabyCostPrice(BigDecimal babyCostPrice) {
		this.babyCostPrice = babyCostPrice;
	}

	public String getProductAttach() {
		return productAttach;
	}

	public void setProductAttach(String productAttach) {
		this.productAttach = productAttach;
	}

	
	
	
}