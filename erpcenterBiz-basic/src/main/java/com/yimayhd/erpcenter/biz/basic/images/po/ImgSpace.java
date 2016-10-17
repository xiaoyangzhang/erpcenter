package com.yimayhd.erpcenter.biz.basic.images.po;

import java.io.Serializable;
import java.util.Date;

public class ImgSpace implements Serializable {
	
	
    private static final long serialVersionUID = -7434066761371843992L;

	/**
     * id
     */
    private Integer imgId;

    /**
     * 名称
     */
    private String imgName;

    /**
     * 父级ID
     */
    private Integer parentId;

    /**
     * 文件类型(0:目录 1.图片 2.视频 3.文本 4.文件)
     */
    private Integer type;

    /**
     * 文件路径
     */
    private String filePath;

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
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 状态 1可用 0 不可用 -1删除
     */
    private Integer status;
    

    
    public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName == null ? null : imgName.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
	
	@Override
	public String toString() {
		return "ImgSpace [imgId=" + imgId + ", imgName=" + imgName
				+ ", parentId=" + parentId + ", type=" + type + ", filePath="
				+ filePath + ", sysId=" + sysId + ", dealerId=" + dealerId
				+ ", userId=" + userId + ", createTime=" + createTime
				+ ", status=" + status + "]";
	}
    
    
    
}