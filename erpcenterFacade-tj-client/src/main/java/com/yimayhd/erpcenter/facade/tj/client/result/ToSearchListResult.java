package com.yimayhd.erpcenter.facade.tj.client.result;

import java.io.Serializable;
import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.product.po.ProductInfo;

public class ToSearchListResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<DicInfo> brandList;
	private PageBean<ProductInfo> pageBean;
	private Integer page;
	public List<DicInfo> getBrandList() {
		return brandList;
	}
	public void setBrandList(List<DicInfo> brandList) {
		this.brandList = brandList;
	}
	public PageBean<ProductInfo> getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean<ProductInfo> pageBean) {
		this.pageBean = pageBean;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
}
