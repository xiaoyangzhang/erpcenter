package com.yimayhd.erpcenter.biz.sys.vo;

import java.io.Serializable;
import java.util.Map;

public class MenuOptVo implements Serializable {
	Map<String, Map<String,Boolean>> MenuOptMap;
	Map<String,Boolean> optMap;
	public Map<String, Map<String, Boolean>> getMenuOptMap() {
		return MenuOptMap;
	}
	public void setMenuOptMap(Map<String, Map<String, Boolean>> menuOptMap) {
		MenuOptMap = menuOptMap;
	}
	public Map<String, Boolean> getOptMap() {
		return optMap;
	}
	public void setOptMap(Map<String, Boolean> optMap) {
		this.optMap = optMap;
	}
	
}
