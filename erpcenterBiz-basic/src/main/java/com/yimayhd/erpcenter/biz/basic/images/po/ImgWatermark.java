package com.yimayhd.erpcenter.biz.basic.images.po;

import java.io.Serializable;
import java.util.Date;

public class ImgWatermark implements Serializable {
	
	
    private static final long serialVersionUID = -5055319737032228223L;

	/**
     * 水印ID
     */
    private Integer watermarkId;

    /**
     * 名称
     */
    private String name;

    /**
     * 水印路径
     */
    private String filePath;

    /**
     * 透明度
     */
    private Integer alpha;

    /**
     * 基准点(1: 左上 2右下 3 中间)
     */
    private Integer fiducialPoint;

    /**
     * 上边距
     */
    private Integer marginTop;

    /**
     * 下边距
     */
    private Integer marginBottom;

    /**
     * 系统站点ID
     */
    private String sysId;

    /**
     * 商户id
     */
    private Integer dealerId;

    /**
     * 所属用户ID
     */
    private Integer userId;

    /**
     * 最后更新时间
     */
    private Date lastModifiedTime;
    /**
     * 水印图片width
     */
    private Integer iconImageWidth;
    /**
     * 水印图片Height
     */
    private Integer iconImageHeight;

    /**
     * 状态 1启用 0 不启用 
     */
    private Integer status;
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public Integer getAlpha() {
        return alpha;
    }

    public void setAlpha(Integer alpha) {
        this.alpha = alpha;
    }

    public Integer getFiducialPoint() {
        return fiducialPoint;
    }

    public void setFiducialPoint(Integer fiducialPoint) {
        this.fiducialPoint = fiducialPoint;
    }

    public Integer getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(Integer marginTop) {
        this.marginTop = marginTop;
    }

    public Integer getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(Integer marginBottom) {
        this.marginBottom = marginBottom;
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public Integer getWatermarkId() {
		return watermarkId;
	}

	public void setWatermarkId(Integer watermarkId) {
		this.watermarkId = watermarkId;
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

	public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    
	public Integer getIconImageWidth() {
		return iconImageWidth;
	}

	public void setIconImageWidth(Integer iconImageWidth) {
		this.iconImageWidth = iconImageWidth;
	}

	public Integer getIconImageHeight() {
		return iconImageHeight;
	}

	public void setIconImageHeight(Integer iconImageHeight) {
		this.iconImageHeight = iconImageHeight;
	}

	@Override
	public String toString() {
		return "ImgWatermark [watermarkId=" + watermarkId + ", name=" + name
				+ ", filePath=" + filePath + ", alpha=" + alpha
				+ ", fiducialPoint=" + fiducialPoint + ", marginTop="
				+ marginTop + ", marginBottom=" + marginBottom + ", sysId="
				+ sysId + ", dealerId=" + dealerId + ", userId=" + userId
				+ ", lastModifiedTime=" + lastModifiedTime
				+ ", iconImageWidth=" + iconImageWidth + ", iconImageHeight="
				+ iconImageHeight + ", status=" + status + "]";
	}

	
    
    
}