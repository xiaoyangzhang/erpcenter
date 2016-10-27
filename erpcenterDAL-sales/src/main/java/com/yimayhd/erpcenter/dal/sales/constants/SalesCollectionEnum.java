package com.yimayhd.erpcenter.dal.sales.constants;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 
 *
 */
public enum SalesCollectionEnum {

    PRODUCT_STATE("产品状态", 1, "productManager"),PRODUCT_STOCK("产品状态", 2, "productStock")
    ;
    
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
