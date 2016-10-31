/**
 * 
 */
package com.yimayhd.erpcenter.facade.ticket.result;

import java.util.ArrayList;
import java.util.HashMap;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.airticket.bo.AirTicketRequestBO;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketRequest;


/**
 * @ClassName: InRecordResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class ShowListResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;
	
	private HashMap<String, Integer> count;
	private PageBean<AirTicketRequest> pageBean;
	private ArrayList<AirTicketRequestBO> resultBo;
	private boolean isArrange;
	
	public HashMap<String, Integer> getCount() {
		return count;
	}
	public void setCount(HashMap<String, Integer> count) {
		this.count = count;
	}
	public PageBean<AirTicketRequest> getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean<AirTicketRequest> pageBean) {
		this.pageBean = pageBean;
	}
	public ArrayList<AirTicketRequestBO> getResultBo() {
		return resultBo;
	}
	public void setResultBo(ArrayList<AirTicketRequestBO> resultBo) {
		this.resultBo = resultBo;
	}
	public boolean isArrange() {
		return isArrange;
	}
	public void setArrange(boolean isArrange) {
		this.isArrange = isArrange;
	}
	
}
