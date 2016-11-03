/**
 * 
 */
package com.yimayhd.erpcenter.facade.ticket.result;

import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.airticket.bo.AirTicketResourceBO;
import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketLeg;


/**
 * @ClassName: InRecordResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class EditResourceResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;
	
	private AirTicketResourceBO resourceBo;
	private List<AirTicketLeg> legList;
	private List<String> templateNames;

	public AirTicketResourceBO getResourceBo() {
		return resourceBo;
	}
	public void setResourceBo(AirTicketResourceBO resourceBo) {
		this.resourceBo = resourceBo;
	}
	public List<AirTicketLeg> getLegList() {
		return legList;
	}
	public void setLegList(List<AirTicketLeg> legList) {
		this.legList = legList;
	}
	public List<String> getTemplateNames() {
		return templateNames;
	}
	public void setTemplateNames(List<String> templateNames) {
		this.templateNames = templateNames;
	}
}
