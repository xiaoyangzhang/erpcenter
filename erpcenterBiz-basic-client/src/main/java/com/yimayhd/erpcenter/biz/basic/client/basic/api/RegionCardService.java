package com.yimayhd.erpcenter.biz.basic.client.basic.api;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.client.basic.po.SysRegionCard;



public interface RegionCardService {
	List<SysRegionCard> getFirstList(); 
	List<SysRegionCard> getSecondList();
	List<SysRegionCard> getThirdList();		
}
