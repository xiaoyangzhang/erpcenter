package com.yimayhd.erpcenter.dal.product.constants;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 *产品模块solr collection枚举
 *
 */
public enum ProductCollectionEnum {

    
    HOTEL_LIST("酒店列表", 1, "hotel"),
    HOTEL_DETAIL("酒店详情", 2, "hotelDetail"),
    SCENIC_LIST("景区列表", 3, "scenic")
    ;
    
    private String name;
    private int id;
    private String collection;

    ProductCollectionEnum(String name, int id, String collection) {
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

    
    public static ProductCollectionEnum getClientByCollection(String collection) {
        if(StringUtils.isBlank(collection)){
            return null;
        }
        for (ProductCollectionEnum solrdumpClient : ProductCollectionEnum.values()) {
            if (solrdumpClient.getCollection().equals(collection)) {
                return solrdumpClient;
            }
        }
        return null;
    }
    
    
    public static ProductCollectionEnum getClientById(int id) {
        for (ProductCollectionEnum solrdumpClient : ProductCollectionEnum.values()) {
            if (solrdumpClient.getId() == id) {
                return solrdumpClient;
            }
        }
        return null;
    }


}
