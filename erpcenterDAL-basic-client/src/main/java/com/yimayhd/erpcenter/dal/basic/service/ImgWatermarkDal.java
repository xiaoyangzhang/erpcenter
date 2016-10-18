package com.yimayhd.erpcenter.dal.basic.service;

import com.yimayhd.erpcenter.dal.basic.po.ImgWatermark;

/**
 * 图片水印相关接口类
 * @author xusq
 *
 */
public interface ImgWatermarkDal {
	
	
	/**
	 * 	添加水印信息
	 */
	public int saveWatermark(ImgWatermark watermark) throws Exception;
	
	
	/**
	 *	 删除水印信息
	 */
	public int deleteWatermarkById(Integer watemarkId) throws Exception;
	
	
	/**
	 *	 修改水印信息
	 */
	public int updateWatermark(ImgWatermark watermark) throws Exception;
	
	
	/**
	 * 	查询水印信息
	 */
	public ImgWatermark findWatermarkById(Integer watermarkId) throws Exception;

	/**
	 *	 删除水印信息
	 */
	public int deleteWatermarkById(Integer currentUserId, Integer currentDealerId,
			String currentSysId);
	
	/**
	 *	根据条件信息获取对应的水印信息  返回list.get(0)
	 */
	public ImgWatermark findImgWatermarkByConditions(ImgWatermark watermark) throws Exception;
	
}
