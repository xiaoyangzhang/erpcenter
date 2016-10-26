package com.yimayhd.erpcenter.dal.sales.client.taobao.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 淘宝交易中订单返回字段
 */
public class TaobaoTradeOrder implements Serializable {
    
    private static final long serialVersionUID = 5617674836661107091L;
    
    private String tid;
    private String oid;
    private String status;
    private String num_iid;
    private String title;
    private String price;
    private String sku_id;
    private String order_from;
    private Date modified;
    private Date end_time;
    private Integer num;
    private String total_fee;
    private String payment;
    private String discount_fee;
    private String adjust_fee;
    private String sku_properties_name;
    private String refund_status;
    private String outer_iid;
    private String refund_id;
    
    public String getTid() {
        return tid;
    }
    
    public void setTid(String tid) {
        this.tid = tid;
    }
    
    public String getOid() {
        return oid;
    }
    
    public void setOid(String oid) {
        this.oid = oid;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getNum_iid() {
        return num_iid;
    }
    
    public void setNum_iid(String num_iid) {
        this.num_iid = num_iid;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getPrice() {
        return price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
    
    public String getSku_id() {
        return sku_id;
    }
    
    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }
    
    public String getOrder_from() {
        return order_from;
    }
    
    public void setOrder_from(String order_from) {
        this.order_from = order_from;
    }
    
    public Date getModified() {
        return modified;
    }
    
    public void setModified(Date modified) {
        this.modified = modified;
    }
    
    public Date getEnd_time() {
        return end_time;
    }
    
    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }
    
    public Integer getNum() {
        return num;
    }
    
    public void setNum(Integer num) {
        this.num = num;
    }
    
    public String getTotal_fee() {
        return total_fee;
    }
    
    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }
    
    public String getPayment() {
        return payment;
    }
    
    public void setPayment(String payment) {
        this.payment = payment;
    }
    
    public String getDiscount_fee() {
        return discount_fee;
    }
    
    public void setDiscount_fee(String discount_fee) {
        this.discount_fee = discount_fee;
    }
    
    public String getAdjust_fee() {
        return adjust_fee;
    }
    
    public void setAdjust_fee(String adjust_fee) {
        this.adjust_fee = adjust_fee;
    }
    
    public String getSku_properties_name() {
        return sku_properties_name;
    }
    
    public void setSku_properties_name(String sku_properties_name) {
        this.sku_properties_name = sku_properties_name;
    }
    
    public String getRefund_status() {
        return refund_status;
    }
    
    public void setRefund_status(String refund_status) {
        this.refund_status = refund_status;
    }
    
    public String getOuter_iid() {
        return outer_iid;
    }
    
    public void setOuter_iid(String outer_iid) {
        this.outer_iid = outer_iid;
    }
    
    public String getRefund_id() {
        return refund_id;
    }
    
    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }
}
