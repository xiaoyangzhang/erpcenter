package org.erpcenterFacade.common.client.service;

import java.util.List;

import org.erpcenterFacade.common.client.query.LoadProductDepartmentDTO;
import org.erpcenterFacade.common.client.result.LoadProductDepartmentResult;

import com.yimayhd.erpcenter.dal.basic.dto.TreeDto;
import com.yimayhd.erpcenter.dal.basic.po.ImgSpace;


/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/18 19:48
 */
public interface ProductFacadeCommon {

    /**
     * 加载
     * 产品名称:部门:计调
     *
     * @param loadProductDepartmentDTO
     * @return
     */
    public LoadProductDepartmentResult loadProductDepartment(LoadProductDepartmentDTO loadProductDepartmentDTO);
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
     * 查询图片树
     * @param bizId
     * @return
     * @author wangjun
     */
    List<TreeDto> findImgDirTree(int bizId);
    
    /**
     * 图片集合
     * @param bizId
     * @param imgId
     * @param name
     * @param sortFileds
     * @param order
     * @return
     * @author wangjun
     */
    List<ImgSpace> imgList(int bizId,int imgId,String name,String sortFileds,String order);
    
    //TODO 商家列表 等supplier上了后再写
//    supplierList();
   
}
