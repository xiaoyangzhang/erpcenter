package com.yimayhd.erpcenter.facade.sales.result;

import com.yimayhd.erpcenter.dal.sales.client.operation.po.BookingGuide;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrder;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.GroupOrderPrintPo;
import com.yimayhd.erpcenter.dal.sales.client.sales.po.TourGroup;
import com.yimayhd.erpcenter.dal.sales.client.sales.vo.GroupPriceVo;
import com.yimayhd.erpcenter.dal.sys.po.PlatformEmployeePo;
import com.yimayhd.erpresource.dal.po.SupplierInfo;

import java.util.List;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/10/27 19:37
 */
public class ToSKChargePreviewResult extends ResultSupport {
    private GroupOrder supplier;
    private List<BookingGuide> guides;
    private List<GroupOrderPrintPo> gopps;
    private List<GroupPriceVo> vos;
    private List<SupplierInfo> supplierList;
    private PlatformEmployeePo po;
    private String imgPath;
    private TourGroup tour;

    public GroupOrder getSupplier() {
        return supplier;
    }

    public void setSupplier(GroupOrder supplier) {
        this.supplier = supplier;
    }

    public List<BookingGuide> getGuides() {
        return guides;
    }

    public void setGuides(List<BookingGuide> guides) {
        this.guides = guides;
    }

    public List<GroupOrderPrintPo> getGopps() {
        return gopps;
    }

    public void setGopps(List<GroupOrderPrintPo> gopps) {
        this.gopps = gopps;
    }

    public List<GroupPriceVo> getVos() {
        return vos;
    }

    public void setVos(List<GroupPriceVo> vos) {
        this.vos = vos;
    }

    public List<SupplierInfo> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<SupplierInfo> supplierList) {
        this.supplierList = supplierList;
    }

    public PlatformEmployeePo getPo() {
        return po;
    }

    public void setPo(PlatformEmployeePo po) {
        this.po = po;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public TourGroup getTour() {
        return tour;
    }

    public void setTour(TourGroup tour) {
        this.tour = tour;
    }
}
