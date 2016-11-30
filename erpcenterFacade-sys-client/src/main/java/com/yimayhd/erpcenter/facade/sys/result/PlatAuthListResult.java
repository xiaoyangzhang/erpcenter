package com.yimayhd.erpcenter.facade.sys.result;

import com.yimayhd.erpcenter.dal.sys.po.PlatAuth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyong on 2016/11/28.
 */
public class PlatAuthListResult extends ResultSupport implements Serializable{

    private List<PlatAuth>  platAuthList  = new ArrayList<PlatAuth>();

    public void setPlatAuthList(List<PlatAuth> platAuthList) {
        this.platAuthList = platAuthList;
    }

    public List<PlatAuth> getPlatAuthList() {
        return platAuthList;
    }
}
