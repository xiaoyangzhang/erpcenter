package com.yimayhd.erpcenter.facade.tj.client.result;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.erpcenter.dal.product.po.TrafficRes;
import com.yimayhd.erpcenter.dal.product.po.TrafficResProduct;

public class ToUpdateResNumStockStateResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TrafficRes trafficResBean;
	private List<TrafficResProduct> resBindingProList;
	private TrafficResProduct sumResProBean;
	public TrafficRes getTrafficResBean() {
		return trafficResBean;
	}
	public void setTrafficResBean(TrafficRes trafficResBean) {
		this.trafficResBean = trafficResBean;
	}
	public List<TrafficResProduct> getResBindingProList() {
		return resBindingProList;
	}
	public void setResBindingProList(List<TrafficResProduct> resBindingProList) {
		this.resBindingProList = resBindingProList;
	}
	public TrafficResProduct getSumResProBean() {
		return sumResProBean;
	}
	public void setSumResProBean(TrafficResProduct sumResProBean) {
		this.sumResProBean = sumResProBean;
	}
	

}
