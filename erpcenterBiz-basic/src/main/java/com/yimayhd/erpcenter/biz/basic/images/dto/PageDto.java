package com.yimayhd.erpcenter.biz.basic.images.dto;

import java.io.Serializable;

public class PageDto implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4903851028558204519L;
	
	   /**
     * 站点Code
     */
    private String sysId;

    /**
     * 商户ID
     */
    private Integer dealerId;

    /**
     * 所属用户ID
     */
    private Integer userId;
    
    /**
     * 文件类型(0:目录 1.图片 2.视频 3.文本 4.文件)
     */
    private Integer type=1;
    
    /**
     * 状态 1可用 0 不可用 -1删除
     */
    private Integer status=1;
    
    
    /**
     * 分页开始数
     */
    private Integer start=0;
    
    
    /**
     * 分页大小20
     */
    private Integer size=20;
    

    public PageDto() {
		super();
	}

	public PageDto(String sysId, Integer dealerId, Integer userId,
			Integer type, Integer status, Integer start, Integer size) {
		super();
		this.sysId = sysId;
		this.dealerId = dealerId;
		this.userId = userId;
		this.type = type;
		this.status = status;
		this.start = start;
		this.size = size;
	}
    
	public String getSysId() {
		return sysId;
	}


	public void setSysId(String sysId) {
		this.sysId = sysId;
	}


	public Integer getDealerId() {
		return dealerId;
	}


	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Integer getStart() {
		return start;
	}


	public void setStart(Integer start) {
		this.start = start;
	}


	public Integer getSize() {
		return size;
	}


	public void setSize(Integer size) {
		this.size = size;
	}
    

}
