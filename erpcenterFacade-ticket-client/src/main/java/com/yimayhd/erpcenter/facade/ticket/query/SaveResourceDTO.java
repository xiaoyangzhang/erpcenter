package com.yimayhd.erpcenter.facade.ticket.query;

import java.io.Serializable;
import java.util.Date;

import com.yimayhd.erpcenter.dal.sales.client.airticket.po.AirTicketResource;

public class SaveResourceDTO implements Serializable{
	
	private static final long serialVersionUID = 6707676794554415017L;
	
	private Integer bizId;
	private Date endIssueTime;
	private String saveTemplate;
	private String depCityFirst;
	private Date depDateFirst;
	private Integer employeeId;
	private String employeeName;
	private AirTicketResource airTicketResourcePo;
	private String legList;
	
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Date getEndIssueTime() {
		return endIssueTime;
	}
	public void setEndIssueTime(Date endIssueTime) {
		this.endIssueTime = endIssueTime;
	}
	public String getSaveTemplate() {
		return saveTemplate;
	}
	public void setSaveTemplate(String saveTemplate) {
		this.saveTemplate = saveTemplate;
	}
	public String getDepCityFirst() {
		return depCityFirst;
	}
	public void setDepCityFirst(String depCityFirst) {
		this.depCityFirst = depCityFirst;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public AirTicketResource getAirTicketResourcePo() {
		return airTicketResourcePo;
	}
	public void setAirTicketResourcePo(AirTicketResource airTicketResourcePo) {
		this.airTicketResourcePo = airTicketResourcePo;
	}
	public String getLegList() {
		return legList;
	}
	public void setLegList(String legList) {
		this.legList = legList;
	}
	public Date getDepDateFirst() {
		return depDateFirst;
	}
	public void setDepDateFirst(Date depDateFirst) {
		this.depDateFirst = depDateFirst;
	}
}
