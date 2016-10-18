package com.yimayhd.erpcenter.facade.common.service.impl;

import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import org.erpcenterFacade.common.client.query.LoadProductDepartmentDTO;
import org.erpcenterFacade.common.client.result.LoadProductDepartmentResult;
import org.erpcenterFacade.common.client.service.ProductFacadeCommon;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/18 19:55
 */
public class ProductFacadeCommonImpl implements ProductFacadeCommon {
    @Autowired
    private DicBiz dicBiz;
    @Autowired
    private PlatformOrgBiz platformOrgBiz;
    @Autowired
    private PlatformEmployeeBiz platformEmployeeBiz;

    /**
     * 加载
     * 产品名称:部门:计调员
     *
     * @param loadProductDepartmentDTO
     * @return
     */
    @Override
    public LoadProductDepartmentResult loadProductDepartment(LoadProductDepartmentDTO loadProductDepartmentDTO) {
        LoadProductDepartmentResult loadProductDepartmentResult = new LoadProductDepartmentResult();
        loadProductDepartmentResult.setBrandList(dicBiz.getListByTypeCode(BasicConstants.CPXL_PP, loadProductDepartmentDTO.getBizId()));
        loadProductDepartmentResult.setOrgJsonStr(platformOrgBiz.getComponentOrgTreeJsonStr(loadProductDepartmentDTO.getBizId()));
        loadProductDepartmentResult.setOrgUserJsonStr(platformEmployeeBiz.getComponentOrgUserTreeJsonStr(loadProductDepartmentDTO.getBizId()));
        return loadProductDepartmentResult;
    }
}
