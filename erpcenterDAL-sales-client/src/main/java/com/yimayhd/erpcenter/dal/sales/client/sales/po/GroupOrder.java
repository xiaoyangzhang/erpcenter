package com.yimayhd.erpcenter.dal.sales.client.sales.po;


import com.yimayhd.erpcenter.common.util.LogFieldAnno;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingShopDetailDeploy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class GroupOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @LogFieldAnno(isKey = true)
    private Integer id;

    private Integer bizId;
    
	@LogFieldAnno(description = "团ID")
    private Integer groupId;

    private String groupNo;
    
    private Set<Integer> orderIdSet;

    private Integer supplierId;
    @LogFieldAnno(description = "客户名称", delOrIns = true)
    private String supplierName;

    private int supplierCount = 0;

    private int guestCount = 0;

    private Integer groupMode;
    private Integer groupState;

    private String groupCode;

    private String orderNo;
    
    private Integer gender;
    
    private Integer ageFirst;
    
    private Integer ageSecond;
    
    private String nativePlace;

    private Integer orderNoSort;

    private Integer orderType;
    @LogFieldAnno(description = "订单类型", needExtDescription = "[{\"key\":\"0\", \"value\":\"预留订单\"},{\"key\":\"1\", \"value\":\"确认订单\"}]")
    private Integer type; // 订单类型
    @LogFieldAnno(description = "合同号")
    private String contractNo; // 合同号
    private Integer guestSourceId; // 客人来源
    @LogFieldAnno(description = "客人来源")
    private String guestSourceName; // 客人来源名称

    private String aiyouGroupId; // 爱游订单（团）ID

    private Integer reqType;

    private Integer dateType;

    private Integer showNum;
    @LogFieldAnno(description = "客户订单号")
    private String supplierCode;
    @LogFieldAnno(description = "客户联系人")
    private String contactName;
    @LogFieldAnno(description = "客户联系人电话")
    private String contactTel;
    @LogFieldAnno(description = "客户联系人手机")
    private String contactMobile;
    @LogFieldAnno(description = "客户联系人传真")
    private String contactFax;

    private Integer operatorId;
    @LogFieldAnno(description = "计调")
    private String operatorName;

    private Integer saleOperatorId;

    private String saleOperatorIds;
    @LogFieldAnno(description = "销售")
    private String saleOperatorName;

    private Integer operType;
    @LogFieldAnno(description = "成人人数", delOrIns = true)
    private Integer numAdult;
    @LogFieldAnno(description = "儿童人数", delOrIns = true)
    private Integer numChild;
    @LogFieldAnno(description = "陪同人数", delOrIns = true)
    private Integer numGuide;

    private Integer priceId;
    @LogFieldAnno(description = "出团日期", delOrIns = true)
    private String departureDate;

    private String fitDate;

    private String tourists; // 客源地
    
	private List<GroupOrderGuest> guests = new ArrayList<GroupOrderGuest>() ;

    private String startTime;
    private String endTime;
    private Integer productId;
    private Integer productBrandId;

    @LogFieldAnno(description = "产品品牌")
    private String productBrandName;
    @LogFieldAnno(description = "线路简称")
    private String productShortName;
    @LogFieldAnno(description = "产品名称")
    private String productName;
    @LogFieldAnno(description = "客人接站牌", delOrIns = true)
    private String receiveMode;
    @LogFieldAnno(description = "服务标准")
    private String serviceStandard;
    @LogFieldAnno(description = "备注")
    private String remark;
    @LogFieldAnno(description = "内部备注")
    private String remarkInternal;

    private Integer creatorId;

    private String creatorName;

    private String creatorDept;

    private TourGroup tourGroup;

    private List<GroupOrderGuest> groupOrderGuestList;

    private List<GroupOrderPrice> orderPrices;

    private Long createTime;
    private String createTimeStr;
    private Integer state;
    private Integer pageSize;
    private Integer page = 1;
    @LogFieldAnno(description = "团款金额")
    private BigDecimal total;
    private BigDecimal totalCash;
    private BigDecimal budget; // 成本预算
    private BigDecimal income; // 成本收入

    private BigDecimal otherTotal;
    private BigDecimal otherTotalCash;
    private BigDecimal otherTotalBalance;
    private BigDecimal totalBudget; // 成本预算
    private BigDecimal totalIncome; // 成本收入
    private List<Integer> idList;
    private String auditUser;

    private Integer auditUserId;
    
    private String buyerNick;
    /***内部结算（销售） 
     * @author TengDong
     * @Date 20161102
     * ***/
    @LogFieldAnno(description = "收入")
    private BigDecimal sumTotal;     
    @LogFieldAnno(description = "其它收入")
    private BigDecimal sumQdtotal;  
    @LogFieldAnno(description = "成本")
    private BigDecimal sumBudget;  
    @LogFieldAnno(description = "毛利")
    private BigDecimal sumProfit;
    
    private Integer byType;
   
    public Integer getByType() {
		return byType;
	}

	public void setByType(Integer byType) {
		this.byType = byType;
	}

	public Integer getAgeFirst() {
		return ageFirst;
	}

	public void setAgeFirst(Integer ageFirst) {
		this.ageFirst = ageFirst;
	}

	public Integer getAgeSecond() {
		return ageSecond;
	}

	public void setAgeSecond(Integer ageSecond) {
		this.ageSecond = ageSecond;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public BigDecimal getSumTotal() {
		return sumTotal;
	}

	public void setSumTotal(BigDecimal sumTotal) {
		this.sumTotal = sumTotal;
	}

	public BigDecimal getSumQdtotal() {
		return sumQdtotal;
	}

	public void setSumQdtotal(BigDecimal sumQdtotal) {
		this.sumQdtotal = sumQdtotal;
	}

	public BigDecimal getSumBudget() {
		return sumBudget;
	}

	public void setSumBudget(BigDecimal sumBudget) {
		this.sumBudget = sumBudget;
	}

	public BigDecimal getSumProfit() {
		return sumProfit;
	}

	public void setSumProfit(BigDecimal sumProfit) {
		this.sumProfit = sumProfit;
	}
	
	
	
	
	
	

	private Date auditTime;
    private Integer stateFinance;
    private Integer select; // 预算利润查询里面标记操作计调和销售计调
    // 客源类型
    private String sourceTypeId;
    @LogFieldAnno(description = "客源类型")
    private String sourceTypeName;
    @LogFieldAnno(description = "其它收入")
    private BigDecimal qdtotal;
    @LogFieldAnno(description = "毛利")
    private BigDecimal g_profit;

    public BigDecimal getG_profit() {
		return g_profit;
	}

	public void setG_profit(BigDecimal g_profit) {
		this.g_profit = g_profit;
	}

	public BigDecimal getQdtotal() {
		return qdtotal;
	}

	public void setQdtotal(BigDecimal qdtotal) {
		this.qdtotal = qdtotal;
	}

	// 客源地
    private Integer provinceId;
    @LogFieldAnno(description = "客源地(省)")
    private String provinceName;
    private Integer cityId;
    @LogFieldAnno(description = "客源地(市 )")
    private String cityName;

    private Integer departProvinceId;
    private String departProvinceName;
    private Integer departCityId;
    private String departCityName;
    
    private String orgNames;

    private String orgIds;

    private String prices;
    @LogFieldAnno(description = "锁单状态", needExtDescription = "[{\"key\":\"0\", \"value\":\"未锁\"},{\"key\":\"1\", \"value\":\"已锁\"}]")
    private Integer orderLockState;

    private String tourState;

    private String nums;

    private String guestNames;

    private String mobiles;
    private String cerNums;
    private String hotelLevels;
    private String hotelNums;
    private String guestName;
    private String mobile;
    private String upAir; // 省外接机信息
    private String offAir;// 省外送机信息
    private String trans; // 省内交通
    private List<GroupOrder> orderList;
    private Integer rowSpan;
    // 散客日志页面使用字段
    private Integer totalOrderNum;
    private Integer totalNumAdult;
    private Integer totalNumChild;
    private BigDecimal totalOrderIncome;
    // 散客日志总合计用到别名
    private Integer totalAdult;
    private Integer totalChild;

    private Integer totalOrder;
    private BigDecimal totalSum;
    private String pickUpInfo;// 接机信息
    private String transferInfo;// 送机信息
    private String guestInfo;// 客人信息
    private String priceInfo;// 订单项目类别价格信息
    private Integer itemId;
    private Integer cashState; // 收款状态，0：未收清，1：已收清
    // 应收款详情导出添加别名
    private Integer orderCount;

    private BigDecimal preTotal;

    private BigDecimal totalBalance;
    private Integer paymentState;
    private String operatorIds;
    private Integer affirmOrderCount;// 确认订单数
    private Integer reserveOrderCount;// 预留订单数
    private BookingShopDetailDeploy shopDetailDeploy;
    private Integer quartzTime;

    private Map<String, Object> personMap;

    private List<GroupRoute> groupRouteList;

    private Integer day1;
    private Integer day2;
    private Integer day3;
    private Integer day4;
    private Integer day5;
    private Integer day6;
    private Integer day7;
    private Integer day8;
    private Integer day9;
    private Integer day10;

    private Integer day11;
    private Integer day12;
    private Integer day13;
    private Integer day14;
    private Integer day15;
    private Integer day16;
    private Integer day17;
    private Integer day18;
    private Integer day19;
    private Integer day20;

    private Integer day21;
    private Integer day22;
    private Integer day23;
    private Integer day24;
    private Integer day25;
    private Integer day26;
    private Integer day27;
    private Integer day28;
    private Integer day29;
    private Integer day30;
    private Integer day31;
    private Integer dayNums;
    private Integer person;
    private Integer sumPerson;
    private String orderBusiness;

    private Integer numChildBaby;
    private Integer extResId;
    // 订单状态（0待确认（占位）、1已确认（占位）、2正常取消（退还），3系统执行自动任务取消（清位））
    @LogFieldAnno(description = "客源类型", needExtDescription = "[{\"key\":\"0\", \"value\":\"待确认\"},{\"key\":\"1\", \"value\":\"已确认\"},{\"key\":\"2\", \"value\":\"取消\"},{\"key\":\"3\", \"value\":\"清位取消\"}]")
    private Integer extResState;
    private Integer extResCleanTime;
    private Integer extResCleanStock;
    @LogFieldAnno(description = "订金")
    private BigDecimal extResPrepay;

    private Integer extResConfirmId;

    private String extResConfirmName;

    private String limitTime;

    private Integer pushOrderId;
    private Date pushTimeUpdate;
    private String pushSupplierName;
    
    private Integer sourceTypes;

    private BigDecimal cost;
    private BigDecimal costCash;
    private BigDecimal costBalance;
    private String dateStart;
    private BigDecimal groupCost;
    private String patTime;
    private String receiptTime;
    private String sendSignTime;
    private String sendTime;
    private String companyName;
    private String expressOrderNo;
    private String businessName;
    
    
    public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getExpressOrderNo() {
		return expressOrderNo;
	}

	public void setExpressOrderNo(String expressOrderNo) {
		this.expressOrderNo = expressOrderNo;
	}

	private Integer b2b_export_state; //审校状态
    
    public String getPatTime() {
		return patTime;
	}

	public void setPatTime(String patTime) {
		this.patTime = patTime;
	}

	public String getReceiptTime() {
		return receiptTime;
	}

	public void setReceiptTime(String receiptTime) {
		this.receiptTime = receiptTime;
	}

	public String getSendSignTime() {
		return sendSignTime;
	}

	public void setSendSignTime(String sendSignTime) {
		this.sendSignTime = sendSignTime;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	private String extVisaInfo;

    public String getExtVisaInfo() {
		return extVisaInfo;
	}

	public void setExtVisaInfo(String extVisaInfo) {
		this.extVisaInfo = extVisaInfo;
	}

	public Set<Integer> getOrderIdSet() {
		return orderIdSet;
	}

	public void setOrderIdSet(Set<Integer> orderIdSet) {
		this.orderIdSet = orderIdSet;
	}

	public List<GroupOrderGuest> getGuests() {
		return guests;
	}

	public void setGuests(List<GroupOrderGuest> guests) {
		this.guests = guests;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getCostCash() {
		return costCash;
	}

	public void setCostCash(BigDecimal costCash) {
		this.costCash = costCash;
	}

	public BigDecimal getCostBalance() {
		return costBalance;
	}

	public void setCostBalance(BigDecimal costBalance) {
		this.costBalance = costBalance;
	}

	public Integer getSourceTypes() {
		return sourceTypes;
	}
   
	public void setSourceTypes(Integer sourceTypes) {
		this.sourceTypes = sourceTypes;
	}
	
    private Integer orderMode;


	public Integer getOrderMode() {
		return orderMode;
	}

	public void setOrderMode(Integer orderMode) {
		this.orderMode = orderMode;
	}

	public String getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(String limitTime) {
        this.limitTime = limitTime;
    }

    public Integer getExtResConfirmId() {
        return extResConfirmId;
    }

    public void setExtResConfirmId(Integer extResConfirmId) {
        this.extResConfirmId = extResConfirmId;
    }

    public String getExtResConfirmName() {
        return extResConfirmName;
    }

    public void setExtResConfirmName(String extResConfirmName) {
        this.extResConfirmName = extResConfirmName;
    }

    public BigDecimal getExtResPrepay() {
        return extResPrepay;
    }

    public void setExtResPrepay(BigDecimal extResPrepay) {
        this.extResPrepay = extResPrepay;
    }

    public Integer getExtResId() {
        return extResId;
    }

    public void setExtResId(Integer extResId) {
        this.extResId = extResId;
    }

    public Integer getExtResState() {
        return extResState;
    }

    public void setExtResState(Integer extResState) {
        this.extResState = extResState;
    }

    public Integer getExtResCleanTime() {
        return extResCleanTime;
    }

    public void setExtResCleanTime(Integer extResCleanTime) {
        this.extResCleanTime = extResCleanTime;
    }

    public Integer getExtResCleanStock() {
        return extResCleanStock;
    }

    public void setExtResCleanStock(Integer extResCleanStock) {
        this.extResCleanStock = extResCleanStock;
    }

    public Integer getNumChildBaby() {
        return numChildBaby;
    }

    public void setNumChildBaby(Integer numChildBaby) {
        this.numChildBaby = numChildBaby;
    }

    public Integer getPerson() {
        return person;
    }

    public void setPerson(Integer person) {
        this.person = person;
    }

    public Integer getDayNums() {
        return dayNums;
    }

    public void setDayNums(Integer dayNums) {
        this.dayNums = dayNums;
    }

    public Integer getAffirmOrderCount() {
        return affirmOrderCount;
    }

    public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	
    public String getAiyouGroupId() {
        return aiyouGroupId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public Integer getAuditUserId() {
        return auditUserId;
    }


    public BigDecimal getBudget() {
        return budget;
    }

    public Integer getCashState() {
        return cashState;
    }

    public List<String> getCerNums() {
        List<String> list = new ArrayList<String>();
        if (this.cerNums != null) {
            String[] cerNums = this.cerNums.split(",");
            for (String string : cerNums) {
                if (string != null && string != "") {
                    list.add(string);
                }
            }
        }
        return list;
    }

    public Integer getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getContactFax() {
        return contactFax;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactTel() {
        return contactTel;
    }

    public String getContractNo() {
        return contractNo;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public String getCreatorDept() {
        return creatorDept;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public Integer getDateType() {
        return dateType;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getFitDate() {
        return fitDate;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public Integer getGroupMode() {
        return groupMode;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public List<GroupOrderGuest> getGroupOrderGuestList() {
        return groupOrderGuestList;
    }

    public List<GroupRoute> getGroupRouteList() {
        return groupRouteList;
    }

    public Integer getGroupState() {
        return groupState;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public String getGuestInfo() {
        return guestInfo;
    }

    public String getGuestName() {
        return guestName;
    }

    public List<String> getGuestNames() {
        List<String> list = new ArrayList<String>();
        if (this.guestNames != null) {
            String[] names = this.guestNames.split(",");
            for (String string : names) {
                if (string != null && string != "") {
                    list.add(string);
                }
            }
        }
        return list;
    }

    public Integer getGuestSourceId() {
        return guestSourceId;
    }

    public String getGuestSourceName() {
        return guestSourceName;
    }

    public String getHotelLevels() {
        return hotelLevels;
    }

    public String getHotelNums() {
        return hotelNums;
    }

    public Integer getId() {
        return id;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public Integer getItemId() {
        return itemId;
    }

    public String getMobile() {
        return mobile;
    }

    public List<String> getMobiles() {
        List<String> list = new ArrayList<String>();
        if (this.mobiles != null) {
            String[] mobiles = this.mobiles.split(",");
            for (String string : mobiles) {
                if (string != null && string != "") {
                    list.add(string);
                }
            }
        }
        return list;
    }

    public Integer getNumAdult() {
        return numAdult;
    }

    public Integer getNumChild() {
        return numChild;
    }

    public Integer getNumGuide() {
        return numGuide;
    }

    public String getNums() {
        return nums;
    }

    public String getOffAir() {
        return offAir;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public String getOperatorIds() {
        return operatorIds;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public Integer getOperType() {
        return operType;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public List<GroupOrder> getOrderList() {
        return orderList;
    }

    public Integer getOrderLockState() {
        return orderLockState;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public Integer getOrderNoSort() {
        return orderNoSort;
    }

    public List<GroupOrderPrice> getOrderPrices() {
        return orderPrices;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public String getOrgIds() {
        return orgIds;
    }

    public String getOrgNames() {
        return orgNames;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPaymentState() {
        return paymentState;
    }

    public Map<String, Object> getPersonMap() {
        return personMap;
    }

    public String getPickUpInfo() {
        return pickUpInfo;
    }

    public BigDecimal getPreTotal() {
        return preTotal;
    }

    public Integer getPriceId() {
        return priceId;
    }

    public String getPriceInfo() {
        return priceInfo;
    }

    public String getPrices() {
        return prices;
    }

    public Integer getProductBrandId() {
        return productBrandId;
    }

    public String getProductBrandName() {
        return productBrandName;
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductShortName() {
        return productShortName;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public Integer getQuartzTime() {
        return quartzTime;
    }

    public String getReceiveMode() {
        return receiveMode;
    }

    public String getRemark() {
        return remark;
    }

    public String getRemarkInternal() {
        return remarkInternal;
    }

    public Integer getReqType() {
        return reqType;
    }

    public Integer getReserveOrderCount() {
        return reserveOrderCount;
    }

    public Integer getRowSpan() {
        return rowSpan;
    }

    public Integer getSaleOperatorId() {
        return saleOperatorId;
    }

    public String getSaleOperatorIds() {
        return saleOperatorIds;
    }

    public String getSaleOperatorName() {
        return saleOperatorName;
    }

    public Integer getSelect() {
        return select;
    }

    public String getServiceStandard() {
        return serviceStandard;
    }

    public BookingShopDetailDeploy getShopDetailDeploy() {
        return shopDetailDeploy;
    }

    public Integer getShowNum() {
        return showNum;
    }

    public String getSourceTypeId() {
        return sourceTypeId;
    }

    public String getSourceTypeName() {
        return sourceTypeName;
    }

    public String getStartTime() {
        return startTime;
    }

    public Integer getState() {
        return state;
    }

    public Integer getStateFinance() {
        return stateFinance;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public int getSupplierCount() {
        return supplierCount;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Integer getTotalAdult() {
        return totalAdult;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public BigDecimal getTotalBudget() {
        return totalBudget;
    }

    public BigDecimal getTotalCash() {
        return totalCash;
    }

    public Integer getTotalChild() {
        return totalChild;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public Integer getTotalNumAdult() {
        return totalNumAdult;
    }

    public Integer getTotalNumChild() {
        return totalNumChild;
    }

    public Integer getTotalOrder() {
        return totalOrder;
    }

    public BigDecimal getTotalOrderIncome() {
        return totalOrderIncome;
    }

    public Integer getTotalOrderNum() {
        return totalOrderNum;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public TourGroup getTourGroup() {
        return tourGroup;
    }

    public String getTourists() {
        return tourists;
    }

    public String getTourState() {
        return tourState;
    }

    public String getTrans() {
        return trans;
    }

    public String getTransferInfo() {
        return transferInfo;
    }

    public Integer getType() {
        return type;
    }

    public String getUpAir() {
        return upAir;
    }

    public void setAffirmOrderCount(Integer affirmOrderCount) {
        this.affirmOrderCount = affirmOrderCount;
    }

    public void setAiyouGroupId(String aiyouGroupId) {
        this.aiyouGroupId = aiyouGroupId;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    public void setAuditUserId(Integer auditUserId) {
        this.auditUserId = auditUserId;
    }

   

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public void setCashState(Integer cashState) {
        this.cashState = cashState;
    }

    public void setCerNums(String cerNums) {
        this.cerNums = cerNums;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setContactFax(String contactFax) {
        this.contactFax = contactFax == null ? null : contactFax.trim();
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile == null ? null : contactMobile.trim();
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel == null ? null : contactTel.trim();
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public void setCreatorDept(String creatorDept) {
        this.creatorDept = creatorDept;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    public void setDateType(Integer dateType) {
        this.dateType = dateType;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setFitDate(String fitDate) {
        this.fitDate = fitDate;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public void setGroupMode(Integer groupMode) {
        this.groupMode = groupMode;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public void setGroupOrderGuestList(List<GroupOrderGuest> groupOrderGuestList) {
        this.groupOrderGuestList = groupOrderGuestList;
    }

    public void setGroupRouteList(List<GroupRoute> groupRouteList) {
        this.groupRouteList = groupRouteList;
    }

    public void setGroupState(Integer groupState) {
        this.groupState = groupState;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public void setGuestInfo(String guestInfo) {
        this.guestInfo = guestInfo;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public void setGuestNames(String guestNames) {
        this.guestNames = guestNames;
    }

    public void setGuestSourceId(Integer guestSourceId) {
        this.guestSourceId = guestSourceId;
    }

    public void setGuestSourceName(String guestSourceName) {
        this.guestSourceName = guestSourceName;
    }

    public void setHotelLevels(String hotelLevels) {
        this.hotelLevels = hotelLevels;
    }

    public void setHotelNums(String hotelNums) {
        this.hotelNums = hotelNums;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles;
    }

    public void setNumAdult(Integer numAdult) {
        this.numAdult = numAdult;
    }

    public void setNumChild(Integer numChild) {
        this.numChild = numChild;
    }

    public void setNumGuide(Integer numGuide) {
        this.numGuide = numGuide;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public void setOffAir(String offAir) {
        this.offAir = offAir;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public void setOperatorIds(String operatorIds) {
        this.operatorIds = operatorIds;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public void setOrderList(List<GroupOrder> orderList) {
        this.orderList = orderList;
    }

    public void setOrderLockState(Integer orderLockState) {
        this.orderLockState = orderLockState;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public void setOrderNoSort(Integer orderNoSort) {
        this.orderNoSort = orderNoSort;
    }

    public void setOrderPrices(List<GroupOrderPrice> orderPrices) {
        this.orderPrices = orderPrices;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public void setOrgIds(String orgIds) {
        this.orgIds = orgIds;
    }

    public void setOrgNames(String orgNames) {
        this.orgNames = orgNames;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPaymentState(Integer paymentState) {
        this.paymentState = paymentState;
    }

    public void setPersonMap(Map<String, Object> personMap) {
        this.personMap = personMap;
    }

    public void setPickUpInfo(String pickUp) {
        this.pickUpInfo = pickUp;
    }

    public void setPreTotal(BigDecimal preTotal) {
        this.preTotal = preTotal;
    }

    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }

    public void setPriceInfo(String priceInfo) {
        this.priceInfo = priceInfo;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public void setProductBrandId(Integer productBrandId) {
        this.productBrandId = productBrandId;
    }

    public void setProductBrandName(String productBrandName) {
        this.productBrandName = productBrandName == null ? null : productBrandName.trim();
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public void setProductShortName(String productShortName) {
        this.productShortName = productShortName;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setQuartzTime(Integer quartzTime) {
        this.quartzTime = quartzTime;
    }

    public void setReceiveMode(String receiveMode) {
        this.receiveMode = receiveMode == null ? null : receiveMode.trim();
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public void setRemarkInternal(String remarkInternal) {
        this.remarkInternal = remarkInternal == null ? null : remarkInternal.trim();
    }

    public void setReqType(Integer reqType) {
        this.reqType = reqType;
    }

    public void setReserveOrderCount(Integer reserveOrderCount) {
        this.reserveOrderCount = reserveOrderCount;
    }

    public void setRowSpan(Integer rowSpan) {
        this.rowSpan = rowSpan;
    }

    public void setSaleOperatorId(Integer saleOperatorId) {
        this.saleOperatorId = saleOperatorId;
    }

    public void setSaleOperatorIds(String saleOperatorIds) {
        this.saleOperatorIds = saleOperatorIds;
    }

    public void setSaleOperatorName(String saleOperatorName) {
        this.saleOperatorName = saleOperatorName == null ? null : saleOperatorName.trim();
    }

    public void setSelect(Integer select) {
        this.select = select;
    }

    public void setServiceStandard(String serviceStandard) {
        this.serviceStandard = serviceStandard == null ? null : serviceStandard.trim();
    }

    public void setShopDetailDeploy(BookingShopDetailDeploy shopDetailDeploy) {
        this.shopDetailDeploy = shopDetailDeploy;
    }

    public void setShowNum(Integer showNum) {
        this.showNum = showNum;
    }

    public void setSourceTypeId(String sourceTypeId) {
        this.sourceTypeId = sourceTypeId;
    }

    public void setSourceTypeName(String sourceTypeName) {
        this.sourceTypeName = sourceTypeName;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setStateFinance(Integer stateFinance) {
        this.stateFinance = stateFinance;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode == null ? null : supplierCode.trim();
    }

    public void setSupplierCount(int supplierCount) {
        this.supplierCount = supplierCount;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setTotalAdult(Integer totalAdult) {
        this.totalAdult = totalAdult;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    public void setTotalBudget(BigDecimal totalBudget) {
        this.totalBudget = totalBudget;
    }

    public void setTotalCash(BigDecimal totalCash) {
        this.totalCash = totalCash;
    }

    public void setTotalChild(Integer totalChild) {
        this.totalChild = totalChild;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public void setTotalNumAdult(Integer totalNumAdult) {
        this.totalNumAdult = totalNumAdult;
    }

    public void setTotalNumChild(Integer totalNumChild) {
        this.totalNumChild = totalNumChild;
    }

    public void setTotalOrder(Integer totalOrder) {
        this.totalOrder = totalOrder;
    }

    public void setTotalOrderIncome(BigDecimal totalOrderIncome) {
        this.totalOrderIncome = totalOrderIncome;
    }

    public void setTotalOrderNum(Integer totalOrderNum) {
        this.totalOrderNum = totalOrderNum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }

    public void setTourGroup(TourGroup tourGroup) {
        this.tourGroup = tourGroup;
    }

    public void setTourists(String tourists) {
        this.tourists = tourists;
    }

    public void setTourState(String tourState) {
        this.tourState = tourState;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public void setTransferInfo(String transfer) {
        this.transferInfo = transfer;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setUpAir(String upAir) {
        this.upAir = upAir;
    }

    public Integer getDay1() {
        return day1;
    }

    public void setDay1(Integer day1) {
        this.day1 = day1;
    }

    public Integer getDay2() {
        return day2;
    }

    public void setDay2(Integer day2) {
        this.day2 = day2;
    }

    public Integer getDay3() {
        return day3;
    }

    public void setDay3(Integer day3) {
        this.day3 = day3;
    }

    public Integer getDay4() {
        return day4;
    }

    public void setDay4(Integer day4) {
        this.day4 = day4;
    }

    public Integer getDay5() {
        return day5;
    }

    public void setDay5(Integer day5) {
        this.day5 = day5;
    }

    public Integer getDay6() {
        return day6;
    }

    public void setDay6(Integer day6) {
        this.day6 = day6;
    }

    public Integer getDay7() {
        return day7;
    }

    public void setDay7(Integer day7) {
        this.day7 = day7;
    }

    public Integer getDay8() {
        return day8;
    }

    public void setDay8(Integer day8) {
        this.day8 = day8;
    }

    public Integer getDay9() {
        return day9;
    }

    public void setDay9(Integer day9) {
        this.day9 = day9;
    }

    public Integer getDay10() {
        return day10;
    }

    public void setDay10(Integer day10) {
        this.day10 = day10;
    }

    public Integer getDay11() {
        return day11;
    }

    public void setDay11(Integer day11) {
        this.day11 = day11;
    }

    public Integer getDay12() {
        return day12;
    }

    public void setDay12(Integer day12) {
        this.day12 = day12;
    }

    public Integer getDay13() {
        return day13;
    }

    public void setDay13(Integer day13) {
        this.day13 = day13;
    }

    public Integer getDay14() {
        return day14;
    }

    public void setDay14(Integer day14) {
        this.day14 = day14;
    }

    public Integer getDay15() {
        return day15;
    }

    public void setDay15(Integer day15) {
        this.day15 = day15;
    }

    public Integer getDay16() {
        return day16;
    }

    public void setDay16(Integer day16) {
        this.day16 = day16;
    }

    public Integer getDay17() {
        return day17;
    }

    public void setDay17(Integer day17) {
        this.day17 = day17;
    }

    public Integer getDay18() {
        return day18;
    }

    public void setDay18(Integer day18) {
        this.day18 = day18;
    }

    public Integer getDay19() {
        return day19;
    }

    public void setDay19(Integer day19) {
        this.day19 = day19;
    }

    public Integer getDay20() {
        return day20;
    }

    public void setDay20(Integer day20) {
        this.day20 = day20;
    }

    public Integer getDay21() {
        return day21;
    }

    public void setDay21(Integer day21) {
        this.day21 = day21;
    }

    public Integer getDay22() {
        return day22;
    }

    public void setDay22(Integer day22) {
        this.day22 = day22;
    }

    public Integer getDay23() {
        return day23;
    }

    public void setDay23(Integer day23) {
        this.day23 = day23;
    }

    public Integer getDay24() {
        return day24;
    }

    public void setDay24(Integer day24) {
        this.day24 = day24;
    }

    public Integer getDay25() {
        return day25;
    }

    public void setDay25(Integer day25) {
        this.day25 = day25;
    }

    public Integer getDay26() {
        return day26;
    }

    public void setDay26(Integer day26) {
        this.day26 = day26;
    }

    public Integer getDay27() {
        return day27;
    }

    public void setDay27(Integer day27) {
        this.day27 = day27;
    }

    public Integer getDay28() {
        return day28;
    }

    public void setDay28(Integer day28) {
        this.day28 = day28;
    }

    public Integer getDay29() {
        return day29;
    }

    public void setDay29(Integer day29) {
        this.day29 = day29;
    }

    public Integer getDay30() {
        return day30;
    }

    public void setDay30(Integer day30) {
        this.day30 = day30;
    }

    public Integer getDay31() {
        return day31;
    }

    public void setDay31(Integer day31) {
        this.day31 = day31;
    }

    /**
     * @return the orderBusiness
     */
    public String getOrderBusiness() {
        return orderBusiness;
    }

    /**
     * @param orderBusiness
     *            the orderBusiness to set
     */
    public void setOrderBusiness(String orderBusiness) {
        this.orderBusiness = orderBusiness;
    }

    public Integer getSumPerson() {
        return sumPerson;
    }

    public void setSumPerson(Integer sumPerson) {
        this.sumPerson = sumPerson;
    }

    public Integer getPushOrderId() {
        return pushOrderId;
    }

    public void setPushOrderId(Integer pushOrderId) {
        this.pushOrderId = pushOrderId;
    }

    public Date getPushTimeUpdate() {
        return pushTimeUpdate;
    }

    public void setPushTimeUpdate(Date pushTimeUpdate) {
        this.pushTimeUpdate = pushTimeUpdate;
    }

    public String getPushSupplierName() {
        return pushSupplierName;
    }

    public void setPushSupplierName(String pushSupplierName) {
        this.pushSupplierName = pushSupplierName;
    }

	public BigDecimal getGroupCost() {
		return groupCost;
	}

	public void setGroupCost(BigDecimal groupCost) {
		this.groupCost = groupCost;
	}
	
	

	public Integer getB2b_export_state() {
		return b2b_export_state;
	}

	public void setB2b_export_state(Integer b2b_export_state) {
		this.b2b_export_state = b2b_export_state;
	}

	public Integer getDepartProvinceId() {
		return departProvinceId;
	}

	public void setDepartProvinceId(Integer departProvinceId) {
		this.departProvinceId = departProvinceId;
	}

	public String getDepartProvinceName() {
		return departProvinceName;
	}

	public void setDepartProvinceName(String departProvinceName) {
		this.departProvinceName = departProvinceName;
	}

	public Integer getDepartCityId() {
		return departCityId;
	}

	public void setDepartCityId(Integer departCityId) {
		this.departCityId = departCityId;
	}

	public String getDepartCityName() {
		return departCityName;
	}

	public void setDepartCityName(String departCityName) {
		this.departCityName = departCityName;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public BigDecimal getOtherTotal() {
		return otherTotal;
	}

	public void setOtherTotal(BigDecimal otherTotal) {
		this.otherTotal = otherTotal;
	}

	public BigDecimal getOtherTotalCash() {
		return otherTotalCash;
	}

	public void setOtherTotalCash(BigDecimal otherTotalCash) {
		this.otherTotalCash = otherTotalCash;
	}

	public BigDecimal getOtherTotalBalance() {
		return otherTotalBalance;
	}

	public void setOtherTotalBalance(BigDecimal otherTotalBalance) {
		this.otherTotalBalance = otherTotalBalance;
	}
	
	
	

}