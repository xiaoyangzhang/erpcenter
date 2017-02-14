package com.yimayhd.erpcenter.facade.common.service.impl;

import com.yimayhd.erpcenter.biz.basic.service.DicBiz;
import com.yimayhd.erpcenter.dal.basic.po.DicInfo;
import org.erpcenterFacade.common.client.service.BasicCommonFacade;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by DELL on 2016/12/29.
 */
public class BasicCommonFacadeImpl implements BasicCommonFacade {
    @Autowired
    private DicBiz dicBiz;
    @Override
    public List<DicInfo> getProductDesc(String typeCode, Integer bizId) {
        return dicBiz.getListByTypeCode(typeCode,bizId);
    }

    @Override
    public List<DicInfo> getListByTypeCode(String typeCode, Integer bizId) {
        return getProductDesc(typeCode, bizId);
    }

    @Override
    public List<DicInfo> getListByTypeCode(String typeCode) {
        return dicBiz.getListByTypeCode(typeCode);
    }
}
