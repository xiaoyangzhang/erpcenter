package com.yimayhd.erpcenter.facade.tj.client.result;

import java.io.Serializable;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.taobao.po.PlatTaobaoTrade;

public class ImportTaobaoOrderTableResult extends ResultSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PageBean<PlatTaobaoTrade> pageBean;

	public PageBean<PlatTaobaoTrade> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<PlatTaobaoTrade> pageBean) {
		this.pageBean = pageBean;
	}
	
}
