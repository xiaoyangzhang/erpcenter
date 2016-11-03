/**
 * 
 */
package com.yimayhd.erpcenter.facade.ticket.result;

import com.yimayhd.erpcenter.dal.sales.client.airticket.bo.AirTicketRequestBO;
import com.yimayhd.erpcenter.dal.sales.client.airticket.bo.AirTicketResourceBO;


/**
 * @ClassName: InRecordResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class PickUpGuestResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;
	
	private AirTicketRequestBO bo;
	private AirTicketResourceBO resourceBo;
	private String existingTransport;
	
	public AirTicketRequestBO getBo() {
		return bo;
	}
	public void setBo(AirTicketRequestBO bo) {
		this.bo = bo;
	}
	public AirTicketResourceBO getResourceBo() {
		return resourceBo;
	}
	public void setResourceBo(AirTicketResourceBO resourceBo) {
		this.resourceBo = resourceBo;
	}
	public String getExistingTransport() {
		return existingTransport;
	}
	public void setExistingTransport(String existingTransport) {
		this.existingTransport = existingTransport;
	}
	
	
}
