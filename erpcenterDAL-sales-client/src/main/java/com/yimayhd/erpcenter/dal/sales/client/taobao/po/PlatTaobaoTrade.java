package com.yimayhd.erpcenter.dal.sales.client.taobao.po;

import java.io.Serializable;
import java.util.Date;

public class PlatTaobaoTrade implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6261869917654884981L;

	private Integer id;

    private Integer bizId;

    private String tid;

    private Integer orderId;

    private String payment;

    private String postFee;

    private String receiverName;

    private String receiverState;

    private String receiverAddress;

    private String receiverZip;

    private String receiverMobile;

    private String receiverPhone;

    private String receiverCity;

    private String receiverDistrict;

    private Date created;

    private Date payTime;

    private Date modified;

    private Date endTime;

    private String alipayNo;

    private String buyerMemo;

    private String alipayId;

    private String buyerAlipayNo;

    private String buyerNick;

    private String buyerEmail;

    private String tradeFrom;

    private String status;

    private String type;

    private String stepTradeStatus;

    private String stepPaidFee;

    private Date syncTime;

    private String syncUsername;

    private String myState;

    private String myStoreId;
    private String sellerMemo;
    private String buyerMessage;
    
    private String title;
    
    private String skuPropertiesName;
    
    private String outerIid;
    
    private String price;
    
    private Integer num;
    
    private String oid;
    
	private Integer pageSize;
	private Integer page = 1;

    

	public Integer getPageSize() {
		return pageSize;
	}


	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


	public Integer getPage() {
		return page;
	}


	public void setPage(Integer page) {
		this.page = page;
	}


	public String getOuterIid() {
		return outerIid;
	}


	public void setOuterIid(String outerIid) {
		this.outerIid = outerIid;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public Integer getNum() {
		return num;
	}


	public void setNum(Integer num) {
		this.num = num;
	}


	public String getOid() {
		return oid;
	}


	public void setOid(String oid) {
		this.oid = oid;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getSkuPropertiesName() {
		return skuPropertiesName;
	}


	public void setSkuPropertiesName(String skuPropertiesName) {
		this.skuPropertiesName = skuPropertiesName;
	}

	public String getSellerMemo() {
        return sellerMemo;
    }

    public void setSellerMemo(String sellerMemo) {
        this.sellerMemo = sellerMemo == null ? null : sellerMemo.trim();
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage == null ? null : buyerMessage.trim();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
    }

    public String getPostFee() {
        return postFee;
    }

    public void setPostFee(String postFee) {
        this.postFee = postFee == null ? null : postFee.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState == null ? null : receiverState.trim();
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    public String getReceiverZip() {
        return receiverZip;
    }

    public void setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip == null ? null : receiverZip.trim();
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile == null ? null : receiverMobile.trim();
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity == null ? null : receiverCity.trim();
    }

    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict == null ? null : receiverDistrict.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getAlipayNo() {
        return alipayNo;
    }

    public void setAlipayNo(String alipayNo) {
        this.alipayNo = alipayNo == null ? null : alipayNo.trim();
    }

    public String getBuyerMemo() {
        return buyerMemo;
    }

    public void setBuyerMemo(String buyerMemo) {
        this.buyerMemo = buyerMemo == null ? null : buyerMemo.trim();
    }

    public String getAlipayId() {
        return alipayId;
    }

    public void setAlipayId(String alipayId) {
        this.alipayId = alipayId == null ? null : alipayId.trim();
    }

    public String getBuyerAlipayNo() {
        return buyerAlipayNo;
    }

    public void setBuyerAlipayNo(String buyerAlipayNo) {
        this.buyerAlipayNo = buyerAlipayNo == null ? null : buyerAlipayNo.trim();
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick == null ? null : buyerNick.trim();
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail == null ? null : buyerEmail.trim();
    }

    public String getTradeFrom() {
        return tradeFrom;
    }

    public void setTradeFrom(String tradeFrom) {
        this.tradeFrom = tradeFrom == null ? null : tradeFrom.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStepTradeStatus() {
        return stepTradeStatus;
    }

    public void setStepTradeStatus(String stepTradeStatus) {
        this.stepTradeStatus = stepTradeStatus == null ? null : stepTradeStatus.trim();
    }

    public String getStepPaidFee() {
        return stepPaidFee;
    }

    public void setStepPaidFee(String stepPaidFee) {
        this.stepPaidFee = stepPaidFee == null ? null : stepPaidFee.trim();
    }

    public Date getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(Date syncTime) {
        this.syncTime = syncTime;
    }

    public String getSyncUsername() {
        return syncUsername;
    }

    public void setSyncUsername(String syncUsername) {
        this.syncUsername = syncUsername == null ? null : syncUsername.trim();
    }

    public String getMyState() {
        return myState;
    }

    public void setMyState(String myState) {
        this.myState = myState == null ? null : myState.trim();
    }

    public String getMyStoreId() {
        return myStoreId;
    }

    public void setMyStoreId(String myStoreId) {
        this.myStoreId = myStoreId == null ? null : myStoreId.trim();
    }
}