package com.yimayhd.erpcenter.facade.dataanalysis.client.query;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;

public class ShopSelectListDTO extends BaseDTO {
	private static final long serialVersionUID = -8889481280036904592L;
	private TourGroupVO group;

	public TourGroupVO getGroup() {
		return group;
	}

	public void setGroup(TourGroupVO group) {
		this.group = group;
	}
}
