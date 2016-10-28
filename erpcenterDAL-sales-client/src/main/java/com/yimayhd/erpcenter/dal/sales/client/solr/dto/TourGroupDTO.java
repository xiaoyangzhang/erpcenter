package com.yimayhd.erpcenter.dal.sales.client.solr.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TourGroupDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3898684074431147626L;
	
	/**
	 * 团id
	 */
	private Integer tgGroupId;
	
	/**
	 * 团编号
	 */
	private String  tgGroupCode;
	
	/**
	 * 0时为散客团，>0时为定制团(字典为团类别) -1一地散 -2初始团
	 */
    private Integer tgGroupMode;
    
    /**
     * 供应商名称
     */
    private String gOsupplierName;
    
    /**
     * 出团日期
     */
    private Date tgDateStart;
    
    /**
     * 品牌名称
     */
    private String tgProductBrandName;
    
    /**
     * 产品名称
     */
    private String tgProductName;
    
    /**
     * 成人数量
     */
    private Integer tgTotalAdult;
    
    /**
     * 儿童数量
     */
    private Integer tgTotalChild;
    
    /**
     * 导游数量??
     */
    
    private Integer tgTotalGuide;
    
    /**
     * 
     */
    private String tgOperatorName;
    
    /**
     * 0.未确认  1.已确认（待出团，行程中，已离开） 2.作废  3审核 4封存
     */
    private Integer tgGroupState;
    
    
    private Date tgCreateTime;
    
    /**
     * 计调
     */
    private Integer tgOperatorId;
    
    /**
     * 预算
     */
    private BigDecimal opBudget;
    
    /**
     * 收入
     */
    
    private BigDecimal opIncome;
    
    /**
     * 
     */
    private Integer tgBizId;
    

	public Integer getTgBizId() {
		return tgBizId;
	}

	public void setTgBizId(Integer tgBizId) {
		this.tgBizId = tgBizId;
	}

	public Integer getTgGroupId() {
		return tgGroupId;
	}

	public void setTgGroupId(Integer tgGroupId) {
		this.tgGroupId = tgGroupId;
	}

	public String getTgGroupCode() {
		return tgGroupCode;
	}

	public void setTgGroupCode(String tgGroupCode) {
		this.tgGroupCode = tgGroupCode;
	}

	public Integer getTgGroupMode() {
		return tgGroupMode;
	}

	public void setTgGroupMode(Integer tgGroupMode) {
		this.tgGroupMode = tgGroupMode;
	}

	public String getgOsupplierName() {
		return gOsupplierName;
	}

	public void setgOsupplierName(String gOsupplierName) {
		this.gOsupplierName = gOsupplierName;
	}

	public Date getTgDateStart() {
		return tgDateStart;
	}

	public void setTgDateStart(Date tgDateStart) {
		this.tgDateStart = tgDateStart;
	}

	public String getTgProductBrandName() {
		return tgProductBrandName;
	}

	public void setTgProductBrandName(String tgProductBrandName) {
		this.tgProductBrandName = tgProductBrandName;
	}


	public String getTgProductName() {
		return tgProductName;
	}

	public void setTgProductName(String tgProductName) {
		this.tgProductName = tgProductName;
	}

	public Integer getTgTotalAdult() {
		return tgTotalAdult;
	}

	public void setTgTotalAdult(Integer tgTotalAdult) {
		this.tgTotalAdult = tgTotalAdult;
	}

	public Integer getTgTotalChild() {
		return tgTotalChild;
	}

	public void setTgTotalChild(Integer tgTotalChild) {
		this.tgTotalChild = tgTotalChild;
	}

	public Integer getTgTotalGuide() {
		return tgTotalGuide;
	}

	public void setTgTotalGuide(Integer tgTotalGuide) {
		this.tgTotalGuide = tgTotalGuide;
	}

	public String getTgOperatorName() {
		return tgOperatorName;
	}

	public void setTgOperatorName(String tgOperatorName) {
		this.tgOperatorName = tgOperatorName;
	}

	public Integer getTgGroupState() {
		return tgGroupState;
	}

	public void setTgGroupState(Integer tgGroupState) {
		this.tgGroupState = tgGroupState;
	}

	public Date getTgCreateTime() {
		return tgCreateTime;
	}

	public void setTgCreateTime(Date tgCreateTime) {
		this.tgCreateTime = tgCreateTime;
	}

	public Integer getTgOperatorId() {
		return tgOperatorId;
	}

	public void setTgOperatorId(Integer tgOperatorId) {
		this.tgOperatorId = tgOperatorId;
	}

	public BigDecimal getOpBudget() {
		return opBudget;
	}

	public void setOpBudget(BigDecimal opBudget) {
		this.opBudget = opBudget;
	}

	public BigDecimal getOpIncome() {
		return opIncome;
	}

	public void setOpIncome(BigDecimal opIncome) {
		this.opIncome = opIncome;
	}
    
}
