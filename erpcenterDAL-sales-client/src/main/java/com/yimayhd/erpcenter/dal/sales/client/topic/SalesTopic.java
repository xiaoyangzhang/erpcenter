package com.yimayhd.erpcenter.dal.sales.client.topic;


public enum SalesTopic {

    GROUP_ORDER_UPDATED(SalesTopic.TOPIC,"ORDER_UPDATED","团订单修改"),
	
    TOUR_GROUP_UPDATED(SalesTopic.TOPIC,"GROUP_UPDATED","团信息修改"),
    
    ORDER_GUEST_UPDATED(SalesTopic.TOPIC,"GUEST_UPDATED","游客信息修改"),
    
    ORDER_PRICE_UPDATED(SalesTopic.TOPIC,"PRICE_UPDATED","订单价格信息修改");
	
    public static final String TOPIC = "ERP_SALES";
    /**
     * 主题
     */
    private String topic;

    /**
     * 标签
     */
    private String tags;

    /**
     * 描述
     */
    private String desc;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private SalesTopic(String topic, String tags, String desc) {
        this.topic = topic;
        this.tags = tags;
        this.desc = desc;
    }
}
