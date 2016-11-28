package com.yimayhd.erpcenter.facade.tj.client.query;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.taobao.po.PlatTaobaoTrade;
import com.yimayhd.erpcenter.facade.tj.client.result.ResultSupport;

public class PresellProductStatistics extends ResultSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1395456923766700860L;
	
	private PageBean<PlatTaobaoTrade> pageBean;
	
	private Integer bizId;

	public PageBean<PlatTaobaoTrade> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<PlatTaobaoTrade> pageBean) {
		this.pageBean = pageBean;
	}

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	
	

}
