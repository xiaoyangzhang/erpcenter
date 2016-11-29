package com.yimayhd.erpcenter.facade.tj.client.query;

import java.io.Serializable;
import java.util.Map;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sales.client.taobao.po.PlatTaobaoTrade;

public class PresellTaobaoOriginalOrderDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer pageSize;
    private Integer page; 
    private String authClient;
    private PageBean<PlatTaobaoTrade> pageBean;
    private String curUserName;
    private Integer bizId;
    private Map<String, Object> pm;
    
    
	/**
	 * @return the pm
	 */
	public Map<String, Object> getPm() {
		return pm;
	}
	/**
	 * @param pm the pm to set
	 */
	public void setPm(Map<String, Object> pm) {
		this.pm = pm;
	}
	/**
	 * @return the curUserName
	 */
	public String getCurUserName() {
		return curUserName;
	}
	/**
	 * @param curUserName the curUserName to set
	 */
	public void setCurUserName(String curUserName) {
		this.curUserName = curUserName;
	}
	/**
	 * @return the bizId
	 */
	public Integer getBizId() {
		return bizId;
	}
	/**
	 * @param bizId the bizId to set
	 */
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}
	/**
	 * @return the authClient
	 */
	public String getAuthClient() {
		return authClient;
	}
	/**
	 * @param authClient the authClient to set
	 */
	public void setAuthClient(String authClient) {
		this.authClient = authClient;
	}
	/**
	 * @return the pageBean
	 */
	public PageBean<PlatTaobaoTrade> getPageBean() {
		return pageBean;
	}
	/**
	 * @param pageBean the pageBean to set
	 */
	public void setPageBean(PageBean<PlatTaobaoTrade> pageBean) {
		this.pageBean = pageBean;
	}
    
    
	
	
	
	
}
