package com.yimayhd.erpcenter.dal.sys.po;

import java.io.Serializable;
import java.util.List;


public class PlatformOrgPo implements Serializable{

	private static final long serialVersionUID = -1673503433537477723L;
	private Integer orgId;
	private Integer sysId;//系统主键
	private Integer bizId;
	private Integer parentId;//自关联 一级部门为0
	private String name;//组织名称
	private String code;//编码
	private Integer seqNum;//同级别显示顺序
	private String comment;//组织机构描述
	private boolean status;//-不启用 1-启用
	private boolean delStatus;//0-逻辑删除 1-未删除
	private String createTime;//创建时间
	private String updateTime;//更新时间
	private String type;//树
	private String logo;
	//private List<dep>
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	private String  orgPath;
	
	public String getOrgPath() {
		return orgPath;
	}
	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}
	private AdditionalParameters additionalParameters;
	
	 
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public AdditionalParameters getAdditionalParameters() {
		return additionalParameters;
	}
	public void setAdditionalParameters(AdditionalParameters additionalParameters) {
		this.additionalParameters = additionalParameters;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getSysId() {
		return sysId;
	}
	public void setSysId(Integer sysId) {
		this.sysId = sysId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getSeqNum() {
		return seqNum;
	}
	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean isDelStatus() {
		return delStatus;
	}
	public void setDelStatus(boolean delStatus) {
		this.delStatus = delStatus;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
}
