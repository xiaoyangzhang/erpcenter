package com.yimayhd.erpcenter.dal.basic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yimayhd.erpcenter.dal.basic.dao.ImgReferenceMapper;
import com.yimayhd.erpcenter.dal.basic.po.ImgReference;
import com.yimayhd.erpcenter.dal.basic.service.ImgReferenceDal;


@Service
@Transactional
public class ImgReferenceDalImpl implements ImgReferenceDal{

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
