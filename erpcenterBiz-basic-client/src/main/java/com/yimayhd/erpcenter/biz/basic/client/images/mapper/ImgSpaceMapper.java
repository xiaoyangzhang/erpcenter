package com.yimayhd.erpcenter.biz.basic.client.images.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.biz.basic.images.dto.PageDto;
import com.yimayhd.erpcenter.biz.basic.images.dto.TreeDto;
import com.yimayhd.erpcenter.biz.basic.images.po.ImgSpace;

public interface ImgSpaceMapper {
    int deleteByPrimaryKey(Integer imgId);

    int insert(ImgSpace record);

    int insertSelective(ImgSpace record);

    ImgSpace selectByPrimaryKey(Integer imgId);

    int updateByPrimaryKeySelective(ImgSpace record);

    int updateByPrimaryKey(ImgSpace record);

	List<ImgSpace> selectImgSpaceListPage(PageBean<ImgSpace> pageBean);
	 /**
	  *  <!-- 20150709 added by gejinjun 不分页功能 -->
	  * @param pageBean
	  * @return
	  */
	//List<ImgSpace> selectImgSpaceList(Map<String,Object> map);
	List<ImgSpace> selectImgSpaceList(@Param("space")ImgSpace space,@Param("sortFileds")String sortFileds,@Param("order")String order);

	List<TreeDto> selectDirTreeByConditions(ImgSpace imgSpace);

	int insertListImgSpaces(List<ImgSpace> imgSpaces);

	int imageDelete(@Param("parentId") Integer parentId,@Param("updateTime") Date updateTime);

	List<ImgSpace> selectImgSpaceListWithStartAndSize(PageDto  pagedto);

	int restoreImageById(@Param("imageId") Integer imageId);

	int queryImageById(@Param("imageId") Integer imageId);

	int perpetualDelRestore(@Param("imageId") Integer imageId);

	int imageRename(@Param("imageId") Integer imageId,@Param("imgName")String imgName);

	int moveImage(@Param("imageId")Integer imageId,@Param("parentId")Integer parentId);

	int replaceImage(@Param("uploadFileUrl") String uploadFileUrl,@Param("fileName") String fileName, @Param("imageId") Integer imageId);
	
	int updateNewPathName(@Param("id")Integer id,@Param("newPathName")String newPathName);

}