package com.yimayhd.erpcenter.dal.basic.service;

import java.util.List;

import com.yimayhd.erpcenter.dal.basic.po.SysRegionCard;


public interface RegionCardDal {
	List<SysRegionCard> getFirstList(); 
	List<SysRegionCard> getSecondList();
	List<SysRegionCard> getThirdList();		
}
