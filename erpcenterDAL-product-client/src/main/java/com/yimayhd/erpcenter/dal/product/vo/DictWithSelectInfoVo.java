package com.yimayhd.erpcenter.dal.product.vo;

import com.yimayhd.erpcenter.dal.basic.po.DicInfo;


/**
 * Created by ZhengZiyu on 2015/7/7.
 */
public class DictWithSelectInfoVo extends DicInfo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean selected;

    public DictWithSelectInfoVo(DicInfo dicInfo, Boolean selected){
        this.setId(dicInfo.getId());
        this.setCode(dicInfo.getCode());
        this.setNote(dicInfo.getNote());
        this.setOrderId(dicInfo.getOrderId());
        this.setStatus(dicInfo.getStatus());
        this.setTypeCode(dicInfo.getTypeCode());
        this.setTypeId(dicInfo.getTypeId());
        this.setValue(dicInfo.getValue());
        this.selected = selected;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

}
