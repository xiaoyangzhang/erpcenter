/**
 * 
 */
package com.yimayhd.erpcenter.facade.ticket.result;

import java.util.ArrayList;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.airticket.bo.AirTicketResourceBO;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketResource;


/**
 * @ClassName: InRecordResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class ShowResourceResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;
	
	private PageBean<AirTicketResource> pageBean;
	private ArrayList<AirTicketResourceBO> boList;
	
	public PageBean<AirTicketResource> getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean<AirTicketResource> pageBean) {
		this.pageBean = pageBean;
	}
	public ArrayList<AirTicketResourceBO> getBoList() {
		return boList;
	}
	public void setBoList(ArrayList<AirTicketResourceBO> boList) {
		this.boList = boList;
	}
}
