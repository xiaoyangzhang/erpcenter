package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

public class PreviewFitTransferResult implements Serializable {
	private static final long serialVersionUID = -5474445530545179886L;
	private String DeliveryDetail;
	private List<GroupOrderPrintPo> gopps;
	private String total;
	private String imgPath;
	private TourGroup tourGroup;
	private String guideString = "";
	private String driverString = "";
	private String operatormobile;

	public String getOperatormobile() {
		return operatormobile;
	}

	public void setOperatormobile(String operatormobile) {
		this.operatormobile = operatormobile;
	}

	public String getDeliveryDetail() {
		return DeliveryDetail;
	}

	public void setDeliveryDetail(String deliveryDetail) {
		DeliveryDetail = deliveryDetail;
	}

	public List<GroupOrderPrintPo> getGopps() {
		return gopps;
	}

	public void setGopps(List<GroupOrderPrintPo> gopps) {
		this.gopps = gopps;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public TourGroup getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
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
}
