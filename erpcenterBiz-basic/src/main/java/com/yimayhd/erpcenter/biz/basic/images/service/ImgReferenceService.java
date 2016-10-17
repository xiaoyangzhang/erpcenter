package com.yimayhd.erpcenter.biz.basic.images.service;

import com.yimayhd.erpcenter.biz.basic.images.po.ImgReference;

/**
 * 图片引用接口类
 * @author xusq
 *
 */
public interface ImgReferenceService {

	
	/**
	 *	添加引用信息
	 */
	public int saveReference(ImgReference reference) throws Exception;
	
	/**
	 *	删除引用信息
	 */
	public int deleteReferenceById(Long imgReferenceId) throws Exception;
	
	/**
	 *	跟新引用信息
	 */
	public int updateReference(ImgReference reference) throws Exception;
	
	/**
	 *	根据引用信息id获取图片的引用
	 */
	public ImgReference findReferenceById(Long referenceId) throws Exception;	

}
