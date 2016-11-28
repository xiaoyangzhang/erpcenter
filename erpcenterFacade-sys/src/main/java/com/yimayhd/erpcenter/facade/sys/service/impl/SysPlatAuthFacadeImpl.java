package com.yimayhd.erpcenter.facade.sys.service.impl;

import com.yimayhd.erpcenter.biz.sys.service.PlatAuthBiz;
import com.yimayhd.erpcenter.dal.sys.po.PlatAuth;
import com.yimayhd.erpcenter.facade.sys.result.PlatAuthListResult;
import com.yimayhd.erpcenter.facade.sys.service.SysPlatAuthFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */

public class SysPlatAuthFacadeImpl implements SysPlatAuthFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysPlatAuthFacadeImpl.class);
    @Autowired
    private PlatAuthBiz platAuthBiz;

    @Override
    public PlatAuthListResult findByBizIdAndOrgNotZero(Integer bizId) {

        PlatAuthListResult result = new PlatAuthListResult();
        try {
            List<PlatAuth> list =  platAuthBiz.findByBizIdAndOrgNotZero(bizId);
            result.setPlatAuthList(list);
        }catch (Exception e){
            result.setSuccess(false);
            LOGGER.error(e.getMessage());
        }
        return result;
    }
}
