package com.yimayhd.erpcenter.dal.sales.constants;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 
 *
 */
public enum SalesCollectionEnum {


    SALES_GROUPORDER("订单查询", 1, "saleOrder"),
    SALES_TOURGROUP("团查询", 2, "saleTour"),
    SALES_SALELIST("产品销售列表", 3, "productManager");
    
    private String name;
    private int id;
    private String collection;

    SalesCollectionEnum(String name, int id, String collection) {
        this.name = name;
        this.id = id;
        this.collection =  collection;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getCollection() {
        return collection;
    }

    
    public static SalesCollectionEnum getClientByCollection(String collection) {
        if(StringUtils.isBlank(collection)){
            return null;
        }
        for (SalesCollectionEnum solrdumpClient : SalesCollectionEnum.values()) {
            if (solrdumpClient.getCollection().equals(collection)) {
                return solrdumpClient;
            }
        }
        return null;
    }
    
    
    public static SalesCollectionEnum getClientById(int id) {
        for (SalesCollectionEnum solrdumpClient : SalesCollectionEnum.values()) {
            if (solrdumpClient.getId() == id) {
                return solrdumpClient;
            }
        }
        return null;
    }



}
