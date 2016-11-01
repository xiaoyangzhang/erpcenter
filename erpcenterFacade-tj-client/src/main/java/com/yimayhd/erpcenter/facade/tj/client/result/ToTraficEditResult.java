package com.yimayhd.erpcenter.facade.tj.client.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.TrafficRes;
import com.yimayhd.erpcenter.dal.product.po.TrafficResLine;

public class ToTraficEditResult extends ResultSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TrafficRes trafficRes;
	private List<TrafficResLine> trafficResLines;
	public TrafficRes getTrafficRes() {
		return trafficRes;
	}
	public void setTrafficRes(TrafficRes trafficRes) {
		this.trafficRes = trafficRes;
	}
	public List<TrafficResLine> getTrafficResLines() {
		return trafficResLines;
	}
	public void setTrafficResLines(List<TrafficResLine> trafficResLines) {
		this.trafficResLines = trafficResLines;
	}
	
	
	
}
