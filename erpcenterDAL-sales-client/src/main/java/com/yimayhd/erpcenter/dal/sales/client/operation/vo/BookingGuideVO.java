package com.yimayhd.erpcenter.dal.sales.client.operation.vo;

import java.io.Serializable;
import java.util.List;

import com.yihg.operation.po.BookingGuide;
import com.yihg.operation.po.BookingGuideTimes;

public class BookingGuideVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7324580621862604444L;

	private Integer groupId ;
	private List<BookingGuidesVO> guideVOs;
	
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public List<BookingGuidesVO> getGuideVOs() {
		return guideVOs;
	}
	public void setGuideVOs(List<BookingGuidesVO> guideVOs) {
		this.guideVOs = guideVOs;
	}
	
	
	
	
	
	
}
