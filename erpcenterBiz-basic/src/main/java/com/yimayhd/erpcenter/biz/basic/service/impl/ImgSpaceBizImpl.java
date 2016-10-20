package com.yimayhd.erpcenter.biz.basic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.service.ImgSpaceBiz;
import com.yimayhd.erpcenter.dal.basic.dto.PageDto;
import com.yimayhd.erpcenter.dal.basic.dto.TreeDto;
import com.yimayhd.erpcenter.dal.basic.po.ImgSpace;
import com.yimayhd.erpcenter.dal.basic.service.ImgSpaceDal;


@Service
@Transactional
public class ImgSpaceBizImpl implements ImgSpaceBiz{
	@Autowired
	private ImgSpaceDal imgSpaceDal;

	@Override
	public int saveImgSpace(ImgSpace imgSpace) throws Exception {
		return imgSpaceDal.saveImgSpace(imgSpace);
	}

	@Override
	public int saveListImgSpaces(List<ImgSpace> imgSpaces) throws Exception {
		return imgSpaceDal.saveListImgSpaces(imgSpaces);
	}

	@Override
	public int deleteImgSpaceById(Integer id) throws Exception {
		return imgSpaceDal.deleteImgSpaceById(id);
	}

	@Override
	public int updateImgSpace(ImgSpace imgSpace) throws Exception {
		return imgSpaceDal.updateImgSpace(imgSpace);
	}

	@Override
	public ImgSpace findImgSpaceById(Integer id) throws Exception {
		return imgSpaceDal.findImgSpaceById(id);
	}

	@Override
	public List<ImgSpace> findImgSpaceByParentId(Integer id) throws Exception {
		return imgSpaceDal.findImgSpaceByParentId(id);
	}

	@Override
	public PageBean<ImgSpace> findImgSpaceByConditions(PageBean<ImgSpace> pageBean) throws Exception {
		return imgSpaceDal.findImgSpaceByConditions(pageBean);
	}

	@Override
	public List<ImgSpace> getImgList(ImgSpace space, String sortFileds, String order) throws Exception {
		return imgSpaceDal.getImgList(space, sortFileds, order);
	}

	@Override
	public List<ImgSpace> findImgSpaceByConditions(ImgSpace imgSpace) throws Exception {
		return imgSpaceDal.findImgSpaceByConditions(imgSpace);
	}

	@Override
	public List<ImgSpace> findImgSpaceByConditions(PageDto pageDto) throws Exception {
		return imgSpaceDal.findImgSpaceByConditions(pageDto);
	}

	@Override
	public List<TreeDto> findImgDirTree(ImgSpace imgSpace) throws Exception {
		return imgSpaceDal.findImgDirTree(imgSpace);
	}

	@Override
	public int imageDelete(Integer parentId) throws Exception {
		return imgSpaceDal.imageDelete(parentId);
	}

	@Override
	public int restoreImageById(Integer imageId) {
		return imgSpaceDal.restoreImageById(imageId);
	}

	@Override
	public int queryImageById(Integer imageId) {
		return imgSpaceDal.queryImageById(imageId);
	}

	@Override
	public int perpetualDelRestore(Integer imageId) {
		return imgSpaceDal.perpetualDelRestore(imageId);
	}

	@Override
	public int imageRename(Integer imageId, String imgName) {
		return imgSpaceDal.imageRename(imageId, imgName);
	}

	@Override
	public int moveImage(Integer imageId, Integer parentId) {
		return imgSpaceDal.moveImage(imageId, parentId);
	}

	@Override
	public int replaceImage(String uploadFileUrl, String fileName, Integer imageId) {
		return imgSpaceDal.replaceImage(uploadFileUrl, fileName, imageId);
	}

	@Override
	public int updateNewPathName(Integer imgId, String newPathNew) {
		return imgSpaceDal.updateNewPathName(imgId, newPathNew);
	}
}
