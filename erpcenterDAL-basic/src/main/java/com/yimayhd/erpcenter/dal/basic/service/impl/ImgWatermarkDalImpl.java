package com.yimayhd.erpcenter.dal.basic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yimayhd.erpcenter.dal.basic.dao.ImgWatermarkMapper;
import com.yimayhd.erpcenter.dal.basic.po.ImgWatermark;
import com.yimayhd.erpcenter.dal.basic.service.ImgWatermarkDal;



@Service
@Transactional
public class ImgWatermarkDalImpl implements ImgWatermarkDal {

	@Autowired
	private ImgWatermarkMapper watermarkMapper;
	
	public int saveWatermark(ImgWatermark watermark) throws Exception {
		
		return watermarkMapper.insertSelective(watermark);
	}

	public int deleteWatermarkById(Integer watemarkId) throws Exception {
		
		ImgWatermark record = new ImgWatermark();
		record.setWatermarkId(watemarkId);
		record.setStatus(-1);
		
		return watermarkMapper.updateByPrimaryKeySelective(record );
	}

	public int updateWatermark(ImgWatermark watermark) throws Exception {
		
		return watermarkMapper.updateByPrimaryKeySelective(watermark);
	}

	public ImgWatermark findWatermarkById(Integer watermarkId) throws Exception {
		
		return watermarkMapper.selectByPrimaryKey(watermarkId);
	}
	
	/**
	 *	 删除水印信息
	 */
	@Override
	public int deleteWatermarkById(Integer currentUserId, Integer currentDealerId,
			String currentSysId) {
		return this.watermarkMapper.deleteWatermarkByThreeId(currentUserId,currentDealerId,currentSysId);
	}

	public ImgWatermark findImgWatermarkByConditions(ImgWatermark watermark)
			throws Exception {
		List<ImgWatermark> list = watermarkMapper.findImgWatermarkByConditions(watermark);
		if (null==list||list.size()==0){
			return null; 
		}
		if (list.size()==1) {
            return list.get(0);
		}else{
            return null;
		}
		
	}

}
