package com.yimayhd.erpcenter.dal.sales.client.taobao.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 淘宝交易返回字段
 */
public class TaobaoTrade implements Serializable {
    
    private static final long serialVersionUID = 184434306520797943L;
    
    private String tid;
    private String payment;
    private String post_fee;
    private String receiver_name;
    private String receiver_state;
    private String receiver_address;
    private String receiver_zip;
    private String receiver_mobile;
    private String receiver_phone;
    private String receiver_city;
    private String receiver_district;
    private Date created;
    private Date pay_time;
    private Date modified;
    private Date end_time;
    private String seller_memo;
    private String alipay_no;
    private String buyer_message;
    private String buyer_memo;
    private String alipay_id;
    private String buyer_alipay_no;
    private String buyer_nick;
    private String buyer_email;
    private String trade_from;
    private String status;
    private String type;
    private String step_trade_status;
    private String step_paid_fee;
    
    private String orders;
    
    public String getTid() {
        return tid;
    }
    
    public void setTid(String tid) {
        this.tid = tid;
    }
    
    public String getPayment() {
        return payment;
    }
    
    public void setPayment(String payment) {
        this.payment = payment;
    }
    
    public String getPost_fee() {
        return post_fee;
    }
    
    public void setPost_fee(String post_fee) {
        this.post_fee = post_fee;
    }
    
    public String getReceiver_name() {
        return receiver_name;
    }
    
    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }
    
    public String getReceiver_state() {
        return receiver_state;
    }
    
    public void setReceiver_state(String receiver_state) {
        this.receiver_state = receiver_state;
    }
    
    public String getReceiver_address() {
        return receiver_address;
    }
    
    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address;
    }
    
    public String getReceiver_zip() {
        return receiver_zip;
    }
    
    public void setReceiver_zip(String receiver_zip) {
        this.receiver_zip = receiver_zip;
    }
    
    public String getReceiver_mobile() {
        return receiver_mobile;
    }
    
    public void setReceiver_mobile(String receiver_mobile) {
        this.receiver_mobile = receiver_mobile;
    }
    
    public String getReceiver_phone() {
        return receiver_phone;
    }
    
    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }
    
    public String getReceiver_city() {
        return receiver_city;
    }
    
    public void setReceiver_city(String receiver_city) {
        this.receiver_city = receiver_city;
    }
    
    public String getReceiver_district() {
        return receiver_district;
    }
    
    public void setReceiver_district(String receiver_district) {
        this.receiver_district = receiver_district;
    }
    
    public Date getCreated() {
        return created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }
    
    public Date getPay_time() {
        return pay_time;
    }
    
    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
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
    
    public String getSeller_memo() {
        return seller_memo;
    }
    
    public void setSeller_memo(String seller_memo) {
        this.seller_memo = seller_memo;
    }
    
    public String getAlipay_no() {
        return alipay_no;
    }
    
    public void setAlipay_no(String alipay_no) {
        this.alipay_no = alipay_no;
    }
    
    public String getBuyer_message() {
        return buyer_message;
    }
    
    public void setBuyer_message(String buyer_message) {
        this.buyer_message = buyer_message;
    }
    
    public String getBuyer_memo() {
        return buyer_memo;
    }
    
    public void setBuyer_memo(String buyer_memo) {
        this.buyer_memo = buyer_memo;
    }
    
    public String getAlipay_id() {
        return alipay_id;
    }
    
    public void setAlipay_id(String alipay_id) {
        this.alipay_id = alipay_id;
    }
    
    public String getBuyer_alipay_no() {
        return buyer_alipay_no;
    }
    
    public void setBuyer_alipay_no(String buyer_alipay_no) {
        this.buyer_alipay_no = buyer_alipay_no;
    }
    
    public String getBuyer_nick() {
        return buyer_nick;
    }
    
    public void setBuyer_nick(String buyer_nick) {
        this.buyer_nick = buyer_nick;
    }
    
    public String getBuyer_email() {
        return buyer_email;
    }
    
    public void setBuyer_email(String buyer_email) {
        this.buyer_email = buyer_email;
    }
    
    public String getTrade_from() {
        return trade_from;
    }
    
    public void setTrade_from(String trade_from) {
        this.trade_from = trade_from;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getStep_trade_status() {
        return step_trade_status;
    }
    
    public void setStep_trade_status(String step_trade_status) {
        this.step_trade_status = step_trade_status;
    }
    
    public String getStep_paid_fee() {
        return step_paid_fee;
    }
    
    public void setStep_paid_fee(String step_paid_fee) {
        this.step_paid_fee = step_paid_fee;
    }
    
    public String getOrders() {
        return orders;
    }
    
    public void setOrders(String orders) {
        this.orders = orders;
    }
}
