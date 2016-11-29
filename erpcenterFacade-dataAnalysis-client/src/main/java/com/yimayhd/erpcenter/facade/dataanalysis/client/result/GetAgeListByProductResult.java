package com.yimayhd.erpcenter.facade.dataanalysis.client.result;

import com.yihg.mybatis.utility.PageBean;

import java.util.List;
import java.util.Map;

public class GetAgeListByProductResult extends BaseResult {
	private static final long serialVersionUID = 1682594542432810099L;
	private List<Map<String, Object>> ageMaps;
	private Map<String, Object> personMap;
	private Map<Object,Object> ageMap;
	private PageBean pageBean;

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public Map<Object, Object> getAgeMap() {
		return ageMap;
	}

	public void setAgeMap(Map<Object, Object> ageMap) {
		this.ageMap = ageMap;
	}

	public List<Map<String, Object>> getAgeMaps() {
		return ageMaps;
	}

	public void setAgeMaps(List<Map<String, Object>> ageMaps) {
		this.ageMaps = ageMaps;
	}

	public Map<String, Object> getPersonMap() {
		return personMap;
	}

	public void setPersonMap(Map<String, Object> personMap) {
		this.personMap = personMap;
	}
}
