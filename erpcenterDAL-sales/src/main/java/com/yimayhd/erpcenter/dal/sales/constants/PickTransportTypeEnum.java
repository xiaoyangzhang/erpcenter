package com.yimayhd.erpcenter.dal.sales.constants;


public enum PickTransportTypeEnum {

    PICK("接机", 0),
    SEND("送机", 1);
    
    private String name;
    private int id;

    PickTransportTypeEnum(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    
    public static PickTransportTypeEnum getClientById(int id) {
        for (PickTransportTypeEnum pickTransportTypeEnum : PickTransportTypeEnum.values()) {
            if (pickTransportTypeEnum.getId() == id) {
                return pickTransportTypeEnum;
            }
        }
        return null;
    }

}
