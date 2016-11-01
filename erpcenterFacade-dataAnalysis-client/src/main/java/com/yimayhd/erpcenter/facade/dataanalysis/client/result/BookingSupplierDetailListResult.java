package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplierDetail;

public class BookingSupplierDetailListResult extends BaseResult {
	private static final long serialVersionUID = -1830111862209704061L;
	private List<BookingSupplierDetail> detailList;

	public List<BookingSupplierDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<BookingSupplierDetail> detailList) {
		this.detailList = detailList;
	}
}
