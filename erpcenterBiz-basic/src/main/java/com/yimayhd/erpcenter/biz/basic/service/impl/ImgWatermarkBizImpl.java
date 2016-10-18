package com.yimayhd.erpcenter.biz.basic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yimayhd.erpcenter.biz.basic.service.ImgWatermarkBiz;
import com.yimayhd.erpcenter.dal.basic.po.ImgWatermark;
import com.yimayhd.erpcenter.dal.basic.service.ImgWatermarkDal;



@Service
@Transactional
public class ImgWatermarkBizImpl implements ImgWatermarkBiz {
	@Autowired
	private ImgWatermarkDal imgWatermarkDal;

	@Override
	public int saveWatermark(ImgWatermark watermark) throws Exception {
		return imgWatermarkDal.saveWatermark(watermark);
	}

	@Override
	public int deleteWatermarkById(Integer watemarkId) throws Exception {
		return imgWatermarkDal.deleteWatermarkById(watemarkId);
	}

	@Override
	public int updateWatermark(ImgWatermark watermark) throws Exception {
		return imgWatermarkDal.updateWatermark(watermark);
	}

	@Override
	public ImgWatermark findWatermarkById(Integer watermarkId) throws Exception {
		return imgWatermarkDal.findWatermarkById(watermarkId);
	}

	@Override
	public int deleteWatermarkById(Integer currentUserId, Integer currentDealerId, String currentSysId) {
		return imgWatermarkDal.deleteWatermarkById(currentUserId, currentDealerId, currentSysId);
	}

	@Override
	public ImgWatermark findImgWatermarkByConditions(ImgWatermark watermark) throws Exception {
		return imgWatermarkDal.findImgWatermarkByConditions(watermark);
	}
}
