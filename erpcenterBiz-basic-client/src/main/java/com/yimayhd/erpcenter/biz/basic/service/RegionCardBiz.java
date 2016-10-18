package com.yimayhd.erpcenter.biz.basic.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.SysRegionCard;


public interface RegionCardBiz {
	List<SysRegionCard> getFirstList(); 
	List<SysRegionCard> getSecondList();
	List<SysRegionCard> getThirdList();		
}
