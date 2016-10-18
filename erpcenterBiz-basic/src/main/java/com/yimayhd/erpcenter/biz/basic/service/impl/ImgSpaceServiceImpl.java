package com.yimayhd.erpcenter.biz.basic.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.dto.PageDto;
import com.yimayhd.erpcenter.dal.basic.dto.TreeDto;
import com.yimayhd.erpcenter.dal.basic.po.ImgSpace;
import com.yimayhd.erpcenter.dal.basic.service.ImgSpaceDal;


@Service
@Transactional
public class ImgSpaceServiceImpl implements ImgSpaceDal{

	@Override
	public int saveImgSpace(ImgSpace imgSpace) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveListImgSpaces(List<ImgSpace> imgSpaces) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteImgSpaceById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateImgSpace(ImgSpace imgSpace) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ImgSpace findImgSpaceById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ImgSpace> findImgSpaceByParentId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<ImgSpace> findImgSpaceByConditions(PageBean<ImgSpace> pageBean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ImgSpace> getImgList(ImgSpace space, String sortFileds, String order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ImgSpace> findImgSpaceByConditions(ImgSpace imgSpace) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ImgSpace> findImgSpaceByConditions(PageDto pageDto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TreeDto> findImgDirTree(ImgSpace imgSpace) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int imageDelete(Integer parentId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int restoreImageById(Integer imageId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int queryImageById(Integer imageId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int perpetualDelRestore(Integer imageId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int imageRename(Integer imageId, String imgName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int moveImage(Integer imageId, Integer parentId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int replaceImage(String uploadFileUrl, String fileName, Integer imageId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateNewPathName(Integer imgId, String newPathNew) {
		// TODO Auto-generated method stub
		return 0;
	}}
