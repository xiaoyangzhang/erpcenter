package com.yimayhd.erpcenter.facade.sales.result.grouporder;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingSupplier;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupGuidePrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupRouteDayVO;
import com.yimayhd.erpcenter.facade.sales.result.BaseStateResult;

public class PreviewFitGuideResult extends BaseStateResult {
	private static final long serialVersionUID = -6507897654467992391L;
	private String guideString;
	private String driverString;
	private TourGroup tourGroup;
	
	private String operatorMobile;
	private List<GroupOrderPrintPo> gopps;
	
	private List<GroupGuidePrintPo> pos;
	private List<GroupRouteDayVO> routeDayVOList;
	
	private List<BookingSupplier> hotelList;

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

	public List<GroupOrderPrintPo> getGopps() {
		return gopps;
	}

	public void setGopps(List<GroupOrderPrintPo> gopps) {
		this.gopps = gopps;
	}

	public List<GroupGuidePrintPo> getPos() {
		return pos;
	}

	public void setPos(List<GroupGuidePrintPo> pos) {
		this.pos = pos;
	}

	public List<GroupRouteDayVO> getRouteDayVOList() {
		return routeDayVOList;
	}

	public void setRouteDayVOList(List<GroupRouteDayVO> routeDayVOList) {
		this.routeDayVOList = routeDayVOList;
	}

	public List<BookingSupplier> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<BookingSupplier> hotelList) {
		this.hotelList = hotelList;
	}
}
