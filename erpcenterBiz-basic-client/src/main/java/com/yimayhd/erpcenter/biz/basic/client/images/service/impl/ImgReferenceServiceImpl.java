package com.yimayhd.erpcenter.biz.basic.client.images.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yimayhd.erpcenter.biz.basic.client.images.mapper.ImgReferenceMapper;
import com.yimayhd.erpcenter.biz.basic.images.po.ImgReference;
import com.yimayhd.erpcenter.biz.basic.images.service.ImgReferenceService;


@Service
@Transactional
public class ImgReferenceServiceImpl implements ImgReferenceService{

	@Autowired
	private ImgReferenceMapper imgReferenceMapper;
	
	public int saveReference(ImgReference reference) throws Exception {
		return imgReferenceMapper.insertSelective(reference);
	}

	public int deleteReferenceById(Long imgReferenceId) throws Exception {
		
		return 0;
	}

	public int updateReference(ImgReference reference) throws Exception {
		return imgReferenceMapper.updateByPrimaryKeySelective(reference);
	}

	public ImgReference findReferenceById(Long referenceId) throws Exception {
		return imgReferenceMapper.selectByPrimaryKey(referenceId);
	}



}
