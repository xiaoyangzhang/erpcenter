package com.yimayhd.erpcenter.facade.sys.result;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.yimayhd.erpcenter.dal.sys.po.PlatformOrgPo;
/**
 * 
 * 描述：用户信息封装返回对象
 * @author liyong
 * 2016年10月20日
 */
public class PlatformEmployeeResult implements Serializable{


	/**
	 * 描述：
	 * @author liyong
	 * 2016年10月20日 
	 */
	private static final long serialVersionUID = 1L;

	private Integer employeeId;
	
	private Integer bizId;
	
	private Integer orgId;
	private String orgName;
	private String roleName;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * 登录名称
	 */
	private String loginName;
	
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 性别
	 */
	private Integer gender;
	/**
	 * 职务
	 */
	private String position;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 传真
	 */
	private String fax;
	
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * qq号
	 */
	private String qqCode;
	/**
	 * 启用状态
	 */
	private Integer status;
	/**
	 * 逻辑删除状态
	 */
	private Integer delStatus;
	/**
	 * 是否是管理员  0-普通用户   1-子系统管理员    2-超级管理员
	 */
	private Integer isSuper;
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	/**
	 * 更新时间
	 */
	private Timestamp updateTime;
	/**
	 * 分页
	 */
	
	private Integer page;
	private Integer pageSize;
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	private String orgIds;
	private List<String> role;
	
	private PlatformOrgPo platformOrgPo;
	
	public PlatformOrgPo getPlatformOrgPo() {
		return platformOrgPo;
	}
	public void setPlatformOrgPo(PlatformOrgPo platformOrgPo) {
		this.platformOrgPo = platformOrgPo;
	}
	public List<String> getRole() {
		return role;
	}
	public void setRole(List<String> role) {
		this.role = role;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQqCode() {
		return qqCode;
	}
	public void setQqCode(String qqCode) {
		this.qqCode = qqCode;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}
	public Integer getIsSuper() {
		return isSuper;
	}
	public void setIsSuper(Integer isSuper) {
		this.isSuper = isSuper;
	}
	
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
}
