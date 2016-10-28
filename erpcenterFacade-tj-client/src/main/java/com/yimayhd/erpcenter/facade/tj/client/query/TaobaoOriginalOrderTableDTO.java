package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.taobao.po.PlatTaobaoTrade;

public class TaobaoOriginalOrderTableDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int bizId;
	private PageBean<PlatTaobaoTrade> pageBean;
	public int getBizId() {
		return bizId;
	}
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	public PageBean<PlatTaobaoTrade> getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean<PlatTaobaoTrade> pageBean) {
		this.pageBean = pageBean;
	}
	
	

}
