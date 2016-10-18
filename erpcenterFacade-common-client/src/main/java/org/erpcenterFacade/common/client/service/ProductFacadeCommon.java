package org.erpcenterFacade.common.client.service;

import org.erpcenterFacade.common.client.query.LoadProductDepartmentDTO;
import org.erpcenterFacade.common.client.result.LoadProductDepartmentResult;

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
}
