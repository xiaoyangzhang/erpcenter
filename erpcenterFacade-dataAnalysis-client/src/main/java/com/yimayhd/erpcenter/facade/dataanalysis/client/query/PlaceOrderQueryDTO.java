package com.yimayhd.erpcenter.facade.dataanalysis.client.query;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName: ${ClassName}
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Auther xueshengdong
 * @Date 2016/12/1 15:23
 */
public class PlaceOrderQueryDTO implements Serializable {
    private static final long serialVersionUID = -8889481280036904592L;

    private Integer bizId;
    private Map paramters;

    private String citysSupplierIds;
    private String supplierLevel;
    private String sl;
    private String ssl;
    private String rp;
    private Integer page;
    private Integer pageSize;
    private String svc;
    private Integer visit;



    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public Map getParamters() {
        return paramters;
    }

    public void setParamters(Map paramters) {
        this.paramters = paramters;
    }

    public String getCitysSupplierIds() {
        return citysSupplierIds;
    }

    public void setCitysSupplierIds(String citysSupplierIds) {
        this.citysSupplierIds = citysSupplierIds;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getSsl() {
        return ssl;
    }

    public void setSsl(String ssl) {
        this.ssl = ssl;
    }

    public String getRp() {
        return rp;
    }

    public void setRp(String rp) {
        this.rp = rp;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSvc() {
        return svc;
    }

    public void setSvc(String svc) {
        this.svc = svc;
    }

    public Integer getVisit() {
        return visit;
    }

    public void setVisit(Integer visit) {
        this.visit = visit;
    }

    public String getSupplierLevel() {
        return supplierLevel;
    }

    public void setSupplierLevel(String supplierLevel) {
        this.supplierLevel = supplierLevel;
    }
}
