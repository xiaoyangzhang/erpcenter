/**
 * 
 */
package com.yimayhd.erpcenter.facade.ticket.result;

import java.util.ArrayList;
import java.util.List;

import com.yimayhd.erpcenter.dal.sales.client.airticket.bo.AirTicketRequestBO;
import com.yimayhd.erpcenter.dal.sales.client.airticket.bo.AirTicketResourceBO;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;


/**
 * @ClassName: InRecordResult
 * @author hongfei.guo
 * @date 2016年10月19日
 */
public class EditRequestResult extends ResultSupport{

	private static final long serialVersionUID = -5895100754940754874L;
	
	private AirTicketRequestBO bo;
	private AirTicketResourceBO resourceBo;
	private List<GroupOrderGuest> groupGuestList;
	private ArrayList<Boolean> inTicketList;
	
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
	public List<GroupOrderGuest> getGroupGuestList() {
		return groupGuestList;
	}
	public void setGroupGuestList(List<GroupOrderGuest> groupGuestList) {
		this.groupGuestList = groupGuestList;
	}
	public ArrayList<Boolean> getInTicketList() {
		return inTicketList;
	}
	public void setInTicketList(ArrayList<Boolean> inTicketList) {
		this.inTicketList = inTicketList;
	}
}
