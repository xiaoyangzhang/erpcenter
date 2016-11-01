package com.yimayhd.erpcenter.facade.sales.result;

import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderGuest;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrice;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupRoute;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;
import com.yimayhd.erpresource.dal.po.SupplierInfo;

import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/27 19:58
 */
public class ToSaleChargeResult extends ResultSupport {
    private List<GroupOrderGuest> guests;
    private String imgPath;
    private GroupOrder groupOrder;
    private SupplierInfo supplier;
    private String company;
    private PlatformEmployeePo employee;
    private List<GroupRoute> routeList;
    private List<GroupOrderPrice> priceList;
    private List<SysBizBankAccount> accountList;
    private String otherPrice;

    public String getOtherPrice() {
        return otherPrice;
    }

    public void setOtherPrice(String otherPrice) {
        this.otherPrice = otherPrice;
    }

    public List<GroupOrderGuest> getGuests() {
        return guests;
    }

    public void setGuests(List<GroupOrderGuest> guests) {
        this.guests = guests;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public GroupOrder getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(GroupOrder groupOrder) {
        this.groupOrder = groupOrder;
    }

    public SupplierInfo getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierInfo supplier) {
        this.supplier = supplier;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public PlatformEmployeePo getEmployee() {
        return employee;
    }

    public void setEmployee(PlatformEmployeePo employee) {
        this.employee = employee;
    }

    public List<GroupRoute> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<GroupRoute> routeList) {
        this.routeList = routeList;
    }

    public List<GroupOrderPrice> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<GroupOrderPrice> priceList) {
        this.priceList = priceList;
    }

    public List<SysBizBankAccount> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<SysBizBankAccount> accountList) {
        this.accountList = accountList;
    }
}
