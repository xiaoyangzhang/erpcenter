package com.yimayhd.erpcenter.common.po;

import java.io.Serializable;
import java.util.List;

public class LogOperator implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9148499146430592183L;
	private Integer id;
	private Integer bizId;
	private Integer batchId;
	private String action;
	private String tableName;
	private Integer tableId;
	private Integer tableParentId;
	private String logText;
	private String logBrief;
	private String editUser;
	private String editTime;
	
	//以下两个属性用于返回查询明细时使用
	private String tableTitle;
	private List<String> listDetail;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}

	public Integer getTableId() {
		return tableId;
	}
	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	public String getLogText() {
		return logText;
	}
	public void setLogText(String logText) {
		this.logText = logText;
	}
	public String getEditUser() {
		return editUser;
	}
	public void setEditUser(String editUser) {
		this.editUser = editUser;
	}
	public String getEditTime() {
		return editTime;
	}
	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getLogBrief() {
		return logBrief;
	}
	public void setLogBrief(String logBrief) {
		this.logBrief = logBrief;
	}
	
	public Integer getBatchId() {
		return batchId;
	}
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
	public List<String> getListDetail() {
		return listDetail;
	}
	public void setListDetail(List<String> listDetail) {
		this.listDetail = listDetail;
	}
	public String getTableTitle() {
		return tableTitle;
	}
	public void setTableTitle(String tableTitle) {
		this.tableTitle = tableTitle;
	}
	public Integer getTableParentId() {
		return tableParentId;
	}
	public void setTableParentId(Integer tableParentId) {
		this.tableParentId = tableParentId;
	}



}
