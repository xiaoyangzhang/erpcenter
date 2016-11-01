package com.yimayhd.erpcenter.facade.tj.client.result;

import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpresource.dal.po.SupplierContractPriceDateInfo;

public class SelectShopProjectListResult extends ResultSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PageBean pageBean;
	private List<SupplierContractPriceDateInfo> priceList;
	private String allTotalPerson;
	private String allTotalFace;
	private String allBuyTotal;
	private String allRepayTotal;
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public List<SupplierContractPriceDateInfo> getPriceList() {
		return priceList;
	}
	public void setPriceList(List<SupplierContractPriceDateInfo> priceList) {
		this.priceList = priceList;
	}
	public String getAllTotalPerson() {
		return allTotalPerson;
	}
	public void setAllTotalPerson(String allTotalPerson) {
		this.allTotalPerson = allTotalPerson;
	}
	public String getAllTotalFace() {
		return allTotalFace;
	}
	public void setAllTotalFace(String allTotalFace) {
		this.allTotalFace = allTotalFace;
	}
	public String getAllBuyTotal() {
		return allBuyTotal;
	}
	public void setAllBuyTotal(String allBuyTotal) {
		this.allBuyTotal = allBuyTotal;
	}
	public String getAllRepayTotal() {
		return allRepayTotal;
	}
	public void setAllRepayTotal(String allRepayTotal) {
		this.allRepayTotal = allRepayTotal;
	}
	
	
	

}
