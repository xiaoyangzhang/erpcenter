package com.yimayhd.erpcenter.biz.basic.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yimayhd.erpcenter.dal.basic.po.ImgWatermark;
import com.yimayhd.erpcenter.dal.basic.service.ImgWatermarkDal;



@Service
@Transactional
public class ImgWatermarkServiceImpl implements ImgWatermarkDal {

	@Override
	public int saveWatermark(ImgWatermark watermark) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteWatermarkById(Integer watemarkId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateWatermark(ImgWatermark watermark) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ImgWatermark findWatermarkById(Integer watermarkId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteWatermarkById(Integer currentUserId, Integer currentDealerId, String currentSysId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ImgWatermark findImgWatermarkByConditions(ImgWatermark watermark) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}}
