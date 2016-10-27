package org.yimayhd.erpcenter.facade.finance.query;

import java.util.Map;
import java.util.Set;

import org.yimayhd.erpcenter.facade.finance.BaseListPage;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;

public class AduditStatisticsListDTO extends BaseListPage{
	
	private static final long serialVersionUID = 6707676794554415017L;
	
	private String sl;
	private String ssl;
	private String rp;
	private String svc;
	private Integer bizId;
	private Map<String,Object> paramters;
	private Set<Integer> set;
	private String saleOperatorIds;
	private String orgIds;
	
	
	public String getSaleOperatorIds() {
		return saleOperatorIds;
	}
	public void setSaleOperatorIds(String saleOperatorIds) {
		this.saleOperatorIds = saleOperatorIds;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public Map<String, Object> getParamters() {
		return paramters;
	}
	public void setParamters(Map<String, Object> paramters) {
		this.paramters = paramters;
	}
	public Set<Integer> getSet() {
		return set;
	}
	public void setSet(Set<Integer> set) {
		this.set = set;
	}
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
}
