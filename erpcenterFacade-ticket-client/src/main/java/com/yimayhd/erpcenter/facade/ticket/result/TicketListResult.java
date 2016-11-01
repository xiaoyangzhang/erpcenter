/**
 * 
 */
package com.yimayhd.erpcenter.facade.ticket.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.airticket.bo.AirTicketRequestBO;
import com.yimayhd.erpcenter.dal.sales.client.airticket.bo.AirTicketResourceBO;


/**
 * @ClassName: InRecordResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class TicketListResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;
	
	private AirTicketResourceBO resourceBo;
	private List<AirTicketRequestBO> boList;
	
	public AirTicketResourceBO getResourceBo() {
		return resourceBo;
	}
	public void setResourceBo(AirTicketResourceBO resourceBo) {
		this.resourceBo = resourceBo;
	}
	public List<AirTicketRequestBO> getBoList() {
		return boList;
	}
	public void setBoList(List<AirTicketRequestBO> boList) {
		this.boList = boList;
	}
	
	
}
