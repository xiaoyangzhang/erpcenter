package com.yimayhd.erpcenter.dal.product.topic;


public enum ProductTopic {

    PRODUCT_MODIFY(ProductTopic.TOPIC,"PRODUCT_MODIFY","产品信息修改");
	
    public static final String TOPIC = "ERP_PRODUCT";
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

    private ProductTopic(String topic, String tags, String desc) {
        this.topic = topic;
        this.tags = tags;
        this.desc = desc;
    }
}
