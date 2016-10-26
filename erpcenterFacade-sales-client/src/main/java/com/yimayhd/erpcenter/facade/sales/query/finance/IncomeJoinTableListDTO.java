package com.yimayhd.erpcenter.facade.sales.query.finance;

import java.util.Map;
import java.util.Set;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.TourGroupVO;
import com.yimayhd.erpcenter.facade.sales.BaseListPage;

public class IncomeJoinTableListDTO extends BaseListPage{
	
	private static final long serialVersionUID = 6707676794554415017L;
	
	private TourGroupVO group;
	private Map<String,Object> paramters;
    private Set<Integer> set;
    private Integer bizId;
    
    public TourGroupVO getGroup() {
		return group;
	}
	public void setGroup(TourGroupVO group) {
		this.group = group;
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
	public Integer getBizId() {
		return bizId;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
}
