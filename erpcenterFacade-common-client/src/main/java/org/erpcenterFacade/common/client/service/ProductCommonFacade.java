package org.erpcenterFacade.common.client.service;

import java.util.List;
import java.util.Map;

import com.yimayhd.erpcenter.dal.basic.dto.TreeDto;

import org.erpcenterFacade.common.client.query.BrandQueryDTO;
import org.erpcenterFacade.common.client.query.DepartmentTuneQueryDTO;
import org.erpcenterFacade.common.client.result.BrandQueryResult;
import org.erpcenterFacade.common.client.result.DepartmentTuneQueryResult;
import org.erpcenterFacade.common.client.result.RegionResult;
import org.springframework.web.multipart.MultipartFile;

import com.yimayhd.erpcenter.dal.basic.po.ImgSpace;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/18 19:48
 */
public interface ProductCommonFacade {

    /**
     *  部门 计调 查询
     * @param departmentTuneQueryDTO
     * @return
     */
    public DepartmentTuneQueryResult departmentTuneQuery(DepartmentTuneQueryDTO departmentTuneQueryDTO);
    /**
     * 产品品牌 查询
     *
     * @param brandQueryDTO
     * @return
     */
    public BrandQueryResult brandQuery(BrandQueryDTO brandQueryDTO);
    /**
     * 
    * created by zhangxiaoyang
    * @date 2016年10月19日
    * @Description:设置计调/部门的查询参数
    * @param 
    * @return String
    * @throws
     */
    public String setSaleOperatorIds(String operatorIds,String orgIds,int bizId);
    /**
     * 图片集
     * @param bizId
     * @param imgId
     * @param name
     * @param sortFileds
     * @param order
     * @return
     * @author wangjun
     */
    public List<ImgSpace> imgList(int bizId, int imgId, String name,
			String sortFileds, String order);
    /**
     * 图片树
     * @param bizId
     * @return
     * @author wangjun
     */
    public List<TreeDto> findImgDirTree(int bizId);
    
    /**
	 * 选择计调人员页面
	 * @param bizId
	 * @param type 单选 single 多选multi
	 * @return
	 */
    public List<Map<String, String>> orgUserTree(int bizId,String type);
    
    /**
     * 选择计调--查询
     * @param bizId
     * @param name
     * @param type
     * @return
     * @author wangjun
     */
    public List<Map<String, String>> queryOrgUserTree(int bizId,String name,String type);
    /**
     * 
    * created by zhangxiaoyang
    * @date 2016年10月20日
    * @Description:查询所有省份
    * @param 
    * @return RegionResult
    * @throws
     */
    public RegionResult queryProvinces();
    
    /**
     * 上传图片
     * @param parentId
     * @return
     * @author wangjun
     */
    public ImgSpace toUploadPage(int parentId);
}
