package com.yimayhd.erpcenter.facade.service;

import com.yimayhd.erpcenter.facade.query.ToListStateDTO;
import com.yimayhd.erpcenter.facade.result.ToListStateResult;

/**
 * Created by Administrator on 2016/10/17.
 */
public interface ProductUpAndDownFrameFacade {
    public ToListStateResult toListState(ToListStateDTO toListStateDTO);
}
