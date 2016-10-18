package com.yimayhd.erpcenter.dal.sys.po;

import java.util.List;

/**
 * PlatformMenu entity. @author MyEclipse Persistence Tools
 */
/**
 * @author : zhangchao
 * @date : 2015年5月25日 下午4:27:11
 * @Description: 系统菜单实体类
 */
public class PlatformMenuPo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer menuId;
	private Integer sysId;//系统主键
	private Integer bizId;
	private Integer parentId;//父级菜单 自关联
	private String name;//菜单名称
	private String url;//菜单URL
	private String code;//菜单编码
	private String css;//菜单样式
	private Integer resourceType;//0-操作 1-菜单
	private String comment;//描述
	private Integer seqNum;//排序
	private Integer status;//0-不启用 1-启用
	private Integer delStatus;//0-逻辑删除 1-未删除
	private String createTime;//创建时间
	private String updateTime;//更新时间
	
	private String type;//树
	private AdditionalParameters additionalParameters;
	/**
	 * 子菜单或操作列表
	 */
	private List<PlatformMenuPo> childMenuList;

	// Constructors

	/** default constructor */
	public PlatformMenuPo() {
	}

	/** minimal constructor */
	public PlatformMenuPo(Integer sysId, Integer parentId, String name, String url,
			String code, Integer resourceType, Integer seqNum, Integer status,
			Integer delStatus, String createTime, String updateTime) {
		this.sysId = sysId;
		this.parentId = parentId;
		this.name = name;
		this.url = url;
		this.code = code;
		this.resourceType = resourceType;
		this.seqNum = seqNum;
		this.status = status;
		this.delStatus = delStatus;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	/** full constructor */
	public PlatformMenuPo(Integer sysId, Integer parentId, String name, String url,
			String code, Integer resourceType, String comment, Integer seqNum,
			Integer status, Integer delStatus, String createTime,
			String updateTime) {
		this.sysId = sysId;
		this.parentId = parentId;
		this.name = name;
		this.url = url;
		this.code = code;
		this.resourceType = resourceType;
		this.comment = comment;
		this.seqNum = seqNum;
		this.status = status;
		this.delStatus = delStatus;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	// Property accessors

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getSysId() {
		return this.sysId;
	}

	public void setSysId(Integer sysId) {
		this.sysId = sysId;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getSeqNum() {
		return this.seqNum;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDelStatus() {
		return this.delStatus;
	}

	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

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

	public List<PlatformMenuPo> getChildMenuList() {
		return childMenuList;
	}

	public void setChildMenuList(List<PlatformMenuPo> childMenuList) {
		this.childMenuList = childMenuList;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	
}