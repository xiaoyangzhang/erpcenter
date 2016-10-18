package com.yimayhd.erpcenter.dal.product.vo;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.TrafficRes;
import com.yimayhd.erpcenter.dal.product.po.TrafficResLine;
import com.yimayhd.erpcenter.dal.product.po.TrafficResProduct;
import com.yimayhd.erpcenter.dal.product.po.TrafficResStocklog;


public class TrafficResVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8058619776940114346L;
	
	private TrafficRes trafficRes;
	private List<TrafficResLine> trafficResLine;
	private List<TrafficResProduct> trafficResproduct;
	private List<TrafficResStocklog> trafficResStocklog;
	
	public TrafficRes getTrafficRes() {
		return trafficRes;
	}
	public void setTrafficRes(TrafficRes trafficRes) {
		this.trafficRes = trafficRes;
	}
	public List<TrafficResLine> getTrafficResLine() {
		return trafficResLine;
	}
	public void setTrafficResLine(List<TrafficResLine> trafficResLine) {
		this.trafficResLine = trafficResLine;
	}
	public List<TrafficResProduct> getTrafficResproduct() {
		return trafficResproduct;
	}
	public void setTrafficResproduct(List<TrafficResProduct> trafficResproduct) {
		this.trafficResproduct = trafficResproduct;
	}
	public List<TrafficResStocklog> getTrafficResStocklog() {
		return trafficResStocklog;
	}
	public void setTrafficResStocklog(List<TrafficResStocklog> trafficResStocklog) {
		this.trafficResStocklog = trafficResStocklog;
	}
	
	
}
