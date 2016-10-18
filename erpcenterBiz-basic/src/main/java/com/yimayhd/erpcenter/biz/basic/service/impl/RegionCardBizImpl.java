package com.yimayhd.erpcenter.biz.basic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.erpcenter.biz.basic.service.RegionCardBiz;
import com.yimayhd.erpcenter.dal.basic.po.SysRegionCard;
import com.yimayhd.erpcenter.dal.basic.service.RegionCardDal;


public class RegionCardBizImpl implements RegionCardBiz {
	@Autowired
	private RegionCardDal regionCardDal;

	@Override
	public List<SysRegionCard> getFirstList() {
		return regionCardDal.getFirstList();
	}

	@Override
	public List<SysRegionCard> getSecondList() {
		return regionCardDal.getSecondList();
	}

	@Override
	public List<SysRegionCard> getThirdList() {
		return regionCardDal.getThirdList();
	}
}
