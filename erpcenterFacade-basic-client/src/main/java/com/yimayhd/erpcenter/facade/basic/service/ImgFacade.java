package com.yimayhd.erpcenter.facade.basic.service;

import java.util.List;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.basic.dto.PageDto;
import com.yimayhd.erpcenter.dal.basic.dto.TreeDto;
import com.yimayhd.erpcenter.dal.basic.po.ImgSpace;
import com.yimayhd.erpcenter.dal.basic.po.ImgWatermark;
import com.yimayhd.erpcenter.facade.basic.result.ResultSupport;

/**
 * 
* @ClassName: ImgFacade
* @Description: TODO(这里用一句话描述这个类的作用)
* @author zhangxiaoyang
* @date 2016年10月31日
 */
public interface ImgFacade {

	ResultSupport saveWatermark(ImgWatermark watermark) throws Exception ;
	List<ImgSpace> findImgSpaceByConditions(PageDto pageDto) throws Exception;
	List<TreeDto> findImgDirTree(ImgSpace imgSpace) throws Exception;
	PageBean findImgSpaceByConditions(PageBean pageBean)throws Exception ;
	ResultSupport restoreImageById(Integer imageId)throws Exception ;
	ResultSupport perpetualDelRestore(Integer imageId)throws Exception ;
	ResultSupport imageRename(Integer imageId,String imgName)throws Exception ;
	ResultSupport moveImage(Integer imageId,Integer parentId)throws Exception ;
	ResultSupport replaceImage(String uploadFileUrl,String fileName,Integer imageId)throws Exception ;
	ResultSupport saveImgSpace(ImgSpace imgSpace)throws Exception ;
	ResultSupport imageDelete(Integer parentId)throws Exception ;
	ResultSupport saveListImgSpaces(List<ImgSpace> imgSpaces)throws Exception ;
}
