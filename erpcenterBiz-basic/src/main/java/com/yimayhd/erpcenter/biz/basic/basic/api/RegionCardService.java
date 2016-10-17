package com.yimayhd.erpcenter.biz.basic.basic.api;

import java.util.List;

import com.yimayhd.erpcenter.biz.basic.basic.po.SysRegionCard;


public interface RegionCardService {
	List<SysRegionCard> getFirstList(); 
	List<SysRegionCard> getSecondList();
	List<SysRegionCard> getThirdList();		
}
