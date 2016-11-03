package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import java.math.BigDecimal;
import java.util.HashMap;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingAirTicket;

public class GetAirTicketDetailResult extends BaseResult {
	private static final long serialVersionUID = 6624613992124918218L;
	private PageBean<BookingAirTicket> pageBean;
	private HashMap<String, BigDecimal> sum;

	public PageBean<BookingAirTicket> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<BookingAirTicket> pageBean) {
		this.pageBean = pageBean;
	}

	public HashMap<String, BigDecimal> getSum() {
		return sum;
	}

	public void setSum(HashMap<String, BigDecimal> sum) {
		this.sum = sum;
	}
}
