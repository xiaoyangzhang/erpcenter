package org.erpcenterFacade.common.client.service;

import org.erpcenterFacade.common.client.query.BrandQueryDTO;
import org.erpcenterFacade.common.client.query.DepartmentTuneQueryDTO;
import org.erpcenterFacade.common.client.result.BrandQueryResult;
import org.erpcenterFacade.common.client.result.DepartmentTuneQueryResult;

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
    public BrandQueryResult brandQuery(BrandQueryDTO brandQueryDTO);
}
