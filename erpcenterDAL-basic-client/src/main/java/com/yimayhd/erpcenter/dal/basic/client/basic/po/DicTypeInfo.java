package com.yimayhd.erpcenter.dal.basic.client.basic.po;

import java.io.Serializable;

public class DicTypeInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3394962747623902106L;
	private Integer id;
	private String code;
	private String value;
	private Integer status;
	private String note;
	private Integer pid;
	private String treePath;
	private Integer level;
	private Integer shareStatus;
	
	private int orderId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getTreePath() {
		return treePath;
	}
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Integer getShareStatus() {
		if(shareStatus==null){
			shareStatus=0;
		}
		return shareStatus;
	}
	public void setShareStatus(Integer shareStatus) {
		this.shareStatus = shareStatus;
	}
	
}
