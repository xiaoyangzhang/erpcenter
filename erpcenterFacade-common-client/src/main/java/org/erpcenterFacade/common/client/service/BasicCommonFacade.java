package org.erpcenterFacade.common.client.service;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;

import java.util.List;

/**
 * Created by DELL on 2016/12/29.
 */
public interface BasicCommonFacade {

    public List<DicInfo> getProductDesc(String typeCode,Integer bizId);

}
