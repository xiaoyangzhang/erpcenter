package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class PreviewGuestWithTransResult extends BaseStateResult {
	private static final long serialVersionUID = 3784053335478246445L;
	private TourGroup tourGroup;
	private String operatorMobile;
	private String guideString = "";
	private String driverString = "";
	private String total;
	private List<GroupOrderPrintPo> gopps;

	public TourGroup getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}

	public String getOperatorMobile() {
		return operatorMobile;
	}

	public void setOperatorMobile(String operatorMobile) {
		this.operatorMobile = operatorMobile;
	}

	public String getGuideString() {
		return guideString;
	}

	public void setGuideString(String guideString) {
		this.guideString = guideString;
	}

	public String getDriverString() {
		return driverString;
	}

	public void setDriverString(String driverString) {
		this.driverString = driverString;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<GroupOrderPrintPo> getGopps() {
		return gopps;
	}

	public void setGopps(List<GroupOrderPrintPo> gopps) {
		this.gopps = gopps;
	}
}
