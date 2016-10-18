package com.yimayhd.erpcenter.biz.basic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yimayhd.erpcenter.dal.basic.po.ImgReference;
import com.yimayhd.erpcenter.dal.basic.service.ImgReferenceDal;


@Service
@Transactional
public class ImgReferenceServiceImpl implements ImgReferenceDal{

	@Override
	public int saveReference(ImgReference reference) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReferenceById(Long imgReferenceId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReference(ImgReference reference) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ImgReference findReferenceById(Long referenceId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}}
