package com.yimayhd.erpcenter.dal.product.solrdump.enums;

import org.apache.commons.lang3.StringUtils;



public enum SolrdumpClient {
    
    HOTEL_LIST("上下架列表", 1, "productState"),
    HOTEL_DETAIL("库存列表", 2, "productStock");

    
    private String name;
    private int id;
    private String collection;

    SolrdumpClient(String name, int id, String collection) {
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

    
    public static SolrdumpClient getClientByCollection(String collection) {
        if(StringUtils.isBlank(collection)){
            return null;
        }
        for (SolrdumpClient solrdumpClient : SolrdumpClient.values()) {
            if (solrdumpClient.getCollection().equals(collection)) {
                return solrdumpClient;
            }
        }
        return null;
    }
    
    
    public static SolrdumpClient getClientById(int id) {
        for (SolrdumpClient solrdumpClient : SolrdumpClient.values()) {
            if (solrdumpClient.getId() == id) {
                return solrdumpClient;
            }
        }
        return null;
    }

}
