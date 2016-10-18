package com.yimayhd.erpcenter.facade.service;

import com.yimayhd.erpcenter.facade.query.DetailDTO;
import com.yimayhd.erpcenter.facade.query.ToListStateDTO;
import com.yimayhd.erpcenter.facade.query.ToSearchListStateDTO;
import com.yimayhd.erpcenter.facade.query.UpStateDTO;
import com.yimayhd.erpcenter.facade.result.DetailResult;
import com.yimayhd.erpcenter.facade.result.ToListStateResult;
import com.yimayhd.erpcenter.facade.result.UpStateResult;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/18 18:08
 */
public interface ProductUpAndDownFrameFacade {
    /**
     * 加载
     * 产品名称:部门:计调
     *
     * @param toListStateDTO
     * @return
     */
    public ToListStateResult toListState(ToListStateDTO toListStateDTO);

    /**
     * 产品列表查询
     *
     * @param toSearchListStateDTO
     * @return
     */
    public ToListStateResult toSearchListState(ToSearchListStateDTO toSearchListStateDTO);


    /**
     * 产品上下架
     *
     * @param upStateDTO
     * @return
     */
    public UpStateResult upState(UpStateDTO upStateDTO);

    /**
     * 产品详细
     *
     * @param detailDTO
     * @return
     */
    public DetailResult detail(DetailDTO detailDTO);
}
