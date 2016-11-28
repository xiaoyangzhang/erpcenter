package com.yimayhd.erpcenter.facade.tj.client.result;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.taobao.po.PlatTaobaoTrade;

public class PresellProductStatisticsListResult extends ResultSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3499221712439278922L;

	private PageBean<PlatTaobaoTrade> pageBean ;

	public PageBean<PlatTaobaoTrade> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<PlatTaobaoTrade> pageBean) {
		this.pageBean = pageBean;
	}
	
	
}
