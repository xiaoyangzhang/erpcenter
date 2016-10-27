package com.yimayhd.erpcenter.dal.sales.client.operation.po;

import java.io.Serializable;

public class BookingShopEstimate implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4026938882682136999L;

	private Integer id;

    private Integer groupId;

    private Double profitPersonAvg;

    private Double repayRateAvg;

    private Double buyPersonAvg;

    private Double personNum;

    private Double buyTotal;

    private Integer userId;

    private Long createTime;

    private Long updateLastTime;

    private String updateLastUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Double getProfitPersonAvg() {
        return profitPersonAvg;
    }

    public void setProfitPersonAvg(Double profitPersonAvg) {
        this.profitPersonAvg = profitPersonAvg;
    }

    public Double getRepayRateAvg() {
        return repayRateAvg;
    }

    public void setRepayRateAvg(Double repayRateAvg) {
        this.repayRateAvg = repayRateAvg;
    }

    public Double getBuyPersonAvg() {
        return buyPersonAvg;
    }

    public void setBuyPersonAvg(Double buyPersonAvg) {
        this.buyPersonAvg = buyPersonAvg;
    }

    public Double getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Double personNum) {
        this.personNum = personNum;
    }

    public Double getBuyTotal() {
        return buyTotal;
    }

    public void setBuyTotal(Double buyTotal) {
        this.buyTotal = buyTotal;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateLastTime() {
        return updateLastTime;
    }

    public void setUpdateLastTime(Long updateLastTime) {
        this.updateLastTime = updateLastTime;
    }

    public String getUpdateLastUser() {
        return updateLastUser;
    }

    public void setUpdateLastUser(String updateLastUser) {
        this.updateLastUser = updateLastUser == null ? null : updateLastUser.trim();
    }
}