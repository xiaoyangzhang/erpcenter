package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import com.yihg.mybatis.utility.PageBean;

public class HotelDetailPreviewResult extends BaseResult{
	private static final long serialVersionUID = -1914400009732870768L;
	private PageBean pb;

	public PageBean getPb() {
		return pb;
	}

	public void setPb(PageBean pb) {
		this.pb = pb;
	}
}
