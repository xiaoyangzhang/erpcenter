package com.yimayhd.erpcenter.facade.sales.result;

import com.yihg.mybatis.utility.PageBean;
import com.yimayhd.erpcenter.dal.product.po.ProductGroupSupplier;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.*;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpcenter.dal.sys.po.SysBizBankAccount;
import com.yimayhd.erpresource.dal.po.SupplierContactMan;
import com.yimayhd.erpresource.dal.po.SupplierInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/28 9:55
 */
public class ToPreviewResult extends ResultSupport {
    private String guideString;
    private String guestGuideString;
    private String imgPath;
    private GroupOrder groupOrder;
    private  SupplierInfo supplier;
    private String company;
    private PlatformEmployeePo employee;
    private  List<GroupRoute> routeList;
    private List<GroupOrderPrice> priceList;
    private GroupOrderPrintPo gopp;
    private List<GroupOrderGuest> guests;
    private  List<GroupRequirement> grogShopList;
    private List<GroupOrderTransport> groupOrderTransports;

    private GroupOrderGuest groupOrderGuest;
    private GroupRoute groupRoute;
    private HashMap<String, Object> json;
    private List<AutocompleteInfo> infoList;
    private List<SupplierContactMan> supplierContactManList;
    private List<SupplierInfo> supplierInfos;
    private List<ProductGroupSupplier> supplierList;
    private GroupOrder staticInfo;
    private PageBean pageBean;
    private   List<SysBizBankAccount> sysBizBankAccountList;
    private GroupOrderPrice groupOrderPrice;

    public GroupOrderPrice getGroupOrderPrice() {
        return groupOrderPrice;
    }

    public void setGroupOrderPrice(GroupOrderPrice groupOrderPrice) {
        this.groupOrderPrice = groupOrderPrice;
    }

    public List<SysBizBankAccount> getSysBizBankAccountList() {
        return sysBizBankAccountList;
    }

    public void setSysBizBankAccountList(List<SysBizBankAccount> sysBizBankAccountList) {
        this.sysBizBankAccountList = sysBizBankAccountList;
    }

    public GroupOrderGuest getGroupOrderGuest() {
        return groupOrderGuest;
    }

    public void setGroupOrderGuest(GroupOrderGuest groupOrderGuest) {
        this.groupOrderGuest = groupOrderGuest;
    }

    public GroupRoute getGroupRoute() {
        return groupRoute;
    }

    public void setGroupRoute(GroupRoute groupRoute) {
        this.groupRoute = groupRoute;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public GroupOrder getStaticInfo() {
        return staticInfo;
    }

    public void setStaticInfo(GroupOrder staticInfo) {
        this.staticInfo = staticInfo;
    }

    public List<ProductGroupSupplier> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<ProductGroupSupplier> supplierList) {
        this.supplierList = supplierList;
    }

    public HashMap<String, Object> getJson() {
        return json;
    }

    public void setJson(HashMap<String, Object> json) {
        this.json = json;
    }

    public List<SupplierInfo> getSupplierInfos() {
        return supplierInfos;
    }

    public void setSupplierInfos(List<SupplierInfo> supplierInfos) {
        this.supplierInfos = supplierInfos;
    }

    public List<SupplierContactMan> getSupplierContactManList() {
        return supplierContactManList;
    }

    public void setSupplierContactManList(List<SupplierContactMan> supplierContactManList) {
        this.supplierContactManList = supplierContactManList;
    }

    public List<AutocompleteInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<AutocompleteInfo> infoList) {
        this.infoList = infoList;
    }

    public String getGuideString() {
        return guideString;
    }

    public void setGuideString(String guideString) {
        this.guideString = guideString;
    }

    public String getGuestGuideString() {
        return guestGuideString;
    }

    public void setGuestGuideString(String guestGuideString) {
        this.guestGuideString = guestGuideString;
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

    public GroupOrderPrintPo getGopp() {
        return gopp;
    }

    public void setGopp(GroupOrderPrintPo gopp) {
        this.gopp = gopp;
    }

    public List<GroupOrderGuest> getGuests() {
        return guests;
    }

    public void setGuests(List<GroupOrderGuest> guests) {
        this.guests = guests;
    }

    public List<GroupRequirement> getGrogShopList() {
        return grogShopList;
    }

    public void setGrogShopList(List<GroupRequirement> grogShopList) {
        this.grogShopList = grogShopList;
    }

    public List<GroupOrderTransport> getGroupOrderTransports() {
        return groupOrderTransports;
    }

    public void setGroupOrderTransports(List<GroupOrderTransport> groupOrderTransports) {
        this.groupOrderTransports = groupOrderTransports;
    }
}
