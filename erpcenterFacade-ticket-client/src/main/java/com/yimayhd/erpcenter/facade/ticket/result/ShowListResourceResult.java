/**
 * 
 */
package com.yimayhd.erpcenter.facade.ticket.result;

import java.util.ArrayList;
import java.util.HashMap;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.airticket.bo.AirTicketResourceBO;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketResource;


/**
 * @ClassName: InRecordResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class ShowListResourceResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;
	
	private String thisPage;
	private PageBean<AirTicketResource> pageBean;
	private ArrayList<AirTicketResourceBO> boList;
	private HashMap<String, Integer> count;
	
	public String getThisPage() {
		return thisPage;
	}
	public void setThisPage(String thisPage) {
		this.thisPage = thisPage;
	}
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
	public HashMap<String, Integer> getCount() {
		return count;
	}
	public void setCount(HashMap<String, Integer> count) {
		this.count = count;
	}
}
