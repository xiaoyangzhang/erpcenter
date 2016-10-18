package com.yimayhd.erpcenter.dal.sys.po;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserSession implements Serializable{
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 用户类型
	 */
	private int isSuper;
	/**
	 * 用户id
	 */
	private int employeeId;
	/**
	 * 当前登录用户的站点代码
	 */
	private String siteCode;
	
	/**
	 * 当前商家信息
	 */
	private SysBizInfo bizInfo; 
	/**
	 * 当前用户信息
	 */
	private PlatformEmployeePo employeeInfo;
	
	/**
	 * 组织机构信息
	 */
	private PlatformOrgPo orgInfo;
	
	/**
	 * 组织机构信息
	 */
	//private List<PlatformOrgPo>  orgList;
	/**
	 * 角色信息
	 */
	private List<PlatformRolePo>  roleList;
	/**
	 * 菜单权限信息
	 */
	private List<PlatformMenuPo>  menuList;
	
	private Map<String,Map<String,Boolean>> menuOptMap;
	/**
	 * 操作标识map
	 */
	private Map<String,Boolean> optMap;
	
	private Set<Integer> dataUserIdSet;	
	
	private Map<String,String> bizConfigMap;
	
	public Map<String, Map<String, Boolean>> getMenuOptMap() {
		return menuOptMap;
	}
	public void setMenuOptMap(Map<String, Map<String, Boolean>> menuOptMap) {
		this.menuOptMap = menuOptMap;
	}
	public Map<String, Boolean> getOptMap() {
		return optMap;
	}
	public void setOptMap(Map<String, Boolean> optMap) {
		this.optMap = optMap;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIsSuper() {
		return isSuper;
	}
	public void setIsSuper(int isSuper) {
		this.isSuper = isSuper;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	/*public List<PlatformOrgPo> getOrgList() {
		return orgList;
	}
	public void setOrgList(List<PlatformOrgPo> orgList) {
		this.orgList = orgList;
	}*/
	public List<PlatformRolePo> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<PlatformRolePo> roleList) {
		this.roleList = roleList;
	}
	public List<PlatformMenuPo> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<PlatformMenuPo> menuList) {
		this.menuList = menuList;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public SysBizInfo getBizInfo() {
		return bizInfo;
	}
	public void setBizInfo(SysBizInfo bizInfo) {
		this.bizInfo = bizInfo;
	}
	public PlatformEmployeePo getEmployeeInfo() {
		return employeeInfo;
	}
	public void setEmployeeInfo(PlatformEmployeePo employeeInfo) {
		this.employeeInfo = employeeInfo;
	}
	public Set<Integer> getDataUserIdSet() {
		return dataUserIdSet;
	}
	public void setDataUserIdSet(Set<Integer> dataUserIdSet) {
		this.dataUserIdSet = dataUserIdSet;
	}
	public Map<String, String> getBizConfigMap() {
		return bizConfigMap;
	}
	public void setBizConfigMap(Map<String, String> bizConfigMap) {
		this.bizConfigMap = bizConfigMap;
	}
	public PlatformOrgPo getOrgInfo() {
		return orgInfo;
	}
	public void setOrgInfo(PlatformOrgPo orgInfo) {
		this.orgInfo = orgInfo;
	}
	
}
