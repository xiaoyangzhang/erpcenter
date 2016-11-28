package com.yimayhd.erpcenter.facade.sales.result;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class ProfitEverifyTableResult extends ResultSupport {
    private Integer pageTotalAudit;
    private Integer pageTotalChild;
    private Integer pageTotalGuide;
    private BigDecimal sum_total;
    private BigDecimal sum_qdtotal;
    private BigDecimal sum_budget;
    private BigDecimal sum_g_profit;
    private PageBean<GroupOrder> pageBean;
    private String numberPeople;
    private BigDecimal z_sum_total;
    private BigDecimal z_sum_qdtotal;
    private BigDecimal z_sum_budget;
    private BigDecimal z_sum_profit;
    private List<DicInfo> typeList;
    private boolean groupOrderTatol;

    public boolean isGroupOrderTatol() {
        return groupOrderTatol;
    }

    public void setGroupOrderTatol(boolean groupOrderTatol) {
        this.groupOrderTatol = groupOrderTatol;
    }

    public Integer getPageTotalAudit() {
        return pageTotalAudit;
    }

    public void setPageTotalAudit(Integer pageTotalAudit) {
        this.pageTotalAudit = pageTotalAudit;
    }

    public Integer getPageTotalChild() {
        return pageTotalChild;
    }

    public void setPageTotalChild(Integer pageTotalChild) {
        this.pageTotalChild = pageTotalChild;
    }

    public Integer getPageTotalGuide() {
        return pageTotalGuide;
    }

    public void setPageTotalGuide(Integer pageTotalGuide) {
        this.pageTotalGuide = pageTotalGuide;
    }

    public BigDecimal getSum_total() {
        return sum_total;
    }

    public void setSum_total(BigDecimal sum_total) {
        this.sum_total = sum_total;
    }

    public BigDecimal getSum_qdtotal() {
        return sum_qdtotal;
    }

    public void setSum_qdtotal(BigDecimal sum_qdtotal) {
        this.sum_qdtotal = sum_qdtotal;
    }

    public BigDecimal getSum_budget() {
        return sum_budget;
    }

    public void setSum_budget(BigDecimal sum_budget) {
        this.sum_budget = sum_budget;
    }

    public BigDecimal getSum_g_profit() {
        return sum_g_profit;
    }

    public void setSum_g_profit(BigDecimal sum_g_profit) {
        this.sum_g_profit = sum_g_profit;
    }

    public PageBean<GroupOrder> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<GroupOrder> pageBean) {
        this.pageBean = pageBean;
    }

    public String getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(String numberPeople) {
        this.numberPeople = numberPeople;
    }

    public BigDecimal getZ_sum_total() {
        return z_sum_total;
    }

    public void setZ_sum_total(BigDecimal z_sum_total) {
        this.z_sum_total = z_sum_total;
    }

    public BigDecimal getZ_sum_qdtotal() {
        return z_sum_qdtotal;
    }

    public void setZ_sum_qdtotal(BigDecimal z_sum_qdtotal) {
        this.z_sum_qdtotal = z_sum_qdtotal;
    }

    public BigDecimal getZ_sum_budget() {
        return z_sum_budget;
    }

    public void setZ_sum_budget(BigDecimal z_sum_budget) {
        this.z_sum_budget = z_sum_budget;
    }

    public BigDecimal getZ_sum_profit() {
        return z_sum_profit;
    }

    public void setZ_sum_profit(BigDecimal z_sum_profit) {
        this.z_sum_profit = z_sum_profit;
    }

    public List<DicInfo> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<DicInfo> typeList) {
        this.typeList = typeList;
    }
}
