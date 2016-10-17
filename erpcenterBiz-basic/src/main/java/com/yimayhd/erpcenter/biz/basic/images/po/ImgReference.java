package com.yimayhd.erpcenter.biz.basic.images.po;

import java.io.Serializable;

public class ImgReference implements Serializable {
	
	
    private static final long serialVersionUID = 2726851136801937924L;

	/**
     * 引用id
     */
    private Long referenceId;

    /**
     * 图片ID
     */
    private Long imgId;

    /**
     * 图片url
     */
    private String imgUrl;

    /**
     * 引用目标ID
     */
    private Long targetId;

    /**
     * 引用对应的目标名
     */
    private String targetName;

    /**
     * 引用目标链接
     */
    private String targetUrl;
  
    /**
     * 图片被引用的类型
     */
    private String type;

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName == null ? null : targetName.trim();
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl == null ? null : targetUrl.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

	@Override
	public String toString() {
		return "ImgReference [referenceId=" + referenceId + ", imgId=" + imgId
				+ ", imgUrl=" + imgUrl + ", targetId=" + targetId
				+ ", targetName=" + targetName + ", targetUrl=" + targetUrl
				+ ", type=" + type + "]";
	}
    
    
    
}