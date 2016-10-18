package com.yimayhd.erpcenter.dal.product.po;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.yimayhd.erpcenter.dal.product.constans.Constants;

public class ProductRouteSupplier implements Serializable {
    private static final long serialVersionUID = 5102126714474100748L;
    private Integer id;

    private Integer routeId;

    private Integer supplierType;

    private String supplierTypeName;

    private Integer subType;

    private Integer supplierId;

    private String supplierName;

    private Long createTime;


    private static final Map<Integer, String> supplierTypeMap = new HashMap<Integer, String>();
    static{
        supplierTypeMap.put(Constants.TRAVELAGENCY, "组团社社");
        supplierTypeMap.put(Constants.RESTAURANT, "餐厅");
        supplierTypeMap.put(Constants.HOTEL, "酒店");
        supplierTypeMap.put(Constants.FLEET, "车队");
        supplierTypeMap.put(Constants.SCENICSPOT, "景区");
        supplierTypeMap.put(Constants.SHOPPING, "购物");
        supplierTypeMap.put(Constants.ENTERTAINMENT, "娱乐");
        supplierTypeMap.put(Constants.GUIDE, "导游");
        supplierTypeMap.put(Constants.AIRTICKETAGENT, "机票");
        supplierTypeMap.put(Constants.TRAINTICKETAGENT, "火车票");
        supplierTypeMap.put(Constants.GOLF, "高尔夫");
        supplierTypeMap.put(Constants.OTHER, "其他");
        supplierTypeMap.put(Constants.CONTRACTAGREEMENT, "合同协议");
        supplierTypeMap.put(Constants.SUPPLIERCOMMENT, "商家评论");
        supplierTypeMap.put(Constants.INSURANCE, "保险");
        supplierTypeMap.put(Constants.LOCALTRAVEL, "地接社");

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(Integer supplierType) {
        this.supplierTypeName = supplierTypeMap.get(supplierType);
        this.supplierType = supplierType;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierTypeName() {
        return supplierTypeName;
    }

    public void setSupplierTypeName(String supplierTypeName) {
        this.supplierTypeName = supplierTypeName;
    }
}