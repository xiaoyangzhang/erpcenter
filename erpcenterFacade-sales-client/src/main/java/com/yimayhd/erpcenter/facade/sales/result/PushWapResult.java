package com.yimayhd.erpcenter.facade.sales.result;

import com.yimayhd.erpcenter.dal.sales.client.sales.vo.AssistantGroupVO;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.AssistantSupplierVO;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/27 17:24
 */
public class PushWapResult extends ResultSupport {
    //团信息
    private AssistantGroupVO groupVo;
    //团行程用到的供应商及图片信息
    private AssistantSupplierVO supplierVo;

    public AssistantSupplierVO getSupplierVo() {
        return supplierVo;
    }

    public void setSupplierVo(AssistantSupplierVO supplierVo) {
        this.supplierVo = supplierVo;
    }

    public AssistantGroupVO getGroupVo() {
        return groupVo;
    }

    public void setGroupVo(AssistantGroupVO groupVo) {
        this.groupVo = groupVo;
    }
}
