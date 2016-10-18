package com.yimayhd.erpcenter.facade.product.service.impl;


import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformEmployeeBiz;
import com.yimayhd.erpcenter.biz.sys.service.PlatformOrgBiz;
import com.yimayhd.erpcenter.common.contants.BasicConstants;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import com.yimayhd.erpcenter.facade.query.ToListStateDTO;
import com.yimayhd.erpcenter.facade.query.ToSearchListStateDTO;
import com.yimayhd.erpcenter.facade.result.ToListStateResult;
import com.yimayhd.erpcenter.facade.service.ProductUpAndDownFrameFacade;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */
public class ProductUpAndDownFrameFacadeImpl implements ProductUpAndDownFrameFacade {

    @Autowired
    private DicBiz dicBiz;
    @Autowired
    private PlatformOrgBiz platformOrgBiz;
    @Autowired
    private PlatformEmployeeBiz platformEmployeeBiz;
    /**
     * 跳转至产品管理页面
     * @param toListStateDTO
     * @return
     */
    @Override
    public ToListStateResult toListState(ToListStateDTO toListStateDTO) {
        ToListStateResult toListStateResult = new ToListStateResult();
        toListStateResult.setBrandList(dicBiz.getListByTypeCode(BasicConstants.CPXL_PP,toListStateDTO.getBizId()));
        toListStateResult.setOrgJsonStr(platformOrgBiz.getComponentOrgTreeJsonStr(toListStateDTO.getBizId()));
        toListStateResult.setOrgUserJsonStr(platformEmployeeBiz.getComponentOrgUserTreeJsonStr(toListStateDTO.getBizId()));
        return toListStateResult;
    }

    @Override
    public ToListStateResult toSearchListState(ToSearchListStateDTO toSearchListStateDTO) {
        return null;
    }
}
