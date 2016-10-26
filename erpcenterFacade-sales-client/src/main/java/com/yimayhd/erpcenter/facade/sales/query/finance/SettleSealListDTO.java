package com.yimayhd.erpcenter.facade.sales.query.finance;

import java.util.Map;
import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import com.yimayhd.erpcenter.facade.sales.BaseListPage;


public class SettleSealListDTO extends BaseListPage{
    private static final long serialVersionUID = -5738945525593633404L;
    
    private String sl;
	private String ssl;
	private String rp;  
	private String svc;
	private TourGroupVO group;
	private int bizId;
	private Map<String,Object> pms;
	private Set<Integer> set;
	
	public String getSl() {
		return sl;
	}
	public void setSl(String sl) {
		this.sl = sl;
	}
	public String getSsl() {
		return ssl;
	}
	public void setSsl(String ssl) {
		this.ssl = ssl;
	}
	public String getRp() {
		return rp;
	}
	public void setRp(String rp) {
		this.rp = rp;
	}
	public String getSvc() {
		return svc;
	}
	public void setSvc(String svc) {
		this.svc = svc;
	}
	public TourGroupVO getGroup() {
		return group;
	}
	public void setGroup(TourGroupVO group) {
		this.group = group;
	}
	public int getBizId() {
		return bizId;
	}
	public void setBizId(int bizId) {
		this.bizId = bizId;
	}
	public Map<String, Object> getPms() {
		return pms;
	}
	public void setPms(Map<String, Object> pms) {
		this.pms = pms;
	}
	public Set<Integer> getSet() {
		return set;
	}
	public void setSet(Set<Integer> set) {
		this.set = set;
	}
   
}