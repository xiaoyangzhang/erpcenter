package com.yimayhd.erpcenter.facade.operation.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;

public class AYToAddSightResult  extends ResultSupport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6274711634344244757L;
	
	private TourGroup tourGroup;
	
	private List<DicInfo> hotelType1;
	
	private List<DicInfo> resTypes;
	
	private List<DicInfo> CarTypes;

	public TourGroup getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(TourGroup tourGroup) {
		this.tourGroup = tourGroup;
	}

	public List<DicInfo> getHotelType1() {
		return hotelType1;
	}

	public void setHotelType1(List<DicInfo> hotelType1) {
		this.hotelType1 = hotelType1;
	}

	public List<DicInfo> getResTypes() {
		return resTypes;
	}

	public void setResTypes(List<DicInfo> resTypes) {
		this.resTypes = resTypes;
	}

	public List<DicInfo> getCarTypes() {
		return CarTypes;
	}

	public void setCarTypes(List<DicInfo> carTypes) {
		CarTypes = carTypes;
	}
	
	
	

}
