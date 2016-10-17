package com.yimayhd.erpcenter.biz.basic.client.images.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.client.images.mapper.ImgSpaceMapper;
import com.yimayhd.erpcenter.biz.basic.images.dto.PageDto;
import com.yimayhd.erpcenter.biz.basic.images.dto.TreeDto;
import com.yimayhd.erpcenter.biz.basic.images.po.ImgSpace;
import com.yimayhd.erpcenter.biz.basic.images.service.ImgSpaceServie;

@Service
@Transactional
public class ImgSpaceServiceImpl implements ImgSpaceServie{

	@Autowired
	private ImgSpaceMapper imgSpaceMapper;
	
	public int saveImgSpace(ImgSpace imgSpace) throws Exception {
		
		return imgSpaceMapper.insertSelective(imgSpace);
	}

	public int saveListImgSpaces(List<ImgSpace> imgSpace) throws Exception {
		
		return imgSpaceMapper.insertListImgSpaces(imgSpace);
	}

	public int deleteImgSpaceById(Integer id) throws Exception {
		
		ImgSpace record = new ImgSpace();
		record.setImgId(id);
		record.setStatus(-1);
		
		return imgSpaceMapper.updateByPrimaryKeySelective(record);
	}

	public int updateImgSpace(ImgSpace imgSpace) throws Exception {
		return imgSpaceMapper.updateByPrimaryKeySelective(imgSpace);
	}

	public ImgSpace findImgSpaceById(Integer id) throws Exception {
		return imgSpaceMapper.selectByPrimaryKey(id);
	}

	public List<ImgSpace> findImgSpaceByParentId(Integer id) throws Exception {
		
		PageBean<ImgSpace> pageBean = new PageBean<ImgSpace>();
		
		ImgSpace imgSpace = new ImgSpace();
		imgSpace.setParentId(id);
		
		pageBean.setParameter(imgSpace);
		
		return imgSpaceMapper.selectImgSpaceListPage(pageBean);
	}

	public PageBean<ImgSpace> findImgSpaceByConditions(PageBean<ImgSpace> pageBean) throws Exception {
		
		List<ImgSpace> selectImgSpaceListPage = imgSpaceMapper.selectImgSpaceListPage(pageBean);
		
		pageBean.setResult(selectImgSpaceListPage);
		
		return pageBean;
	}
	
	/**
	 * 不分页
	 */
	//public List<ImgSpace> getImgList(Map<String,Object> map) throws Exception{
	public List<ImgSpace> getImgList(ImgSpace space,String sortFileds,String order) throws Exception{
		List<ImgSpace> list = imgSpaceMapper.selectImgSpaceList(space,sortFileds,order);
		return list;
	}
	

	public List<ImgSpace> findImgSpaceByConditions(ImgSpace imgSpace) throws Exception {
		
		PageBean<ImgSpace> pageBean = new PageBean<ImgSpace>();
		
		pageBean.setParameter(imgSpace);
		
		
		return imgSpaceMapper.selectImgSpaceListPage(pageBean);
	}

	/**
	 *	根据查询条件查询带 分页参数的图片列表   返回list 给Ueditor在线图片 分页效果
	 */
	public List<ImgSpace> findImgSpaceByConditions(PageDto pagedto) throws Exception{
		
		return imgSpaceMapper.selectImgSpaceListWithStartAndSize(pagedto);
	};
	
	
	public List<TreeDto> findImgDirTree(ImgSpace imgSpace) throws Exception {
		return imgSpaceMapper.selectDirTreeByConditions(imgSpace);
	}
	/**
	 *	根据id删除子文件以及本身
	 */
	@Override
	public int imageDelete(Integer parentId) throws Exception {
		
		//SendSMS.sendNoticeMessage("父ID为6565的  文件夹和子文件夹被删除 【怡美天香】", "18511256591", "");
		//SendHtmlMail.send("814694604@qq.com", "图片空间邮件提示", "父ID为"+parentId+"文件夹和子文件夹被删除");
		
		return this.imgSpaceMapper.imageDelete(parentId,new Date());
	}

	/***
	 * 还原回收站
	 */
	@Override
	public int restoreImageById(Integer imageId) {
		return this.imgSpaceMapper.restoreImageById(imageId);
	}

	@Override
	public int queryImageById(Integer imageId) {
		return this.imgSpaceMapper.queryImageById(imageId);
	}

	@Override
	public int perpetualDelRestore(Integer imageId) {
		return this.imgSpaceMapper.perpetualDelRestore(imageId);
	}

	@Override
	public int imageRename(Integer imageId,String imgName) {
		return this.imgSpaceMapper.imageRename(imageId,imgName);
	}

	@Override
	public int moveImage(Integer imageId, Integer parentId) {
		return this.imgSpaceMapper.moveImage(imageId,parentId);
	}

	@Override
	public int replaceImage(String uploadFileUrl, String fileName, Integer imageId) {
		return this.imgSpaceMapper.replaceImage(uploadFileUrl,fileName,imageId);
	}

	@Override
	public int updateNewPathName(Integer imgId, String newPathNew) {
		return this.imgSpaceMapper.updateNewPathName(imgId, newPathNew);
	}
}
