package com.yimayhd.erpcenter.facade.sales.result;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;
import com.yimayhd.erpresource.dal.po.SupplierInfo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/27 18:57
 */
public class BookingProfitTableResult extends ResultSupport {
    private PageBean pageBean;

    public Map<String, Object> getSum() {
        return sum;
    }

    public void setSum(Map<String, Object> sum) {
        this.sum = sum;
    }

    private Map<String, Object> sum;

    private  List<SysBizBankAccount> sysBizBankAccountList;

    private SupplierInfo supplierInfo;

    public SupplierInfo getSupplierInfo() {
        return supplierInfo;
    }

    public void setSupplierInfo(SupplierInfo supplierInfo) {
        this.supplierInfo = supplierInfo;
    }

    public List<SysBizBankAccount> getSysBizBankAccountList() {
        return sysBizBankAccountList;
    }

    public void setSysBizBankAccountList(List<SysBizBankAccount> sysBizBankAccountList) {
        this.sysBizBankAccountList = sysBizBankAccountList;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

}
