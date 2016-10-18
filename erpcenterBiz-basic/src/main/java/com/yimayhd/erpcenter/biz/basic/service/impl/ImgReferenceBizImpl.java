package com.yimayhd.erpcenter.biz.basic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yimayhd.erpcenter.dal.basic.po.ImgReference;
import com.yimayhd.erpcenter.dal.basic.service.ImgReferenceDal;


@Service
@Transactional
public class ImgReferenceBizImpl implements ImgReferenceDal{
	@Autowired
	private ImgReferenceDal imgReferenceDal;
	@Override
	public int saveReference(ImgReference reference) throws Exception {
		return imgReferenceDal.saveReference(reference);
	}

	@Override
	public int deleteReferenceById(Long imgReferenceId) throws Exception {
		return imgReferenceDal.deleteReferenceById(imgReferenceId);
	}

	@Override
	public int updateReference(ImgReference reference) throws Exception {
		return imgReferenceDal.updateReference(reference);
	}

	@Override
	public ImgReference findReferenceById(Long referenceId) throws Exception {
		return imgReferenceDal.findReferenceById(referenceId);
	}
}
