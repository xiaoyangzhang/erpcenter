package com.yimayhd.erpcenter.facade.basic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.ImgSpaceBiz;
import com.yimayhd.erpcenter.biz.basic.service.ImgWatermarkBiz;
import com.yimayhd.erpcenter.dal.basic.dto.PageDto;
import com.yimayhd.erpcenter.dal.basic.dto.TreeDto;
import com.yimayhd.erpcenter.dal.basic.po.ImgSpace;
import com.yimayhd.erpcenter.dal.basic.po.ImgWatermark;
import com.yimayhd.erpcenter.facade.basic.errorcode.BasicErrorCode;
import com.yimayhd.erpcenter.facade.basic.result.ResultSupport;
import com.yimayhd.erpcenter.facade.basic.service.ImgFacade;

public class ImgFacadeImpl implements ImgFacade {

	@Autowired
	private ImgWatermarkBiz imgWatermarkBiz;
	@Autowired
	private ImgSpaceBiz imgSpaceBiz;
	@Override
	public ResultSupport saveWatermark(ImgWatermark watermark) throws Exception {
		ResultSupport resultSupport = new ResultSupport();
		
		int saveResult = imgWatermarkBiz.saveWatermark(watermark);
		if (saveResult < 1) {
			resultSupport.setErrorCode(BasicErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}
	@Override
	public List<ImgSpace> findImgSpaceByConditions(PageDto pageDto) throws Exception {
		List<ImgSpace> imgSpaces = imgSpaceBiz.findImgSpaceByConditions(pageDto);
		return imgSpaces;
	}
	@Override
	public List<TreeDto> findImgDirTree(ImgSpace imgSpace) throws Exception {
		List<TreeDto> dirTree = imgSpaceBiz.findImgDirTree(imgSpace);
		return dirTree;
	}
	@Override
	public PageBean findImgSpaceByConditions(PageBean pageBean)throws Exception  {
		PageBean page = imgSpaceBiz.findImgSpaceByConditions(pageBean);
		return page;
	}
	@Override
	public ResultSupport restoreImageById(Integer imageId) throws Exception {
		ResultSupport resultSupport = new ResultSupport();
		int saveResult = imgSpaceBiz.restoreImageById(imageId);
		if (saveResult < 0) {
			resultSupport.setErrorCode(BasicErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}
	@Override
	public ResultSupport perpetualDelRestore(Integer imageId) throws Exception {
		
		ResultSupport resultSupport = new ResultSupport();
		int saveResult = imgSpaceBiz.perpetualDelRestore(imageId);
		if (saveResult < 0) {
			resultSupport.setErrorCode(BasicErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}
	@Override
	public ResultSupport imageRename(Integer imageId, String imgName)
			throws Exception {
		
		ResultSupport resultSupport = new ResultSupport();
		int saveResult = imgSpaceBiz.imageRename(imageId,imgName);
		if (saveResult < 0) {
			resultSupport.setErrorCode(BasicErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}
	@Override
	public ResultSupport moveImage(Integer imageId, Integer parentId)
			throws Exception {

		ResultSupport resultSupport = new ResultSupport();
		int saveResult = imgSpaceBiz.moveImage(imageId,parentId);
		if (saveResult < 0) {
			resultSupport.setErrorCode(BasicErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}
	@Override
	public ResultSupport replaceImage(String uploadFileUrl, String fileName,
			Integer imageId) throws Exception {

		ResultSupport resultSupport = new ResultSupport();
		int saveResult = imgSpaceBiz.replaceImage(uploadFileUrl,fileName,imageId);
		if (saveResult < 0) {
			resultSupport.setErrorCode(BasicErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}
	@Override
	public ResultSupport saveImgSpace(ImgSpace imgSpace) throws Exception {
		
		ResultSupport resultSupport = new ResultSupport();
		int saveResult = imgSpaceBiz.saveImgSpace(imgSpace);
		if (saveResult < 0) {
			resultSupport.setErrorCode(BasicErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}
	@Override
	public ResultSupport imageDelete(Integer parentId) throws Exception {
		ResultSupport resultSupport = new ResultSupport();
		int saveResult = imgSpaceBiz.imageDelete(parentId);
		if (saveResult < 0) {
			resultSupport.setErrorCode(BasicErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}
	@Override
	public ResultSupport saveListImgSpaces(List<ImgSpace> imgSpaces)
			throws Exception {
		ResultSupport resultSupport = new ResultSupport();
		int saveResult = imgSpaceBiz.saveListImgSpaces(imgSpaces);
		if (saveResult < 0) {
			resultSupport.setErrorCode(BasicErrorCode.MODIFY_ERROR);
		}
		return resultSupport;
	}

}
