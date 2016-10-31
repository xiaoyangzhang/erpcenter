package com.yimayhd.erpcenter.facade.sales.query.grouporder;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TransportVO;

public class AddManyGroupOrderTransportDTO implements Serializable {
	private static final long serialVersionUID = -5386474759370623657L;
	private TransportVO transportVO;

	public TransportVO getTransportVO() {
		return transportVO;
	}

	public void setTransportVO(TransportVO transportVO) {
		this.transportVO = transportVO;
	}

}
