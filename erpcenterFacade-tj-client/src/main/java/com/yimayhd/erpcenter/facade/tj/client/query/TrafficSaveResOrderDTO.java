package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.SpecialGroupOrderVO;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;

public class TrafficSaveResOrderDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SpecialGroupOrderVO vo;
	private String id;
	private String ids;
	private Integer groupMode;
	private Integer trpId;
	private Integer see;
	private int bizId;
	private PlatformEmployeePo curUser;
	private int userId;
	private String orderNo;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public PlatformEmployeePo getCurUser() {
		return curUser;
	}
	public void setCurUser(PlatformEmployeePo curUser) {
		this.curUser = curUser;
	}
	public int getBizId() {
		return bizId;
	}
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	public SpecialGroupOrderVO getVo() {
		return vo;
	}
	public void setVo(SpecialGroupOrderVO vo) {
		this.vo = vo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getGroupMode() {
		return groupMode;
	}
	public void setGroupMode(Integer groupMode) {
		this.groupMode = groupMode;
	}
	public Integer getTrpId() {
		return trpId;
	}
	public void setTrpId(Integer trpId) {
		this.trpId = trpId;
	}
	public Integer getSee() {
		return see;
	}
	public void setSee(Integer see) {
		this.see = see;
	}
	
}
