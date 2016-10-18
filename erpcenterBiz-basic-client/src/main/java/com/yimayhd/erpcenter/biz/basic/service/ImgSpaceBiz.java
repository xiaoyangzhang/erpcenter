package com.yimayhd.erpcenter.biz.basic.service;


import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.dto.PageDto;
import com.yimayhd.erpcenter.dal.basic.dto.TreeDto;
import com.yimayhd.erpcenter.dal.basic.po.ImgSpace;

/**
 * 图片空间图片信息接口
 * @author xusq
 *
 */
public interface ImgSpaceBiz {

	
	/**
	 *	添加图片空间信息
	 */
	public int saveImgSpace(ImgSpace imgSpace) throws Exception;
	
	/**
	 *	添加多张图片空间信息
	 */
	public int saveListImgSpaces(List<ImgSpace> imgSpaces) throws Exception;
	
	/**
	 *	删除图片空间信息
	 */
	public int deleteImgSpaceById(Integer id) throws Exception;
	
	
	/**
	 *	修改图片空间信息
	 */
	public int updateImgSpace(ImgSpace imgSpace) throws Exception;
	
	
	/**
	 *	根据图片空间id获取 对应的图片空间信息
	 */
	public ImgSpace findImgSpaceById(Integer id) throws Exception;
	
	
	/**
	 *	根据父目录信息获取对应的子集所有信息
	 */
	public List<ImgSpace> findImgSpaceByParentId(Integer id) throws Exception;
	
	/**
	 *	根据条件信息获取对应的甜片空间信息  分页信息
	 */
	public PageBean<ImgSpace> findImgSpaceByConditions(PageBean<ImgSpace> pageBean) throws Exception;
	
	/**
	 *	根据条件信息获取对应的文件列表，不分页
	 */
	//public List<ImgSpace>getImgList(Map<String,Object> map) throws Exception;
	public List<ImgSpace> getImgList(ImgSpace space,String sortFileds,String order) throws Exception;
	
	
	/**
	 *	根据条件信息获取对应的甜片空间信息  返回list
	 */
	public List<ImgSpace> findImgSpaceByConditions(ImgSpace imgSpace) throws Exception;
	

	/**
	 *	根据查询条件查询带 分页参数的图片列表   返回list 给Ueditor在线图片 分页效果
	 */
	public List<ImgSpace> findImgSpaceByConditions(PageDto pageDto) throws Exception;
	
	
	/**
	 *	根据用户信息查询对应的文件夹树形信息
	 */
	public List<TreeDto> findImgDirTree(ImgSpace imgSpace) throws Exception;
	
	/**
	 *	根据id删除子文件以及本身
	 */
	public int imageDelete(Integer parentId) throws Exception;

	/***
	* @return 
	* @exception
	* @see 还原回收站
	 */
	public int restoreImageById(Integer imageId);

	/***
	* @param 
	* @return 
	* @exception
	* @see
	 */
	public int queryImageById(Integer imageId);
	/***
	* @param 
	* @return 
	* @exception
	* @see 永久性删除回收站内容
	 */
	public int perpetualDelRestore(Integer imageId);
	/***
	 * 
	* @param 
	* @return 
	* @exception
	* @see 图片重命名
	 */
	public int imageRename(Integer imageId,String imgName);
	/***
	* @param 
	* @return 
	* @exception
	* @see 移动图片或者文件夹
	 */
	public int moveImage(Integer imageId, Integer parentId);
	/***
	 * 
	* @param 
	* @return 
	* @exception
	* @see 图片替换
	 */
	public int replaceImage(String uploadFileUrl, String fileName, Integer imageId);
	
	public int updateNewPathName(Integer imgId,String newPathNew);
	
}
